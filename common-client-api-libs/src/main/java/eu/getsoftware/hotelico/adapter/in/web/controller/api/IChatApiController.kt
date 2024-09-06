package eu.getsoftware.hotelico.adapter.`in`.web.controller.api

import eu.getsoftware.hotelico.clients.common.dto.CustomerDTO
import eu.getsoftware.hotelico.clients.infrastructure.chat.dto.ChatMsgDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

interface IChatApiController {
    @PostMapping("/message")
    fun postMessage(@RequestBody msgDTO: ChatMsgDTO?): ChatMsgDTO

    @PostMapping("/customer")
    fun updateUser(@RequestBody customerDTO: CustomerDTO?): CustomerDTO
}