package eu.getsoftware.hotelico.hotelCustomer.adapter.out.persistence;

import eu.getsoftware.hotelico.common.utils.HibernateUtils;
import eu.getsoftware.hotelico.hotel.adapter.out.persistence.model.hotel.HotelRootEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "customer_hotel_checkin")
@AssociationOverrides({
		@AssociationOverride(name = "pk.customer",
				joinColumns = @JoinColumn(name = "CUSTOMER_ID")),
		@AssociationOverride(name = "pk.hotel",
				joinColumns = @JoinColumn(name = "HOTEL_ID")) })
public class CustomerHotelCheckin implements java.io.Serializable {

	private static final long serialVersionUID = -2949611288215768311L;
	
	@Column(name = "active", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
	private boolean active = true;

	private CustomerHotelCheckinId pk = new CustomerHotelCheckinId();
	
	@Column(name = "fullCheckin", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean fullCheckin = false;	
	
	@Column(name = "staffCheckin", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean staffCheckin = false;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "validFrom", nullable = false, length = 10)
	private Date validFrom;

	@Temporal(TemporalType.DATE)
	@Column(name = "validTo", nullable = false, length = 10)
	private Date validTo;
	
	public CustomerHotelCheckin() {
		super();
	}

	@EmbeddedId
	public CustomerHotelCheckinId getPk() {
		return pk;
	}

	public void setPk(CustomerHotelCheckinId pk) {
		this.pk = pk;
	}
	
	public void setCustomer(CustomerRootEntity customerEntity) {
		getPk().setCustomer(customerEntity);
	}

	@Transient
	public HotelRootEntity getHotel() {
		return getPk().getHotel();
	}
	
	@Transient
	public CustomerRootEntity getCustomer() {
		return getPk().getCustomer();
	}
	
	public boolean isStaffCheckin()
	{
		return staffCheckin;
	}

	public void setStaffCheckin(boolean staffCheckin)
	{
		this.staffCheckin = staffCheckin;
	}

	public void setHotel(HotelRootEntity category) {
		getPk().setHotel(category);
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

	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}

	public boolean isFullCheckin()
	{
		return fullCheckin;
	}

	public void setFullCheckin(boolean fullCheckin)
	{
		this.fullCheckin = fullCheckin;
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		CustomerHotelCheckin that = (CustomerHotelCheckin) o;
		
		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;
		
		return true;
	}
}