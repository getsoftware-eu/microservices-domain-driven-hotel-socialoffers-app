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