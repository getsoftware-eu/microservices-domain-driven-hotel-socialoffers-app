package de.hotelico.model;

import de.hotelico.service.IFileUploadable;
import de.hotelico.utils.HibernateUtils;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hotel")
public class Hotel implements Serializable, IFileUploadable
{

    private static final long serialVersionUID = -4429239383913774205L;
	
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
    //Eugen: NEVER MAKE setId !!!!!!!!!!
    private long id;
    
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
    
    @Column(name = "name", nullable = false)
    private String name;    
    
    @Column(name = "wellcomeMessage", nullable = false)
    private String wellcomeMessage;
   
    @Column(name = "description", nullable = true, columnDefinition = "LONGTEXT")
    @Type(type = "text")
    private String description;    
    
    @Column(name = "info", nullable = true, columnDefinition = "LONGTEXT")
    @Type(type = "text")
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
    
    @Column(name = "guestPushIds", nullable = true, columnDefinition="LONGTEXT")
    private String guestPushIds = "";    
	
	@Column(name = "unsubscribeNotificationPushIds", nullable = true, columnDefinition="LONGTEXT")
    private String unsubscribeNotificationPushIds = "";

    @ElementCollection(targetClass=CustomerHotelCheckin.class)
    @OneToMany(/*fetch = FetchType.LAZY,*/ mappedBy = "pk.hotel")
    private Set<CustomerHotelCheckin> customerHotelHistories = new HashSet<CustomerHotelCheckin>(0);

//    @ElementCollection(targetClass=WallPost.class)
//    @OneToMany(/*fetch = FetchType.LAZY,*/ mappedBy = "pkw.hotel")
//    private Set<WallPost> wallPosts = new HashSet<WallPost>(0);

    //eugen: mappedBy entity!
    @OneToMany(mappedBy="hotel")
    private Set<HotelWallPost> hotelWallPosts;
    
    //eugen: mappedBy entity!
    @OneToMany(mappedBy="hotel")
    private Set<HotelActivity> hotelActivities;

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
    
    public Hotel() {
        super();
    }

    public long getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public String getCity()
    {
        return city;
    }

    public void setId(int id)
    {
//        this.id = id;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public String getStreet()
    {
        return street;
    }

    public String getHouse()
    {
        return house;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getFax()
    {
        return fax;
    }

    public String getEmail()
    {
        return email;
    }

    public String getCurrentHotelAccessCode()
    {
        return currentHotelAccessCode;
    }

    public String getPictureUrl()
    {
        return pictureUrl;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public void setHouse(String house)
    {
        this.house = house;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public void setEmail(String eMail)
    {
        this.email = eMail;
    }

    public void setCurrentHotelAccessCode(String currentHotelAccessCode)
    {
        this.currentHotelAccessCode = currentHotelAccessCode;
    }

    public void setPictureUrl(String pictureUrl)
    {
        this.pictureUrl = pictureUrl;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public void setHotelActivities(Set<HotelActivity> hotelActivities)
    {
        this.hotelActivities = hotelActivities;
    }

    public boolean isActive()
    {
        return active;
    }

    public Set<HotelActivity> getHotelActivities()
    {
        return hotelActivities;
    }
    //##################### 1:n:1 ###################################

    public Set<CustomerHotelCheckin> getCustomerHotelHistories() {
        return this.customerHotelHistories;
    }

    public void setCustomerHotelHistories(Set<CustomerHotelCheckin> customerHotelHistories) {
        this.customerHotelHistories = customerHotelHistories;
    }

    public Set<HotelWallPost> getHotelWallPosts()
    {
        return hotelWallPosts;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public void setMapLink(String mapLink)
    {
        this.mapLink = mapLink;
    }

    public String getWebsite()
    {
        return website;
    }

    public String getMapLink()
    {
        return mapLink;
    }

    public Integer getRating()
    {
        return rating;
    }

    public long getCreationTime()
    {
        return creationTime;
    }

    public void setCreationTime(long creationTime)
    {
        this.creationTime = creationTime;
    }

    public void setRating(Integer rating)
    {
        this.rating = rating;
    }

    public void setHotelWallPosts(Set<HotelWallPost> hotelWallPosts)
    {
        this.hotelWallPosts = hotelWallPosts;
    }

    //    public Set<WallPost> getWallPosts() {
//        return this.wallPosts;
//    }
//
//    public void setWallPosts(Set<WallPost> wallPosts) {
//        this.wallPosts = wallPosts;
//    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }
    // #############################################################################

    @Override
    public String getPlainFilePath(final int upperOrderId)
    {
        //        if (getId() < upperOrderId)
        //        {
        //            return String.valueOf(getId());
        //        }

        return "hotel/" + getId() + "";
    }

    @Override
    public void setMediaUploaded(boolean mediaUploaded)
    {
        this.mediaUploaded = mediaUploaded;
    }

    public long getConsistencyId()
    {
        return consistencyId;
    }

    public void setConsistencyId(long consistencyId)
    {
        this.consistencyId = consistencyId;
    }

    public boolean isMediaUploaded()
    {
        return mediaUploaded;
    }

    public boolean isVirtual()
    {
        return virtual;
    }

    public void setVirtual(boolean virtual)
    {
        this.virtual = virtual;
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
	
	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

    public String getPreviewPictureUrl()
    {
        return previewPictureUrl;
    }

    public void setPreviewPictureUrl(String previewPictureUrl)
    {
        this.previewPictureUrl = previewPictureUrl;
    }

    public String getGuestPushIds()
    {
        return guestPushIds;
    }

    public void setGuestPushIds(String guestPushIds)
    {
        this.guestPushIds = guestPushIds;
    }

    public String getWellcomeMessage()
    {
        return wellcomeMessage;
    }

    public void setWellcomeMessage(String wellcomeMessage)
    {
        this.wellcomeMessage = wellcomeMessage;
    }
	
	public String getUnsubscribeNotificationPushIds()
	{
		return unsubscribeNotificationPushIds;
	}
	
	public void setUnsubscribeNotificationPushIds(String unsubscribeNotificationPushIds)
	{
		this.unsubscribeNotificationPushIds = unsubscribeNotificationPushIds;
	}
}
