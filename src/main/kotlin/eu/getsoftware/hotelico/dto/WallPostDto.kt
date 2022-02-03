package eu.getsoftware.hotelico.dto;

import java.sql.Timestamp;
import java.util.Date;

public class WallPostDto: BasicDTO
{
    var creationTime = 0L

    var timestamp: Timestamp? = null

    var message: String? = null

    var senderName: String? = null

    var sendTimeString: String? = null

    var hotelStaff = false

    var hotelId = 0L

    var senderId = 0L

    var specialContent = mutableMapOf<String, String>()

    constructor(): super()

    fun getSendTime(): Date? {
        return if (timestamp != null) Date(timestamp!!.time) else null
    }

}
