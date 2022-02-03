package eu.getsoftware.hotelico.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eu.getsoftware.hotelico.dto.MenuOrderDTO;

import eu.getsoftware.hotelico.service.MailService;
import eu.getsoftware.hotelico.utils.DealStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import eu.getsoftware.hotelico.dto.ChatMessageDTO;
import eu.getsoftware.hotelico.dto.CustomerDTO;
import eu.getsoftware.hotelico.dto.CustomerNotificationDto;
import eu.getsoftware.hotelico.dto.HotelActivityDto;
import eu.getsoftware.hotelico.dto.WallPostDto;
import eu.getsoftware.hotelico.model.ChatMessage;
import eu.getsoftware.hotelico.model.Customer;
import eu.getsoftware.hotelico.model.HotelActivity;
import eu.getsoftware.hotelico.repository.ChatRepository;
import eu.getsoftware.hotelico.repository.CheckinRepository;
import eu.getsoftware.hotelico.repository.CustomerRepository;
import eu.getsoftware.hotelico.service.CacheService;
import eu.getsoftware.hotelico.service.ChatService;
import eu.getsoftware.hotelico.service.CustomerService;
import eu.getsoftware.hotelico.service.HotelService;
import eu.getsoftware.hotelico.service.MenuService;
import eu.getsoftware.hotelico.service.NotificationService;
import eu.getsoftware.hotelico.utils.ControllerUtils;
import eu.getsoftware.hotelico.utils.HotelEvent;

/**
 * <br/>
 * Created by e.fanshil
 * At 05.02.2016 12:10
 */
@Service
public class NotificationServiceImpl implements NotificationService
{
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private MenuService menuService;	
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private CustomerService customerService;	
	
	@Autowired
	private ChatService chatService;	
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private CustomerRepository customerRepository;	
	
	@Autowired
	private CheckinRepository checkinRepository;	
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void notificateAboutEntityEvent(CustomerDTO dto, HotelEvent event, String eventContent, long entityId)
	{
		if(dto==null)
		{
			return;
		}
		
		List<Long> allOnlineCustomerIds = cacheService.getOnlineCustomerIds();
		
		if(HotelEvent.EVENT_LOGO_CUSTOMER_CHANGE_MESSAGE.equals(event) && !allOnlineCustomerIds.contains(dto.getId()))
		{
			allOnlineCustomerIds.add(dto.getId());
		}
		
		for(Long nextOnlineCustomerId: allOnlineCustomerIds)
		{
			CustomerNotificationDto receiverNotification = this.getCustomerNotification(nextOnlineCustomerId, event);
			
			if(dto.getHotelId()!=null)
			{
				receiverNotification.setCustomerEvent(dto.getId(), dto.getHotelId(), event, eventContent, entityId);
				
				//                if(event.getPushUrl()!=null)
				//                {
				//                    receiverNotification.setPushCustomerEvent(event.getPushTitle(), eventContent, event.getPushUrl(), event.getPushIcon());
				//                    cacheService.setLastPushNotifiation(nextOnlineCustomerId, receiverNotification);
				//                    sendPushRequest(nextOnlineCustomerId);
				//                }
			}
			
			simpMessagingTemplate.convertAndSend(ControllerUtils.SOCKET_NOTIFICATION_TOPIC + nextOnlineCustomerId, receiverNotification);
		}
	}
	
