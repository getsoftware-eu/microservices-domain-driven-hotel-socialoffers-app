package eu.getsoftware.hotelico.apiController

import eu.getsoftware.hotelico.clients.infrastructure.chat.dto.ChatMsgDTO
import eu.getsoftware.hotelico.clients.infrastructure.hotel.dto.CustomerDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

interface IChatApiController {
    @PostMapping("/message")
    fun postMessage(@RequestBody msgDTO: ChatMsgDTO?): ChatMsgDTO

    @PostMapping("/customer")
    fun updateUser(@RequestBody customerDTO: CustomerDTO?): CustomerDTO
}