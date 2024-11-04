package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.outPortServiceImpl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Sets;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.ChatMessageCommand;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.repository.CheckinRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.repository.CustomerRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.outPortServiceImpl.microservice.MessagingRabbitMQProducer;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository.HotelRepository;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.port.out.iPortService.CustomerPortService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.LastMessagesService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.awt.geom.Point2D.Double;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import static eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties.convertToDate;
import static eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties.convertToLocalDateTime;

/**
 * <br/>
 * Created by e.fanshil
 * At 16.10.2015 11:17
 */
@Slf4j
@Service
public class LastMessagesServiceImpl implements LastMessagesService
{
	
	@Autowired
	private MessagingRabbitMQProducer hotelRabbitMQProducer;
	
	private final CustomerPortService customerService;	
	
	private final CheckinRepository checkinRepository;

	private final HotelRepository hotelRepository;
	
	private final CustomerRepository customerRepository;
	
	private Map<Long, Date> lastCustomerOnlineMap = new HashMap<>();
	private Map<Long, Long> currentConsistencyIdsMap = new HashMap<>();
	
	private BlockingQueue<ChatMsgDTO> lastUnreadMsgQueue = new ArrayBlockingQueue<>(10);
	private BlockingQueue<ChatMsgDTO> lastMsgQueue = new ArrayBlockingQueue<>(10);
	
	private Map<Long, Map<Long, List<ChatMsgDTO>>> unreadChatsForReceiverFromSendersMap = new HashMap<>();
	
	private Map<Long, Map<Long, ChatMsgDTO>> lastMessageBetweenCustomersMap = new HashMap<>();
	
	private Map<String, CustomerDTO> waitingSocialCustomers =  new HashMap<>();
	
	private Map<Long, CustomerNotificationDTO> lastPushNotifications =  new HashMap<>();
	
	private Map<Long, CustomerNotificationDTO> lastFullNotifications =  new HashMap<>();
	
	private long virtualHotelId = 0L;
	
	private long demoHotelId = 0L;
	
	private HashMap<Long, Long> customersToHotelIdMap =  new HashMap<>();
	
	/**
	 * anonymGuests id to GPS point
	 */
	private HashMap<Long, Double> guestToGpsPointMap = new HashMap<>();
	
	public LastMessagesServiceImpl(CustomerPortService customerService, CheckinRepository checkinRepository, HotelRepository hotelRepository, ChatService chatService, CustomerRepository customerRepository)
	{
		this.customerService = customerService;
		this.checkinService = checkinService;
		this.hotelService = hotelService;
		this.chatService = chatService;
	}
	
	@PostConstruct
	public void init() {
		unreadChatsForReceiverFromSendersMap = new HashMap<>();
		lastMessageBetweenCustomersMap = new HashMap<>();
	}
	
	@Override
	public long getInitHotelId()
	{
		if(!AppConfigProperties.ALLOW_INIT_VIRTUAL_HOTEL)
		{
			return 0;	
		}
		
		return virtualHotelId>0 ? virtualHotelId: getRepositoryVirtualHotelId();
	}
	
	@Override
	public long getDemoHotelId()
	{
		return demoHotelId>0? demoHotelId: getRepositoryDemoHotelId();
	}

	private int getRepositoryVirtualHotelId()
	{
		Integer virtualHotelId = hotelRepository.getVirtualHotelId();

		return  virtualHotelId==null? 0 : virtualHotelId;
	}
	
	private int getRepositoryDemoHotelId()
	{
		Integer demoHotelId = hotelRepository.getDemoHotelId();

		return  demoHotelId==null? 0 : demoHotelId;
	}
	
	@Override
	public Optional<Date> getLastCustomerOnlineTime(long customerId)
	{
		Optional<Date> lastCustomerOnlineTime = lastCustomerOnlineMap.entrySet().stream().filter(e -> e.getKey() == customerId).map(Entry::getValue).findFirst();
				
 		if(!lastCustomerOnlineTime.isPresent())
		{
			Optional<ICustomerRootEntity> customerEntityOpt = customerService.getEntityById(customerId);
			
			if(customerEntityOpt.isEmpty() || !customerEntityOpt.get().isLogged())
			{
				return Optional.empty();
			}
			
			Date lastSeenOnline = customerEntityOpt.get().getLastSeenOnline();
			
			if(lastSeenOnline != null)
			{
				lastCustomerOnlineMap.put(customerId, lastSeenOnline);
				return Optional.of(lastSeenOnline);
			}
		}
		
		return lastCustomerOnlineTime;
	}
	