	@Override
	public CustomerNotificationDto getCustomerNotification(long receiverId, HotelEvent event)
	{
		CustomerNotificationDto nextNotification = new CustomerNotificationDto();
		
		nextNotification.setCreationTime(new Date().getTime());
		
		//######################### 
		
		long receiverHotelId = customerService.getCustomerHotelId(receiverId);
		
		nextNotification.setId(new Date().getTime());
		
		nextNotification.setReceiverId((long)receiverId);
		
		///#########################################################################
		
		///###########   CHECK ALL ONLINE //////////////
		
		List<Long> allOnlineCustomersIds = cacheService.getOnlineCustomerIds();
		
		Set<Long> onlineGuests = new HashSet<>();
		
		for (Long nextOnlineCustomerId: allOnlineCustomersIds)
		{
			if(nextOnlineCustomerId!=receiverId)
			{
				onlineGuests.add(nextOnlineCustomerId);
			}
		}
		/////////// 1 ONLINE - SET RESULTS TO MAP
		
		nextNotification.setHotelOnlineGuestIds(onlineGuests.toArray(new Integer[onlineGuests.size()]));
		
		if(HotelEvent.EVENT_ONLINE_CUSTOMERS.equals(event))
		{
			//Eugen: short notification only about online users! only if it has changes!!!
			
			CustomerNotificationDto previousNotification = cacheService.getLastFullNotification(receiverId);
			
			boolean onlineListIsEqual = previousNotification!=null && Arrays.equals(previousNotification.getHotelOnlineGuestIds(), nextNotification.getHotelOnlineGuestIds());
			
			if(onlineListIsEqual)
			{
				return null;
			}
			else
			{
				return nextNotification;
			}
		}
		
		///########### 2. CHECKIN HOTEL INFORMATION ################################################
		
		if(receiverHotelId>0)
		{
			//################## REAL HOTEL NOTIFICATIONS #####################
			
			//######## ACTIVITIES 
			
			if (ControllerUtils.USE_UNREAD_ACTIVITY_COUNT)
			{
				//                int hotelActivitiesTotalNumber = hotelActivityRepository.getTimeValidCounterByHotelId(receiverHotelId, new Date());
				
				//            Integer seenActivitiesNumber = customerRepository.getSeenActivitiesNumber(receiverId);
				//TODO EUGEN: calculate hotelUnreadActivitiesNumber without Hotel Object with query
				//                int hotelUnreadActivitiesNumber = 0;//hotelActivitiesTotalNumber - (seenActivitiesNumber!=null? seenActivitiesNumber: 0);
				//
				//                if (hotelUnreadActivitiesNumber > 0)
				//                    nextNotification.setHotelUnreadActivitiesNumber(hotelUnreadActivitiesNumber);
			}
			
			////// HOTEL GUESTS
			Integer hotelGuestsCount = checkinRepository.getActiveCountByHotelId(receiverHotelId, new Date());
			
			nextNotification.setHotelGuestsNumber(hotelGuestsCount);
			
			//########## LAST SEEN CUSTOMERS
			
			if (ControllerUtils.USE_LAST_SEEN_CUSTOMERS)
			{
				//TODO Eugen
				Map<Long, String> lastSeenOnlineMap = new HashMap<>();
				nextNotification.setLastSeenOnlineHotelGuests(lastSeenOnlineMap);
				
			}
			
			boolean isStaffOrAdmin = customerService.isStaffOrAdminId(receiverId);
			
			//TODO Eugen: Menu orders of customer: Problem one time ausführung
			if(isStaffOrAdmin || HotelEvent.EVENT_MENU_NEW_UPDATE.equals(event))
			{
				List<MenuOrderDTO> menusOfCustomer = menuService.getActiveMenusByCustomerId(receiverId, receiverHotelId, 0, -1, false);
				
				int acceptedMenuCount = 0;
				for (MenuOrderDTO nextMenu: menusOfCustomer)
				{
					DealStatus clientOrderStatus = DealStatus.parseByName(nextMenu.getOrderStatus());
					
					if(DealStatus.ACCEPTED.equals(clientOrderStatus))
					{
						acceptedMenuCount++;
					}
				}
				
				nextNotification.setMyMenuOrdersNumber(acceptedMenuCount);
				
				List<MenuOrderDTO> waitingMenusToRoom = menuService.getAllHotelMenusToRoom(receiverId, receiverHotelId, 0);
				nextNotification.setMenuOrderInRoomNumber(waitingMenusToRoom.size());
			}
			
		}
		
		// ########## DEALS
		
		int myDealsNumber = hotelService.getCustomerDealCounter(receiverId, 0);
				
		nextNotification.setMyDealsNumber(myDealsNumber);
		
		
		//########### GLOBAL CHAT
		
		if(true)
		{
			//######################## 3. ONLY UNREAD CHAT INFO
			
			Map<Long, List<ChatMessage>> unreadChatsBySenders = cacheService.getCustomerUnreadChatsBySenders(receiverId);
			
			Map<Long, Integer> unreadChatBySenderCounter = new HashMap<>();
			
			//        List<Integer> hotelStaffIdList = checkinRepository.getStaffIdsByHotelId(receiverHotelId);
			
			for (long nextUnreadSenderId : unreadChatsBySenders.keySet())
			{
				//TODO Eugen: soll ich Staff von anderen Hotels ausschliessen???? ja
				if (nextUnreadSenderId != receiverId /*&& !hotelStaffIdList.contains(nextUnreadSenderId)*/)
				{
					List<ChatMessage> unreadChatMessages = unreadChatsBySenders.get(nextUnreadSenderId);
					
					if (!unreadChatMessages.isEmpty() && ControllerUtils.CHAT_NOTIFICATE_DELIEVERY_INDIVIDUAL_CHAT)
					{
						ChatMessage lastUnreadMessage = unreadChatMessages.get(unreadChatMessages.size() - 1);
						
						if (!lastUnreadMessage.isDelieveredToReceiver() && !lastUnreadMessage.isSeenByReceiver() && lastUnreadMessage.getReceiver().getId() == receiverId && allOnlineCustomersIds.contains(receiverId))
						{
							if (!lastUnreadMessage.isDelieveredToReceiver()) //Eugen: ONLY 1 Time, then it will be marked as delivered!!!
							{
								lastUnreadMessage.setDelieveredToReceiver(true);
								chatRepository.saveAndFlush(lastUnreadMessage);
								
								//Eugen: notificate Sender, that his message was delievered
								ChatMessageDTO chatMessageDto = chatService.convertMessageToDto(lastUnreadMessage);
								simpMessagingTemplate.convertAndSend(ControllerUtils.SOCKET_CHAT_TOPIC + lastUnreadMessage.getSender().getId() + "", chatMessageDto);
							}
							
						}
					}
					
					//                if(!unreadChatMessages.isEmpty() && (!unreadChatMessages.get(0).getSender().isHotelStaff() || hotelStaffIdList.contains(unreadChatMessages.get(0).getSender().getId())) && (!unreadChatMessages.get(0).getReceiver().isHotelStaff() || hotelStaffIdList.contains(unreadChatMessages.get(0).getReceiver().getId())))
					{
						unreadChatBySenderCounter.put(nextUnreadSenderId, unreadChatMessages.size());
					}
				}
			}
			
			nextNotification.setUnreadChatProSenderCount(unreadChatBySenderCounter);
			nextNotification.setUnreadChatsCounter(unreadChatBySenderCounter.keySet().size() + "");
			
			/////////// 4. CHATLIST - ALL LAST CHAT MESSAGES
			
			Map<Long, String> lastMessagesForCustomer = new HashMap<>();
			Map<Long, String> lastMessageTimesForCustomer = new HashMap<>();
			Map<Long, Boolean> lastMessageSeenByCustomer = new HashMap<>();
			Map<Long, Boolean> lastMessageDelieveredToCustomer = new HashMap<>();
			
			Set<Customer> allChatPartners = new HashSet<>();
			
			//TODO Eugen: save last message in cache
			allChatPartners.addAll(chatRepository.getChatSendersByCustomerId(receiverId));
			allChatPartners.addAll(chatRepository.getChatReceiversByCustomerId(receiverId));
			
			//        allChatPartners.removeAll(hotelCustomers);
			//        Set<Customer> notHotelChatPartners = allChatPartners;
			
			//////////////
			
			for (Customer nextChatCustomer : allChatPartners)
			{
				//            if (ControllerUtils.isCustomerOnline(nextNotHotelCustomer))
				//            {
				//                onlineHotelGuests.add(nextNotHotelCustomer.getId());
				//            }
				//            else
				//            {
				//                lastSeenOnlineMap.put(nextNotHotelCustomer.getId(), ControllerUtils.getTimeFormatted(nextNotHotelCustomer.getLastSeenOnline()));
				//            }
				
				//#######################
				
				//TODO eugen: iterate over all chatPartners auto in query
				//TODO Eugen: cashingService
				//            ChatMessage nextLastMessage = chatRepository.getLastMessageByCustomerAndReceiverIds(nextChatCustomer.getId(), receiverId);
				ChatMessage nextLastMessage = cacheService.getLastMessageBetweenCustomers(nextChatCustomer.getId(), receiverId);
				//Integer chatPartnerId = nextLastMessage.getSender().getId()==sessionCustomer.getId()? nextLastMessage.getReceiver().getId(): nextLastMessage.getSender().getId();
				if (nextLastMessage != null)
				{
					Timestamp timeStamp = nextLastMessage.getTimestamp();
					
					String time = ControllerUtils.getTimeFormatted(timeStamp);
					
					String message = nextLastMessage.getMessage();
					//                message = message.length() > ControllerUtils.chatListMessageLength ? message.substring(0, ControllerUtils.chatListMessageLength-2) + "..." : message;
					
					lastMessagesForCustomer.put(nextChatCustomer.getId(), message);// + " (" + time + ")");
					lastMessageTimesForCustomer.put(nextChatCustomer.getId(), time);
					
					//only if it notifications to message sender
					if (nextLastMessage.getSender().getId() == receiverId && ControllerUtils.CHAT_NOTIFICATE_DELIEVERY_CHATLIST)
					{
						lastMessageSeenByCustomer.put(nextChatCustomer.getId(), nextLastMessage.getReceiver().equals(nextChatCustomer) && nextLastMessage.isSeenByReceiver());
						lastMessageDelieveredToCustomer.put(nextChatCustomer.getId(), nextLastMessage.getReceiver().equals(nextChatCustomer) && nextLastMessage.isDelieveredToReceiver());
					}
				}
				//#######################
			}
			
			/////////////
			nextNotification.setLastMessagesToMe(lastMessagesForCustomer);
			nextNotification.setLastMessageTimesToMe(lastMessageTimesForCustomer);
			
			nextNotification.setLastMessageSeenByCustomer(lastMessageSeenByCustomer);
			nextNotification.setLastMessageDelieveredToCustomer(lastMessageDelieveredToCustomer);
			////////////////////////////////
		}
		
		cacheService.setLastFullNotification(receiverId, nextNotification);
		
		return nextNotification;
	}
	
