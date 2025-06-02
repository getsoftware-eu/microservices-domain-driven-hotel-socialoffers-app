package eu.getsoftware.hotelico.service.booking.application.deal.domain;

import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDBActivity;

public interface ICustomerDeal {

    CustomerDomainEntityId getCustomerId();

    void setCustomerId(CustomerDomainEntityId customerEntityId);
    
    HotelDBActivity getActivity();

    void setActivity(HotelDBActivity activity);
    
    void generateCode();

    String getDealCode();
}
