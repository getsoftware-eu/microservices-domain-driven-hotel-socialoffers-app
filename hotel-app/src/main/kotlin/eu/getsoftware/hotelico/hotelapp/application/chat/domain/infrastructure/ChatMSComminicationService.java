package eu.getsoftware.hotelico.hotelapp.application.chat.domain.infrastructure;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.domain.customer.ICustomerRootEntity;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ChatMSComminicationService
{
    List<IChatMsgDomain> getChatMessages();
    
    ChatMsgDTO convertMessageToDto(IChatMessageDomain nextMessage);

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

    Collection<? extends ICustomerRootEntity> getChatSendersByCustomerId(long receiverId);

    Collection<? extends ICustomerRootEntity> getChatReceiversByCustomerId(long receiverId);
}