	@Override
	public void updateCustomerConsistencyId(long customerId, long consustencyId)
	{
		currentConsistencyIdsMap.put(customerId, consustencyId);
	}
	
//	@Override
	private void updateLastOnlineTime(long customerId)
	{
		boolean updateCustomerRootEntity = true;
		
		if(lastCustomerOnlineMap.keySet().contains(customerId))
		{
			updateCustomerRootEntity = false;
			
			Date lastDate = lastCustomerOnlineMap.get(customerId);
			
			//if it was online yesterday
			if(convertToLocalDateTime(lastDate).getDayOfYear()!= LocalDateTime.now().getDayOfYear())
			{
				updateCustomerRootEntity = true;
			}
			
			lastCustomerOnlineMap.put(customerId, new Date());
		}
		
		if(updateCustomerRootEntity)
		{
			Optional<ICustomerRootEntity> customerEntityOpt = customerService.getEntityById(customerId);
			
			if(customerEntityOpt.isPresent())
			{
				customerEntityOpt.get().updateLastSeenOnline();
				lastCustomerOnlineMap.put(customerId, new Date());
			}
		}
	}
	
	@Override
	public List<CustomerDBEntity> getOnlineCustomers()
	{
		List<CustomerDBEntity> resultList = new ArrayList<>();
		
		Set<Long> customerIds = lastCustomerOnlineMap.keySet();
		
		if(customerIds.isEmpty())
		{
			List<CustomerDBEntity> onlinein24HCustomerEntities =  customerService.getAllIn24hOnline();
			
			for (CustomerDBEntity nextOnlineIn24HCustomerRootEntity : onlinein24HCustomerEntities)
			{
				lastCustomerOnlineMap.put(nextOnlineIn24HCustomerRootEntity.getId(), nextOnlineIn24HCustomerRootEntity.getLastSeenOnline());
				
				if(isNowStillOnline(nextOnlineIn24HCustomerRootEntity.getId()))
				{
					resultList.add(nextOnlineIn24HCustomerRootEntity);
				}
			}
		}
		else {
			
			List<Long> nowOnlineCustomerIdx = new ArrayList<>();
			
			for (long nextCustomerId: customerIds)
			{
				if(isNowStillOnline(nextCustomerId))
				{
					nowOnlineCustomerIdx.add(nextCustomerId);
				}
			}
			
			if(!nowOnlineCustomerIdx.isEmpty())
			{
				resultList.addAll(customerRepository.findByIdIn(nowOnlineCustomerIdx));
			}
		}
		
		return resultList;
	}
	
	@Override
	public void checkCustomerOffline(long offlineId)	
	{
		if(lastCustomerOnlineMap.containsKey(offlineId))
		{
			lastCustomerOnlineMap.remove(offlineId);
		}
	}
	
	@Override
	public void checkCustomerOnline(long onlineId)	
	{
		updateLastOnlineTime(onlineId);
	}
	
	@Override
	public long getCustomerConsistencyId(long customerId)
	{
		if(currentConsistencyIdsMap.containsKey(customerId))
		{
			return currentConsistencyIdsMap.get(customerId);
		}
		
		CustomerDBEntity customerEntity = customerRepository.getOne(customerId);
		
		long newConsistencyId = customerEntity !=null? customerEntity.getConsistencyId(): -1;

		if(customerEntity !=null)
		{
			currentConsistencyIdsMap.put(customerId, newConsistencyId);
		}
		
		return newConsistencyId;
		
	}
	
	@Override
	public void markChatRead(long receiverId, long senderId)
	{
		Object unreadForReceiver = unreadChatsForReceiverFromSendersMap.entrySet().stream().filter(e -> e.getKey() == receiverId).map(Entry::getValue).findFirst();
		
		//empty all unread messages for this sender!
		//remove empty Lists from Map!!! sonst unread chat!!!

		if(unreadForReceiver!=null)
		{
			unreadMsgQueue.stream().filter(chatMessage -> chatMessage.getReceiver().getId() == receiverId).forEach(chatMessage -> chatMessage.re);
			unreadChatsForReceiverFromSendersMap.get(receiverId).remove(senderId);
		}
	}
	
