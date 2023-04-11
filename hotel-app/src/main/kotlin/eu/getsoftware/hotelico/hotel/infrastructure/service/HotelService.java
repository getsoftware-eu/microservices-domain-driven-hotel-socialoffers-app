package eu.getsoftware.hotelico.hotel.infrastructure.service;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import eu.getsoftware.hotelico.checkin.domain.HotelActivity;
import eu.getsoftware.hotelico.clients.infrastructure.exception.HotelException;
import eu.getsoftware.hotelico.clients.infrastructure.utils.ReorderAction;
import eu.getsoftware.hotelico.customer.domain.CustomerRootEntity;
import eu.getsoftware.hotelico.customer.infrastructure.dto.CustomerDTO;
import eu.getsoftware.hotelico.deal.domain.CustomerDeal;
import eu.getsoftware.hotelico.deal.infrastructure.dto.CustomerDealDto;
import eu.getsoftware.hotelico.hotel.domain.HotelRootEntity;
import eu.getsoftware.hotelico.hotel.domain.HotelWallPost;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.HotelDTO;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.ResponseDTO;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.WallPostDTO;

public interface HotelService
{
    @Transactional
    List<HotelDTO> getHotels();    
    
    @Transactional
    List<HotelDTO> getHotelCities(long requesterId); 
    
    @Transactional
    List<HotelDTO> getHotelsByCity(long customerId, String city) throws HotelException;

    @Transactional HotelDTO addHotel(HotelDTO hotelDto);    
    
    @Transactional
    HotelActivityDTO addUpdateHotelActivity(long customerId, HotelActivityDTO hotelActivityDto);
    
    @Transactional
    WallPostDTO addWallPost(WallPostDTO wallPostDto);

    @Transactional HotelDTO updateHotel(HotelDTO hotelDto);    
    
    @Transactional
    HotelActivityDTO updateHotelActivity(HotelActivityDTO hotelActivityDto);

    @Transactional
    WallPostDTO updateWallPost(WallPostDTO wallPostDto);    
    
    @Transactional
    WallPostDTO addUpdateWallPost(WallPostDTO wallPostDto);
    
    @Transactional
    Map<Long,String> getNotLoggedGuestPushIdsByHotel(HotelRootEntity hotelRootEntity);    
	
    @Transactional
    Map<Long,String>  getUnsubscribeGuestPushIds();

    @Transactional
    HotelRootEntity getEntityById(long hotelId);

    @Transactional
    List<HotelActivityDTO> getHotelActivitiesByHotelId(long requesterId, long hotelId);

    @Transactional
    HotelActivityDTO convertActivityToDto(HotelActivity hotelActivity, CustomerRootEntity requester);

    @Transactional
    List<HotelActivityDTO> getHotelActivitiesBySenderAndHotelId(long senderId, long hotelId);
    
    @Transactional
    List<WallPostDTO> getWallPostsByHotelId(long hotelId);
    
    @Transactional HotelDTO getHotelById(long hotelId);    
    
    @Transactional
    HotelActivityDTO getHotelActivityById(long requesterId, long activityId);    
    
    @Transactional
    WallPostDTO getWallPostById(long wallPostId);

    @Transactional ResponseDTO deleteHotel(long hotelId, long customerId);
    
    @Transactional ResponseDTO deleteHotelActivity(long customerId, long activityId);
    
    @Transactional HotelDTO getHotelByCode(String hotelCode);
    
    @Transactional
    List<CustomerDTO> getWallPostParticipantsByHotelId(long requesterId, long hotelId);
	
	@Transactional
	void reorderActivitiesInHotel(ReorderAction action, HotelActivity activity);
	
	@Transactional
	HotelActivityDTO addActivityAction(long customerId, long activityId, String action);

    String getGpsCity(Point2D.Double latLonPoint);

    double getDistanceKmToHotel(Point2D.Double from, HotelRootEntity to);
	
	@Transactional
	List<CustomerDealDto> getDealsByActivityOrHotelId(long customerId, long hotelId, long activityId, boolean onlyDealsOfRequester, boolean closed);
	
	@Transactional ResponseDTO deleteDeal(long customerId, long activityId, long dealId);
	
	@Transactional
    CustomerDealDto addUpdateDeal(long guestCustomerId, long activityId, CustomerDealDto activityDealDto);
	
	@Transactional
    CustomerDealDto addDealAction(long guestCustomerId, long activityId, long givenId, String action, String tablePosition, double totalMoney);
	
	@Transactional
	int getCustomerDealCounter(long customerId, long guestId);
    
    @Transactional
    HotelActivity getActivityByIdOrInitId(long id, long initId);
   
    @Transactional
    CustomerDeal getDealByIdOrInitId(long id, long initId);
    
    WallPostDTO convertWallToDto(HotelWallPost wallPost);

    void avoidDoubleInitId(List<CustomerDeal> resultList);
    
    @Transactional CustomerDTO addGuestAction(long guestCustomerId, String action, Long hotelId, CustomerDTO guestDto);
}
