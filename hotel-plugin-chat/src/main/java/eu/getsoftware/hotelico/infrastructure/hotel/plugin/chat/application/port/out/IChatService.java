package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.application.port.out;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatMessageEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatUserEntity;

public interface IChatService {
    
    ChatMessageEntity convertFromDTO(ChatMsgDTO msgDTO);
    
    ChatUserEntity convertFromDTO(CustomerDTO msgDTO);

    ChatMsgDTO convertToDTO(ChatMessageEntity entity);

    CustomerDTO convertToDTO(ChatUserEntity entity);

    ChatMessageEntity createChatMessageFromDTO(ChatMsgDTO msgDTO);
    
    ChatUserEntity updateCustomerFromDTO(CustomerDTO customerDTO);
}
