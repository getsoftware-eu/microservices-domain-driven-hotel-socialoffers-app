package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotelCustomer.model;

import eu.getsoftware.hotelico.domain.utils.HibernateUtils;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.HotelRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.iPortService.IFileUploadable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Eugen on 16.07.2015.
 */
@Entity
@Getter @Setter
@Table(name = "hotel_activity", schema = "hotel")
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
	private HotelRootEntity hotelRootEntity;

	@ManyToOne
	@JoinColumn(name="senderId")
	private CustomerRootEntity sender;

	@Temporal(TemporalType.DATE)
	@Column(name = "validFrom", length = 10)
	private Date validFrom;

	@Temporal(TemporalType.DATE)
	@Column(name = "validTo", length = 10)
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

	@Column(name = "pictureUrl", length = 250)
	private String pictureUrl;	
	
	@Column(name = "previewPictureUrl", length = 250)
	private String previewPictureUrl;
	
	@Column(name = "mediaUploaded", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean mediaUploaded = false;

	@ManyToMany(cascade = { CascadeType.ALL})
	@JoinTable(name="CUSTOMER_LIKED_ACTIVITIES",
			joinColumns={@JoinColumn(name="ACTIVITY_ID")},
			inverseJoinColumns={@JoinColumn(name="CUSTOMER_ID")}) //TODO Eugen:, nullable = false, updatable = true
	private Set<CustomerRootEntity> likedCustomerEntities = new HashSet<CustomerRootEntity>();	
	
	@ManyToMany(cascade = { CascadeType.ALL})
	@JoinTable(name="CUSTOMER_SUBSCRIBE_ACTIVITIES",
			joinColumns={@JoinColumn(name="ACTIVITY_ID")},
			inverseJoinColumns={@JoinColumn(name="CUSTOMER_ID")}) //TODO Eugen:, nullable = false, updatable = true
	private Set<CustomerRootEntity> subscribeCustomerEntities = new HashSet<CustomerRootEntity>();
	
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
