package eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl;

import eu.getsoftware.hotelico.clients.common.domain.IRootDomainEntity;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.ICustomerDetails;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.ICustomerPreferences;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.AddressValueObject;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
public class CustomerRootDomainEntity implements IRootDomainEntity<CustomerDomainEntityId> {

    @NonNull //Обязательные параметры должны быть отмечены аннотацией @NonNull, чтобы Lombok автоматически генерировал проверки и исключения, если значение не предоставлено.
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
    protected String password;

    public ICustomerDetails customerDetails;
    public ICustomerPreferences customerPreferences;
    private AddressValueObject address;

    /**
     * eu: JE HOCHER VALIDATION TO MODEL, DESTO MEHR WERDEN ES automatisch benutzen.
     * VALIDATAION in einem Service ist nur begrenzt nutzbar!!!
     *
     * we use not ANEMIC, but RICH model here (Entity methods with business logik)
     * therefore Business logik is here, not in JPAEntity, that implement this interface and it's business rules!!!
     * @return high consistency rule about password
     */
    public boolean isPasswordValid() {
        return password != null && password.length() > 5;
    }
    
    public void setAddress(@Validated AddressValueObject address) {
        this.address = address;
    }

    @Override
    public void setInitValues(Map fieldToValues) {
        
    }

    public CustomerDomainEntityId getDomainEntityId() {
        return domainEntityId;
    }
//    public CustomerAggregate getCustomerAggregate(){
//        return new CustomerAggregate(this);
//    }
    
}
