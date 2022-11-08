package eu.getsoftware.hotelico.domain.deal;

/**
 * Created by Eugen on 16.07.2015.
 */

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import eu.getsoftware.hotelico.domain.customer.CustomerRootEntity;
import eu.getsoftware.hotelico.domain.customer.HotelActivity;

@Embeddable
class CustomerDealId implements java.io.Serializable {

	private static final long serialVersionUID = 552154181271568694L;
	
	
	private CustomerRootEntity customerEntity;
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

	public void setActivity(HotelActivity activity) {
		this.activity = activity;
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