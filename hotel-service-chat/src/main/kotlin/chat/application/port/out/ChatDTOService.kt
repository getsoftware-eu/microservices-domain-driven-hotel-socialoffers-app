package chat.application.port.out

import chat.adapter.out.persistence.model.ChatMessageMappedEntity
import chat.adapter.out.persistence.model.ChatUserMappedEntity
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO
import eu.getsoftware.hotelico.clients.api.application.infrastructure.chat.dto.ChatMsgDTO

interface ChatDTOService {

    fun convertFromDTO(msgDTO: ChatMsgDTO): ChatMessageMappedEntity?

    fun convertFromDTO(msgDTO: CustomerDTO): ChatUserMappedEntity?

    fun convertToDTO(entity: ChatMessageMappedEntity): ChatMsgDTO

    fun convertToDTO(entity: ChatUserMappedEntity): CustomerDTO

    fun createChatMessageFromDTO(msgDTO: ChatMsgDTO): ChatMessageMappedEntity

    fun updateCustomerFromDTO(customerDTO: CustomerDTO): ChatUserMappedEntity

}