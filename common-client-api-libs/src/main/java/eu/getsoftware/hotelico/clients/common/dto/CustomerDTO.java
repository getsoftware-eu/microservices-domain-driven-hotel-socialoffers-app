package eu.getsoftware.hotelico.clients.common.dto;

import eu.getsoftware.hotelico.common.dto.BasicDTO;
import eu.getsoftware.hotelico.common.utils.ControllerUtils;
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
public class CustomerDTO extends BasicDTO
        
        //all fields are final!!!
{
    @NonNull
    private String firstName = null;

    private String lastName = "";

    private String password = null;
    
    @NonNull
    private String email = null;

    private String company = null;

    private String employer = null;

    private String city = null;

    private String originalCity = null;

    private String sex = null;

    private String birthdayTime = null;
    
    // private var age: Int? = null;

    private String country = null;

    private String status = null;

    private String jobTitle = null;

    private String jobDescriptor = null;

    private long hotelId = -1;

    private String hotelName = null;

    private String hotelCity = null;

    private String hotelCode = null;

    /**
     * extra save social network picture url
     */
    private String profileImageUrl = null;

    /**
     * calculate current avatarUrl
     */
    private String avatarUrl = null;

    private String errorResponse = null;

    private String prefferedLanguage = null;

    private String website = null;
    private String education = null;
    
    private Map<String, String> systemMessages = new HashMap<>();

    private String lastMessageToMe = null;

    private long lastMessageTimeToMe = 0L;

    private long customerConsistencyId = 0L;

    private long hotelConsistencyId = 0L;
    
    private double points = 0.0;
    
    public boolean hotelStaff = false;
    
    private boolean fullCheckin = false;
    
    private boolean  allowHotelNotification = true;
    
    private boolean  showInGuestList = true;
    
    private boolean  checkedIn = false;
    
    private boolean  admin = false;
    
    private boolean  logged = false;
    
    private boolean  online = false;
    
    private boolean  guestAccount = false;
    
    private boolean  showAvatar = true;
    
    private boolean  hideCheckinPopup = false;
    
    private boolean  hideChromePushPopup = false;
    
    private boolean  hideHotelListPopup = false;
    
    private boolean  hideWallPopup = false;
    
    private boolean  chatWithMe = false;
    
    @Setter
    private boolean  inMyHotel = false;
    
    private Date checkinFrom = null;
    
    private Date checkinTo = null;
    
    private List<String> languages  = null;

    public String getProfileImageUrl() {
        return ControllerUtils.addHostPrefixOnDemand(profileImageUrl);
    }
    
    public int getAge() {

        if (birthdayTime == null)
            return 0;
    
        LocalDate birthDate = Instant.ofEpochMilli(Long.parseLong(birthdayTime)).atZone(ZoneId.systemDefault()).toLocalDate();  //eugen: DateTime(java.lang.Long.parseLong(birthdayTime!!))
        int age = LocalDate.now().getYear() - birthDate.getYear();  //Years.yearsBetween(birthDate!!.year, DateTime()) //
        
        return age;
    }

    public String getCity() {
        return hotelCity != null ? hotelCity : city;
    }
    
    public String getAvatarUrl() {
        return ControllerUtils.addHostPrefixOnDemand(avatarUrl);
    }
    
//    public void setAvatarUrl(String avatarUrl) {
//        this.avatarUrl = avatarUrl;
//    }

    public boolean isFullCheckin() {
        return fullCheckin || ControllerUtils.CHECKIN_FULL_ALWAYS;
    }  
    
    public void setFullCheckin(boolean fullCheckin) {
        this.fullCheckin = fullCheckin;
    }

    public void addSystemMessage(String key, String value) {
        systemMessages.put(key,value);
    }
    
    public void setErrorResponse(String msg){
        this.errorResponse = msg;
    }
    
    //TODO EUGEN: IT BOMBS SERIALIZATION
//    public void setLastMessageTimeToMe(Long lastMessageTimeToMe)
//    {
//        this.lastMessageTimeToMe = lastMessageTimeToMe;
//    }
    
    public CustomerDTO(long initId){
        super(initId);
    }
    
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
     * @param error
     * @return
     */
    public CustomerDTO withErroResponse(String error)
    {
        CustomerDTO temp = new CustomerDTO(0);
        temp.errorResponse = error;
        return temp;
    }
    
    /**
     * CustomerDTO dto = new CustomerBuilder(reuiredParams).setOptional1("user").setOptional2(true).build();
     */
    public static class CustomerBuilder
    {
        /**
         * eugen: builder-Required parameters!!!!!
         */
        
        private long initId;
        @NonNull
        private String firstName;
    
        /**
         *  eugen: builder-Optional parameters - initialized to default values!!!!!!
         */
        
        private String lastName = "";
        private String password = "";
        @NonNull
        private String email = "";
        private String company = "";
        private String employer = "";
        private String city = "";
        private String originalCity = "";
        private String sex = "m";
        private String birthdayTime = null;
        // private var age: Int? = null;
        private String country = "";
        private String status = "";
        private String jobTitle = "";
        private String jobDescriptor = "";
        private long hotelId = -1;
        private String hotelName = "";
        private String hotelCity = "";
        private String hotelCode = "";
        /**
         * extra save social network picture url
         */
        private String profileImageUrl = "";
        /**
         * calculate current avatarUrl
         */
        private String avatarUrl = "";
        private String prefferedLanguage = "en";
        private String website = "";
        private String education = "";
        private String lastMessageToMe = "";
        private long lastMessageTimeToMe = 0L;
        private long customerConsistencyId = 0L;
        private long hotelConsistencyId = 0L;
        private double points = 0.0;
        private boolean hotelStaff = false;
        private boolean fullCheckin = false;
        private boolean  allowHotelNotification = true;
        private boolean  showInGuestList = true;
        private boolean  checkedIn = false;
        private boolean  admin = false;
        private boolean  logged = false;
        private boolean  online = false;
        private boolean  guestAccount = false;
        private boolean  showAvatar = true;
        private boolean  hideCheckinPopup = false;
        private boolean  hideChromePushPopup = false;
        private boolean  hideHotelListPopup = false;
        private boolean  hideWallPopup = false;
        private boolean  chatWithMe = false;
        private boolean  inMyHotel = false;
        private Date checkinFrom = null;
        private Date checkinTo = null;
    
        public CustomerBuilder(long initId) {
            this.initId = initId;
        }
        
        public CustomerBuilder setCity(String city) {
            this.city = city;
            return this;
        }
        
        public CustomerBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        
        public CustomerBuilder setEnabled(String email) {
            this.email = email;
            return this;
        }
    
        public CustomerBuilder setProfileImageUrl(String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
            return this;
        }
    
        public CustomerDTO build(){
            return new CustomerDTO(this);
        }
    }
    
    // private constructor of DTO for Builder
    private CustomerDTO(CustomerBuilder builder) {
        setInitId(builder.initId);
        email = builder.email;
        profileImageUrl = builder.profileImageUrl;
    }
}
