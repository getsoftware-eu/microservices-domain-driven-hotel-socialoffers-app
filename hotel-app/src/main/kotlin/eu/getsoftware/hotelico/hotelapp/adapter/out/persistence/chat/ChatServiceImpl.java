package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.chat;

import eu.getsoftware.hotelico.clients.api.clients.domain.customer.ICustomerRootEntity;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.hotelapp.application.chat.port.out.IChatService;

import java.sql.Timestamp;
import java.util.Date;

public class ChatServiceImpl implements IChatService {
    
    @Override
    public void sendFirstChatMessageOnDemand(ICustomerRootEntity customerEntity, ICustomerRootEntity staffSender, boolean isFullCheckin) {

        ChatMsgDTO lastMessageFromStaff = chatService.getLastMessageByCustomerAndReceiverIds(staffSender.getId(), customerEntity.getId());

        //Send only first staffSender message!    
        if(lastMessageFromStaff==null)
        {

            String wellcomeMsg = "Hi, welcome to our Hotel! Please write me, if you need something";
            String wellcomeGuestMsg = "Hi, welcome to thr guest view of our Hotel! Please get the hotel-code at the reception - without the hotel-code, you are not listed as a hotel guest, and you can not view the customers in the wall... ";

            if("de".equalsIgnoreCase(customerEntity.getEntityAggregate().getPrefferedLanguage()))
            {
                wellcomeMsg = "Hallo, herzlich willkommen im Hotel! Bitte schreiben Sie mir, wenn Sie etwas brauchen";
                wellcomeGuestMsg = "Hallo, herzlich willkommen im Hotel Gast-Zugang! Bitte bekommen Sie den Zugang-Kode an der Rezeption. Ohne Hotel-Kode sind ihre Aktivitäten in Hotel beschränkt";
            }

            String msg = (isFullCheckin ? wellcomeMsg : wellcomeGuestMsg);

            var sender = (staffSender);
            var receiver = (customerEntity);
            var initId = (new Date().getTime());
            var timestamp = (new Timestamp(new Date().getTime()));

            ChatMsgDTO chatMsgDTO = new ChatMsgDTO(initId, msg, sender.getId(), receiver.getId(), timestamp);
            
            chatService.save(chatMsgDTO);
        }
    }
}
