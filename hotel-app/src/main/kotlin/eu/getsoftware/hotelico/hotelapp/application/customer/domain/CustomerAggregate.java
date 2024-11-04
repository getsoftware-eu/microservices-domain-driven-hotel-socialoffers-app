package eu.getsoftware.hotelico.hotelapp.application.customer.domain;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerEntityId;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerDetails;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerPreferences;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

/**
 * TODO DO I  NEED Aggregate DUPLICATION, OR JUST RequestDTO and ResponseDTO?????
 * 
 * Maybe automate with Reflection????
 * 
 * Aggregate for sub Entities: Any internal structural change will be not visible outside!
 * 
 * all setters for customerRootEntity, outside via aggregate: not direct jpa-entity setter logik!!!
 * and all setters and getters for hidden sub-classes
 */
public class CustomerAggregate
{
//	private final CustomerRootEntityBuilder customerBuilder;
	private final CustomerRootEntity customerRootEntity;
	private final ICustomerDetails customerDetails;
	private final ICustomerPreferences customerPreferences;
	private final CustomerEntityId customerEntityId;

	public CustomerAggregate(CustomerRootEntity customerRootEntity){
		this.customerRootEntity = customerRootEntity;
		this.customerEntityId = customerRootEntity.getEntityId();
//		this.customerBuilder = customerRootEntity.toBuilder();
		this.customerDetails = customerRootEntity.getCustomerDetails();
		this.customerPreferences = customerRootEntity.getCustomerPreferences();
	}

	/**
	 * Entry Point to all Entity setters!!!
	 * @return
	 */
//	public static CustomerRootEntity.CustomerRootEntityBuilder getEntityBuilder(CustomerEntityId customerEntityId){
//		return CustomerRootEntity.builder();
//	}

	public static CustomerAggregate buildDomain(CustomerBuilder.CustomerBuilderBuilder domain) {
		CustomerRootEntity build = domain.build().rootEntityBuild();
		return new CustomerAggregate(build);
	}

	public CustomerEntityId getDomainEntityId() {
		return customerRootEntity.getEntityId();
	}

	// Static method to return a new builder instance
	public static CustomerBuilder.CustomerBuilderBuilder builder(CustomerEntityId customerEntityId) {
		return new CustomerBuilder(customerEntityId).CustomerBuilderBuilder();
	}

	// Static inner builder class
	@Builder
	@RequiredArgsConstructor
	public static class CustomerBuilder extends ICustomerRootEntity {

		private final CustomerEntityId customerEntityId;

		private String firstName;
		private String lastName;
		private String status;

//		CustomerBuilder(CustomerEntityId customerEntityId)
//		{
//			this.customerEntityId = customerEntityId;
//		}
		
		// Build method to return a new Customer instance
		public CustomerRootEntity rootEntityBuild() {
			return new CustomerRootEntity(customerEntityId);
		}
	}
}
