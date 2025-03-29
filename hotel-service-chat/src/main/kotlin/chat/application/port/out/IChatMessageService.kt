package chat.application.port.out

import chat.adapter.out.persistence.model.ChatMessageMappedEntity

interface IChatMessageService {
  
    fun getLastMessageByCustomerAndReceiverIds(customerId: Long, receiverId: Long): ChatMessageMappedEntity?

    fun getLastChatMessage(fromCustomerId: Long?, toCustomerId: Long?): ChatMessageMappedEntity?
}
