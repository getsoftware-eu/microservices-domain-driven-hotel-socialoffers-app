package eu.getsoftware.hotelico.service.booking.application.hotel.port.in;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model.InnerHotelEvent;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.IHotelActivity;

import java.util.Map;

/**
 * in port, only DTO, not inner entity
 * <br/>
 * Created by e.fanshil
 * At 05.02.2016 12:10
 */
public interface NotificationUseCase<E extends InnerHotelEvent>
{
	CustomerNotificationDTO getLastNotification(CustomerDomainEntityId customerId, boolean pushRequest);
	
	void createAndSendWebSocketNotification(CustomerDomainEntityId receiverId, E event);

	void sendMailList(CustomerDTO customerEntity, Map<String, String> systemMessages);
	
	void sendFeedMessage(CustomerDTO customerEntity, Map<String, String> systemMessages) throws Throwable;
	
	void createAndSendWebSocketNotification_Chat(CustomerDomainEntityId receiverId, E event, CustomerDomainEntityId senderId, String message);

	void createAndSendWebSocketNotification_Activity(CustomerDomainEntityId receiverId, E event, IHotelActivity activity, String message);
	
	/**
	 * returns null, if there is no changes between last Notification and timeDifference is not over 5 min!
	 * @param receiverId if(EVENT_ONLINE_CUSTOMERS) -> only online customers if it distinguish from previous notification
	 * @param event
	 * @return
	 */
	CustomerNotificationDTO getCustomerNotification(CustomerDomainEntityId receiverId, E event);
	
	void notificateAboutEntityEventWebSocket(CustomerDTO dto, E event, String eventContent, long entityId);
	
	void sendPushRequest(CustomerDomainEntityId customerId) throws Throwable;
	
	void sendPush(String pushId);
	
	void broadcastActivityNotification(HotelActivityDTO hotelActivityDto);
	
	boolean sendPushToAllNotLoggedInHotel(HotelActivityDTO hotelActivity);
	
	void broadcastWallNotification(WallPostDTO wallPostDto);

	void sendNotificationToCustomerOrGuest(CustomerDTO receiver, CustomerDomainEntityId guestCustomerId, E eventDealNewUpdate);
}
