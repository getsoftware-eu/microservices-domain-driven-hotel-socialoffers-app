//package eu.getsoftware.hotelico.service.booking.adapter.out.checkin.model;
//
///**
// * Created by Eugen on 16.07.2015.
// * Many-To-Many intersection table between Customers and Hotels
// * 
// * @Embeddable embedded by other entities:  it does not have independent existence. 
// * Thus we cannot run DB queries, without depending on other class
// */
//
//import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
//import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
//import jakarta.persistence.Embeddable;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;
//
//@Embeddable
//@EqualsAndHashCode
//public class CustomerHotelCheckinId implements java.io.Serializable {
//
//	private static final long serialVersionUID = 552154191271568694L;
//	
//	@Getter
//	@Setter
//	private CustomerDomainEntityId customerEntityId;
//	
//	@Getter
//	@Setter
//	private HotelDomainEntityId hotelEntityId;
//
////	@ManyToOne //Bad für splitting Microservices!!!
////	public CustomerRootEntity getCustomer() {
////		return customerEntity;
////	}
//	
////	public void setCustomer(CustomerRootEntity stock) {
////		this.customerEntity = stock;
////	}
//
////	@ManyToOne
////	public HotelRootEntity getHotel() {
////		return hotelRootEntity;
////	}
////
////	public void setHotel(HotelRootEntity category) {
////		this.hotelRootEntity = category;
////	}
//
//}