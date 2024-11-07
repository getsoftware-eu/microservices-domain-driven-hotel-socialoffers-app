package eu.getsoftware.hotelico.clients.common.domain.domainIDs;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;

import java.util.UUID;

public record CustomerEntityId(
        String value
) implements EntityIdentifier {
    
    public CustomerEntityId(String value, Boolean checkParam) {
        this(value);
        
        if(checkParam && !EntityIdentifier.super.ensureValidUuid(value))
        {
            throw new RuntimeException("not valid param" + value);    
        }
    }
    
    public CustomerEntityId(UUID value) {
        this(value.toString());
    }
}