package eu.getsoftware.hotelico.clients.api.amqp.application.domain.model;

import lombok.AllArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
public class DomainMessage<T extends DomainMessagePayload> {
    
    private final long messageId;
    private final long tenantId;

    //Поля correlationId и causationId поддерживают отслеживание и отладку в распределенных системах.
    private final long correlationId;
    private final long causationId;
    
    private final T payload;
    private final Instant timestamp;

    @Override
    public String toString() {
        return "eu.getsoftware.hotelico.clients.api.amqp.application.domain.model.DomainMessage{" +
               "messageId='" + messageId + '\'' +
               ", correlationId='" + correlationId + '\'' +
               ", causationId='" + causationId + '\'' +
               ", payload=" + payload +
               ", timestamp=" + timestamp +
               '}';
    }
}
