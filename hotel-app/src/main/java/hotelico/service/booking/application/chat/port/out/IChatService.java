package hotelico.service.booking.application.chat.port.out;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;

/**
 * only DTOs betwenn different domains!!!
 */
public interface IChatService {

     void sendFirstChatMessageOnDemand(CustomerDTO customerEntity, CustomerDTO staffSender, boolean isFullCheckin); 
        
}
