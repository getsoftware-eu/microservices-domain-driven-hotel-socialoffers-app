package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.outPortServiceImpl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Sets;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.repository.CheckinRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.repository.CustomerRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.repository.HotelRepository;
import eu.getsoftware.hotelico.hotelapp.application.customer.port.out.iPortService.CustomerPortService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.LastMessagesService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D.Double;
import java.time.LocalDate;
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
@RequiredArgsConstructor
public class LastMessagesServiceImpl implements LastMessagesService
{
	
//	@Autowired
//	private MessagingRabbitMQProducer hotelRabbitMQProducer;
	
	private final CustomerPortService customerService;	
	
	private final CheckinRepository checkinRepository;

	private final HotelRepository hotelRepository;
	
	private final CustomerRepository customerRepository;
	
	private Map<CustomerDomainEntityId, LocalDate> lastCustomerOnlineMap = new HashMap<>();
	private Map<CustomerDomainEntityId, Long> currentConsistencyIdsMap = new HashMap<>();
	
	private BlockingQueue<ChatMsgDTO> lastUnreadMsgQueue = new ArrayBlockingQueue<>(10);
	private BlockingQueue<ChatMsgDTO> lastMsgQueue = new ArrayBlockingQueue<>(10);
	
	private Map<CustomerDomainEntityId, Map<CustomerDomainEntityId, List<ChatMsgDTO>>> unreadChatsForReceiverFromSendersMap = new HashMap<>();
	
	private Map<CustomerDomainEntityId, Map<CustomerDomainEntityId, ChatMsgDTO>> lastMessageBetweenCustomersMap = new HashMap<>();
	
	private Map<String, CustomerDTO> waitingSocialCustomers =  new HashMap<>();
	
	private Map<CustomerDomainEntityId, CustomerNotificationDTO> lastPushNotifications =  new HashMap<>();
	
	private Map<CustomerDomainEntityId, CustomerNotificationDTO> lastFullNotifications =  new HashMap<>();
	
	private HotelDomainEntityId virtualHotelId = null;
	
	private HotelDomainEntityId demoHotelId = null;
	
	private HashMap<CustomerDomainEntityId, HotelDomainEntityId> customersToHotelIdMap =  new HashMap<>();
	
	/**
	 * anonymGuests id to GPS point
	 */
	private HashMap<CustomerDomainEntityId, Double> guestToGpsPointMap = new HashMap<>();
	private ModelMapper modelMapper;
	private ChatService chatService;


	@PostConstruct
	public void init() {
		unreadChatsForReceiverFromSendersMap = new HashMap<>();
		lastMessageBetweenCustomersMap = new HashMap<>();
	}
	
	@Override
	public HotelDomainEntityId getInitHotelId()
	{
		if(!AppConfigProperties.ALLOW_INIT_VIRTUAL_HOTEL)
		{
			return null;	
		}
		
		return virtualHotelId!=null ? virtualHotelId: getRepositoryVirtualHotelId();
	}
	
	@Override
	public HotelDomainEntityId getDemoHotelId()
	{
		return demoHotelId!=null? demoHotelId: getRepositoryDemoHotelId();
	}

	private HotelDomainEntityId getRepositoryVirtualHotelId()
	{
		HotelDomainEntityId virtualHotelId = hotelRepository.getVirtualHotelId();

		return  virtualHotelId==null? null : virtualHotelId;
	}
	
	private HotelDomainEntityId getRepositoryDemoHotelId()
	{
		HotelDomainEntityId demoHotelId = hotelRepository.getDemoHotelDomainId();

		return  demoHotelId==null? null : demoHotelId;
	}
	
	@Override
	public Optional<LocalDate> getLastCustomerOnlineTime(CustomerDomainEntityId customerId) throws Throwable {
		Optional<LocalDate> lastCustomerOnlineTime = lastCustomerOnlineMap.entrySet().stream().filter(e -> e.getKey() == customerId).map(Entry::getValue).findFirst();
				
 		if(!lastCustomerOnlineTime.isPresent())
		{
			CustomerDTO customerEntityOpt = (CustomerDTO) customerService.getByDomainId(customerId).orElseThrow(() -> new RuntimeException("not found"));
			
			if(!customerEntityOpt.isLogged())
			{
				return Optional.empty();
			}
			
			LocalDate lastSeenOnline = customerEntityOpt.getLastSeenOnline();
			
			if(lastSeenOnline != null)
			{
				lastCustomerOnlineMap.put(customerId, lastSeenOnline);
				return Optional.of(lastSeenOnline);
			}
		}
		
		return lastCustomerOnlineTime;
	}
	
