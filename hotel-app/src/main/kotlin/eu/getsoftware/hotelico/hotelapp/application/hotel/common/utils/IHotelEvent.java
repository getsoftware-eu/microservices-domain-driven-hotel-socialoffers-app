package eu.getsoftware.hotelico.hotelapp.application.hotel.common.utils;

public interface IHotelEvent {
    
    String getValue();

    String getPushUrl();

    String getPushTitle();

    String getPushIcon();

    Class getEntityClass();

    String getEntityString();

//    HotelEvent parseByValue(String value);
    
    IHotelEvent getEventCheckin();
    IHotelEvent getEventCheckout();
}
