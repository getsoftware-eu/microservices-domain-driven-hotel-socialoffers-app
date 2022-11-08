package eu.getsoftware.hotelico.chat.infrastructure.dto

import eu.getsoftware.hotelico.clients.infrastructure.dto.BasicDTO
import eu.getsoftware.hotelico.clients.infrastructure.utils.ControllerUtils
import java.sql.Timestamp
import java.util.*

class ChatMessageDTO : BasicDTO
{
  var creationTime: Long? = null

  var message : String? = null
  
  var hotelStaff: Boolean = false
  
  var timestamp: Timestamp? = null
  
  var senderId : Long? = null

  var receiverId: Long? = null

  var seenByReceiver: Boolean = false

  var delieveredToReceiver: Boolean = false

  var specialContent = mapOf<String, String>()
  
  constructor() 
  
  constructor(initId: Long, message: String, senderId: Long, receiverId: Long) {
    this.initId = initId
    this.message = message
    this.senderId = senderId
    this.receiverId = receiverId
  }

  fun getSendTime(): Date?
  {
    return if(timestamp != null) Date(timestamp?.time!!) else null
  }

  fun getSendTimeString(): String?
  {
    return if(timestamp != null) ControllerUtils.getTimeFormatted(timestamp) else null
  }
  
}
