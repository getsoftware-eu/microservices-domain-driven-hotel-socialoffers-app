package eu.getsoftware.hotelico.chat.adapter.`in`.web.controller

//import eu.getsoftware.hotelico.clients.openapi.generated.chat.ChatApi
//import eu.getsoftware.hotelico.clients.openapi.generated.chat.dto.ChatMsgRestDTO
//import eu.getsoftware.hotelico.clients.openapi.generated.chat.dto.CustomerRestDTO
import eu.getsoftware.hotelico.chat.adapter.out.persistence.model.ChatMessageMappedEntity
import eu.getsoftware.hotelico.chat.adapter.out.persistence.model.ChatUserMappedEntity
import eu.getsoftware.hotelico.chat.adapter.out.persistence.repository.ChatMessageRepository
import eu.getsoftware.hotelico.chat.adapter.out.persistence.repository.ChatUserRepository
import eu.getsoftware.hotelico.chat.application.port.out.ChatDTOService
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO
import eu.getsoftware.hotelico.clients.api.application.infrastructure.chat.dto.ChatMsgDTO
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/v0/chat")
@RequiredArgsConstructor
class ChatMSCommunicationController(
    private val chatMessageRepository: ChatMessageRepository,
    private val chatUserRepository: ChatUserRepository,
    private val chatDTOService: ChatDTOService
) //: ChatApi 
{

    //	@PostMapping("/message")  in interface
    //override 
    fun postMessage(@RequestBody msgDTO: ChatMsgDTO): ChatMsgDTO {
        val createdEntity: ChatMessageMappedEntity = chatDTOService.createChatMessageFromDTO(msgDTO)
        return chatDTOService.convertToDTO(createdEntity)
    }

    //	@PostMapping("/customer") in interface
    //override 
    fun updateUser(@RequestBody customerDTO: CustomerDTO): CustomerDTO {
        val updateEntity: ChatUserMappedEntity = chatDTOService.updateCustomerFromDTO(customerDTO)
        return chatDTOService.convertToDTO(updateEntity)
    }
}
