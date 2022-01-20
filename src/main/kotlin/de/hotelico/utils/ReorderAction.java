package de.hotelico.utils;

import de.hotelico.model.ChatMessage;
import de.hotelico.model.Customer;
import de.hotelico.model.CustomerDeal;
import de.hotelico.model.Hotel;
import de.hotelico.model.HotelActivity;
import de.hotelico.model.HotelWallPost;

/**
 * <br/>
 * Created by e.fanshil
 * At 19.10.2015 17:19
 */
public enum ReorderAction
{
	ORDER_TOP("ORDER_TOP", 0),
	ORDER_BOTTOM("ORDER_BOTTOM", 1),
	ORDER_STEP_TOP("ORDER_STEP_TOP", 2),
	ORDER_STEP_BOTTOM("ORDER_STEP_BOTTOM", 3),
	 ;
	
	private String title;
	private int value;
	
	private ReorderAction(String title, int value)
	{
		this.title = title;
		this.value = value;
	}
	
	public int getValue()
	{
		return value;
	}


	public String getTitle()
	{
		return title;
	}

	public static ReorderAction parseByTitle(String title)
	{
		if (!ObjectUtils.isEmpty(title))
		{
			for (ReorderAction nextEvent : ReorderAction.values())
			{
				if (nextEvent.getTitle().equalsIgnoreCase(title))
				{
					return nextEvent;
				}
			}
		}
//		logger.error("Es konnte kein Enum.WoPriority für value='" + value + "' gefunden werden.");
		return null;
	}
	
//	private String getPrefix()
//	{
//		return ControllerUtils.isEmptyString(ControllerUtils.HOST_SUFFIX)? "/" : "/" + ControllerUtils.HOST_SUFFIX;
//	}
}
