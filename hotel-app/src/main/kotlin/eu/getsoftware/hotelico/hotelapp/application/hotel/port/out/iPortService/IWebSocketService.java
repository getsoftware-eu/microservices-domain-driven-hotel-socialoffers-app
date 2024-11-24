package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.innerEvents.InnerDomainEvent;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;

import java.util.List;

public interface IWebSocketService {

    String produceSimpWebsocketMessage(String destination, HotelActivityDTO dto);
    String produceSimpWebsocketMessage(String destination, CustomerNotificationDTO dto);
    String produceSimpWebsocketMessage(String destination, WallPostDTO dto);

    String produceSimpWebsocketMessage(String destination, List<? extends Object> list);

    String produceSimpWebsocketMessage(String destination, ChatMsgDTO dto);

    void notificateAboutEntityEvent(CustomerDTO dto, CustomerNotificationDTO receiverNotification, InnerDomainEvent event);
}
