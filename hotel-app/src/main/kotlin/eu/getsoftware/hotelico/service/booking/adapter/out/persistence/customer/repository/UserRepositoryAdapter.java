package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.repository;

import eu.getsoftware.hotelico.clients.common.adapter.out.persistence.GenericRepositoryAdapter;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.mapper.CustomerEntityMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAdapter
        extends GenericRepositoryAdapter<CustomerRootDomainEntity, CustomerDBEntity, CustomerDomainEntityId> {

    public UserRepositoryAdapter(CustomerRepository repository, CustomerEntityMapper mapper) {
        super(repository, mapper);
    }
}
