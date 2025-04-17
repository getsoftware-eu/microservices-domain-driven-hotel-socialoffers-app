package eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.innerEvents;


public interface InnerDomainEventsProducerService<E extends InnerDomainEvent> {

    /**
     * for asynchronous messaging events between decoupled DDD domains ??
     * -- after subscribing new Event, underlying (mapped)-ENTITY will be updated and persisted in DB?
     * @param domainEventRequestDTO
     */
    void publishInnerEvent(InnerDomainEventRequestDTO domainEventRequestDTO);

}
