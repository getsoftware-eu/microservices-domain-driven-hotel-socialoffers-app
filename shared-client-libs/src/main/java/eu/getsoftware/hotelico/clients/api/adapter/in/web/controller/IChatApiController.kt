package eu.getsoftware.hotelico.clients.api.adapter.`in`.web.controller

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerDTO
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

interface IChatApiController {
    @PostMapping("/message")
    fun postMessage(@RequestBody msgDTO: ChatMsgDTO): ChatMsgDTO

    @PostMapping("/customer")
    fun updateUser(@RequestBody customerDTO: CustomerDTO): CustomerDTO
}