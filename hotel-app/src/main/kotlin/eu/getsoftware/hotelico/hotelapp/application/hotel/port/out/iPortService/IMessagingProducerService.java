package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.notification.ChatMessageRequest;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.notification.CustomerUpdateRequest;
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.utils.IHotelEvent;

public interface IMessagingProducerService<E extends IHotelEvent> {

    void sendChatMessageTopicRequest(ChatMessageRequest chatMessageRequest);

    void sendCustomerNotification(CustomerUpdateRequest requestDTO, E hotelEvent);

}
