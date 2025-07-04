package eu.getsoftware.hotelico.chat.adapter.out.messaging.consumer

import eu.getsoftware.hotelico.chat.adapter.out.persistence.model.ChatMessageMappedEntity
import eu.getsoftware.hotelico.chat.adapter.out.persistence.model.ChatUserMappedEntity
import eu.getsoftware.hotelico.chat.adapter.out.persistence.outPortServiceImpl.ChatMessageService
import eu.getsoftware.hotelico.chat.adapter.out.persistence.repository.ChatUserRepository
import eu.getsoftware.hotelico.clients.api.application.infrastructure.amqpConsumeNotification.ChatMessageCommand
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.HotelUpdateEventMessagePayload
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage.DomainMessage
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {}

//@Slf4j
//@RequiredArgsConstructor
@Component
internal class KafkaChatEventsConsumer (
    private val chatMessageService: ChatMessageService,
    private val chatUserRepository: ChatUserRepository
){

    /**
     * Listen for customer system updates from Kafka topic.
     * @param record Kafka consumer record containing the message.
     */
    @KafkaListener(topics = ["kafka.topic.customer.update"], groupId = "kafka.consumer.group-id")
    fun consumeCustomerUpdateNotification(record: ConsumerRecord<String, DomainMessage<HotelUpdateEventMessagePayload>>) {
        val customerUpdateRequest = record.value()
        log.info("Consumed from topic: {}", mapOf("event" to "consumeCustomerUpdateNotification", "customerUpdateRequest" to customerUpdateRequest ))

        val payload = customerUpdateRequest.payload
        log.info(payload.message)

        //TODO ProcessManagerService that updates Domain-Layer from external Event!!!
        val updatedChatUserOptional = chatUserRepository.findById(payload.messageId)

        val entity: ChatUserMappedEntity

        if (updatedChatUserOptional.isEmpty) {
            entity = ChatUserMappedEntity(payload.customerDomainId)
            //            entity.setFirstName(payload.getSenderName());
        } else {
            val updatedChatUser = updatedChatUserOptional.get()
            entity = chatUserRepository.findByUserDomainId(updatedChatUser.getUserDomainId())

            entity.setFirstName(updatedChatUser.getFirstName())
        }

        chatUserRepository.save(entity)
    }

    /**
     * Listen for chat messages from Kafka topic.
     * @param record Kafka consumer record containing the message.
     */
    @KafkaListener(topics = ["kafka.topic.chat.request"], groupId = "kafka.consumer.group-id")
    fun consumeChatMessageNotification(record: ConsumerRecord<String, ChatMessageCommand>) {
        val chatMessageRequest = record.value()
        
        log.info("Consumed from topic: {}", mapOf("event" to "consumeChatMessageNotification", "chatMessageRequest" to chatMessageRequest, "customMsg" to chatMessageRequest.customMsg ))

        val chatMsgOpt: ChatMessageMappedEntity? 

        if (chatMessageRequest.lastMessage) {
            chatMsgOpt = chatMessageService.getLastChatMessage(
                chatMessageRequest.fromCustomerId,
                chatMessageRequest.toCustomerId
            )
        }

        // Обработка chatMsgOpt или других данных
    }
}
