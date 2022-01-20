package de.hotelico.model;

//import com.sun.glass.ui.android.Activity;
import de.hotelico.service.IFileUploadable;
import de.hotelico.utils.HibernateUtils;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "customer")
@DynamicUpdate
public class Customer implements Serializable, IFileUploadable
{

    private static final long serialVersionUID = -6633762944249828854L;
	
	/**
	 * -180
	 */
	public static final double LOWER_BOUND_LONGITUDE = -180d;
	/**
	 * -90
	 */
	public static final double LOWER_BOUND_LATITUDE = -90d;
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "active", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
    private boolean active = true;    
    
    @Column(name = "logged", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean logged = false;    
    
    @Column(name = "showAvatar", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
    private boolean showAvatar = true;    
    
    @Column(name = "guestAccount", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean guestAccount = false;    
    
    @Column(name = "hideCheckinPopup", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean hideCheckinPopup = false;    
    
    @Column(name = "hideHotelListPopup", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean hideHotelListPopup = false;
    
    @Column(name = "hideWallPopup", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean hideWallPopup = false;   
	
    @Column(name = "hideChromePushPopup", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean hideChromePushPopup = false;
    
    //TODO eugen: check not nullable fields!!!
    
    @Column(name = "userNameHotelico", nullable = true)
    private String userNameHotelico;

    @Column(name = "firstName", nullable = true)
    private String firstName;

    @Column(name = "lastName", nullable = true)
    private String lastName;

    @Column(name = "sex", nullable = true)
    private String sex;      
    
    @Column(name = "website", nullable = true)
    private String website;      
    
    @Column(name = "education", nullable = true)
    private String education;    
    
    @Column(name = "prefferedLanguage", nullable = true)
    private String prefferedLanguage;

    @Column(name = "birthday", nullable = true)
    private Date birthday;    
    
    @Column(name = "status", nullable = true)
    private String status;

    @Column(name = "passwordHash", nullable = true)
    private Long passwordHash;    
    
    @Column(name = "passwordValue", nullable = true)
    private String passwordValue;

    @Column(name = "wrongPasswortCounter", columnDefinition = "int(11) DEFAULT 0")
    private int wrongPasswortCounter;

    @Column(name = "lastResetPasswordRequestTime", columnDefinition = "BIGINT(20) DEFAULT 0")
    private long lastResetPasswordRequestTime;    
    
    @Column(name = "consistencyId", columnDefinition = "BIGINT(20) DEFAULT 0")
    private long consistencyId;

    @Column(name = "lastResetPasswordConfirmationTime")
    private Date lastResetPasswordConfirmationTime;    
    
    @Column(name = "lastSeenOnline")
    private Date lastSeenOnline;
    
    @Column(name = "email", nullable = true)
    private String email;    
    
    @Column(name = "profileImageUrl", nullable = true)
    private String profileImageUrl;

    @Column(name = "company", nullable = true)
    private String company;

    @Column(name = "jobTitle", nullable = true)
    private String jobTitle;

    @Column(name = "city", nullable = true)
    private String city;    
    
    @Column(name = "originalCity", nullable = true)
    private String originalCity;
    
    @Column(name = "country", nullable = true)
    private String country;

    @Column(name = "jobDescriptor", nullable = true)
    private String jobDescriptor;

    @Column(name = "pictureUrl", nullable = true, length = 250)
    private String pictureUrl;

    @Column
    private String linkedInId;

    @Column(name = "mediaUploaded", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean mediaUploaded = false;
    
    @Column
    private String facebookId;    
    
    @Column(name = "hotelStaff", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean hotelStaff = false;   
    
    @Column(name = "admin", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean admin = false;    
    
    @Column(name = "allowHotelNotification", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
    private boolean allowHotelNotification = true;    
    
    @Column(name = "showInGuestList", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
    private boolean showInGuestList = true;
    
    @Column(name = "pushRegistrationId", nullable = true)
    private String pushRegistrationId;
    
    @OneToMany(/*fetch = FetchType.LAZY,*/ mappedBy = "pk.customer", cascade=CascadeType.ALL)
    private Set<CustomerHotelCheckin> customerHotelHistories = new HashSet<CustomerHotelCheckin>(0);

    @ManyToMany(mappedBy="likedCustomers")
    private Set<HotelActivity> likedActivities = new HashSet<HotelActivity>();    
	
	@ManyToMany(mappedBy="subscribeCustomers")
    private Set<HotelActivity> subscribeActivities = new HashSet<HotelActivity>();
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="INTEREST_CUSTOMER_MAP",
            joinColumns={@JoinColumn(name="CUSTOMER_ID")},
            inverseJoinColumns={@JoinColumn(name="INTEREST_ID")})
    private Set<InterestCustomer> interests = new HashSet<InterestCustomer>();

    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="LANGUAGE_CUSTOMER_MAP",
            joinColumns={@JoinColumn(name="CUSTOMER_ID")},
            inverseJoinColumns={@JoinColumn(name="LANGUAGE_ID")})
    private Set<Language> languageSet = new HashSet<Language>();
	
	/**
	 * GPS-Koordinate X (geografische Breite) WGS84.<br />
	 * Format: 0 <= latitude <= 90. Dezimalkomma wird in Dezimalpunkt konvertiert;<br />
	 * Dezimalpunkt und Dezimalkomma sind erlaubt.
	 */
	@Column(name = "latitude", columnDefinition = "double DEFAULT '" + LOWER_BOUND_LATITUDE + "'")
//	@Validate("min=0, max=90")
	private double latitude = LOWER_BOUND_LATITUDE;
	
	/**
	 * GPS-Koordinate Y (geografische Länge) WGS84.<br />
	 * Format: 0 <= longitude <= 180. Dezimalkomma wird in Dezimalpunkt konvertiert<br />
	 * Dezimalpunkt und Dezimalkomma sind erlaubt.
	 */
	@Column(name = "longitude", columnDefinition = "double DEFAULT '" + LOWER_BOUND_LONGITUDE + "'")
//	@Validate("min=0, max=180")
	private double longitude = LOWER_BOUND_LONGITUDE;
	
	@Column(name = "points", columnDefinition="Decimal(10,2) default '0.00'")
	private double points = 0.0;
	
//    //eugen: mappedBy entity.field! ?????
//    @OneToMany(mappedBy="sender")
//    private Set<ChatMessage> sendChatMessages;
//
//    //eugen: mappedBy entity.field! ??????
//    @OneToMany(mappedBy="receiver")
//    private Set<ChatMessage> receivedChatMessages;

    //####################################################
    
//    public Set<ChatMessage> getSendChatMessages()
//    {
//        return sendChatMessages;
//    }

//    public Set<ChatMessage> getReceivedChatMessages()
//    {
//        return receivedChatMessages;
//    }

    public String getProfileImageUrl()
    {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl)
    {
        this.profileImageUrl = profileImageUrl;
    }

//    public void setSendChatMessages(Set<ChatMessage> sendChatMessages)
//    {
//        this.sendChatMessages = sendChatMessages;
//    }
//
//    public void setReceivedChatMessages(Set<ChatMessage> receivedChatMessages)
//    {
//        this.receivedChatMessages = receivedChatMessages;
//    }

    public Customer() {
        super();
    }

    public void setPrefferedLanguage(String prefferedLanguage)
    {
        this.prefferedLanguage = prefferedLanguage;
    }

    public String getPrefferedLanguage()
    {
        return prefferedLanguage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
//        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setShowAvatar(boolean showAvatar)
    {
        this.showAvatar = showAvatar;
    }

    public boolean isShowAvatar()
    {
        return showAvatar;
    }

    public void setAdmin(boolean admin)
    {
        this.admin = admin;
    }

    public boolean isAdmin()
    {
        return admin;
    }
    //    public void setLastSeenOnline(Date lastSeenOnline)
//    {
//        this.lastSeenOnline = lastSeenOnline;
//    }

    public Date getLastSeenOnline()
    {
        return lastSeenOnline;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPasswordHash(Long passwordHash)
    {
        this.passwordHash = passwordHash;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPasswordHash()
    {
        return passwordHash;
    }

    public void setPasswordHash(long passwordHash)
    {
        this.passwordHash = passwordHash;
    }

    public String getEmail()
    {
        return email;
    }

    public String getCompany()
    {
        return company;
    }

    public String getJobTitle()
    {
        return jobTitle;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setCompany(String company)
    {
        this.company = company;
    }

    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }

    public String getStatus()
    {
        return status;
    }

    public String getCountry()
    {
        return country;
    }

    public String getJobDescriptor()
    {
        return jobDescriptor;
    }

    public void setJobDescriptor(String jobDescriptor)
    {
        this.jobDescriptor = jobDescriptor;
    }

    public void setLinkedInId(String linkedInId)
    {
        this.linkedInId = linkedInId;
    }

    public String getLinkedInId()
    {
        return linkedInId;
    }

    public String getPictureUrl()
    {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl)
    {
        this.pictureUrl = pictureUrl;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public void setUserNameHotelico(String userNameHotelico)
    {
        this.userNameHotelico = userNameHotelico;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public void setWrongPasswortCounter(int wrongPasswortCounter)
    {
        this.wrongPasswortCounter = wrongPasswortCounter;
    }

    public void setLastResetPasswordRequestTime(long lastResetPasswordRequestTime)
    {
        this.lastResetPasswordRequestTime = lastResetPasswordRequestTime;
    }

    public void setLastResetPasswordConfirmationTime(Date lastResetPasswordConfirmationTime)
    {
        this.lastResetPasswordConfirmationTime = lastResetPasswordConfirmationTime;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setFacebookId(String facebookId)
    {
        this.facebookId = facebookId;
    }

    public void setHotelStaff(boolean hotelStaff)
    {
        this.hotelStaff = hotelStaff;
    }

    public void setInterests(Set<InterestCustomer> interests)
    {
        this.interests = interests;
    }

    public void setConsistencyId(long consistencyId)
    {
        this.consistencyId = consistencyId;
    }

    public long getConsistencyId()
    {
        return consistencyId;
    }

    public void setLanguageSet(Set<Language> languages)
    {
        this.languageSet = languages;
    }

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public boolean isActive()
    {
        return active;
    }

    public String getUserNameHotelico()
    {
        return userNameHotelico;
    }

    public String getSex()
    {
        return sex;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public int getWrongPasswortCounter()
    {
        return wrongPasswortCounter;
    }

    public long getLastResetPasswordRequestTime()
    {
        return lastResetPasswordRequestTime;
    }

    public Date getLastResetPasswordConfirmationTime()
    {
        return lastResetPasswordConfirmationTime;
    }

    public String getCity()
    {
        return city;
    }

    public String getFacebookId()
    {
        return facebookId;
    }

    public boolean isHotelStaff()
    {
        return hotelStaff;
    }

    public Set<InterestCustomer> getInterests()
    {
        return interests;
    }

    public Set<Language> getLanguageSet()
    {
        return languageSet;
    }

    public void setPasswordValue(String passwordValue)
    {
        this.passwordValue = passwordValue;
    }
    //################### 1:n:1 ###################################
    
    public Set<CustomerHotelCheckin> getCustomerHotelHistories() {
        return this.customerHotelHistories;
    }

    public void setCustomerHotelHistories(Set<CustomerHotelCheckin> customerHotelHistories) {
        this.customerHotelHistories = customerHotelHistories;
    }

    public boolean isLogged()
    {
        return logged;
    }

    public void setLogged(boolean logged)
    {
        this.logged = logged;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setGuestAccount(boolean guestAccount)
    {
        this.guestAccount = guestAccount;
    }

    public boolean isGuestAccount()
    {
        return guestAccount;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public void updateLastSeenOnline()
    {
        this.lastSeenOnline = new Date();
    }

    public String getOriginalCity()
    {
        return originalCity;
    }

    public void setOriginalCity(String originalCity)
    {
        this.originalCity = originalCity;
    }

    public boolean isHideWallPopup()
    {
        return hideWallPopup;
    }

    public void setHideCheckinPopup(boolean hideCheckinPopup)
    {
        this.hideCheckinPopup = hideCheckinPopup;
    }

    public void setHideHotelListPopup(boolean hideHotelListPopup)
    {
        this.hideHotelListPopup = hideHotelListPopup;
    }

    public void setHideWallPopup(boolean hideWallPopup)
    {
        this.hideWallPopup = hideWallPopup;
    }

    public boolean isHideCheckinPopup()
    {
        return hideCheckinPopup;
    }

    public boolean isHideHotelListPopup()
    {
        return hideHotelListPopup;
    }

    @Override
    public String getPlainFilePath(final int upperOrderId)
    {
//        if (getId() < upperOrderId)
//        {
//            return String.valueOf(getId());
//        }

        return "customer/" + getId() + "";
    }

    public String getEducation()
    {
        return education;
    }

    public void setEducation(String education)
    {
        this.education = education;
    }

    public void setLastSeenOnline(Date lastSeenOnline)
    {
        this.lastSeenOnline = lastSeenOnline;
    }

    public Set<HotelActivity> getLikedActivities()
    {
        return likedActivities;
    }

    @Override
    public void setMediaUploaded(boolean mediaUploaded)
    {
        this.mediaUploaded = mediaUploaded;
    }

    public boolean isMediaUploaded()
    {
        return mediaUploaded;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }
	
	public Set<HotelActivity> getSubscribeActivities()
	{
		return subscribeActivities;
	}
	
	
	public String getPushRegistrationId()
    {
        return pushRegistrationId;
    }

    public void setPushRegistrationId(String pushRegistrationId)
    {
        this.pushRegistrationId = pushRegistrationId;
    }
	
	public double getLatitude()
	{
		return latitude;
	}
	
	public double getLongitude()
	{
		return longitude;
	}
	
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

    public boolean isShowInGuestList()
    {
        return showInGuestList;
    }

    public void setShowInGuestList(boolean showInGuestList)
    {
        this.showInGuestList = showInGuestList;
    }

    public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

    public void setAllowHotelNotification(boolean allowHotelNotification)
    {
        this.allowHotelNotification = allowHotelNotification;
    }

    public boolean isAllowHotelNotification()
    {
        return allowHotelNotification;
    }
	
	public Double getPoints()
	{
		return points;
	}
	
	public void setPoints(Double points)
	{
		this.points = points;
	}
	
	public boolean isHideChromePushPopup()
	{
		return hideChromePushPopup;
	}
	
	public void setHideChromePushPopup(boolean hideChromePushPopup)
	{
		this.hideChromePushPopup = hideChromePushPopup;
	}
	
	@Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Customer customer = (Customer) o;

        if (id != customer.id)
        {
            return false;
        }
//        if (active != customer.active)
//        {
//            return false;
//        }
//        if (logged != customer.logged)
//        {
//            return false;
//        }
//        if (showAvatar != customer.showAvatar)
//        {
//            return false;
//        }
//        if (guestAccount != customer.guestAccount)
//        {
//            return false;
//        }
//        if (hideCheckinPopup != customer.hideCheckinPopup)
//        {
//            return false;
//        }
//        if (hideHotelListPopup != customer.hideHotelListPopup)
//        {
//            return false;
//        }
//        if (hideWallPopup != customer.hideWallPopup)
//        {
//            return false;
//        }
//        if (wrongPasswortCounter != customer.wrongPasswortCounter)
//        {
//            return false;
//        }
//        if (lastResetPasswordRequestTime != customer.lastResetPasswordRequestTime)
//        {
//            return false;
//        }
//        if (consistencyId != customer.consistencyId)
//        {
//            return false;
//        }
//        if (mediaUploaded != customer.mediaUploaded)
//        {
//            return false;
//        }
        if (hotelStaff != customer.hotelStaff)
        {
            return false;
        }
        if (admin != customer.admin)
        {
            return false;
        }
//        if (userNameHotelico != null ? !userNameHotelico.equals(customer.userNameHotelico) : customer.userNameHotelico != null)
//        {
//            return false;
//        }
//        if (firstName != null ? !firstName.equals(customer.firstName) : customer.firstName != null)
//        {
//            return false;
//        }
//        if (lastName != null ? !lastName.equals(customer.lastName) : customer.lastName != null)
//        {
//            return false;
//        }
//        if (sex != null ? !sex.equals(customer.sex) : customer.sex != null)
//        {
//            return false;
//        }
//        if (website != null ? !website.equals(customer.website) : customer.website != null)
//        {
//            return false;
//        }
//        if (education != null ? !education.equals(customer.education) : customer.education != null)
//        {
//            return false;
//        }
//        if (prefferedLanguage != null ? !prefferedLanguage.equals(customer.prefferedLanguage) : customer.prefferedLanguage != null)
//        {
//            return false;
//        }
//        if (birthday != null ? !birthday.equals(customer.birthday) : customer.birthday != null)
//        {
//            return false;
//        }
//        if (status != null ? !status.equals(customer.status) : customer.status != null)
//        {
//            return false;
//        }
//        if (passwordHash != null ? !passwordHash.equals(customer.passwordHash) : customer.passwordHash != null)
//        {
//            return false;
//        }
//        if (passwordValue != null ? !passwordValue.equals(customer.passwordValue) : customer.passwordValue != null)
//        {
//            return false;
//        }
//        if (lastResetPasswordConfirmationTime != null ? !lastResetPasswordConfirmationTime.equals(customer.lastResetPasswordConfirmationTime) : customer.lastResetPasswordConfirmationTime != null)
//        {
//            return false;
//        }
//        if (lastSeenOnline != null ? !lastSeenOnline.equals(customer.lastSeenOnline) : customer.lastSeenOnline != null)
//        {
//            return false;
//        }
//        if (email != null ? !email.equals(customer.email) : customer.email != null)
//        {
//            return false;
//        }
//        if (profileImageUrl != null ? !profileImageUrl.equals(customer.profileImageUrl) : customer.profileImageUrl != null)
//        {
//            return false;
//        }
//        if (company != null ? !company.equals(customer.company) : customer.company != null)
//        {
//            return false;
//        }
//        if (jobTitle != null ? !jobTitle.equals(customer.jobTitle) : customer.jobTitle != null)
//        {
//            return false;
//        }
//        if (city != null ? !city.equals(customer.city) : customer.city != null)
//        {
//            return false;
//        }
//        if (originalCity != null ? !originalCity.equals(customer.originalCity) : customer.originalCity != null)
//        {
//            return false;
//        }
//        if (country != null ? !country.equals(customer.country) : customer.country != null)
//        {
//            return false;
//        }
//        if (jobDescriptor != null ? !jobDescriptor.equals(customer.jobDescriptor) : customer.jobDescriptor != null)
//        {
//            return false;
//        }
//        if (pictureUrl != null ? !pictureUrl.equals(customer.pictureUrl) : customer.pictureUrl != null)
//        {
//            return false;
//        }
//        if (linkedInId != null ? !linkedInId.equals(customer.linkedInId) : customer.linkedInId != null)
//        {
//            return false;
//        }
//        if (facebookId != null ? !facebookId.equals(customer.facebookId) : customer.facebookId != null)
//        {
//            return false;
//        }
//        if (customerHotelHistories != null ? !customerHotelHistories.equals(customer.customerHotelHistories) : customer.customerHotelHistories != null)
//        {
//            return false;
//        }
//        if (likedActivities != null ? !likedActivities.equals(customer.likedActivities) : customer.likedActivities != null)
//        {
//            return false;
//        }
//        if (interests != null ? !interests.equals(customer.interests) : customer.interests != null)
//        {
//            return false;
//        }
//        if (languageSet != null ? !languageSet.equals(customer.languageSet) : customer.languageSet != null)
//        {
//            return false;
//        }
//        if (sendChatMessages != null ? !sendChatMessages.equals(customer.sendChatMessages) : customer.sendChatMessages != null)
//        {
//            return false;
//        }
        return true;

    }

    @Override
    public int hashCode()
    {
        int result = (int)id;
        result = 31 * result + (active ? 1 : 0);
//        result = 31 * result + (logged ? 1 : 0);
        result = 31 * result + (showAvatar ? 1 : 0);
//        result = 31 * result + (guestAccount ? 1 : 0);
//        result = 31 * result + (hideCheckinPopup ? 1 : 0);
//        result = 31 * result + (hideHotelListPopup ? 1 : 0);
//        result = 31 * result + (hideWallPopup ? 1 : 0);
//        result = 31 * result + (userNameHotelico != null ? userNameHotelico.hashCode() : 0);
//        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
//        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
//        result = 31 * result + (sex != null ? sex.hashCode() : 0);
//        result = 31 * result + (website != null ? website.hashCode() : 0);
////        result = 31 * result + (education != null ? education.hashCode() : 0);
//        result = 31 * result + (prefferedLanguage != null ? prefferedLanguage.hashCode() : 0);
//        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
//        result = 31 * result + (status != null ? status.hashCode() : 0);
//        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
//        result = 31 * result + (passwordValue != null ? passwordValue.hashCode() : 0);
//        result = 31 * result + wrongPasswortCounter;
//        result = 31 * result + (int) (lastResetPasswordRequestTime ^ (lastResetPasswordRequestTime >>> 32));
        result = 31 * result + (int) (consistencyId ^ (consistencyId >>> 32));
//        result = 31 * result + (lastResetPasswordConfirmationTime != null ? lastResetPasswordConfirmationTime.hashCode() : 0);
//        result = 31 * result + (lastSeenOnline != null ? lastSeenOnline.hashCode() : 0);
//        result = 31 * result + (email != null ? email.hashCode() : 0);
//        result = 31 * result + (profileImageUrl != null ? profileImageUrl.hashCode() : 0);
//        result = 31 * result + (company != null ? company.hashCode() : 0);
//        result = 31 * result + (jobTitle != null ? jobTitle.hashCode() : 0);
//        result = 31 * result + (city != null ? city.hashCode() : 0);
//        result = 31 * result + (originalCity != null ? originalCity.hashCode() : 0);
//        result = 31 * result + (country != null ? country.hashCode() : 0);
//        result = 31 * result + (jobDescriptor != null ? jobDescriptor.hashCode() : 0);
//        result = 31 * result + (pictureUrl != null ? pictureUrl.hashCode() : 0);
        result = 31 * result + (linkedInId != null ? linkedInId.hashCode() : 0);
//        result = 31 * result + (mediaUploaded ? 1 : 0);
        result = 31 * result + (facebookId != null ? facebookId.hashCode() : 0);
//        result = 31 * result + (hotelStaff ? 1 : 0);
        result = 31 * result + (admin ? 1 : 0);
//        result = 31 * result + (customerHotelHistories != null ? customerHotelHistories.hashCode() : 0);
//        result = 31 * result + (likedActivities != null ? likedActivities.hashCode() : 0);
//        result = 31 * result + (interests != null ? interests.hashCode() : 0);
//        result = 31 * result + (languageSet != null ? languageSet.hashCode() : 0);
//        result = 31 * result + (sendChatMessages != null ? sendChatMessages.hashCode() : 0);
//        result = 31 * result + (receivedChatMessages != null ? receivedChatMessages.hashCode() : 0);
        return result;
    }
}
