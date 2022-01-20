package de.hotelico.repository;

import java.util.Date;
import java.util.List;

import de.hotelico.utils.DealStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import de.hotelico.model.CustomerDeal;

public interface DealRepository extends JpaRepository<CustomerDeal, Long> {

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
			"AND c.pk.customer.email = '[anonym]' " 
//			"AND c.validFrom <= :checkDate " +
//			"AND c.validTo >= :checkDate "
			;			
	
	public final static String EXIST_ANONYM_BY_GUEST_CUSTOMER_QUERY = "SELECT count(c)>0 " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
			"AND c.guestCustomerId = :guestCustomerId " +
			"AND c.pk.customer.email = '[anonym]' " 
//			"AND c.validFrom <= :checkDate " +
//			"AND c.validTo >= :checkDate "
			;		
	
	public final static String FIND_ACTIVE_BY_CUSTOMER_AND_ACTIVITY_QUERY = "SELECT c " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
			"AND c.pk.customer.id = :customerId " +
			"AND c.pk.activity.id = :activityId " +
			"AND c.status in (:statusList) " +
			"AND (:checkDateFrom is NULL OR c.validFrom <= :checkDateFrom) " +
			"AND (:checkDateTo is NULL OR c.validTo >= :checkDateTo) ";	
	
//	public final static String FIND_CLOSED_BY_CUSTOMER_AND_ACTIVITY_QUERY = "SELECT c " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
//			"AND c.pk.customer.id = :customerId " +
//			"AND c.pk.activity.id = :activityId " +
//			"AND (c.status = 'closed') " ;
////			"AND c.validFrom <= :checkDate " +
////			"AND c.validTo >= :checkDate ";	
	
	public final static String FIND_ACTIVE_BY_GUEST_CUSTOMER_AND_ACTIVITY_QUERY = "SELECT c " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
			"AND c.guestCustomerId = :guestCustomerId " +
			"AND c.pk.activity.id = :activityId " +
			"AND c.status in (:statusList) " +
			"AND (:checkDateFrom is NULL OR c.validFrom <= :checkDateFrom) " +
			"AND (:checkDateTo is NULL OR c.validTo >= :checkDateTo) ";

	//	public final static String FIND_CLOSED_BY_GUEST_CUSTOMER_AND_ACTIVITY_QUERY = "SELECT c " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
//			"AND c.guestCustomerId = :guestCustomerId " +
//			"AND c.pk.activity.id = :activityId " +
//			"AND (c.status = 'closed') " 
////			"AND c.validFrom <= :checkDate " +
////			"AND c.validTo >= :checkDate "
//			;		
	
	public final static String FIND_ACTIVE_BY_CUSTOMER_AND_HOTEL_QUERY = "SELECT c " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
			"AND c.pk.customer.id = :customerId " +
			"AND c.pk.activity.hotel.id = :hotelId " +
			"AND c.status in (:statusList) " +
			"AND (:checkDateFrom is NULL OR c.validFrom <= :checkDateFrom) " +
			"AND (:checkDateTo is NULL OR c.validTo >= :checkDateTo) ";
	
//	public final static String FIND_CLOSED_BY_CUSTOMER_AND_HOTEL_QUERY = "SELECT c " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
//			"AND c.pk.customer.id = :customerId " +
//			"AND c.pk.activity.hotel.id = :hotelId " +
//			"AND (c.status = 'closed') " ;
////			"AND c.validFrom <= :checkDate " +
////			"AND c.validTo >= :checkDate ";
	
	public final static String FIND_ACTIVE_BY_GUEST_AND_HOTEL_QUERY = "SELECT c " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
			"AND c.guestCustomerId = :guestCustomerId " +
			"AND c.pk.activity.hotel.id = :hotelId " +
			"AND c.status in (:statusList) " +
			"AND (:checkDateFrom is NULL OR c.validFrom <= :checkDateFrom) " +
			"AND (:checkDateTo is NULL OR c.validTo >= :checkDateTo) ";		
	
//	public final static String FIND_CLOSED_BY_GUEST_AND_HOTEL_QUERY = "SELECT c " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
//			"AND c.guestCustomerId = :guestCustomerId " +
//			"AND c.pk.activity.hotel.id = :hotelId " +
//			"AND (c.status = closed) " ;
////			"AND c.validFrom <= :checkDate " +
////			"AND c.validTo >= :checkDate ";		
	
	public final static String FIND_ACTIVE_DEALS_BY_ACTIVITY_QUERY = "SELECT c " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
//			"AND c.pk.customer.id = :customerId " +
			"AND c.pk.activity.id = :activityId " +
			"AND c.status in (:statusList) " +
			"AND (:checkDateFrom is NULL OR c.validFrom <= :checkDateFrom) " +
			"AND (:checkDateTo is NULL OR c.validTo >= :checkDateTo) ";
	
