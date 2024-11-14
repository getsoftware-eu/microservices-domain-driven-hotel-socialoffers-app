package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.innerDomainService.domainEvents;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessage;
import eu.getsoftware.hotelico.clients.common.domain.IRootDomainEntity;
import eu.getsoftware.hotelico.clients.common.domain.domainGateway.DomainEntityGatewayServiceAbstr;
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.utils.IHotelEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IDomainEventsReceiverService<T extends IRootDomainEntity, E extends IHotelEvent> {

    private final DomainEntityGatewayServiceAbstr<T> domainEntityInnerGatewayService;
    
    void onDomainEvent(E domainEvent, DomainMessage<?> eventBody){
        
        switch (domainEvent.getValue()){
            case "CHECKIN_NEW" -> {
                T hotelDomain = domainEntityInnerGatewayService.findEntityById(eventBody.hashCode());
                hotelDomain.getDomainEntityId();
                break;
            }
        }
        
    }
    
}
