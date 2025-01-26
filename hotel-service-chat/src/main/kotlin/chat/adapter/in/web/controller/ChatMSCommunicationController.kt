package chat.adapter.`in`.web.controller

import chat.adapter.out.persistence.model.ChatMessageMappedEntity
import chat.adapter.out.persistence.model.ChatUserMappedEntity
import chat.adapter.out.persistence.repository.ChatMessageRepository
import chat.adapter.out.persistence.repository.ChatUserRepository
import chat.application.port.out.ChatDTOService
import eu.getsoftware.hotelico.clients.api.adapter.`in`.web.controller.IChatApiController
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerDTO
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v0/chat")
@RequiredArgsConstructor
class ChatMSCommunicationController(
    private val chatMessageRepository: ChatMessageRepository,
    private val chatUserRepository: ChatUserRepository,
    private val chatDTOService: ChatDTOService
) : IChatApiController {

    //	@PostMapping("/message")  in interface
    override fun postMessage(@RequestBody msgDTO: ChatMsgDTO): ChatMsgDTO {
        val createdEntity: ChatMessageMappedEntity = chatDTOService.createChatMessageFromDTO(msgDTO)
        return chatDTOService.convertToDTO(createdEntity)
    }

    //	@PostMapping("/customer") in interface
    override fun updateUser(@RequestBody customerDTO: CustomerDTO): CustomerDTO {
        val updateEntity: ChatUserMappedEntity = chatDTOService.updateCustomerFromDTO(customerDTO)
        return chatDTOService.convertToDTO(updateEntity)
    }
}
