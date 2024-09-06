package eu.getsoftware.hotelico.hotel.application.domain;

/**
 * Value objects are model elements that do not have a specific identification. 
 * Their identification is based on the comparison (by value) OF ALL THEIR ATTRIBUTES!!!
 * They also can contain business logic. Value objects mainly live as attributes of other entities.
 * A good example of a value object is the delivery address of the Order entity.
 */
public record Address(
        String city,
        String street,
        Integer houseNumber
) { }
