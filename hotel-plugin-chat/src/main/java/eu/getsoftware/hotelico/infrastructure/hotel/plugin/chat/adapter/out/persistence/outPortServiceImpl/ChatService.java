package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.api.amqp.producer.RabbitMQMessageProducer;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.NotificationConsumeRequest;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.clients.api.infrastructure.notification.NotificationService;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatMessageEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatUserEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.repository.ChatMessageRepository;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.repository.ChatUserRepository;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.application.port.out.IChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService implements IChatService
{
	private final NotificationService notificationService;
	private final RabbitMQMessageProducer rabbitMQMessageProducer;
	private final ChatMessageRepository chatMessageRepository;
	private final ChatUserRepository chatUserRepository;
	
	public ChatMessageEntity convertFromDTO(ChatMsgDTO msgDTO)
	{
		throw new UnsupportedOperationException("not implemented");
		
//		return new ChatMessageEntity();
	}	
	
	public ChatUserEntity convertFromDTO(CustomerDTO msgDTO)
	{
		throw new UnsupportedOperationException("not implemented");
		
//		return new ChatUserEntity();
	}
	
	public ChatMsgDTO convertToDTO(ChatMessageEntity entity)
	{
		throw new UnsupportedOperationException("not implemented");
	}
		
	public CustomerDTO convertToDTO(ChatUserEntity entity)
	{
		throw new UnsupportedOperationException("not implemented");
	}
	
	public ChatMessageEntity createChatMessageFromDTO(ChatMsgDTO msgDTO)
	{
		ChatMessageEntity messageEntity = this.convertFromDTO(msgDTO);
		
		ChatMessageEntity updateEntity = chatMessageRepository.save(messageEntity);
		return updateEntity;
	}
	
	
	
	/**
	 *  inform other user about a new chat message via internal notification module!
	 *  
	 *  Method 3: notification-service persisting every notification in own DB!
	 * 	via my 'notification'-module (that uses 'amqp'-module)
	 *
	 * 	// ACHTUNG: but not menuItem, for this you have to create extra common Entitý! and persist in notification-module 
	 * 	BUT WHERE is exchange = "internal.exchange" and routingKey ?? ANSWER: it is in Notification Module properties  !!!!		
	 * @param toCustomerId
	 * @param toCustomerName
	 * @param message
	 */
	private void sendViaCustomerModuleWithConvertAndPersist(long toCustomerId, String toCustomerName, String message)
	{
		NotificationConsumeRequest myNotification = new NotificationConsumeRequest(123, toCustomerName, toCustomerId, toCustomerName, message);
		notificationService.persistConsumedNotification( myNotification);
	}
	
	public ChatUserEntity updateCustomerFromDTO(CustomerDTO customerDTO)
	{
		ChatUserEntity userEntity = this.convertFromDTO(customerDTO);
		
		return chatUserRepository.save(userEntity);
	}
}
