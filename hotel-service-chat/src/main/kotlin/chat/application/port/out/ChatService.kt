package chat.application.port.out;

import chat.adapter.out.persistence.model.ChatMessageMappedEntity;
import chat.adapter.out.persistence.model.ChatUserMappedEntity;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;

import java.util.List;
import java.util.Set;

public interface ChatService {
    
    ChatMessageMappedEntity convertFromDTO(ChatMsgDTO msgDTO);
    
    ChatUserMappedEntity convertFromDTO(CustomerDTO msgDTO);

    ChatMsgDTO convertToDTO(ChatMessageMappedEntity entity);

    CustomerDTO convertToDTO(ChatUserMappedEntity entity);

    ChatMessageMappedEntity createChatMessageFromDTO(ChatMsgDTO msgDTO);
    
    ChatUserMappedEntity updateCustomerFromDTO(CustomerDTO customerDTO);

    ChatMsgDTO addChatMessage(ChatMsgDTO chatMessageDto);

    List<ChatMsgDTO> getMessagesByCustomerId(long customerId, int receiverId);

    Set<CustomerDTO> getNotHotelChatPartners(long customerId, String city, long hotelId);

    Set<CustomerDTO> getAllContactChatPartners(long customerId, String city, long hotelId);

    void markMessageRead(long customerId, String messageIds);

    Set<CustomerDTO> getAllNotChatPartners(long customerId, String city, long hotelId, int pageNumber);

    void markChatRead(long customerId, int senderId, long maxSeenChatMessageId);

    ChatMsgDTO addUpdateChatMessage(ChatMsgDTO dto);
}
