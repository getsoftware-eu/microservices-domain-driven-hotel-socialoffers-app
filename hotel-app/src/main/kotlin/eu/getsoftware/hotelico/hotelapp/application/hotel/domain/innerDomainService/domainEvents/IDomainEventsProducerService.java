package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.innerDomainService.domainEvents;

import eu.getsoftware.hotelico.hotelapp.application.hotel.common.utils.IHotelEvent;

public interface IDomainEventsProducerService<E extends IHotelEvent> {

    /**
     * for asynchronous messaging events between decoupled DDD domains ??
     * -- after subscribing new Event, underlying (mapped)-ENTITY will be updated and persisted in DB?
     * @param domainEventRequestDTO
     * @param domainEvent
     */
    void sendDomainNotification(DomainEventRequestDTO domainEventRequestDTO, E domainEvent);

}
