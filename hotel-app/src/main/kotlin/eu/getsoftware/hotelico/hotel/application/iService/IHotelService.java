package eu.getsoftware.hotelico.hotel.application.iService;

import eu.getsoftware.hotelico.clients.infrastructure.exception.BasicHotelException;
import eu.getsoftware.hotelico.deal.domain.CustomerDeal;
import eu.getsoftware.hotelico.hotel.application.dto.HotelDTO;
import eu.getsoftware.hotelico.hotel.domain.HotelRootEntity;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.ResponseDTO;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

public interface IHotelService
{
    List<HotelDTO> getHotels();    
    
    List<HotelDTO> getHotelCities(long requesterId); 
    
    List<HotelDTO> getHotelsByCity(long customerId, String city) throws BasicHotelException;
    
    HotelDTO addHotel(HotelDTO hotelDto);    
    
    HotelDTO updateHotel(HotelDTO hotelDto);    
    
    Map<Long, String> getNotLoggedGuestPushIdsByHotel(HotelRootEntity hotelRootEntity);    
	
    Map<Long, String> getUnsubscribeGuestPushIds();

    HotelRootEntity getEntityById(long hotelId);
    
    HotelDTO getHotelById(long hotelId);
   
    ResponseDTO deleteHotel(long hotelId, long customerId);
   
    HotelDTO getHotelByCode(String hotelCode);
    
    String getGpsCity(Point2D.Double latLonPoint);

    double getDistanceKmToHotel(Point2D.Double from, HotelRootEntity to);
	
    void avoidDoubleInitId(List<CustomerDeal> resultList);
    
}
