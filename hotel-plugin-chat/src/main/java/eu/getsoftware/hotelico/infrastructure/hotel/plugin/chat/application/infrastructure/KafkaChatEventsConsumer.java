package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.application.infrastructure;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.ChatMessageCommand;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.HotelUpdateEventMessagePayload;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.domainMessage.DomainMessage;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatMessageMappedEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatUserMappedEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.outPortServiceImpl.ChatMessageService;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class KafkaChatEventsConsumer {

    private final ChatMessageService chatMessageService;
    private final ChatUserRepository chatUserRepository;

    /**
     * Listen for customer system updates from Kafka topic.
     * @param record Kafka consumer record containing the message.
     */
    @KafkaListener(topics = "${kafka.topic.customer.update}", groupId = "${kafka.consumer.group-id}")
    public void consumeCustomerUpdateNotification(ConsumerRecord<String, DomainMessage<HotelUpdateEventMessagePayload>> record) {
        DomainMessage<HotelUpdateEventMessagePayload> customerUpdateRequest = record.value();
        log.info("Consumed {} from topic", customerUpdateRequest);
        
        HotelUpdateEventMessagePayload payload = customerUpdateRequest.getPayload();
        log.info(payload.getMessage());

        //TODO ProcessManagerService that updates Domain-Layer from external Event!!!
        Optional<ChatUserMappedEntity> updatedChatUserOptional = chatUserRepository.findById(payload.getMessageId());

        ChatUserMappedEntity entity;

        if (updatedChatUserOptional.isEmpty()) {
            entity = new ChatUserMappedEntity(payload.getCustomerDomainId());
//            entity.setFirstName(payload.getSenderName());
        } else {
            ChatUserMappedEntity updatedChatUser = updatedChatUserOptional.get();
            entity = chatUserRepository.findByUserId(updatedChatUser.getId());

            entity.setFirstName(updatedChatUser.getFirstName());
        }

        ChatUserMappedEntity persistedEntity = chatUserRepository.save(entity);
    }

    /**
     * Listen for chat messages from Kafka topic.
     * @param record Kafka consumer record containing the message.
     */
    @KafkaListener(topics = "${kafka.topic.chat.request}", groupId = "${kafka.consumer.group-id}")
    public void consumeChatMessageNotification(ConsumerRecord<String, ChatMessageCommand> record) {
        ChatMessageCommand chatMessageRequest = record.value();
        log.info("Consumed {} from topic", chatMessageRequest);
        log.info(chatMessageRequest.customMsg());

        Optional<ChatMessageMappedEntity> chatMsgOpt;

        if (chatMessageRequest.lastMessage()) {
            chatMsgOpt = chatMessageService.getLastChatMessage(chatMessageRequest.fromCustomerId(), chatMessageRequest.toCustomerId());
        }

        // Обработка chatMsgOpt или других данных
    }
}
