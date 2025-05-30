package eu.getsoftware.hotelico.clients.common.domain.ids;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

//@Embeddable
public record MenuOrderDomainEntityId(
        String uuidValue
) implements EntityIdentifier {

    public static MenuOrderDomainEntityId from(@NotEmpty String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("MenuDomainEntityId cannot be null or empty");
        }
        return new MenuOrderDomainEntityId(value);
    }

    public static MenuOrderDomainEntityId from(@NotNull UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("MenuDomainEntityId cannot be null or empty");
        }
        return new MenuOrderDomainEntityId(uuid.toString());
    }

    public static MenuOrderDomainEntityId generate() {
        return new MenuOrderDomainEntityId(UUID.randomUUID().toString());
    }

}