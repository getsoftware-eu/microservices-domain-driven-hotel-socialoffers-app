package de.hotelico.service;

import java.util.Map;

import de.hotelico.model.Customer;
import org.springframework.transaction.annotation.Transactional;

import de.hotelico.dto.CustomerDTO;
import de.hotelico.dto.CustomerNotificationDto;
import de.hotelico.dto.HotelActivityDto;
import de.hotelico.dto.WallPostDto;
import de.hotelico.model.HotelActivity;
import de.hotelico.utils.HotelEvent;

/**
 * <br/>
 * Created by e.fanshil
 * At 05.02.2016 12:10
 */
public interface NotificationService
{
	
	@Transactional
	CustomerNotificationDto getLastNotification(long customerId, boolean pushRequest);
	
//	void sendPushActivity(long receiverId, HotelEvent event, HotelActivity activity, String message, CustomerNotificationDto receiverNotification);

	@Transactional
	void createAndSendNotification(long receiverId, HotelEvent event);

	@Transactional
	void sendMailList(Customer customer, Map<String, String> systemMessages);
	
	@Transactional
	void sendFeedMessage(Customer customer, Map<String, String> systemMessages);
	
	@Transactional
	void createAndSendPushNotification_Chat(long receiverId, HotelEvent event, long senderId, String message);

	@Transactional
	void createAndSendPushNotification_Activity(long receiverId, HotelEvent event, HotelActivity activity, String message);
	
	/**
	 * returns null, if there is no changes between last Notification and timeDifference is not over 5 min!
	 * @param receiverId if(EVENT_ONLINE_CUSTOMERS) -> only online customers if it distinguish from previous notification
	 * @param event
	 * @return
	 */
	@Transactional
	CustomerNotificationDto getCustomerNotification(long receiverId, HotelEvent event);
	
	void notificateAboutEntityEvent(CustomerDTO dto, HotelEvent event, String eventContent, long entityId);
	
	void sendPushRequest(long customerId);
	
	void sendPush(String pushId);
	
	void broadcastActivityNotification(HotelActivityDto hotelActivityDto);
	
	@Transactional
	boolean sendPushToAllNotLoggedInHotel(HotelActivity hotelActivity);
	
	void broadcastWallNotification(WallPostDto wallPostDto);

	void sendNotificationToCustomerOrGuest(Customer receiver, long guestCustomerId, HotelEvent eventDealNewUpdate);
}
