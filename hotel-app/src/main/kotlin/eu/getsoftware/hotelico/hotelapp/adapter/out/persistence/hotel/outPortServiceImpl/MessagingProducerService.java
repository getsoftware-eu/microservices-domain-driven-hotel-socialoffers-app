package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.ChatMessageCommand;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.SocketNotificationCommand;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.HotelEvent;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IMessagingProducerService;
import org.springframework.stereotype.Service;

@Service
public class MessagingProducerService implements IMessagingProducerService<HotelEvent> {
    
    @Override
    public void sendChatMessageCommand(ChatMessageCommand chatMessageRequest) {
        
    }

    @Override
    public void sendSocketNotificationCommand(SocketNotificationCommand requestDTO, HotelEvent hotelEvent) {
        
    }

}
