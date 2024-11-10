package eu.getsoftware.hotelico.clients.common.domain.domainIDs;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;

public record WallPostDomainEntityId(
        String uuidValue
) implements EntityIdentifier {
    public WallPostDomainEntityId {
        if (uuidValue == null || uuidValue.isBlank()) {
            throw new IllegalArgumentException("DomainId cannot be null or blank");
        }
    }
}