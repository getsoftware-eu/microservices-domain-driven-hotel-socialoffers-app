package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatMessageEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatUserEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.application.port.out.IChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements IChatService {

    
    private final ChatMessageService chatService;

    public void sendFirstChatMessageOnDemand(CustomerDTO customerDTO, CustomerDTO staffSender, boolean isFullCheckin) {

        Optional<ChatMessageEntity> lastMessageFromStaff = chatService.getLastMessageByCustomerAndReceiverIds(staffSender.getId(), customerDTO.getId());

        //Send only first staffSender message!    
        if(lastMessageFromStaff.isEmpty())
        {

            String wellcomeMsg = "Hi, welcome to our Hotel! Please write me, if you need something";
            String wellcomeGuestMsg = "Hi, welcome to thr guest view of our Hotel! Please get the hotel-code at the reception - without the hotel-code, you are not listed as a hotel guest, and you can not view the customers in the wall... ";

            if("de".equalsIgnoreCase(customerDTO.getPrefferedLanguage()))
            {
                wellcomeMsg = "Hallo, herzlich willkommen im Hotel! Bitte schreiben Sie mir, wenn Sie etwas brauchen";
                wellcomeGuestMsg = "Hallo, herzlich willkommen im Hotel Gast-Zugang! Bitte bekommen Sie den Zugang-Kode an der Rezeption. Ohne Hotel-Kode sind ihre Aktivitäten in Hotel beschränkt";
            }

            String msg = (isFullCheckin ? wellcomeMsg : wellcomeGuestMsg);

            var sender = (staffSender);
            var receiver = (customerDTO);
            var initId = (new Date().getTime());
            var timestamp = (new Timestamp(new Date().getTime()));

            ChatMsgDTO chatMsgDTO = new ChatMsgDTO(initId, msg, sender.getId(), receiver.getId(), true, false, false, 123, null, null, true);
            
            chatService.save(chatMsgDTO);
        }
    }

    @Override
    public ChatMessageEntity convertFromDTO(ChatMsgDTO msgDTO) {
        return null;
    }

    @Override
    public ChatUserEntity convertFromDTO(CustomerDTO msgDTO) {
        return null;
    }

    @Override
    public ChatMsgDTO convertToDTO(ChatMessageEntity entity) {
        return null;
    }

    @Override
    public CustomerDTO convertToDTO(ChatUserEntity entity) {
        return null;
    }

    @Override
    public ChatMessageEntity createChatMessageFromDTO(ChatMsgDTO msgDTO) {
        return null;
    }

    @Override
    public ChatUserEntity updateCustomerFromDTO(CustomerDTO customerDTO) {
        return null;
    }
}
