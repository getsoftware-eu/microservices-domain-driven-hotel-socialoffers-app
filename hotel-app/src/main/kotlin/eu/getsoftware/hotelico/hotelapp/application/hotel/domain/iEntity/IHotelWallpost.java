package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.iEntity;

public interface IHotelWallpost {
    
    boolean isActive();

    String getMessage();

    String getSpecialWallContent();

    long getInitId();
}
