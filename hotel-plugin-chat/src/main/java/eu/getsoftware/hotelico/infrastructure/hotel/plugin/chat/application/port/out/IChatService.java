package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.application.port.out;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatMessageEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatUserEntity;

import java.util.List;
import java.util.Set;

public interface IChatService {
    
    ChatMessageEntity convertFromDTO(ChatMsgDTO msgDTO);
    
    ChatUserEntity convertFromDTO(CustomerDTO msgDTO);

    ChatMsgDTO convertToDTO(ChatMessageEntity entity);

    CustomerDTO convertToDTO(ChatUserEntity entity);

    ChatMessageEntity createChatMessageFromDTO(ChatMsgDTO msgDTO);
    
    ChatUserEntity updateCustomerFromDTO(CustomerDTO customerDTO);

    ChatMsgDTO addChatMessage(ChatMsgDTO chatMessageDto);

    List<ChatMsgDTO> getMessagesByCustomerId(long customerId, int receiverId);

    Set<CustomerDTO> getNotHotelChatPartners(long customerId, String city, long hotelId);

    Set<CustomerDTO> getAllContactChatPartners(long customerId, String city, long hotelId);

    void markMessageRead(long customerId, String messageIds);

    Set<CustomerDTO> getAllNotChatPartners(long customerId, String city, long hotelId, int pageNumber);

    void markChatRead(long customerId, int senderId, long maxSeenChatMessageId);

    ChatMsgDTO addUpdateChatMessage(ChatMsgDTO dto);
}
