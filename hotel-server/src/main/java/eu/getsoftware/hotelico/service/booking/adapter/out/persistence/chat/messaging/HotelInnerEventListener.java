package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.chat.messaging;

import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.innerevents.InnerDomainEventRequestDTO;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.innerevents.InnerDomainEventsListenerService;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.gateways.HotelGatewayService;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model.InnerHotelEvent;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.AddressValueObject;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.customDomainModelImpl.HotelRootDomainEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model.InnerHotelEvent.EVENT_ADDRESS_UPDATED;

@Component
@RequiredArgsConstructor
/*public*/ class HotelInnerEventListener implements InnerDomainEventsListenerService<InnerHotelEvent> {

    private HotelGatewayService hotelGatewayService;
    
    @EventListener
    public void handleInnerEvent(InnerDomainEventRequestDTO innerEvent) {

        if (innerEvent.eventType() == EVENT_ADDRESS_UPDATED )
        {
            HotelRootDomainEntity entity = hotelGatewayService.findOrThrow((HotelDomainEntityId) innerEvent.domainEntityId());
            AddressValueObject address = AddressValueObject.from(innerEvent.payload());
            entity.setAddress(address);
            hotelGatewayService.saveToDb(entity);
        }
      
        System.out.println("Received event: " + innerEvent.payload());
    }
}
