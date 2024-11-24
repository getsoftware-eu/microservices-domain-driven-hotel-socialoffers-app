package eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.innerEvents;


import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;

public record InnerDomainEventRequestDTO(

        EntityIdentifier domainEntityId,

        InnerDomainEvent eventType,

        String payload
) {}
