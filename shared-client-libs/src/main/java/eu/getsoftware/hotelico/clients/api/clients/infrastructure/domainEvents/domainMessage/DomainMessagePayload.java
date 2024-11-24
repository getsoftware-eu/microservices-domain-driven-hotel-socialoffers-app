package eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.domainMessage;

import lombok.Getter;

import java.time.Instant;

/**
 * Базовый класс для полезной нагрузки сообщений с общими полями.
 */
public abstract class DomainMessagePayload {
    
    @Getter
    private final Instant createdAt;

    protected DomainMessagePayload() {
        this.createdAt = Instant.now();
    }
}