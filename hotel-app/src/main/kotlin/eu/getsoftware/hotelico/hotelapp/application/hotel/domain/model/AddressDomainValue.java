package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Value objects are model elements that do not have a specific identification. 
 * Their identification is based on the comparison (by value) OF ALL THEIR ATTRIBUTES!!!
 * They also can contain business logic. Value objects mainly live as attributes of other entities.
 * A good example of a value object is the delivery address of the Order entity.
 */
@Builder
@Getter
public class AddressDomainValue {
    protected String city;
    protected String street;
    protected Integer houseNumber;

    public AddressDomainValue(String street, String city, Integer houseNumber) {
        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
    }
    
}
