package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.usecase.notification;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.domain.customer.ICustomerRootEntity;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.menu.dto.MenuOrderDTO;
import eu.getsoftware.hotelico.clients.api.infrastructure.notification.NotificationService;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.chatview.model.ChatMessageView;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.HotelEvent;
import eu.getsoftware.hotelico.hotelapp.application.chat.domain.infrastructure.ChatMSComminicationService;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.IHotelActivity;
import eu.getsoftware.hotelico.hotelapp.application.customer.port.out.iPortService.CustomerPortService;
import eu.getsoftware.hotelico.hotelapp.application.deal.domain.infrastructure.utils.DealStatus;
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.utils.IHotelEvent;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.service.HotelRabbitMQProducer;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.in.NotificationUseCase;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.*;
import eu.getsoftware.hotelico.hotelapp.application.menu.infrastructure.service.MenuMSCommunicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.*;

/**
 * <br/>
 * Created by e.fanshil
 * At 05.02.2016 12:10
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationUseCaseImpl implements NotificationUseCase
{
	private final IHotelService hotelService;
	
	private final MenuMSCommunicationService menuMSCommunicationService;	
	
	private final LastMessagesService lastMessagesService;
	
	private final CustomerPortService customerService;	
	
	private final MailService mailService;
	private final ICheckinService checkinService;
	
	private final ChatMSComminicationService chatMSComminicationService;
	
	private final HotelRabbitMQProducer hotelRabbitMQProducer;
	private final NotificationService notificationService;
	private final INotificationService inotificationService;

	@Override
	public void notificateAboutEntityEvent(CustomerDTO dto, IHotelEvent event, String eventContent, long entityId)
	{
		Objects.requireNonNull(dto);
		
		List<Long> allOnlineCustomerIds = lastMessagesService.getOnlineCustomerIds();
		
		if(HotelEvent.EVENT_LOGO_CUSTOMER_CHANGE_MESSAGE.equals(event) && !allOnlineCustomerIds.contains(dto.getId()))
		{
			allOnlineCustomerIds.add(dto.getId());
		}
		
		for(Long nextOnlineCustomerId: allOnlineCustomerIds)
		{
			CustomerNotificationDTO receiverNotification = this.getCustomerNotification(nextOnlineCustomerId, event);

			inotificationService.notificateAboutEntityEvent(dto, receiverNotification, event);
		}
	}
	
	@Override
	public CustomerNotificationDTO getCustomerNotification(long receiverId, HotelEvent event)
	{
		CustomerNotificationDTO nextNotification = new CustomerNotificationDTO();
		
		nextNotification.setCreationTime(new Date().getTime());
		
		//######################### 
		
		long receiverHotelId = customerService.getCustomerHotelId(receiverId);
		
		nextNotification.setId(new Date().getTime());
		
		nextNotification.setReceiverId((long)receiverId);
		
		///#########################################################################
		
		///###########   CHECK ALL ONLINE //////////////
		
		List<Long> allOnlineCustomersIds = lastMessagesService.getOnlineCustomerIds();
		
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
			
			CustomerNotificationDTO previousNotification = lastMessagesService.getLastFullNotification(receiverId);
			
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
			
			if (AppConfigProperties.USE_UNREAD_ACTIVITY_COUNT)
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
			Integer hotelGuestsCount = checkinService.getActiveCountByHotelId(receiverHotelId, new Date());
			
			nextNotification.setHotelGuestsNumber(hotelGuestsCount);
			
			//########## LAST SEEN CUSTOMERS
			
			if (AppConfigProperties.USE_LAST_SEEN_CUSTOMERS)
			{
				//TODO Eugen
				Map<Long, String> lastSeenOnlineMap = new HashMap<>();
				nextNotification.setLastSeenOnlineHotelGuests(lastSeenOnlineMap);
				
			}
			
			boolean isStaffOrAdmin = customerService.isStaffOrAdminId(receiverId);
			
			//TODO Eugen: Menu orders of customer: Problem one time ausführung
			if(isStaffOrAdmin || HotelEvent.EVENT_MENU_NEW_UPDATE.equals(event))
			{
				List<MenuOrderDTO> menusOfCustomer = menuMSCommunicationService.getActiveMenusByCustomerId(receiverId, receiverHotelId, 0, -1, false);
				
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
				
				List<MenuOrderDTO> waitingMenusToRoom = menuMSCommunicationService.getAllHotelMenusToRoom(receiverId, receiverHotelId, 0);
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
			
			Map<Long, List<ChatMessageView>> unreadChatsBySenders = lastMessagesService.getCustomerUnreadChatsBySenders(receiverId);
			
			Map<Long, Integer> unreadChatBySenderCounter = new HashMap<>();
			
			//        List<Integer> hotelStaffIdList = checkinRepository.getStaffIdsByHotelId(receiverHotelId);
			
			for (long nextUnreadSenderId : unreadChatsBySenders.keySet())
			{
				//TODO Eugen: soll ich Staff von anderen Hotels ausschliessen???? ja
				if (nextUnreadSenderId != receiverId /*&& !hotelStaffIdList.contains(nextUnreadSenderId)*/)
				{
					List<ChatMessageView> unreadChatMessages = unreadChatsBySenders.get(nextUnreadSenderId);
					
					if (!unreadChatMessages.isEmpty() && AppConfigProperties.CHAT_NOTIFICATE_DELIEVERY_INDIVIDUAL_CHAT)
					{
						ChatMessageView lastUnreadMessage = unreadChatMessages.get(unreadChatMessages.size() - 1);
						
						if (!lastUnreadMessage.isDelieveredToReceiver() && !lastUnreadMessage.isSeenByReceiver() && lastUnreadMessage.getReceiver().getId() == receiverId && allOnlineCustomersIds.contains(receiverId))
						{
							if (!lastUnreadMessage.isDelieveredToReceiver()) //Eugen: ONLY 1 Time, then it will be marked as delivered!!!
							{
								lastUnreadMessage.setDelieveredToReceiver(true);
								chatMSComminicationService.addUpdateChatMessage(lastUnreadMessage);
								
								//Eugen: notificate Sender, that his message was delievered
								ChatMsgDTO chatMessageDto = chatMSComminicationService.convertMessageToDto(lastUnreadMessage);
								simpMessagingTemplate.convertAndSend(AppConfigProperties.SOCKET_CHAT_TOPIC + lastUnreadMessage.getSender().getId() + "", chatMessageDto);
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
			
			Set<ICustomerRootEntity> allChatPartners = new HashSet<>();
			
			//TODO Eugen: save last message in cache
			allChatPartners.addAll(chatMSComminicationService.getChatSendersByCustomerId(receiverId));
			allChatPartners.addAll(chatMSComminicationService.getChatReceiversByCustomerId(receiverId));
			
			//        allChatPartners.removeAll(hotelCustomers);
			//        Set<Customer> notHotelChatPartners = allChatPartners;
			
			//////////////
			
			for (ICustomerRootEntity nextChatCustomerRootEntity : allChatPartners)
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
				ChatMsgDTO nextLastMessage = lastMessagesService.getLastMessageBetweenCustomers(nextChatCustomerRootEntity.getId(), receiverId);
				//Integer chatPartnerId = nextLastMessage.getSender().getId()==sessionCustomer.getId()? nextLastMessage.getReceiver().getId(): nextLastMessage.getSender().getId();
				if (nextLastMessage != null)
				{
					Timestamp timeStamp = nextLastMessage.timestamp();
					
					String time = AppConfigProperties.getTimeFormatted(timeStamp);
					
					String message = nextLastMessage.message();
					//                message = message.length() > ControllerUtils.chatListMessageLength ? message.substring(0, ControllerUtils.chatListMessageLength-2) + "..." : message;
					
					lastMessagesForCustomer.put(nextChatCustomerRootEntity.getId(), message);// + " (" + time + ")");
					lastMessageTimesForCustomer.put(nextChatCustomerRootEntity.getId(), time);
					
					//only if it notifications to message sender
					if (nextLastMessage.senderId() == receiverId && AppConfigProperties.CHAT_NOTIFICATE_DELIEVERY_CHATLIST)
					{
						lastMessageSeenByCustomer.put(nextChatCustomerRootEntity.getId(), nextLastMessage.getReceiver().equals(nextChatCustomerRootEntity) && nextLastMessage.isSeenByReceiver());
						lastMessageDelieveredToCustomer.put(nextChatCustomerRootEntity.getId(), nextLastMessage.getReceiver().equals(nextChatCustomerRootEntity) && nextLastMessage.isDelieveredToReceiver());
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
		
		lastMessagesService.setLastFullNotification(receiverId, nextNotification);
		
		return nextNotification;
	}
	
	@Override
	public void createAndSendNotification(long receiverId, HotelEvent event){
		
		CustomerNotificationDTO receiverNotification = this.getCustomerNotification(receiverId, event);
		
		if(receiverNotification==null)
		{
			//No changes!
			return;
		}
		
		hotelRabbitMQProducer.produceSimpWebsocketMessage(AppConfigProperties.SOCKET_NOTIFICATION_TOPIC + receiverId + "", receiverNotification);
		
		if(event.getPushUrl()!=null)
		{
			receiverNotification.setPushCustomerEvent("Hotelico.de", event.getPushTitle(), event.getPushUrl(), event.getPushIcon(), "");
			lastMessagesService.setLastPushNotifiation(receiverId, receiverNotification);
			sendPushRequest(receiverId);
		}
		
	}
	
	public void sendFeedMessage(ICustomerRootEntity customerEntity, Map<String, String> systemMessages)
	{
		String feedMessage = systemMessages.get("hotelFeedMessage");
		
		String customerIds = systemMessages.get("hotelFeedCustomerIds");
		
		String inviteActivityId = systemMessages.get("inviteActivityId");
		String sendToAllNotLoggedInHotel = systemMessages.get("sendToAllNotLoggedInHotel");
		
		String fromName = systemMessages.get("fromName");
//		String fromEmail = systemMessages.get("fromMail");
		
		if(AppConfigProperties.isEmptyString(fromName))
		{
			fromName = customerEntity.getFirstName();
		}
		
		// PART 1: send to logged users!
		
		if(customerIds!=null)
		{
			String[] feedCustomersArray = customerIds.split(",");
			
			for(String nextCustomerId: feedCustomersArray)
			{
				long nextId = Integer.parseInt(nextCustomerId.trim());
				
				ICustomerRootEntity nextFeedCustomerRootEntity = customerService.getOne(nextId);
				
				if(nextFeedCustomerRootEntity !=null && nextFeedCustomerRootEntity.getEntityAggregate().isAllowHotelNotification())
				{
					long time = new Date().getTime();
					
					ChatMessageDTO feedChatMessage = new ChatMessageDTO(time);
					
					feedChatMessage.setSenderId(customerEntity.getId());
					feedChatMessage.setReceiverId(nextFeedCustomerRootEntity.getId());
					feedChatMessage.setMessage(feedMessage);
					feedChatMessage.setInitId(time);
					feedChatMessage.setCreationTime(time);
					feedChatMessage.setTimestamp( new Timestamp(time));
					
					if(!AppConfigProperties.isEmptyString(inviteActivityId))
					{
						feedChatMessage.getSpecialContent().put("activityId", inviteActivityId);
					}
					
					chatMSComminicationService.addChatMessage(feedChatMessage);
				}
				
			}
		}
		
		// PART 2: send to anonyme (not logged) users
		
		if(!AppConfigProperties.isEmptyString(sendToAllNotLoggedInHotel))
		{
			Boolean sendToUnLogged = Boolean.parseBoolean(sendToAllNotLoggedInHotel);
			
			if(sendToUnLogged)
			{
				int activId = Integer.parseInt(inviteActivityId);
				IHotelActivity hotelActivity = hotelService.getActivityByIdOrInitId(activId, activId);
				
				//SEND GUEST PUSH!!!
				this.sendPushToAllNotLoggedInHotel(hotelActivity);
			}
		}
	}

	@Transactional
	public void sendMailList(ICustomerRootEntity customerEntity, Map<String, String> systemMessages)
	{
		log.info("mailList inhalt: " + systemMessages);
		
		String mailList = systemMessages.get("mailList");
		
		String mailContent = "";//customerDto.getSystemMessages().get("mailContent");
		
		String fromName = systemMessages.get("fromName");
		
		String fromEmail = systemMessages.get("fromMail");
		
		String customSubject = systemMessages.get("customSubject");
		
		if(AppConfigProperties.isEmptyString(fromName))
		{
			fromName = customerEntity.getFirstName();
		}
		
		if(AppConfigProperties.isEmptyString(fromEmail))
		{
			fromEmail = customerEntity.getEmail();
		}
		
		if(AppConfigProperties.isEmptyString(customSubject))
		{
			customSubject = "Willkomen in unserem Hotel";
		}
		
		if(AppConfigProperties.isEmptyString(mailContent))
		{
			mailContent = mailService.getWellcomeMailBody(customerEntity);
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
	@Transactional
	public void createAndSendPushNotification_Chat(long receiverId, HotelEvent event, long senderId, String message)
	{
		CustomerNotificationDTO receiverNotification = this.getCustomerNotification(receiverId, event);
		
		hotelRabbitMQProducer.produceSimpWebsocketMessage(AppConfigProperties.SOCKET_NOTIFICATION_TOPIC + receiverId + "", receiverNotification);
		
		if(event.getPushUrl()!=null)
		{
			ICustomerRootEntity sender = customerService.getOne(senderId);
			
			if(sender!=null)
			{
				String pushUrlPostfix = String.valueOf(senderId);
				
				receiverNotification.setPushCustomerEvent(event.getPushTitle() + " from " + sender.getFirstName(), message, event.getPushUrl()+pushUrlPostfix, event.getPushIcon(), senderId+"");
				lastMessagesService.setLastPushNotifiation(receiverId, receiverNotification);
				sendPushRequest(receiverId);
			}
			
		}
	}
	
	@Override
	@Transactional
	public void createAndSendPushNotification_Activity(long receiverId, HotelEvent event, IHotelActivity activity, String message)
	{
		CustomerNotificationDTO receiverNotification = this.getCustomerNotification(receiverId, event);
		
		hotelRabbitMQProducer.produceSimpWebsocketMessage(AppConfigProperties.SOCKET_NOTIFICATION_TOPIC + receiverId + "", receiverNotification);
		
		if(event.getPushUrl()!=null)
		{
			if(activity!=null)
			{
				String pushUrlPostfix = "//" + activity.getHotelRootEntity().getId() + "/" + activity.getId();

				receiverNotification.setPushCustomerEvent(event.getPushTitle(), message, event.getPushUrl()+pushUrlPostfix, event.getPushIcon(), activity.getSender().getId()+"");
				lastMessagesService.setLastPushNotifiation(receiverId, receiverNotification);
				sendPushRequest(receiverId);
			}		
		}
	}
	
	 

	@Override
	public CustomerNotificationDTO getLastNotification(long customerId, boolean pushRequest)
	{
		CustomerNotificationDTO dto = lastMessagesService.getLastPushNotifiation(customerId);
		
		if(customerId<=0 || dto==null)
		{
			dto = new CustomerNotificationDTO();
			
			String  title ="Hotelico.de";
			String  message ="New Message!";
			String  icon ="angulr/img/build/logo/logo_push.png";
			//lastPushNotification.put("tag", "angulr/img/build/logo/logo_push.png");
			
			//Default. if no notification was found!!!
			String  relocateUrl = "/" + AppConfigProperties.HOST_SUFFIX + "#/app/chatList/false//";
			
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
		
		ICustomerRootEntity customerEntity = customerService.getOne(customerId);
		
		String pushRegistrationId = customerEntity.getPushRegistrationId();

		sendPush(pushRegistrationId);
	}
	
	@Override
	public void sendPush(String pushRegistrationId)
	{
		if(AppConfigProperties.isEmptyString(pushRegistrationId))
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
	public void broadcastActivityNotification(HotelActivityDTO hotelActivityDto)
	{
		produceRabbitmqMessageService.produceSimpMessage(AppConfigProperties.SOCKET_ACTIVITY_TOPIC + hotelActivityDto.getHotelId() + "", hotelActivityDto);
	}
	
	@Override
	public void broadcastWallNotification(WallPostDTO wallPostDto)
	{
		produceRabbitmqMessageService.produceSimpMessage(AppConfigProperties.SOCKET_WALL_TOPIC + wallPostDto.getHotelId(), wallPostDto);
	}

	@Override
	public boolean sendPushToAllNotLoggedInHotel(IHotelActivity hotelActivity)
	{
		if(hotelActivity==null)
		{
			return false;
		}
		
//		List<CustomerHotelCheckin> allActiveInHotel = checkinService.getActiveByHotelId(hotelActivity.getHotel().getId(), new Date());

		Map<Long, String> notLoggedGuestPushIdsByHotel= hotelService.getNotLoggedGuestPushIdsByHotel(hotelActivity.getHotelRootEntity());

		String notificationMessage = "new last minute deal";

		HotelEvent eventActivityNewLastMinute = HotelEvent.EVENT_ACTIVITY_NEW_LAST_MINUTE;

		Set<String> loggedGuestPushIds = new HashSet<>();
		
		List<ICustomerRootEntity> allActiveInHotel = checkinService.getActiveCustomersByHotelId(hotelActivity.getHotelRootEntity().getId(), new Date());
		
		for (ICustomerRootEntity nextActiveCustomerRootEntity : allActiveInHotel)
		{
			loggedGuestPushIds.add(nextActiveCustomerRootEntity.getPushRegistrationId());
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
				String pushUrlPostfix = "//" + hotelActivity.getHotelRootEntity().getId() + "/" + hotelActivity.getId();

				CustomerNotificationDTO receiverNotification = new CustomerNotificationDTO();

				receiverNotification.setPushCustomerEvent(eventActivityNewLastMinute.getPushTitle(), notificationMessage, eventActivityNewLastMinute.getPushUrl()+pushUrlPostfix, eventActivityNewLastMinute.getPushIcon(), hotelActivity.getSender().getId()+"");
				lastMessagesService.setLastPushNotifiation((int)nextGuestId, receiverNotification);
				this.sendPush(pushId);
			}
		}

		return notLoggedGuestPushIdsByHotel.size()>0;
	}
	
	@Override
	public void sendNotificationToCustomerOrGuest(ICustomerRootEntity receiver, long guestCustomerId, HotelEvent event)
	{
		if(receiver!=null)
		{
			this.createAndSendNotification(receiver.getId(), event);
		}
		else{
			CustomerNotificationDTO receiverNotification = new CustomerNotificationDTO();
			receiverNotification.setCustomerEvent(0, 0, event, "new event", 0);
			receiverNotification.setReceiverId(guestCustomerId);
			hotelRabbitMQProducer.produceSimpWebsocketMessage(AppConfigProperties.SOCKET_NOTIFICATION_TOPIC + guestCustomerId + "", receiverNotification);
		}
	}
}
