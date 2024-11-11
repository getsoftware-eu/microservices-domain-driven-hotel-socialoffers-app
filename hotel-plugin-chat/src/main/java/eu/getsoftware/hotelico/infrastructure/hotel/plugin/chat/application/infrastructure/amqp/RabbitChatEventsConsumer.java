package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.application.infrastructure.amqp;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.ChatMessageCommand;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainLayerPayload.ChatSendEventMessagePayload;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessage;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatMessageEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatUserEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.outPortServiceImpl.ChatMessageService;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class RabbitChatEventsConsumer
{
	private final ChatMessageService chatMessageService;
	private final ChatUserRepository chatUserRepository;
	/**
	 * Listen for customer system update
	 * @param customerUpdateRequest
	 */
	@RabbitListener(queues = "${rabbitmq.queue.customer.update}")
	public void consumeCustomerUpdateNotification(DomainMessage<ChatSendEventMessagePayload> customerUpdateRequest){
		log.info("Consumed {} from queue", customerUpdateRequest);
		log.info(customerUpdateRequest.getPayload().getMessage());
		
		Optional<ChatUserEntity> updatedChatUserOptional = chatUserRepository.findById(customerUpdateRequest.getPayload().getMessageId());
		
		ChatUserEntity entity;
		
		if(updatedChatUserOptional.isEmpty())
		{
			entity = new ChatUserEntity(customerUpdateRequest.getPayload().getSenderId());
			entity.setFirstName(customerUpdateRequest.getPayload().getSenderName());
		}
		else {
			ChatUserEntity updatedChatUser = updatedChatUserOptional.get();
			entity = chatUserRepository.findByUserId(updatedChatUser.getId());
			
//			entity.setEmail(updatedChatUser.getEmail());
			entity.setFirstName(updatedChatUser.getFirstName());
		}
		
		ChatUserEntity persistedEntity = chatUserRepository.save(entity);
		
	}
	
	@RabbitListener(queues = "${rabbitmq.queue.chat.request}")
	public void consumeCustomerUpdateNotification(ChatMessageCommand chatMessageRequest){
		log.info("Consumed {} from queue", chatMessageRequest);
		log.info(chatMessageRequest.customMsg());
		
		Optional<ChatMessageEntity> chatMsgOpt;
		
		if(chatMessageRequest.lastMessage())
		{
			chatMsgOpt = chatMessageService.getLastChatMessage(chatMessageRequest.fromCustomerId(), chatMessageRequest.toCustomerId());
		}
		
		
		
	}
}
