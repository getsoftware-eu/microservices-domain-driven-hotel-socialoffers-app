//package eu.getsoftware.hotelico.hotel.chat.service;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Service;
//
//import eu.getsoftware.hotelico.chat.dto.ChatCustomerDTO;
//import eu.getsoftware.hotelico.chat.dto.ChatMsgDTO;
//import eu.getsoftware.hotelico.hotel.chat.model.ChatCustomerEntity;
//import eu.getsoftware.hotelico.hotel.chat.model.ChatMsgEntity;
//import eu.getsoftware.hotelico.hotel.dto.ChatMessageDTO;
//import eu.getsoftware.hotelico.hotel.dto.CustomerDTO;
//import eu.getsoftware.hotelico.hotel.chat.dto.ChatCustomerDTO;
//import eu.getsoftware.hotelico.model.ChatMessage;
//import eu.getsoftware.hotelico.model.Customer;
//import eu.getsoftware.hotelico.model.CustomerHotelCheckin;
//import eu.getsoftware.hotelico.model.HotelActivity;
//import eu.getsoftware.hotelico.hotel.repository.repository.ChatRepository;
//import eu.getsoftware.hotelico.hotel.repository.repository.CheckinRepository;
//import eu.getsoftware.hotelico.hotel.repository.CustomerRepository;
//import eu.getsoftware.hotelico.service.CacheService;
//import eu.getsoftware.hotelico.service.CustomerService;
//import eu.getsoftware.hotelico.service.HotelService;
//import eu.getsoftware.hotelico.service.NotificationService;
//import eu.getsoftware.hotelico.utils.ControllerUtils;
//import eu.getsoftware.hotelico.utils.HotelEvent;
//import lombok.AllArgsConstructor;
//
///**
// * Created by Eugen on 16.07.2015.
// * Do I need interface in a small microservice? 
// */
//@AllArgsConstructor //dont need injection construction
//@Service
//public class ChatServiceImpl implements ChatService
//{
//	private CustomerService customerService;		
//	private CacheService cacheService;		
//	private NotificationService notificationService;	
//	private HotelService hotelService;
//	private ChatRepository chatRepository;	
//	private CustomerRepository customerRepository;	
//	private CheckinRepository checkinRepository;
//	private SimpMessagingTemplate simpMessagingTemplate;
//	private ModelMapper modelMapper;
//	
//	public List<ChatMsgEntity> getChatMessages()
//	{
//		return null;
//	}
//	
//	public List<ChatMsgDTO> getMessagesByCustomerId(long customerId, long receiverId)
//	{
//		List<ChatMsgEntity> list =  chatRepository.getMessagesByCustomerIds(customerId, receiverId);
//		List<ChatMsgDTO> out = new ArrayList<ChatMsgDTO>();
//		for (ChatMsgEntity nextMessage : list) {
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
//	@Override
//	public ChatMsgDTO convertMessageToDto(ChatMsgEntity nextMessage)
//	{
//		ChatMsgDTO nextDto = nextMessage!=null? modelMapper.map(nextMessage, ChatMsgDTO.class) : null;
//		
//		nextDto = fillDtoFromMessage(nextDto, nextMessage);
//		
//		return nextDto;
//	}
//
//	@Override
//	public ChatMsgDTO addUpdateChatMessage(ChatMsgDTO chatMessageDto)
//	{
//		List<ChatMsgEntity> existMessages = chatRepository.getMessageByCustomerAndInitId(chatMessageDto.getSenderId(), chatMessageDto.getInitId());
//
//		if(existMessages.isEmpty())
//		{
//			//Add Message if it is new
//			chatMessageDto = addChatMessage(chatMessageDto);
//		}
//		else{
//			//If message already exists, maybe deliever-status was changed
//			for (ChatMsgEntity nextExistingMessage :existMessages)
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
//		ChatCustomerEntity sender = customerService.getEntityById(chatMessageDto.getSenderId());
//		ChatCustomerEntity receiver = customerService.getEntityById(chatMessageDto.getReceiverId());
//
//		ChatMsgEntity newMessage = new ChatMessage(chatMessageDto.getMessage(), sender, receiver);
//		newMessage.setInitId(chatMessageDto.getInitId());
//		
//		if (chatMessageDto.getCreationTime()<=0)
//		{
//			chatMessageDto.setCreationTime(new Date().getTime());
//		}
//		
//		newMessage.setTimestamp(new Timestamp(chatMessageDto.getCreationTime()));
//		
//		newMessage.setSpecialChatContent(chatMessageDto.getSpecialContent().toString());
//		
//		chatRepository.saveAndFlush(newMessage);
//		
//		cacheService.setLastMessageBetweenCustomers(newMessage);
//		cacheService.updateUnreadMessagesToCustomer(newMessage);
//	
//		chatMessageDto = fillDtoFromMessage(chatMessageDto, newMessage);
//		
//		if(ControllerUtils.CHAT_DELIEVER_INDIVIDUAL)
//		{
//			simpMessagingTemplate.convertAndSend(ControllerUtils.SOCKET_CHAT_TOPIC + newMessage.getSender().getId() + "", chatMessageDto);
//			simpMessagingTemplate.convertAndSend(ControllerUtils.SOCKET_CHAT_TOPIC + newMessage.getReceiver().getId() + "", chatMessageDto);			
//		}
//
//		notificationService.createAndSendNotification(sender.getId(), HotelEvent.EVENT_CHAT_SEND_MESSAGE);
//		notificationService.createAndSendPushNotification_Chat(receiver.getId(), HotelEvent.EVENT_CHAT_NEW_MESSAGE, sender.getId(), newMessage.getMessage());
//		
//		return chatMessageDto;
//	}
//
//	private ChatMsgDTO fillDtoFromMessage(ChatMsgDTO chatDto, ChatMsgEntity chatMessage)
//	{
//		if(chatMessage==null || chatDto==null)
//		{
//			return null;	
//		}
//		
//		chatDto.setSenderId(chatMessage.getSender().getId());
//
//		chatDto.setReceiverId(chatMessage.getReceiver().getId());
//
//		chatDto.setHotelStaff(chatMessage.getSender().isHotelStaff());
//
//		chatDto.setTimestamp(chatMessage.getTimestamp());
//		
//		chatDto.setCreationTime(chatMessage.getTimestamp().getTime());
//
//		chatDto.setId(chatMessage.getId());
//
//		chatDto.setSeenByReceiver(chatMessage.isSeenByReceiver());
//
//		if(chatMessage.getSpecialChatContent()!=null)
//		{
//			String specContent = chatMessage.getSpecialChatContent();
//
//			String[] split = specContent.replace("{","").replace("}", "").split("=");
//
//			if(split.length>1)
//			{
//				String key = split[0];
//				String value = split[1];
//
//				chatDto.getSpecialContent().put(key, value);
//
//				if(key.equalsIgnoreCase("activityId"))
//				{
//					long activityId = Long.parseLong(value);
//
//					HotelActivity activity = hotelService.getActivityByIdOrInitId((int)activityId, activityId);
//
//					if(activity!=null)
//					{
//						chatDto.getSpecialContent().put("name", activity.getTitle());
//						chatDto.getSpecialContent().put("hotelId", activity.getHotel().getId()+"");
//						chatDto.getSpecialContent().put("pictureUrl", activity.getPreviewPictureUrl()!=null?activity.getPreviewPictureUrl() : ControllerUtils.PREVIEW_ACTIVITY_NOT_AVAILABLE_URL );
//					}
//
//				}
//				else
//				if(key.equalsIgnoreCase("customerId"))
//				{
//					long customerId = Integer.parseInt(value);
//
//					ChatCustomerEntity customer = customerRepository.getOne(customerId);
//
//					if(customer!=null)
//					{
//						chatDto.getSpecialContent().put("name", customer.getFirstName() + " " + customer.getLastName());
//						chatDto.getSpecialContent().put("pictureUrl", customerService.getCustomerAvatarUrl(customer));
//					}
//
//				}
//			}
//		}
//		
//		return chatDto; 
//	}
//
//	@Override
//	public ChatMsgDTO getChatMessageById(long chatMessageId)
//	{
//		return null;
//	}
//
//	@Override
//	public ChatMsgDTO getChatMessageBySender(long senderId)
//	{
//		return null;
//	}
//
//	@Override
//	public ChatMsgDTO getChatMessageByReceiver(long receiverId)
//	{
//		return null;
//	}
//
//	@Override
//	public ChatMsgDTO getChatMessageByDateRange(Date startDate, Date endDate)
//	{
//		return null;
//	}
//
//	@Override
//	public void deleteChatMessage(ChatMsgDTO chatMessageDto)
//	{
//
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
//			List<ChatMsgEntity> messages = chatRepository.getMessageByCustomerAndInitId(customerId, nextMessageIdLong);
//
//			if (messages.isEmpty())
//			{
//				continue;
//			}
//			
//			for(ChatMsgEntity message: messages)
//			{
//				message.setDelieveredToReceiver(true);
//				message.setSeenByReceiver(true);
//				chatRepository.save(message);
//
//				//Notificate Sender about SEEN message
//				simpMessagingTemplate.convertAndSend(ControllerUtils.SOCKET_CHAT_TOPIC + message.getSender().getId() + "", convertMessageToDto(message));
//
//				cacheService.markMessageRead(message);
//				cacheService.markLastMessageBetweenCustomers(message);
//			}
//		}
//		
//		
//	}
//	
//	@Override
//	public void markChatRead(long customerId, long senderId, long maxSeenChatMessageId)
//	{
//		List<ChatMsgEntity> unreadMessagesBySender = chatRepository.getUnreadChatMessagesForCustomerBySender(customerId, senderId);
//
//		ChatMsgEntity latestUnreadMessage = null;
//		
//		for (ChatMsgEntity nextReadMsg: unreadMessagesBySender)
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
//			cacheService.markLastMessageBetweenCustomers(latestUnreadMessage);
//
//			//Notificate Sender about last SEEN message
//			simpMessagingTemplate.convertAndSend(ControllerUtils.SOCKET_CHAT_TOPIC + latestUnreadMessage.getSender().getId() + "", convertMessageToDto(latestUnreadMessage));
//		}
//
//		cacheService.markChatRead(customerId, senderId);
//
//	}
//	
//	@Override
//	public Set<CustomerDTO> getNotHotelChatPartners(long requesterId, String filterCity, long filterHotelId){
//		
//		ChatCustomerEntity customer = customerService.getEntityById(requesterId);
//		
//		Set<CustomerDTO> notHotelChatPartners = new HashSet<>();
//		
//		if(customer!=null)
//		{
//			long customerHotelId = customerService.getCustomerHotelId(requesterId);
//			
//			long virtualHotelId = cacheService.getInitHotelId();
//			
//			boolean customerIsInHotel = customerHotelId>0 && customerHotelId!=virtualHotelId;
//			
//			Set<ChatCustomerEntity> chatPartners = new HashSet<>();
//
//			chatPartners.addAll(chatRepository.getChatSendersByCustomerId(requesterId));
//			chatPartners.addAll(chatRepository.getChatReceiversByCustomerId(requesterId));
//
//			for (ChatCustomerEntity nextPartner : chatPartners)
//			{
//				//exclude a customer itself and hotelStaff from result
//				if(nextPartner.equals(customer) || nextPartner.isHotelStaff())
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
//					ChatMsgEntity nextLastMessage = chatRepository.getLastMessageByCustomerAndReceiverIds(requesterId, nextPartner.getId());
//					
////					CustomerDto chatPartnerDto = modelMapper.map(nextPartner, CustomerDto.class);
////					chatPartnerDto = customerService.fillDtoFromCustomer(nextPartner, chatPartnerDto);
//					ChatCustomerDTO chatPartnerDto = customerService.convertCustomerToDto(nextPartner, partnerHotelId);
//
//					boolean isPartnerInMyHotelWithFullCheckin = customerHotelId>0 && customerHotelId==chatPartnerDto.getHotelId() && checkinRepository.isFullCheckinForCustomerByHotelId(chatPartnerDto.getId(), chatPartnerDto.getHotelId(), new Date());
//
//					chatPartnerDto.setInMyHotel(isPartnerInMyHotelWithFullCheckin);
//
//
//					//TODO eugen: fill dto!!!
//					if (nextLastMessage != null)
//					{
//						Timestamp lastMessageTimestamp = nextLastMessage.getTimestamp();
//
//						String time = ControllerUtils.getTimeFormatted(lastMessageTimestamp);
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
//		ChatCustomerEntity customer = customerService.getEntityById(customerId);
//		
//		Set<CustomerDTO> allContactChatPartners = new HashSet<>();
//		
//		if(customer!=null)
//		{
//			long customerHotelId = customerService.getCustomerHotelId(customerId);
//			
////			int virtualHotelId = cacheService.getInitHotelId();
//			
//			Set<ChatCustomerEntity> allChatPartners = new HashSet<>();
//
//			allChatPartners.addAll(chatRepository.getChatSendersByCustomerId(customerId));
//			allChatPartners.addAll(chatRepository.getChatReceiversByCustomerId(customerId));
//
//			for (ChatCustomerEntity nextPartner : allChatPartners)
//			{
//				//exclude a customer itself and hotelStaff from result
//				if(nextPartner.equals(customer))// || nextPartner.isHotelStaff())
//				{
//					continue;
//				}
//
//				if(nextPartner.isHotelStaff())
//				{
//					int f = 6;
//				}
//				
////				if(!chatPartnerIsInSameHotel)
//				{
//					ChatMsgEntity nextLastMessage = chatRepository.getLastMessageByCustomerAndReceiverIds(customerId, nextPartner.getId());
//					
//					long partnerHotelId = customerService.getCustomerHotelId(nextPartner.getId());
//					
//					ChatCustomerDTO chatPartnerDto = customerService.convertCustomerToDto(nextPartner, partnerHotelId);
//
//					boolean isPartnerInMyHotelWithFullCheckin = customerHotelId>0 && customerHotelId==chatPartnerDto.getHotelId() && checkinRepository.isFullCheckinForCustomerByHotelId(nextPartner.getId(), partnerHotelId, new Date());
//
//					chatPartnerDto.setInMyHotel(isPartnerInMyHotelWithFullCheckin);
//					
//					//TODO eugen: fill dto!!!
//					if (nextLastMessage != null)
//					{
//						Timestamp lastMessageTimestamp = nextLastMessage.getTimestamp();
//
//						String time = ControllerUtils.getTimeFormatted(lastMessageTimestamp);
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
//		ChatCustomerEntity customer = customerService.getEntityById(customerId);
//
//		Set<CustomerDTO> allNotChatPartners = new HashSet<>();
//
//		if(customer!=null)
//		{
//
//			//TODO Eugen: alles auf notActive Query umschreiben!!!
//			//TODO Eugen: bessere Query!!!
//			Set<ChatCustomerEntity> chatPartners = new HashSet<>();
//
//			chatPartners.addAll(chatRepository.getChatSendersByCustomerId(customerId));
//			chatPartners.addAll(chatRepository.getChatReceiversByCustomerId(customerId));
//			
//			List<ChatCustomerEntity> allCustomers = new ArrayList<>();
//			
//			if(filterHotelId>0)
//			{
//				allCustomers = checkinRepository.getActiveCustomersByHotelId(filterHotelId, new Date());
//			}
//			else if(filterCity != null)
//			{
//				allCustomers = checkinRepository.getActiveCustomersByHotelCity(filterCity, new Date());
//			}
//			else
//			{
//				int PAGE_SIZE = 100;
// 				PageRequest request =
//						new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
//				
//				Page<ChatCustomerEntity> customerPages = customerRepository.findByActive(true, request);
//
//				allCustomers = customerPages.getContent();
//				
////				Page<ChatCustomerEntity> allCustomersPage = customerRepository.findAllActiveByPage(new PageRequest(page, 20));
//				
//			}
//			
//			//### Remove requester from Set
//			if((filterCity != null || filterHotelId>0) && allCustomers.contains(customer))
//			{
//				allCustomers.remove(customer);
//			}
//
//			long customerHotelId = customerService.getCustomerHotelId(customerId);
//
//			Set<ChatCustomerEntity> inSameHotelCustomers = new HashSet<>();
//			
//			if(customerHotelId>0 && customerHotelId!=filterHotelId)
//			{
//				//customer is in Hotel, so we need filter here all hotel chat Partners
//				List<CustomerHotelCheckin> inSameHotelCheckins = new ArrayList<>();
//				
//				if(ControllerUtils.SHOW_ONLY_FULL_CHECKIN_USERS)
//				{
//					inSameHotelCheckins = checkinRepository.getActiveFullCheckinByHotelId(customerHotelId, new Date());
//				}
//				else {
//					inSameHotelCheckins = checkinRepository.getActiveByHotelId(customerHotelId, new Date());
//				}
//				
//				for (CustomerHotelCheckin nextCheckin: inSameHotelCheckins)
//				{
//					inSameHotelCustomers.add(nextCheckin.getCustomer());
//				}
//			}
//			else if(customerHotelId == filterHotelId)
//			{
//				//We have already pre select all in this hotel!!!
//				inSameHotelCustomers.addAll(allCustomers);
//			}
//
//			Set<ChatCustomerEntity> notChatPartners = new HashSet<>(allCustomers);
//			notChatPartners.removeAll(chatPartners);
//			
//			//TODO Eugen: why remove in same hotel customers????
////			notChatPartners.removeAll(inSameHotelCustomers);
//
//			if(notChatPartners.size()>50 && filterHotelId<=0)
//			{
//				Set<ChatCustomerEntity> subList = new HashSet<>();
//
//				Iterator<ChatCustomerEntity> it = notChatPartners.iterator();
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
//			for (ChatCustomerEntity nextNotPartner : notChatPartners)
//			{
//				//exclude a customer itself and hotelStaff from result
//				if(nextNotPartner.equals(customer) || nextNotPartner.isHotelStaff())
//				{
//					continue;
//				}
//
//				//TODO Eugen: maybe not convert the whole dto?
//				long nextPartnerHotelId = customerService.getCustomerHotelId(nextNotPartner.getId());
//				
//				ChatCustomerDTO notChatPartnerDto = customerService.convertCustomerToDto(nextNotPartner, nextPartnerHotelId);
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
