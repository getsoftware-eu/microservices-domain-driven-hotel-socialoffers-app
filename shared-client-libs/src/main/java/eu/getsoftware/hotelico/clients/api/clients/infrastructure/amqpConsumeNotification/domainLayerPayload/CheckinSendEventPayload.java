package eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainLayerPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessagePayload;
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
@JsonTypeName("checkin-send-event")
public class CheckinSendEventPayload extends DomainMessagePayload {


    @JsonProperty
    private long entityId;
    @JsonProperty
    private long messageId;
    @JsonProperty
    private long hotelId;
    @JsonProperty
    private long customerId;

    private Date checkinFrom;
    private Date checkinTo;

    private MessageStatus status;
}