package eu.getsoftware.hotelico.hotelapp.application.customer.domain.model;

import eu.getsoftware.hotelico.hotelapp.application.customer.domain.CustomerAggregate;

public interface ICustomerRootEntity {
    
    void setConsistencyId(long consistencyId);

    long getId();

    CustomerAggregate getCustomerGPSPosition();

    void setHotelStaff(boolean b);

    void setLogged(boolean b);

    void setPasswordValue(String password);

    void setPasswordHash(long passwordHash);

    void setGuestAccount(boolean b);

    void setActive(boolean b);

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setStatus(String status);

    void setSex(String sex);

    void setShowAvatar(boolean showAvatar);

    void setShowInGuestList(boolean showInGuestList);

    void setEmail(String email);

    void setLinkedInId(String linkedInId);

    void setFacebookId(String facebookId);

    void setPushRegistrationId(String s);
}
