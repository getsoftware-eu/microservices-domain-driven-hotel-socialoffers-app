package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.usecase.notification;

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.menu.MenuOrderDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.innerEvents.InnerDomainEvent;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.clients.common.utils.DealStatus;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.model.HotelDbActivity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.InnerHotelEvent;
import eu.getsoftware.hotelico.hotelapp.adapter.out.viewEntity.model.ChatMessageView;
import eu.getsoftware.hotelico.hotelapp.application.chat.domain.infrastructure.ChatMSComminicationService;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.out.CheckinPortService;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.IHotelActivity;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.port.out.iPortService.CustomerPortService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.in.NotificationUseCase;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.*;
import eu.getsoftware.hotelico.hotelapp.application.menu.infrastructure.service.MenuMSCommunicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
/*public*/ class NotificationUseCaseImpl implements NotificationUseCase<InnerHotelEvent>
{
	private final IHotelService hotelService;
	
	private final MenuMSCommunicationService menuMSCommunicationService;	
	
	private final LastMessagesService lastMessagesService;
	
	private final CustomerPortService customerService;	
	
	private final MailService mailService;
	private final CheckinPortService checkinService;
	
	private final ChatMSComminicationService chatMSComminicationService;
	
	private final INotificationService notificationService;
	
	private IWebSocketService webSocketService;
	private ModelMapper modelMapper;

	//	@Override
	public void notificateAboutEntityEventWebSocket(CustomerDTO dto, InnerDomainEvent event, String eventContent, long entityId)
	{
		Objects.requireNonNull(dto);
		
		List<CustomerDomainEntityId> allOnlineCustomerIds = lastMessagesService.getOnlineCustomerIds();
		
		if(InnerHotelEvent.EVENT_LOGO_CUSTOMER_CHANGE_MESSAGE.equals(event) && !allOnlineCustomerIds.contains(dto.getId()))
		{
			allOnlineCustomerIds.add(dto.getDomainEntityId());
		}
		
		for( CustomerDomainEntityId nextOnlineCustomerId: allOnlineCustomerIds)
		{
			CustomerNotificationDTO receiverNotification = this.getCustomerNotification(nextOnlineCustomerId, event);

			webSocketService.notificateAboutEntityEvent(dto, receiverNotification, event);
		}
	}
	
//	@Override
	public CustomerNotificationDTO getCustomerNotification(CustomerDomainEntityId receiverId, InnerDomainEvent event)
	{
		CustomerNotificationDTO nextNotification = new CustomerNotificationDTO();
		
		nextNotification.setCreationTime(System.currentTimeMillis());
		
		//######################### 

		HotelDomainEntityId receiverHotelId = customerService.getCustomerHotelId(receiverId);
		
		nextNotification.setId(System.currentTimeMillis());
		
		nextNotification.setReceiverId(receiverId.uuidValue());
		
		///#########################################################################
		
		///###########   CHECK ALL ONLINE //////////////

		List<CustomerDomainEntityId> allOnlineCustomersIds = lastMessagesService.getOnlineCustomerIds();
		
		Set<CustomerDomainEntityId> onlineGuestIds = new HashSet<>();
		
		for ( var nextOnlineCustomerId: allOnlineCustomersIds)
		{
			if(nextOnlineCustomerId!=receiverId)
			{
				onlineGuestIds.add(nextOnlineCustomerId);
			}
		}
		/////////// 1 ONLINE - SET RESULTS TO MAP
		
		nextNotification.setHotelOnlineGuestIds(onlineGuestIds.toArray(new Integer[onlineGuestIds.size()]));
		
		if(InnerHotelEvent.EVENT_ONLINE_CUSTOMERS.equals(event))
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
		
		if(receiverHotelId!=null)
		{
			//################## REAL HOTEL NOTIFICATIONS #####################
			
			//######## ACTIVITIES 
			
			if (AppConfigProperties.USE_UNREAD_ACTIVITY_COUNT)
			{
				//                int hotelActivitiesTotalNumber = hotelActivityRepository.getTimeValidCounterByHotelId(receiverHotelId, new Date());
				
				//            Integer seenActivitiesNumber = customerRepository.getSeenActivitiesNumber(receiverDomainId);
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
				Map<CustomerDomainEntityId, String> lastSeenOnlineMap = new HashMap<>();
				nextNotification.setLastSeenOnlineHotelGuests(lastSeenOnlineMap);
				
			}
			
			boolean isStaffOrAdmin = customerService.isStaffOrAdminId(receiverId);
			
			//TODO Eugen: Menu orders of customer: Problem one time ausführung
			if(isStaffOrAdmin || InnerHotelEvent.EVENT_MENU_NEW_UPDATE.equals(event))
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
			
			//TODO EU: Unread ChatQueue wird automatisch Nachricht deliver, wenn der Customer will be online, why doing this manually?
			//TODO EU: похоже что пытались повторить функциональность ACK/NACK для каждого ожидаюшщего сообщения. Пропадут ли это Queue при restart?
			// - Если очередь настроена как персистентная (то есть при создании указано свойство durable = true), то она сохраняется после перезапуска сервера.
			// - - При отправке сообщения нужно установить флаг deliveryMode = 2 (Persistent). Только в этом случае сообщения сохраняются на диск и будут восстановлены после перезапуска сервера.
			// - - Аварийное завершение работы RabbitMQ: Если сообщения не подтверждены (ACK) клиентом или не записаны на диск до сбоя, они могут быть потеряны.
			Map<CustomerDomainEntityId, List<ChatMsgDTO>> unreadChatsBySenders = lastMessagesService.getCustomerUnreadChatsBySenders(receiverId);
			
			Map<CustomerDomainEntityId, Integer> unreadChatBySenderCounter = new HashMap<>();
			
			//        List<Integer> hotelStaffIdList = checkinRepository.getStaffIdsByHotelId(receiverHotelId);
			
			for (CustomerDomainEntityId nextUnreadSenderId : unreadChatsBySenders.keySet())
			{
				//TODO Eugen: soll ich Staff von anderen Hotels ausschliessen???? ja
				if (nextUnreadSenderId != receiverId /*&& !hotelStaffIdList.contains(nextUnreadSenderId)*/)
				{
					List<ChatMsgDTO> unreadChatMessages = unreadChatsBySenders.get(nextUnreadSenderId);
					
					if (!unreadChatMessages.isEmpty() && AppConfigProperties.CHAT_NOTIFICATE_DELIEVERY_INDIVIDUAL_CHAT)
					{
						ChatMessageView lastUnreadMessage = null;
						
						if (!lastUnreadMessage.isDelieveredToReceiver() && !lastUnreadMessage.isSeenByReceiver() &&  allOnlineCustomersIds.contains(receiverId))
						{
							if (!lastUnreadMessage.isDelieveredToReceiver()) //Eugen: ONLY 1 Time, then it will be marked as delivered!!!
							{
								lastUnreadMessage.setDelieveredToReceiver(true);
								
								//Eugen: notificate Sender, that his message was delievered
								ChatMsgDTO chatMessageDto = chatMSComminicationService.convertMessageToDto(lastUnreadMessage);
//		
								chatMSComminicationService.addUpdateChatMessage(chatMessageDto);
								
//								simpMessagingTemplate.convertAndSend(AppConfigProperties.SOCKET_CHAT_TOPIC + lastUnreadMessage.getSenderId() + "", chatMessageDto);
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
			
			Set<CustomerDTO> allChatPartners = new HashSet<>();
			
			//TODO Eugen: save last message in cache
			allChatPartners.addAll(chatMSComminicationService.getChatSendersByCustomerDomainId(receiverId));
			allChatPartners.addAll(chatMSComminicationService.getChatReceiversByCustomerDomainId(receiverId));
			
			//        allChatPartners.removeAll(hotelCustomers);
			//        Set<Customer> notHotelChatPartners = allChatPartners;
			
			//////////////
			
			for (CustomerDTO nextChatCustomerRootEntity : allChatPartners)
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
				//            ChatMessage nextLastMessage = chatRepository.getLastMessageByCustomerAndReceiverIds(nextChatCustomer.getId(), receiverDomainId);
				ChatMsgDTO nextLastMessage = lastMessagesService.getLastMessageBetweenCustomers(nextChatCustomerRootEntity.getDomainEntityId(), receiverId);
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
					if (nextLastMessage.senderDomainId().equals(receiverId) && AppConfigProperties.CHAT_NOTIFICATE_DELIEVERY_CHATLIST)
					{
						lastMessageSeenByCustomer.put(nextChatCustomerRootEntity.getId(), nextLastMessage.receiverDomainId().equals(nextChatCustomerRootEntity.getDomainEntityId()) && nextLastMessage.seenByReceiver());
						lastMessageDelieveredToCustomer.put(nextChatCustomerRootEntity.getId(), nextLastMessage.receiverDomainId().equals(nextChatCustomerRootEntity.getDomainEntityId()) && nextLastMessage.deliveredToReceiver());
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
	
//	@Override
	public void createAndSendWebSocketNotification(CustomerDomainEntityId receiverId, InnerDomainEvent event) throws Throwable {
		
		CustomerNotificationDTO receiverNotification = this.getCustomerNotification(receiverId, event);
		
		if(receiverNotification==null)
		{
			//No changes!
			return;
		}
		
		webSocketService.produceSimpWebsocketMessage(AppConfigProperties.SOCKET_NOTIFICATION_TOPIC + receiverId + "", receiverNotification);
		
		if(event.getPushUrl()!=null)
		{
			receiverNotification.setSocketPushCustomerEvent("Hotelico.de", event.getPushTitle(), event.getPushUrl(), event.getPushIcon(), "");
			lastMessagesService.setLastPushNotifiation(receiverId, receiverNotification);
			sendPushRequest(receiverId);
		}
		
	}
	
	public void sendFeedMessage(CustomerDTO customerEntity, Map<String, String> systemMessages) throws Throwable {
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
				CustomerDomainEntityId nextId = new CustomerDomainEntityId(nextCustomerId.trim());

				CustomerRootDomainEntity nextFeedCustomerRootEntity = (CustomerRootDomainEntity) customerService.getEntityById(nextId).orElseThrow(() -> new RuntimeException("not found"));
				
				if(nextFeedCustomerRootEntity !=null && nextFeedCustomerRootEntity.isAllowHotelNotification())
				{
					long time = System.currentTimeMillis();

					var senderId=(customerEntity.getDomainEntityId());
					var receiverId=(nextFeedCustomerRootEntity.getDomainEntityId());
					var message=(feedMessage);
					var initId=(time);
					var creationTime=(time);
					var timestamp=( new Timestamp(time));
					
					var specialcontent = new HashMap<String, String>(10);
					specialcontent.put("activityId", inviteActivityId);
					
					var active =  true;
					
					ChatMsgDTO feedChatMessage = new ChatMsgDTO(
							initId,
							message,
							senderId,
							receiverId,
							false, 
							false, 
							false,
							creationTime,
							timestamp,
							specialcontent,
							active);
					
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
				HotelDbActivity hotelActivity = (HotelDbActivity) hotelService.getActivityByIdOrInitId(activId, activId).orElseThrow(()->new RuntimeException());
				
				//SEND GUEST PUSH!!!
				this.sendPushToAllNotLoggedInHotel(modelMapper.map(hotelActivity, HotelActivityDTO.class));
			}
		}
	}

	@Override
	public void createAndSendWebSocketNotification_Chat(CustomerDomainEntityId receiverId, InnerHotelEvent event, CustomerDomainEntityId senderId, String message) {

	}

	@Override
	public void createAndSendWebSocketNotification_Activity(CustomerDomainEntityId receiverId, InnerHotelEvent event, IHotelActivity activity, String message) {

	}

	@Override
	public CustomerNotificationDTO getCustomerNotification(CustomerDomainEntityId receiverId, InnerHotelEvent event) {
		return null;
	}

	@Override
	public void notificateAboutEntityEventWebSocket(CustomerDTO dto, InnerHotelEvent event, String eventContent, long entityId) {

	}

	@Transactional
	public void sendMailList(CustomerDTO customerEntity, Map<String, String> systemMessages)
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
			mailContent = mailService.getWelcomeMailBody(customerEntity);
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
	
//	@Override
	@Transactional
	public void createAndSendWebSocketNotification_Chat(CustomerDomainEntityId receiverId, InnerDomainEvent event, CustomerDomainEntityId senderId, String message) throws Throwable {
		CustomerNotificationDTO receiverNotification = this.getCustomerNotification(receiverId, event);

		String webSocketTopic = AppConfigProperties.SOCKET_NOTIFICATION_TOPIC + receiverId + "";
		webSocketService.produceSimpWebsocketMessage(webSocketTopic, receiverNotification);
		
		if(event.getPushUrl()!=null)
		{
			Optional<CustomerRootDomainEntity> senderOpt = customerService.getEntityById(senderId);
			
			if(senderOpt.isPresent())
			{
				String pushUrlPostfix = String.valueOf(senderId);
				
				receiverNotification.setSocketPushCustomerEvent(event.getPushTitle() + " from " + senderOpt.get().getFirstName(), message, event.getPushUrl()+pushUrlPostfix, event.getPushIcon(), senderId+"");
				lastMessagesService.setLastPushNotifiation(receiverId, receiverNotification);
				sendPushRequest(receiverId);
			}
			
		}
	}
	
	@Transactional
	public void createAndSendWebSocketNotification_Activity(CustomerDomainEntityId receiverId, InnerDomainEvent event, IHotelActivity activity, String message) throws Throwable {
		CustomerNotificationDTO receiverNotification = this.getCustomerNotification(receiverId, event);

		String webSocketTopic = AppConfigProperties.SOCKET_NOTIFICATION_TOPIC + receiverId + "";
		webSocketService.produceSimpWebsocketMessage(webSocketTopic, receiverNotification);
		
		if(event.getPushUrl()!=null)
		{
			if(activity!=null)
			{
				String pushUrlPostfix = "//" + activity.getHotelDomainId() + "/" + activity.getId();

				receiverNotification.setSocketPushCustomerEvent(event.getPushTitle(), message, event.getPushUrl()+pushUrlPostfix, event.getPushIcon(), activity.senderId()+"");
				lastMessagesService.setLastPushNotifiation(receiverId, receiverNotification);
				sendPushRequest(receiverId);
			}		
		}
	}
	
	 

	@Override
	public CustomerNotificationDTO getLastNotification(CustomerDomainEntityId customerId, boolean pushRequest)
	{
		CustomerNotificationDTO dto = lastMessagesService.getLastPushNotifiation(customerId);
		
		if(customerId==null || dto==null)
		{
			dto = new CustomerNotificationDTO();
			
			String  title ="Hotelico.de";
			String  message ="New Message!";
			String  icon ="angulr/img/build/logo/logo_push.png";
			//lastPushNotification.put("tag", "angulr/img/build/logo/logo_push.png");
			
			//Default. if no notification was found!!!
			String  relocateUrl = "/" + AppConfigProperties.HOST_SUFFIX + "#/app/chatList/false//";
			
			dto.setSocketPushCustomerEvent(title, message, relocateUrl, icon, "");
		}
		
		return dto;
	}

	@Override
	public void createAndSendWebSocketNotification(CustomerDomainEntityId receiverId, InnerHotelEvent event) {
		
	}

	@Override
	public void sendPushRequest(CustomerDomainEntityId customerId) throws Throwable {
		if(customerId==null)
		{
			return;
		}

		  CustomerRootDomainEntity customerEntity = (CustomerRootDomainEntity) customerService.getByDomainId(customerId).orElseThrow(()-> new RuntimeException());
		
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

		//        ResponseEntity<String> result = restTemplate.exchange(url, RequestMethod.GET, request, String.class);
		String result = restTemplate.postForObject(url, request, String.class);

		System.out.println(result);
	}

	@Override
	public void broadcastActivityNotification(HotelActivityDTO hotelActivityDto)
	{
		webSocketService.produceSimpWebsocketMessage(AppConfigProperties.SOCKET_ACTIVITY_TOPIC + hotelActivityDto.getHotelId() + "", hotelActivityDto);
	}
	
	@Override
	public void broadcastWallNotification(WallPostDTO wallPostDto)
	{
		webSocketService.produceSimpWebsocketMessage(AppConfigProperties.SOCKET_WALL_TOPIC + wallPostDto.getHotelId(), wallPostDto);
	}

	@Override
	public boolean sendPushToAllNotLoggedInHotel(HotelActivityDTO hotelActivity)
	{
		if(hotelActivity==null)
		{
			return false;
		}
		
//		List<CheckinRootDomainEntity> allActiveInHotel = checkinService.getActiveByHotelId(hotelActivity.getHotel().getId(), new Date());

		Map<CustomerDomainEntityId, String> notLoggedGuestPushIdsByHotel= new HashMap<>();//hotelService.getNotLoggedGuestPushIdsByHotel(hotelActivity.getHotelId());

		String notificationMessage = "new last minute deal";

		InnerDomainEvent eventActivityNewLastMinute = InnerHotelEvent.EVENT_ACTIVITY_NEW_LAST_MINUTE;

		Set<String> loggedGuestPushIds = new HashSet<>();
		
		List<CustomerDTO> allActiveInHotel = checkinService.getActiveCustomersByHotelId(hotelActivity.getHotelId(), new Date());
		
		for (CustomerDTO nextActiveCustomerRootEntity : allActiveInHotel)
		{
			loggedGuestPushIds.add(nextActiveCustomerRootEntity.getPushRegistrationId());
		}
		
		//Eugen: ignore people, who has anonyme unsubscribed! in other hotels too!
		Map<Long, String> unsubscribedGuestsInAllHotels = hotelService.getUnsubscribeGuestPushIds();
		
		Set<CustomerDomainEntityId> notLoggedGuestIds = notLoggedGuestPushIdsByHotel.keySet();
		for (CustomerDomainEntityId nextGuestId : notLoggedGuestIds)
		{
			String pushId = notLoggedGuestPushIdsByHotel.get(nextGuestId);
			
			if(unsubscribedGuestsInAllHotels.keySet().contains(nextGuestId) || unsubscribedGuestsInAllHotels.containsValue(pushId))
			{
				continue;
			}
			
			//Eugen: if a user (with given pushId) has checkin in a hotel, so it is not anonyme push Guest!!!
			if(hotelActivity!=null && !loggedGuestPushIds.contains(pushId))
			{
				String pushUrlPostfix = "//" + hotelActivity.getHotelId() + "/" + hotelActivity.getId();

				CustomerNotificationDTO receiverNotification = new CustomerNotificationDTO();

				receiverNotification.setSocketPushCustomerEvent(eventActivityNewLastMinute.getPushTitle(), notificationMessage, eventActivityNewLastMinute.getPushUrl()+pushUrlPostfix, eventActivityNewLastMinute.getPushIcon(), hotelActivity.getSenderDomainId()+"");
				lastMessagesService.setLastPushNotifiation(nextGuestId, receiverNotification);
				this.sendPush(pushId);
			}
		}

		return notLoggedGuestPushIdsByHotel.size()>0;
	}
	
//	@Override
	public void sendNotificationToCustomerOrGuest(CustomerDTO receiver, CustomerDomainEntityId guestCustomerId, InnerHotelEvent event) {
		if(receiver!=null)
		{
			this.createAndSendWebSocketNotification(receiver.getDomainEntityId(), event);
		}
		else{
			CustomerNotificationDTO receiverNotification = new CustomerNotificationDTO();
			receiverNotification.setCustomerEvent(0, 0, event, "new event", 0);
			receiverNotification.setReceiverId(guestCustomerId.uuidValue());
			webSocketService.produceSimpWebsocketMessage(AppConfigProperties.SOCKET_NOTIFICATION_TOPIC + guestCustomerId + "", receiverNotification);
		}
	}
}
