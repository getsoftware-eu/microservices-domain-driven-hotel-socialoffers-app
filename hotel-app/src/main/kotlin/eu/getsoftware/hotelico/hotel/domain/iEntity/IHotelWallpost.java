package eu.getsoftware.hotelico.hotel.domain.iEntity;

public interface IHotelWallpost {
    
    boolean isActive();

    String getMessage();

    String getSpecialWallContent();

    long getInitId();
}
