//package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.outPortServiceImpl;
//
//import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
//import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
//import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.lang.NotImplementedException;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.sql.Timestamp;
//import java.util.*;
//
///**
// * Created by Eugen on 16.07.2015.
// */
//@Service
//@RequiredArgsConstructor
//public class ChatMSCommunicationServiceImpl implements ChatMSComminicationService
//{
//	private CustomerPortService customerService;		
//	
//	private LastMessagesService lastMessagesService;		
//	
//	private INotificationService notificationService;	
//	
//	private IHotelService hotelService;
//	
//	private CustomerRepository customerRepository;	
//	
//	private CheckinRepository checkinRepository;
//
//	MessagingRabbitMQProducer hotelRabbitMQProducer;
//	
//	private ModelMapper modelMapper;
//	
//	private IWebSocketService webSocketService;
//
//	public List<ChatMessageView> getChatMessages()
//	{
//		throw new NotImplementedException();
//	}
//	
//	public List<ChatMsgDTO> getMessagesByCustomerId(long customerId, long receiverId)
//	{
//		List<ChatMessageView> list = chatRepository.getMessagesByCustomerIds(customerId, receiverId);
//		List<ChatMsgDTO> out = new ArrayList<ChatMsgDTO>();
//		for (ChatMessageView nextMessage : list) {
//			
//			if(nextMessage.getReceiver().getId()==customerId)
//			{
//				nextMessage.setSeenByReceiver(true);
//				chatRepository.save(nextMessage);
//			}
//
//			ChatMsgDTO nextDto = convertMessageToDto(nextMessage);
//			
//			out.add(nextDto);
//		}
//		
//		return out;
//	}
//	
////	@Override
////	public ChatMsgDTO convertMessageToDto(ChatMessageView nextMessage)
////	{
////		ChatMsgDTO nextDto = nextMessage!=null ? modelMapper.map(nextMessage, ChatMsgDTO.class) : null;
////		
////		nextDto = fillDtoFromMessage(nextDto, nextMessage);
////		
////		return nextDto;
////	}
//
//	@Override
//	public ChatMsgDTO addUpdateChatMessage(ChatMsgDTO chatMessageDto)
//	{
//		List<ChatMessageView> existMessages = chatRepository.getMessageByCustomerAndInitId(chatMessageDto.getSenderId(), chatMessageDto.getInitId());
//
//		if(existMessages.isEmpty())
//		{
//			//Add Message if it is new
//			chatMessageDto = addChatMessage(chatMessageDto);
//		}
//		else{
//			//If message already exists, maybe deliever-status was changed
//			for (ChatMessageView nextExistingMessage :existMessages)
//			{
//				//Add Message, if it was delievered or seen by receiver
//				if(!nextExistingMessage.isDelieveredToReceiver() && chatMessageDto.getDelieveredToReceiver() 
//					||
//					!nextExistingMessage.isSeenByReceiver() && chatMessageDto.getSeenByReceiver()
//				)
//				{
//					chatMessageDto = addChatMessage(chatMessageDto);
//				}
//			}
//		}
//		
//		return chatMessageDto;
//	}
//	
//	@Override
//	public ChatMsgDTO addChatMessage(ChatMsgDTO chatMessageDto)
//	{
//		boolean existsMessageId = chatRepository.existMessageWithInitId(chatMessageDto.getInitId());
//		
//		if(existsMessageId && chatMessageDto.getSenderId()==chatMessageDto.getReceiverId())
//		{
//			return null;
//		}
//		
//		//Only if such message not exists in DB! == null
// 		
//		CustomerDBEntity sender = customerService.getEntityById(chatMessageDto.getSenderId());
//		CustomerDBEntity receiver = customerService.getEntityById(chatMessageDto.getReceiverId());
//
//		ChatMessageView newMessage = new ChatMessageView(chatMessageDto.getMessage(), sender, receiver);
//		newMessage.setInitId(chatMessageDto.getInitId());
//		
//		if (chatMessageDto.getCreationTime()<=0)
//		{
//			chatMessageDto.setCreationTime(System.currentTimeMillis());
//		}
//		
//		newMessage.setTimestamp(new Timestamp(chatMessageDto.getCreationTime()));
//		
//		newMessage.setSpecialChatContent(chatMessageDto.getSpecialContent().toString());
//		
//		chatRepository.saveAndFlush(newMessage);
//		
//		lastMessagesService.setLastMessageBetweenCustomers(newMessage);
//		lastMessagesService.updateUnreadMessagesToCustomer(newMessage);
//	
//		chatMessageDto = fillDtoFromMessage(chatMessageDto, newMessage);
//		
//		if(AppConfigProperties.CHAT_DELIEVER_INDIVIDUAL)
//		{
//			webSocketService.produceSimpWebsocketMessage(AppConfigProperties.SOCKET_CHAT_TOPIC + newMessage.getSender().getId() + "", chatMessageDto);
//			webSocketService.produceSimpWebsocketMessage(AppConfigProperties.SOCKET_CHAT_TOPIC + newMessage.getReceiver().getId() + "", chatMessageDto);			
//		}
//
//		notificationService.createAndSendNotification(sender.getId(), HotelEvent.EVENT_CHAT_SEND_MESSAGE);
//		notificationService.createAndSendPushNotification_Chat(receiver.getId(), HotelEvent.EVENT_CHAT_NEW_MESSAGE, sender.getId(), newMessage.getMessage());
//		
//		return chatMessageDto;
//	}
//
////	private ChatMsgDTO fillDtoFromMessage(ChatMsgDTO chatDto, ChatMessageView chatMessage)
////	{
////		if(chatMessage==null || chatDto==null)
////		{
////			return null;	
////		}
////		
////		chatDto.setSenderId(chatMessage.getSender().getId());
////
////		chatDto.setReceiverId(chatMessage.getReceiver().getId());
////
////		chatDto.setHotelStaff(chatMessage.getSender().isHotelStaff());
////
////		chatDto.setTimestamp(chatMessage.getTimestamp());
////		
////		chatDto.setCreationTime(chatMessage.getTimestamp().getTime());
////
////		chatDto.setId(chatMessage.getId());
////
////		chatDto.setSeenByReceiver(chatMessage.isSeenByReceiver());
////
////		if(chatMessage.getSpecialChatContent()!=null)
////		{
////			String specContent = chatMessage.getSpecialChatContent();
////
////			String[] split = specContent.replace("{","").replace("}", "").split("=");
////
////			if(split.length>1)
////			{
////				String key = split[0];
////				String value = split[1];
////
////				chatDto.getSpecialContent().put(key, value);
////
////				if(key.equalsIgnoreCase("activityId"))
////				{
////					long activityId = Long.parseLong(value);
////
////					HotelActivity activity = hotelService.getActivityByIdOrInitId((int)activityId, activityId);
////
////					if(activity!=null)
////					{
////						chatDto.getSpecialContent().put("name", activity.getTitle());
////						chatDto.getSpecialContent().put("hotelId", activity.getHotelRootEntity().getId()+"");
////						chatDto.getSpecialContent().put("pictureUrl", activity.getPreviewPictureUrl()!=null?activity.getPreviewPictureUrl() : AppConfigProperties.PREVIEW_ACTIVITY_NOT_AVAILABLE_URL );
////					}
////
////				}
////				else
////				if(key.equalsIgnoreCase("customerId"))
////				{
////					long customerId = Integer.parseInt(value);
////
////					CustomerDBEntity customerEntity = customerRepository.getOne(customerId);
////
////					if(customerEntity !=null)
////					{
////						chatDto.getSpecialContent().put("name", customerEntity.getFirstName() + " " + customerEntity.getLastName());
////						chatDto.getSpecialContent().put("pictureUrl", customerService.getCustomerAvatarUrl(customerEntity));
////					}
////
////				}
////			}
////		}
////		
////		return chatDto; 
////	}
//
//	@Override
//	public ChatMsgDTO getChatMessageById(long chatMessageId)
//	{
//		throw new NotImplementedException();
//	}
//
//	@Override
//	public ChatMsgDTO getChatMessageBySender(long senderId)
//	{
//		throw new NotImplementedException();
//	}
//
//	@Override
//	public ChatMsgDTO getChatMessageByReceiver(long receiverId)
//	{
//		throw new NotImplementedException();
//	}
//
//	@Override
//	public ChatMsgDTO getChatMessageByDateRange(Date startDate, Date endDate)
//	{
//		throw new NotImplementedException();
//	}
//
//	@Override
//	public void deleteChatMessage(ChatMsgDTO chatMessageDto)
//	{
//		throw new NotImplementedException();
//	}
//
//	@Override
//	public void markMessageRead(long customerId, String messageIds)
//	{
//		String[] messageIdArray = messageIds!=null? messageIds.split(",") : new String[0];
//		
//		for (String nextMessageId : messageIdArray)
//		{
//			long nextMessageIdLong = Long.parseLong(nextMessageId);
//
//			List<ChatMessageView> messages = chatRepository.getMessageByCustomerAndInitId(customerId, nextMessageIdLong);
//
//			if (messages.isEmpty())
//			{
//				continue;
//			}
//			
//			for(ChatMessageView message: messages)
//			{
//				message.setDelieveredToReceiver(true);
//				message.setSeenByReceiver(true);
//				chatRepository.save(message);
//
//				//Notificate Sender about SEEN message
//				webSocketService.produceSimpWebsocketMessage(AppConfigProperties.SOCKET_CHAT_TOPIC + message.getSender().getId() + "", convertMessageToDto(message));
//
//				lastMessagesService.markMessageRead(message);
//				lastMessagesService.markLastMessageBetweenCustomers(message);
//			}
//		}
//		
//		
//	}
//	
//	@Override
//	public void markChatRead(long customerId, long senderId, long maxSeenChatMessageId)
//	{
//		List<ChatMessageView> unreadMessagesBySender = chatRepository.getUnreadChatMessagesForCustomerBySender(customerId, senderId);
//
//		ChatMessageView latestUnreadMessage = null;
//		
//		for (ChatMessageView nextReadMsg: unreadMessagesBySender)
//		{
//			if(nextReadMsg.getInitId() <= maxSeenChatMessageId)
//			{
//				nextReadMsg.setSeenByReceiver(true);
//				chatRepository.save(nextReadMsg);
//				
//				if (latestUnreadMessage == null || latestUnreadMessage.getInitId() < nextReadMsg.getInitId())
//				{
//					latestUnreadMessage = nextReadMsg;
//				}
//			}
//		}
//
//		if(latestUnreadMessage!=null)
//		{
//			lastMessagesService.markLastMessageBetweenCustomers(latestUnreadMessage);
//
//			//Notificate Sender via WebSocket about last UN-SEEN message
//			webSocketService.produceSimpWebsocketMessage(AppConfigProperties.SOCKET_CHAT_TOPIC + latestUnreadMessage.getSender().getId() + "", convertMessageToDto(latestUnreadMessage));
//		}
//
//		lastMessagesService.markChatRead(customerId, senderId);
//
//	}
//	
//	@Override
//	public Set<CustomerDTO> getNotHotelChatPartners(long requesterId, String filterCity, long filterHotelId){
//		
//		CustomerDBEntity customerEntity = customerService.getEntityById(requesterId);
//		
//		Set<CustomerDTO> notHotelChatPartners = new HashSet<>();
//		
//		if(customerEntity !=null)
//		{
//			long customerHotelId = customerService.getCustomerHotelId(requesterId);
//			
//			long virtualHotelId = lastMessagesService.getInitHotelId();
//			
//			boolean customerIsInHotel = customerHotelId>0 && customerHotelId!=virtualHotelId;
//			
//			Set<CustomerDBEntity> chatPartners = new HashSet<>();
//
//			chatPartners.addAll(chatRepository.getChatSendersByCustomerId(requesterId));
//			chatPartners.addAll(chatRepository.getChatReceiversByCustomerId(requesterId));
//
//			for (CustomerDBEntity nextPartner : chatPartners)
//			{
//				//exclude a customer itself and hotelStaff from result
//				if(nextPartner.equals(customerEntity) || nextPartner.isHotelStaff())
//				{
//					continue;
//				}
//
//				boolean chatPartnerIsInSameHotel = true;
//				
//				if(!customerIsInHotel)
//				{
//					chatPartnerIsInSameHotel = false;
//				}
//				
//				long partnerHotelId = customerService.getCustomerHotelId(nextPartner.getId());
//				
//				if(chatPartnerIsInSameHotel)
//				{
//					if(customerHotelId != partnerHotelId || partnerHotelId == virtualHotelId )
//					{
//						chatPartnerIsInSameHotel = false;
//					}
//				}
//				
//				if(!chatPartnerIsInSameHotel)
//				{
//					ChatMessageView nextLastMessage = chatRepository.getLastMessageByCustomerAndReceiverIds(requesterId, nextPartner.getId());
//					
////					CustomerDto chatPartnerDto = modelMapper.map(nextPartner, CustomerDto.class);
////					chatPartnerDto = customerService.fillDtoFromCustomer(nextPartner, chatPartnerDto);
//					CustomerDTO chatPartnerDto = customerService.convertCustomerToDto(nextPartner, partnerHotelId);
//
//					boolean isPartnerInMyHotelWithFullCheckin = customerHotelId>0 && customerHotelId==chatPartnerDto.getHotelId() && checkinRepository.isFullCheckinForCustomerByHotelId(chatPartnerDto.getId(), chatPartnerDto.getHotelId(), LocalDate.now());
//
//					chatPartnerDto.setInMyHotel(isPartnerInMyHotelWithFullCheckin);
//					
//					//TODO eugen: fill dto!!!
//					if (nextLastMessage != null)
//					{
//						Timestamp lastMessageTimestamp = nextLastMessage.getTimestamp();
//
//						String time = AppConfigProperties.getTimeFormatted(lastMessageTimestamp);
//
//						String message = nextLastMessage.getMessage();
//// Only Client can short the message, because it has to be encoded first. only then cuttet!!!					
//// 	message = message.length() > ControllerUtils.chatListMessageLength ? message.substring(0, ControllerUtils.chatListMessageLength-2) + "..." : message;
//
//						chatPartnerDto.setLastMessageToMe(message);//+ " (at " + time + ")");
//						chatPartnerDto.setLastMessageTimeToMe(lastMessageTimestamp.getTime());
//						chatPartnerDto.setChatWithMe(true);
//
//						//int unReadCounter = chatRepository.getNotReadMessageCountBySenderId(requesterId, dto.getId());
//					}
//
//					notHotelChatPartners.add(chatPartnerDto);
//				}
//
//			}
//			
//		}
//		
//		return notHotelChatPartners;
//	}
//	
//	@Override
//	public Set<CustomerDTO> getAllContactChatPartners(long guestCustomerId, String filterCity, long filterHotelId){
//		
//		long customerId = guestCustomerId;
//		
//		CustomerDBEntity customerEntity = customerService.getEntityById(customerId);
//		
//		Set<CustomerDTO> allContactChatPartners = new HashSet<>();
//		
//		if(customerEntity !=null)
//		{
//			long customerHotelId = customerService.getCustomerHotelId(customerId);
//			
////			int virtualHotelId = cacheService.getInitHotelId();
//			
//			Set<CustomerDBEntity> allChatPartners = new HashSet<>();
//
//			allChatPartners.addAll(chatRepository.getChatSendersByCustomerId(customerId));
//			allChatPartners.addAll(chatRepository.getChatReceiversByCustomerId(customerId));
//
//			for (CustomerDBEntity nextPartner : allChatPartners)
//			{
//				//exclude a customer itself and hotelStaff from result
//				if(nextPartner.equals(customerEntity))// || nextPartner.isHotelStaff())
//				{
//					continue;
//				}
//				
////				if(!chatPartnerIsInSameHotel)
//				{
//					ChatMessageView nextLastMessage = chatRepository.getLastMessageByCustomerAndReceiverIds(customerId, nextPartner.getId());
//					
//					long partnerHotelId = customerService.getCustomerHotelId(nextPartner.getId());
//					
//					CustomerDTO chatPartnerDto = customerService.convertCustomerToDto(nextPartner, partnerHotelId);
//
//					boolean isPartnerInMyHotelWithFullCheckin = customerHotelId>0 && customerHotelId==chatPartnerDto.getHotelId() && checkinRepository.isFullCheckinForCustomerByHotelId(nextPartner.getId(), partnerHotelId, LocalDate.now());
//
//					chatPartnerDto.setInMyHotel(isPartnerInMyHotelWithFullCheckin);
//					
//					//TODO eugen: fill dto!!!
//					if (nextLastMessage != null)
//					{
//						Timestamp lastMessageTimestamp = nextLastMessage.getTimestamp();
//
//						String time = AppConfigProperties.getTimeFormatted(lastMessageTimestamp);
//
//						String message = nextLastMessage.getMessage();
//// Only Client can short the message, because it has to be encoded first. only then cuttet!!!					
//// 	message = message.length() > ControllerUtils.chatListMessageLength ? message.substring(0, ControllerUtils.chatListMessageLength-2) + "..." : message;
//
//						chatPartnerDto.setLastMessageToMe(message);//+ " (at " + time + ")");
//						chatPartnerDto.setLastMessageTimeToMe(lastMessageTimestamp.getTime());
//						chatPartnerDto.setChatWithMe(true);
//
//						//int unReadCounter = chatRepository.getNotReadMessageCountBySenderId(customerId, dto.getId());
//					}
//
//					allContactChatPartners.add(chatPartnerDto);
//				}
//
//			}
//			
//		}
//		
//		return allContactChatPartners;
//	}
//	
//	@Override
//	public Set<CustomerDTO> getAllNotChatPartners(long customerId, String filterCity, long filterHotelId, int pageNumber)
//	{
//		CustomerDBEntity customerEntity = customerService.getEntityById(customerId);
//
//		Set<CustomerDTO> allNotChatPartners = new HashSet<>();
//
//		if(customerEntity !=null)
//		{
//
//			//TODO Eugen: alles auf notActive Query umschreiben!!!
//			//TODO Eugen: bessere Query!!!
//			Set<CustomerDBEntity> chatPartners = new HashSet<>();
//
//			chatPartners.addAll(chatRepository.getChatSendersByCustomerId(customerId));
//			chatPartners.addAll(chatRepository.getChatReceiversByCustomerId(customerId));
//			
//			List<CustomerDBEntity> allCustomerEntities;
//			
//			if(filterHotelId>0)
//			{
//				allCustomerEntities = checkinRepository.getActiveCustomersByHotelId(filterHotelId, new Date());
//			}
//			else if(filterCity != null)
//			{
//				allCustomerEntities = checkinRepository.getActiveCustomersByHotelCity(filterCity, new Date());
//			}
//			else
//			{
//				int PAGE_SIZE = 100;
// 				PageRequest request =
//						PageRequest.of(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
//				
//				Page<CustomerDBEntity> customerPages = customerRepository.findByActive(true, request);
//
//				allCustomerEntities = customerPages.getContent();
//				
////				Page<Customer> allCustomersPage = customerRepository.findAllActiveByPage(new PageRequest(page, 20));
//				
//			}
//			
//			//### Remove requester from Set
//			if((filterCity != null || filterHotelId>0) && allCustomerEntities.contains(customerEntity))
//			{
//				allCustomerEntities.remove(customerEntity);
//			}
//
//			long customerHotelId = customerService.getCustomerHotelId(customerId);
//
//			Set<CustomerDBEntity> inSameHotelCustomerEntities = new HashSet<>();
//			
//			if(customerHotelId>0 && customerHotelId!=filterHotelId)
//			{
//				//customer is in Hotel, so we need filter here all hotel chat Partners
//				List<CheckinRootDomainEntity> inSameHotelCheckins = new ArrayList<>();
//				
//				if(AppConfigProperties.SHOW_ONLY_FULL_CHECKIN_USERS)
//				{
//					inSameHotelCheckins = checkinRepository.getActiveFullCheckinByHotelId(customerHotelId, new Date());
//				}
//				else {
//					inSameHotelCheckins = checkinRepository.getActiveByHotelId(customerHotelId, new Date());
//				}
//				
//				for (CheckinRootDomainEntity nextCheckin: inSameHotelCheckins)
//				{
//					inSameHotelCustomerEntities.add(nextCheckin.getCustomer());
//				}
//			}
//			else if(customerHotelId == filterHotelId)
//			{
//				//We have already pre select all in this hotel!!!
//				inSameHotelCustomerEntities.addAll(allCustomerEntities);
//			}
//
//			Set<CustomerDBEntity> notChatPartners = new HashSet<>(allCustomerEntities);
//			notChatPartners.removeAll(chatPartners);
//			
//			//TODO Eugen: why remove in same hotel customers????
////			notChatPartners.removeAll(inSameHotelCustomers);
//
//			if(notChatPartners.size()>50 && filterHotelId<=0)
//			{
//				Set<CustomerDBEntity> subList = new HashSet<>();
//
//				Iterator<CustomerDBEntity> it = notChatPartners.iterator();
//
//				int counter = 0;
//				while (it.hasNext() && counter<50)
//				{
//					subList.add(it.next());
//					counter++;
//				}
//
//				notChatPartners = subList;
//			}
//			
//			for (CustomerDBEntity nextNotPartner : notChatPartners)
//			{
//				//exclude a customer itself and hotelStaff from result
//				if(nextNotPartner.equals(customerEntity) || nextNotPartner.isHotelStaff())
//				{
//					continue;
//				}
//
//				//TODO Eugen: maybe not convert the whole dto?
//				long nextPartnerHotelId = customerService.getCustomerHotelId(nextNotPartner.getId());
//				
//				CustomerDTO notChatPartnerDto = customerService.convertCustomerToDto(nextNotPartner, nextPartnerHotelId);
//
//				boolean isPartnerInMyHotelWithFullCheckin = customerHotelId>0 && customerHotelId==notChatPartnerDto.getHotelId() && checkinRepository.isFullCheckinForCustomerByHotelId(notChatPartnerDto.getId(), notChatPartnerDto.getHotelId(), new Date());
//
//				notChatPartnerDto.setInMyHotel(isPartnerInMyHotelWithFullCheckin);
//						
//				allNotChatPartners.add(notChatPartnerDto);
//
//			}
//
//		}
//
//		return allNotChatPartners;
//	}
//		 
//}
