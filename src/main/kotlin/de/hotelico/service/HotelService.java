package de.hotelico.service;

import de.hotelico.dto.CustomerDealDto;
import de.hotelico.dto.CustomerDTO;
import de.hotelico.dto.HotelActivityDto;
import de.hotelico.dto.HotelDTO;
import de.hotelico.dto.ResponseDTO;
import de.hotelico.dto.WallPostDto;
import de.hotelico.exception.HotelException;
import de.hotelico.model.Customer;
import de.hotelico.model.CustomerDeal;
import de.hotelico.model.Hotel;
import de.hotelico.model.HotelActivity;
import de.hotelico.model.HotelWallPost;
import de.hotelico.utils.ReorderAction;

import org.springframework.transaction.annotation.Transactional;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

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
    HotelActivityDto addUpdateHotelActivity(long customerId, HotelActivityDto hotelActivityDto);
    
    @Transactional
    WallPostDto addWallPost(WallPostDto wallPostDto);

    @Transactional HotelDTO updateHotel(HotelDTO hotelDto);    
    
    @Transactional
    HotelActivityDto updateHotelActivity(HotelActivityDto hotelActivityDto);
    

    @Transactional
    WallPostDto updateWallPost(WallPostDto wallPostDto);    
    
    @Transactional
    WallPostDto addUpdateWallPost(WallPostDto wallPostDto);
    
    @Transactional
    Map<Long,String> getNotLoggedGuestPushIdsByHotel(Hotel hotel);    
	
    @Transactional
    Map<Long,String>  getUnsubscribeGuestPushIds();

    @Transactional
    Hotel getEntityById(long hotelId);

    @Transactional
    List<HotelActivityDto> getHotelActivitiesByHotelId(long requesterId, long hotelId);

    @Transactional
    HotelActivityDto convertActivityToDto(HotelActivity hotelActivity, Customer requester);

    @Transactional
    List<HotelActivityDto> getHotelActivitiesBySenderAndHotelId(long senderId, long hotelId);
    
    @Transactional
    List<WallPostDto> getWallPostsByHotelId(long hotelId);
    
    @Transactional HotelDTO getHotelById(long hotelId);    
    
    @Transactional
    HotelActivityDto getHotelActivityById(long requesterId, long activityId);    
    
    @Transactional
    WallPostDto getWallPostById(long wallPostId);

    @Transactional ResponseDTO deleteHotel(long hotelId, long customerId);
    
    @Transactional ResponseDTO deleteHotelActivity(long customerId, long activityId);
    
    @Transactional HotelDTO getHotelByCode(String hotelCode);
    
    @Transactional
    List<CustomerDTO> getWallPostParticipantsByHotelId(long requesterId, long hotelId);
	
	@Transactional
	void reorderActivitiesInHotel(ReorderAction action, HotelActivity activity);
	
	@Transactional 
    HotelActivityDto addActivityAction(long customerId, long activityId, String action);

    //    @Transactional
//    void deleteWallPost(WallPostDto wallPostDto);
    String getGpsCity(Point2D.Double latLonPoint);

    double getDistanceKmToHotel(Point2D.Double from, Hotel to);
	
	@Transactional
	List<CustomerDealDto> getDealsByActivityOrHotelId(long customerId, long hotelId, long activityId, boolean onlyDealsOfRequester, boolean closed);
	
//	/**
//	 * get filterStatusList of closed or active deals
//	 * @param closed
//	 * @return
//	 */
//	List<String> getFilterStatusList(boolean closed);
	
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
    
    WallPostDto convertWallToDto(HotelWallPost wallPost);

    void avoidDoubleInitId(List<CustomerDeal> resultList);
    
    @Transactional CustomerDTO addGuestAction(long guestCustomerId, String action, Long hotelId, CustomerDTO guestDto);
}
