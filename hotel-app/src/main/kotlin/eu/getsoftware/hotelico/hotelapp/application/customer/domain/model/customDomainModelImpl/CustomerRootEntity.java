package eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.customDomainModelImpl;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerDetails;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerPreferences;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Builder
@Setter
@Getter
@RequiredArgsConstructor
public class CustomerRootEntity implements ICustomerRootEntity {

    private final CustomerDomainEntityId domainEntityId;
    
    private final long id;
    private final boolean hotelStaff;
    private final String firstName;
    private final String lastName;
    private final String status;

    private final boolean logged;

    private final String passwordValue;

    private final long passwordHash;

    private final  boolean guestAccount;

    private final boolean active;

    private final String sex;

    private final  boolean showAvatar;

    private final  boolean showInGuestList;

    private final String  email ;

    private final String linkedInId;

    private final String facebookId;

    private final String pushRegistrationId;

    private final boolean admin;

    private final String prefferedLanguage;

    private final  double latitude;

    private final double longitude;

//    CustomerRootEntity(CustomerEntityId customerEntityId)
//    {
//        this.customerEntityId = customerEntityId;
//    }
    
    public CustomerAggregate getCustomerAggregate(){
        return new CustomerAggregate(this);
    }

    @Override
    public void setConsistencyId(long consistencyId) {
        
    }

    @Override
    public void setHotelStaff(boolean b) {

    }

    @Override
    public void setLogged(boolean b) {

    }

    @Override
    public void setPasswordValue(String password) {

    }

    @Override
    public void setPasswordHash(long passwordHash) {

    }

    @Override
    public void setGuestAccount(boolean b) {

    }

    @Override
    public void setActive(boolean b) {

    }

    @Override
    public void setFirstName(String firstName) {

    }

    @Override
    public void setLastName(String lastName) {

    }

    @Override
    public void setStatus(String status) {

    }

    @Override
    public void setSex(String sex) {

    }

    @Override
    public void setShowAvatar(boolean showAvatar) {

    }

    @Override
    public void setShowInGuestList(boolean showInGuestList) {

    }

    @Override
    public void setEmail(String email) {

    }

//    @Override
//    public void setLinkedInId(String linkedInId) {
//
//    }

 

    @Override
    public void setLatitude(double latitude) {

    }

    @Override
    public void setLongitude(double longitude) {

    }

    @Override
    public ICustomerDetails getCustomerDetails() {
        return null;
    }

    @Override
    public ICustomerPreferences getCustomerPreferences() {
        return null;
    }

    

    @Override
    public void setInitValues(Map<String, String> fieldToValues) {

    }

    public boolean isAllowHotelNotification() {
        return false;
    }
}
