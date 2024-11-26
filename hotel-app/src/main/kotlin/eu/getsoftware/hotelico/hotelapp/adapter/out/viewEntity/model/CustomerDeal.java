package eu.getsoftware.hotelico.hotelapp.adapter.out.viewEntity.model;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.DealStatus;
import eu.getsoftware.hotelico.clients.common.utils.HibernateUtils;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.model.HotelDbActivity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.hotelapp.application.deal.domain.ICustomerDeal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Getter @Setter
@Table(name = "customer_activity_deal", schema = "deal")
//@AssociationOverrides({
//		@AssociationOverride(name = "pk.customer",
//				joinColumns = @JoinColumn(name = "CUSTOMER_ID")),
//		@AssociationOverride(name = "pk.activity",
//				joinColumns = @JoinColumn(name = "ACTIVITY_ID")) })
public class CustomerDeal implements ICustomerDeal {
	
	@Column(name = "active", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
	private boolean active = true;

	private CustomerDealId pk = new CustomerDealId();
	
	@Column
	private String dealCode = "xxx";	
	
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
	
	public CustomerDeal() {
	}
	
	public CustomerDeal(CustomerDBEntity customerEntity, HotelDbActivity activity, long initId) {
		this(customerEntity, activity);
		this.initId = initId;
	}
	
	public CustomerDeal(CustomerDBEntity customerEntity, HotelDbActivity activity) {
		
		this();
		
		setCustomerId(customerEntity.getDomainEntityId());
		setActivity(activity);
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

	@Override
	public CustomerDomainEntityId getCustomerId() {
		return null;
	}

	public void setCustomerId(CustomerDomainEntityId customerEntityId) {
		getPk().setCustomerId(customerEntityId);
	}

	@Transient
	public HotelDbActivity getActivity() {
		return getPk().getActivity();
	}

	public void setActivity(HotelDbActivity activity) {
		getPk().setActivity(activity);
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CustomerDeal that = (CustomerDeal) o;

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