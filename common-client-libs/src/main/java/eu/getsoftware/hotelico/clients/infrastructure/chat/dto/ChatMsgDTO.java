package eu.getsoftware.hotelico.clients.infrastructure.chat.dto;

import java.sql.Timestamp;

public record ChatMsgDTO(
		Long initId,
	    String message,
	    Long senderId,
	    Long receiverId,
	    Boolean hotelStaff,
	    Boolean seenByReceiver,
	    Boolean delieveredToReceiver,
	    Long creationTime,
	    Timestamp timestamp
    ) 
		//implements BasicDTO(initId)
{
//    Map specialContent = HashMap<String, String>();
//	
//	Date getSendTime() 
//    {
//        return if(timestamp != null) Date(timestamp?.time!!) else null
//    }
//	
//	String getSendTimeString() 
//    {
//        return if(timestamp != null) eu.getsoftware.hotelico.hotel.clients.chat.utils.ChatUtils.getTimeFormatted(timestamp) else null
//    }

}
