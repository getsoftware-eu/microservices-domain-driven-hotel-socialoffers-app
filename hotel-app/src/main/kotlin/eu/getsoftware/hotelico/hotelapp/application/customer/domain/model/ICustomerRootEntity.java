//package eu.getsoftware.hotelico.hotelapp.application.customer.domain.model;
//
//import eu.getsoftware.hotelico.clients.common.domain.IDomainEntity;
//import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
//
///**
// * Try to entkoppeln domain logik from domainEntity, mabe all actions only with interface :)))))
// */
//public interface CustomerRootDomainEntity extends IDomainEntity {
//
//    CustomerDomainEntityId getDomainEntityId();
//
//    void setConsistencyId(long consistencyId);
//
//    long getId();
//    
//    void setHotelStaff(boolean b);
//
//    void setLogged(boolean b);
//
//    void setPasswordValue(String password);
//
//    void setPasswordHash(long passwordHash);
//
//    void setGuestAccount(boolean b);
//
//    void setActive(boolean b);
//
//    void setFirstName(String firstName);
//
//    void setLastName(String lastName);
//
//    void setStatus(String status);
//
//    void setSex(String sex);
//
//    void setShowAvatar(boolean showAvatar);
//
//    void setShowInGuestList(boolean showInGuestList);
//
//    void setEmail(String email);
//
////    void setLinkedInId(String linkedInId);
//
////    void setFacebookId(String facebookId);
//    
//    boolean isHotelStaff();
//
//    boolean isAdmin();
//
//    String getPrefferedLanguage();
//
//    String getFirstName();
//
//    String getEmail();
//
//    double getLatitude();
//
//    double getLongitude();
//
//    void setLatitude(double latitude);
//
//    void setLongitude(double longitude);
//
//    ICustomerDetails getCustomerDetails();
//
//    ICustomerPreferences getCustomerPreferences();
//
//}
