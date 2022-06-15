package eu.getsoftware.hotelico.hotel.service.impl;

import static eu.getsoftware.hotelico.hotel.utils.ControllerUtils.convertToLocalDateTime;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import eu.getsoftware.hotelico.hotel.dto.WallPostDto;
import eu.getsoftware.hotelico.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.getsoftware.hotelico.hotel.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotel.model.ChatMessage;
import eu.getsoftware.hotelico.hotel.model.Customer;
import eu.getsoftware.hotelico.hotel.model.CustomerHotelCheckin;
import eu.getsoftware.hotelico.hotel.model.Hotel;
import eu.getsoftware.hotelico.hotel.repository.ChatRepository;
import eu.getsoftware.hotelico.hotel.repository.CheckinRepository;
import eu.getsoftware.hotelico.hotel.repository.CustomerRepository;
import eu.getsoftware.hotelico.hotel.repository.HotelRepository;
import eu.getsoftware.hotelico.hotel.repository.WallPostRepository;
import eu.getsoftware.hotelico.hotel.service.CacheService;
import eu.getsoftware.hotelico.hotel.service.CheckinService;
import eu.getsoftware.hotelico.hotel.service.CustomerService;
import eu.getsoftware.hotelico.hotel.service.NotificationService;
import eu.getsoftware.hotelico.hotel.utils.ControllerUtils;
import eu.getsoftware.hotelico.hotel.utils.HotelEvent;

/**
 * <br/>
 * Created by e.fanshil
 * At 03.02.2016 14:04
 */
@Service
public class CheckinServiceImpl implements CheckinService
{
	@Autowired
	private CustomerService customerService;		
	
	@Autowired
	private CacheService cacheService;	
			
	@Autowired
	private HotelService hotelService;	
		
	@Autowired
	private NotificationService notificationService;	
	
	@Autowired
	private CustomerRepository customerRepository;	
	
	@Autowired
	private HotelRepository hotelRepository;	
	
	@Autowired
	private WallPostRepository wallPostRepository;	
	
	@Autowired
	private CheckinRepository checkinRepository;	
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public CustomerDTO updateCheckin(CustomerDTO customerDto) {
		
		Customer customer = customerDto.getId()>0 ? customerRepository.getOne(customerDto.getId()) : null;
		
		customerDto = setCustomerCheckin(customerDto, customer);
		
		return customerDto; //mapper.map(customerRepository.saveAndFlush(customer), CustomerDto.class);
	}
	
