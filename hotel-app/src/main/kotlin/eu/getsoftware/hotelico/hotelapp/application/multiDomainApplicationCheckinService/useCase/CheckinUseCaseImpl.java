package eu.getsoftware.hotelico.hotelapp.application.multiDomainApplicationCheckinService.useCase;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerRequestDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.hotelapp.application.chat.port.out.IChatService;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerHotelCheckin;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.port.out.iPortService.CustomerPortService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.IHotelRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.in.NotificationUseCase;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.ICheckinService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IHotelService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IWallpostService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.LastMessagesService;
import eu.getsoftware.hotelico.hotelapp.application.multiDomainApplicationCheckinService.port.in.CheckinUseCase;
import eu.getsoftware.hotelico.hotelapp.application.multiDomainApplicationCheckinService.useCase.handler.CreateHotelCheckinHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Application Service that uses multiply domain services (is portService = domainService?)
 * <br/>
 * Created by e.fanshil
 * At 03.02.2016 14:04
 */
@Service
@RequiredArgsConstructor
/*public*/ class CheckinUseCaseImpl implements CheckinUseCase
{
	private CustomerPortService customerService;		
	
	private LastMessagesService lastMessagesService;	
			
	private IHotelService hotelService;			
	
	private ICheckinService checkinService;		
	
	private IChatService chatService;	
	
	private IWallpostService wallpostService;	
		
	private NotificationUseCase notificationUseCase;
	
	private eu.getsoftware.hotelico.hotelapp.application.hotel.common.utils.IHotelEvent hotelEvent;

	@Transactional
	@Override
	public CustomerDTO setCustomerCheckin(CustomerRequestDTO customerRequestDto) {

		Optional<ICustomerRootEntity> customerEntityOpt = customerRequestDto.getId()>0 ? customerService.getEntityById(customerRequestDto.getId()) : Optional.empty();

		if(customerEntityOpt.isEmpty())
		{
			return new CustomerDTO.CustomerBuilder(customerRequestDto.getId()).setStatus("customer not found").build();
		}
		
		IHotelRootEntity hotelRootEntity = null;
		
		ICustomerRootEntity customerEntity = customerEntityOpt.get();
		CustomerDTO customerResponseDto = new CustomerDTO(customerEntity.getId());
		
		long virtualHotelId = lastMessagesService.getInitHotelId();
		
		String virtualHotelCode = AppConfigProperties.ALLOW_INIT_VIRTUAL_HOTEL ? hotelService.getVirtualHotelCode() : null;
		
		boolean isFullCheckin = false;
		
		if(customerRequestDto.getHotelCode()!=null && !(customerRequestDto.getHotelCode().equals(virtualHotelCode) ))
		{
			hotelRootEntity = hotelService.findByCurrentHotelAccessCodeAndActive(customerRequestDto.getHotelCode(), true);
			
			//Eugen: full checkin, wenn checked-in with Hotel-Code!
			isFullCheckin = (hotelRootEntity != null);
		}
		if(hotelRootEntity == null && customerRequestDto.getHotelId()>0 && (!AppConfigProperties.ALLOW_INIT_VIRTUAL_HOTEL || customerRequestDto.getHotelId() != virtualHotelId))
		{
			hotelRootEntity = hotelService.getOne(customerRequestDto.getHotelId());
		}
		
		List<ICustomerHotelCheckin> activeCustomerCheckins = checkinService.getActiveByCustomerId(customerEntity.getId(), new Date());
		
		boolean isDemoCheckin = hotelRootEntity != null && AppConfigProperties.HOTEL_DEMO_CODE.equalsIgnoreCase(hotelRootEntity.getCurrentHotelAccessCode());
		boolean checkinDateIsValid = AppConfigProperties.NO_CHECKOUT_FOR_DEMOHOTEL && isDemoCheckin || customerRequestDto.getCheckinTo()!=null && customerRequestDto.getCheckinTo().after(new Date());
		
		isFullCheckin = isFullCheckin || isDemoCheckin;// set id DTO getter! || ControllerUtils.CHECKIN_FULL_ALWAYS;
		
		//If checkin exists, 
		if(hotelRootEntity != null && !hotelRootEntity.isVirtual() && checkinDateIsValid)
		{
			Date lastSameHotelCheckinDate = checkinService.getLastByCustomerAndHotelId(customerEntity.getId(), hotelRootEntity.getId());

			var hotelDto = hotelService.getHotelById(hotelRootEntity.getId()); //customerRequestDto.setHotelId(hotelRootEntity.getId()); //eu: NO setters for parameter!!!!
			
			ICustomerHotelCheckin nowGoodCheckin = null;
			
			//if no checkin exists, create a new one
			if(activeCustomerCheckins.isEmpty())
			{
				nowGoodCheckin = CreateHotelCheckinHandler.handle(customerRequestDto, customerEntity, hotelRootEntity, isFullCheckin);

				customerResponseDto.setErrorResponse("");
				
				//Inform others!!!
				if(!customerEntity.isHotelStaff() && !customerEntity.isAdmin())
				{
					customerResponseDto.setHotelId(hotelRootEntity.getId());
					notificationUseCase.notificateAboutEntityEvent(customerResponseDto, hotelEvent.getEventCheckin(), "New check-in in your hotel", hotelRootEntity.getId());
				}
				
				//sent wellcome message to new fullCheckin customers
				if(!customerEntity.isHotelStaff() && !customerEntity.isAdmin())
				{
					CustomerDTO staffSender = this.getStaffbyHotelId(hotelRootEntity.getId());
					
					if (staffSender!=null)
					{
						//TODO EUGEN: Check here if I already sent a message to him
						// ### SEND WELLCOME MESSAGE

						CustomerDTO customerDTO = customerService.convertCustomerToDto(customerEntity, customerEntity.getId());
						chatService.sendFirstChatMessageOnDemand(customerDTO, staffSender, isFullCheckin);

						HotelDTO hotelDTO = hotelService.getHotelById(hotelRootEntity.getId());
						wallpostService.sendNotificationWallpostOnDemand(customerDTO, lastSameHotelCheckinDate, hotelDto, staffSender);

					}
				}
				
			}
			else //correct actual checkin, if the same hotel
			{
				//EUGEN: only update checkin, no event
				
				for (ICustomerHotelCheckin nextCheckin : activeCustomerCheckins)
				{
					if(hotelRootEntity.equals(nextCheckin.getHotel())) //if the checkin of actual hotel, NO HOTEL EVENT!
					{
						nowGoodCheckin = updateHotelCheckin(customerRequestDto, customerEntity, nextCheckin, isFullCheckin);
						customerResponseDto.setErrorResponse("");
						break;
					}
				}
			}
			
			long consistencyId = new Date().getTime();
			customerEntity.getEntityAggregate().setConsistencyId(consistencyId);
			
			lastMessagesService.updateCustomerConsistencyId(customerEntity.getId(), consistencyId);

			ICustomerRootEntity iCustomerRootEntity = customerService.save(customerEntity);
			
			customerResponseDto = customerService.convertCustomerToDto(iCustomerRootEntity, true, nowGoodCheckin);
			
		}
		else{ //if no hotel more, maybe cancel actual checkin
			
			for (ICustomerHotelCheckin nextCheckin : activeCustomerCheckins)
			{
				nextCheckin.setActive(false);
				
				//                    if(nextCheckin.getCustomer().getSeenActivities()!=null)
				//                        nextCheckin.getCustomer().getSeenActivities().clear();
				
				customerService.save(nextCheckin.getCustomer());
				
				//TODO eu: how to implement domain events, without  low level entity .class ??
				
				notificationUseCase.notificateAboutEntityEvent(customerResponseDto, hotelEvent.getEventCheckout(), "Checkout from your hotel", nextCheckin.getHotel().getId());
				
				checkinService.save(nextCheckin);
			}
			
			long wantedHotelId = customerRequestDto.getHotelId();
			
			long consistencyId = new Date().getTime();
			customerEntity.getEntityAggregate().setConsistencyId(consistencyId);
			lastMessagesService.updateCustomerConsistencyId(customerEntity.getId(), consistencyId);

			customerResponseDto = customerService.convertMyCustomerToFullDto(customerService.save(customerEntity));
			
			if(wantedHotelId>0 && (customerRequestDto.getCheckinTo()==null || customerRequestDto.getCheckinTo().before(new Date()))) {
				customerResponseDto.setErrorResponse("Checkin Date is wrong or in past");
			}
			else
			{
				if(wantedHotelId>0)
				{
					customerResponseDto.setErrorResponse("Hotel wurde nicht gefunden");
				}
				
				if(wantedHotelId == virtualHotelId && AppConfigProperties.ALLOW_INIT_VIRTUAL_HOTEL)
				{
					customerResponseDto.setHotelId(virtualHotelId);
					customerResponseDto.setFullCheckin(true);
				}
			}
		}
		
		//Eugen: every time update customer current hotelId!!!
		lastMessagesService.updateCustomerHotelId(customerResponseDto.getId(), customerResponseDto.getHotelId());
		
		
		return customerResponseDto;
			
	}

	private ICustomerHotelCheckin updateHotelCheckin(CustomerRequestDTO customerRequestDTO, ICustomerRootEntity customerEntity, ICustomerHotelCheckin nextCheckin, boolean isFullCheckin) {
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
	public CustomerDTO updateOwnDtoCheckinInfo(CustomerDTO dto, ICustomerHotelCheckin validCheckin)
	{
		if(dto==null || dto.getId()<=0){
			return  null;
		}
		
		//Reset dto only if consistencyId too old!!!
		//        dto = synchronizeCustomerToDto(dto);
		
		List<ICustomerHotelCheckin> activeCheckins = validCheckin!=null ? new ArrayList<ICustomerHotelCheckin>() : checkinService.getActiveByCustomerId(dto.getId(), new Date());
		
		//        CustomerHotelCheckin validCheckin = null;
		
		if(!activeCheckins.isEmpty() || validCheckin!=null) //exists active checkin
		{
			Iterator<ICustomerHotelCheckin> iterator = activeCheckins.iterator();
			
			while(iterator.hasNext() && validCheckin==null)
			{
				ICustomerHotelCheckin customerCheckin = iterator.next();
				
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
