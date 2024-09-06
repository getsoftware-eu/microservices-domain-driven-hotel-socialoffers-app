package eu.getsoftware.hotelico.deal.application.infrastructure.utils;

import eu.getsoftware.hotelico.common.utils.ObjectUtils;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * <br/>
 * Created by e.fanshil
 * At 19.10.2015 17:19
 */
@Getter
public enum DealStatus
{
	ACCEPTED("ACCEPTED", 1),
	EXECUTED("EXECUTED", 2),
	REJECTED("REJECTED", 3),
	CLOSED("CLOSED", 4);
	
	private String name;
	private int value;
	
	DealStatus(String name, int value)
	{
		this.name = name;
		this.value = value;
	}
	
	public static DealStatus parseByName(String name)
	{
		if (!ObjectUtils.isEmpty(name))
		{
			for (DealStatus nextEvent : DealStatus.values())
			{
				if (nextEvent.name.equalsIgnoreCase(name))
				{
					return nextEvent;
				}
			}
		}
		return null;
	}
	
	public static List<DealStatus> getFilterStatusList(boolean closed)
	{
		List<DealStatus> filterDealStatusList =  new ArrayList<>();
		
		if(closed)
		{
			filterDealStatusList.add(DealStatus.CLOSED);
		}
		else{
			filterDealStatusList.add(DealStatus.ACCEPTED);
			filterDealStatusList.add(DealStatus.EXECUTED);
		}
		return filterDealStatusList;
	}
	
	
	
 
}