	@Override
	public CustomerDTO setCustomerCheckin(CustomerDTO customerDto, Customer customer)
	{
		Hotel hotel = null;
		
		if(customer!=null)
		{
			customerDto.setErrorResponse("");
			
			long virtualHotelId = cacheService.getInitHotelId();
			
			String virtualHotelCode = ControllerUtils.ALLOW_INIT_VIRTUAL_HOTEL ? hotelRepository.getVirtualHotelCode() : null;
			
			boolean isFullCheckin = false;
			
			if(customerDto.getHotelCode()!=null && !(virtualHotelCode!=null && customerDto.getHotelCode().equals(virtualHotelCode) ))
			{
				hotel = hotelRepository.findByCurrentHotelAccessCodeAndActive(customerDto.getHotelCode(), true);
				
				//Eugen: full checkin, wenn checked-in with Hotel-Code!
				isFullCheckin = (hotel!=null);
			}
			if(hotel==null && customerDto.getHotelId()!=null && customerDto.getHotelId()>0 && (!ControllerUtils.ALLOW_INIT_VIRTUAL_HOTEL || customerDto.getHotelId() != virtualHotelId))
			{
				hotel = hotelRepository.getOne(customerDto.getHotelId());
			}
			
			List<CustomerHotelCheckin> activeCustomerCheckins = checkinRepository.getActiveByCustomerId(customer.getId(), new Date());
			
			boolean isDemoCheckin = hotel != null && ControllerUtils.HOTEL_DEMO_CODE.equalsIgnoreCase(hotel.getCurrentHotelAccessCode());
			boolean checkinDateIsValid = ControllerUtils.NO_CHECKOUT_FOR_DEMOHOTEL && isDemoCheckin || customerDto.getCheckinTo()!=null && customerDto.getCheckinTo().after(new Date());
			
			isFullCheckin = isFullCheckin || isDemoCheckin;// set id DTO getter! || ControllerUtils.CHECKIN_FULL_ALWAYS;
			
			//If checkin exists, 
			if(hotel != null && !hotel.isVirtual() && checkinDateIsValid)
			{

				Date lastSameHotelCheckin = checkinRepository.getLastByCustomerAndHotelId(customer.getId(), hotel.getId());

				customerDto.setHotelId(hotel.getId());
				
				CustomerHotelCheckin nowGoodCheckin = null;
				
				//if no checkin exists, create a new one
				if(activeCustomerCheckins.isEmpty())
				{
					CustomerHotelCheckin customerHotelCheckin = new CustomerHotelCheckin();
					customerHotelCheckin.setCustomer(customer);
					customerHotelCheckin.setHotel(hotel);
					
					customerHotelCheckin.setStaffCheckin(customer.isHotelStaff());
					customerHotelCheckin.setFullCheckin(isFullCheckin);
					customerDto.setFullCheckin(isFullCheckin);
					
					customerHotelCheckin.setValidFrom(customerDto.getCheckinFrom());
					customerHotelCheckin.setValidTo(customerDto.getCheckinTo());
					checkinRepository.saveAndFlush(customerHotelCheckin);
					nowGoodCheckin = customerHotelCheckin;
					
					customerDto.setErrorResponse("");
					
					//Inform others!!!
					if(!customer.isHotelStaff() && !customer.isAdmin())
					{
						customerDto.setHotelId(hotel.getId());
						notificationService.notificateAboutEntityEvent(customerDto, HotelEvent.EVENT_CHECKIN, "New check-in in your hotel", hotel.getId());
					}
					
					//sent wellcome message to new fullCheckin customers
					if(!customer.isHotelStaff() && !customer.isAdmin())
					{
						Customer staffSender = this.getStaffbyHotelId(hotel.getId());
						
						if (staffSender!=null)
						{
							//TODO EUGEN: Check here if I already sent a message to him
							// ### SEND WELLCOME MESSAGE
							
							ChatMessage lastMessageFromStaff = chatRepository.getLastMessageByCustomerAndReceiverIds(staffSender.getId(), customer.getId());
							
							//Send only first staffSender message!    
							if(lastMessageFromStaff==null)
							{
								ChatMessage hiMessage = new ChatMessage();
								
								String wellcomeMsg = "Hi, welcome to our Hotel! Please write me, if you need something";
								String wellcomeGuestMsg = "Hi, welcome to thr guest view of our Hotel! Please get the hotel-code at the reception - without the hotel-code, you are not listed as a hotel guest, and you can not view the customers in the wall... ";
								
								if("de".equalsIgnoreCase(customer.getPrefferedLanguage()))
								{
									wellcomeMsg = "Hallo, herzlich willkommen im Hotel! Bitte schreiben Sie mir, wenn Sie etwas brauchen";
									wellcomeGuestMsg = "Hallo, herzlich willkommen im Hotel Gast-Zugang! Bitte bekommen Sie den Zugang-Kode an der Rezeption. Ohne Hotel-Kode sind ihre Aktivitäten in Hotel beschränkt";
								}
								
								hiMessage.setMessage(isFullCheckin? wellcomeMsg : wellcomeGuestMsg);
								
								hiMessage.setSender(staffSender);
								hiMessage.setReceiver(customer);
								hiMessage.setInitId(new Date().getTime());
								hiMessage.setTimestamp(new Timestamp(new Date().getTime()));
								chatRepository.save(hiMessage);
							}
							
							// ### Notificate Wall
							if(lastSameHotelCheckin==null || convertToLocalDateTime(lastSameHotelCheckin).getDayOfYear() != LocalDateTime.now().getDayOfYear() )
							{

								WallPostDto checkinNotificationWallDto = new WallPostDto();

								checkinNotificationWallDto.getSpecialContent().put("customerId", String.valueOf(customer.getId()));
								checkinNotificationWallDto.setHotelId(hotel.getId());
								checkinNotificationWallDto.setSenderId(staffSender.getId());
//								checkinNotificationWallDto.setsetValidUntil(new DateTime().plusDays(1).toDate());
								checkinNotificationWallDto.setInitId(new Date().getTime());
								checkinNotificationWallDto.setMessage("New guest at our hotel:");

								hotelService.addUpdateWallPost(checkinNotificationWallDto);
								
							}
							
						}
					}
					
				}
				else //correct actual checkin, if the same hotel
				{
					//EUGEN: only update checkin, no event
					
					for (CustomerHotelCheckin nextCheckin : activeCustomerCheckins)
					{
						if(hotel.equals(nextCheckin.getHotel())) //if the checkin of actual hotel, NO HOTEL EVENT!
						{
							//update the values of checkin
							nextCheckin.setValidFrom(customerDto.getCheckinFrom());
							nextCheckin.setValidTo(customerDto.getCheckinTo());
							nextCheckin.setStaffCheckin(customer.isHotelStaff());
							nextCheckin.setFullCheckin(isFullCheckin);
							checkinRepository.saveAndFlush(nextCheckin);
							customerDto.setErrorResponse("");
							nowGoodCheckin = nextCheckin;
							break;
						}
					}
				}
				
				long consistencyId = new Date().getTime();
				customer.setConsistencyId(consistencyId);
				
				cacheService.updateCustomerConsistencyId(customer.getId(), consistencyId);
				
				customerDto = customerService.convertCustomerToDto(customerRepository.saveAndFlush(customer), true, nowGoodCheckin);
				
			}
			else{ //if no hotel more, maybe cancel actual checkin
				
				for (CustomerHotelCheckin nextCheckin : activeCustomerCheckins)
				{
					nextCheckin.setActive(false);
					
					//                    if(nextCheckin.getCustomer().getSeenActivities()!=null)
					//                        nextCheckin.getCustomer().getSeenActivities().clear();
					
					customerRepository.save(nextCheckin.getCustomer());
					
					notificationService.notificateAboutEntityEvent(customerDto, HotelEvent.EVENT_CHECKOUT, "Checkout from your hotel", nextCheckin.getHotel().getId());
					
					checkinRepository.saveAndFlush(nextCheckin);
				}
				
				Long wantedHotelId = customerDto.getHotelId();
				
				long consistencyId = new Date().getTime();
				customer.setConsistencyId(consistencyId);
				cacheService.updateCustomerConsistencyId(customer.getId(), consistencyId);
				
				customerDto = customerService.convertMyCustomerToFullDto(customerRepository.saveAndFlush(customer));
				
				if(wantedHotelId>0 && (customerDto.getCheckinTo()==null || customerDto.getCheckinTo().before(new Date()))) {
					customerDto.setErrorResponse("Checkin Date is wrong or in past");
				}
				else
				{
					if(wantedHotelId>0)
					{
						customerDto.setErrorResponse("Hotel wurde nicht gefunden");
					}
					
					if(wantedHotelId == virtualHotelId && ControllerUtils.ALLOW_INIT_VIRTUAL_HOTEL)
					{
						customerDto.setHotelId(virtualHotelId);
						customerDto.setFullCheckin(true);
					}
				}
			}
			
			//Eugen: every time update customer current hotelId!!!
			cacheService.updateCustomerHotelId(customerDto.getId(), customerDto.getHotelId());
			
			
			
			return customerDto;
			
		}
		
		return null;
	}
	
