package eu.getsoftware.hotelico.clients.common.domain.ids;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

//@Embeddable
public record CustomerDealDomainEntityId(
        String uuidValue
) implements EntityIdentifier {

    public CustomerDealDomainEntityId { //Eu: primary constructor!!! without parameters
        if (uuidValue == null || uuidValue.isBlank()) {
            throw new IllegalArgumentException("uuidValue cannot be null or blank");
        }
    }

    
    public CustomerDealDomainEntityId(String value, Boolean checkParam) { //Eu: additional constructor!!!
        this(value);

        if(checkParam && !EntityIdentifier.super.ensureValidUuid(value))
        {
            throw new RuntimeException("not valid param" + value);    
        }
    }

    public CustomerDealDomainEntityId(UUID value) {
        this(value.toString());
    } //Eu: additional constructor!!!

    public static CustomerDealDomainEntityId from(@NotEmpty int value) {
        return new CustomerDealDomainEntityId(String.valueOf(value), true);
    }

    @Override
    public String toString() {
        return uuidValue;
    }


}