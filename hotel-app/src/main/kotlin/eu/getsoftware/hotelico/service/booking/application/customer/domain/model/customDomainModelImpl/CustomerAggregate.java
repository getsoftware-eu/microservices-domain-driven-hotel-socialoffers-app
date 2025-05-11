package eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.ICustomerDetails;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.ICustomerPreferences;

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
	private final CustomerRootDomainEntity customerRootEntity;
	private final ICustomerDetails customerDetails;
	private final ICustomerPreferences customerPreferences;
	private final CustomerDomainEntityId customerEntityId;

	public CustomerAggregate(CustomerRootDomainEntity customerRootEntity){
		this.customerRootEntity = customerRootEntity;
		this.customerEntityId = customerRootEntity.getDomainEntityId();
//		this.customerBuilder = customerRootEntity.toBuilder();
		this.customerDetails = customerRootEntity.getCustomerDetails();
		this.customerPreferences = customerRootEntity.getCustomerPreferences();
	}

	/**
	 * Entry Point to all Entity setters!!!
	 * @return
	 */
//	public static CustomerRootDomainEntity.CustomerRootDomainEntityBuilder getEntityBuilder(CustomerDomainEntityId customerEntityId){
//		return CustomerRootDomainEntity.builder().domainEntityId(customerEntityId);
//	}

	
//	public static CustomerAggregate buildDomain(CustomerBuilder.CustomerBuilderBuilder domain) {
//		CustomerRootEntity build = domain.build().rootEntityBuild();
//		return new CustomerAggregate(build);
//	}
//
//	// Static method to return a new builder instance
//	public static CustomerBuilder.CustomerBuilderBuilder builder(CustomerEntityId customerEntityId) {
//		return new CustomerBuilder.CustomerBuilderBuilder().customerEntityId(customerEntityId);
//	}
//
//	/**
//	 *
//	 Этот пример кода создан мной для демонстрации того, как можно вручную вынести функциональность @Builder в отдельный класс при помощи библиотеки Lombok. Он основан на общей практике использования Lombok и шаблона проектирования "строитель" (builder pattern).
//
//	 Идея разделения @Builder на отдельный внутренний класс иногда используется для упрощения основного класса или создания более гибкой структуры кода, что может быть полезно в определённых архитектурных ситуациях.
//	 */
//	// Static inner builder class
//	@Builder
//	public static class CustomerBuilder extends CustomerRootDomainEntity {
//
//		private final CustomerEntityId customerEntityId;
//
//		private String firstName;
//		private String lastName;
//		private String status;
//
//		// Build method to return a new Customer instance
//		public CustomerRootEntity rootEntityBuild() {
//			return new CustomerRootEntity(customerEntityId);
//		}
//	}
}
