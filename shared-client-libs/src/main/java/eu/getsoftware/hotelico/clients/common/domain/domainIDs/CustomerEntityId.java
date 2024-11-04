package eu.getsoftware.hotelico.clients.common.domain.domainIDs;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;

import java.util.UUID;

public class CustomerEntityId extends EntityIdentifier {
    public CustomerEntityId(String value) {
        super(value);
    }
    
    public CustomerEntityId(UUID value) {
        super(value.toString());
    }
}