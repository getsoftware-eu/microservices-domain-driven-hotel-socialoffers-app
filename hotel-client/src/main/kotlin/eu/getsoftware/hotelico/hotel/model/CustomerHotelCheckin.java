package eu.getsoftware.hotelico.hotel.model;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import eu.getsoftware.hotelico.hotel.utils.HibernateUtils;
import lombok.Data;


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
	}

	@EmbeddedId
	public CustomerHotelCheckinId getPk() {
		return pk;
	}

	public void setPk(CustomerHotelCheckinId pk) {
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
	public Hotel getHotel() {
		return getPk().getHotel();
	}

	public boolean isStaffCheckin()
	{
		return staffCheckin;
	}

	public void setStaffCheckin(boolean staffCheckin)
	{
		this.staffCheckin = staffCheckin;
	}

	public void setHotel(Hotel category) {
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
}