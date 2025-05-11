package eu.getsoftware.hotelico.chat.application.domain.usecase

import chat.application.port.`in`.process.ChatCheckinUseCase
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseRequestDTO
import eu.getsoftware.hotelico.clients.api.application.infrastructure.chat.dto.ChatMsgDTO

internal class ChatCheckinUseCaseImpl: ChatCheckinUseCase {
    
    override fun createCustomerCheckinMsg(checkinUseCaseRequestDto: CheckinUseCaseRequestDTO): ChatMsgDTO {
        TODO("Not yet implemented")
    }
}