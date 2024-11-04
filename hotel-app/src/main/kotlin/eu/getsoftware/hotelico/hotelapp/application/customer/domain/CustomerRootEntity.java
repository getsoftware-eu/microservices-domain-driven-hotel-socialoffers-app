package eu.getsoftware.hotelico.hotelapp.application.customer.domain;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerEntityId;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;

//@Builder
class CustomerRootEntity implements ICustomerRootEntity {

    private final CustomerEntityId customerEntityId;
    
    private String firstName;
    private String lastName;
    private String status;

    CustomerRootEntity(CustomerEntityId customerEntityId)
    {
        this.customerEntityId = customerEntityId;
    }
    
    public CustomerAggregate getCustomerAggregate(){
        return new CustomerAggregate(this);
    }
    
}
