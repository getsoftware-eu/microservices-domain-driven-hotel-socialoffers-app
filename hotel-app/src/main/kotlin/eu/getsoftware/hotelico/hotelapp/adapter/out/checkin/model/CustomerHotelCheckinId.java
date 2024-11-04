package eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.model;

/**
 * Created by Eugen on 16.07.2015.
 * Many-To-Many intersection table between Customers and Hotels
 * 
 * @Embeddable embedded by other entities:  it does not have independent existence. 
 * Thus we cannot run DB queries, without depending on other class
 */

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
class CustomerHotelCheckinId implements java.io.Serializable {

	private static final long serialVersionUID = 552154191271568694L;
	
	@Getter
	@Setter
	private String customerEntityId;
	
	@Getter
	@Setter
	private String hotelEntityId;

//	@ManyToOne //Bad für splitting Microservices!!!
//	public CustomerRootEntity getCustomer() {
//		return customerEntity;
//	}
	
//	public void setCustomer(CustomerRootEntity stock) {
//		this.customerEntity = stock;
//	}

//	@ManyToOne
//	public HotelRootEntity getHotel() {
//		return hotelRootEntity;
//	}
//
//	public void setHotel(HotelRootEntity category) {
//		this.hotelRootEntity = category;
//	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CustomerHotelCheckinId that = (CustomerHotelCheckinId) o;

		if (customerEntityId != null ? !customerEntityId.equals(that.customerEntityId) : that.customerEntityId != null) return false;
		if (hotelEntityId != null ? !hotelEntityId.equals(that.hotelEntityId) : that.hotelEntityId != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (customerEntityId != null ? customerEntityId.hashCode() : 0);
		result = 31 * result + (hotelEntityId != null ? hotelEntityId.hashCode() : 0);
		return result;
	}

}