package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model;

public interface IHotelWallPost {
    
    boolean isActive();

    String getMessage();

    String getSpecialWallContent();

    long getInitId();
}
