package eu.getsoftware.hotelico.clients.api.clients.domain.chat

import java.sql.Timestamp

interface IChatMessageView {
    
    val isActive: Boolean
    val message: String?
    val initId: Long

    val timestamp: Timestamp?

    val isSeenByReceiver: Boolean
    val isDelieveredToReceiver: Boolean
    
    //    CustomerDomainEntityId getSenderId();
    //    CustomerDomainEntityId getReceiverId();
    
    val specialChatContent: String?
}
