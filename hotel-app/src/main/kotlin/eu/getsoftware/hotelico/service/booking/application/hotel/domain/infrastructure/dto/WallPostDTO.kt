package eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.WallPostDomainEntityId
import eu.getsoftware.hotelico.clients.common.dto.BasicDTO
import java.sql.Timestamp
import java.util.*

data class WallPostDTO(var hotelId : HotelDomainEntityId, var senderId: CustomerDomainEntityId): BasicDTO<WallPostDomainEntityId>()
{
    var creationTime = 0L

    var timestamp: Timestamp? = null

    var message: String? = null

    var senderName: String? = null

    var sendTimeString: String? = null

    var hotelStaff = false
    
    var specialContent = mutableMapOf<String, String>()

    fun getSendTime(): Date? {
        return if (timestamp != null) Date(timestamp!!.time) else null
    }
}
