package eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import org.springframework.transaction.annotation.Transactional;

import java.awt.geom.Point2D.Double;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <br/>
 * Created by e.fanshil
 * At 16.10.2015 11:17
 */
public interface LastMessagesService
{
	/**
	 * get cashed id of virtual hotel
	 *
	 * @return
	 */
	HotelDomainEntityId getInitHotelId();	
	
	/**
	 * get cashed id of virtual hotel
	 *
	 * @return
	 */
	HotelDomainEntityId getDemoHotelId();

	/**
	 * lastOnlineSeenTime for customer id
	 *
	 * @param customerId
	 * @return NULL if OFFLINE!
	 */
	@Transactional
	Optional<LocalDate> getLastCustomerOnlineTime(CustomerDomainEntityId customerId) throws Throwable;

	/**
	 * get still online customerList. DB QUERY!!!
	 *
	 * @return
	 */
	@Transactional
	List<CustomerDomainEntityId> getOnlineCustomers();

	@Transactional
	List<CustomerDomainEntityId> getOnlineCustomerIds();

	//	@Transactional
	void checkCustomerOffline(CustomerDomainEntityId offlineId);

	@Transactional
	void checkCustomerOnline(CustomerDomainEntityId onlineId) throws Throwable;

	@Transactional
	void updateCustomerConsistencyId(CustomerDomainEntityId customerId);

	@Transactional
	long getCustomerConsistencyId(CustomerDomainEntityId customerId);

	@Transactional
	Map<CustomerDomainEntityId, List<ChatMsgDTO>> getCustomerUnreadChatsBySenders(CustomerDomainEntityId receiverId);

	@Transactional
	void updateUnreadMessagesToCustomer(ChatMsgDTO newMessage);

	/**
	 * for every receiver, we collect messages, that was not read by ihm.
	 * So if a receiver reads a new message, we have to delete it from here!
	 * @param readMessage
	 */
	void markMessageRead(ChatMsgDTO readMessage);

	void markChatRead(CustomerDomainEntityId receiverId, CustomerDomainEntityId senderId);

	ChatMsgDTO getLastMessageBetweenCustomers(CustomerDomainEntityId senderId, CustomerDomainEntityId receiverId);
	
	void markLastMessageBetweenCustomers(ChatMsgDTO seenMessage);
	
	@Transactional
	void setLastMessageBetweenCustomers(ChatMsgDTO lastMessage);

	void addWaitingSocialDto(String sessionState, CustomerDTO socialCustomer);

	Optional<CustomerDTO> getWaitingSocialCustomer(String sessionState);
	
	/**
	 *
	 * @param customerId  
	 * @throws IllegalArgumentException if customerId < 0                     
	 * @return
	 */
	CustomerNotificationDTO getLastPushNotifiation(CustomerDomainEntityId customerId);
	
	void setLastPushNotifiation(CustomerDomainEntityId customerId, CustomerNotificationDTO lastNotification);
	
	void setLastFullNotification(CustomerDomainEntityId receiverId, CustomerNotificationDTO nextNotification);
	
	CustomerNotificationDTO getLastFullNotification(CustomerDomainEntityId receiverId);
	
	/**
	 * send periodical notification max every 5 min!
	 * @param sessionCustomerId
	 * @return
	 */
	boolean isNotificationDelayReady(CustomerDomainEntityId sessionCustomerId);

	HotelDomainEntityId getCustomerHotelId(CustomerDomainEntityId customerId);
	
	void updateCustomerHotelId(CustomerDomainEntityId customerId, HotelDomainEntityId hotelId);
	
	void addGuestGpsPosition(CustomerDomainEntityId requesterId, Double latLonPoint);
	
	Double getGuestGpsPosition(CustomerDomainEntityId requesterId);
	
	static final int ONLINE_DELAY_MINUTES = 25;
}