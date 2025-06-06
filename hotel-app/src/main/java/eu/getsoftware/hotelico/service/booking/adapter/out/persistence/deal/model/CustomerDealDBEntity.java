package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.deal.model;

import eu.getsoftware.hotelico.clients.common.domain.ids.ActivityDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.DealStatus;
import eu.getsoftware.hotelico.clients.common.utils.HibernateUtils;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelActivityDBEntity;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.service.booking.application.deal.domain.ICustomerDeal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * eu: this example with @Embedded
 * eu: Customer has @OneToOne with Details
 */
@Entity
@Getter @Setter
@Table(name = "customer_activity_deals", schema = "customer")
//@AssociationOverrides({
//		@AssociationOverride(name = "pk.customer",
//				joinColumns = @JoinColumn(name = "CUSTOMER_ID")),
//		@AssociationOverride(name = "pk.activity",
//				joinColumns = @JoinColumn(name = "ACTIVITY_ID")) })
public class CustomerDealDBEntity implements ICustomerDeal {
	
	@Column(name = "active", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
	private boolean active = true;

	private CustomerDealId pk = new CustomerDealId();
	
	@Getter
	@Column
	private String dealCode = "xxx";	
	
	public String getDealCode() {
		return dealCode;
	}
	
	@Column
	private String tablePosition = "";

	@Column(name = "initId", columnDefinition = "BIGINT(20) DEFAULT 0")
	private long initId;
	
//	@Column(name = "guestCustomerId", columnDefinition = "BIGINT(20) DEFAULT 0")
//	@Embedded
//	@Convert(converter = CustomerDomainEntityIdConverter.class)
//	private CustomerDomainEntityId guestCustomerId;

	@Column(name = "totalMoney", columnDefinition = "Decimal(6,2) default 0")
	private Double totalMoney;
	
	@Enumerated(EnumType.STRING)
	private DealStatus status;

	@Column(name = "consistencyId", columnDefinition = "BIGINT(20) DEFAULT 0")
	private long consistencyId = System.currentTimeMillis();
	
	@Temporal(TemporalType.DATE)
	@Column(name = "validFrom", nullable = false, length = 10)
	private Date validFrom = new Date();

	@Temporal(TemporalType.DATE)
	@Column(name = "validTo", nullable = false, length = 10)
	private Date validTo;
	
	public CustomerDealDBEntity() {
	}
	
	public CustomerDealDBEntity(CustomerDBEntity customerEntity, HotelActivityDBEntity activity, long initId) {
		this(customerEntity, activity);
		this.initId = initId;
	}
	
	public CustomerDealDBEntity(CustomerDBEntity customerEntity, HotelActivityDBEntity activity) {
		
		this();
		
		setCustomerDomainEntityId(customerEntity.getDomainEntityId());
		setActivityDomainEntityId(activity.getDomainEntityId());
		generateCode();

		int validDays = activity.getDealDaysDuration()>0 ? activity.getDealDaysDuration() : 1;
		this.validTo = convertToDate(LocalDateTime.now().plusDays(validDays).withHour(4));
		this.initId = ThreadLocalRandom.current().nextInt(1, 999999);
	}
	
	private Date convertToDate(LocalDateTime localDateTime)
	{
		return java.sql.Timestamp.valueOf(localDateTime);
	}
	
	@EmbeddedId
	public CustomerDealId getPk() {
		return pk;
	}

//	@Transient
//	public CustomerDomainEntityId getCustomerId() {
//		return getPk().getCustomerId();
//	}

	@Override @Transient
	public CustomerDomainEntityId getCustomerDomainEntityId() {
		return null;
	}

	public void setCustomerDomainEntityId(CustomerDomainEntityId customerEntityId) {
		getPk().setCustomerDomainEntityId(customerEntityId);
	}

	@Transient
	public ActivityDomainEntityId getActivityDomainEntityId() {
		return getPk().getActivityDomainEntityId();
	}
	
	public void setActivityDomainEntityId(ActivityDomainEntityId activity) {
		getPk().setActivityDomainEntityId(activity);
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CustomerDealDBEntity that = (CustomerDealDBEntity) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
							: that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
	
	public void generateCode()
	{
		this.dealCode = String.valueOf(ThreadLocalRandom.current().nextInt(1, 999999));
	}

}