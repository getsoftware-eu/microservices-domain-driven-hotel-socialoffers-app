package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model;

import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.clients.common.utils.ObjectUtils;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.chatview.model.ChatMessageView;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.deal.model.CustomerDeal;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotelCustomer.model.HotelActivity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.utils.IHotelEvent;

/**
 * <br/>
 * Created by e.fanshil
 * At 19.10.2015 17:19
 */
public enum HotelEvent implements IHotelEvent
{
	//TODO: eu ask: how to use event in UseCase Layer, if there are class implementations???
	
	EVENT_ALL_INFO("EVENT_ALL_INFO", null, null, null, null),
	EVENT_CHECKIN("EVENT_CHECKIN", HotelRootEntity.class, null, null, null),
	EVENT_REGISTER("EVENT_REGISTER", CustomerRootEntity.class, null, null, null),
	EVENT_CHECKOUT("EVENT_CHECKOUT", HotelRootEntity.class, null, null, null),
	EVENT_LOGIN("EVENT_LOGIN", CustomerRootEntity.class, null, null, null),
	EVENT_DEAL_NEW_UPDATE("EVENT_DEAL_NEW_UPDATE", CustomerDeal.class, null, null, null),
	EVENT_MENU_NEW_UPDATE("EVENT_MENU_NEW_UPDATE", CustomerDeal.class, null, null, null),
	EVENT_ACTIVITY_NEW_LAST_MINUTE("EVENT_ACTIVITY_NEW_LAST_MINUTE", HotelActivity.class, "/" + AppConfigProperties.HOST_SUFFIX + "#/app/activityList", "Last Minute Offer", null),
	EVENT_WALL_NEW_MESSAGE("EVENT_WALL_NEW_MESSAGE", HotelWallPost.class, "/" + AppConfigProperties.HOST_SUFFIX + "#/app/wall", "New Wall Message", AppConfigProperties.PUSH_ICON),
	EVENT_WALL_SEND_MESSAGE("EVENT_WALL_SEND_MESSAGE", HotelWallPost.class, null, null, null),
	EVENT_CHAT_NEW_MESSAGE("EVENT_CHAT_NEW_MESSAGE", ChatMessageView.class, "/" + AppConfigProperties.HOST_SUFFIX + "#/app/chat/", "New chat Message", AppConfigProperties.PUSH_ICON),
	EVENT_CHAT_SEND_MESSAGE("EVENT_CHAT_SEND_MESSAGE", ChatMessageView.class, null, null, null),
	
	EVENT_LOGO_CUSTOMER_CHANGE_MESSAGE("EVENT_LOGO_CUSTOMER_CHANGE_MESSAGE", CustomerRootEntity.class, null, null, null),
	EVENT_LOGO_ACTIVITY_CHANGE_MESSAGE("EVENT_LOGO_ACTIVITY_CHANGE_MESSAGE", HotelActivity.class, null, null, null),
	EVENT_LOGO_HOTEL_CHANGE_MESSAGE("EVENT_LOGO_HOTEL_CHANGE_MESSAGE", HotelRootEntity.class, null, null, null),
	
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

	@Override
	public IHotelEvent getEventCheckin() {
		return HotelEvent.EVENT_CHECKIN;
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
}
