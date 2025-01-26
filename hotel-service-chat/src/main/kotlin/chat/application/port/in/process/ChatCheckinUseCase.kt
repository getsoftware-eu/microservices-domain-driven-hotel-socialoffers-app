package chat.application.port.`in`.process

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CheckinRequestDTO
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO

interface ChatCheckinUseCase {

    @Throws(Throwable::class)
    fun createCustomerCheckinMsg(checkinRequestDto: CheckinRequestDTO): ChatMsgDTO

}