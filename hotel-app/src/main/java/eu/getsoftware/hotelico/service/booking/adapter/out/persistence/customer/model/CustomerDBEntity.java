package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model;

import eu.getsoftware.hotelico.clients.common.utils.HibernateUtils;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.ICustomerDetails;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.ICustomerPreferences;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Root Enity, only one way to get sub Entites
 * main data, will be fetched with every query
 */
@Entity
@NoArgsConstructor//(access = AccessLevel.PROTECTED) // Для Hibernate или других ORM
@Getter 
//@Setter(value = AccessLevel.PACKAGE)
//@Builder(toBuilder = true)
//@AllArgsConstructor(access = AccessLevel.PACKAGE)
//@NoArgsConstructor
@Table(name = "customer", schema = "customer")
@DynamicUpdate
public class CustomerDBEntity extends CustomerRootDomainEntity implements Serializable
{
    @Id
    @Setter(AccessLevel.PROTECTED)
    @Getter
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "customer_id_generator")
    @SequenceGenerator(name="customer_id_generator", sequenceName = "customer_id_seq")
    private long id;

//    public CustomerDBEntity() {
//        super();
//    }

//    @Embedded 
//    @Convert(converter = CustomerDomainEntityIdConverter.class)
//    @Column(name = "domain_id", length = 50) //Аннотирует DomainId как ValueObject,  Вам не нужно явно маппить строковое поле DomainId вручную.
//    public CustomerDomainEntityId getDomainEntityId() {return domainEntityId;} // Ваш Value Object

    @Version
    private Long version;
    
    @Setter
    @Column(name = "active", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
    private boolean active = true;
    
    @Column(name = "logged", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    boolean getLogged() {return logged;};
    
    @Column(name = "showAvatar", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
    private boolean getShowAvatar() {return showAvatar;};
    
    @Column(name = "guestAccount", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean guestAccount = false;
    
    @Column(name = "userNameHotelico")
    private String userNameHotelico;
    
    @NonNull
    @Column(name = "firstName")
    private String firstName;
    
    @NonNull
    @Column(name = "lastName")
    private String lastName;
    
    @Column(name = "sex")
    private String sex;
    
    @Column(name = "website")
    private String website;
    
    @Column(name = "education")
    private String education;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "passwordHash")
    public long getPasswordHash() {return passwordHash;};
    
    @Column(name = "passwordValue", nullable = true)
    private String passwordValue;
    
    @Column(name = "wrongPasswortCounter", columnDefinition = "int(11) DEFAULT 0")
    private int wrongPasswortCounter;
    
    @Column(name = "lastResetPasswordRequestTime", columnDefinition = "BIGINT(20) DEFAULT 0")
    private long lastResetPasswordRequestTime;
    
    @Column(name = "consistencyId", columnDefinition = "BIGINT(20) DEFAULT 0")
    @Setter
    private long consistencyId;
   
    @Column(name = "lastResetPasswordConfirmationTime")
    private Date lastResetPasswordConfirmationTime;
    
    @Column(name = "lastSeenOnline")
    private LocalDate lastSeenOnline;
    
    @Column(name = "email", nullable = true)
    private String email;
    
    @Column(name = "pictureUrl",  length = 250)
    @Setter
    private String pictureUrl;

    @Column
    private String linkedInId;
    
    @Column(name = "mediaUploaded", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean mediaUploaded = false;
    
    @Column
    private String facebookId;
   
    @Setter
    @Column(name = "hotelStaff", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean hotelStaff = false;
    
    @Column(name = "admin", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean admin = false;
    
    @Column(name = "showInGuestList", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
    private boolean showInGuestList = true;
    
    @Column(name = "pushRegistrationId")
    private String pushRegistrationId;
    
//    @ManyToMany(mappedBy= "likedCustomerEntities")
    @Column @ElementCollection
    private Set<String> likedActivityIds = new HashSet<String>();
    
//    @ManyToMany(mappedBy= "subscribeCustomerEntities")
    @Column @ElementCollection
    private Set<String> subscribeActivityIds = new HashSet<String>();
    
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
    
    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private CustomerDetails customerDetails;
    
    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }
    
    //TODO for mapper!
    public void setCustomerDetails(ICustomerDetails icustomerDetails) {
//        this.customerDetails = customerDetails;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private CustomerGPSPosition customerGPSPosition;    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preferences_id")
    private CustomerPreferences customerPreferences;

    public CustomerPreferences getCustomerPreferences(ICustomerPreferences customerPreferences) {
        this.customerPreferences = (CustomerPreferences) customerPreferences;
        return this.customerPreferences;
    }
    
    //TODO for mapper
    public void setCustomerPreferences(ICustomerPreferences customerPreferences) {
//        this.customerPreferences = (CustomerPreferences) customerPreferences;
    }
    
    public ICustomerPreferences getCustomerPreferences() {
        return customerPreferences;
    }

    @Column(name = "points", columnDefinition="Decimal(10,2) default '0.00'")
	private double points = 0.0;
    
    //####################################################

//    public CustomerDBEntity{
//        super();
//    }
    
    public void updateLastSeenOnline()
    {
        this.lastSeenOnline = LocalDate.now();
    }
    
    //################### 1:n:1 ###################################

    //    @Override
    public String getPlainFilePath(final int upperOrderId)
    {
//        if (getId() < upperOrderId)
//        {
//            return String.valueOf(getId());
//        }

        return "customer/" + getId() + "";
    }
    
    //@Override
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

        CustomerDBEntity customerRootEntity = (CustomerDBEntity) o;

        if (id != customerRootEntity.id)
        {
            return false;
        }
        if (hotelStaff != customerRootEntity.hotelStaff)
        {
            return false;
        }
        if (admin != customerRootEntity.admin)
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) id;
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (showAvatar ? 1 : 0);
        result = 31 * result + (int) (lastResetPasswordRequestTime ^ (lastResetPasswordRequestTime >>> 32));
        result = 31 * result + (int) (consistencyId ^ (consistencyId >>> 32));
        result = 31 * result + (linkedInId != null ? linkedInId.hashCode() : 0);
        result = 31 * result + (facebookId != null ? facebookId.hashCode() : 0);
        result = 31 * result + (admin ? 1 : 0);
        
        return result;
    }
    
    //public CustomerAggregate getEntityAggregate()
//    {
//        return new CustomerAggregate(this);
//    }

    @Override
    public String getPrefferedLanguage() {
        return "";
    }

    @Override
    public double getLatitude() {
        return 0;
    }

    @Override
    public double getLongitude() {
        return 0;
    }
    

    public void doLogout() {
    }

    public void doLogged(boolean b) {
    }

    public void doActive(boolean b) {
    }

    public String getProfileImageUrl() {
        return null;
    }

    public Optional<String> getProfileImageLink() {
        return Optional.of(null);
    }
    
}
