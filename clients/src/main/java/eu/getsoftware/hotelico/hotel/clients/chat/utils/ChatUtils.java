package eu.getsoftware.hotelico.hotel.clients.chat.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

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
		
		LocalTime midnight = LocalTime.MIDNIGHT;
		LocalDate today = LocalDate.now(ZoneId.of("Europe/Berlin"));
		LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
		
		LocalDateTime curLocalDate = LocalDateTime.ofInstant(
				date.toInstant(), ZoneId.systemDefault());
		
		if(curLocalDate.compareTo(todayMidnight)>0)
			time = ChatUtils.timeFormat.format(date.getTime());
		else
			//			time = ControllerUtils.dateTimeFormat.format(date.getTime());
			time = ChatUtils.dateFormat.format(date.getTime());
		return time;
	}
}
