package chat.application.domain.usecase

import chat.application.port.`in`.process.ChatCheckinUseCase
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CheckinRequestDTO
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO

internal class ChatCheckinUseCaseImpl: ChatCheckinUseCase {
    
    override fun createCustomerCheckinMsg(checkinRequestDto: CheckinRequestDTO): ChatMsgDTO {
        TODO("Not yet implemented")
    }
}