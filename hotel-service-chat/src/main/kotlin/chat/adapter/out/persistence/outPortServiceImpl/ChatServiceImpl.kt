package chat.adapter.out.persistence.outPortServiceImpl;

import chat.adapter.out.persistence.model.ChatMessageMappedEntity;
import chat.adapter.out.persistence.model.ChatUserMappedEntity;
import chat.application.port.out.ChatService;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {

    private final ChatMessageService chatService;

    public void sendFirstChatMessageOnDemand(CustomerDTO customerDTO, CustomerDTO staffSender, boolean isFullCheckin) {

        Optional<ChatMessageMappedEntity> lastMessageFromStaff = chatService.getLastMessageByCustomerAndReceiverIds(staffSender.getId(), customerDTO.getId());

        //Send only first staffSender message!    
        if(lastMessageFromStaff.isEmpty())
        {

            String welcomeMsg = "Hi, welcome to our Hotel! Please write me, if you need something";
            String welcomeGuestMsg = "Hi, welcome to thr guest view of our Hotel! Please get the hotel-code at the reception - without the hotel-code, you are not listed as a hotel guest, and you can not view the customers in the wall... ";

            if("de".equalsIgnoreCase(customerDTO.getPrefferedLanguage()))
            {
                welcomeMsg = "Hallo, herzlich willkommen im Hotel! Bitte schreiben Sie mir, wenn Sie etwas brauchen";
                welcomeGuestMsg = "Hallo, herzlich willkommen im Hotel Gast-Zugang! Bitte bekommen Sie den Zugang-Kode an der Rezeption. Ohne Hotel-Kode sind ihre Aktivitäten in Hotel beschränkt";
            }

            String msg = (isFullCheckin ? welcomeMsg : welcomeGuestMsg);

            var sender = (staffSender);
            var receiver = (customerDTO);
            var initId = (System.currentTimeMillis());
            var timestamp = (new Timestamp(System.currentTimeMillis()));

            ChatMsgDTO chatMsgDTO = new ChatMsgDTO(initId, msg, sender.getDomainEntityId(), receiver.getDomainEntityId(), true, false, false, 123, null, null, true);
            
            chatService.save(chatMsgDTO);
        }
    }

    @Override
    public ChatMessageMappedEntity convertFromDTO(ChatMsgDTO msgDTO) {
        return null;
    }

    @Override
    public ChatUserMappedEntity convertFromDTO(CustomerDTO msgDTO) {
        return null;
    }

    @Override
    public ChatMsgDTO convertToDTO(ChatMessageMappedEntity entity) {
        return null;
    }

    @Override
    public CustomerDTO convertToDTO(ChatUserMappedEntity entity) {
        return null;
    }

    @Override
    public ChatMessageMappedEntity createChatMessageFromDTO(ChatMsgDTO msgDTO) {
        return null;
    }

    @Override
    public ChatUserMappedEntity updateCustomerFromDTO(CustomerDTO customerDTO) {
        return null;
    }

	public ChatMsgDTO addChatMessage(ChatMsgDTO chatMessageDto) {
		return null;
	}

	public List<ChatMsgDTO> getMessagesByCustomerId(long customerId, int receiverId) {
		return List.of();
	}

	public Set<CustomerDTO> getNotHotelChatPartners(long customerId, String city, long hotelId) {
		return Set.of();
	}

	public Set<CustomerDTO> getAllContactChatPartners(long customerId, String city, long hotelId) {
		return Set.of();
	}

	public Set<CustomerDTO> getAllNotChatPartners(long customerId, String city, long hotelId, int pageNumber) {
		return Set.of();
	}

	public void markMessageRead(long customerId, String messageIds) {
	}

	public void markChatRead(long customerId, int senderId, long maxSeenChatMessageId) {
	}

    @Override
    public ChatMsgDTO addUpdateChatMessage(ChatMsgDTO dto) {
        return null;
    }
}
