package de.hotelico.model;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import de.hotelico.utils.DealStatus;
import de.hotelico.utils.HibernateUtils;

@Entity
@Table(name = "customer_activity_deal")
@AssociationOverrides({
		@AssociationOverride(name = "pk.customer",
				joinColumns = @JoinColumn(name = "CUSTOMER_ID")),
		@AssociationOverride(name = "pk.activity",
				joinColumns = @JoinColumn(name = "ACTIVITY_ID")) })
public class CustomerDeal implements java.io.Serializable {

	private static final long serialVersionUID = -2949611288215768311L;
	
	@Column(name = "active", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
	private boolean active = true;

	private CustomerDealId pk = new CustomerDealId();
	
	@Column
	private String dealCode = "xxx";	
	
	@Column
	private String tablePosition = "";

	@Column(name = "initId", columnDefinition = "BIGINT(20) DEFAULT 0")
	private long initId;
	
	@Column(name = "guestCustomerId", columnDefinition = "BIGINT(20) DEFAULT 0")
	private long guestCustomerId;

	@Column(name = "totalMoney", columnDefinition = "Decimal(6,2) default 0")
	private Double totalMoney;
	
	@Enumerated(EnumType.STRING)
	private DealStatus status;

	@Column(name = "consistencyId", columnDefinition = "BIGINT(20) DEFAULT 0")
	private long consistencyId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "validFrom", nullable = false, length = 10)
	private Date validFrom;

	@Temporal(TemporalType.DATE)
	@Column(name = "validTo", nullable = false, length = 10)
	private Date validTo;
	
	public CustomerDeal() {
	}
	
	public CustomerDeal(Customer customer, HotelActivity activity, long initId) {
		this(customer, activity);
		
		setInitId(initId);
	}
	
	public CustomerDeal(Customer customer, HotelActivity activity) {
		
		this();
		
		setCustomer(customer);
		setActivity(activity);
		generateCode();
		setValidFrom(new Date());

		int validDays = activity.getDealDaysDuration()>0 ? activity.getDealDaysDuration() : 1;
		
		setValidTo(new DateTime().plusDays(validDays).withHourOfDay(4).toDate());

		setInitId(ThreadLocalRandom.current().nextInt(1, 999999));
		setConsistencyId(new Date().getTime());
	}

	
	
	@EmbeddedId
	public CustomerDealId getPk() {
		return pk;
	}

	public void setPk(CustomerDealId pk) {
		this.pk = pk;
	}

	@Transient
	public Customer getCustomer() {
		return getPk().getCustomer();
	}
	
	public void setCustomer(Customer customer) {
		getPk().setCustomer(customer);
	}

	@Transient
	public HotelActivity getActivity() {
		return getPk().getActivity();
	}

	public void setActivity(HotelActivity activity) {
		getPk().setActivity(activity);
	}


	public Date getValidFrom() {
		return this.validFrom;
	}
	

	public Date getValidTo() {
		return this.validTo;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}	
	
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
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

	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
	
	public void generateCode()
	{
		this.dealCode = String.valueOf(ThreadLocalRandom.current().nextInt(1, 999999));
		
	}

	public String getDealCode()
	{
		return dealCode;
	}

	public long getInitId()
	{
		return initId;
	}

	public long getConsistencyId()
	{
		return consistencyId;
	}

	public void setDealCode(String dealCode)
	{
		this.dealCode = dealCode;
	}

	public void setInitId(long initId)
	{
		this.initId = initId;
	}

	public void setConsistencyId(long consistencyId)
	{
		this.consistencyId = consistencyId;
	}

	public long getGuestCustomerId()
	{
		return guestCustomerId;
	}

	public void setGuestCustomerId(long guestCustomerId)
	{
		this.guestCustomerId = guestCustomerId;
	}

	public DealStatus getStatus()
	{
		return status;
	}

	public void setStatus(DealStatus dealStatus)
	{
		this.status = dealStatus;
	}

	public String getTablePosition()
	{
		return tablePosition;
	}

	public void setTablePosition(String tablePosition)
	{
		this.tablePosition = tablePosition;
	}

	public Double getTotalMoney()
	{
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney)
	{
		this.totalMoney = totalMoney;
	}

}