	@Override
	public void createAndSendNotification(long receiverId, HotelEvent event){
		
		CustomerNotificationDto receiverNotification = this.getCustomerNotification(receiverId, event);
		
		if(receiverNotification==null)
		{
			//No changes!
			return;
		}
		
		simpMessagingTemplate.convertAndSend(ControllerUtils.SOCKET_NOTIFICATION_TOPIC + receiverId + "", receiverNotification);
		
		if(event.getPushUrl()!=null)
		{
			receiverNotification.setPushCustomerEvent("Hotelico.de", event.getPushTitle(), event.getPushUrl(), event.getPushIcon(), "");
			cacheService.setLastPushNotifiation(receiverId, receiverNotification);
			sendPushRequest(receiverId);
		}
		
	}
	
	public void sendFeedMessage(Customer customer, Map<String, String> systemMessages)
	{
		String feedMessage = systemMessages.get("hotelFeedMessage");
		
		String customerIds = systemMessages.get("hotelFeedCustomerIds");
		
		String inviteActivityId = systemMessages.get("inviteActivityId");
		String sendToAllNotLoggedInHotel = systemMessages.get("sendToAllNotLoggedInHotel");
		
		String fromName = systemMessages.get("fromName");
//		String fromEmail = systemMessages.get("fromMail");
		
		if(ControllerUtils.isEmptyString(fromName))
		{
			fromName = customer.getFirstName();
		}
		
		// PART 1: send to logged users!
		
		if(customerIds!=null)
		{
			String[] feedCustomersArray = customerIds.split(",");
			
			for(String nextCustomerId: feedCustomersArray)
			{
				long nextId = Integer.parseInt(nextCustomerId.trim());
				
				Customer nextFeedCustomer = customerRepository.getOne(nextId);
				
				if(nextFeedCustomer!=null && nextFeedCustomer.isAllowHotelNotification())
				{
					ChatMessageDTO feedChatMessage = new ChatMessageDTO();
					
					feedChatMessage.setSenderId(customer.getId());
					feedChatMessage.setReceiverId(nextFeedCustomer.getId());
					feedChatMessage.setMessage(feedMessage);
					feedChatMessage.setInitId(new Date().getTime());
					feedChatMessage.setCreationTime(new Date().getTime());
					feedChatMessage.setTimestamp( new Timestamp(new Date().getTime()));
					
					if(!ControllerUtils.isEmptyString(inviteActivityId))
					{
						feedChatMessage.getSpecialContent().put("activityId", inviteActivityId);
					}
					
					chatService.addChatMessage(feedChatMessage);
				}
				
			}
		}
		
		// PART 2: send to anonyme (not logged) users
		
		if(!ControllerUtils.isEmptyString(sendToAllNotLoggedInHotel))
		{
			Boolean sendToUnLogged = Boolean.parseBoolean(sendToAllNotLoggedInHotel);
			
			if(sendToUnLogged)
			{
				int activId = Integer.parseInt(inviteActivityId);
				HotelActivity hotelActivity = hotelService.getActivityByIdOrInitId(activId, activId);
				
				//SEND GUEST PUSH!!!
				this.sendPushToAllNotLoggedInHotel(hotelActivity);
			}
		}
	}
	
