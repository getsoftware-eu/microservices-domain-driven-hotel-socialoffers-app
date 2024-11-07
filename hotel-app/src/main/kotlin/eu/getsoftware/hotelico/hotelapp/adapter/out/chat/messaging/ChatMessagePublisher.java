package eu.getsoftware.hotelico.hotelapp.adapter.out.chat.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessage;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessagePayload;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.hotelapp.adapter.out.service.messaging.KafkaMessagePublisher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.springframework.stereotype.Component;

import static eu.getsoftware.hotelico.hotelapp.adapter.out.chat.messaging.MessageStatus.QUEUED;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatMessagePublisher {

    private final KafkaMessagePublisher kafkaMessagePublisher;

    public void publishChatSentEvent(ChatMsgDTO chatMsgDTO){
        publishMessage("chat.message.sent.event", chatMsgDTO);
    }

    private void publishMessage(String messageType, ChatMsgDTO chatMsgDTO) {

        ChatSendEventMessagePayload eventPayload = ChatSendEventMessagePayload.builder()
                .messageId(chatMsgDTO.initId())
                .message(chatMsgDTO.message())
                .status(QUEUED)
                .build();

//        DomainMessage<?> eventMessage = domainMessageFactory
//                .prepareDomainMessageForType(messageType)
//                .withEntity(checkinDTO)
//                .withEntityAndProjection(checkinEntity, CheckinDTO.class) //eu: create projection for json
//                .withCurrentTenant()
//                .withAdditionalProperty("data","test")
//                .build();

        DomainMessage<?> eventMessage = DomainMessage.builder(messageType)
                .tenantId(1L)
                .build(eventPayload);

        String messageKeyForKafkaPartition = String.valueOf(chatMsgDTO.receiverId());
        
        kafkaMessagePublisher.publishMessageToPartition(messageKeyForKafkaPartition, eventMessage);
        log.info("Published message {}", eventMessage);
    }

    /**
     * Payload have to be send in Queue in JSON - FORM
     */
    @AllArgsConstructor
    @Builder
    @Getter
    @JsonTypeName("chat-send-event")
    static class ChatSendEventMessagePayload extends DomainMessagePayload {

        @JsonProperty
        private long messageId;
        @JsonProperty 
        private String message;
        
        private MessageStatus status;
    }
    
}
