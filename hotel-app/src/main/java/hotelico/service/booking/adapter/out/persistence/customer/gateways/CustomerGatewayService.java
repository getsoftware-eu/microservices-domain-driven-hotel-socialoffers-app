package hotelico.service.booking.adapter.out.persistence.customer.gateways;

import eu.getsoftware.hotelico.clients.common.domain.domainGateway.GenericRepositoryService;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import hotelico.service.booking.adapter.out.persistence.customer.repository.UserRepositoryAdapter;
import hotelico.service.booking.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerGatewayService extends GenericRepositoryService<CustomerRootDomainEntity, CustomerDomainEntityId> {
    
    public CustomerGatewayService(UserRepositoryAdapter repositoryAdapter) {
        super(repositoryAdapter);
    }
}
