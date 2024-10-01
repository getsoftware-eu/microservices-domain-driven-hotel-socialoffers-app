package eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto;

import lombok.Getter;

import java.sql.Timestamp;
import java.util.Map;

@Getter
public record ChatMsgDTO(
		Long initId,
		String message,
		Long senderId,
		Long receiverId,
		Boolean hotelStaff,
		Boolean seenByReceiver,
		Boolean delieveredToReceiver,
		Long creationTime,
		Timestamp timestamp,
		Map<String, String> specialContent

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
