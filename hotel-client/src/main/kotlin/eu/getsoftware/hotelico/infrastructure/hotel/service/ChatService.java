package eu.getsoftware.hotelico.infrastructure.hotel.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import eu.getsoftware.hotelico.domain.chat.ChatMessage;
import eu.getsoftware.hotelico.infrastructure.hotel.dto.ChatMessageDTO;
import eu.getsoftware.hotelico.infrastructure.hotel.dto.CustomerDTO;

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
