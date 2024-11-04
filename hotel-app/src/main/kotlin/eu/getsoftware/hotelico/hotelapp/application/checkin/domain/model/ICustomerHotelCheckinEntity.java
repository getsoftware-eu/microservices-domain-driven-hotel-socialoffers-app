package eu.getsoftware.hotelico.hotelapp.application.checkin.domain.model;

import eu.getsoftware.hotelico.clients.common.domain.IDomainEntity;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelEntityId;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

/**
 * eu: try all validation put higher, ideally in entity or domain service
 * 
 */
public interface ICustomerHotelCheckinEntity extends IDomainEntity {

    default ICustomerHotelCheckinEntity createInstance(@NotNull CustomerEntityId customerEntityId, @NotNull HotelEntityId hotelEntityId, @NotNull Date validFrom, @NotNull Date validTo) {
        setCheckinDates(validFrom, validTo);
        setCustomerEntityId(customerEntityId);
        setHotelEntityId(hotelEntityId);
        return this;
    }

    /**
     * je höher zum Model is the validation, desto mehr werden es benutzen
     * @param validFrom
     * @param validTo
     */
    default void setCheckinDates(@NotNull Date validFrom, @NotNull Date validTo){
        boolean isDateValid = validFrom.before(new Date()) && validTo.before(validFrom);

        if(!isDateValid) throw new IllegalArgumentException("Invalid checkin dates");

        setValidFrom(validFrom);
        setValidTo(validTo);
    }


//    default ICustomerHotelCheckinEntity createInstance(CheckinId checkinId,ICustomerRootEntity customerEntity, IHotelRootEntity hotelRootEntity, boolean isFullCheckin) {
//        setCheckinId(checkinId);
//        createInstance(customerEntity, hotelRootEntity, isFullCheckin);
//        return this;
//    }
//
//    /**
//     * Creates entity without an ID. Use to create a new entity that is not yet ++++++
//     * persisted.
//     */
//    public static ICustomerHotelCheckinEntity withoutId(
//            ICustomerRootEntity customerEntity,
//            IHotelRootEntity hotelRootEntity) {
//        return new ICustomerHotelCheckinEntity(null, customerEntity, hotelRootEntity);
//    }
//
//    @Value //eu: we dont show (private) id as value, but use difference with other Ids outside: Account.AccountId :))
//    public static class CheckinId {
//        private Long value;
//    }
//    
//    /**
//     * Creates entity with an ID. Use to reconstitute a persisted entity. +++++++++++
//     */
//    public static ICustomerHotelCheckinEntity withId(
//            CheckinId existingCheckinId,
//            ICustomerRootEntity customerEntity,
//            IHotelRootEntity hotelRootEntity) {
//        return new ICustomerHotelCheckinEntity(existingCheckinId, customerEntity, hotelRootEntity);
//    }
    
    void setCustomerEntityId(CustomerEntityId customerEntityId);
    void setHotelEntityId(HotelEntityId hotelEntityId);

//    HotelRootEntity getHotel();
//
//    CustomerEntityId getCustomer();
    
    boolean isStaffCheckin();

    void setStaffCheckin(boolean staffCheckin);

    Date getValidFrom();

    Date getValidTo();

    void setValidFrom(@NotNull Date validFrom);

    void setValidTo(@NotNull Date validTo);

    boolean isActive();

    void setActive(boolean active);

    int hashCode();

    boolean isFullCheckin();

    void setFullCheckin(boolean fullCheckin);

}
