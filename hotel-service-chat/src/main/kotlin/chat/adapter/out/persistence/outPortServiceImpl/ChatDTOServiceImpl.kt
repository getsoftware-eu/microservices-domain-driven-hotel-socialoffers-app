package chat.adapter.out.persistence.outPortServiceImpl

import chat.adapter.out.persistence.model.ChatMessageMappedEntity
import chat.adapter.out.persistence.model.ChatUserMappedEntity
import chat.application.port.out.ChatDTOService
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO
import eu.getsoftware.hotelico.clients.api.application.infrastructure.chat.dto.ChatMsgDTO
import org.springframework.stereotype.Service

@Service
internal class ChatDTOServiceImpl : ChatDTOService {
    
    override fun convertFromDTO(msgDTO: ChatMsgDTO): ChatMessageMappedEntity {
        TODO("Not yet implemented")
    }

    override fun convertFromDTO(msgDTO: CustomerDTO): ChatUserMappedEntity? {
        TODO("Not yet implemented")
    }

    override fun convertToDTO(entity: ChatMessageMappedEntity): ChatMsgDTO {
        TODO("Not yet implemented")
    }

    override fun convertToDTO(entity: ChatUserMappedEntity): CustomerDTO {
        TODO("Not yet implemented")
    }

    override fun createChatMessageFromDTO(msgDTO: ChatMsgDTO): ChatMessageMappedEntity {
        TODO("Not yet implemented")
    }

    override fun updateCustomerFromDTO(customerDTO: CustomerDTO): ChatUserMappedEntity {
        TODO("Not yet implemented")
    }


}