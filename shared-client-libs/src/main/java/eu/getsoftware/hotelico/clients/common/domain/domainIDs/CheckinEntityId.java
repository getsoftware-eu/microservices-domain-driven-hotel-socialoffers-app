package eu.getsoftware.hotelico.clients.common.domain.domainIDs;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;

public record CheckinEntityId(
        String uuidValue
    ) implements EntityIdentifier {
   
}