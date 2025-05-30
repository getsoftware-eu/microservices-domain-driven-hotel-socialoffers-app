package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model;

import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.innerevents.InnerDomainEvent;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.clients.common.utils.ObjectUtils;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDbActivity;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.service.booking.adapter.out.viewEntity.model.ChatMessageView;
import eu.getsoftware.hotelico.service.booking.adapter.out.viewEntity.model.CustomerDeal;

/**
 * <br/>
 * Created by e.fanshil
 * At 19.10.2015 17:19
 */
public enum InnerHotelEvent implements InnerDomainEvent
{
	//TODO: eu ask: how to use event in UseCase Layer, if there are class implementations???
	
	EVENT_ALL_INFO("EVENT_ALL_INFO", null, null, null, null),
	EVENT_ADDRESS_UPDATED("EVENT_ADDRESS_UPDATED", HotelDBEntity.class, null, null, null),
	EVENT_CHECKIN("EVENT_CHECKIN", HotelDBEntity.class, null, null, null),
	EVENT_REGISTER("EVENT_REGISTER", CustomerDBEntity.class, null, null, null),
	EVENT_CHECKOUT("EVENT_CHECKOUT", HotelDBEntity.class, null, null, null),
	EVENT_LOGIN("EVENT_LOGIN", CustomerDBEntity.class, null, null, null),
	EVENT_DEAL_NEW_UPDATE("EVENT_DEAL_NEW_UPDATE", CustomerDeal.class, null, null, null),
	EVENT_MENU_NEW_UPDATE("EVENT_MENU_NEW_UPDATE", CustomerDeal.class, null, null, null),
	EVENT_ACTIVITY_NEW_LAST_MINUTE("EVENT_ACTIVITY_NEW_LAST_MINUTE", HotelDbActivity.class, "/" + AppConfigProperties.HOST_SUFFIX + "#/app/activityList", "Last Minute Offer", null),
	EVENT_WALL_NEW_MESSAGE("EVENT_WALL_NEW_MESSAGE", HotelWallPost.class, "/" + AppConfigProperties.HOST_SUFFIX + "#/app/wall", "New Wall Message", AppConfigProperties.PUSH_ICON),
	EVENT_WALL_SEND_MESSAGE("EVENT_WALL_SEND_MESSAGE", HotelWallPost.class, null, null, null),
	EVENT_CHAT_NEW_MESSAGE("EVENT_CHAT_NEW_MESSAGE", ChatMessageView.class, "/" + AppConfigProperties.HOST_SUFFIX + "#/app/chat/", "New chat Message", AppConfigProperties.PUSH_ICON),
	EVENT_CHAT_SEND_MESSAGE("EVENT_CHAT_SEND_MESSAGE", ChatMessageView.class, null, null, null),
	
	EVENT_LOGO_CUSTOMER_CHANGE_MESSAGE("EVENT_LOGO_CUSTOMER_CHANGE_MESSAGE", CustomerDBEntity.class, null, null, null),
	EVENT_LOGO_ACTIVITY_CHANGE_MESSAGE("EVENT_LOGO_ACTIVITY_CHANGE_MESSAGE", HotelDbActivity.class, null, null, null),
	EVENT_LOGO_HOTEL_CHANGE_MESSAGE("EVENT_LOGO_HOTEL_CHANGE_MESSAGE", HotelDBEntity.class, null, null, null),
	
	EVENT_PING("EVENT_PING", null, null, null, null),
	EVENT_ONLINE_CUSTOMERS("EVENT_ONLINE_CUSTOMERS", null, null, null, null),
	
	EVENT_REMOVE_ACTIVITY("EVENT_REMOVE_ACTIVITY", HotelDbActivity.class, null, null, null);
	
	private String value;
	private String pushUrl;
	private String pushTitle;
	private String pushIcon;
	private Class entityClass;
	
	private InnerHotelEvent(String value, Class entityClass, String pushUrl, String pushTitle, String pushIcon)
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
	public InnerDomainEvent getEventCheckin() {
		return InnerHotelEvent.EVENT_CHECKIN;
	}

	@Override
	public InnerDomainEvent getEventCheckout() {
		return null;
	}

	public static InnerHotelEvent parseByValue(String value)
	{
		if (!ObjectUtils.isEmpty(value))
		{
			for (InnerHotelEvent nextEvent : InnerHotelEvent.values())
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
