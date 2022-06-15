package eu.getsoftware.hotelico.hotel.chat.dto;

import java.util.*

data class ChatCustomerDTO(

    var firstName: String? = null,
    
    var lastName: String = "",
    
    var password: String? = null,
    
    var email: String? = null,
    
    var company: String? = null,
    
    var employer: String? = null,
    
    private var city: String? = null,
    
    var originalCity: String? = null,
    
    var sex: String? = null,
    
    var birthdayTime: String? = null,
    
    // private var age: Int? = null,
    
    var country: String? = null,
    
    var status: String? = null,
    
    var jobTitle: String? = null,
    
    var jobDescriptor: String? = null,
    
    var hotelId: Long? = null,
    
    var hotelName: String? = null,
    
    var hotelCity: String? = null,
    
    var hotelCode: String? = null) : eu.getsoftware.hotelico.hotel.chat.dto.BasicDTO()

    /**
     * extra save social network picture url
     */
    private var profileImageUrl: String? = null

    /**
     * calculate current avatarUrl
     */
    private var avatarUrl: String? = null
    
    var errorResponse: String? = null
    
    var prefferedLanguage: String? = null
    
    var website: String? = null
    var education: String? = null
    
    var systemMessages: MutableMap<String, String> = mutableMapOf()
    
    var lastMessageToMe: String? = null
    
    var lastMessageTimeToMe: Long? = 0L
    
    var customerConsistencyId: Long? = 0L
    
    var hotelConsistencyId: Long? = 0L
    
    var points = 0.0
    
    var hotelStaff = false
    
    private var fullCheckin = false
    
    var allowHotelNotification = true
    
    var showInGuestList = true
    
    var checkedIn = false
    
    var admin = false
    
    var logged = false
    
    var online = false
    
    var guestAccount = false
    
    var showAvatar = true
    
    var hideCheckinPopup = false
    
    var hideChromePushPopup = false
    
    var hideHotelListPopup = false
    
    var hideWallPopup = false
    
    var chatWithMe = false
    var inMyHotel = false
    
    var checkinFrom: Date? = null
    
    var checkinTo: Date? = null
    
    var languages: Array<String>? = null
 
    fun getProfileImageUrl(): String? {
       //TODO S3 imageUrl + getId()
        return null;
    }
    
//    fun setProfileImageUrl(profileImageUrl: String) {
//        //TODO S3 imageUrl + getId()
//        this.profileImageUrl = profileImageUrl
//    }
    
//    fun getCity(): String? {
//        return hotelCity ?: city
//    }
//    
//    fun setCity(city: String) {
//        this.city = city
//    }

//Eugen 2020
//    fun getAge(): Int? {
//
//        return if (birthdayTime == null)
//            0
//        else{
//            val birthDate =  Instant.ofEpochMilli(java.lang.Long.parseLong(birthdayTime!!)).atZone(ZoneId.systemDefault()).toLocalDate();
//            //LocalDate.of(java.lang.Long.parseLong(birthdayTime!!))
//            val age = birthDate!!.year - LocalDate.now().year
//            age
//        }
//    }

//    fun getAvatarUrl(): String? {
//        return ControllerUtils.addHostPrefixOnDemand(avatarUrl)
//    }
    
//    fun setAvatarUrl(avatarUrl: String) {
//        avatarUrl = avatarUrl
//    }

//    fun isFullCheckin(): Boolean {
//        return fullCheckin || ControllerUtils.CHECKIN_FULL_ALWAYS
//    }  
    
//    fun setFullCheckin(fullCheckin: Boolean) {
//        this.fullCheckin = fullCheckin
//    }

    fun addSystemMessage(key: String, value: String) {
        systemMessages[key] = value
    }

    //TODO EUGEN: IT BOMBS SERIALIZATION
//    public void setLastMessageTimeToMe(Long lastMessageTimeToMe)
//    {
//        this.lastMessageTimeToMe = lastMessageTimeToMe;
//    }
