package eu.getsoftware.hotelico.hotelapp.application.checkin.domain;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CheckinEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelEntityId;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.model.ICustomerHotelCheckinEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class CheckinRootEntity implements ICustomerHotelCheckinEntity {

    @Setter
    private HotelEntityId hotelEntityId;
    
    @Setter
    private CustomerEntityId customerEntityId;
    
    @Getter
    private final CheckinEntityId entityId;

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
