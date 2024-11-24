package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model;

import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.AddressDomainValue;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DBAddress extends AddressDomainValue {

    public DBAddress() {
        super(null, null, null);  // Конструктор по умолчанию для JPA
    }

    public DBAddress(String street, String city, Integer houseNumber) {
        super(street, city, houseNumber);
    }

    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    @Column(name = "city")
    public String getCity() {return city;}

    @Column(name = "house_number")
    public Integer getHouseNumber() {
        return houseNumber;
    }
}