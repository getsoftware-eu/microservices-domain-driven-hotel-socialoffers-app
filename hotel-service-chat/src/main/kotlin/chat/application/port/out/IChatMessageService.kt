package chat.application.port.out;

import chat.adapter.out.persistence.model.ChatMessageMappedEntity;

import java.util.Optional;

public interface IChatMessageService {
    
    Optional<ChatMessageMappedEntity> getLastMessageByCustomerAndReceiverIds(long customerId, long receiverId);

    Optional<ChatMessageMappedEntity> getLastChatMessage(Long fromCustomerId, Long toCustomerId);
}
