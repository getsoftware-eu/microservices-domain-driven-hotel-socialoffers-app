//package eu.getsoftware.hotelico.service.booking.adapter.out.hotel;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotEmpty;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import static lombok.AccessLevel.NONE;
//import static lombok.AccessLevel.PRIVATE;
//
//@Entity
//@Table(name = "HOTEL", uniqueConstraints = {@UniqueConstraint(name = "U_HOTEL_TENANT_ID_SKU", columnNames = {"TENANT_ID", "SKU"})})
//@Multitenant
//@TenantDiscriminatorColumn(discriminatorType = LONG, columnDefinition = "INT NOT NULL")
//@Getter
//@Setter
//@NoArgsConstructor(access = PRIVATE) // for JPA/Jackson
//@AllowedMarkdownForProduct
//public class HotelAggregate extends AbstractEntity implements WithAttributes, WithCurrentLocale {
//    
//    @NotEmpty
//    @Basic
//    @Column(name = "SKU", nullable = false, length = 255)
//    private String sku;
//
//    @Getter(NONE)
//    @Setter(NONE)
//    @ElementCollection(fetch = LAZY)
//    @CollectionTable(name = "HOTEL_ATTRIBUTE", joinColumns = @JoinColumn(name = "HOTEL_ID", nullable = false))
//    private Map<AttributeKey, Attribute> attrs = newHashMap();
//
//    @InlinedAttribute
//    public String getName() {
//        return getAttributes().withCurrentLocale(NAME, STRING);
//    }
//
//    @InlinedAttribute
//    public void setName(String value) {
//        getAttributes().withCurrentLocale(NAME, STRING, value);
//    }
//}
