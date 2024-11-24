package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.gateways;

import eu.getsoftware.hotelico.clients.common.domain.domainGateway.GenericRepositoryService;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.repository.UserRepositoryAdapter;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerGatewayService extends GenericRepositoryService<CustomerRootDomainEntity, CustomerDomainEntityId> {
    
    public CustomerGatewayService(UserRepositoryAdapter repositoryAdapter) {
        super(repositoryAdapter);
    }
}
