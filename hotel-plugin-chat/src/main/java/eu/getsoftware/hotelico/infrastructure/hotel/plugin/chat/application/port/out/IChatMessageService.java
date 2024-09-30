package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.application.port.out;

import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatMessageEntity;

import java.util.Optional;

public interface IChatMessageService {
    
    Optional<ChatMessageEntity> getLastMessageByCustomerAndReceiverIds(long customerId, long receiverId);

    Optional<ChatMessageEntity> getLastChatMessage(Long fromCustomerId, Long toCustomerId);
}
