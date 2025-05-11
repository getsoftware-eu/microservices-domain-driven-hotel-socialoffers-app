package eu.getsoftware.hotelico.chat.adapter.out.persistence.outPortServiceImpl

import eu.getsoftware.hotelico.chat.adapter.out.persistence.model.ChatMessageMappedEntity
import eu.getsoftware.hotelico.chat.application.port.out.ChatService
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO
import eu.getsoftware.hotelico.clients.api.application.infrastructure.chat.dto.ChatMsgDTO
import org.springframework.stereotype.Service
import java.sql.Timestamp

//@RequiredArgsConstructor
@Service
internal class ChatServiceImpl(
    val chatMessageService: ChatMessageService
) : ChatService {
    
    fun sendFirstChatMessageOnDemand(customerDTO: CustomerDTO, staffSender: CustomerDTO, isFullCheckin: Boolean) {
        val lastMessageFromStaff: ChatMessageMappedEntity? = chatMessageService.getLastMessageByCustomerAndReceiverIds(staffSender.id, customerDTO.id)

        //Send only first staffSender message!    
        if (lastMessageFromStaff==null) {
            var welcomeMsg = "Hi, welcome to our Hotel! Please write me, if you need something"
            var welcomeGuestMsg =
                "Hi, welcome to thr guest view of our Hotel! Please get the hotel-code at the reception - without the hotel-code, you are not listed as a hotel guest, and you can not view the customers in the wall... "

            if ("de".equals(customerDTO.prefferedLanguage, ignoreCase = true)) {
                welcomeMsg = "Hallo, herzlich willkommen im Hotel! Bitte schreiben Sie mir, wenn Sie etwas brauchen"
                welcomeGuestMsg =
                    "Hallo, herzlich willkommen im Hotel Gast-Zugang! Bitte bekommen Sie den Zugang-Kode an der Rezeption. Ohne Hotel-Kode sind ihre Aktivitäten in Hotel beschränkt"
            }

            val msg = (if (isFullCheckin) welcomeMsg else welcomeGuestMsg)

            val sender = (staffSender)
            val receiver = (customerDTO)
            val initId = (System.currentTimeMillis())
            val timestamp = (Timestamp(System.currentTimeMillis()))

            val chatMsgDTO = ChatMsgDTO(
                initId,
                msg,
                sender.domainEntityId,
                receiver.domainEntityId,
                true,
                false,
                false,
                123,
                null,
                null,
                true
            )

            chatMessageService.save(chatMsgDTO)
        }
    }

    override fun addChatMessage(chatMessageDto: ChatMsgDTO): ChatMsgDTO {
        TODO("Not yet implemented")
    }

    override fun getMessagesByCustomerId(customerId: Long, receiverId: Int): List<ChatMsgDTO> {
        return listOf<ChatMsgDTO>()
    }

    override fun getNotHotelChatPartners(customerId: Long, city: String?, hotelId: Long): Set<CustomerDTO> {
        return setOf<CustomerDTO>()
    }

    override fun getAllContactChatPartners(customerId: Long, city: String?, hotelId: Long): Set<CustomerDTO> {
        return setOf<CustomerDTO>()
    }

    override fun getAllNotChatPartners(
        customerId: Long,
        city: String?,
        hotelId: Long,
        pageNumber: Int
    ): Set<CustomerDTO> {
        return setOf<CustomerDTO>()
    }

    override fun markMessageRead(customerId: Long, messageIds: String?) {
    }

    override fun markChatRead(customerId: Long, senderId: Int, maxSeenChatMessageId: Long) {
    }

    override fun addUpdateChatMessage(dto: ChatMsgDTO): ChatMsgDTO {
        TODO("Not yet implemented")

    }
}
