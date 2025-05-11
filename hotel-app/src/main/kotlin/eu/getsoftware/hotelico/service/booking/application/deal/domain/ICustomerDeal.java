package eu.getsoftware.hotelico.service.booking.application.deal.domain;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDbActivity;

public interface ICustomerDeal {

    CustomerDomainEntityId getCustomerId();

    void setCustomerId(CustomerDomainEntityId customerEntityId);
    
    HotelDbActivity getActivity();

    void setActivity(HotelDbActivity activity);
    
    void generateCode();

    String getDealCode();
}
