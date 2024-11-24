package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotEmpty;

/**
 * Value objects are model elements that do not have a specific identification. 
 * Their identification is based on the comparison (by value) OF ALL THEIR ATTRIBUTES!!!
 * They also can contain business logic. Value objects mainly live as attributes of other entities.
 * A good example of a value object is the delivery address of the Order entity.
 */
//@Builder
//@Getter
public record AddressValueObject(
        @NotEmpty String street,
        @NotEmpty String city,
        @NotEmpty String houseNumber
) {

    public static AddressValueObject from(@NotEmpty String city, @NotEmpty String street, @NotEmpty String houseNumber) {
        return new AddressValueObject(city, street, houseNumber);
    } 
    public static AddressValueObject from(String asJson) {
        return new AddressValueObject("city", "street", "houseNumber");
    }

    public String serialize() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize record", e);
        }
    }
}