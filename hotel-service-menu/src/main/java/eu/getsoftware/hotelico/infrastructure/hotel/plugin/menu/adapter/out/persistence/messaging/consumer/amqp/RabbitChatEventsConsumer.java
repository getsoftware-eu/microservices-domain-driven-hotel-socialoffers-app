package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.messaging.consumer.amqp;//package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.messaging.consumer.amqp;
//
//import eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.HotelUpdateEventMessagePayload;
//import eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.domainMessage.DomainMessage;
//import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatMessageMappedEntity;
//import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatUserMappedEntity;
//import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.outPortServiceImpl.ChatMessageService;
//import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.repository.ChatUserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//
//import java.util.Optional;
//
//@Slf4j
//@RequiredArgsConstructor
//public class RabbitChatEventsConsumer
//{
//	private final ChatMessageService chatMessageService;
//	private final ChatUserRepository chatUserRepository;
//	/**
//	 * Listen for customer system update
//	 * @param customerUpdateRequest
//	 */
//	@RabbitListener(queues = "${rabbitmq.queue.customer.update}")
//	public void consumeCustomerUpdateNotification(DomainMessage<HotelUpdateEventMessagePayload> customerUpdateRequest){
//		log.info("Consumed {} from queue", customerUpdateRequest);
//		log.info(customerUpdateRequest.getPayload().getMessage());
//		
//		Optional<ChatUserMappedEntity> updatedChatUserOptional = chatUserRepository.findById(customerUpdateRequest.getPayload().getMessageId());
//		
//		ChatUserMappedEntity entity;
//		
//		if(updatedChatUserOptional.isEmpty())
//		{
//			entity = new ChatUserMappedEntity(customerUpdateRequest.getPayload().getSenderId());
//			entity.setFirstName(customerUpdateRequest.getPayload().getSenderName());
//		}
//		else {
//			ChatUserMappedEntity updatedChatUser = updatedChatUserOptional.get();
//			entity = chatUserRepository.findByUserId(updatedChatUser.getId());
//			
////			entity.setEmail(updatedChatUser.getEmail());
//			entity.setFirstName(updatedChatUser.getFirstName());
//		}
//		
//		ChatUserMappedEntity persistedEntity = chatUserRepository.save(entity);
//		
//	}
//	
//	@RabbitListener(queues = "${rabbitmq.queue.chat.request}")
//	public void consumeCustomerUpdateNotification(ChatMessageCommand chatMessageRequest){
//		log.info("Consumed {} from queue", chatMessageRequest);
//		log.info(chatMessageRequest.customMsg());
//
//		Optional<ChatMessageMappedEntity> chatMsgOpt;
//
//		if(chatMessageRequest.lastMessage())
//		{
//			chatMsgOpt = chatMessageService.getLastChatMessage(chatMessageRequest.fromCustomerId(), chatMessageRequest.toCustomerId());
//		}
//
//
//
//	}
//}
