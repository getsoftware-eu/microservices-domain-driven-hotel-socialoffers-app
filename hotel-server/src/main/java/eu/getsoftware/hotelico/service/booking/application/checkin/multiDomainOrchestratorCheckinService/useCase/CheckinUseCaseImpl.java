package eu.getsoftware.hotelico.service.booking.application.checkin.multiDomainOrchestratorCheckinService.useCase;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseRequestDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.HotelDTO;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.amqpConsumeNotification.ChatMessageCommand;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.amqpConsumeNotification.SocketNotificationCommand;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.innerevents.InnerDomainEventImpl;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.exception.domain.BusinessException;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.gateways.CheckinGatewayService;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.mapper.CheckinDtoMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.mapper.CheckinEntityMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.messaging.CheckinMessagePublisher;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.gateways.CustomerGatewayService;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.mapper.CustomerDtoMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.mapper.HotelDtoMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.mapper.HotelDtoMapperImpl;
import eu.getsoftware.hotelico.service.booking.application.chat.port.out.IChatService;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.model.CheckinDomainFactory;
import eu.getsoftware.hotelico.service.booking.application.checkin.multiDomainOrchestratorCheckinService.useCase.handler.CreateHotelCheckinHandler;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.in.queryservice.CheckinInDTOQueryService;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.in.usecase.CheckinUseCase;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.out.CheckinOutEntityQueryService;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.out.CheckinOutWriteService;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.out.GPSValidationHandler;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.customDomainModelImpl.HotelRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.hotel.port.in.NotificationUseCase;
import eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService.IHotelService;
import eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService.IMessagingProducerService;
import eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService.IWallpostService;
import eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService.LastMessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Architecture: Application Service that uses multiply domain services (is portService = domainService?)
 * <br/>
 * 
 * User story:
 * As [Customer], Bob want to make a [checkin()] in [hotel] NY, in order to attend to social interaction within a hotel.
 * 
 * UseCase: 
 *  - Name: "new Hotel-Checkin"
 *  - Short description: "Customer makes a checkin in a selected hotel"
 *  - Actors: "Customer", "Hotelico App"
 *  - Layer: clouds (top-view)
 *  - Trigger: Customer selects a hotel from the list and confirms the selection
 *  - Pre-Condition: 
 *  				1. Customer is registered and has no active checkin.
 *  				2. Customer opens a pre-selected list of hotels nearby and confirms his selection
 *  				3. Customer set the start and end date of checkin	
 *  				
 *  - Primary flow: 1. [Hotel] validates the Checkin-data and GPS-Coordinates of the customer
 *  				2. The created [checkin]-[notification] will be propagated to customer
 *  				3. Checkin-[event] is published in the system
 *  				   -sub : Hotel [notification] is published in a hotel-feed
 *  				4. Auto-generated [chat message] from the hotel-staff contact will be sent to customer
 *  			    5. hotel [offers feed] and to a hotel [social main-chat] will be shown to customer		
 *  				 
 *  - Alternative flow:	
 *  				1a. [GPS-Area] of customer is wrong and [checkin status] "waiting back" is shown on the main page 
 *  			
 *  - Flow with errors:		
 *  				2a. not correct [checkin dates] of customer : date selection will be reloaded with an error message
 *  				3a. the [requirements of the selected hotel] are ot fulfilled : Customer receives an error 	
 * 
 *  - Result:       page with the [social board] of the selected hotel will be shown to customer 
 *  
 *  - After condition: Checkin persisted in DB
 *
 *  - UseCase notes: Customer inputs hotelId, checkin request (dates, hotel) will be validated, 
 *  				new checkin-instance is created, older checkins disabled if needed, 
 * 					checkin-notification is published (+notificationInHotelFeed +initChatMessageToCustomer) 
 * 
 * - Invariante: 	only 1 active [checkin] for the given [customer] in the system
 * 
 * Created by e.fanshil
 * At 03.02.2016 14:04
 */
@Service
@RequiredArgsConstructor
class CheckinUseCaseImpl implements CheckinUseCase
{
	private final CheckinGatewayService userGatewayService;
	private final CustomerGatewayService customerGatewayService;
	private final HotelDtoMapperImpl hotelDtoMapperImpl;

	private IMessagingProducerService<InnerDomainEventImpl> messagingProducerService;		
	
	private LastMessagesService lastMessagesService;	
			
	private IHotelService<HotelRootDomainEntity> hotelService;			
	
	private CheckinInDTOQueryService checkinDTOQueryService;		
	
	private IChatService chatService;	
	
	private IWallpostService wallpostService;	
		
	private NotificationUseCase notificationUseCase;
	
	private GPSValidationHandler gpsValidationHandler;
	
	private CheckinMessagePublisher checkinMessagePublisher;
	private CheckinDtoMapper checkinDtoMapper;

