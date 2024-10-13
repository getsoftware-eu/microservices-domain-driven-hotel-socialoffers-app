package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.application.infrastructure.amqp;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.ChatMessageConsumeRequest;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.CustomerUpdateConsumeRequest;
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
public class ChatEventsConsumer
{
	private final ChatMessageService chatMessageService;
	private final ChatUserRepository chatUserRepository;
	/**
	 * Listen for customer system update
	 * @param customerUpdateRequest
	 */
	@RabbitListener(queues = "${rabbitmq.queue.customer.update}")
	public void consumeCustomerUpdateNotification(CustomerUpdateConsumeRequest customerUpdateRequest){
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
	
	@RabbitListener(queues = "${rabbitmq.queue.chat.request}")
	public void consumeCustomerUpdateNotification(ChatMessageConsumeRequest chatMessageRequest){
		log.info("Consumed {} from queue", chatMessageRequest);
		log.info(chatMessageRequest.customMsg());
		
		Optional<ChatMessageEntity> chatMsgOpt;
		
		if(chatMessageRequest.lastMessage())
		{
			chatMsgOpt = chatMessageService.getLastChatMessage(chatMessageRequest.fromCustomerId(), chatMessageRequest.toCustomerId());
		}
		
		
		
	}
}
