package eu.getsoftware.hotelico.chat.application.port.`in`.usecase

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseRequestDTO
import eu.getsoftware.hotelico.clients.api.application.infrastructure.chat.dto.ChatMsgDTO

interface ChatCheckinUseCase {

    @Throws(Throwable::class)
    fun createCustomerCheckinMsg(checkinUseCaseRequestDto: CheckinUseCaseRequestDTO): ChatMsgDTO

}