package eu.getsoftware.hotelico.hotelapp.application.deal.domain;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.model.HotelDbActivity;

public interface ICustomerDeal {

    CustomerDomainEntityId getCustomerId();

    void setCustomerId(CustomerDomainEntityId customerEntityId);
    
    HotelDbActivity getActivity();

    void setActivity(HotelDbActivity activity);
    
    void generateCode();

    String getDealCode();
}
