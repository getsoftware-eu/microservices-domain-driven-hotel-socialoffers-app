package eu.getsoftware.hotelico.clients.common.domain.ids;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

//@Embeddable
public record ActivityDomainEntityId(
        @NotEmpty String uuidValue
) implements EntityIdentifier {

    public static ActivityDomainEntityId from(@NotEmpty String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("ActivityDomainEntityId cannot be null or empty");
        }
        return new ActivityDomainEntityId(value);
    }

    public static ActivityDomainEntityId from(@NotNull UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("ActivityDomainEntityId cannot be null or empty");
        }
        return new ActivityDomainEntityId(uuid.toString());
    }

    public static ActivityDomainEntityId generate() {
        return new ActivityDomainEntityId(UUID.randomUUID().toString());
    }
    
    public void validateBusinessConditions(String value, Boolean checkParam) { //Eu: additional constructor!!!
        
        if(checkParam && !EntityIdentifier.super.ensureValidUuid(value))
        {
            throw new RuntimeException("not valid param" + value);    
        }
    }
}