package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.exception.BasicHotelException;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.IHotelActivity;
import eu.getsoftware.hotelico.hotelapp.application.deal.domain.ICustomerDeal;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.ResponseDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.IHotelRootEntity;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IHotelService
{
    List<HotelDTO> getHotels();

    List<HotelDTO> getHotelCities(long requesterId);

    List<HotelDTO> getHotelsByCity(long customerId, String city) throws BasicHotelException;

    Map<Long, String> getNotLoggedGuestPushIdsByHotel(IHotelRootEntity hotelRootEntity);

    Map<Long, String> getUnsubscribeGuestPushIds();

    Optional<? extends IHotelRootEntity> getEntityById(long hotelId);

    HotelDTO getHotelById(long hotelId);

    HotelDTO getHotelByCode(String hotelCode);
    
    boolean existsHotelByCode(String hotelCode);

    String getGpsCity(Point2D.Double latLonPoint);

    double getDistanceKmToHotel(Point2D.Double from, IHotelRootEntity to);

    void avoidDoubleInitId(List<ICustomerDeal> resultList);
    
    HotelDTO addHotel(HotelDTO hotelDto);    
    
    HotelDTO updateHotel(HotelDTO hotelDto);    
   
    ResponseDTO deleteHotel(long hotelId, long customerId);

    String getVirtualHotelCode();

    Optional<IHotelRootEntity> findByCurrentHotelAccessCodeAndActive(String hotelCode, boolean active);

    Optional<IHotelRootEntity> getOne(long hotelId);
    
    HotelDTO convertToDTO(IHotelRootEntity hotelEntity);

    void addUpdateWallPost(WallPostDTO checkinNotificationWallDto);

    int getCustomerDealCounter(long receiverId, int i);

    IHotelActivity getActivityByIdOrInitId(int activId, int activId1);
}
