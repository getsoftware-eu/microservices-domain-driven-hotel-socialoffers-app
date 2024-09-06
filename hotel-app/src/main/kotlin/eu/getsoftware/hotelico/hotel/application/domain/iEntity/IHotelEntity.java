package eu.getsoftware.hotelico.hotel.application.domain.iEntity;

import java.util.Set;

public interface IHotelEntity {
    //eugen: mappedBy entity!
    Set<IHotelWallpost> getHotelWallPosts();

}
