package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.hotel.outPortServiceImpl.microservice

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IWebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class WebSocketNotificationService implements IWebSocketService {

    /**
     * expected the user is the one authenticated with the WebSocket session
     */
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public String produceSimpWebsocketMessage(String destination, CustomerNotificationDTO dto) {

        simpMessagingTemplate.convertAndSend(destination, dto);
        return "WebSocketMessage(" + dto + ")" + " has been produced.";
    }

    public String produceSimpWebsocketMessage(String destination, List<? extends Object> list) {

        simpMessagingTemplate.convertAndSend(destination, list);
        return "WebSocketMessage(" + list + ")" + " has been produced.";
    }

    public String produceSimpWebsocketMessage(String destination, ChatMsgDTO dto) {

        simpMessagingTemplate.convertAndSend(destination, dto);
        return "WebSocketMessage(" + dto + ")" + " has been produced.";
    }
}