package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.innerDomainService.domainEvents;

import eu.getsoftware.hotelico.clients.common.model.innerModelService.IDomainRegisterDTOGateway;
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.utils.IHotelEvent;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.IHotelEntity;

public class IDomainEventsReceiverService<E extends IHotelEvent> {

    IDomainRegisterDTOGateway domainEntityInnerGatewayService = IDomainRegisterDTOGateway<IHotelEntity, InRequestDTO, OutResponseDTO>();
    
    void onDomainEvent(E event, String eventBody){
        
        switch (event){
            case TopicEvent.CHECKIN_NEW -> {
                IHotelEntity hotelDomain = domainEntityInnerGatewayService.findDomainEntityById(eventBody.hotelInitId);
                hotelDomain.newCheckinAction();
                break;
            }
        }
        
    }
    
}
