package eu.getsoftware.hotelico.hotelapp.application.checkin.domain;

import eu.getsoftware.hotelico.clients.common.domain.IRootDomainEntity;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CheckinDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import lombok.*;

import java.util.Date;
import java.util.Map;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckinRootDomainEntity implements IRootDomainEntity {

    @Setter
    protected HotelDomainEntityId hotelDomainEntityId;
    
    @Setter
    protected CustomerDomainEntityId customerDomainEntityId;
    
    @Getter
    protected CheckinDomainEntityId domainEntityId;

    @Setter
    private Date validFrom;
    
    @Setter
    private Date validTo;
    
    @Setter
    boolean staffCheckin;      
    
    @Setter
    boolean fullCheckin;    
    
    @Setter
    boolean active;

    @Override
    public void setInitValues(Map<String, String> fieldToValues) {
        
    }

//    //TODO ???? between domain objects??
//    @Override
//    public HotelRootEntity getHotel() {
//        return null;
//    }
//
//    //TODO ???? between domain objects??
//    @Override
//    public CustomerEntityId getCustomer() {
//        return null;
//    }
}
