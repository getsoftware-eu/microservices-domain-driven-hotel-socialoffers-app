package eu.getsoftware.hotelico.hotelapp.adapter.out.chat;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.hotelapp.adapter.out.viewEntity.model.ChatMessageView;
import eu.getsoftware.hotelico.hotelapp.application.chat.domain.infrastructure.ChatMSComminicationService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ChatMSComminicationServiceImpl implements ChatMSComminicationService {
    @Override
    public List<ChatMsgDTO> getChatMessages() {
        return List.of();
    }

    @Override
    public ChatMsgDTO convertMessageToDto(ChatMessageView nextMessage) {
        return null;
    }

    @Override
    public ChatMsgDTO addUpdateChatMessage(ChatMsgDTO chatMessageDto) {
        return null;
    }

    @Override
    public ChatMsgDTO addChatMessage(ChatMsgDTO chatMessageDto) {
        return null;
    }

    @Override
    public List<ChatMsgDTO> getMessagesByCustomerId(long customerId, long receiverId) {
        return List.of();
    }

    @Override
    public ChatMsgDTO getChatMessageById(long chatMessageId) {
        return null;
    }

    @Override
    public ChatMsgDTO getChatMessageBySender(long senderId) {
        return null;
    }

    @Override
    public ChatMsgDTO getChatMessageByReceiver(long receiverId) {
        return null;
    }

    @Override
    public ChatMsgDTO getChatMessageByDateRange(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public void deleteChatMessage(ChatMsgDTO chatMessageDto) {

    }

    @Override
    public void markMessageRead(long customerId, String messageIds) {

    }

    @Override
    public void markChatRead(long customerId, long senderId, long maxSeenChatMessageId) {

    }

    @Override
    public Set<CustomerDTO> getNotHotelChatPartners(long customerId, String city, long hotelId) {
        return Set.of();
    }

    @Override
    public Set<CustomerDTO> getAllContactChatPartners(long customerId, String city, long hotelId) {
        return Set.of();
    }

    @Override
    public Set<CustomerDTO> getAllNotChatPartners(long customerId, String city, long hotelId, int pageNumber) {
        return Set.of();
    }

    @Override
    public Collection<? extends CustomerDTO> getChatSendersByCustomerId(long receiverId) {
        return List.of();
    }

    @Override
    public Collection<? extends CustomerDTO> getChatReceiversByCustomerId(long receiverId) {
        return List.of();
    }
}