	private CheckinEntityMapper checkinEntityMapper;
	private final CheckinDomainFactory checkinDomainFactory = new CheckinDomainFactory(checkinEntityMapper);
	private final CustomerDtoMapper customerDtoMapper;
	private final HotelDtoMapper hotelDtoMapper;
	private CheckinGatewayService checkinGatewayService;
	private CheckinOutEntityQueryService checkinOutEntityQueryService;
    private CheckinOutWriteService checkinWriteService;

    @Transactional
	@Override
	public CheckinUseCaseDTO createCustomerCheckin(@Validated CheckinUseCaseRequestDTO customerRequestDto) {

		// UseCase : Primary-flow
		//eu: error: not validate DTO!!! DTO validates itself!!!! 
		// validateCheckin(customerRequestDto); // UseCase.Primary-flow.step.1
		
		validateCustomerWithGPSLocationService(customerRequestDto.customerId(), customerRequestDto.hotelId()); //UseCase.Primary-flow.step.2

		CheckinRootDomainEntity entity = createCheckinFromDto(customerRequestDto); //UseCase.Primary-flow.step.3

		userGatewayService.saveToDb(entity);
		
		sendInitMessageFromHotelStaffToCustomer(entity); //UseCase.Primary-flow.step.4
		
		CheckinUseCaseDTO checkinResponseDTO = checkinDtoMapper.toDto(entity); //UseCase.Primary-flow.step.5
		
		//eu: error: create no commands to other domains, they receive event and execute own logik!!!
		sendHotelNewGuestSocketNotificationCommand(checkinResponseDTO);
		
		return checkinResponseDTO;
	}


