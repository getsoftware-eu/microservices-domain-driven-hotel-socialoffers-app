package eu.getsoftware.hotelico.clients.common.domain.domainIDs;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;

public record HotelDomainEntityId(
        String uuidValue
) implements EntityIdentifier {
    public HotelDomainEntityId {
        if (uuidValue == null || uuidValue.isBlank()) {
            throw new IllegalArgumentException("DomainId cannot be null or blank");
        }
    }
}