package eu.getsoftware.hotelico.infrastructure.hotel.utils;//package de.hotelico.utils;
//
///**
// * <br/>
// * Created by e.fanshil
// * At 05.02.2016 14:29
// */
//public enum WallSpecialTypeKey
//{
//	DEFAULT(0, ""),
//	CHECKIN_NOTIFICATION_CUSTOMER_ID(1, "customerId");
//	
//	WallSpecialTypeKey(int value, String description)
//	{
//		this.description = description;
//		this.value = value;
//	}
//	
//	private final int value;
//	private final String description;
//	
//	// ########################################################################################
//	
//	public int getValue()
//	{
//		return value;
//	}
//	
//	public String getDescription()
//	{
//		return description;
//	}
//	
//	public boolean isOneOf(WallSpecialTypeKey... codes)
//	{
//		for (WallSpecialTypeKey nextCode : codes)
//		{
//			if (this == nextCode)
//			{
//				return true;
//			}
//		}
//		
//		return false;
//	}
//	
//	public static WallSpecialTypeKey parse(int value)
//	{
//		for (WallSpecialTypeKey key : WallSpecialTypeKey.values())
//		{
//			if (key.getValue() == value)
//			{
//				return key;
//			}
//		}
//		return WallSpecialTypeKey.DEFAULT;
//	}	
//	
//	public static WallSpecialTypeKey parse(String desription)
//	{
//		for (WallSpecialTypeKey key : WallSpecialTypeKey.values())
//		{
//			if (key.getDescription() == desription)
//			{
//				return key;
//			}
//		}
//		return WallSpecialTypeKey.DEFAULT;
//	}
//}
