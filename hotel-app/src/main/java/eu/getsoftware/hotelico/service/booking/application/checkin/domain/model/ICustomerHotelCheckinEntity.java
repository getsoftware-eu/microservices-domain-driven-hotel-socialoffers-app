//package eu.getsoftware.hotelico.hotelapp.application.checkin.domain.model;
//
//import eu.getsoftware.hotelico.clients.common.domain.IDomainEntity;
//import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
//import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
//import jakarta.validation.constraints.NotNull;
//
//import java.util.Date;
//
///**
// * eu: try all validation put higher, ideally in entity or domain service
// * 
// */
//public interface CheckinRootDomainEntity extends IDomainEntity {
//
//    default CheckinRootDomainEntity createInstance(@NotNull CustomerDomainEntityId customerDomainEntityId, @NotNull HotelDomainEntityId hotelDomainEntityId, @NotNull Date validFrom, @NotNull Date validTo) {
//        setCheckinDates(validFrom, validTo);
//        setCustomerDomainEntityId(customerDomainEntityId);
//        setHotelDomainEntityId(hotelDomainEntityId);
//        return this;
//    }
//
//    /**
//     * je höher zum Model is the validation, desto mehr werden es benutzen
//     * @param validFrom
//     * @param validTo
//     */
//    default void setCheckinDates(@NotNull Date validFrom, @NotNull Date validTo){
//        boolean isDateValid = validFrom.before(new Date()) && validTo.before(validFrom);
//
//        if(!isDateValid) throw new IllegalArgumentException("Invalid checkin dates");
//
//        setValidFrom(validFrom);
//        setValidTo(validTo);
//    }
//
//
////    default CheckinRootDomainEntity createInstance(CheckinId checkinId,CustomerRootDomainEntity customerEntity, HotelDomainEntity hotelRootEntity, boolean isFullCheckin) {
////        setCheckinId(checkinId);
////        createInstance(customerEntity, hotelRootEntity, isFullCheckin);
////        return this;
////    }
////
////    /**
////     * Creates entity without an ID. Use to create a new entity that is not yet ++++++
////     * persisted.
////     */
////    public static CheckinRootDomainEntity withoutId(
////            CustomerRootDomainEntity customerEntity,
////            HotelDomainEntity hotelRootEntity) {
////        return new CheckinRootDomainEntity(null, customerEntity, hotelRootEntity);
////    }
////
////    @Value //eu: we dont show (private) id as value, but use difference with other Ids outside: Account.AccountId :))
////    public static class CheckinId {
////        private Long value;
////    }
////    
////    /**
////     * Creates entity with an ID. Use to reconstitute a persisted entity. +++++++++++
////     */
////    public static CheckinRootDomainEntity withId(
////            CheckinId existingCheckinId,
////            CustomerRootDomainEntity customerEntity,
////            HotelDomainEntity hotelRootEntity) {
////        return new CheckinRootDomainEntity(existingCheckinId, customerEntity, hotelRootEntity);
////    }
//    
//    void setCustomerDomainEntityId(CustomerDomainEntityId customerEntityId);
//    void setHotelDomainEntityId(HotelDomainEntityId hotelEntityId);
//
//    boolean isStaffCheckin();
//
//    void setStaffCheckin(boolean staffCheckin);
//
//    Date getValidFrom();
//
//    Date getValidTo();
//
//    void setValidFrom(@NotNull Date validFrom);
//
//    void setValidTo(@NotNull Date validTo);
//
//    boolean isActive();
//
//    void setActive(boolean active);
//
//    int hashCode();
//
//    boolean isFullCheckin();
//
//    void setFullCheckin(boolean fullCheckin);
//
//    HotelDomainEntityId getHotelDomainEntityId();
//
//    CustomerDomainEntityId getCustomerDomainEntityId();
//}
