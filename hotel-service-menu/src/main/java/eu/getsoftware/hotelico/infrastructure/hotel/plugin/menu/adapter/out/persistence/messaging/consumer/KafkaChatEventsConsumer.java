package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.messaging.consumer;

import eu.getsoftware.hotelico.clients.api.application.infrastructure.amqpConsumeNotification.ChatMessageCommand;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.HotelUpdateEventMessagePayload;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.domainMessage.DomainMessage;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.messaging.service.MenuMessageService;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.model.MenuUserMappedEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.repository.MenuUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class KafkaChatEventsConsumer {

    private final MenuMessageService menuMessageService;
    private final MenuUserRepository menuUserRepository;

    /**
     * Listen for customer system updates from Kafka topic.
     * @param record Kafka consumer record containing the message.
     */
    @KafkaListener(topics = "kafka.topic.customer.update", groupId = "kafka.consumer.group-id")
    public void consumeCustomerUpdateNotification(ConsumerRecord<String, DomainMessage<HotelUpdateEventMessagePayload>> record) {
        DomainMessage<HotelUpdateEventMessagePayload> customerUpdateRequest = record.value();
        log.info("Consumed {} from topic", customerUpdateRequest);
        
        HotelUpdateEventMessagePayload payload = customerUpdateRequest.getPayload();
        log.info(payload.getMessage());

        //TODO ProcessManagerService that updates Domain-Layer from external Event!!!
        Optional<MenuUserMappedEntity> updatedChatUserOptional = menuUserRepository.findById(payload.getMessageId());

        MenuUserMappedEntity entity;

        if (updatedChatUserOptional.isEmpty()) {
            entity = new MenuUserMappedEntity(payload.getCustomerDomainId());
//            entity.setFirstName(payload.getSenderName());
        } else {
            MenuUserMappedEntity updatedChatUser = updatedChatUserOptional.get();
            entity = menuUserRepository.findByUserId(updatedChatUser.getId());

            entity.setFirstName(updatedChatUser.getFirstName());
        }

        MenuUserMappedEntity persistedEntity = menuUserRepository.save(entity);
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

//        Optional<MenuIOrderMappedEntity> chatMsgOpt;
//
//        if (chatMessageRequest.lastMessage()) {
//            chatMsgOpt = menuMessageService.getLastChatMessage(chatMessageRequest.fromCustomerId(), chatMessageRequest.toCustomerId());
//        }

        // Обработка chatMsgOpt или других данных
    }
}
