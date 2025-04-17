package eu.getsoftware.hotelico.clients.api.application.infrastructure.amqpConsumeNotification;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;

public record ChatMessageCommand(
        CustomerDomainEntityId fromCustomerId,
        CustomerDomainEntityId toCustomerId,
        boolean lastMessage,
        String customMsg
) {
}
