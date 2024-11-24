package eu.getsoftware.hotelico.clients.common.domain;

import java.util.UUID;

/**
 *
 * Контракты и соглашения: Если связь между контекстами требует передачи данных, DDD предлагает использовать интеграционные механизмы, 
 * такие как Domain Events, Anti-Corruption Layer или API, которые позволят передавать OrderId между контекстами. 
 * Это позволяет избежать зависимости одного контекста от внутренней реализации другого.
 */
public interface EntityIdentifier {

    String uuidValue();
    
    default boolean ensureValidUuid(String value) throws IllegalArgumentException {
        UUID.fromString(value);
        return true;
    }
    
}