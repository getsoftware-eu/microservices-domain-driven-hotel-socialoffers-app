package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.customer.model.domainServiceImpl;

import eu.getsoftware.hotelico.clients.common.domain.domainGateway.DomainEntityGatewayServiceAbstr;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.customer.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.customer.repository.CustomerRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class CustomerPersistGatewayServiceImpl extends DomainEntityGatewayServiceAbstr<CustomerRootEntity> {

    private final CustomerRepository customerRepository;

    public CustomerPersistGatewayServiceImpl(CustomerRepository customerRepository, CustomerRepository customerRepository1) {
        super(customerRepository);
        this.customerRepository = customerRepository1;
    }

    @NotNull
    @Override
    public Class<CustomerRootEntity> getAssetClass() {
        return CustomerRootEntity.class;
    }
    
    @Override
    public boolean existsByName(@NotNull String name) {
        return customerRepository.findByName(name).isPresent();
    }

    
}
