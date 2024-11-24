package eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CheckinDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.CheckinRequestDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.ChatMessageCommand;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.SocketNotificationCommand;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.exception.domain.BusinessException;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.messaging.CheckinMessagePublisher;
import eu.getsoftware.hotelico.hotelapp.application.chat.port.out.IChatService;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.CheckinRootDomainEntity;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.handler.CreateHotelCheckinHandler;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.in.CheckinUseCase;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.out.CheckinPortService;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.out.GPSValidationHandler;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.port.out.iPortService.CustomerPortService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.innerDomainService.domainEvents.DomainEvent;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.customDomainModelImpl.HotelDomainEntity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.in.NotificationUseCase;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IHotelService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IMessagingProducerService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IWallpostService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.LastMessagesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;

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
	private CustomerPortService<CustomerRootDomainEntity> customerService;
	
	private IMessagingProducerService<DomainEvent> messagingProducerService;		
	
	private LastMessagesService lastMessagesService;	
			
	private IHotelService<HotelDomainEntity> hotelService;			
	
	private CheckinPortService checkinService;		
	
	private IChatService chatService;	
	
	private IWallpostService wallpostService;	
		
	private NotificationUseCase notificationUseCase;
	
	private GPSValidationHandler gpsValidationHandler;
	
	private CheckinMessagePublisher checkinMessagePublisher;
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public CheckinDTO createCustomerCheckin(@Validated CheckinRequestDTO customerRequestDto) {

		// UseCase : Primary-flow
		customerRequestDto.validateBusinessLogic();

		//eu: error: not validate DTO!!! DTO validates itself!!!! 
		// validateCheckin(customerRequestDto); // UseCase.Primary-flow.step.1
		
		validateCustomerWithGPSLocationService(customerRequestDto.customerId(), customerRequestDto.hotelId()); //UseCase.Primary-flow.step.2

		CheckinRootDomainEntity newCheckin = createCheckin(customerRequestDto); //UseCase.Primary-flow.step.3
		
		sendInitMessageFromHotelStaffToCustomer(newCheckin); //UseCase.Primary-flow.step.4
		
		CheckinDTO checkinResponseDTO = checkinService.getResponseDTO(newCheckin); //UseCase.Primary-flow.step.5
		
		//eu: error: create no commands to other domains, they receive event and execute own logik!!!
		sendHotelNewGuestSocketNotificationCommand(checkinResponseDTO);
		
		return checkinResponseDTO;
	}

	 

	/**
	 * eu: not manually event, but @Observer repository or Service!!!
	 * @param checkinResponseDTO
	 */
	private void sendHotelNewGuestSocketNotificationCommand(CheckinDTO checkinResponseDTO) {

		//eu:1 NOT MANUALLY, BUT WITH Service @OBSERVER!!! with DDD Publisher!
		// checkinMessagePublisher.publishCheckinCreatedEvent(checkinResponseDTO);
		
		
		// eu:2 manuell topic exchange
		// notificatinService.publishEvent(EVENT_CHECKIN, checkinResponseDTO); //UseCase.Primary-flow.step.6

		SocketNotificationCommand notificationCustomerCommand = new SocketNotificationCommand(
				checkinResponseDTO.getCustomerId(),
				checkinResponseDTO.getHotelId(),
				"customer-name from DB",
				"new customer in Hotel");


		messagingProducerService.sendSocketNotificationCommand(notificationCustomerCommand, DomainEvent.TEST);
	}

	private boolean validateCustomerWithGPSLocationService(CustomerDomainEntityId customerId, HotelDomainEntityId hotelId) throws BusinessException {
		
		int distanceKm = AppConfigProperties.checkinDistanceKm;
		
		if(! gpsValidationHandler.checkLastCustomerLocationDiffToHotelById(customerId, hotelId, distanceKm))
		{
			throw new BusinessException("Checkin will be activated only in area of " + distanceKm + "km. near the hotel.");
		}
		
		return true;
	}
	

	private CheckinRootDomainEntity createCheckin(CheckinRequestDTO checkinRequestDto) {
		
		CustomerRootDomainEntity customerDomainEntity = customerService.getEntityById(checkinRequestDto.customerId())
				.orElseThrow(() -> new BusinessException("Customer " + checkinRequestDto.customerId()+ " not found"));
		
		var hotelEntity = getHotelEntityFromCheckinRequest(checkinRequestDto)				
				.orElseThrow(() -> new BusinessException("Hotel " + checkinRequestDto + " wurde nicht gefunden"));
		
		boolean isFullCheckin = hotelEntity.isVirtual;

		boolean isDemoCheckin = AppConfigProperties.HOTEL_DEMO_CODE.equalsIgnoreCase(hotelEntity.getCurrentHotelAccessCode());
		boolean checkinDateIsValid = AppConfigProperties.NO_CHECKOUT_FOR_DEMOHOTEL && isDemoCheckin || checkinRequestDto.checkinDatesAreValid();

		isFullCheckin = isFullCheckin || isDemoCheckin;// set id DTO getter! || ControllerUtils.CHECKIN_FULL_ALWAYS;

//		CheckinDTO checkinResponseDto = new CheckinDTO(checkinRequestDto.getCustomerId(), checkinRequestDto.getHotelId());

		CheckinRootDomainEntity newCheckinEntity = checkinService.createCheckin(modelMapper.map(customerDomainEntity, CustomerDTO.class), modelMapper.map(hotelEntity, HotelDTO.class), isFullCheckin); //TODO persist now or later??

		List<CheckinRootDomainEntity> activeCustomerCheckins = checkinService.getActiveByCustomerId(customerDomainEntity.getDomainEntityId(), new Date());

		//If checkin exists, 
		if(!hotelEntity.isVirtual && checkinDateIsValid)
		{
			Date lastSameHotelCheckinDate = checkinService.getLastByCustomerAndHotelId(customerDomainEntity.getDomainEntityId(), hotelEntity.getDomainEntityId());

			var hotelDto = hotelService.getHotelById(hotelEntity.getDomainEntityId()); //checkinRequestDto.setHotelId(hotelRootEntity.getId()); //eu: NO setters for parameter!!!!

			CheckinRootDomainEntity nowGoodCheckin = null;
			
			//if no checkin exists, create a new one
			if(activeCustomerCheckins.isEmpty())
			{
				nowGoodCheckin =  new CreateHotelCheckinHandler().handle(checkinRequestDto, modelMapper.map(customerDomainEntity, CustomerDTO.class), modelMapper.map(hotelEntity, HotelDTO.class), isFullCheckin);

//				checkinResponseDto.setErrorResponse("");

				//Inform others!!!
				if(!customerDomainEntity.isHotelStaff() && !customerDomainEntity.isAdmin())
				{
//					checkinResponseDto.setHotelId(hotelEntity.getId());
//					notificationUseCase.notificateAboutEntityEventWebSocket(checkinResponseDto, hotelEvent.getEventCheckin(), "New check-in in your hotel", hotelRootEntity.getId());
				}

				//sent wellcome message to new fullCheckin customers
				if(!customerDomainEntity.isHotelStaff() && !customerDomainEntity.isAdmin())
				{
					CustomerDTO staffSender = this.getStaffbyHotelId(hotelEntity.getDomainEntityId());

					if (staffSender!=null)
					{
						//TODO EUGEN: Check here if I already sent a message to him
						// ### SEND WELLCOME MESSAGE

						CustomerDTO customerDTO = customerService.convertCustomerWithHotelToDto(customerDomainEntity, hotelEntity.getDomainEntityId());
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

			long consistencyId = new Date().getTime();
//			customerDomainEntity.setConsistencyId(consistencyId);

			lastMessagesService.updateCustomerConsistencyId(customerDomainEntity.getDomainEntityId(), consistencyId);

			customerService.save(customerDomainEntity);

			var customerDto = customerService.convertCustomerWithHotelToDto(customerDomainEntity, true, nowGoodCheckin);

		}
		else{ //if no hotel more, maybe cancel actual checkin

			for (CheckinRootDomainEntity nextCheckin : activeCustomerCheckins)
			{
				nextCheckin.setActive(false);

				//                    if(nextCheckin.getCustomer().getSeenActivities()!=null)
				//                        nextCheckin.getCustomer().getSeenActivities().clear();

//				customerService.save(nextCheckin.getCustomerId());

				//TODO eu: how to implement domain events, without  low level entity .class ??

//				notificationUseCase.notificateAboutEntityEventWebSocket(checkinResponseDto, hotelEvent.getEventCheckout(), "Checkout from your hotel", nextCheckin.getHotel().getId());

				checkinService.save(nextCheckin);
			}

			var wantedHotelId = checkinRequestDto.hotelId();

			long consistencyId = new Date().getTime();
//			customerDomainEntity.setConsistencyId(consistencyId);
			lastMessagesService.updateCustomerConsistencyId(customerDomainEntity.getDomainEntityId(), consistencyId);

			customerService.save(customerDomainEntity);
			var customerDto = customerService.convertMyCustomerToFullDto(customerDomainEntity);

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

	private Optional<HotelDomainEntity> getHotelEntityFromCheckinRequest(CheckinRequestDTO checkinRequestDto) {

		HotelDomainEntityId virtualHotelId = lastMessagesService.getInitHotelId();

		String virtualHotelCode = AppConfigProperties.ALLOW_INIT_VIRTUAL_HOTEL ? hotelService.getVirtualHotelCode() : null;

		Optional<HotelDomainEntity> hotelEntityOpt = Optional.empty();
		
//		if(checkinRequestDto.getHotelCode()!=null && !(checkinRequestDto.getHotelCode().equals(virtualHotelCode) ))
//		{
//			hotelEntityOpt = hotelService.findByCurrentHotelAccessCodeAndActive(checkinRequestDto.getHotelCode(), true);
//		}
		
//		if(hotelEntityOpt.isEmpty() && checkinRequestDto.getHotelId()>0 && (!AppConfigProperties.ALLOW_INIT_VIRTUAL_HOTEL || checkinRequestDto.getHotelId() != virtualHotelId))
//		{
//			hotelEntityOpt = hotelService.getOne(checkinRequestDto.getHotelId());
//		}
		
		return hotelEntityOpt;
	}


	private void sendInitMessageFromHotelStaffToCustomer(CheckinRootDomainEntity newCheckin) {

		HotelDomainEntityId hotelId = newCheckin.getHotelDomainEntityId();

		HotelDomainEntity hotel = hotelService.getEntityByDomainId(hotelId).orElseThrow(() -> new BusinessException("hotel not found"));

		CustomerDomainEntityId initHotelStaffId = null;
				
//				hotel.getStaffIdList().stream().findFirst()
//				.orElseThrow(() -> new JsonError("HotelStaff is not set for hotel" + newCheckin.getHotelDomainEntityId()));

		ChatMessageCommand wellcomeChatMessageCommand = new ChatMessageCommand(
				initHotelStaffId,
				newCheckin.getCustomerDomainEntityId(), 
				false,
				newCheckin.getHotelDomainEntityId()+"getWellcomeMessage()");
		
		messagingProducerService.sendChatMessageCommand(wellcomeChatMessageCommand); //UseCase.Primary-flow.step.6
	}

	private void updateHotelCheckin(CheckinRequestDTO checkinRequestDTO, CustomerRootDomainEntity customerEntity, CheckinRootDomainEntity nextCheckin, boolean isFullCheckin) {
		//update the values of checkin
		nextCheckin.setValidFrom(checkinRequestDTO.checkinFrom());
		nextCheckin.setValidTo(checkinRequestDTO.checkinTo());
//		nextCheckin.setStaffCheckin(customerEntity.isHotelStaff);
		nextCheckin.setFullCheckin(isFullCheckin);
		  checkinService.save(nextCheckin);
	}
	
	@Override
	public CustomerDTO getStaffbyHotelId(HotelDomainEntityId hotelId)
	{
		return checkinService.getStaffByHotelId(hotelId).stream().findFirst().orElseThrow(() -> new RuntimeException("staff not found for hotel " + hotelId));
	}
	

//	@Override
	public CustomerDTO updateOwnDtoCheckinInfo(CustomerDTO dto, CheckinRootDomainEntity validCheckin)
	{
		if(dto==null || dto.getId()<=0){
			return  null;
		}
		
		//Reset dto only if consistencyId too old!!!
		//        dto = synchronizeCustomerToDto(dto);
		
		List<CheckinRootDomainEntity> activeCheckins = validCheckin!=null ? new ArrayList<CheckinRootDomainEntity>() : checkinService.getActiveByCustomerId(dto.getDomainEntityId(), new Date());
		
		//        CheckinRootDomainEntity validCheckin = null;
		
		if(!activeCheckins.isEmpty() || validCheckin!=null) //exists active checkin
		{
			Iterator<CheckinRootDomainEntity> iterator = activeCheckins.iterator();
			
			while(iterator.hasNext() && validCheckin==null)
			{
				CheckinRootDomainEntity customerCheckin = iterator.next();
				
				//If checkin is old, set it not active
				if(new Date().after(customerCheckin.getValidTo()))
				{
					customerCheckin.setActive(false);
					checkinService.save(customerCheckin);
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
			dto.setActive(true);
			
//			dto.setFullCheckin(validCheckin.isFullCheckin());
			
			dto.setCheckinFrom(validCheckin.getValidFrom());
			dto.setCheckinTo(validCheckin.getValidTo());
			
			dto = customerService.fillDtoWithHotelInfo(dto, validCheckin);
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
}
