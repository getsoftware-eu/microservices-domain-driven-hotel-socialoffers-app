package eu.getsoftware.hotelico.hotelapp.adapter.out.chat.messaging;

import eu.getsoftware.hotelico.clients.api.amqp.application.domain.model.DomainMessage;
import eu.getsoftware.hotelico.clients.api.amqp.application.domain.model.DomainMessagePayload;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatMessagePublisher {
    
    private final DomainMessagePublisher domainMessagePublisher;
    private final DomainMessageFactory domainMessageFactory;
    
    public void publishChatSentEvent(ChatMsgDTO chatMsgDTO){
        publishMessage("chat.message.sent.event", chatMsgDTO);
    }
    
    private void publishMessage(String messageType, ChatMsgDTO chatMsgDTO) {

        ChatSendEventMessage eventMessage = ChatSendEventMessage.builder()
                .messageId(chatMsgDTO.initId())
                .status(QUEUED)
                .build();

        DomainMessage<ChatSendEventMessage> domainMessage = domainChatMessageFactory.create(messageType, eventMessage);
        domainMessagePublisher.publish(domainMessage);
        log.info("Published message of type {}", domainMessage.getMessageType());
    }
    
    @AllArgsConstructor
    @Builder
    @Getter
    @JsonTypeName("chat-send-event")
    static class ChatSendEventMessage extends DomainMessagePayload {
        private long messageId;
        private MessageStatus status;
    }
    
}
