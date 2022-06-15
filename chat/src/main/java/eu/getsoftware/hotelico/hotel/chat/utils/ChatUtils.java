package eu.getsoftware.hotelico.hotel.chat.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public class ChatUtils
{
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	
	public static String getTimeFormatted(Timestamp timeStamp)
	{
		return timeStamp!=null? getTimeFormatted(new Date(timeStamp.getTime())) : "";
	}
	
	public static String getTimeFormatted(Date date)
	{
		String time = "";
		
		if(date==null)
			return time;
		
		if(date.after(new DateTime().withTimeAtStartOfDay().toDate()))
			time = ChatUtils.timeFormat.format(date.getTime());
		else
			//			time = ControllerUtils.dateTimeFormat.format(date.getTime());
			time = ChatUtils.dateFormat.format(date.getTime());
		return time;
	}
}
