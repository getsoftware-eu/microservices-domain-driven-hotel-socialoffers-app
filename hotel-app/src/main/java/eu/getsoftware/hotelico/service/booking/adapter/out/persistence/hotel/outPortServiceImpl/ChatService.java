package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.api.application.domain.chat.IChatMessageView;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    
    public List<ChatMsgDTO> getUnreadChatMessagesForCustomer(CustomerDomainEntityId receiverId) {
        return new ArrayList<>();
    }
    
    public IChatMessageView getLastMessageByCustomerAndReceiverIds(Long id, long l){
        return null;
    }
}
