package eu.getsoftware.hotelico.hotel.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import eu.getsoftware.hotelico.hotel.service.IFileUploadable;
import eu.getsoftware.hotelico.hotel.utils.HibernateUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "customer")
@DynamicUpdate
public class Customer implements Serializable, IFileUploadable
{
    private static final long serialVersionUID = -6633762944249828854L;
    
    @Id
    @Setter(AccessLevel.PROTECTED)
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
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private CustomerDetails customerDetails;
    
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
    
    public Customer() {
        super();
    } 
    
    public Customer(String _firstName, String _lastName) {
        this();
        this.firstName = _firstName;
        this.lastName = _lastName;
    }

    public long getId(){ return this.id; }
    
    public void updateLastSeenOnline()
    {
        this.lastSeenOnline = new Date();
    }
    
    //################### 1:n:1 ###################################
    
    @Override
    public String getPlainFilePath(final int upperOrderId)
    {
//        if (getId() < upperOrderId)
//        {
//            return String.valueOf(getId());
//        }

        return "customer/" + getId() + "";
    }
    
    @Override
    public void setMediaUploaded(boolean mediaUploaded)
    {
        this.mediaUploaded = mediaUploaded;
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
        if (hotelStaff != customer.hotelStaff)
        {
            return false;
        }
        if (admin != customer.admin)
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int)id;
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (showAvatar ? 1 : 0);
        result = 31 * result + (int) (lastResetPasswordRequestTime ^ (lastResetPasswordRequestTime >>> 32));
        result = 31 * result + (int) (consistencyId ^ (consistencyId >>> 32));
        result = 31 * result + (linkedInId != null ? linkedInId.hashCode() : 0);
        result = 31 * result + (facebookId != null ? facebookId.hashCode() : 0);
        result = 31 * result + (admin ? 1 : 0);
        
        return result;
    }
}
