package eu.getsoftware.hotelico.service.booking.application.deal.domain;

import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelActivityDBEntity;

public interface ICustomerDeal {

    CustomerDomainEntityId getCustomerId();

    void setCustomerId(CustomerDomainEntityId customerEntityId);
    
    HotelActivityDBEntity getActivity();

    void setActivity(HotelActivityDBEntity activity);
    
    void generateCode();

    String getDealCode();
}
