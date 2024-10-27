package eu.getsoftware.hotelico.clients.api.amqp.application.domain.model;

import java.time.Instant;

/**
 * Базовый класс для полезной нагрузки сообщений с общими полями.
 */
public abstract class DomainMessagePayload {
    private final Instant createdAt;

    protected DomainMessagePayload() {
        this.createdAt = Instant.now();
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}