package eu.getsoftware.hotelico.clients.common.domain.ids;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

//@Embeddable
public record MenuDomainEntityId(
        String uuidValue
) implements EntityIdentifier {

    public static MenuDomainEntityId from(@NotEmpty String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("MenuDomainEntityId cannot be null or empty");
        }
        return new MenuDomainEntityId(value);
    }

    public static MenuDomainEntityId from(@NotNull UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("MenuDomainEntityId cannot be null or empty");
        }
        return new MenuDomainEntityId(uuid.toString());
    }

    public static MenuDomainEntityId generate() {
        return new MenuDomainEntityId(UUID.randomUUID().toString());
    }

}