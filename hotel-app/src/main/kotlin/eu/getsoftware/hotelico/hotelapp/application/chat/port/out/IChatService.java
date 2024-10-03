package eu.getsoftware.hotelico.hotelapp.application.chat.port.out;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;

public interface IChatService {

     void sendFirstChatMessageOnDemand(CustomerDTO customerEntity, CustomerDTO staffSender, boolean isFullCheckin); 
        
}
