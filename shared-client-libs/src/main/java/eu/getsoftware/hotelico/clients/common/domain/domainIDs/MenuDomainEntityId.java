package eu.getsoftware.hotelico.clients.common.domain.domainIDs;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;

import java.util.UUID;

//@Embeddable
public record MenuDomainEntityId(
        String uuidValue
) implements EntityIdentifier {

    public MenuDomainEntityId { //Eu: primary constructor!!! without parameters
        if (uuidValue == null || uuidValue.isBlank()) {
            throw new IllegalArgumentException("uuidValue cannot be null or blank");
        }
    }
    
    public MenuDomainEntityId(String value, Boolean checkParam) { //Eu: additional constructor!!!
        this(value);
        
        if(checkParam && !EntityIdentifier.super.ensureValidUuid(value))
        {
            throw new RuntimeException("not valid param" + value);    
        }
    }
    
    public MenuDomainEntityId(UUID value) {
        this(value.toString());
    } //Eu: additional constructor!!!


    @Override
    public String toString() {
        return uuidValue;
    }
}