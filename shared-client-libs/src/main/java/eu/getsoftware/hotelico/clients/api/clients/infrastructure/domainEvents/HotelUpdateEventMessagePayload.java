package eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.domainMessage.DomainMessagePayload;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.domainMessage.DomainMessagePayloadStatus;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
     * Payload have to be send in Queue in JSON - FORM
     */
    @AllArgsConstructor
    @Builder
    @Getter
    @JsonTypeName("hotel-update-event")
    public class HotelUpdateEventMessagePayload extends DomainMessagePayload {

        @JsonProperty
        private long messageId;        
        
        @JsonProperty
        private HotelDomainEntityId hotelId;   
        
        @JsonProperty 
        private String message;       
        
        private DomainMessagePayloadStatus status;
}