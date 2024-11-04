package eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.customDomainModelImpl;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerEntityId;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class CustomerRootEntity implements ICustomerRootEntity {

    private final CustomerEntityId customerEntityId;
    
    private String firstName;
    private String lastName;
    private String status;

//    CustomerRootEntity(CustomerEntityId customerEntityId)
//    {
//        this.customerEntityId = customerEntityId;
//    }
    
    public CustomerAggregate getCustomerAggregate(){
        return new CustomerAggregate(this);
    }
    
}
