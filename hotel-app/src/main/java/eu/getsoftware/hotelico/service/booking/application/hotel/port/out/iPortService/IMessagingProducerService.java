package eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.application.infrastructure.amqpConsumeNotification.ChatMessageCommand;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.amqpConsumeNotification.SocketNotificationCommand;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.innerevents.InnerDomainEvent;

public interface IMessagingProducerService<E extends InnerDomainEvent> {

    void sendChatMessageCommand(ChatMessageCommand chatMessageRequest);

    void sendSocketNotificationCommand(SocketNotificationCommand requestDTO, E hotelEvent);

    /**
     * for events in domain layer??
     */
//    void sendDomainNotification(DomainUpdateRequest requestDTO, E hotelEvent);

}
