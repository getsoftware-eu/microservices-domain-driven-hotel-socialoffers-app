package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.notification.ChatMessageRequest;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.notification.CustomerUpdateRequest;

public interface IMessagingProducerService {

    void sendChatMessageTopicRequest(ChatMessageRequest chatMessageRequest);
    
    void sendTopicViaPreconfiguredRabbitmqProducer(CustomerUpdateRequest customerUpdateRequest);
    
    void notificateAllAboutCustomer(CustomerUpdateRequest requestDTO);
 

}
