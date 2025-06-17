package eu.getsoftware.hotelico.service.booking.application.deal.domain;

import eu.getsoftware.hotelico.clients.common.domain.ids.ActivityDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;

public interface ICustomerDeal {

    CustomerDomainEntityId getCustomerDomainEntityId();

    void setCustomerDomainEntityId(CustomerDomainEntityId customerEntityId);
    
    ActivityDomainEntityId getActivityDomainEntityId();

    void setActivityDomainEntityId(ActivityDomainEntityId activity);
    
    void generateCode();

    String getDealCode();
}
