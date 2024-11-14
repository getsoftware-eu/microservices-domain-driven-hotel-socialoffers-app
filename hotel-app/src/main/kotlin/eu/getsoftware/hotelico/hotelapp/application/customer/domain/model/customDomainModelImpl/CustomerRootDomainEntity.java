package eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.customDomainModelImpl;

import eu.getsoftware.hotelico.clients.common.domain.IRootDomainEntity;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerDetails;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerPreferences;
import lombok.*;

import java.util.Map;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRootDomainEntity implements IRootDomainEntity {

    protected CustomerDomainEntityId domainEntityId;

    protected boolean hotelStaff;
    protected String firstName;
    protected String lastName;
    protected String status;

    protected boolean logged;
    protected boolean allowHotelNotification;

    protected String passwordValue;

    protected long passwordHash;

    private  boolean guestAccount;

    private boolean active;

    protected String sex;

    protected boolean showAvatar;

    protected  boolean showInGuestList;

    protected String  email ;

    protected String linkedInId;

    protected String facebookId;

    protected String pushRegistrationId;

    protected boolean admin;

    protected String prefferedLanguage;

    protected double latitude;

    protected double longitude;

    public ICustomerDetails customerDetails;
    public ICustomerPreferences customerPreferences;

    @Override
    public void setInitValues(Map<String, String> fieldToValues) {
        
    }

//    public CustomerAggregate getCustomerAggregate(){
//        return new CustomerAggregate(this);
//    }
    
}
