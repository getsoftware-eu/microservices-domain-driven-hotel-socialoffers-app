package eu.getsoftware.hotelico.hotel.chat.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.getsoftware.hotelico.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.hotel.chat.dto.ChatCustomerDTO;
import eu.getsoftware.hotelico.hotel.chat.model.ChatMsgEntity;

@Service
public interface ChatService
{
    @Transactional
    List<ChatMsgEntity> getChatMessages();
    
    @Transactional ChatMsgDTO convertMessageToDto(ChatMsgEntity nextMessage);

    @Transactional ChatMsgDTO addUpdateChatMessage(ChatMsgDTO chatMessageDto);    
    
    @Transactional ChatMsgDTO addChatMessage(ChatMsgDTO chatMessageDto);
    
    @Transactional
    List<ChatMsgDTO> getMessagesByCustomerId(long customerId, long receiverId);   
    
    @Transactional ChatMsgDTO getChatMessageById(long chatMessageId);    
    
    @Transactional ChatMsgDTO getChatMessageBySender(long senderId);    
    
    @Transactional ChatMsgDTO getChatMessageByReceiver(long receiverId);    
    
    @Transactional ChatMsgDTO getChatMessageByDateRange(Date startDate, Date endDate);

    @Transactional
    void deleteChatMessage(ChatMsgDTO chatMessageDto);
    
    @Transactional
    void markMessageRead(long customerId, String messageIds);
    
    @Transactional
    void markChatRead(long customerId, long senderId, long maxSeenChatMessageId);
    
    @Transactional
    Set<ChatCustomerDTO> getNotHotelChatPartners(long customerId, String city, long hotelId);    
    
    @Transactional
    Set<ChatCustomerDTO> getAllContactChatPartners(long customerId, String city, long hotelId);
    
    @Transactional
    Set<ChatCustomerDTO> getAllNotChatPartners(long customerId, String city, long hotelId, int pageNumber);
}
