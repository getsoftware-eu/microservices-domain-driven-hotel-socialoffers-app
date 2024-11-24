package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.repository;

import eu.getsoftware.hotelico.clients.common.adapter.out.persistence.GenericRepositoryAdapter;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.mapper.CustomerEntityMapper;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.repository.CustomerRepository;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import org.springframework.stereotype.Component;

@Component
public class CheckinRepositoryAdapter
        extends GenericRepositoryAdapter<CustomerRootDomainEntity, CustomerDBEntity, CustomerDomainEntityId> {

    public CheckinRepositoryAdapter(CustomerRepository repository, CustomerEntityMapper mapper) {
        super(repository, mapper);
    }
}
