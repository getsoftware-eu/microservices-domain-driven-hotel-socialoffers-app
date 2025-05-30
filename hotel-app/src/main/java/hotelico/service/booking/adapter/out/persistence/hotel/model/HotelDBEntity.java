package hotelico.service.booking.adapter.out.persistence.hotel.model;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.HibernateUtils;
import hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDbActivity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

//@Builder
//@AllArgsConstructor

@NoArgsConstructor(access = AccessLevel.PROTECTED) // Для Hibernate или других ORM
@Entity
@Getter @Setter
@Table(name = "hotel", schema = "hotel")
//@RequiredArgsConstructor - eu: NEVER FOR ENTITY
public class HotelDBEntity /*extends HotelRootDomainEntity*/ implements Serializable
{
    private static final long serialVersionUID = -4429239383913774205L;
	
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "hotel_id_generator")
    @SequenceGenerator(name="hotel_id_generator", sequenceName = "hotel_id_seq")
    private long hotel_id;

//    @Embedded
//    @Convert(converter = HotelDomainEntityIdConverter.class)
    @Embedded @Column(name = "domain_entity_id", length = 50)
    public HotelDomainEntityId domainEntityId;

//    @Convert(converter = DomainEntityIdConverter.class)
    @Embedded @Column(name = "address", length = 50)
    public AddressDBEmbeddable address;

    @Column(name = "name", nullable = false)
    public String name;
    
    @Version
    private Long version;
    
    @Column(name = "rating", nullable = true)
    private Integer rating;

    @Column(name = "consistencyId", columnDefinition = "BIGINT(20) DEFAULT 0")
    private long consistencyId;    
    
    @Column(name = "creationTime", columnDefinition = "BIGINT(20) DEFAULT 0")
    private long creationTime;
    
    @Column(name = "active", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
    private boolean active = true;
    
    @Column(name = "virtual", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean virtual = false;
    
    @Column(name = "welcomeMessage", nullable = false)
    private String welcomeMessage;
   
    @Column(name = "description", nullable = true, columnDefinition = "LONGTEXT")
    @JdbcTypeCode(SqlTypes.LONGVARCHAR) // Use LONGVARCHAR for long text fields

    private String description;    
    
    @Column(name = "info", nullable = true, columnDefinition = "LONGTEXT")
    @JdbcTypeCode(SqlTypes.LONGVARCHAR) // Use LONGVARCHAR for long text fields

    private String info;

    @Column(name = "city", nullable = true)
    private String city;

    @Column(name = "postalCode", nullable = true)
    private String postalCode;    
    
    @Column(name = "website", nullable = true)
    private String website;    
    
    @Column(name = "mapLink", nullable = true)
    private String mapLink;

    @Column(name = "street", nullable = true)
    private String street;

    @Column(name = "house", nullable = true)
    private String house;

    @Column(name = "phone", nullable = true)
    private String phone;

    @Column(name = "fax", nullable = true)
    private String fax;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "currentHotelAccessCode", nullable = true)
    private String currentHotelAccessCode;
    
    @Column(name = "previewPictureUrl", nullable = true, length = 250)
    private String previewPictureUrl;    
    
    @Column(name = "pictureUrl", nullable = true, length = 250)
    private String pictureUrl;    
    
	//TODO convert string data to own structure
    @Column(name = "guestPushIds", nullable = true, columnDefinition="LONGTEXT")
    private String guestPushIds = "";    
	
	@Column(name = "unsubscribeNotificationPushIds", nullable = true, columnDefinition="LONGTEXT")
    private String unsubscribeNotificationPushIds = "";

//    @ElementCollection(targetClass= CheckinRootDomainEntity.class)
//    @OneToMany(/*fetch = FetchType.LAZY,*/ mappedBy = "pk.hotel")
    @Column @ElementCollection
    private List<Long> customerHotelHistories = listOf();

//    @ElementCollection(targetClass=WallPost.class)
//    @OneToMany(/*fetch = FetchType.LAZY,*/ mappedBy = "pkw.hotel")
//    private Set<WallPost> wallPosts = new HashSet<WallPost>(0);

    //eugen: mappedBy entity!
//    @OneToMany(mappedBy= "hotel")
//    private Set<HotelWallPost> hotelWallPosts;
    
    @Column @ElementCollection
    private Set<String> hotelWallPostIds = new HashSet<String>();
    
    //eugen: mappedBy entity!
//    @OneToMany(mappedBy= "hotelRootEntity")
//    private Set<HotelDbActivity> hotelActivities;

    @Column @ElementCollection
    private Set<String> hotelActivityIds = new HashSet<String>();
    
    @Column(name = "mediaUploaded", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean mediaUploaded = false;
	
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
    
    @ElementCollection
    private Collection<String> getStaffIdList;

//    @Override
    public Set<HotelWallPost> hotelWallPosts() {
        return Set.of();
    }

//    @Override
    public Set<HotelDbActivity> hotelActivities() {
        return Set.of();
    }

    public long getId() {
        return hotel_id;
    }
    
    public String getPlainFilePath(final int upperOrderId)
    {
        return "hotel/" + getId() + "";
    }

    public void setMediaUploaded(boolean mediaUploaded)
    {
        this.mediaUploaded = mediaUploaded;
    }

//    @Override
    public Collection<CustomerDomainEntityId> getStaffIdList() {
        return getStaffIdList.stream().map(CustomerDomainEntityId::new).toList();
    }

    /**
	 * -180
	 */
	public static final double LOWER_BOUND_LONGITUDE = -180d;
	/**
	 * -90
	 */
	public static final double LOWER_BOUND_LATITUDE = -90d;
}
