package eu.getsoftware.hotelico.hotelapp.application.hotel.port.in;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelEvent;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.IHotelActivity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;

import java.util.Map;

/**
 * in port, only DTO, not inner entity
 * <br/>
 * Created by e.fanshil
 * At 05.02.2016 12:10
 */
public interface NotificationUseCase<E extends HotelEvent>
{
	CustomerNotificationDTO getLastNotification(long customerId, boolean pushRequest);
	
	void createAndSendWebSocketNotification(long receiverId, E event);

	void sendMailList(CustomerDTO customerEntity, Map<String, String> systemMessages);
	
	void sendFeedMessage(CustomerDTO customerEntity, Map<String, String> systemMessages);
	
	void createAndSendWebSocketNotification_Chat(long receiverId, E event, long senderId, String message);

	void createAndSendWebSocketNotification_Activity(long receiverId, E event, IHotelActivity activity, String message);
	
	/**
	 * returns null, if there is no changes between last Notification and timeDifference is not over 5 min!
	 * @param receiverId if(EVENT_ONLINE_CUSTOMERS) -> only online customers if it distinguish from previous notification
	 * @param event
	 * @return
	 */
	CustomerNotificationDTO getCustomerNotification(long receiverId, E event);
	
	void notificateAboutEntityEventWebSocket(CustomerDTO dto, E event, String eventContent, long entityId);
	
	void sendPushRequest(long customerId);
	
	void sendPush(String pushId);
	
	void broadcastActivityNotification(HotelActivityDTO hotelActivityDto);
	
	boolean sendPushToAllNotLoggedInHotel(HotelActivityDTO hotelActivity);
	
	void broadcastWallNotification(WallPostDTO wallPostDto);

	void sendNotificationToCustomerOrGuest(CustomerDTO receiver, long guestCustomerId, E eventDealNewUpdate);
}
