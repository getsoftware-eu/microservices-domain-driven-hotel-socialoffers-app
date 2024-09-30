package eu.getsoftware.hotelico.hotelapp.application.hotel.port.in;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.IHotelActivity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.utils.IHotelEvent;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;

import java.util.Map;

/**
 * <br/>
 * Created by e.fanshil
 * At 05.02.2016 12:10
 */
public interface NotificationUseCase
{
	CustomerNotificationDTO getLastNotification(long customerId, boolean pushRequest);
	
	void createAndSendNotification(long receiverId, IHotelEvent event);

	void sendMailList(ICustomerRootEntity customerEntity, Map<String, String> systemMessages);
	
	void sendFeedMessage(ICustomerRootEntity customerEntity, Map<String, String> systemMessages);
	
	void createAndSendPushNotification_Chat(long receiverId, IHotelEvent event, long senderId, String message);

	void createAndSendPushNotification_Activity(long receiverId, IHotelEvent event, IHotelActivity activity, String message);
	
	/**
	 * returns null, if there is no changes between last Notification and timeDifference is not over 5 min!
	 * @param receiverId if(EVENT_ONLINE_CUSTOMERS) -> only online customers if it distinguish from previous notification
	 * @param event
	 * @return
	 */
	CustomerNotificationDTO getCustomerNotification(long receiverId, IHotelEvent event);
	
	void notificateAboutEntityEvent(CustomerDTO dto, IHotelEvent event, String eventContent, long entityId);
	
	void sendPushRequest(long customerId);
	
	void sendPush(String pushId);
	
	void broadcastActivityNotification(HotelActivityDTO hotelActivityDto);
	
	boolean sendPushToAllNotLoggedInHotel(IHotelActivity hotelActivity);
	
	void broadcastWallNotification(WallPostDTO wallPostDto);

	void sendNotificationToCustomerOrGuest(ICustomerRootEntity receiver, long guestCustomerId, IHotelEvent eventDealNewUpdate);
}
