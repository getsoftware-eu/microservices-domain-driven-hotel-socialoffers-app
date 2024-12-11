//package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.domainServiceImpl;
//
//import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerDBEntity;
//import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.repository.CustomerRepository;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomerPersistGatewayServiceImpl extends DomainEntityGatewayServiceAbstr<CustomerDBEntity> {
//
//    private final CustomerRepository customerRepository;
//
//    public CustomerPersistGatewayServiceImpl(CustomerRepository customerRepository, CustomerRepository customerRepository1) {
//        super(customerRepository);
//        this.customerRepository = customerRepository1;
//    }
//
//    @NotNull
//    @Override
//    public Class<CustomerDBEntity> getAssetClass() {
//        return CustomerDBEntity.class;
//    }
//    
//    @Override
//    public boolean existsByName(@NotNull String name) {
//        return customerRepository.findByName(name).isPresent();
//    }
//
//    
//}
