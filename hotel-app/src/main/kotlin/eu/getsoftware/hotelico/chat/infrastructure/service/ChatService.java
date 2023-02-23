package eu.getsoftware.hotelico.chat.infrastructure.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import eu.getsoftware.hotelico.chat.domain.ChatMessage;
import eu.getsoftware.hotelico.chat.infrastructure.dto.ChatMessageDTO;
import eu.getsoftware.hotelico.customer.infrastructure.dto.CustomerDTO;

public interface ChatService
{
    List<ChatMessage> getChatMessages();
    
    ChatMessageDTO convertMessageToDto(ChatMessage nextMessage);

    @Transactional 
    ChatMessageDTO addUpdateChatMessage(ChatMessageDTO chatMessageDto);    
    
    @Transactional 
    ChatMessageDTO addChatMessage(ChatMessageDTO chatMessageDto);
    
    @Transactional
    List<ChatMessageDTO> getMessagesByCustomerId(long customerId, long receiverId);   
    
    ChatMessageDTO getChatMessageById(long chatMessageId);    
    
    ChatMessageDTO getChatMessageBySender(long senderId);    
    
    ChatMessageDTO getChatMessageByReceiver(long receiverId);    
    
    ChatMessageDTO getChatMessageByDateRange(Date startDate, Date endDate);

    @Transactional
    void deleteChatMessage(ChatMessageDTO chatMessageDto);
    
    @Transactional
    void markMessageRead(long customerId, String messageIds);
    
    @Transactional
    void markChatRead(long customerId, long senderId, long maxSeenChatMessageId);
    
    Set<CustomerDTO> getNotHotelChatPartners(long customerId, String city, long hotelId);    
    
    Set<CustomerDTO> getAllContactChatPartners(long customerId, String city, long hotelId);
    
    Set<CustomerDTO> getAllNotChatPartners(long customerId, String city, long hotelId, int pageNumber);
}
