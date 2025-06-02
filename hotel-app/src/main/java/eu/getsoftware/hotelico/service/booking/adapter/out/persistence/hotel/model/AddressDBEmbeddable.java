package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class AddressDBEmbeddable {
    private @NonNull String street;
    private @NonNull String city;
    private @NonNull String houseNumber;
}