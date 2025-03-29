package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.chat.messaging;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.innerEvents.InnerDomainEventRequestDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.innerEvents.InnerDomainEventsProducerService;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.InnerHotelEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
/*public*/ class HotelInnerEventPublisher implements InnerDomainEventsProducerService<InnerHotelEvent> {

    private final ApplicationEventPublisher eventPublisher;

    public void publishInnerEvent(InnerDomainEventRequestDTO innerEvent) {
        eventPublisher.publishEvent(innerEvent);
        System.out.println("Event published: " + innerEvent);
    }
}
