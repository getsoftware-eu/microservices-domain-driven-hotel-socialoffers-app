package eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainApplicationCheckinService.useCase;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerRequestDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.exception.JsonError;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.notification.ChatMessageRequest;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.notification.CustomerUpdateRequest;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.hotel.model.HotelEvent;
import eu.getsoftware.hotelico.hotelapp.application.chat.port.out.IChatService;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.model.ICustomerHotelCheckinEntity;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainApplicationCheckinService.useCase.dto.CheckinDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainApplicationCheckinService.useCase.dto.CheckinRequestDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.in.CheckinUseCase;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.out.ICheckinService;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.port.out.iPortService.CustomerPortService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.IHotelRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.in.NotificationUseCase;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IHotelService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IMessagingProducerService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IWallpostService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.LastMessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.hotel.model.HotelEvent.EVENT_CHECKIN;

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
/*public*/ class CheckinUseCaseImpl implements CheckinUseCase
{
	private CustomerPortService customerService;
	
	private IMessagingProducerService messagingProducerService;		
	
	private LastMessagesService lastMessagesService;	
			
	private IHotelService hotelService;			
	
	private ICheckinService checkinService;		
	
	private IChatService chatService;	
	
	private IWallpostService wallpostService;	
		
	private NotificationUseCase notificationUseCase;
	
	@Transactional
	@Override
	public CheckinDTO validateAndCreateCustomerCheckin(CheckinRequestDTO customerRequestDto) throws JsonError {

		// UseCase : Primary-flow

		validateCheckin(customerRequestDto); // UseCase.Primary-flow.step.1
		
		validateCustomerGPSLocation(customerRequestDto.customerId()); //UseCase.Primary-flow.step.2

		ICustomerHotelCheckinEntity newCheckin = createCheckin(customerRequestDto); //UseCase.Primary-flow.step.3

		sendInitMessageFromHotelStaffToCustomer(newCheckin); //UseCase.Primary-flow.step.4
		
		CheckinDTO checkinResponseDTO = checkinService.getResponseDTO(newCheckin); //UseCase.Primary-flow.step.5
		
		notificateHotelAboutNewGuest(checkinResponseDTO);
				
		return checkinResponseDTO;
	}

	private void notificateHotelAboutNewGuest(CheckinDTO checkinResponseDTO) {

		// notificatinService.publishEvent(EVENT_CHECKIN, checkinResponseDTO); //UseCase.Primary-flow.step.6

		HotelEvent hotelEvent = EVENT_CHECKIN;
		
		CustomerUpdateRequest notificationCustomerRequest = new CustomerUpdateRequest(
				checkinResponseDTO.getCustomerId(),
				checkinResponseDTO.getHotelId(),
				"customer-name from DB",
				"customer-email from DB");		
				
		messagingProducerService.sendCustomerNotification(notificationCustomerRequest, hotelEvent);
	}

	private boolean validateCustomerGPSLocation(long customerId, long hotelId) {
		int distanceKm = AppConfigProperties.checkinDistanceKm;
		if(! GPSValidationHandler.checkLastCustomerLocationDiffToHotelById(customerId, hotelId, distanceKm))
		{
			throw new JsonError("Checkin will be activated only in area of " + distanceKm + "km. near the hotel.");
		}
		return true;
	}

	private static boolean validateCheckin(CheckinRequestDTO checkinRequestDto) throws JsonError {

		if( checkinRequestDto.getId()<=0)
		{
			throw new JsonError("no user for checkin.");
		}

		if(! CheckinValidationHandler.validateCheckinDates(checkinRequestDto))
		{
			throw new JsonError("please correct your checkin information.");
		}

		if(checkinRequestDto.getHotelId()>0 && (checkinRequestDto.getCheckinTo()==null || checkinRequestDto.getCheckinTo().before(new Date()))) {
			throw new JsonError("Checkin Date is wrong or in past");
		}
		
		return true;
	}

	private ICustomerHotelCheckinEntity createCheckin(CheckinRequestDTO checkinRequestDto) throws JsonError {
		
		ICustomerRootEntity customerEntity = customerService.getEntityById(checkinRequestDto.getCustomerId())
				.orElseThrow(() -> new JsonError("Customer not found"));
		
		var hotelEntity = getHotelEntityFromCheckinRequest(checkinRequestDto)				
				.orElseThrow(() -> new JsonError("Hotel wurde nicht gefunden"));
		
		boolean isFullCheckin = hotelEntity.isVirtual();

		boolean isDemoCheckin = AppConfigProperties.HOTEL_DEMO_CODE.equalsIgnoreCase(hotelEntity.getCurrentHotelAccessCode());
		boolean checkinDateIsValid = AppConfigProperties.NO_CHECKOUT_FOR_DEMOHOTEL && isDemoCheckin || checkinRequestDto.getCheckinTo()!=null && checkinRequestDto.getCheckinTo().after(new Date());

		isFullCheckin = isFullCheckin || isDemoCheckin;// set id DTO getter! || ControllerUtils.CHECKIN_FULL_ALWAYS;

//		CheckinDTO checkinResponseDto = new CheckinDTO(checkinRequestDto.getCustomerId(), checkinRequestDto.getHotelId());

		ICustomerHotelCheckinEntity newCheckinEntity = checkinService.createCheckinEntity(customerEntity, hotelEntity, isFullCheckin); //TODO persist now or later??

		List<ICustomerHotelCheckinEntity> activeCustomerCheckins = checkinService.getActiveByCustomerId(customerEntity.getId(), new Date());

		//If checkin exists, 
		if(!hotelEntity.isVirtual() && checkinDateIsValid)
		{
			Date lastSameHotelCheckinDate = checkinService.getLastByCustomerAndHotelId(customerEntity.getId(), hotelEntity.getId());

			var hotelDto = hotelService.getHotelById(hotelEntity.getId()); //checkinRequestDto.setHotelId(hotelRootEntity.getId()); //eu: NO setters for parameter!!!!

			ICustomerHotelCheckinEntity nowGoodCheckin = null;
			
			//if no checkin exists, create a new one
			if(activeCustomerCheckins.isEmpty())
			{
				nowGoodCheckin = CreateHotelCheckinHandler().handle(checkinRequestDto, customerEntity, hotelEntity, isFullCheckin);

				checkinResponseDto.setErrorResponse("");

				//Inform others!!!
				if(!customerEntity.isHotelStaff() && !customerEntity.isAdmin())
				{
					checkinResponseDto.setHotelId(hotelEntity.getId());
					notificationUseCase.notificateAboutEntityEventWebSocket(checkinResponseDto, hotelEvent.getEventCheckin(), "New check-in in your hotel", hotelRootEntity.getId());
				}

				//sent wellcome message to new fullCheckin customers
				if(!customerEntity.isHotelStaff() && !customerEntity.isAdmin())
				{
					CustomerDTO staffSender = this.getStaffbyHotelId(hotelEntity.getId());

					if (staffSender!=null)
					{
						//TODO EUGEN: Check here if I already sent a message to him
						// ### SEND WELLCOME MESSAGE

						CustomerDTO customerDTO = customerService.convertCustomerToDto(customerEntity, customerEntity.getId());
						chatService.sendFirstChatMessageOnDemand(customerDTO, staffSender, isFullCheckin);

						HotelDTO hotelDTO = hotelService.getHotelById(hotelEntity.getId());
						wallpostService.sendNotificationWallpostOnDemand(customerDTO, lastSameHotelCheckinDate, hotelDto, staffSender);

					}
				}

			}
			else //correct actual checkin, if the same hotel
			{
				//EUGEN: only update checkin, no event

				for (ICustomerHotelCheckinEntity nextCheckin : activeCustomerCheckins)
				{
					if(hotelEntity.equals(nextCheckin.getHotel())) //if the checkin of actual hotel, NO HOTEL EVENT!
					{
						nowGoodCheckin = updateHotelCheckin(checkinRequestDto, customerEntity, nextCheckin, isFullCheckin);
						checkinResponseDto.setErrorResponse("");
						break;
					}
				}
			}

			long consistencyId = new Date().getTime();
			customerEntity.getEntityAggregate().setConsistencyId(consistencyId);

			lastMessagesService.updateCustomerConsistencyId(customerEntity.getId(), consistencyId);

			ICustomerRootEntity iCustomerRootEntity = customerService.save(customerEntity);

			var customerDto = customerService.convertCustomerToDto(iCustomerRootEntity, true, nowGoodCheckin);

		}
		else{ //if no hotel more, maybe cancel actual checkin

			for (ICustomerHotelCheckinEntity nextCheckin : activeCustomerCheckins)
			{
				nextCheckin.setActive(false);

				//                    if(nextCheckin.getCustomer().getSeenActivities()!=null)
				//                        nextCheckin.getCustomer().getSeenActivities().clear();

				customerService.save(nextCheckin.getCustomer());

				//TODO eu: how to implement domain events, without  low level entity .class ??

				notificationUseCase.notificateAboutEntityEventWebSocket(checkinResponseDto, hotelEvent.getEventCheckout(), "Checkout from your hotel", nextCheckin.getHotel().getId());

				checkinService.save(nextCheckin);
			}

			long wantedHotelId = checkinRequestDto.getHotelId();

			long consistencyId = new Date().getTime();
			customerEntity.getEntityAggregate().setConsistencyId(consistencyId);
			lastMessagesService.updateCustomerConsistencyId(customerEntity.getId(), consistencyId);

			var customerDto = customerService.convertMyCustomerToFullDto(customerService.save(customerEntity));

//			if(wantedHotelId>0 && (checkinRequestDto.getCheckinTo()==null || checkinRequestDto.getCheckinTo().before(new Date()))) {
//				checkinResponseDto.setErrorResponse("Checkin Date is wrong or in past");
//			}
//			else
//			{
				if(wantedHotelId == virtualHotelId && AppConfigProperties.ALLOW_INIT_VIRTUAL_HOTEL)
				{
					checkinResponseDto.setHotelId(virtualHotelId);
					checkinResponseDto.setFullCheckin(true);
				}
//			}
		}

		//Eugen: every time update customer current hotelId!!!
		lastMessagesService.updateCustomerHotelId(newCheckinEntity.getCustomer().getId(), newCheckinEntity.getHotel().getId());

		return newCheckinEntity;
	}

	private Optional<IHotelRootEntity> getHotelEntityFromCheckinRequest(CheckinRequestDTO checkinRequestDto) {

		long virtualHotelId = lastMessagesService.getInitHotelId();

		String virtualHotelCode = AppConfigProperties.ALLOW_INIT_VIRTUAL_HOTEL ? hotelService.getVirtualHotelCode() : null;

		Optional<IHotelRootEntity> hotelEntityOpt = Optional.empty();
		
		if(checkinRequestDto.getHotelCode()!=null && !(checkinRequestDto.getHotelCode().equals(virtualHotelCode) ))
		{
			hotelEntityOpt = hotelService.findByCurrentHotelAccessCodeAndActive(checkinRequestDto.getHotelCode(), true);
		}
		
		if(hotelEntityOpt.isEmpty() && checkinRequestDto.getHotelId()>0 && (!AppConfigProperties.ALLOW_INIT_VIRTUAL_HOTEL || checkinRequestDto.getHotelId() != virtualHotelId))
		{
			hotelEntityOpt = hotelService.getOne(checkinRequestDto.getHotelId());
		}
		
		return hotelEntityOpt;
	}


	private void sendInitMessageFromHotelStaffToCustomer(ICustomerHotelCheckinEntity newCheckin) throws JsonError {
		CustomerDTO initHotelStaff = newCheckin.getHotel().getStaffList().stream().findFirst()
				.orElseThrow(()-> new JsonError("HotelStaff is not set for hotel" + newCheckin.getHotel().getId()));

		ChatMessageRequest wellcomeChatMessage = new ChatMessageRequest(
				initHotelStaff.getId(),
				newCheckin.getCustomer().getId(), 
				false,
				newCheckin.getHotel().getWellcomeMessage());
		
		messagingProducerService.sendChatMessageTopicRequest(wellcomeChatMessage); //UseCase.Primary-flow.step.6

//		chatService.sendFirstChatMessageOnDemand(
//				EVENT_CHECKIN, 
//				initHotelStaff, 
//				newCheckin.getCustomer(), 
//				newCheckin.getHotel().getWellcomeMessage()
//		);
	}

	private ICustomerHotelCheckinEntity updateHotelCheckin(CustomerRequestDTO customerRequestDTO, ICustomerRootEntity customerEntity, ICustomerHotelCheckinEntity nextCheckin, boolean isFullCheckin) {
		//update the values of checkin
		nextCheckin.setValidFrom(customerRequestDTO.getCheckinFrom());
		nextCheckin.setValidTo(customerRequestDTO.getCheckinTo());
		nextCheckin.setStaffCheckin(customerEntity.isHotelStaff());
		nextCheckin.setFullCheckin(isFullCheckin);
		return checkinService.save(nextCheckin);
	}
	
	@Override
	public CustomerDTO getStaffbyHotelId(long hotelId)
	{
		return checkinService.getStaffByHotelId(hotelId).stream().findFirst().orElseThrow(() -> new RuntimeException("staff not found for hotel " + hotelId));
	}
	

	@Override
	public CustomerDTO updateOwnDtoCheckinInfo(CustomerDTO dto, ICustomerHotelCheckinEntity validCheckin)
	{
		if(dto==null || dto.getId()<=0){
			return  null;
		}
		
		//Reset dto only if consistencyId too old!!!
		//        dto = synchronizeCustomerToDto(dto);
		
		List<ICustomerHotelCheckinEntity> activeCheckins = validCheckin!=null ? new ArrayList<ICustomerHotelCheckinEntity>() : checkinService.getActiveByCustomerId(dto.getId(), new Date());
		
		//        CustomerHotelCheckin validCheckin = null;
		
		if(!activeCheckins.isEmpty() || validCheckin!=null) //exists active checkin
		{
			Iterator<ICustomerHotelCheckinEntity> iterator = activeCheckins.iterator();
			
			while(iterator.hasNext() && validCheckin==null)
			{
				ICustomerHotelCheckinEntity customerCheckin = iterator.next();
				
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
			ICustomerRootEntity checkinCustomerRootEntity = validCheckin.getCustomer();
			
			//TODO Eugen: cannot set logged automaticly
			//TODO Eugen: checkinCustomerRootEntity.setActive(true)? update entity?
			//SETTING NEW CHECKIN
			//checkinCustomerRootEntity.setActive(true);
			dto.setActive(true);
			
			dto.setFullCheckin(validCheckin.isFullCheckin());
			
			dto.setCheckinFrom(validCheckin.getValidFrom());
			dto.setCheckinTo(validCheckin.getValidTo());
			
			dto = customerService.fillDtoWithHotelInfo(dto, validCheckin);
		}
		
		if(validCheckin==null)
		{
			long initHotelId = lastMessagesService.getInitHotelId();
			
			if(initHotelId>0)
			{
				dto.setHotelId(initHotelId);
				dto.setFullCheckin(true);
			}
		}
		
		return dto;
	}
}
