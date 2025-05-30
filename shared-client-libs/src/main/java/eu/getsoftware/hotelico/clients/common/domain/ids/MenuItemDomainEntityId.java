package eu.getsoftware.hotelico.clients.common.domain.ids;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

//@Embeddable
public record MenuItemDomainEntityId(
        String uuidValue
) implements EntityIdentifier {

    public static MenuItemDomainEntityId from(@NotEmpty String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("MenuDomainEntityId cannot be null or empty");
        }
        return new MenuItemDomainEntityId(value);
    }

    public static MenuItemDomainEntityId from(@NotNull UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("MenuDomainEntityId cannot be null or empty");
        }
        return new MenuItemDomainEntityId(uuid.toString());
    }

    public static MenuItemDomainEntityId generate() {
        return new MenuItemDomainEntityId(UUID.randomUUID().toString());
    }

}