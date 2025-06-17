package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.gateways;

import eu.getsoftware.hotelico.clients.common.domain.gateways.GenericRepositoryService;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.repository.UserRepositoryAdapter;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerGatewayService extends GenericRepositoryService<CustomerRootDomainEntity, CustomerDomainEntityId> {
    
    public CustomerGatewayService(UserRepositoryAdapter repositoryAdapter) {
        super(repositoryAdapter);
    }
}
