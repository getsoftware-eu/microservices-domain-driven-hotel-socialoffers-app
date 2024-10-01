package eu.getsoftware.hotelico.hotelapp.application.chat.port.out;

import eu.getsoftware.hotelico.clients.api.clients.domain.customer.ICustomerRootEntity;

public interface IChatService {

     void sendFirstChatMessageOnDemand(ICustomerRootEntity customerEntity, ICustomerRootEntity staffSender, boolean isFullCheckin); 
        
}
