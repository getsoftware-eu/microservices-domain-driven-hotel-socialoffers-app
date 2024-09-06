package eu.getsoftware.hotelico.chat.infrastructure.service;

import eu.getsoftware.hotelico.chat.domain.ChatMessageView;
import eu.getsoftware.hotelico.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.infrastructure.chat.dto.ChatMsgDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ChatMSComminicationService
{
    List<ChatMessageView> getChatMessages();
    
    ChatMsgDTO convertMessageToDto(ChatMessageView nextMessage);

    @Transactional 
    ChatMsgDTO addUpdateChatMessage(ChatMsgDTO chatMessageDto);    
    
    @Transactional 
    ChatMsgDTO addChatMessage(ChatMsgDTO chatMessageDto);
    
    @Transactional
    List<ChatMsgDTO> getMessagesByCustomerId(long customerId, long receiverId);   
    
    ChatMsgDTO getChatMessageById(long chatMessageId);    
    
    ChatMsgDTO getChatMessageBySender(long senderId);    
    
    ChatMsgDTO getChatMessageByReceiver(long receiverId);    
    
    ChatMsgDTO getChatMessageByDateRange(Date startDate, Date endDate);

    @Transactional
    void deleteChatMessage(ChatMsgDTO chatMessageDto);
    
    @Transactional
    void markMessageRead(long customerId, String messageIds);
    
    @Transactional
    void markChatRead(long customerId, long senderId, long maxSeenChatMessageId);
    
    Set<CustomerDTO> getNotHotelChatPartners(long customerId, String city, long hotelId);    
    
    Set<CustomerDTO> getAllContactChatPartners(long customerId, String city, long hotelId);
    
    Set<CustomerDTO> getAllNotChatPartners(long customerId, String city, long hotelId, int pageNumber);
}
