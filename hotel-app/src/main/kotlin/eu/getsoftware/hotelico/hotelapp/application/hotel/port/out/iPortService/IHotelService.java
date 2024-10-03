package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.exception.BasicHotelException;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.IHotelActivity;
import eu.getsoftware.hotelico.hotelapp.application.deal.domain.ICustomerDeal;
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.dto.HotelResponseDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.ResponseDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.IHotelRootEntity;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

public interface IHotelService
{
    List<HotelResponseDTO> getHotels();

    List<HotelResponseDTO> getHotelCities(long requesterId);

    List<HotelResponseDTO> getHotelsByCity(long customerId, String city) throws BasicHotelException;

    Map<Long, String> getNotLoggedGuestPushIdsByHotel(IHotelRootEntity hotelRootEntity);

    Map<Long, String> getUnsubscribeGuestPushIds();

    IHotelRootEntity getEntityById(long hotelId);

    HotelResponseDTO getHotelById(long hotelId);

    HotelResponseDTO getHotelByCode(String hotelCode);

    String getGpsCity(Point2D.Double latLonPoint);

    double getDistanceKmToHotel(Point2D.Double from, IHotelRootEntity to);

    void avoidDoubleInitId(List<ICustomerDeal> resultList);
    
    HotelResponseDTO addHotel(HotelResponseDTO hotelDto);    
    
    HotelResponseDTO updateHotel(HotelResponseDTO hotelDto);    
   
    ResponseDTO deleteHotel(long hotelId, long customerId);

    String getVirtualHotelCode();

    IHotelRootEntity findByCurrentHotelAccessCodeAndActive(String hotelCode, boolean active);

    IHotelRootEntity getOne(long hotelId);

    void addUpdateWallPost(WallPostDTO checkinNotificationWallDto);

    int getCustomerDealCounter(long receiverId, int i);

    IHotelActivity getActivityByIdOrInitId(int activId, int activId1);
}
