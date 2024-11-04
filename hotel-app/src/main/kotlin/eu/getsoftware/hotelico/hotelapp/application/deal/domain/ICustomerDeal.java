package eu.getsoftware.hotelico.hotelapp.application.deal.domain;

import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.model.HotelActivity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model.CustomerDBEntity;

public interface ICustomerDeal {
    
    CustomerDBEntity getCustomer();

    void setCustomer(CustomerDBEntity customerEntity);
    
    HotelActivity getActivity();

    void setActivity(HotelActivity activity);
    
    void generateCode();

    String getDealCode();
}
