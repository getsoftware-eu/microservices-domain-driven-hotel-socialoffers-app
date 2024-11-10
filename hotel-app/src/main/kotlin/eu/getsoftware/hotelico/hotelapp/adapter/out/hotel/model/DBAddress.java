package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model;

import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DBAddress extends Address {

    public DBAddress() {
        super(null, null, null);  // Конструктор по умолчанию для JPA
    }

    public DBAddress(String street, String city, Integer houseNumber) {
        super(street, city, houseNumber);
    }

    @Column(name = "street")
    public String getStreet() {
        return super.getStreet();
    }

    @Column(name = "city")
    public String getCity() {
        return super.getCity();
    }

    @Column(name = "house_number")
    public Integer getHouseNumber() {
        return super.getHouseNumber();
    }
}