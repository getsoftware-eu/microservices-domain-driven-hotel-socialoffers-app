package de.hotelico.service;

import de.hotelico.dto.ChatMessageDTO;
import de.hotelico.dto.CustomerDTO;
import de.hotelico.model.ChatMessage;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ChatService
{
    @Transactional
    List<ChatMessage> getChatMessages();
    
    @Transactional ChatMessageDTO convertMessageToDto(ChatMessage nextMessage);

    @Transactional ChatMessageDTO addUpdateChatMessage(ChatMessageDTO chatMessageDto);    
    
    @Transactional ChatMessageDTO addChatMessage(ChatMessageDTO chatMessageDto);
    
    @Transactional
    List<ChatMessageDTO> getMessagesByCustomerId(long customerId, long receiverId);   
    
    @Transactional ChatMessageDTO getChatMessageById(long chatMessageId);    
    
    @Transactional ChatMessageDTO getChatMessageBySender(long senderId);    
    
    @Transactional ChatMessageDTO getChatMessageByReceiver(long receiverId);    
    
    @Transactional ChatMessageDTO getChatMessageByDateRange(Date startDate, Date endDate);

    @Transactional
    void deleteChatMessage(ChatMessageDTO chatMessageDto);
    
    @Transactional
    void markMessageRead(long customerId, String messageIds);
    
    @Transactional
    void markChatRead(long customerId, long senderId, long maxSeenChatMessageId);
    
    @Transactional
    Set<CustomerDTO> getNotHotelChatPartners(long customerId, String city, long hotelId);    
    
    @Transactional
    Set<CustomerDTO> getAllContactChatPartners(long customerId, String city, long hotelId);
    
    @Transactional
    Set<CustomerDTO> getAllNotChatPartners(long customerId, String city, long hotelId, int pageNumber);
}
