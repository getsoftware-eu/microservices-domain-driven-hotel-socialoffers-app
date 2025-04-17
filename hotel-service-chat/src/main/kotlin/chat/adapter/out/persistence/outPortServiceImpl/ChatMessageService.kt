package chat.adapter.out.persistence.outPortServiceImpl

import chat.adapter.out.persistence.model.ChatMessageMappedEntity
import chat.adapter.out.persistence.repository.ChatMessageRepository
import chat.application.port.out.IChatMessageService
import eu.getsoftware.hotelico.clients.api.application.infrastructure.chat.dto.ChatMsgDTO
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId
import org.springframework.stereotype.Service
import java.util.*

@Service
//@RequiredArgsConstructor
class ChatMessageService(
    private val chatMessageRepository: ChatMessageRepository
) : IChatMessageService {
    
    fun saveMessage(message: ChatMessageMappedEntity) {
        chatMessageRepository.save(message)
    }

    val allMessages: List<ChatMessageMappedEntity>
        get() = chatMessageRepository.findAll()

    fun getLastMessageByCustomerAndReceiverIds(
        customerId: CustomerDomainEntityId?,
        receiverId: CustomerDomainEntityId?
    ): ChatMessageMappedEntity? {
        return chatMessageRepository.getLastMessageByCustomerAndReceiverIds(customerId, receiverId)
    }

    fun getLastChatMessage(
        fromCustomerId: CustomerDomainEntityId?,
        toCustomerId: CustomerDomainEntityId?
    ): Optional<ChatMessageMappedEntity> {
        return chatMessageRepository.getLastMessageByCustomerAndReceiverIds(fromCustomerId, toCustomerId)
    }

    fun save(chatMsgDTO: ChatMsgDTO?) {
        //TODO("Not yet implemented")
    }

    override fun getLastMessageByCustomerAndReceiverIds(customerId: Long, receiverId: Long): ChatMessageMappedEntity? {
        //TODO("Not yet implemented")
    }

    override fun getLastChatMessage(fromCustomerId: Long?, toCustomerId: Long?): ChatMessageMappedEntity? {
        //TODO("Not yet implemented")
    }
}
