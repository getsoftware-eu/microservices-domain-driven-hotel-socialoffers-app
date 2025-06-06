package eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage;

import java.time.Instant;

/**
 * Базовый класс для полезной нагрузки сообщений с общими полями.
 */
public interface DomainMessagePayload {

    String getMessage();
    
    default Instant getCreatedAt(){
        return Instant.now();
    };

    long getMessageId();
}