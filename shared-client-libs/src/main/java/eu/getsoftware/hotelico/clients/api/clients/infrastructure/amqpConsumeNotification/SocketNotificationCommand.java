package eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;

public record SocketNotificationCommand(
        CustomerDomainEntityId customerId,
        HotelDomainEntityId hotelId,
        String customerName,
        String message
) {
}
