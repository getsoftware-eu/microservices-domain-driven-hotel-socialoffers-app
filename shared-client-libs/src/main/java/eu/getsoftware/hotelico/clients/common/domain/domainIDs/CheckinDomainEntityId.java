package eu.getsoftware.hotelico.clients.common.domain.domainIDs;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CheckinDomainEntityId(
        @NotEmpty String uuidValue
    ) implements EntityIdentifier {

    public static CheckinDomainEntityId from(@NotEmpty String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("CheckinDomainEntityId cannot be null or empty");
        }
        return new CheckinDomainEntityId(value);
    }

    public static CheckinDomainEntityId from(@NotNull UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("CheckinDomainEntityId cannot be null or empty");
        }
        return new CheckinDomainEntityId(uuid.toString());
    }

    public static CheckinDomainEntityId generate() {
        return new CheckinDomainEntityId(UUID.randomUUID().toString());
    }

}