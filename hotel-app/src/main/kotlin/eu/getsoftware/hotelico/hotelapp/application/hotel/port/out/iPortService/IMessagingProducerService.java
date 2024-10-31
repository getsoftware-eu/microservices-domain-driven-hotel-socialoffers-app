package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.ChatMessageCommand;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.CustomerUpdateCommand;
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.utils.IHotelEvent;

public interface IMessagingProducerService<E extends IHotelEvent> {

    void sendChatMessageCommand(ChatMessageCommand chatMessageRequest);

    void sendCustomerNotificationCommand(CustomerUpdateCommand requestDTO, E hotelEvent);

    /**
     * for events in domain layer??
     */
//    void sendDomainNotification(DomainUpdateRequest requestDTO, E hotelEvent);

}
