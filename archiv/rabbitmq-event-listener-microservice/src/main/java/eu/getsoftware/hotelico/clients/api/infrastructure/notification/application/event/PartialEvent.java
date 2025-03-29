package eu.getsoftware.hotelico.clients.api.infrastructure.notification.application.event;

import eu.getsoftware.hotelico.clients.api.amqp.application.domain.model.DomainMessagePayload;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Представляет частичное событие с основными полями для последующего заполнения.
 * status : "IN_PROGRESS", "PENDING", "COMPLETED"
 * 
 * PartialEvent partialEvent = new PartialEvent(
 *     "event123",      // eventId
 *     "CheckinStarted",// eventType
 *     "IN_PROGRESS"    // статус события
 * );
 */
@Getter
@RequiredArgsConstructor
public class PartialEvent extends DomainMessagePayload {
    private final String eventId;
    private final String eventType;
    private String status; // example, "IN_PROGRESS", "PENDING", "COMPLETED"

    @Override
    public String toString() {
        return "PartialEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}