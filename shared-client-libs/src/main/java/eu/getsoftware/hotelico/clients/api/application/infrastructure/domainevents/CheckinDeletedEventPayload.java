package eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage.DomainMessagePayload;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage.DomainMessagePayloadStatus;
import eu.getsoftware.hotelico.clients.common.domain.ids.CheckinDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Payload have to be send in Queue in JSON - FORM
 */
@AllArgsConstructor
@Builder
@Getter
@JsonTypeName("checkin-deleted-event")
public class CheckinDeletedEventPayload extends DomainMessagePayload {
    
    @JsonProperty
    private CheckinDomainEntityId entityId;
    
    @JsonProperty
    private long messageId;
    
    @JsonProperty
    private HotelDomainEntityId hotelId;
    
    @JsonProperty
    private CustomerDomainEntityId customerId;

    private DomainMessagePayloadStatus status;
}