	@Override
	public Customer getStaffbyHotelId(long hotelId)
	{
		Customer staff = null;
	 
		List<Customer> hotelStaffs = checkinRepository.getStaffByHotelId(hotelId);

		Iterator<Customer> staffIterator = hotelStaffs.iterator();
		
		if (staffIterator.hasNext())
		{
			staff = staffIterator.next();
		}
		
		return staff;
	}
	

	@Override
	public CustomerDTO updateOwnDtoCheckinInfo(CustomerDTO dto, CustomerHotelCheckin validCheckin)
	{
		if(dto==null || dto.getId()<=0){
			return  null;
		}
		
		//Reset dto only if consistencyId too old!!!
		//        dto = synchronizeCustomerToDto(dto);
		
		List<CustomerHotelCheckin> activeCheckins = validCheckin!=null ? new ArrayList<CustomerHotelCheckin>() : checkinRepository.getActiveByCustomerId(dto.getId(), new Date());
		
		//        CustomerHotelCheckin validCheckin = null;
		
		if(!activeCheckins.isEmpty() || validCheckin!=null) //exists active checkin
		{
			Iterator<CustomerHotelCheckin> iterator = activeCheckins.iterator();
			
			while(iterator.hasNext() && validCheckin==null)
			{
				CustomerHotelCheckin customerCheckin = iterator.next();
				
				//If checkin is old, set it not active
				if(new Date().after(customerCheckin.getValidTo()))
				{
					customerCheckin.setActive(false);
					checkinRepository.saveAndFlush(customerCheckin);
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
			Customer checkinCustomer = validCheckin.getCustomer();
			
			//TODO Eugen: cannot set logged automaticly
			//SETTING NEW CHECKIN
			checkinCustomer.setActive(true);
			
			dto.setFullCheckin(validCheckin.isFullCheckin());
			
			dto.setCheckinFrom(validCheckin.getValidFrom());
			dto.setCheckinTo(validCheckin.getValidTo());
			
			dto = customerService.fillDtoWithHotelInfo(dto, validCheckin.getHotel(), validCheckin);
		}
		
		if(validCheckin==null)
		{
			long initHotelId = cacheService.getInitHotelId();
			
			if(initHotelId>0)
			{
				dto.setHotelId(initHotelId);
				dto.setFullCheckin(true);
			}
		}
		
		return dto;
	}
}
