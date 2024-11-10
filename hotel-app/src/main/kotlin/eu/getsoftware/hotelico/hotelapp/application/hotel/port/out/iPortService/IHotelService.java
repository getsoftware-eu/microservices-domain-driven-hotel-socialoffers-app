package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.exception.BasicHotelException;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.ReorderAction;
import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.model.HotelDbActivity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.viewEntity.model.CustomerDeal;
import eu.getsoftware.hotelico.hotelapp.application.deal.domain.infrastructure.dto.CustomerDealDTO;
import eu.getsoftware.hotelico.hotelapp.application.deal.domain.infrastructure.utils.ActivityAction;
import eu.getsoftware.hotelico.hotelapp.application.deal.domain.infrastructure.utils.DealAction;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.ResponseDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.IHotelRootEntity;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IHotelService<T extends IHotelRootEntity>
{
    List<HotelDTO> getHotels();

    List<HotelDTO> getHotelCities(long requesterId);

    List<HotelDTO> getHotelsByCity(long customerId, String city) throws BasicHotelException;

    HotelActivityDTO updateHotelActivity(HotelActivityDTO hotelActivityDto);

    Map<Long, String> getNotLoggedGuestPushIdsByHotel(T hotelRootEntity);

    Map<Long, String> getUnsubscribeGuestPushIds();

    Optional<T> getEntityById(long hotelId);
    Optional<T> getEntityByDomainId(HotelDomainEntityId hotelId);

    HotelDTO getHotelById(HotelDomainEntityId hotelId);
    HotelDTO getHotelByDomainId(HotelDomainEntityId hotelId);

    HotelDTO getHotelByCode(String hotelCode);
    
    boolean existsHotelByCode(String hotelCode);

    HotelActivityDTO addActivityAction(long customerId, long activityId, ActivityAction action);

    void reorderActivitiesInHotel(ReorderAction action, HotelDbActivity activity);

    String getGpsCity(Point2D.Double latLonPoint);

    double getDistanceKmToHotel(Point2D.Double from, T hotelRootEntity);

    WallPostDTO getWallPostById(long wallPostId);

    HotelDTO addHotel(HotelDTO hotelDto);

    Optional<CustomerDeal> getDealByIdOrInitId(long id, long initId);

    WallPostDTO addWallPost(WallPostDTO wallPostDto) throws Throwable;

    HotelDTO updateHotel(HotelDTO hotelDto);

    CustomerDTO addGuestAction(long guestCustomerId, String action, Long hotelId, CustomerDTO guestDto);

    ResponseDTO deleteHotel(long hotelId, long customerId);

    String getVirtualHotelCode();

    Optional<T> findByCurrentHotelAccessCodeAndActive(String hotelCode, boolean active);

    Optional<T> getOne(long hotelId);
    
    HotelDTO convertToDTO(T hotelEntity);

    WallPostDTO addUpdateWallPost(WallPostDTO checkinNotificationWallDto) throws Throwable;

    int getCustomerDealCounter(long receiverId, int i);

    public WallPostDTO updateWallPost(WallPostDTO wallPostDto);
    
    public HotelActivityDTO addUpdateHotelActivity(long customerId, HotelActivityDTO hotelActivityDto) throws Throwable;

    Optional<T> getActivityByIdOrInitId(long activId, long activId1);

    List<WallPostDTO> getWallPostsByHotelId(long hotelId);

    List<CustomerDTO> getWallPostParticipantsByHotelId(long requesterId, long hotelId);

    List<CustomerDealDTO> getDealsByActivityOrHotelId(long customerId, long hotelId, int activityId, boolean onlyRequesterDeals, boolean showClosed);

    CustomerDealDTO addDealAction(long customerId, long activityId, long dealId, DealAction dealAction, String tablePosition, double totalMoney);

    List<CustomerDealDTO> getDealsByActivityOrHotelId(long customerId, long hotelId, long activityId, boolean onlyDealsOfRequester, boolean closed);

    ResponseDTO deleteDeal(long customerId, long activityId, int dealId);

    CustomerDealDTO addUpdateDeal(long guestCustomerId, long activityId, CustomerDealDTO dealDto);

    HotelActivityDTO addUpdateHotelActivity(Long senderId, HotelActivityDTO activityDto);

    List<HotelActivityDTO> getHotelActivitiesByHotelId(long customerId, long hotelId);

    HotelActivityDTO addActivityAction(long customerId, long activityId, String action);


    List<HotelActivityDTO> getHotelActivitiesBySenderAndHotelId(long senderId, long hotelId);

    HotelActivityDTO getHotelActivityById(long customerId, long activityId);

    ResponseDTO deleteHotelActivity(long customerId, long activityId) throws Throwable;

    int getCustomerDealCounter(long customerId, long guestId);

    HotelActivityDTO convertActivityToDto(HotelDbActivity activity, CustomerDBEntity sender);
}
