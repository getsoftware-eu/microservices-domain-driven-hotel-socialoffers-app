package eu.getsoftware.hotelico.hotelapp.application.deal.domain;

import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.model.HotelDbActivity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model.CustomerDBEntity;

public interface ICustomerDeal {
    
    CustomerDBEntity getCustomer();

    void setCustomer(CustomerDBEntity customerEntity);
    
    HotelDbActivity getActivity();

    void setActivity(HotelDbActivity activity);
    
    void generateCode();

    String getDealCode();
}
