//package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.model;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import jakarta.persistence.Access;
//import jakarta.persistence.Basic;
//import jakarta.persistence.Embeddable;
//import jakarta.persistence.Enumerated;
//import jakarta.validation.constraints.NotNull;
//import lombok.*;
//import org.hibernate.validator.constraints.Range;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//
//import static jakarta.persistence.AccessType.FIELD;
//import static jakarta.persistence.EnumType.STRING;
//import static lombok.AccessLevel.PRIVATE;
//
///**
// * eu: DDD example
// */
//@Access(FIELD)
//@Embeddable
//@Getter
//@ToString
//@AllArgsConstructor(access = PRIVATE)
//@NoArgsConstructor(access = PRIVATE) // JPA
//public class Price implements Serializable {
//    private static final long serialVersionUID = -1554812008438549239L;
//
//    public static final int DB_PRECISION = 17;
//
//    public static final int DB_SCALE = 5;
//
//    @NotNull
//    @NonNull
//    @Enumerated(STRING)
//    private TaxModel taxModel;
//
//    @NotNull @NonNull
//    @Enumerated(STRING)
//    private CurrencyCode currency;
//
//    @NotNull @NonNull
//    @Range
//    @Basic
//    @JsonSerialize(using = BigDecimalStripTrailingZerosSerializer.class)
//    private BigDecimal amount;
//
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    private DerivedPrice derivedPrice;
//
//    @JsonCreator
//    public static Price of(TaxModel taxModel, String currency, BigDecimal amount) {
//        return new Price(taxModel, CurrencyCode.valueOf(currency), amount, null);
//    }
//
//    public static Price of(TaxModel taxModel, CurrencyCode currency, BigDecimal amount) {
//        return new Price(taxModel, currency, amount, null);
//    }
//
//    public static Price of(TaxModel taxModel, MonetaryAmount amount) {
//        CurrencyCode currency = extractCurrency(amount);
//        BigDecimal value = extractValue(amount);
//        return of(taxModel, currency, value);
//    }
//
//    public Price withDerivedPrice(MonetaryAmount amount, double taxRate) {
//        return new Price(this.taxModel, this.currency, this.amount,
//                new DerivedPrice(taxModel.opposite(), extractCurrency(amount), extractValue(amount), taxRate));
//    }
//
//    public MonetaryAmount toMoney() {
//        return Money.of(amount, currency.getCurrency().getCurrencyCode());
//    }
//
//    private static CurrencyCode extractCurrency(MonetaryAmount amount) {
//        return CurrencyCode.valueOf(amount.getCurrency().getCurrencyCode());
//    }
//
//    private static BigDecimal extractValue(MonetaryAmount amount) {
//        return amount.getNumber().numberValue(BigDecimal.class);
//    }
//
//    //help data rest in patch scenarios
//    @SuppressWarnings("squid:UnusedPrivateMethod")
//    private void setCurrency(String currency) {
//        this.currency = CurrencyCode.valueOf(currency);
//    }
//}
