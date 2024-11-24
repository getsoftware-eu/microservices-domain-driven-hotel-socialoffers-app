package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.gateways;

import eu.getsoftware.hotelico.clients.common.domain.domainGateway.GenericRepositoryService;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerDBEntity;
import org.springframework.stereotype.Service;

@Service
public class UserGatewayService extends GenericRepositoryService<CustomerDBEntity, CustomerDomainEntityId> {
    
    public UserGatewayService(UserRepositoryAdapter repositoryAdapter) {
        super(repositoryAdapter);
    }
}
