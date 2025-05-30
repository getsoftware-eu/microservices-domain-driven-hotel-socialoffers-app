package hotelico.service.booking.application.checkin.domain;

import eu.getsoftware.hotelico.clients.common.domain.IRootDomainEntity;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CheckinDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckinRootDomainEntity implements IRootDomainEntity<CheckinDomainEntityId> {

    @Setter
    protected HotelDomainEntityId hotelDomainEntityId;
    
    @Setter
    protected CustomerDomainEntityId customerDomainEntityId;
    
    @Getter
    protected CheckinDomainEntityId domainEntityId;

    @Setter
    private LocalDate validFrom;
    
    @Setter
    private LocalDate validTo;
    
    @Setter
    boolean staffCheckin;      
    
    @Setter
    boolean fullCheckin;    
    
    @Setter
    boolean active;

    @Override
    public void setInitValues(Map fieldToValues) {
        
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
