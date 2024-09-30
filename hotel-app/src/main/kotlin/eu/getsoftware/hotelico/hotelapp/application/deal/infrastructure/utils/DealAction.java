package eu.getsoftware.hotelico.hotelapp.application.deal.infrastructure.utils;

import eu.getsoftware.hotelico.clients.common.utils.ObjectUtils;
import lombok.Getter;

/**
 * <br/>
 * Created by e.fanshil
 * At 19.10.2015 17:19
 */
@Getter
public enum DealAction
{
	NEW_DEAL("newDeal"),
	ACCEPT_DEAL("acceptDeal"),
	EXECUTE("execute" ),
	CLOSE("close"),
	EDIT_DEAL("editDeal"),
	REJECT_DEAL("rejectDeal");
	
	private String name;
	
	DealAction(String name)
	{
		this.name = name;
	}
	
	public static DealAction parseByName(String name)
	{
		if (!ObjectUtils.isEmpty(name))
		{
			for (DealAction nextEvent : DealAction.values())
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
