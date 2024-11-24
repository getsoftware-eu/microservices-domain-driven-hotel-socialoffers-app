package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model;

import jakarta.persistence.Embeddable;
import lombok.NonNull;

@Embeddable
public class AddressDBEmbeddable {
    private @NonNull String street;
    private @NonNull String city;
    private @NonNull String houseNumber;
}