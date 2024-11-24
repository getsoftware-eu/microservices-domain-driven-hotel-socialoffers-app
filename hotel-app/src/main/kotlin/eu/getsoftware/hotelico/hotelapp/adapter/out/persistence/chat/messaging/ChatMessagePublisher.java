package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.chat.messaging;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainLayerPayload.ChatSendEventMessagePayload;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessage;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.hotelapp.adapter.out.service.messaging.KafkaMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainLayerPayload.MessageStatus.QUEUED;

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

        String messageKeyForKafkaPartition = String.valueOf(chatMsgDTO.receiverDomainId());
        
        kafkaMessagePublisher.publishMessageToPartition(messageKeyForKafkaPartition, eventMessage);
        log.info("Published message {}", eventMessage);
    }

    
    
}