	@Override
	public void markMessageRead(ChatMessageView readMessage)
	{
		Map<Long, List<ChatMessageView>> unreadForeReceiver = unreadChatsForReceiverFromSendersMap.get(readMessage.getReceiver().getId());

		if(unreadForeReceiver==null)
		{
			unreadForeReceiver = new HashMap<>();

			//TODO Eugen: who is receiver hier????
			List<ChatMessageView> unreadMessagesByReceiver = chatRepository.getUnreadChatMessagesForCustomer(readMessage.getReceiver().getId());
			
			for (ChatMessageView nextUnreadMessageByReceiver: unreadMessagesByReceiver)
			{
				List<ChatMessageView> unreadMessagesForSenderByReceiver = unreadForeReceiver.get(readMessage.getSender().getId());
				
				if(unreadMessagesForSenderByReceiver==null)
				{
					unreadMessagesForSenderByReceiver = new ArrayList<>();
				}

				unreadMessagesForSenderByReceiver.add(nextUnreadMessageByReceiver);

				unreadForeReceiver.put(readMessage.getSender().getId(), unreadMessagesForSenderByReceiver);
			}
			
			unreadChatsForReceiverFromSendersMap.put(readMessage.getReceiver().getId(), unreadForeReceiver);
		}
		
		List<ChatMessageView> unreadSenderReceiver = unreadForeReceiver.get(readMessage.getSender().getId());
		
		if(unreadSenderReceiver!=null && unreadSenderReceiver.contains(readMessage))
		{
			unreadSenderReceiver.remove(readMessage);
			
			if(unreadSenderReceiver.isEmpty())
			{
				//remove empty Lists from Map!!! sonst unread chat!!!
				unreadForeReceiver.remove(readMessage.getSender().getId());
			}
			else{
				unreadForeReceiver.put(readMessage.getSender().getId(), unreadSenderReceiver);
			}
			
			unreadChatsForReceiverFromSendersMap.put(readMessage.getReceiver().getId(), unreadForeReceiver);
		}
		
	}
	
	@Override
	public void updateUnreadMessagesToCustomer(ChatMessageView newUnreadMessage)
	{
		//If there is no unreadMessages for this receiver, create it from DB
		long receiverId = newUnreadMessage.getReceiver().getId();
		if(!unreadChatsForReceiverFromSendersMap.containsKey(receiverId))
		{
			Map<Long, List<ChatMessageView>> unreadMessagesForReceiver = getUnreadChatsBySender(receiverId);
			
			unreadChatsForReceiverFromSendersMap.put(receiverId, unreadMessagesForReceiver);
		}
		
		long newSenderId = newUnreadMessage.getSender().getId();
		
		Map<Long, List<ChatMessageView>> unreadByReceiver = unreadChatsForReceiverFromSendersMap.get(receiverId);

		List<ChatMessageView> unreadForSender = new ArrayList<>();

		//ADD new ChatMessage to List on demand, if it is not constains there
		if(unreadByReceiver!=null && unreadByReceiver.containsKey(newSenderId))
		{
			unreadForSender = unreadByReceiver.get(newSenderId);
		}
		
		unreadForSender.add(newUnreadMessage);

		unreadByReceiver.put(newSenderId, unreadForSender);
		unreadChatsForReceiverFromSendersMap.put(receiverId, unreadByReceiver);
		
	}	
	
	@Override
	public Map<Long, List<ChatMessageView>> getCustomerUnreadChatsBySenders(long receiverId)
	{
		//set from DB is not exists
		if(!unreadChatsForReceiverFromSendersMap.containsKey(receiverId))
		{
			Map<Long, List<ChatMessageView>> unreadMessagesForReceiver = getUnreadChatsBySender(receiverId);
			
			unreadChatsForReceiverFromSendersMap.put(receiverId, unreadMessagesForReceiver);
		}
		
		return unreadChatsForReceiverFromSendersMap.get(receiverId);
	}
	
	
	private Map<Long, List<ChatMessageView>> getUnreadChatsBySender(long receiverId)
	{
		List<ChatMessageView> allUnreadChatsForReceiver = chatRepository.getUnreadChatMessagesForCustomer(receiverId);
		
		Map<Long, List<ChatMessageView>> unreadMessagesForReceiver = new HashMap<>();
		
		if(allUnreadChatsForReceiver!=null)
		{
			for(ChatMessageView nextUnreadChatmessage: allUnreadChatsForReceiver)
			{
				long nextSenderId = nextUnreadChatmessage.getSender().getId();

				List<ChatMessageView> unreadFromSender = unreadMessagesForReceiver.containsKey(nextSenderId)? unreadMessagesForReceiver.get(nextSenderId) : new ArrayList<ChatMessageView>();

				unreadFromSender.add(nextUnreadChatmessage);

				unreadMessagesForReceiver.put(nextSenderId, unreadFromSender);
			}
		}
		return unreadMessagesForReceiver;
	}
	
