package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto;

import eu.getsoftware.hotelico.clients.common.dto.BasicDTO
import java.sql.Timestamp
import java.util.*

data class WallPostDTO(var hotelId : Long = 0L): BasicDTO()
{
    var creationTime = 0L

    var timestamp: Timestamp? = null

    var message: String? = null

    var senderName: String? = null

    var sendTimeString: String? = null

    var hotelStaff = false
    
    var senderId = 0L

    var specialContent = mutableMapOf<String, String>()

    fun getSendTime(): Date? {
        return if (timestamp != null) Date(timestamp!!.time) else null
    }
}
