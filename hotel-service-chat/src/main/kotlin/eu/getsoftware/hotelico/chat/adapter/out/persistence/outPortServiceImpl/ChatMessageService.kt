package eu.getsoftware.hotelico.chat.adapter.out.persistence.outPortServiceImpl

import eu.getsoftware.hotelico.chat.adapter.out.persistence.model.ChatMessageMappedEntity
import eu.getsoftware.hotelico.chat.adapter.out.persistence.repository.ChatMessageRepository
import eu.getsoftware.hotelico.chat.application.port.out.IChatMessageService
import eu.getsoftware.hotelico.clients.api.application.infrastructure.chat.dto.ChatMsgDTO
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId
import org.springframework.stereotype.Service

@Service
//@RequiredArgsConstructor
class ChatMessageService(
    private val chatMessageRepository: ChatMessageRepository
) : IChatMessageService {
    
    fun saveMessage(message: ChatMessageMappedEntity) {
        chatMessageRepository.save(message)
    }

    val allMessages: MutableList<ChatMessageMappedEntity?>
        get() = chatMessageRepository.findAll()

    fun getLastMessageByCustomerAndReceiverIds(
        customerId: CustomerDomainEntityId,
        receiverId: CustomerDomainEntityId
    ): ChatMessageMappedEntity? {
        return chatMessageRepository.getLastMessageByCustomerAndReceiverIds(customerId, receiverId)
    }

    fun getLastChatMessage(
        fromCustomerId: CustomerDomainEntityId,
        toCustomerId: CustomerDomainEntityId
    ): ChatMessageMappedEntity? {
        return chatMessageRepository.getLastMessageByCustomerAndReceiverIds(fromCustomerId, toCustomerId)
    }

    fun save(chatMsgDTO: ChatMsgDTO?) {
        //TODO("Not yet implemented")
    }

    override fun getLastMessageByCustomerAndReceiverIds(customerId: Long, receiverId: Long): ChatMessageMappedEntity? {
        //TODO("Not yet implemented")
        return null;
    }

    override fun getLastChatMessage(fromCustomerId: Long?, toCustomerId: Long?): ChatMessageMappedEntity? {
        //TODO("Not yet implemented")
        return null;
    }
}
