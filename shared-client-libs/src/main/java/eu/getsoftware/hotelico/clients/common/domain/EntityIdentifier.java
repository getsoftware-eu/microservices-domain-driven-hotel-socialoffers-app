package eu.getsoftware.hotelico.clients.common.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * Контракты и соглашения: Если связь между контекстами требует передачи данных, DDD предлагает использовать интеграционные механизмы, 
 * такие как Domain Events, Anti-Corruption Layer или API, которые позволят передавать OrderId между контекстами. 
 * Это позволяет избежать зависимости одного контекста от внутренней реализации другого.
 */
public abstract class EntityIdentifier implements Serializable {
    final protected String value;

    public EntityIdentifier(String value) {
        ensureValidUuid(value);

        this.value = value;
    }

    protected EntityIdentifier() {
        this.value = null;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntityIdentifier that = (EntityIdentifier) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private void ensureValidUuid(String value) throws IllegalArgumentException {
        UUID.fromString(value);
    }
}