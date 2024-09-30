package eu.getsoftware.hotelico.hotelapp.application.deal.domain;

import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotelCustomer.model.HotelActivity;

public interface ICustomerDeal {
    
    CustomerRootEntity getCustomer();

    void setCustomer(CustomerRootEntity customerEntity);
    
    HotelActivity getActivity();

    void setActivity(HotelActivity activity);
    
    void generateCode();

    String getDealCode();
}
