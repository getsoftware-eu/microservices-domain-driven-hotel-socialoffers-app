package eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.innerevents;

public interface InnerDomainEventsListenerService<E extends InnerDomainEvent> {

    /**
     * for asynchronous messaging events between decoupled DDD domains ??
     * -- after subscribing new Event, underlying (mapped)-ENTITY will be updated and persisted in DB?
     * @param domainEventRequestDTO
     */
    void handleInnerEvent(InnerDomainEventRequestDTO domainEventRequestDTO);

}
