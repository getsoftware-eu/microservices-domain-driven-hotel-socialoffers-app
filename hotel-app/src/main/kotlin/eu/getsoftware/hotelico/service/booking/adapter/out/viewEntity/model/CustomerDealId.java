package eu.getsoftware.hotelico.service.booking.adapter.out.viewEntity.model;

/**
 * Created by Eugen on 16.07.2015.
 */

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDbActivity;
import eu.getsoftware.hotelico.service.booking.application.deal.domain.ICustomerDealId;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
class CustomerDealId implements ICustomerDealId, Serializable {

	private CustomerDomainEntityId customerEntityId;
	
	@Setter
	private HotelDbActivity activity;

//	@ManyToOne
//	@Embedded
//	@Convert(converter = CustomerDomainEntityIdConverter.class)
//	public CustomerDomainEntityId getCustomerId() {
//		return customerEntityId;
//	}

	public void setCustomerId(CustomerDomainEntityId customerEntity) {
		this.customerEntityId = customerEntity;
	}

	@ManyToOne
	public HotelDbActivity getActivity() {
		return activity;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CustomerDealId that = (CustomerDealId) o;

		if (customerEntityId != null ? !customerEntityId.equals(that.customerEntityId) : that.customerEntityId != null) return false;
		if (activity != null ? !activity.equals(that.activity) : that.activity != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (customerEntityId != null ? customerEntityId.hashCode() : 0);
		result = 31 * result + (activity != null ? activity.hashCode() : 0);
		return result;
	}

}