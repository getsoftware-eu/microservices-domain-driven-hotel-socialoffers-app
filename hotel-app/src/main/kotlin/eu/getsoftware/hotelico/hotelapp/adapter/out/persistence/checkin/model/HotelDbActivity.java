package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.model;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.HibernateUtils;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.IHotelActivity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Eugen on 16.07.2015.
 */
@Entity
@Getter @Setter
@Table(name = "hotel_activity", schema = "hotel")
public class HotelDbActivity implements IHotelActivity, Serializable
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
	
	@Getter
	@Embedded @Column  
	public HotelDomainEntityId hotelDomainId;
	
	@ManyToOne
	@JoinColumn(name="senderDomainId")
	private CustomerDBEntity sender;

	@Temporal(TemporalType.DATE)
	@Column(name = "validFrom", length = 10)
	private LocalDate validFrom;

	@Temporal(TemporalType.DATE)
	@Column(name = "validTo", length = 10)
	private LocalDate validTo;
	
	@Column
	private String title;
	
	@Column
	private String shortDescription;
	
	@Column(name = "activityArea", nullable = false)
	private String activityArea;
	
	@Column( columnDefinition = "LONGTEXT")
	@JdbcTypeCode(SqlTypes.LONGVARCHAR) // Use LONGVARCHAR for long text fields

	private String description;	
	
	@Column
	private boolean thirdPartyActivity;

	@Column(name = "pictureUrl", length = 250)
	private String pictureUrl;	
	
	@Column(name = "previewPictureUrl", length = 250)
	private String previewPictureUrl;
	
	@Column(name = "mediaUploaded", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean mediaUploaded = false;
	
//	@ManyToMany(cascade = { CascadeType.ALL})
//	@JoinTable(name="CUSTOMER_LIKED_ACTIVITIES",
//			joinColumns={@JoinColumn(name="ACTIVITY_ID")},
//			inverseJoinColumns={@JoinColumn(name="CUSTOMER_ID")}) //TODO Eugen:, nullable = false, updatable = true
	@ElementCollection
	@CollectionTable(name = "liked_customer_domain_ids")
	@Column(name = "value")
	private Set<String> likedCustomerDomainEntityIds = new HashSet<>();	
	
//	@ManyToMany(cascade = { CascadeType.ALL})
//	@JoinTable(name="CUSTOMER_SUBSCRIBE_ACTIVITIES",
//			joinColumns={@JoinColumn(name="ACTIVITY_ID")},
//			inverseJoinColumns={@JoinColumn(name="CUSTOMER_ID")}) //TODO Eugen:, nullable = false, updatable = true
	@ElementCollection
	@CollectionTable(name = "subscribe_customer_domain_ids")
	@Column(name = "value")
	private Set<String> subscribeCustomerDomainEntityIds = new HashSet<>();
	
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

	/**
	 * we can not map Set<CustomerDomainEntityId> , only if set @Embeddable in Domain ValueObject. But we did not want to pollute domain Layer!
	 * @return
	 */
	public void setLikedCustomerDomainEntityIds(Collection<CustomerDomainEntityId> domainIds) {

        this.likedCustomerDomainEntityIds = domainIds.stream()
				.map(CustomerDomainEntityId::uuidValue)
				.collect(Collectors.toSet());
	}
	
	public Set<CustomerDomainEntityId> getLikedCustomerDomainEntityIds() {
		return likedCustomerDomainEntityIds.stream()
					.map(CustomerDomainEntityId::new)
					.collect(Collectors.toSet());
	}
	
	/**
	 * we can not map Set<CustomerDomainEntityId> , only if set @Embeddable in Domain ValueObject. But we did not want to pollute domain Layer!
	 * @return
	 */
	public void setSubscribeCustomerDomainEntityIds(Collection<CustomerDomainEntityId> domainIds) {

		this.subscribeCustomerDomainEntityIds = domainIds.stream()
				.map(CustomerDomainEntityId::uuidValue)
				.collect(Collectors.toSet());
	}
	
	public Set<CustomerDomainEntityId> getSubscribeCustomerDomainEntityIds() {
		return subscribeCustomerDomainEntityIds.stream()
					.map(CustomerDomainEntityId::new)
					.collect(Collectors.toSet());
	}


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

	@Override
	public String senderId() {
		return "";
	}

}
