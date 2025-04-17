package eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.innerEvents;


import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;

public record InnerDomainEventRequestDTO(

        EntityIdentifier domainEntityId,

        InnerDomainEvent eventType,

        String payload
) {}
