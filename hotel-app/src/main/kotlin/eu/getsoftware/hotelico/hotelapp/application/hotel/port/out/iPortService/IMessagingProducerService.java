package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.innerEvents.InnerDomainEvent;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.eventConsumeNotification.ChatMessageCommand;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.eventConsumeNotification.SocketNotificationCommand;

public interface IMessagingProducerService<E extends InnerDomainEvent> {

    void sendChatMessageCommand(ChatMessageCommand chatMessageRequest);

    void sendSocketNotificationCommand(SocketNotificationCommand requestDTO, E hotelEvent);

    /**
     * for events in domain layer??
     */
//    void sendDomainNotification(DomainUpdateRequest requestDTO, E hotelEvent);

}
