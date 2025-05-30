package eu.getsoftware.hotelico.clients.api.application.infrastructure.amqpConsumeNotification;

import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;

public record ChatMessageCommand(
        CustomerDomainEntityId fromCustomerId,
        CustomerDomainEntityId toCustomerId,
        boolean lastMessage,
        String customMsg
) {
}
