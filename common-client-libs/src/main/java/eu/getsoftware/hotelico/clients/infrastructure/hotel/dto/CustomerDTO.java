package eu.getsoftware.hotelico.clients.infrastructure.hotel.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.lang.NonNull;

import eu.getsoftware.hotelico.clients.infrastructure.dto.BasicDTO;
import eu.getsoftware.hotelico.clients.infrastructure.utils.ControllerUtils;
import lombok.Getter;
import lombok.With;
import lombok.experimental.SuperBuilder;

@With
@Getter
@SuperBuilder
public class CustomerDTO<points> extends BasicDTO
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
    
    private List<String> languages  = null;

    public String getProfileImageUrl() {
        return ControllerUtils.addHostPrefixOnDemand(profileImageUrl);
    }
    
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
    
    public String getCity() {
        return hotelCity != null ? hotelCity : city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {

        if (birthdayTime == null)
            return 0;
    
        LocalDate birthDate = Instant.ofEpochMilli(Long.parseLong(birthdayTime)).atZone(ZoneId.systemDefault()).toLocalDate();  //eugen: DateTime(java.lang.Long.parseLong(birthdayTime!!))
        int age = LocalDate.now().getYear() - birthDate.getYear();  //Years.yearsBetween(birthDate!!.year, DateTime()) //
        
        return age;
    }

    public String getAvatarUrl() {
        return ControllerUtils.addHostPrefixOnDemand(avatarUrl);
    }
    
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

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
    
    public void setPassword(String s)
    {
        this.password = s;
    }    
    
    public void setLogged(boolean logged)
    {
        this.logged = logged;
    }
}
