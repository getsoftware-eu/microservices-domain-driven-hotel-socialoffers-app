package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.deal.model;

/**
 * Created by Eugen on 16.07.2015.
 */

import eu.getsoftware.hotelico.clients.common.domain.ids.ActivityDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.application.deal.domain.ICustomerDealId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@Getter
class CustomerDealId implements ICustomerDealId, Serializable {

	@Column
	private String customerDomainEntityIdValue;

	@Column
	private String hotelDomainEntityIdValue;
	
	@Column
	private String activityDomainEntityIdValue;

//	@ManyToOne
//	@Embedded
//	@Convert(converter = CustomerDomainEntityIdConverter.class)
//	public CustomerDomainEntityId getCustomerId() {
//		return customerEntityId;
//	}

	public void setCustomerDomainEntityId(CustomerDomainEntityId customerEntity) {
		this.customerDomainEntityIdValue = customerEntity.uuidValue();
	}

	public ActivityDomainEntityId getActivityDomainEntityId() {
		return ActivityDomainEntityId.from(activityDomainEntityIdValue);
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CustomerDealId that = (CustomerDealId) o;

		if (customerDomainEntityIdValue != null ? !customerDomainEntityIdValue.equals(that.customerDomainEntityIdValue) : that.customerDomainEntityIdValue != null) return false;
		if (activityDomainEntityIdValue != null ? !activityDomainEntityIdValue.equals(that.activityDomainEntityIdValue) : that.activityDomainEntityIdValue != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (customerDomainEntityIdValue != null ? customerDomainEntityIdValue.hashCode() : 0);
		result = 31 * result + (activityDomainEntityIdValue != null ? activityDomainEntityIdValue.hashCode() : 0);
		return result;
	}

	public void setActivityDomainEntityId(ActivityDomainEntityId activity) {
		this.activityDomainEntityIdValue = activity.uuidValue();
	}
}