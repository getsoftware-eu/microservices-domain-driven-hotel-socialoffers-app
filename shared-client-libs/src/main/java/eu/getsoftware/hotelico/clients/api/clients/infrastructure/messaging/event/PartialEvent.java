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
public class PartialEvent extends DomainMessagePayload {
    private final String eventId;
    private final String eventType;
    private String status; // example, "IN_PROGRESS", "PENDING", "COMPLETED"

    public PartialEvent(String eventId, String eventType, String status) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.status = status;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PartialEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}