	@Override
	public List<Long> getOnlineCustomerIds()
	{
		List<Long> resultList = new ArrayList<>();
		
		Set<Long> customerIds = lastCustomerOnlineMap.keySet();
		
		if(customerIds.isEmpty())
		{
			List<CustomerDBEntity> onlinein24HCustomerEntities =  customerService.getAllIn24hOnline();
			
			for (CustomerDBEntity nextOnlineIn24HCustomerRootEntity : onlinein24HCustomerEntities)
			{
				lastCustomerOnlineMap.put(nextOnlineIn24HCustomerRootEntity.getId(), nextOnlineIn24HCustomerRootEntity.getLastSeenOnline());
				
				if(isNowStillOnline(nextOnlineIn24HCustomerRootEntity.getId()))
				{
					resultList.add(nextOnlineIn24HCustomerRootEntity.getId());
				}
			}
		}
		else {
			
			for (long nextCustomerId: customerIds)
			{
				if(isNowStillOnline(nextCustomerId))
				{
					resultList.add(nextCustomerId);
				}
			}
		}
		
		return resultList;
	}
	
	private boolean isNowStillOnline(long customerId)
	{
		Optional<Date> lastOnline = getLastCustomerOnlineTime(customerId);
		
		return lastOnline.isPresent() && lastOnline.get().after(convertToDate(LocalDateTime.now().minusMinutes(ONLINE_DELAY_MINUTES)));
	}
	
	@Override
	public void setLastMessageBetweenCustomers(ChatMessageView lastMessage)
	{
		long senderId = lastMessage.getSender().getId();
		long receiverId = lastMessage.getReceiver().getId();
		
		long fromMin = senderId < receiverId? senderId : receiverId;
		long toMax = senderId > receiverId? senderId : receiverId;
		
		Map<Long, ChatMessageView> newLastMsgMap = lastMessageBetweenCustomersMap.get(fromMin);
				
		if(newLastMsgMap==null)
		{
			newLastMsgMap = new HashMap<>();
		}
			
		newLastMsgMap.put(toMax, lastMessage);
		
		lastMessageBetweenCustomersMap.put(fromMin, newLastMsgMap);
	}

	@Override
	public void addWaitingSocialDto(String sessionState, CustomerDTO socialCustomer)
	{
		waitingSocialCustomers.put(sessionState, socialCustomer);
	}

	@Override
	public Optional<CustomerDTO> getWaitingSocialCustomer(String sessionStateVal)
	{
		CustomerDTO customerDTO = waitingSocialCustomers.get(sessionStateVal);
		
		return Optional.ofNullable(customerDTO);
	}
	
	
	@Override
	public CustomerNotificationDTO getLastPushNotifiation(long customerId)
	{
		if(customerId <= 0)
		{
			throw new IllegalArgumentException("customerId should be > 0, but value is " + customerId);
		}
		
		return lastPushNotifications.get(customerId);
	}

	@Override
	public void setLastPushNotifiation(long customerId, CustomerNotificationDTO lastNotification)
	{
		lastPushNotifications.put(customerId, lastNotification);
	}
	
	@Override
	public void setLastFullNotification(long receiverId, CustomerNotificationDTO nextNotification)
	{
		lastFullNotifications.put(receiverId, nextNotification);
	}
	
	@Override
	public CustomerNotificationDTO getLastFullNotification(long receiverId)
	{
		return lastFullNotifications.get(receiverId);
	}
	
	@Override
	public boolean isNotificationDelayReady(long sessionCustomerId)
	{
		if(!lastFullNotifications.containsKey(sessionCustomerId))
		{
			return true;
		}
		
		CustomerNotificationDTO lastNotification = lastFullNotifications.get(sessionCustomerId);
		
		if(lastNotification==null)
		{
			return true;
		}
		
		long lastNotificationTime = lastNotification.getCreationTime();
		
		long diff = (new Date()).getTime() - lastNotificationTime;
		
		long diffMinutes = diff / (60 * 1000) % 60;
		
		return diffMinutes >= AppConfigProperties.NOTIFICATION_MIN_DELAY_MINUTES;
	}

	@Override
	public long getCustomerHotelId(long customerId)
	{
		if(!customersToHotelIdMap.containsKey(customerId))
		{
			customersToHotelIdMap.put(customerId, checkinRepository.getCustomerHotelId(customerId, new Date()));
		}

		Long hotelId = customersToHotelIdMap.get(customerId);
		return hotelId!=null? hotelId : getInitHotelId();
	}

