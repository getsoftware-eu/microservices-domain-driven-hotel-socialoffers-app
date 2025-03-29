package chat.application.port.out

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerDTO
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO

interface ChatService {
    
    fun addChatMessage(chatMessageDto: ChatMsgDTO): ChatMsgDTO

    fun getMessagesByCustomerId(customerId: Long, receiverId: Int): List<ChatMsgDTO>

    fun getNotHotelChatPartners(customerId: Long, city: String?, hotelId: Long): Set<CustomerDTO>

    fun getAllContactChatPartners(customerId: Long, city: String?, hotelId: Long): Set<CustomerDTO>

    fun markMessageRead(customerId: Long, messageIds: String?)

    fun getAllNotChatPartners(customerId: Long, city: String?, hotelId: Long, pageNumber: Int): Set<CustomerDTO>

    fun markChatRead(customerId: Long, senderId: Int, maxSeenChatMessageId: Long)

    fun addUpdateChatMessage(dto: ChatMsgDTO): ChatMsgDTO
}
