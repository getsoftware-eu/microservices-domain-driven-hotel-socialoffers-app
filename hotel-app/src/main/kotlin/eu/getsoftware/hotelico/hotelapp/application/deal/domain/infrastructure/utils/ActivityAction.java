package eu.getsoftware.hotelico.hotelapp.application.deal.domain.infrastructure.utils;

import eu.getsoftware.hotelico.clients.common.utils.ObjectUtils;
import lombok.Getter;

/**
 * <br/>
 * Created by e.fanshil
 * At 19.10.2015 17:19
 */
@Getter
public enum ActivityAction
{
	LIKE("like"),
	DISLIKE("dislike"),
	SUBSCRIBE("subscribe" ),
	UNSUBSCRIBE("unsubscribe"),
	ORDER_STEP_TOP("order_step_top"),
	ORDER_STEP_BOTTOM("order_step_bottom");
	
	private String name;
	
	ActivityAction(String name)
	{
		this.name = name;
	}
	
	public static ActivityAction parseByName(String name)
	{
		if (!ObjectUtils.isEmpty(name))
		{
			for (ActivityAction nextEvent : ActivityAction.values())
			{
				if (nextEvent.name.equalsIgnoreCase(name))
				{
					return nextEvent;
				}
			}
		}
		return null;
	}
}
