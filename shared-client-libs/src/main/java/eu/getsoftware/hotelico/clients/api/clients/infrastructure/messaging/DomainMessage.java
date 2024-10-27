import java.time.Instant;

public class DomainMessage<T extends DomainMessagePayload> {
    private final String messageId;
    private final String correlationId;
    private final String causationId;
    //Поля correlationId и causationId поддерживают отслеживание и отладку в распределенных системах.
    private final T payload;
    private final Instant timestamp;

    public DomainMessage(String messageId, String correlationId, String causationId, T payload) {
        this.messageId = messageId;
        this.correlationId = correlationId;
        this.causationId = causationId;
        this.payload = payload;
        this.timestamp = Instant.now();
    }

    public String getMessageId() {
        return messageId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public String getCausationId() {
        return causationId;
    }

    public T getPayload() {
        return payload;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "DomainMessage{" +
               "messageId='" + messageId + '\'' +
               ", correlationId='" + correlationId + '\'' +
               ", causationId='" + causationId + '\'' +
               ", payload=" + payload +
               ", timestamp=" + timestamp +
               '}';
    }
}