	@Override
	public void updateCustomerHotelId(long customerId, long hotelId)
	{
		customersToHotelIdMap.put(customerId, hotelId);
	}
	
	@Override
	public void addGuestGpsPosition(long requesterId, Double latLonPoint)
	{
		guestToGpsPointMap.put(requesterId, latLonPoint);
	}
	
	@Override
	public Double getGuestGpsPosition(long requesterId)
	{
		return guestToGpsPointMap.get(requesterId);
	}
	
	@Override
	public void markLastMessageBetweenCustomers(ChatMessageView seenMessage)
	{
		long senderId = seenMessage.getSender().getId();
		long receiverId = seenMessage.getReceiver().getId();

		long fromMin = senderId < receiverId? senderId : receiverId;
		long toMax = senderId > receiverId? senderId : receiverId;

		Map<Long, ChatMessageView> newLastMsgMap = lastMessageBetweenCustomersMap.get(fromMin);

		if(newLastMsgMap==null)
		{
			newLastMsgMap = new HashMap<>();
		}

		if(newLastMsgMap.get(toMax)!=null && newLastMsgMap.get(toMax).equals(seenMessage))
		{
			newLastMsgMap.put(toMax, seenMessage);
			lastMessageBetweenCustomersMap.put(fromMin, newLastMsgMap);
		}

	}
	
	@Override
	public ChatMsgDTO getLastMessageBetweenCustomers(long senderId, long receiverId){
		
		long fromMin = senderId < receiverId? senderId : receiverId;
		long toMax = senderId > receiverId? senderId : receiverId;

		Map<Long, ChatMsgDTO> lastMsgMap = lastMessageBetweenCustomersMap.get(fromMin);
		
		if(lastMsgMap==null || !lastMsgMap.containsKey(toMax))
		{
			// ChatMessage dbLastMessage = chatRepository.getLastMessageByCustomerAndReceiverIds(fromMin, toMax);
			
			//TODO asynchron request chat microService
			RabbitConverterFuture<ChatMsgDTO> chatMsgOptFuture = hotelRabbitMQProducer.sendAsynchDirectExchangeMethodCall(new ChatMessageCommand(senderId, receiverId, true, ""));
			
			chatMsgOptFuture.addCallback(new ListenableFutureCallback<>() {
				@Override
				public void onFailure(Throwable ex) {
					// ...
				}
				
				@Override
				public void onSuccess(ChatMsgDTO chatMsgDTO) {
					log.info("chatMsgDTO received {}", chatMsgDTO);
					updateAsynchLastMessageFromCustomer(chatMsgDTO, fromMin, toMax);
				}
			});
		}
		
		return lastMsgMap!=null? lastMsgMap.get(toMax) : null;
	}
	
	private void updateAsynchLastMessageFromCustomer(ChatMsgDTO chatMsgDTO, long fromMin, long toMax)
	{
		if(chatMsgDTO != null)
		{
			Map<Long, ChatMsgDTO> localLastMsgMap = lastMessageBetweenCustomersMap.get(fromMin);
			if(localLastMsgMap == null)
			{
				localLastMsgMap = new HashMap<>();
			}
			
			localLastMsgMap.put(toMax, chatMsgDTO);
			lastMessageBetweenCustomersMap.put(fromMin, localLastMsgMap);
		}
	}
	
	///###############################################################
	
	private LoadingCache<String, UserStats> statsByUser = CacheBuilder.newBuilder().build(new CacheLoader<String, UserStats>() {
		
		@Override
		public UserStats load(String key) throws Exception {
			return new UserStats();
		}
		
	});
	
	public void mark(String username) {
		statsByUser.getUnchecked(username).mark();
	}
	
	public Set<String> getActiveUsers() {
		Set<String> active = Sets.newTreeSet();
		for (String user : statsByUser.asMap().keySet()) {
			// has the user checked in within the last 5 seconds?
			if ((System.currentTimeMillis() - statsByUser.getUnchecked(user).lastAccess()) < 5000) {
				active.add(user);
			}
		}
		return active;
	}
	
	private static class UserStats {
		
		private AtomicLong lastAccess = new AtomicLong(System.currentTimeMillis());
		
		private void mark() {
			lastAccess.set(System.currentTimeMillis());
		}
		
		private long lastAccess() {
			return lastAccess.get();
		}
	}
}
