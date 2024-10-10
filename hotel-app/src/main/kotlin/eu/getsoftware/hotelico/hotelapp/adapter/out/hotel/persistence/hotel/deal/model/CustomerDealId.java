package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.deal.model;

/**
 * Created by Eugen on 16.07.2015.
 */

import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.checkin.model.HotelActivity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.customer.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.deal.domain.ICustomerDealId;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Setter;

@Embeddable
class CustomerDealId implements ICustomerDealId {

	private CustomerRootEntity customerEntity;
	
	@Setter
	private HotelActivity activity;

	@ManyToOne
	public CustomerRootEntity getCustomer() {
		return customerEntity;
	}

	public void setCustomer(CustomerRootEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	@ManyToOne
	public HotelActivity getActivity() {
		return activity;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CustomerDealId that = (CustomerDealId) o;

		if (customerEntity != null ? !customerEntity.equals(that.customerEntity) : that.customerEntity != null) return false;
		if (activity != null ? !activity.equals(that.activity) : that.activity != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (customerEntity != null ? customerEntity.hashCode() : 0);
		result = 31 * result + (activity != null ? activity.hashCode() : 0);
		return result;
	}

}