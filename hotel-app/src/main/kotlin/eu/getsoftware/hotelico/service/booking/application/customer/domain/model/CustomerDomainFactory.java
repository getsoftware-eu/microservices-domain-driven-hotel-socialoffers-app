package eu.getsoftware.hotelico.service.booking.application.customer.domain.model;


import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.AddressValueObject;

public class CustomerDomainFactory /*implements ICustomerDomainFactory<CustomerRootDomainEntity>*/ {

    public static CustomerRootDomainEntity create(String name, String email, String password) {
        
        return create(name, email, password, null);
    }
    
    public static CustomerRootDomainEntity create(String name, String email, String password, AddressValueObject address) {
        
        CustomerRootDomainEntity customer = CustomerRootDomainEntity.builder()
                .firstName(name)
                .email(email)
                .password(password)
                .build();

        if(email!=null) customer.setEmail(email); 
        
        if(address!=null) customer.setAddress(address);
        
        //Aggreate has no setters for these fields!
//        customer.setInitValues(Map.ofEntries(Map.entry("name", name), Map.entry("password", password)));
        
        return customer;
    }
}
