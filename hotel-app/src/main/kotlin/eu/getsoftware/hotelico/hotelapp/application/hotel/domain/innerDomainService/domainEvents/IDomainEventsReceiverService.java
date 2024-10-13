package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.innerDomainService.domainEvents;

import eu.getsoftware.hotelico.clients.common.domain.IDomainEntity;
import eu.getsoftware.hotelico.clients.common.model.innerModelService.IDomainRegisterDTOGateway;
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.utils.IHotelEvent;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.IHotelEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IDomainEventsReceiverService<T extends IDomainEntity, E extends IHotelEvent> {

    private final IDomainRegisterDTOGateway<T> domainEntityInnerGatewayService;
    
    void onDomainEvent(E domainEvent, String eventBody){
        
        switch (domainEvent){
            case CHECKIN_NEW -> {
                IHotelEntity hotelDomain = domainEntityInnerGatewayService.findEntityById(eventBody.hotelId);
                hotelDomain.newCheckinAction();
                break;
            }
        }
        
    }
    
}