	@Override
	public void updateCustomerConsistencyId(CustomerDomainEntityId customerId)
	{
		currentConsistencyIdsMap.put(customerId, System.currentTimeMillis());
	}
	
//	@Override
	private void updateLastOnlineTime(CustomerDomainEntityId customerId) throws Throwable {
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
			
			lastCustomerOnlineMap.put(customerId, LocalDate.now());
		}
		
		if(updateCustomerRootEntity)
		{
			CustomerDTO customerEntityOpt = (CustomerDTO) customerService.getByDomainId(customerId).orElseThrow(() -> new RuntimeException("not found"));
			
			customerEntityOpt.updateLastSeenOnline();
			lastCustomerOnlineMap.put(customerId, LocalDate.now());
		}
	}
	
	@Override
	public List<CustomerDomainEntityId> getOnlineCustomers()
	{
		List<CustomerDomainEntityId> resultList = new ArrayList<>();
		
		Set<CustomerDomainEntityId> customerIds = lastCustomerOnlineMap.keySet();
		
		if(customerIds.isEmpty())
		{
			List<CustomerDBEntity> onlinein24HCustomerEntities =  customerService.getAllIn24hOnline();
			
			for (CustomerDBEntity nextOnlineIn24HCustomerRootEntity : onlinein24HCustomerEntities)
			{
				lastCustomerOnlineMap.put(nextOnlineIn24HCustomerRootEntity.getDomainEntityId(), nextOnlineIn24HCustomerRootEntity.getLastSeenOnline());
				
				if(isNowStillOnline(nextOnlineIn24HCustomerRootEntity.getDomainEntityId()))
				{
					resultList.add(nextOnlineIn24HCustomerRootEntity.getDomainEntityId());
				}
			}
		}
		else {
			
			List<CustomerDomainEntityId> nowOnlineCustomerIdx = new ArrayList<>();
			
			for (CustomerDomainEntityId nextCustomerId: customerIds)
			{
				if(isNowStillOnline(nextCustomerId))
				{
					nowOnlineCustomerIdx.add(nextCustomerId);
				}
			}
			
//			if(!nowOnlineCustomerIdx.isEmpty())
//			{
//				resultList.addAll(customerRepository.findByDomainIdIn(nowOnlineCustomerIdx));
//			}
		}
		
		return resultList;
	}
	
	@Override
	public void checkCustomerOffline(CustomerDomainEntityId offlineId)	
	{
		if(lastCustomerOnlineMap.containsKey(offlineId))
		{
			lastCustomerOnlineMap.remove(offlineId);
		}
	}
	
	@Override
	public void checkCustomerOnline(CustomerDomainEntityId onlineId) throws Throwable {
		updateLastOnlineTime(onlineId);
	}
	
	@Override
	public long getCustomerConsistencyId(CustomerDomainEntityId customerId)
	{
		if(currentConsistencyIdsMap.containsKey(customerId))
		{
			return currentConsistencyIdsMap.get(customerId);
		}

		Optional<CustomerDBEntity> customerEntity = customerRepository.findByDomainId(customerId);
		
		long newConsistencyId = customerEntity.isPresent()? customerEntity.get().getConsistencyId(): -1;

		if(customerEntity !=null)
		{
			currentConsistencyIdsMap.put(customerId, newConsistencyId);
		}
		
		return newConsistencyId;
		
	}
	
	@Override
	public void markChatRead(CustomerDomainEntityId receiverId, CustomerDomainEntityId senderId)
	{
		Object unreadForReceiver = unreadChatsForReceiverFromSendersMap.entrySet().stream().filter(e -> e.getKey() == receiverId).map(Entry::getValue).findFirst();
		
		//empty all unread messages for this sender!
		//remove empty Lists from Map!!! sonst unread chat!!!

		if(unreadForReceiver!=null)
		{
//			unreadMsgQueue.stream().filter(chatMessage -> chatMessage.receiverDomainId() == receiverDomainId).forEach(chatMessage -> chatMessage.re);
			unreadChatsForReceiverFromSendersMap.get(receiverId).remove(senderId);
		}
	}
	
	@Override
	public void markMessageRead(ChatMsgDTO readMessage)
	{
		Map<CustomerDomainEntityId, List<ChatMsgDTO>> unreadForeReceiver = unreadChatsForReceiverFromSendersMap.get(readMessage.receiverDomainId());

		if(unreadForeReceiver==null)
		{
			unreadForeReceiver = new HashMap<>();

			//TODO Eugen: who is receiver hier????
			List<ChatMsgDTO> unreadMessagesByReceiver = chatService.getUnreadChatMessagesForCustomer(readMessage.receiverDomainId());
			
			for (ChatMsgDTO nextUnreadMessageByReceiver: unreadMessagesByReceiver)
			{
				List<ChatMsgDTO> unreadMessagesForSenderByReceiver = unreadForeReceiver.get(readMessage.senderDomainId());
				
				if(unreadMessagesForSenderByReceiver==null)
				{
					unreadMessagesForSenderByReceiver = new ArrayList<>();
				}

				unreadMessagesForSenderByReceiver.add(nextUnreadMessageByReceiver);

				unreadForeReceiver.put(readMessage.senderDomainId(), unreadMessagesForSenderByReceiver);
			}
			
			unreadChatsForReceiverFromSendersMap.put(readMessage.receiverDomainId(), unreadForeReceiver);
		}
		
		List<ChatMsgDTO> unreadSenderReceiver = unreadForeReceiver.get(readMessage.senderDomainId());
		
		if(unreadSenderReceiver!=null && unreadSenderReceiver.contains(readMessage))
		{
			unreadSenderReceiver.remove(readMessage);
			
			if(unreadSenderReceiver.isEmpty())
			{
				//remove empty Lists from Map!!! sonst unread chat!!!
				unreadForeReceiver.remove(readMessage.senderDomainId());
			}
			else{
				unreadForeReceiver.put(readMessage.senderDomainId(), unreadSenderReceiver);
			}
			
			unreadChatsForReceiverFromSendersMap.put(readMessage.receiverDomainId(), unreadForeReceiver);
		}
		
	}
	
	@Override
	public void updateUnreadMessagesToCustomer(ChatMsgDTO newUnreadMessage)
	{
		//If there is no unreadMessages for this receiver, create it from DB
		CustomerDomainEntityId receiverId = newUnreadMessage.receiverDomainId();
		if(!unreadChatsForReceiverFromSendersMap.containsKey(receiverId))
		{
			Map<CustomerDomainEntityId, List<ChatMsgDTO>> unreadMessagesForReceiver = getUnreadChatsBySender(receiverId);
			
			unreadChatsForReceiverFromSendersMap.put(receiverId, unreadMessagesForReceiver);
		}

		CustomerDomainEntityId newSenderId = newUnreadMessage.senderDomainId();
		
		Map<CustomerDomainEntityId, List<ChatMsgDTO>> unreadByReceiver = unreadChatsForReceiverFromSendersMap.get(receiverId);

		List<ChatMsgDTO> unreadForSender = new ArrayList<>();

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
	public Map<CustomerDomainEntityId, List<ChatMsgDTO>> getCustomerUnreadChatsBySenders(CustomerDomainEntityId receiverDomainId)
	{
		//set from DB is not exists
		if(!unreadChatsForReceiverFromSendersMap.containsKey(receiverDomainId))
		{
			Map<CustomerDomainEntityId, List<ChatMsgDTO>> unreadMessagesForReceiver = getUnreadChatsBySender(receiverDomainId);
			
			unreadChatsForReceiverFromSendersMap.put(receiverDomainId, unreadMessagesForReceiver);
		}
		
		return unreadChatsForReceiverFromSendersMap.get(receiverDomainId);
	}
	
	
	private Map<CustomerDomainEntityId, List<ChatMsgDTO>> getUnreadChatsBySender(CustomerDomainEntityId receiverId)
	{
		List<ChatMsgDTO> allUnreadChatsForReceiver = chatService.getUnreadChatMessagesForCustomer(receiverId);
		
		Map<CustomerDomainEntityId, List<ChatMsgDTO>> unreadMessagesForReceiver = new HashMap<>();
		
		if(allUnreadChatsForReceiver!=null)
		{
			for(ChatMsgDTO nextUnreadChatmessage: allUnreadChatsForReceiver)
			{
				CustomerDomainEntityId nextSenderId = nextUnreadChatmessage.senderDomainId();

				List<ChatMsgDTO> unreadFromSender = unreadMessagesForReceiver.containsKey(nextSenderId)? unreadMessagesForReceiver.get(nextSenderId) : new ArrayList<ChatMsgDTO>();

				unreadFromSender.add(nextUnreadChatmessage);

				List<ChatMsgDTO> unreadFromSenderDtos = modelMapper.map(unreadFromSender, new TypeToken<List<ChatMsgDTO>>() {}.getType());

				unreadMessagesForReceiver.put(nextSenderId, unreadFromSenderDtos);
			}
		}
		return unreadMessagesForReceiver;
	}
	
	@Override
	public List<CustomerDomainEntityId> getOnlineCustomerIds()
	{
		List<CustomerDomainEntityId> resultList = new ArrayList<>();
		
		Set<CustomerDomainEntityId> customerIds = lastCustomerOnlineMap.keySet();
		
		if(customerIds.isEmpty())
		{
			List<CustomerDBEntity> onlinein24HCustomerEntities =  customerService.getAllIn24hOnline();
			
			for (CustomerDBEntity nextOnlineIn24HCustomerRootEntity : onlinein24HCustomerEntities)
			{
				lastCustomerOnlineMap.put(nextOnlineIn24HCustomerRootEntity.getDomainEntityId(), nextOnlineIn24HCustomerRootEntity.getLastSeenOnline());
				
				if(isNowStillOnline(nextOnlineIn24HCustomerRootEntity.getDomainEntityId()))
				{
					resultList.add(nextOnlineIn24HCustomerRootEntity.getDomainEntityId());
				}
			}
		}
		else {
			
			for (CustomerDomainEntityId nextCustomerId: customerIds)
			{
				if(isNowStillOnline(nextCustomerId))
				{
					resultList.add(nextCustomerId);
				}
			}
		}
		
		return resultList;
	}
	
	private boolean isNowStillOnline(CustomerDomainEntityId customerId)
	{
        Optional<LocalDate> lastOnline = null;
        try {
            lastOnline = getLastCustomerOnlineTime(customerId);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return lastOnline.isPresent() && lastOnline.get().isAfter(convertToDate(LocalDate.now().minus(ONLINE_DELAY_MINUTES)));
	}
	
	@Override
	public void setLastMessageBetweenCustomers(ChatMsgDTO lastMessage)
	{
		CustomerDomainEntityId senderId = lastMessage.senderDomainId();
		CustomerDomainEntityId receiverId = lastMessage.receiverDomainId();

		CustomerDomainEntityId fromMin = senderId;// < receiverId? senderId : receiverId;
		CustomerDomainEntityId toMax = senderId;// > receiverId? senderId : receiverId;
		
		Map<CustomerDomainEntityId, ChatMsgDTO> newLastMsgMap = lastMessageBetweenCustomersMap.get(fromMin);
				
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
	public CustomerNotificationDTO getLastPushNotifiation(CustomerDomainEntityId customerId)
	{
		if(customerId == null)
		{
			throw new IllegalArgumentException("customerId should be > 0, but value is " + customerId);
		}
		
		return lastPushNotifications.get(customerId);
	}

	@Override
	public void setLastPushNotifiation(CustomerDomainEntityId customerId, CustomerNotificationDTO lastNotification)
	{
		lastPushNotifications.put(customerId, lastNotification);
	}
	
	@Override
	public void setLastFullNotification(CustomerDomainEntityId receiverId, CustomerNotificationDTO nextNotification)
	{
		lastFullNotifications.put(receiverId, nextNotification);
	}
	
	@Override
	public CustomerNotificationDTO getLastFullNotification(CustomerDomainEntityId receiverId)
	{
		return lastFullNotifications.get(receiverId);
	}
	
	@Override
	public boolean isNotificationDelayReady(CustomerDomainEntityId sessionCustomerId)
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
	public HotelDomainEntityId getCustomerHotelId(CustomerDomainEntityId customerId)
	{
		if(!customersToHotelIdMap.containsKey(customerId))
		{
			customersToHotelIdMap.put(customerId, checkinRepository.getCustomerHotelDomainId(customerId, LocalDate.now()));
		}

		HotelDomainEntityId hotelId = customersToHotelIdMap.get(customerId);
		return hotelId!=null? hotelId : getInitHotelId();
	}

	@Override
	public void updateCustomerHotelId(CustomerDomainEntityId customerId, HotelDomainEntityId hotelId)
	{
		customersToHotelIdMap.put(customerId, hotelId);
	}
	
	@Override
	public void addGuestGpsPosition(CustomerDomainEntityId requesterId, Double latLonPoint)
	{
		guestToGpsPointMap.put(requesterId, latLonPoint);
	}
	
	@Override
	public Double getGuestGpsPosition(CustomerDomainEntityId requesterId)
	{
		return guestToGpsPointMap.get(requesterId);
	}
	
	@Override
	public void markLastMessageBetweenCustomers(ChatMsgDTO seenMessage)
	{
		CustomerDomainEntityId senderId = seenMessage.senderDomainId();
		CustomerDomainEntityId receiverId = seenMessage.receiverDomainId();

		CustomerDomainEntityId fromMin = senderId;//senderId < receiverId? senderId : receiverId;
		CustomerDomainEntityId toMax = receiverId;//senderId > receiverId? senderId : receiverId;

		Map<CustomerDomainEntityId, ChatMsgDTO> newLastMsgMap = lastMessageBetweenCustomersMap.get(fromMin);

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
	public ChatMsgDTO getLastMessageBetweenCustomers(CustomerDomainEntityId senderId, CustomerDomainEntityId receiverId){

		CustomerDomainEntityId fromMin = senderId;// < receiverId? senderId : receiverId;
		CustomerDomainEntityId toMax = senderId;// > receiverId? senderId : receiverId;

		Map<CustomerDomainEntityId, ChatMsgDTO> lastMsgMap = lastMessageBetweenCustomersMap.get(fromMin);
		
		if(lastMsgMap==null || !lastMsgMap.containsKey(toMax))
		{
			// ChatMessage dbLastMessage = chatRepository.getLastMessageByCustomerAndReceiverIds(fromMin, toMax);
			
			//TODO asynchron request chat microService
//			RabbitConverterFuture<ChatMsgDTO> chatMsgOptFuture = hotelRabbitMQProducer.sendAsynchDirectExchangeMethodCall(new ChatMessageCommand(senderDomainId, receiverDomainId, true, ""));
//			
//			chatMsgOptFuture.addCallback(new ListenableFutureCallback<>() {
//				@Override
//				public void onFailure(Throwable ex) {
//					// ...
//				}
//				
//				@Override
//				public void onSuccess(ChatMsgDTO chatMsgDTO) {
//					log.info("chatMsgDTO received {}", chatMsgDTO);
//					updateAsynchLastMessageFromCustomer(chatMsgDTO, fromMin, toMax);
//				}
//			});
		}
		
		return lastMsgMap!=null? lastMsgMap.get(toMax) : null;
	}
	
	private void updateAsynchLastMessageFromCustomer(ChatMsgDTO chatMsgDTO, CustomerDomainEntityId fromMin, CustomerDomainEntityId toMax)
	{
		if(chatMsgDTO != null)
		{
			Map<CustomerDomainEntityId, ChatMsgDTO> localLastMsgMap = lastMessageBetweenCustomersMap.get(fromMin);
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