	@Override
	public CheckinRootDomainEntity createCheckinFromDto(CheckinUseCaseRequestDTO checkinRequestDto) {

		CustomerRootDomainEntity customerDomainEntity = customerGatewayService.findOrThrow(checkinRequestDto.customerId());
//				.orElseThrow(() -> new BusinessException("Customer " + checkinRequestDto.customerId()+ " not found"));

		var hotelEntity = checkinOutEntityQueryService.getHotelEntityFromCheckinRequest(checkinRequestDto)
				.orElseThrow(() -> new BusinessException("Hotel " + checkinRequestDto + " wurde nicht gefunden"));

		boolean isFullCheckin = hotelEntity.isVirtual;

		boolean isDemoCheckin = AppConfigProperties.HOTEL_DEMO_CODE.equalsIgnoreCase(hotelEntity.getCurrentHotelAccessCode());
		boolean checkinDateIsValid = AppConfigProperties.NO_CHECKOUT_FOR_DEMOHOTEL && isDemoCheckin || checkinRequestDto.checkinDatesAreValid();

		isFullCheckin = isFullCheckin || isDemoCheckin;// set id DTO getter! || ControllerUtils.CHECKIN_FULL_ALWAYS;

//		CheckinDTO checkinResponseDto = new CheckinDTO(checkinRequestDto.getCustomerId(), checkinRequestDto.getHotelId());

		CheckinRootDomainEntity newCheckinEntity = this.createCheckin(customerDtoMapper.toDto(customerDomainEntity), hotelDtoMapper.toDto(hotelEntity), isFullCheckin); //TODO persist now or later??

		List<CheckinRootDomainEntity> activeCustomerCheckins = checkinOutEntityQueryService.getActiveByCustomerId(customerDomainEntity.getDomainEntityId(), new Date());

		//If checkin exists, 
		if(!hotelEntity.isVirtual && checkinDateIsValid)
		{
			LocalDate lastSameHotelCheckinDate = checkinOutEntityQueryService.getLastByCustomerAndHotelId(customerDomainEntity.getDomainEntityId(), hotelEntity.getDomainEntityId());

			var hotelDto = hotelService.getHotelById(hotelEntity.getDomainEntityId()); //checkinRequestDto.setHotelId(hotelRootEntity.getId()); //eu: NO setters for parameter!!!!

			CheckinRootDomainEntity nowGoodCheckin = null;

			//if no checkin exists, create a new one
			if(activeCustomerCheckins.isEmpty())
			{
				nowGoodCheckin =  new CreateHotelCheckinHandler().handle(checkinRequestDto, customerDtoMapper.toDto(customerDomainEntity), hotelDtoMapper.toDto(hotelEntity), isFullCheckin);

//				checkinResponseDto.setErrorResponse("");

				//Inform others!!!
				if(!customerDomainEntity.isHotelStaff() && !customerDomainEntity.isAdmin())
				{
//					checkinResponseDto.setHotelId(hotelEntity.getId());
//					notificationUseCase.notificateAboutEntityEventWebSocket(checkinResponseDto, hotelEvent.getEventCheckin(), "New check-in in your hotel", hotelRootEntity.getId());
				}

				//sent welcome message to new fullCheckin customers
				if(!customerDomainEntity.isHotelStaff() && !customerDomainEntity.isAdmin())
				{
					CustomerDTO staffSender = checkinDTOQueryService.getStaffFirstByHotelId(hotelEntity.getDomainEntityId());

					if (staffSender!=null)
					{
						//TODO EUGEN: Check here if I already sent a message to him
						// ### SEND Welcome MESSAGE

						CustomerDTO customerDTO = customerDtoMapper.toDtoWithHotelInfo(customerDomainEntity, hotelEntity.getDomainEntityId());
						chatService.sendFirstChatMessageOnDemand(customerDTO, staffSender, isFullCheckin);

						HotelDTO hotelDTO = hotelService.getHotelByDomainId(hotelEntity.getDomainEntityId());
						wallpostService.sendNotificationWallpostOnDemand(customerDTO, lastSameHotelCheckinDate, hotelDto, staffSender);

					}
				}

			}
			else //correct actual checkin, if the same hotel
			{
				//EUGEN: only update checkin, no event

				for (CheckinRootDomainEntity nextCheckin : activeCustomerCheckins)
				{
					if(hotelEntity.equals(nextCheckin.getHotelDomainEntityId())) //if the checkin of actual hotel, NO HOTEL EVENT!
					{
						updateHotelCheckin(checkinRequestDto, customerDomainEntity, nextCheckin, isFullCheckin);
//						checkinResponseDto.setErrorResponse("");
						break;
					}
				}
			}


		}
		else{ //if no hotel more, maybe cancel actual checkin

			for (CheckinRootDomainEntity nextCheckin : activeCustomerCheckins)
			{
				nextCheckin.setActive(false);

				//                    if(nextCheckin.getCustomer().getSeenActivities()!=null)
				//                        nextCheckin.getCustomer().getSeenActivities().clear();

//				checkinGatewayService.save(nextCheckin.getCustomerId());

				//TODO eu: how to implement domain events, without  low level entity .class ??

//				notificationUseCase.notificateAboutEntityEventWebSocket(checkinResponseDto, hotelEvent.getEventCheckout(), "Checkout from your hotel", nextCheckin.getHotel().getId());

				checkinWriteService.save(nextCheckin);
			}

			var wantedHotelId = checkinRequestDto.hotelId();

//			customerDomainEntity.setConsistencyId(consistencyId);
			lastMessagesService.updateCustomerConsistencyId(customerDomainEntity.getDomainEntityId());

			customerGatewayService.saveToDb(customerDomainEntity);
			var customerDto = customerDtoMapper.toFullDto(customerDomainEntity);

//			if(wantedHotelId>0 && (checkinRequestDto.getCheckinTo()==null || checkinRequestDto.getCheckinTo().before(new Date()))) {
//				checkinResponseDto.setErrorResponse("Checkin Date is wrong or in past");
//			}
//			else
//			{
//				if(wantedHotelId == virtualHotelId && AppConfigProperties.ALLOW_INIT_VIRTUAL_HOTEL)
//				{
//					checkinResponseDto = checkinResponseDto.withHotelId(virtualHotelId);
//					checkinResponseDto.setFullCheckin(true);
//				}
//			}
		}

		//Eugen: every time update customer current hotelId!!!
		lastMessagesService.updateCustomerHotelId(newCheckinEntity.getCustomerDomainEntityId(), newCheckinEntity.getHotelDomainEntityId());

		return newCheckinEntity;
	}
	
	/**
	 * eu: not manually event, but @Observer repository or Service!!!
	 * @param checkinResponseDTO
	 */
	private void sendHotelNewGuestSocketNotificationCommand(CheckinUseCaseDTO checkinResponseDTO) {

		//eu:1 NOT MANUALLY, BUT WITH Service @OBSERVER!!! with DDD Publisher!
		// checkinMessagePublisher.publishCheckinCreatedEvent(checkinResponseDTO);
		
		
		// eu:2 manuell topic exchange
		// notificatinService.publishEvent(EVENT_CHECKIN, checkinResponseDTO); //UseCase.Primary-flow.step.6

		SocketNotificationCommand notificationCustomerCommand = new SocketNotificationCommand(
				checkinResponseDTO.getCustomerId(),
				checkinResponseDTO.getHotelId(),
				"customer-name from DB",
				"new customer in Hotel");


		messagingProducerService.sendSocketNotificationCommand(notificationCustomerCommand, InnerDomainEventImpl.TEST);
	}

	private boolean validateCustomerWithGPSLocationService(CustomerDomainEntityId customerId, HotelDomainEntityId hotelId) throws BusinessException {
		
		int distanceKm = AppConfigProperties.checkinDistanceKm;
		
		if(! gpsValidationHandler.checkLastCustomerLocationDiffToHotelById(customerId, hotelId, distanceKm))
		{
			throw new BusinessException("Checkin will be activated only in area of " + distanceKm + "km. near the hotel.");
		}
		
		return true;
	}
	

