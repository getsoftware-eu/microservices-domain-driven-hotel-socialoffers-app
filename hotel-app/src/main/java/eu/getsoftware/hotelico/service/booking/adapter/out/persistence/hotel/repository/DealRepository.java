package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository;

import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.DealStatus;
import eu.getsoftware.hotelico.service.booking.adapter.out.viewEntity.model.CustomerDealDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DealRepository extends JpaRepository<CustomerDealDBEntity, Long> {

	public final static String FIND_BY_INIT_ID_QUERY = "SELECT c " +
			"FROM CustomerDeal c " +
			"WHERE c.initId = :initId " ;
	
	public final static String FIND_ACTIVE_BY_CUSTOMER_QUERY = "SELECT c " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
			"AND c.pk.customer.id = :customerId " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";	
	
	public final static String FIND_ACTIVE_BY_GUEST_CUSTOMER_QUERY = "SELECT c " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
			"AND c.guestCustomerId = :guestCustomerId " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";		
	
	public final static String COUNT_ACTIVE_BY_GUEST_OR_CUSTOMER_QUERY = "SELECT count(c) " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
			"AND (c.guestCustomerId = :guestCustomerId OR c.pk.customer.id = :customerId)" +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";	
	
	public final static String FIND_ANONYM_DEALS_FOR_GUEST_ID_QUERY = "SELECT c " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
			"AND c.guestCustomerId = :guestCustomerId " +
			"AND c.pk.customer.email = '[anonym]' ";			
	
	public final static String EXIST_ANONYM_BY_GUEST_CUSTOMER_QUERY = "SELECT count(c)>0 " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
			"AND c.guestCustomerId = :guestCustomerId " +
			"AND c.pk.customer.email = '[anonym]' ";		
	
	public final static String FIND_ACTIVE_BY_CUSTOMER_AND_ACTIVITY_QUERY = "SELECT c " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
			"AND c.pk.customer.id = :customerId " +
			"AND c.pk.activity.id = :activityId " +
			"AND c.status in (:statusList) " +
			"AND (:checkDateFrom is NULL OR c.validFrom <= :checkDateFrom) " +
			"AND (:checkDateTo is NULL OR c.validTo >= :checkDateTo) ";	
	
	public final static String FIND_ACTIVE_BY_GUEST_CUSTOMER_AND_ACTIVITY_QUERY = "SELECT c " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
			"AND c.guestCustomerId = :guestCustomerId " +
			"AND c.pk.activity.id = :activityId " +
			"AND c.status in (:statusList) " +
			"AND (:checkDateFrom is NULL OR c.validFrom <= :checkDateFrom) " +
			"AND (:checkDateTo is NULL OR c.validTo >= :checkDateTo) ";

	public final static String FIND_ACTIVE_BY_CUSTOMER_AND_HOTEL_QUERY = "SELECT c " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
			"AND c.pk.customer.id = :customerId " +
			"AND c.pk.activity.hotel.id = :hotelId " +
			"AND c.status in (:statusList) " +
			"AND (:checkDateFrom is NULL OR c.validFrom <= :checkDateFrom) " +
			"AND (:checkDateTo is NULL OR c.validTo >= :checkDateTo) ";
	
	public final static String FIND_ACTIVE_BY_GUEST_AND_HOTEL_QUERY = "SELECT c " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
			"AND c.guestCustomerId = :guestCustomerId " +
			"AND c.pk.activity.hotel.id = :hotelId " +
			"AND c.status in (:statusList) " +
			"AND (:checkDateFrom is NULL OR c.validFrom <= :checkDateFrom) " +
			"AND (:checkDateTo is NULL OR c.validTo >= :checkDateTo) ";		
	
	public final static String FIND_ACTIVE_DEALS_BY_ACTIVITY_QUERY = "SELECT c " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
//			"AND c.pk.customer.id = :customerId " +
			"AND c.pk.activity.id = :activityId " +
			"AND c.status in (:statusList) " +
			"AND (:checkDateFrom is NULL OR c.validFrom <= :checkDateFrom) " +
			"AND (:checkDateTo is NULL OR c.validTo >= :checkDateTo) ";
	
	public final static String FIND_ACTIVE_DEALS_BY_HOTEL_QUERY = "SELECT c " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
//			"AND c.pk.customer.id = :customerId " +
			"AND c.pk.activity.hotel.id = :hotelId " +
			"AND c.status in (:statusList) " +
			"AND (:checkDateFrom is NULL OR c.validFrom <= :checkDateFrom) " +
			"AND (:checkDateTo is NULL OR c.validTo >= :checkDateTo) ";	
	
	public final static String EXIST_ACTIVE_DEALS_FOR_CUSTOMER_BY_ACTIVITY_QUERY = "SELECT count(c)>0 " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
			"AND c.pk.activity.id = :activityId " +
			"AND c.pk.customer.id = :customerId " +
			"AND (" +
			  "( " +
//				" c.fullCheckin = true AND" +
				"c.validFrom <= :checkDate " +
				"AND c.validTo >= :checkDate " +
			  " ) " +
//				"OR ( c.pk.customer.activityStaff = TRUE ) " 
			" ) "
			;

	/**
	 * Find customer active checkIn.
	 */
	@Query(FIND_ACTIVE_BY_CUSTOMER_QUERY)
	public List<CustomerDealDBEntity> getActiveByCustomerId(@Param("customerId") CustomerDomainEntityId customerId, @Param("checkDate") LocalDate checkDate);		
		
	@Query(FIND_ACTIVE_BY_GUEST_CUSTOMER_QUERY)
	public List<CustomerDealDBEntity> getActiveByGuestId(@Param("guestCustomerId") CustomerDomainEntityId guestCustomerId, @Param("checkDate") LocalDate checkDate);
	
	@Query(COUNT_ACTIVE_BY_GUEST_OR_CUSTOMER_QUERY)
	public Integer countActiveDealsByCustomerOrGuest(@Param("customerId") CustomerDomainEntityId customerId, @Param("guestCustomerId") CustomerDomainEntityId guestCustomerId, @Param("checkDate") LocalDate checkDate);
	
	@Query(FIND_ANONYM_DEALS_FOR_GUEST_ID_QUERY)
	public List<CustomerDealDBEntity> getAnonymDealsByGuestId(@Param("guestCustomerId") CustomerDomainEntityId guestCustomerId);	
	
	
	@Query(EXIST_ANONYM_BY_GUEST_CUSTOMER_QUERY)
	public boolean existAnonymDelasByGuestId(@Param("guestCustomerId") CustomerDomainEntityId guestCustomerId);		
	
	/**
	 * Find Deals by customer and activity
	 */
	@Query(FIND_ACTIVE_BY_CUSTOMER_AND_ACTIVITY_QUERY)
	public List<CustomerDealDBEntity> getActiveByCustomerAndActivityId(@Param("customerId") CustomerDomainEntityId customerId, @Param("activityId") Long activityId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") LocalDate checkDateFrom, @Param("checkDateTo") LocalDate checkDateTo);	
		
	/**
	 * Find Deals by guestId and activity
	 */
	@Query(FIND_ACTIVE_BY_GUEST_CUSTOMER_AND_ACTIVITY_QUERY)
	public List<CustomerDealDBEntity> getActiveByGuestAndActivityId(@Param("guestCustomerId") CustomerDomainEntityId guestCustomerId, @Param("activityId") Long activityId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") LocalDate checkDateFrom, @Param("checkDateTo") LocalDate checkDateTo);	
			
	/**
	 * Find Deals by customer and whole Hotel
	 */
	@Query(FIND_ACTIVE_BY_CUSTOMER_AND_HOTEL_QUERY)
	public List<CustomerDealDBEntity> getActiveByCustomerAndHotelId(@Param("customerId") CustomerDomainEntityId customerId, @Param("hotelDomainId") HotelDomainEntityId hotelId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") LocalDate checkDateFrom, @Param("checkDateTo") LocalDate checkDateTo);	
		
	/**
	 * Find Deals by customer and whole Hotel
	 */
	@Query(FIND_ACTIVE_BY_GUEST_AND_HOTEL_QUERY)
	public List<CustomerDealDBEntity> getActiveByGuestAndHotelId(@Param("guestCustomerId") CustomerDomainEntityId guestCustomerId, @Param("hotelDomainId") HotelDomainEntityId hotelDomainId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") LocalDate checkDateFrom, @Param("checkDateTo") LocalDate checkDateTo);	
		
	@Query(EXIST_ACTIVE_DEALS_FOR_CUSTOMER_BY_ACTIVITY_QUERY)
	public boolean countActiveDealsForActivity(@Param("customerId") CustomerDomainEntityId customerId, @Param("activityId") Long activityId, @Param("checkDate") LocalDate checkDate);

	@Query(FIND_ACTIVE_DEALS_BY_ACTIVITY_QUERY)
	public List<CustomerDealDBEntity> getActivityDeals(@Param("activityId") Long activityId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") LocalDate checkDateFrom, @Param("checkDateTo") LocalDate checkDateTo);
	
	@Query(FIND_ACTIVE_DEALS_BY_HOTEL_QUERY)
	public List<CustomerDealDBEntity> getHotelDeals(@Param("hotelId") HotelDomainEntityId hotelId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") LocalDate checkDateFrom, @Param("checkDateTo") LocalDate checkDateTo);	
	
	@Query(FIND_BY_INIT_ID_QUERY)
	List<CustomerDealDBEntity> getByInitId(@Param("initId") Long initId);

    Optional<CustomerDealDBEntity> findByHotelDomainId(HotelDomainEntityId id);

    Optional<CustomerDealDBEntity> findByHotelId(HotelDomainEntityId id);
}
