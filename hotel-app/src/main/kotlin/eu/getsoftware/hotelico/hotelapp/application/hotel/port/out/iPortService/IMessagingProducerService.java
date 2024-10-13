package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.ChatMessageConsumeRequest;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.CustomerUpdateConsumeRequest;
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.utils.IHotelEvent;

public interface IMessagingProducerService<E extends IHotelEvent> {

    void sendChatMessageTopicRequest(ChatMessageConsumeRequest chatMessageRequest);

    void sendCustomerNotification(CustomerUpdateConsumeRequest requestDTO, E hotelEvent);

    /**
     * for events in domain layer??
     */
//    void sendDomainNotification(DomainUpdateRequest requestDTO, E hotelEvent);

}