	public void sendMailList(Customer customer, Map<String, String> systemMessages)
	{
		logger.info("mailList inhalt: " + systemMessages);
		
		String mailList = systemMessages.get("mailList");
		
		String mailContent = "";//customerDto.getSystemMessages().get("mailContent");
		
		String fromName = systemMessages.get("fromName");
		
		String fromEmail = systemMessages.get("fromMail");
		
		String customSubject = systemMessages.get("customSubject");
		
		if(ControllerUtils.isEmptyString(fromName))
		{
			fromName = customer.getFirstName();
		}
		
		if(ControllerUtils.isEmptyString(fromEmail))
		{
			fromEmail = customer.getEmail();
		}
		
		if(ControllerUtils.isEmptyString(customSubject))
		{
			customSubject = "Willkomen in unserem Hotel";
		}
		
		if(ControllerUtils.isEmptyString(mailContent))
		{
			mailContent = mailService.getWellcomeMailBody(customer);
		}
		else
		{
			try
			{
				//Eugen: decodes encodeUriComponent!!!
				mailContent = URLDecoder.decode(mailContent, "UTF-8");
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
		
		String[] customerMails = mailList.split(",");
		
		for (int i = 0; i < customerMails.length; i++)
		{
			String nextDestinationMail = customerMails[i];
			
			//TODO Eugen: check if email is valid!!!
			
			mailService.sendMail(nextDestinationMail, customSubject, mailContent, null);
		}
	}
	
	@Override
	public void createAndSendPushNotification_Chat(long receiverId, HotelEvent event, long senderId, String message)
	{
		CustomerNotificationDto receiverNotification = this.getCustomerNotification(receiverId, event);
		
		simpMessagingTemplate.convertAndSend(ControllerUtils.SOCKET_NOTIFICATION_TOPIC + receiverId + "", receiverNotification);
		
		if(event.getPushUrl()!=null)
		{
			Customer sender = customerRepository.getOne(senderId);
			
			if(sender!=null)
			{
				String pushUrlPostfix = String.valueOf(senderId);
				
				receiverNotification.setPushCustomerEvent(event.getPushTitle() + " from " + sender.getFirstName(), message, event.getPushUrl()+pushUrlPostfix, event.getPushIcon(), senderId+"");
				cacheService.setLastPushNotifiation(receiverId, receiverNotification);
				sendPushRequest(receiverId);
			}
			
		}
	}
	
	@Override
	public void createAndSendPushNotification_Activity(long receiverId, HotelEvent event, HotelActivity activity, String message)
	{
		CustomerNotificationDto receiverNotification = this.getCustomerNotification(receiverId, event);
		
		simpMessagingTemplate.convertAndSend(ControllerUtils.SOCKET_NOTIFICATION_TOPIC + receiverId + "", receiverNotification);
		
		if(event.getPushUrl()!=null)
		{
			if(activity!=null)
			{
				String pushUrlPostfix = "//" + activity.getHotel().getId() + "/" + activity.getId();

				receiverNotification.setPushCustomerEvent(event.getPushTitle(), message, event.getPushUrl()+pushUrlPostfix, event.getPushIcon(), activity.getSender().getId()+"");
				cacheService.setLastPushNotifiation(receiverId, receiverNotification);
				sendPushRequest(receiverId);
			}		
		}
	}
	
	 

	@Override
	public CustomerNotificationDto getLastNotification(long customerId, boolean pushRequest)
	{
		CustomerNotificationDto dto = cacheService.getLastPushNotifiation(customerId);
		
		if(customerId<=0 || dto==null)
		{
			dto = new CustomerNotificationDto();
			
			String  title ="Hotelico.de";
			String  message ="New Message!";
			String  icon ="angulr/img/build/logo/logo_push.png";
			//lastPushNotification.put("tag", "angulr/img/build/logo/logo_push.png");
			
			//Default. if no notification was found!!!
			String  relocateUrl = "/" + ControllerUtils.HOST_SUFFIX + "#/app/chatList/false//";
			
			dto.setPushCustomerEvent(title, message, relocateUrl, icon, "");
		}
		
		return dto;
	}
	
	@Override
	public void sendPushRequest(long customerId)
	{
		
		if(customerId<=0)
		{
			return;
		}
		
		Customer customer = customerRepository.getOne(customerId);
		
		String pushRegistrationId = customer.getPushRegistrationId();

		sendPush(pushRegistrationId);
	}
	
	@Override
	public void sendPush(String pushRegistrationId)
	{
		if(ControllerUtils.isEmptyString(pushRegistrationId))
		{
			return;
		}

		final String url = "https://gcm-http.googleapis.com/gcm/send";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "key=AIzaSyDwNJoAez4eCheYTUb9zb617DuSNXk3AqQ");

		String requestJson = ("{" +
				" \"to\" : \"" + pushRegistrationId + "\" "
				+ "}");

		HttpEntity request = new HttpEntity(requestJson, headers);

		RestTemplate restTemplate = new RestTemplate();

		//        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
		String result = restTemplate.postForObject(url, request, String.class);

		System.out.println(result);
	}

	@Override
	public void broadcastActivityNotification(HotelActivityDto hotelActivityDto)
	{
		simpMessagingTemplate.convertAndSend(ControllerUtils.SOCKET_ACTIVITY_TOPIC + hotelActivityDto.getHotelId() + "", hotelActivityDto);
	}
	
	@Override
	public void broadcastWallNotification(WallPostDto wallPostDto)
	{
		simpMessagingTemplate.convertAndSend(ControllerUtils.SOCKET_WALL_TOPIC + wallPostDto.getHotelId(), wallPostDto);
	}

	@Override
	public boolean sendPushToAllNotLoggedInHotel(HotelActivity hotelActivity)
	{
		if(hotelActivity==null)
		{
			return false;
		}
		
//		List<CustomerHotelCheckin> allActiveInHotel = checkinRepository.getActiveByHotelId(hotelActivity.getHotel().getId(), new Date());

		Map<Long, String> notLoggedGuestPushIdsByHotel= hotelService.getNotLoggedGuestPushIdsByHotel(hotelActivity.getHotel());

		String notificationMessage = "new last minute deal";

		HotelEvent eventActivityNewLastMinute = HotelEvent.EVENT_ACTIVITY_NEW_LAST_MINUTE;

		Set<String> loggedGuestPushIds = new HashSet<>();
		
		List<Customer> allActiveInHotel = checkinRepository.getActiveCustomersByHotelId(hotelActivity.getHotel().getId(), new Date());
		
		for (Customer nextActiveCustomer: allActiveInHotel)
		{
			loggedGuestPushIds.add(nextActiveCustomer.getPushRegistrationId());
		}
		
		//Eugen: ignore people, who has anonyme unsubscribed! in other hotels too!
		Map<Long, String> unsubscribedGuestsInAllHotels = hotelService.getUnsubscribeGuestPushIds();
		
		Set<Long> notLoggedGuestIds = notLoggedGuestPushIdsByHotel.keySet();
		for (long nextGuestId : notLoggedGuestIds)
		{
			String pushId = notLoggedGuestPushIdsByHotel.get(nextGuestId);
			
			if(unsubscribedGuestsInAllHotels.keySet().contains(nextGuestId) || unsubscribedGuestsInAllHotels.containsValue(pushId))
			{
				continue;
			}
			
			//Eugen: if a user (with given pushId) has checkin in a hotel, so it is not anonyme push Guest!!!
			if(hotelActivity!=null && !loggedGuestPushIds.contains(pushId))
			{
				String pushUrlPostfix = "//" + hotelActivity.getHotel().getId() + "/" + hotelActivity.getId();

				CustomerNotificationDto receiverNotification = new CustomerNotificationDto();

				receiverNotification.setPushCustomerEvent(eventActivityNewLastMinute.getPushTitle(), notificationMessage, eventActivityNewLastMinute.getPushUrl()+pushUrlPostfix, eventActivityNewLastMinute.getPushIcon(), hotelActivity.getSender().getId()+"");
				cacheService.setLastPushNotifiation((int)nextGuestId, receiverNotification);
				this.sendPush(pushId);
			}
		}

		return notLoggedGuestPushIdsByHotel.size()>0;
	}
	
	@Override
	public void sendNotificationToCustomerOrGuest(Customer receiver, long guestCustomerId, HotelEvent event)
	{
		if(receiver!=null)
		{
			this.createAndSendNotification(receiver.getId(), event);
		}
		else{
			CustomerNotificationDto receiverNotification = new CustomerNotificationDto();
			receiverNotification.setCustomerEvent(0, 0, event, "new event", 0);
			receiverNotification.setReceiverId(guestCustomerId);
			simpMessagingTemplate.convertAndSend(ControllerUtils.SOCKET_NOTIFICATION_TOPIC + guestCustomerId + "", receiverNotification);
		}
	}
}
