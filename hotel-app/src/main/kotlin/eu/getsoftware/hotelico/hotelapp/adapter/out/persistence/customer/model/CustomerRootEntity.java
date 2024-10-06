package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model;

import eu.getsoftware.hotelico.clients.common.utils.HibernateUtils;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotelCustomer.model.HotelActivity;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.CustomerAggregate;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerDetails;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerPreferences;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IFileUploadable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Root Enity, only one way to get sub Entites
 * main data, will be fetched with every query
 */
@Entity
@Getter @Setter(value = AccessLevel.PACKAGE)
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Table(name = "customer", schema = "customer")
@DynamicUpdate
public class CustomerRootEntity implements ICustomerRootEntity, Serializable, IFileUploadable
{
    @Id
    @Setter(AccessLevel.PROTECTED)
    @Getter
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "customer_id_generator")
    @SequenceGenerator(name="customer_id_generator", sequenceName = "customer_id_seq")
    private long id;
    
    @Column(columnDefinition = "BINARY(16)")
    private UUID customerUUID;
    
    @Column(name = "active", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
    private boolean active = true;
    
    @Column(name = "logged", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean logged = false;
    
    @Column(name = "showAvatar", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
    private boolean showAvatar = true;
    
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
    private Long passwordHash;
    
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
    private Date lastSeenOnline;
    
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
   
    @Column(name = "hotelStaff", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean hotelStaff = false;
    
    @Column(name = "admin", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean admin = false;
    
    @Column(name = "showInGuestList", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
    private boolean showInGuestList = true;
    
    @Column(name = "pushRegistrationId")
    private String pushRegistrationId;
    
    @ManyToMany(mappedBy= "likedCustomerEntities")
    private Set<HotelActivity> likedActivities = new HashSet<HotelActivity>();
    
    @ManyToMany(mappedBy= "subscribeCustomerEntities")
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

    public ICustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private CustomerGPSPosition customerGPSPosition;    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preferences_id")
    private CustomerPreferences customerPreferences;

    public ICustomerPreferences getCustomerPreferences() {
        return customerPreferences;
    }

    @Column(name = "points", columnDefinition="Decimal(10,2) default '0.00'")
	private double points = 0.0;
    
    //####################################################
    
    @Override
    public void setPasswordHash(long passwordHash) {
        
    }

    public void updateLastSeenOnline()
    {
        this.lastSeenOnline = new Date();
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

        CustomerRootEntity customerRootEntity = (CustomerRootEntity) o;

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
    
    public CustomerAggregate getEntityAggregate()
    {
        return new CustomerAggregate(this);
    }
	
}
