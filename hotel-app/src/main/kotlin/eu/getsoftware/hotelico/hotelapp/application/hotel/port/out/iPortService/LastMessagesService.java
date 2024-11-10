package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import org.springframework.transaction.annotation.Transactional;

import java.awt.geom.Point2D.Double;
import java.util.Date;
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
	Optional<Date> getLastCustomerOnlineTime(long customerId) throws Throwable;

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
	void checkCustomerOffline(long offlineId);

	@Transactional
	void checkCustomerOnline(long onlineId) throws Throwable;

	@Transactional
	void updateCustomerConsistencyId(CustomerDomainEntityId customerId, long consustencyId);

	@Transactional
	long getCustomerConsistencyId(long customerId);

	@Transactional
	Map<Long, List<ChatMsgDTO>> getCustomerUnreadChatsBySenders(long receiverId);

	@Transactional
	void updateUnreadMessagesToCustomer(ChatMsgDTO newMessage);

	/**
	 * for every receiver, we collect messages, that was not read by ihm.
	 * So if a receiver reads a new message, we have to delete it from here!
	 * @param readMessage
	 */
	void markMessageRead(ChatMsgDTO readMessage);

	void markChatRead(long receiverId, long senderId);

	ChatMsgDTO getLastMessageBetweenCustomers(long senderId, long receiverId);
	
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
	CustomerNotificationDTO getLastPushNotifiation(long customerId);
	
	void setLastPushNotifiation(long customerId, CustomerNotificationDTO lastNotification);
	
	void setLastFullNotification(long receiverId, CustomerNotificationDTO nextNotification);
	
	CustomerNotificationDTO getLastFullNotification(long receiverId);
	
	/**
	 * send periodical notification max every 5 min!
	 * @param sessionCustomerId
	 * @return
	 */
	boolean isNotificationDelayReady(long sessionCustomerId);

	long getCustomerHotelId(long customerId);
	
	void updateCustomerHotelId(CustomerDomainEntityId customerId, HotelDomainEntityId hotelId);
	
	void addGuestGpsPosition(long requesterId, Double latLonPoint);
	
	Double getGuestGpsPosition(long requesterId);
	
	static final int ONLINE_DELAY_MINUTES = 25;
}