package de.hotelico.model;

import de.hotelico.service.IFileUploadable;
import de.hotelico.utils.HibernateUtils;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Eugen on 16.07.2015.
 */
@Entity
@Table(name = "hotel_activity")
public class HotelActivity implements Serializable, IFileUploadable
{

	private static final long serialVersionUID = -3552760230942289778L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	/**
	 * messageId -> creationTime
	 * consistencyId -> last update time
	 */
	@Column(name = "initId", columnDefinition = "BIGINT(20) DEFAULT 0")
	private long initId = 0;

	/**
	 * messageId -> creationTime
	 * consistencyId -> last update time
	 */
	@Column(name = "consistencyId", columnDefinition = "BIGINT(20) DEFAULT 0")
	private long consistencyId;

	@Column(name = "orderIndex", nullable = false, columnDefinition = "int(11) DEFAULT 0")
	private int orderIndex = 0;
	
	@Column(name = "active", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
	private boolean active = true;
	
	@ManyToOne
	@JoinColumn(name="hotelId")
	private Hotel hotel;

	@ManyToOne
	@JoinColumn(name="senderId")
	private Customer sender;

	@Temporal(TemporalType.DATE)
	@Column(name = "validFrom", nullable = true, length = 10)
	private Date validFrom;

	@Temporal(TemporalType.DATE)
	@Column(name = "validTo", nullable = true, length = 10)
	private Date validTo;
	
	@Column
	private String title;
	
	@Column
	private String shortDescription;
	
	@Column(name = "activityArea", nullable = false)
	private String activityArea;
	
	@Column( columnDefinition = "LONGTEXT")
	@Type(type = "text")
	private String description;	
	
	@Column
	private boolean thirdPartyActivity;

	@Column(name = "pictureUrl", nullable = true, length = 250)
	private String pictureUrl;	
	
	@Column(name = "previewPictureUrl", nullable = true, length = 250)
	private String previewPictureUrl;
	
	@Column(name = "mediaUploaded", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean mediaUploaded = false;

	@ManyToMany(cascade = { CascadeType.ALL})
	@JoinTable(name="CUSTOMER_LIKED_ACTIVITIES",
			joinColumns={@JoinColumn(name="ACTIVITY_ID")},
			inverseJoinColumns={@JoinColumn(name="CUSTOMER_ID")}) //TODO Eugen:, nullable = false, updatable = true
	private Set<Customer> likedCustomers = new HashSet<Customer>();	
	
	@ManyToMany(cascade = { CascadeType.ALL})
	@JoinTable(name="CUSTOMER_SUBSCRIBE_ACTIVITIES",
			joinColumns={@JoinColumn(name="ACTIVITY_ID")},
			inverseJoinColumns={@JoinColumn(name="CUSTOMER_ID")}) //TODO Eugen:, nullable = false, updatable = true
	private Set<Customer> subscribeCustomers = new HashSet<Customer>();
	
	@Column(name = "dealAllowed", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
	private boolean dealAllowed = true;	
	
	@Column(name = "hidden", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean hidden = false;
	
	@Column(name = "lastMinute", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean lastMinute = false;
	
	@Column(name = "dealDaysDuration", nullable = false, columnDefinition = "int default 1")
	private int dealDaysDuration = 1;	
	
	@Column(name = "limitDealNumber", nullable = false, columnDefinition = "int default 1")
	private int limitDealNumber = 1;
	
	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	public long getId()
	{
		return id;
	}

	public boolean isActive()
	{
		return active;
	}

	public Hotel getHotel()
	{
		return hotel;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public void setHotel(Hotel hotel)
	{
		this.hotel = hotel;
	}

	public Date getValidFrom()
	{
		return validFrom;
	}

	public Date getValidTo()
	{
		return validTo;
	}

	public String getTitle()
	{
		return title;
	}

	public String getShortDescription()
	{
		return shortDescription;
	}
	

	public Long getInitId()
	{
		return initId;
	}

	public String getDescription()
	{
		return description;
	}

	public void setPictureUrl(String pictureUrl)
	{
		this.pictureUrl = pictureUrl;
	}

	public String getPictureUrl()
	{
		return pictureUrl;
	}

	public boolean isThirdPartyActivity()
	{
		return thirdPartyActivity;
	}

	public void setValidFrom(Date validFrom)
	{
		this.validFrom = validFrom;
	}

	public void setValidTo(Date validTo)
	{
		this.validTo = validTo;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setShortDescription(String shortDescription)
	{
		this.shortDescription = shortDescription;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setThirdPartyActivity(boolean thirdPartyActivity)
	{
		this.thirdPartyActivity = thirdPartyActivity;
	}

	public Customer getSender()
	{
		return sender;
	}

	public void setSender(Customer sender)
	{
		this.sender = sender;
	}

	@Override
	public String getPlainFilePath(final int upperOrderId)
	{
		//        if (getId() < upperOrderId)
		//        {
		//            return String.valueOf(getId());
		//        }

		return "activity/" + getId() + "";
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

	public void setLikedCustomers(Set<Customer> likedCustomers)
	{
		this.likedCustomers = likedCustomers;
	}

	public Set<Customer> getLikedCustomers()
	{
		return likedCustomers;
	}
	
	public Set<Customer> getSubscribeCustomers()
	{
		return subscribeCustomers;
	}
	
	public void setSubscribeCustomers(Set<Customer> subscribeCustomers)
	{
		this.subscribeCustomers = subscribeCustomers;
	}

	public int getDealDaysDuration()
	{
		return dealDaysDuration;
	}

	public void setId(int id)
	{
//		this.id = id;
	}
	
	/**
	 * 0: no Deal number Limit
	 * @return
	 */
	public int getLimitDealNumber()
	{
		return limitDealNumber;
	}
	
	
	public boolean isDealAllowed()
	{
		return dealAllowed;
	}
	
	public void setDealAllowed(boolean dealAllowed)
	{
		this.dealAllowed = dealAllowed;
	}
	
	public void setDealDaysDuration(int dealDaysDuration)
	{
		this.dealDaysDuration = dealDaysDuration;
	}
	
	/**
	 * 0: no Deal number Limit
	 * @return
	 */
	public void setLimitDealNumber(int limitDealNumber)
	{
		this.limitDealNumber = limitDealNumber;
	}
	
	public boolean isLastMinute()
	{
		return lastMinute;
	}
	
	public void setLastMinute(boolean lastMinute)
	{
		this.lastMinute = lastMinute;
	}
	
	public String getPreviewPictureUrl()
	{
		return previewPictureUrl;
	}
	
	public void setPreviewPictureUrl(String previewPictureUrl)
	{
		this.previewPictureUrl = previewPictureUrl;
	}
	
	public String getActivityArea()
	{
		return activityArea;
	}
	
	public void setActivityArea(String activityArea)
	{
		this.activityArea = activityArea;
	}

	public boolean isHidden()
	{
		return hidden;
	}

	public void setHidden(boolean showActivity)
	{
		this.hidden = showActivity;
	}

	public void setInitId(long initId)
	{
		this.initId = initId;
	}
	
	
	public int getOrderIndex()
	{
		return orderIndex;
	}
	
	public void setOrderIndex(int orderIndex)
	{
		this.orderIndex = orderIndex;
	}
}
