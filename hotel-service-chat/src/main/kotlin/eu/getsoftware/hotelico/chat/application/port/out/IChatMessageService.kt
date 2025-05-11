package eu.getsoftware.hotelico.chat.application.port.out

import eu.getsoftware.hotelico.chat.adapter.out.persistence.model.ChatMessageMappedEntity

interface IChatMessageService {
  
    fun getLastMessageByCustomerAndReceiverIds(customerId: Long, receiverId: Long): ChatMessageMappedEntity?

    fun getLastChatMessage(fromCustomerId: Long?, toCustomerId: Long?): ChatMessageMappedEntity?
}