	private void sendInitMessageFromHotelStaffToCustomer(CheckinRootDomainEntity newCheckin) {

		HotelDomainEntityId hotelId = newCheckin.getHotelDomainEntityId();

		HotelRootDomainEntity hotel = hotelService.getEntityByDomainId(hotelId).orElseThrow(() -> new BusinessException("hotel not found"));

		lastMessagesService.updateCustomerConsistencyId(newCheckin.getCustomerDomainEntityId());

		CustomerDomainEntityId initHotelStaffId = null;
				
//				hotel.getStaffIdList().stream().findFirst()
//				.orElseThrow(() -> new JsonError("HotelStaff is not set for hotel" + newCheckin.getHotelDomainEntityId()));

		ChatMessageCommand welcomeChatMessageCommand = new ChatMessageCommand(
				initHotelStaffId,
				newCheckin.getCustomerDomainEntityId(), 
				false,
				newCheckin.getHotelDomainEntityId()+"getWelcomeMessage()");
		
		messagingProducerService.sendChatMessageCommand(welcomeChatMessageCommand); //UseCase.Primary-flow.step.6
	}

	private void updateHotelCheckin(CheckinUseCaseRequestDTO checkinRequestDTO, CustomerRootDomainEntity customerEntity, CheckinRootDomainEntity nextCheckin, boolean isFullCheckin) {
		//update the values of checkin
		nextCheckin.setLvalidFrom(checkinRequestDTO.checkinFrom());
		nextCheckin.setLvalidTo(checkinRequestDTO.checkinTo());
//		nextCheckin.setStaffCheckin(customerEntity.isHotelStaff);
		nextCheckin.setFullCheckin(isFullCheckin);
		  checkinWriteService.save(nextCheckin);
	}

	@Override
	public CheckinRootDomainEntity createCheckin(CustomerDTO customer, HotelDTO hotel, boolean isFullCheckin) {
		return null;
	}
	
//	@Override
	public CustomerDTO updateOwnDtoCheckinInfo(CustomerDTO dto, CheckinRootDomainEntity validCheckin)
	{
		if(dto==null || dto.getCustomerConsistencyId()<=0){
			return  null;
		}
		
		//Reset dto only if consistencyId too old!!!
		//        dto = synchronizeCustomerToDto(dto);
		
		List<CheckinRootDomainEntity> activeCheckins = validCheckin!=null ? new ArrayList<CheckinRootDomainEntity>() : checkinOutEntityQueryService.getActiveByCustomerId(dto.getDomainEntityId(), new Date());
		
		//        CheckinRootDomainEntity validCheckin = null;
		
		if(!activeCheckins.isEmpty() || validCheckin!=null) //exists active checkin
		{
			Iterator<CheckinRootDomainEntity> iterator = activeCheckins.iterator();
			
			while(iterator.hasNext() && validCheckin==null)
			{
				CheckinRootDomainEntity customerCheckin = iterator.next();
				
				//If checkin is old, set it not active
				if(LocalDate.now().isAfter(customerCheckin.getLvalidTo()))
				{
					customerCheckin.setActive(false);
                    checkinWriteService.save(customerCheckin);
				}
				else
				{
					validCheckin = customerCheckin;
					break;
				}
			}
		}
		
		if(validCheckin!=null) // if checkin exists and valid
		{
//			CustomerRootDomainEntity checkinCustomerRootEntity = validCheckin.getCustomer();
			
			//TODO Eugen: cannot set logged automaticly
			//TODO Eugen: checkinCustomerRootEntity.setActive(true)? update entity?
			//SETTING NEW CHECKIN
			//checkinCustomerRootEntity.setActive(true);
//			dto.setActive(true);
			
//			dto.setFullCheckin(validCheckin.isFullCheckin());
			
			dto.setCheckinFrom(validCheckin.getLvalidFrom());
			dto.setCheckinTo(validCheckin.getLvalidTo());
			
			dto = customerDtoMapper.fillDtoWithHotelInfo(dto, validCheckin);
		}
		
		if(validCheckin==null)
		{
			HotelDomainEntityId initHotelId = lastMessagesService.getInitHotelId();
			
			if(initHotelId!=null)
			{
//				dto.setHotelId(initHotelId);
//				dto.setFullCheckin(true);
			}
		}
		
		return dto;
	}

//	@Override
//	public void save(CheckinRootDomainEntity CheckinRootDomainEntity) {
//
//	}
//
//	@Override
//	public CustomerDTO updateCheckin(CustomerDTO sessionCustomer) {
//		return null;
//	}

	
}
