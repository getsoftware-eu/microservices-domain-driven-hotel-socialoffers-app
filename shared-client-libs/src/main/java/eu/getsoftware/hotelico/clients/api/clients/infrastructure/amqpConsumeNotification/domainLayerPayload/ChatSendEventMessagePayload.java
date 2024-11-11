package eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainLayerPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessagePayload;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
     * Payload have to be send in Queue in JSON - FORM
     */
    @AllArgsConstructor
    @Builder
    @Getter
    @JsonTypeName("chat-send-event")
    public class ChatSendEventMessagePayload extends DomainMessagePayload {

        @JsonProperty
        private long messageId;        
        
        @JsonProperty
        private CustomerDomainEntityId senderId;   
        
        @JsonProperty
        private CustomerDomainEntityId receiverId;
        
        @JsonProperty 
        private String message;       
        
        @JsonProperty 
        private String senderName;
        
        private MessageStatus status;
}