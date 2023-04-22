package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.infrastructure.service;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import eu.getsoftware.hotelico.amqp.RabbitMQMessageProducer;
import eu.getsoftware.hotelico.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.clients.infrastructure.hotel.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.infrastructure.notification.CustomerUpdateRequest;
import eu.getsoftware.hotelico.clients.infrastructure.notification.NotificationRequest;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.domain.model.ChatMessageEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.domain.model.ChatUserEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.infrastructure.repository.ChatUserRepository;
import eu.getsoftware.hotelico.infrastructure.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatService
{
	private final NotificationService notificationService;
	private final RabbitMQMessageProducer rabbitMQMessageProducer;
	private final ChatUserRepository chatUserRepository;
	
	public ChatService(NotificationService notificationService, RabbitMQMessageProducer rabbitMQMessageProducer, ChatUserRepository chatUserRepository)
	{
		this.notificationService = notificationService;
		this.rabbitMQMessageProducer = rabbitMQMessageProducer;
		this.chatUserRepository = chatUserRepository;
	}
	
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
	
	/**
	 * Listen for customer system update
	 * @param customerUpdateRequest
	 */
	@RabbitListener(queues = "${rabbitmq.queue.customer.update}")
	public void consumeNotification(CustomerUpdateRequest customerUpdateRequest){
		log.info("Consumed {} from queue", customerUpdateRequest);
		log.info(customerUpdateRequest.message());
		
		Optional<ChatUserEntity> updatedChatUserOptional = chatUserRepository.findById(customerUpdateRequest.customerId());
		
		ChatUserEntity entity;
		
		if(updatedChatUserOptional.isEmpty())
		{
			entity = new ChatUserEntity(customerUpdateRequest.customerId());
			entity.setFirstName(customerUpdateRequest.customerName());
		}
		else {
			ChatUserEntity updatedChatUser = updatedChatUserOptional.get();
			entity = chatUserRepository.findByUserId(updatedChatUser.getId());
			
			entity.setEmail(updatedChatUser.getEmail());
			entity.setFirstName(updatedChatUser.getFirstName());
		}
		
		ChatUserEntity persistedEntity = chatUserRepository.save(entity);
		
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
		NotificationRequest myNotification = new NotificationRequest(toCustomerId, toCustomerName, message);
		notificationService.send(myNotification);
	}
	
	
}
