package eu.getsoftware.hotelico.chat.application.domain.model;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId

data class ChatMsgDomainEntity(
    val hotelDomainEntityId: HotelDomainEntityId,
    val senderDomainEntityId: CustomerDomainEntityId,
    val receiverDomainEntityId: CustomerDomainEntityId
)
