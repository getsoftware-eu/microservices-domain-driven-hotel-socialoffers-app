package eu.getsoftware.hotelico.clients.common.domain.ids;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

//@Embeddable
public record HotelDomainEntityId(
        String uuidValue
) implements EntityIdentifier {

    public static HotelDomainEntityId from(@NotEmpty String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("HotelDomainEntityId cannot be null or empty");
        }
        return new HotelDomainEntityId(value);
    }

    public static HotelDomainEntityId from(@NotNull UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("HotelDomainEntityId cannot be null or empty");
        }
        return new HotelDomainEntityId(uuid.toString());
    }
    public static HotelDomainEntityId from(@NotEmpty int value) {
        return from(String.valueOf(value));
    }
    
    public static HotelDomainEntityId generate() {
        return new HotelDomainEntityId(UUID.randomUUID().toString());
    }

//    HotelDomainEntityId map(I value){
//        
//    }

}