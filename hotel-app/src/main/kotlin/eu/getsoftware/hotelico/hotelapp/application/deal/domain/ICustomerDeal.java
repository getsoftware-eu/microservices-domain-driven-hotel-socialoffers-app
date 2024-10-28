package eu.getsoftware.hotelico.hotelapp.application.deal.domain;

import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.model.HotelActivity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model.CustomerRootEntity;

public interface ICustomerDeal {
    
    CustomerRootEntity getCustomer();

    void setCustomer(CustomerRootEntity customerEntity);
    
    HotelActivity getActivity();

    void setActivity(HotelActivity activity);
    
    void generateCode();

    String getDealCode();
}
