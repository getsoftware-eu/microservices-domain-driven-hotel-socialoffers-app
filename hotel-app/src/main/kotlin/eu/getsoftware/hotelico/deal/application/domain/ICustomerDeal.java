package eu.getsoftware.hotelico.deal.application.domain;

import eu.getsoftware.hotelico.customer.adapter.out.persistence.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotelCustomer.adapter.out.persistence.HotelActivity;

public interface ICustomerDeal {
    
    CustomerRootEntity getCustomer();

    void setCustomer(CustomerRootEntity customerEntity);
    
    HotelActivity getActivity();

    void setActivity(HotelActivity activity);
    
    void generateCode();

    String getDealCode();
}
