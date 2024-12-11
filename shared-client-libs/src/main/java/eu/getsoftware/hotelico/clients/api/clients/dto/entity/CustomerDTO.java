package eu.getsoftware.hotelico.clients.api.clients.dto.entity;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.dto.BasicDTO;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.With;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DTO lieber immutable - all field are final and no side effects!
 */
@With
@Getter
@SuperBuilder //eu:  will inherit the builder capabilities from the abstract class, allowing you to set fields from both the abstract class and the subclass.
public class CustomerDTO extends BasicDTO<CustomerDomainEntityId>
        
        //all fields are final!!!
{
    @NonNull
    private final String firstName = null;

    private final String lastName = "";
    
    private final CustomerDomainEntityId domainEntityId = new CustomerDomainEntityId("");

    @NonNull
    private final String email = null;

    private final String company = null;

    private final String employer = null;

    @NonNull
    private final String city = "";

    private final String originalCity = null;

    private final String sex = null;

    private final String birthdayTime = null;
    
    // private var age: Int? = null;

    private final String country = null;

    private final String status = null;

    private final String jobTitle = null;

    private final String jobDescriptor = null;

    private final long hotelId = -1;

    private final HotelDTO hotelDTO = null;
    
    /**
     * extra save social network picture url
     */
    private final String profileImageUrl = null;

    /**
     * calculate current avatarUrl
     */
    private final String avatarUrl = null;

    private final String errorResponse = "";

    private final String prefferedLanguage = null;

    private final String website = null;
    private final String education = null;
    
    private final Map<String, String> systemMessages = new HashMap<>();

    private final String lastMessageToMe = null;

    private final long lastMessageTimeToMe = 0L;

    private final long customerConsistencyId = 0L;

    private final long hotelConsistencyId = 0L;
    
    private final double points = 0.0;
    
    public final boolean hotelStaff = false;
    
    @Setter
    private final boolean fullCheckin = false;
    
    private final boolean allowHotelNotification = true;
    
    private final boolean showInGuestList = true;
    
    private final boolean checkedIn = false;
    
    private final boolean admin = false;
    
    private final boolean logged = false;
    
    private final boolean online = false;
    
    private final boolean guestAccount = false;
    
    private final boolean showAvatar = true;
    
    private final boolean hideCheckinPopup = false;
    
    private final boolean hideChromePushPopup = false;
    
    private final boolean hideHotelListPopup = false;
    
    private final boolean hideWallPopup = false;
    
    private final boolean chatWithMe = false;
    
    private final boolean inMyHotel = false;
    
    private final LocalDate checkinFrom = null;
    
    private final LocalDate checkinTo = null;
    
    private final List<String> languages  = null;

    public String getProfileImageUrl() {
        return AppConfigProperties.addHostPrefixOnDemand(profileImageUrl);
    }
    
    public int getAge() {

        if (birthdayTime == null)
            return 0;
    
        LocalDate birthDate = Instant.ofEpochMilli(Long.parseLong(birthdayTime)).atZone(ZoneId.systemDefault()).toLocalDate();  //eugen: DateTime(java.lang.Long.parseLong(birthdayTime!!))
        int age = LocalDate.now().getYear() - birthDate.getYear();  //Years.yearsBetween(birthDate!!.year, DateTime()) //
        
        return age;
    }

    public String getAvatarUrl() {
        return AppConfigProperties.addHostPrefixOnDemand(avatarUrl);
    }
    
//    public void setAvatarUrl(String avatarUrl) {
//        this.avatarUrl = avatarUrl;
//    }

    public boolean isFullCheckin() {
        return fullCheckin || AppConfigProperties.CHECKIN_FULL_ALWAYS;
    }  
    
    public void addSystemMessage(String key, String value) {
        systemMessages.put(key,value);
    }
    
    //TODO EUGEN: IT BOMBS SERIALIZATION
//    public void setLastMessageTimeToMe(Long lastMessageTimeToMe)
//    {
//        this.lastMessageTimeToMe = lastMessageTimeToMe;
//    }
    
//    public CustomerDTO(long initId){
//        super(initId);
//    }
    
//    public void setPassword(String s)
//    {
//        this.password = s;
//    }    
//    
//    public void setLogged(boolean logged)
//    {
//        this.logged = logged;
//    }
//	
//	public void setInMyHotel(boolean b)
//	{
//        this.inMyHotel = b;
//	}
//    
//    public void setHotelId(long id)
//    {
//        this.hotelId = id;
//    }
//    
//    public boolean isAdmin()
//    {
//        return admin;
//    }
    
    /**
     * functional methods, that creates a new instance with custom parameter
//     * @param error
     * @return
     */
//    public CustomerDTO withErrorResponse(String error)
//    {
//        CustomerDTO temp = new CustomerDTO(CustomerDTO.builder()); //.errorResonse(error).build();
//        //temp.errorResponse = error;
//        return temp;
//    }

//    public void setHotelId(long id) {
//        
//        this.hotelId = id;
//    }

    public void setCheckinFrom(Date validFrom) {
    }

    public void setCheckinTo(Date validTo) {
        
    }

    public void setLanguages(List<String> newLanguages) {
    }

    public void setAvatarUrl(String customerAvatarUrl) {
    }

    public void setOnline(boolean customerOnline) {
    }

    public void setCustomerConsistencyId(long consistencyId) {
    }

    public void setBirthdayTime(String s) {
    }

    public String getPassword() {
        return "";
    }

    public void doLogged(boolean b) {
    }

    public LocalDate getLastSeenOnline() {
        return null;
    }

    public void updateLastSeenOnline() {
        
    }

    public String getHotelCode() {
        return "-";
    }

    public String getPushRegistrationId() {
        return null;
    }


//    /**
//     * CustomerDTO dto = new CustomerBuilder(reuiredParams).setOptional1("user").setOptional2(true).build();
//     */
//    public static class CustomerBuilder
//    {
//        /**
//         * eugen: builder-Required parameters!!!!!
//         */
//        
//        private final long initId;
//        @NonNull
//        private final String firstName;
//    
//        /**
//         *  eugen: builder-Optional parameters - initialized to default values!!!!!!
//         */
//        
//        private final String lastName = "";
//        private final String password = "";
//        @NonNull
//        private final String email = "";
//        private final String company = "";
//        private final String employer = "";
//        private final String city = "";
//        private final String originalCity = "";
//        private final String sex = "m";
//        private final String birthdayTime = null;
//        // private var age: Int? = null;
//        private final String country = "";
//        private final String status = "";
//        private final String jobTitle = "";
//        private final String jobDescriptor = "";
//        private final long hotelId = -1;
//        private final String hotelName = "";
//        private final String hotelCity = "";
//        private final String hotelCode = "";
//        /**
//         * extra save social network picture url
//         */
//        private final String profileImageUrl = "";
//        /**
//         * calculate current avatarUrl
//         */
//        private final String avatarUrl = "";
//        private final String prefferedLanguage = "en";
//        private final String website = "";
//        private final String education = "";
//        private final String lastMessageToMe = "";
//        private final long lastMessageTimeToMe = 0L;
//        private final long customerConsistencyId = 0L;
//        private final long hotelConsistencyId = 0L;
//        private double points = 0.0;
//        private final boolean hotelStaff = false;
//        private final boolean fullCheckin = false;
//        private final boolean  allowHotelNotification = true;
//        private final boolean  showInGuestList = true;
//        private final boolean  checkedIn = false;
//        private final boolean  admin = false;
//        private final boolean  logged = false;
//        private final boolean  online = false;
//        private final boolean  guestAccount = false;
//        private final boolean  showAvatar = true;
//        private final boolean  hideCheckinPopup = false;
//        private final boolean  hideChromePushPopup = false;
//        private final boolean  hideHotelListPopup = false;
//        private final boolean  hideWallPopup = false;
//        private final boolean  chatWithMe = false;
//        private final boolean  inMyHotel = false;
//        private LocalDate checkinFrom = null;
//        private LocalDate checkinTo = null;
//    
//        public CustomerBuilder(long initId) {
//            this.initId = initId;
//        }
//        
//        public CustomerBuilder setCity(String city) {
//            this.city = city;
//            return this;
//        }
//        
//        public CustomerBuilder setFirstName(String firstName) {
//            this.firstName = firstName;
//            return this;
//        }
//        
//        public CustomerBuilder setStatus(String status) {
//            this.status = status;
//            return this;
//        }
//    
//        public CustomerBuilder setProfileImageUrl(String profileImageUrl) {
//            this.profileImageUrl = profileImageUrl;
//            return this;
//        }
//    
//        public CustomerDTO build(){
//            return new CustomerDTO(this);
//        }
//    }
    
    // private constructor of DTO for Builder
//    private CustomerDTO(CustomerBuilder builder) {
//        setInitId(builder.initId);
//        email = builder.email;
//        profileImageUrl = builder.profileImageUrl;
//    }
}
