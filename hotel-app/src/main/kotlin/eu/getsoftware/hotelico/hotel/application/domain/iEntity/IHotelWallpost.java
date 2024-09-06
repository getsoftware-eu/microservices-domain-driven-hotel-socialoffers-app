package eu.getsoftware.hotelico.hotel.application.domain.iEntity;

public interface IHotelWallpost {
    
    boolean isActive();

    String getMessage();

    String getSpecialWallContent();

    long getInitId();
}
