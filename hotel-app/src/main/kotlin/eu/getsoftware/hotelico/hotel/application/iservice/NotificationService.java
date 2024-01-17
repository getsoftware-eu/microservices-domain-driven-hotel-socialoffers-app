package eu.getsoftware.hotelico.hotel.application.iservice;

import eu.getsoftware.hotelico.checkin.domain.HotelActivity;
import eu.getsoftware.hotelico.clients.infrastructure.hotel.dto.CustomerDTO;
import eu.getsoftware.hotelico.customer.domain.CustomerRootEntity;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.hotel.infrastructure.utils.HotelEvent;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <br/>
 * Created by e.fanshil
 * At 05.02.2016 12:10
 */
public interface NotificationService
{
	
	@Transactional
	CustomerNotificationDTO getLastNotification(long customerId, boolean pushRequest);
	
//	void sendPushActivity(long receiverId, HotelEvent event, HotelActivity activity, String message, CustomerNotificationDto receiverNotification);

	@Transactional
	void createAndSendNotification(long receiverId, HotelEvent event);

	@Transactional
	void sendMailList(CustomerRootEntity customerEntity, Map<String, String> systemMessages);
	
	@Transactional
	void sendFeedMessage(CustomerRootEntity customerEntity, Map<String, String> systemMessages);
	
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
	CustomerNotificationDTO getCustomerNotification(long receiverId, HotelEvent event);
	
	void notificateAboutEntityEvent(CustomerDTO dto, HotelEvent event, String eventContent, long entityId);
	
	void sendPushRequest(long customerId);
	
	void sendPush(String pushId);
	
	void broadcastActivityNotification(HotelActivityDTO hotelActivityDto);
	
	@Transactional
	boolean sendPushToAllNotLoggedInHotel(HotelActivity hotelActivity);
	
	void broadcastWallNotification(WallPostDTO wallPostDto);

	void sendNotificationToCustomerOrGuest(CustomerRootEntity receiver, long guestCustomerId, HotelEvent eventDealNewUpdate);
}
