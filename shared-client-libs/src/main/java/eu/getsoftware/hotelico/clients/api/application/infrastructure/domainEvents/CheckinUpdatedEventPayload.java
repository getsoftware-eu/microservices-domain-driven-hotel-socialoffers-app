package eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.domainMessage.DomainMessagePayload;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.domainMessage.DomainMessagePayloadStatus;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CheckinDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * Payload have to be send in Queue in JSON - FORM
 */
@AllArgsConstructor
@Builder
@Getter
@JsonTypeName("checkin-updated-event")
public class CheckinUpdatedEventPayload extends DomainMessagePayload {
    
    @JsonProperty
    private CheckinDomainEntityId entityId;
    
    @JsonProperty
    private long messageId;
    
    @JsonProperty
    private HotelDomainEntityId hotelId;
    
    @JsonProperty
    private CustomerDomainEntityId customerId;

    private Date checkinFrom;
    
    private Date checkinTo;

    private DomainMessagePayloadStatus status;

    public CheckinDomainEntityId getEntityId() {
        return entityId;
    }
}