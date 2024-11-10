package eu.getsoftware.hotelico.hotelapp.application.checkin.domain;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CheckinEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.model.ICustomerHotelCheckinEntity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.IHotelRootEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class CheckinRootEntity implements ICustomerHotelCheckinEntity {

    @Setter
    private HotelDomainEntityId hotelEntityId;
    
    @Setter
    private CustomerDomainEntityId customerEntityId;
    
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

    @Override
    public IHotelRootEntity getHotelId() {
        return null;
    }

    @Override
    public Long getCustomerId() {
        return 0L;
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
