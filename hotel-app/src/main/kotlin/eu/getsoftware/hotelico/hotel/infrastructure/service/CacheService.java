package eu.getsoftware.hotelico.hotel.infrastructure.service;

import java.awt.geom.Point2D.Double;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import eu.getsoftware.hotelico.chat.domain.ChatMessage;
import eu.getsoftware.hotelico.customer.infrastructure.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.CustomerNotificationDto;
import eu.getsoftware.hotelico.hotel.model.CustomerEntity;

/**
 * <br/>
 * Created by e.fanshil
 * At 16.10.2015 11:17
 */
public interface CacheService
{
	/**
	 * get cashed id of virtual hotel
	 *
	 * @return
	 */
	long getInitHotelId();	
	
	/**
	 * get cashed id of virtual hotel
	 *
	 * @return
	 */
	long getDemoHotelId();

	/**
	 * lastOnlineSeenTime for customer id
	 *
	 * @param customerId
	 * @return NULL if OFFLINE!
	 */
	@Transactional
	Optional<Date> getLastCustomerOnlineTime(long customerId);

	/**
	 * get still online customerList. DB QUERY!!!
	 *
	 * @return
	 */
	@Transactional
	List<CustomerEntity> getOnlineCustomers();

	@Transactional
	List<Long> getOnlineCustomerIds();

	//	@Transactional
	void checkCustomerOffline(long offlineId);

	@Transactional
	void checkCustomerOnline(long onlineId);

	@Transactional
	void updateCustomerConsistencyId(long customerId, long consustencyId);

	@Transactional
	long getCustomerConsistencyId(long customerId);

	@Transactional
	Map<Long, List<ChatMessage>> getCustomerUnreadChatsBySenders(long receiverId);

	@Transactional
	void updateUnreadMessagesToCustomer(ChatMessage newMessage);

	/**
	 * for every receiver, we collect messages, that was not read by ihm.
	 * So if a receiver reads a new message, we have to delete it from here!
	 * @param readMessage
	 */
	void markMessageRead(ChatMessage readMessage);

	void markChatRead(long receiverId, long senderId);

	@Transactional
	ChatMessage getLastMessageBetweenCustomers(long senderId, long receiverId);
	
	void markLastMessageBetweenCustomers(ChatMessage seenMessage);
	
	@Transactional
	void setLastMessageBetweenCustomers(ChatMessage lastMessage);

	void addWaitingSocialDto(String sessionState, CustomerDTO socialCustomer);

	CustomerDTO getWaitingSocialCustomer(String sessionState);

	CustomerNotificationDto getLastPushNotifiation(long customerId);
	
	void setLastPushNotifiation(long customerId, CustomerNotificationDto lastNotification);
	
	void setLastFullNotification(long receiverId, CustomerNotificationDto nextNotification);
	
	CustomerNotificationDto getLastFullNotification(long receiverId);
	
	/**
	 * send periodical notification max every 5 min!
	 * @param sessionCustomerId
	 * @return
	 */
	boolean isNotificationDelayReady(long sessionCustomerId);

	long getCustomerHotelId(long customerId);
	
	void updateCustomerHotelId(long customerId, long hotelId);
	
	void addGuestGpsPosition(long requesterId, Double latLonPoint);
	
	Double getGuestGpsPosition(long requesterId);
	
	static final int ONLINE_DELAY_MINUTES = 25;
}