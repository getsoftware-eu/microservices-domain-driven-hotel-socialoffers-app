package eu.getsoftware.hotelico.clients.common.domain.ids;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;

import java.util.UUID;

public record ChatMsgDomainEntityId(
        String uuidValue
) implements EntityIdentifier {
    
    public ChatMsgDomainEntityId(String value, Boolean checkParam) { //Eu: additional constructor!!!
        this(value);
        
        if(checkParam && !EntityIdentifier.super.ensureValidUuid(value))
        {
            throw new RuntimeException("not valid param" + value);    
        }
    }
    
    public ChatMsgDomainEntityId(UUID value) {
        this(value.toString());
    } //Eu: additional constructor!!!


    @Override
    public String toString() {
        return uuidValue;
    }
}