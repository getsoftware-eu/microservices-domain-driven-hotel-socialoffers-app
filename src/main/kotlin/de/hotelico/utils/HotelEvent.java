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
public enum HotelEvent
{
	EVENT_ALL_INFO("EVENT_ALL_INFO", null, null, null, null),
	EVENT_CHECKIN("EVENT_CHECKIN", Hotel.class, null, null, null),
	EVENT_REGISTER("EVENT_REGISTER", Customer.class, null, null, null),
	EVENT_CHECKOUT("EVENT_CHECKOUT", Hotel.class, null, null, null),
	EVENT_LOGIN("EVENT_LOGIN", Customer.class, null, null, null),
	EVENT_DEAL_NEW_UPDATE("EVENT_DEAL_NEW_UPDATE", CustomerDeal.class, null, null, null),
	EVENT_MENU_NEW_UPDATE("EVENT_MENU_NEW_UPDATE", CustomerDeal.class, null, null, null),
	EVENT_ACTIVITY_NEW_LAST_MINUTE("EVENT_ACTIVITY_NEW_LAST_MINUTE", HotelActivity.class, "/" + ControllerUtils.HOST_SUFFIX + "#/app/activityList", "Last Minute Offer", null),
	EVENT_WALL_NEW_MESSAGE("EVENT_WALL_NEW_MESSAGE", HotelWallPost.class, "/" + ControllerUtils.HOST_SUFFIX + "#/app/wall", "New Wall Message", ControllerUtils.PUSH_ICON),
	EVENT_WALL_SEND_MESSAGE("EVENT_WALL_SEND_MESSAGE", HotelWallPost.class, null, null, null),
	EVENT_CHAT_NEW_MESSAGE("EVENT_CHAT_NEW_MESSAGE", ChatMessage.class, "/" + ControllerUtils.HOST_SUFFIX + "#/app/chat/", "New chat Message", ControllerUtils.PUSH_ICON),
	EVENT_CHAT_SEND_MESSAGE("EVENT_CHAT_SEND_MESSAGE", ChatMessage.class, null, null, null),
	
	EVENT_LOGO_CUSTOMER_CHANGE_MESSAGE("EVENT_LOGO_CUSTOMER_CHANGE_MESSAGE", Customer.class, null, null, null),
	EVENT_LOGO_ACTIVITY_CHANGE_MESSAGE("EVENT_LOGO_ACTIVITY_CHANGE_MESSAGE", HotelActivity.class, null, null, null),
	EVENT_LOGO_HOTEL_CHANGE_MESSAGE("EVENT_LOGO_HOTEL_CHANGE_MESSAGE", Hotel.class, null, null, null),
	
	EVENT_PING("EVENT_PING", null, null, null, null),
	EVENT_ONLINE_CUSTOMERS("EVENT_ONLINE_CUSTOMERS", null, null, null, null),
	
	EVENT_REMOVE_ACTIVITY("EVENT_REMOVE_ACTIVITY", HotelActivity.class, null, null, null);
	
	private String value;
	private String pushUrl;
	private String pushTitle;
	private String pushIcon;
	private Class entityClass;
	
	private HotelEvent(String value, Class entityClass, String pushUrl, String pushTitle, String pushIcon)
	{
		this.value = value;
		this.pushUrl = pushUrl;
		this.pushTitle = pushTitle;
		this.pushIcon = pushIcon;
		this.entityClass = entityClass;
	}
	
	public String getValue()
	{
		return value;
	}

	public String getPushUrl()
	{
		return pushUrl;
	}

	public String getPushTitle()
	{
		return pushTitle;
	}

	public String getPushIcon()
	{
		return pushIcon;
	}

	public Class getEntityClass()
	{
		return entityClass;
	}
	
	public String getEntityString()
	{
		return entityClass.getSimpleName();
	}
	
	public static HotelEvent parseByValue(String value)
	{
		if (!ObjectUtils.isEmpty(value))
		{
			for (HotelEvent nextEvent : HotelEvent.values())
			{
				if (nextEvent.getValue().equalsIgnoreCase(value))
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
