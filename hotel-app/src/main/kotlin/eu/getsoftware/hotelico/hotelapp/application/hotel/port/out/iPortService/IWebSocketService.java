package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;

import java.util.List;

public interface IWebSocketService {

    String produceSimpWebsocketMessage(String destination, CustomerNotificationDTO dto);

    String produceSimpWebsocketMessage(String destination, List<? extends Object> list);

    String produceSimpWebsocketMessage(String destination, ChatMsgDTO dto);
}
