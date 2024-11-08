package eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.model;

/**
 * Created by Eugen on 16.07.2015.
 * Many-To-Many intersection table between Customers and Hotels
 * 
 * @Embeddable embedded by other entities:  it does not have independent existence. 
 * Thus we cannot run DB queries, without depending on other class
 */

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode
class CustomerHotelCheckinId implements java.io.Serializable {

	private static final long serialVersionUID = 552154191271568694L;
	
	@Getter
	@Setter
	private long customerEntityId;
	
	@Getter
	@Setter
	private long hotelEntityId;

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

}