	//	public final static String FIND_CLOSED_DEALS_BY_ACTIVITY_QUERY = "SELECT c " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
////			"AND c.pk.customer.id = :customerId " +
//			"AND c.pk.activity.id = :activityId " +
//			"AND (c.status = 'closed') ";
////			"AND c.validFrom <= :checkDate " +
////			"AND c.validTo >= :checkDate ";	
	
	public final static String FIND_ACTIVE_DEALS_BY_HOTEL_QUERY = "SELECT c " +
			"FROM CustomerDeal c " +
			"WHERE c.active = true " +
//			"AND c.pk.customer.id = :customerId " +
			"AND c.pk.activity.hotel.id = :hotelId " +
			"AND c.status in (:statusList) " +
			"AND (:checkDateFrom is NULL OR c.validFrom <= :checkDateFrom) " +
			"AND (:checkDateTo is NULL OR c.validTo >= :checkDateTo) ";	
	
//	public final static String FIND_CLOSED_DEALS_BY_HOTEL_QUERY = "SELECT c " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
////			"AND c.pk.customer.id = :customerId " +
//			"AND c.pk.activity.hotel.id = :hotelId " +
//			"AND (c.status = 'closed') ";
////			"AND c.validFrom <= :checkDate " +
////			"AND c.validTo >= :checkDate ";	
	
//	public final static String FIND_DEALS_BY_CUSTOMER_QUERY = "SELECT c.pk.activity.id " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
//			"AND c.pk.customer.id = :customerId " +
//			"AND ( " +
//			  " ( " +
//				  " c.validFrom <= :checkDate " +
//				  " AND c.validTo >= :checkDate " +
//			  " ) " +
//			  "OR (c.pk.customer.hotelStaff = TRUE)" +
//			")";	
	
//	public final static String FIND_STAFF_BY_DEAL_QUERY = "SELECT c.pk.customer " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
//			"AND c.pk.activity.id = :activityId " +
//			"AND c.pk.customer.activityStaff = true ";	
//	
//	public final static String FIND_STAFF_IDS_BY_DEAL_QUERY = "SELECT c.pk.customer.id " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
//			"AND c.pk.activity.id = :activityId " +
//			"AND c.pk.customer.activityStaff = true ";	
//	
//	public final static String FIND_CUSTOMERS_BY_activity_CITY_QUERY = "SELECT c.pk.customer " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
//			"AND c.pk.customer.active = true " +
//			"AND (" 
//			+ "c.pk.activity.city = :activityCity " +
//			"  OR c.pk.customer.city = :activityCity " 
//			+")  " +
//			"AND c.validFrom <= :checkDate " +
//			"AND c.validTo >= :checkDate ";	
//	
//	
//	public final static String FIND_CHECKIN_UNIQUE_CITIES_QUERY = "SELECT DISTINCT c.pk.activity.city " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
//			"AND c.pk.customer.active = true " +
//			"AND c.pk.activity.active = true " +
//			"AND c.validFrom <= :checkDate " +
//			"AND c.validTo >= :checkDate ";
//	
//	public final static String FIND_ACTIVE_BY_activity_QUERY = "SELECT c " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
//			"AND c.pk.activity.id = :activityId " +
//			"AND c.validFrom <= :checkDate " +
//			"AND c.validTo >= :checkDate ";	
//	
//	public final static String FIND_ACTIVE_CUSTOMERS_BY_activity_QUERY = "SELECT c.pk.customer " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
//			"AND c.pk.activity.id = :activityId " +	
//			"AND c.pk.customer.active = true " +
//			"AND c.validFrom <= :checkDate " +
//			"AND c.validTo >= :checkDate ";	
//	
//	public final static String FIND_ACTIVE_FULL_CHECKIN_BY_activity_QUERY = "SELECT c " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
//			"AND c.fullCheckin = true " +
//			"AND c.pk.activity.id = :activityId " +
//			"AND c.validFrom <= :checkDate " +
//			"AND c.validTo >= :checkDate ";	
//	
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
//	
//	public final static String COUNT_ACTIVE_BY_activity_QUERY = "SELECT count(c) " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
//			"AND c.pk.activity.id = :activityId "+
//			"AND c.validFrom <= :checkDate " +
//			"AND c.validTo >= :checkDate ";
//	
//	public final static String COUNT_NOT_STAFF_ACTIVE_BY_activity_QUERY = "SELECT count(c) " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
////			"AND c.fullCheckin = true " +
//			"AND c.pk.customer.activityStaff = false " +
//			"AND c.pk.activity.id = :activityId " +
//			"AND c.validFrom <= :checkDate " +
//			"AND c.validTo >= :checkDate ";
//	
//	public final static String COUNT_NOT_STAFF_FULL_CHECKIN_ACTIVE_BY_activity_QUERY = "SELECT count(c) " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
//			"AND c.fullCheckin = true " +
//			"AND c.pk.customer.activityStaff = false " +
//			"AND c.pk.activity.id = :activityId " +
//			"AND c.validFrom <= :checkDate " +
//			"AND c.validTo >= :checkDate ";
	
	
//	public final static String COUNT_ONLINE_BY_activity_QUERY = "SELECT count(c.) " +
//			"FROM CustomerDeal c " +
//			"WHERE c.active = true " +
//			"AND c.pk.activity.id = :activityId";

