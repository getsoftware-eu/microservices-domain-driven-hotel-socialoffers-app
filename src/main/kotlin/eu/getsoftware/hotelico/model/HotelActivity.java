package eu.getsoftware.hotelico.model;

import eu.getsoftware.hotelico.service.IFileUploadable;
import eu.getsoftware.hotelico.utils.HibernateUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

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
@Getter @Setter
@Table(name = "hotel_activity")
public class HotelActivity implements Serializable, IFileUploadable
{

	private static final long serialVersionUID = -3552760230942289778L;
	
	@Id
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	/**
	 * 0: no Deal number Limit
	 */
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

	@Override
	public String getPlainFilePath(final int upperOrderId)
	{
		return "activity/" + getId() + "";
	}

	@Override
	public void setMediaUploaded(boolean mediaUploaded)
	{
		this.mediaUploaded = mediaUploaded;
	}
	
}
