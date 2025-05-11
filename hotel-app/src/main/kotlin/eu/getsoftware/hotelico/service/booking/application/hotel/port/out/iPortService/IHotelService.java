package eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.HotelDTO;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.exception.BasicHotelException;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.WallPostDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.ActivityAction;
import eu.getsoftware.hotelico.clients.common.utils.DealAction;
import eu.getsoftware.hotelico.clients.common.utils.ReorderAction;
import eu.getsoftware.hotelico.hotelapp.application.deal.domain.infrastructure.dto.CustomerDealDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.ResponseDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDbActivity;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.service.booking.adapter.out.viewEntity.model.CustomerDeal;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.customDomainModelImpl.HotelRootDomainEntity;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IHotelService<T extends HotelRootDomainEntity>
{
    List<HotelDTO> getHotels();

    List<HotelDTO> getHotelCities(CustomerDomainEntityId requesterId);

    List<HotelDTO> getHotelsByCity(CustomerDomainEntityId customerId, String city) throws BasicHotelException;

    HotelActivityDTO updateHotelActivity(HotelActivityDTO hotelActivityDto);

    Map<Long, String> getNotLoggedGuestPushIdsByHotel(T hotelRootEntity);

    Map<Long, String> getUnsubscribeGuestPushIds();

    Optional<T> getEntityById(HotelDomainEntityId hotelId);
    Optional<T> getEntityByDomainId(HotelDomainEntityId hotelId);

    HotelDTO getHotelById(HotelDomainEntityId hotelId);
    HotelDTO getHotelByDomainId(HotelDomainEntityId hotelId);

    HotelDTO getHotelByCode(String hotelCode);
    
    boolean existsHotelByCode(String hotelCode);

    HotelActivityDTO addActivityAction(CustomerDomainEntityId customerId, long activityId, ActivityAction action) throws Throwable;

    void reorderActivitiesInHotel(ReorderAction action, HotelDbActivity activity);

    String getGpsCity(Point2D.Double latLonPoint);

    double getDistanceKmToHotel(Point2D.Double from, T hotelRootEntity);

    WallPostDTO getWallPostById(WallPostDomainEntityId wallPostId);

    HotelDTO addHotel(HotelDTO hotelDto);

    Optional<CustomerDeal> getDealByIdOrInitId(HotelDomainEntityId id, long initId);

    WallPostDTO addWallPost(WallPostDTO wallPostDto) throws Throwable;

    HotelDTO updateHotel(HotelDTO hotelDto);

    CustomerDTO addGuestAction(CustomerDomainEntityId guestCustomerId, String action, Long hotelId, CustomerDTO guestDto);

    ResponseDTO deleteHotel(HotelDomainEntityId hotelId, CustomerDomainEntityId customerId);

    String getVirtualHotelCode();

    Optional<T> findByCurrentHotelAccessCodeAndActive(String hotelCode, boolean active);

    Optional<T> getOne(HotelDomainEntityId hotelId);
    
    HotelDTO convertToDTO(T hotelEntity);

    WallPostDTO addUpdateWallPost(WallPostDTO checkinNotificationWallDto) throws Throwable;

    int getCustomerDealCounter(CustomerDomainEntityId receiverId, int i);

    public WallPostDTO updateWallPost(WallPostDTO wallPostDto);
    
    public HotelActivityDTO addUpdateHotelActivity(CustomerDomainEntityId customerId, HotelActivityDTO hotelActivityDto) throws Throwable;

    Optional<T> getActivityByIdOrInitId(long activId, long activId1);

    List<WallPostDTO> getWallPostsByHotelId(HotelDomainEntityId hotelId);

    List<CustomerDTO> getWallPostParticipantsByHotelId(CustomerDomainEntityId requesterId, HotelDomainEntityId hotelId);

    List<CustomerDealDTO> getDealsByActivityOrHotelId(CustomerDomainEntityId customerId, HotelDomainEntityId hotelId, int activityId, boolean onlyRequesterDeals, boolean showClosed);

    CustomerDealDTO addDealAction(CustomerDomainEntityId customerId, long activityId, long dealId, DealAction dealAction, String tablePosition, double totalMoney);

    List<CustomerDealDTO> getDealsByActivityOrHotelId(CustomerDomainEntityId customerId, HotelDomainEntityId hotelId, long activityId, boolean onlyDealsOfRequester, boolean closed);

    ResponseDTO deleteDeal(CustomerDomainEntityId customerId, long activityId, int dealId);

    CustomerDealDTO addUpdateDeal(CustomerDomainEntityId guestCustomerId, long activityId, CustomerDealDTO dealDto);

//    HotelActivityDTO addUpdateHotelActivity(CustomerDomainEntityId senderId, HotelActivityDTO activityDto);

    List<HotelActivityDTO> getHotelActivitiesByHotelId(CustomerDomainEntityId customerId, HotelDomainEntityId hotelId);

    HotelActivityDTO addActivityAction(CustomerDomainEntityId customerId, long activityId, String action);

    List<HotelActivityDTO> getHotelActivitiesBySenderAndHotelId(CustomerDomainEntityId senderId, HotelDomainEntityId hotelId);

    HotelActivityDTO getHotelActivityById(CustomerDomainEntityId customerId, long activityId) throws Throwable;

    ResponseDTO deleteHotelActivity(CustomerDomainEntityId customerId, long activityId) throws Throwable;

    int getCustomerDealCounter(CustomerDomainEntityId customerId, CustomerDomainEntityId guestId);

    HotelActivityDTO convertActivityToDto(HotelDbActivity activity, CustomerDBEntity sender);
}