	/**
	 * Find customer active checkIn.
	 */
	@Query(FIND_ACTIVE_BY_CUSTOMER_QUERY)
	public List<CustomerDeal> getActiveByCustomerId(@Param("customerId") Long customerId, @Param("checkDate") Date checkDate);		
		
	@Query(FIND_ACTIVE_BY_GUEST_CUSTOMER_QUERY)
	public List<CustomerDeal> getActiveByGuestId(@Param("guestCustomerId") Long guestCustomerId, @Param("checkDate") Date checkDate);
	
	@Query(COUNT_ACTIVE_BY_GUEST_OR_CUSTOMER_QUERY)
	public Integer countActiveDealsByCustomerOrGuest(@Param("customerId") Long customerId, @Param("guestCustomerId") Long guestCustomerId, @Param("checkDate") Date checkDate);
	
	@Query(FIND_ANONYM_DEALS_FOR_GUEST_ID_QUERY)
	public List<CustomerDeal> getAnonymDealsByGuestId(@Param("guestCustomerId") Long guestCustomerId);	
	
	
	@Query(EXIST_ANONYM_BY_GUEST_CUSTOMER_QUERY)
	public boolean existAnonymDelasByGuestId(@Param("guestCustomerId") Long guestCustomerId);		
	
	/**
	 * Find Deals by customer and activity
	 */
	@Query(FIND_ACTIVE_BY_CUSTOMER_AND_ACTIVITY_QUERY)
	public List<CustomerDeal> getActiveByCustomerAndActivityId(@Param("customerId") Long customerId, @Param("activityId") Long activityId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") Date checkDateFrom, @Param("checkDateTo") Date checkDateTo);	
		
//	/**
//	 * Find Deals by customer and activity
//	 */
//	@Query(FIND_CLOSED_BY_CUSTOMER_AND_ACTIVITY_QUERY)
//	public List<CustomerDeal> getClosedByCustomerAndActivityId(@Param("customerId") Long customerId, @Param("activityId") Long activityId);	
//	
	/**
	 * Find Deals by guestId and activity
	 */
	@Query(FIND_ACTIVE_BY_GUEST_CUSTOMER_AND_ACTIVITY_QUERY)
	public List<CustomerDeal> getActiveByGuestAndActivityId(@Param("guestCustomerId") Long guestCustomerId, @Param("activityId") Long activityId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") Date checkDateFrom, @Param("checkDateTo") Date checkDateTo);	
			
//	/**
//	 * Find closed Deals by guestId and activity
//	 */
//	@Query(FIND_CLOSED_BY_GUEST_CUSTOMER_AND_ACTIVITY_QUERY)
//	public List<CustomerDeal> getClosedByGuestAndActivityId(@Param("guestCustomerId") Long guestCustomerId, @Param("activityId") Long activityId);	
//		
	/**
	 * Find Deals by customer and whole Hotel
	 */
	@Query(FIND_ACTIVE_BY_CUSTOMER_AND_HOTEL_QUERY)
	public List<CustomerDeal> getActiveByCustomerAndHotelId(@Param("customerId") Long customerId, @Param("activityId") Long activityId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") Date checkDateFrom, @Param("checkDateTo") Date checkDateTo);	
		
//	/**
//	 * Find Deals by customer and whole Hotel
//	 */
//	@Query(FIND_CLOSED_BY_CUSTOMER_AND_HOTEL_QUERY)
//	public List<CustomerDeal> getClosedByCustomerAndHotelId(@Param("customerId") Long customerId, @Param("activityId") Long activityId);	
//		
	/**
	 * Find Deals by customer and whole Hotel
	 */
	@Query(FIND_ACTIVE_BY_GUEST_AND_HOTEL_QUERY)
	public List<CustomerDeal> getActiveByGuestAndHotelId(@Param("guestCustomerId") Long guestCustomerId, @Param("activityId") Long activityId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") Date checkDateFrom, @Param("checkDateTo") Date checkDateTo);	
		
//	/**
//	 * Find Deals by customer and whole Hotel
//	 */
//	@Query(FIND_CLOSED_BY_GUEST_AND_HOTEL_QUERY)
//	public List<CustomerDeal> getClosedByGuestAndHotelId(@Param("guestCustomerId") Long guestCustomerId, @Param("activityId") Long activityId);	
	
//	/**
//	 * Find customer active checkIn.
//	 */
//	@Query(COUNT_ACTIVE_BY_activity_QUERY)
//	public Integer getActiveCountByactivityId(@Param("activityId") Long activityId, @Param("checkDate") Date checkDate);
//
//	/**
//	 * Find NOT STAFF active Checkin counter
//	 * @param activityId
//	 * @return
//	 */
//	@Query(COUNT_NOT_STAFF_ACTIVE_BY_activity_QUERY)
//	public Integer getActiveCountExcludingStaffByactivityId(@Param("activityId") Long activityId, @Param("checkDate") Date checkDate);	
//	
//	@Query(COUNT_NOT_STAFF_FULL_CHECKIN_ACTIVE_BY_activity_QUERY)
//	public Integer getFullCheckinCountExcludingStaffByactivityId(@Param("activityId") Long activityId, @Param("checkDate") Date checkDate);
	
//	/**
//	 * Find customer active checkIn.
//	 */
//	@Query(COUNT_ONLINE_BY_activity_QUERY)
//	public Integer getOnlineCountByactivityId(@Param("activityId") Long activityId);	
	
	/**
	 * Find customers active checkIn by activity.
	 */
//	@Query(FIND_ACTIVE_BY_activity_QUERY)
//	public List<CustomerDeal> getActiveByactivityId(@Param("activityId") Long activityId, @Param("checkDate") Date checkDate);	
//	
//	@Query(FIND_ACTIVE_CUSTOMERS_BY_activity_QUERY)
//	public List<Customer> getActiveCustomersByactivityId(@Param("activityId") Long activityId, @Param("checkDate") Date checkDate);	
//	
//	@Query(FIND_ACTIVE_FULL_CHECKIN_BY_activity_QUERY)
//	public List<CustomerDeal> getActiveFullCheckinByactivityId(@Param("activityId") Long activityId, @Param("checkDate") Date checkDate);	
//	
	@Query(EXIST_ACTIVE_DEALS_FOR_CUSTOMER_BY_ACTIVITY_QUERY)
	public boolean countActiveDealsForActivity(@Param("customerId") Long customerId, @Param("activityId") Long activityId, @Param("checkDate") Date checkDate);

	@Query(FIND_ACTIVE_DEALS_BY_ACTIVITY_QUERY)
	public List<CustomerDeal> getActivityDeals(@Param("activityId") Long activityId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") Date checkDateFrom, @Param("checkDateTo") Date checkDateTo);
	
//	@Query(FIND_CLOSED_DEALS_BY_ACTIVITY_QUERY)
//	public List<CustomerDeal> getClosedActivityDeals(@Param("activityId") Long activityId);
//	
	@Query(FIND_ACTIVE_DEALS_BY_HOTEL_QUERY)
	public List<CustomerDeal> getHotelDeals(@Param("hotelId") Long hotelId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") Date checkDateFrom, @Param("checkDateTo") Date checkDateTo);	
	
//	@Query(FIND_CLOSED_DEALS_BY_HOTEL_QUERY)
//	public List<CustomerDeal> getClosedHotelDeals(@Param("hotelId") Long hotelId);

	@Query(FIND_BY_INIT_ID_QUERY)
	List<CustomerDeal> getByInitId(@Param("initId") Long initId);

	
	//
////	@Query(FIND_STAFF_BY_activity_QUERY)
////	public List<Customer> getStaffByactivityId(@Param("activityId") Long activityId);	
////	
////	@Query(FIND_STAFF_IDS_BY_activity_QUERY)
////	public List<Integer> getStaffIdsByactivityId(@Param("activityId") Long activityId);
//	
//	@Query(FIND_CUSTOMERS_BY_activity_CITY_QUERY)
//	List<Customer> getActiveCustomersByactivityCity(@Param("activityCity") String activityCity, @Param("checkDate") Date checkDate);
//	
//	@Query(FIND_CHECKIN_UNIQUE_CITIES_QUERY)
//	List<String> findNotStaffCheckinUniueCities(@Param("checkDate") Date checkDate);
}
