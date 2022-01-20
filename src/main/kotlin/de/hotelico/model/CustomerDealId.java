package de.hotelico.model;

/**
 * Created by Eugen on 16.07.2015.
 */

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class CustomerDealId implements java.io.Serializable {

	private static final long serialVersionUID = 552154181271568694L;
	
	
	private Customer customer;
	private HotelActivity activity;

	@ManyToOne
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

		if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
		if (activity != null ? !activity.equals(that.activity) : that.activity != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (customer != null ? customer.hashCode() : 0);
		result = 31 * result + (activity != null ? activity.hashCode() : 0);
		return result;
	}

}