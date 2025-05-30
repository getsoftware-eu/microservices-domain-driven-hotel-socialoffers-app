package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.outPortServiceImpl.microservice;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.innerevents.InnerDomainEvent;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService.IWebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebSocketNotificationService implements IWebSocketService {

    /**
     * expected the user is the one authenticated with the WebSocket session
     */
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public String produceSimpWebsocketMessage(String destination, HotelActivityDTO dto) {
        return "";
    }

    public String produceSimpWebsocketMessage(String destination, CustomerNotificationDTO dto) {

        simpMessagingTemplate.convertAndSend(destination, dto);
        return "WebSocketMessage(" + dto + ")" + " has been produced.";
    }

    @Override
    public String produceSimpWebsocketMessage(String destination, WallPostDTO dto) {
        return "";
    }

    public String produceSimpWebsocketMessage(String destination, List<? extends Object> list) {

        simpMessagingTemplate.convertAndSend(destination, list);
        return "WebSocketMessage(" + list + ")" + " has been produced.";
    }

    public String produceSimpWebsocketMessage(String destination, ChatMsgDTO dto) {

        simpMessagingTemplate.convertAndSend(destination, dto);
        return "WebSocketMessage(" + dto + ")" + " has been produced.";
    }

    @Override
    public void notificateAboutEntityEvent(CustomerDTO dto, CustomerNotificationDTO receiverNotification, InnerDomainEvent event) {

    }
}