package de.hotelico.repository;

import de.hotelico.model.Customer;
import de.hotelico.model.CustomerHotelCheckin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CheckinRepository extends JpaRepository<CustomerHotelCheckin, Long> {

	public final static String FIND_ACTIVE_BY_CUSTOMER_QUERY = "SELECT c " +
			"FROM CustomerHotelCheckin c " +
			"WHERE c.active = true " +
			"AND c.pk.customer.id = :customerId " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";	
	
	public final static String FIND_HOTEL_BY_CUSTOMER_QUERY = "SELECT c.pk.hotel.id " +
			"FROM CustomerHotelCheckin c " +
			"WHERE c.active = true " +
			"AND c.pk.customer.id = :customerId " +
			"AND ( " +
			  " ( " +
				  " c.validFrom <= :checkDate " +
				  " AND c.validTo >= :checkDate " +
			  " ) " +
			  "OR (c.pk.customer.hotelStaff = TRUE)" +
			")";	
	
	public final static String FIND_STAFF_BY_HOTEL_QUERY = "SELECT c.pk.customer " +
			"FROM CustomerHotelCheckin c " +
			"WHERE c.active = true " +
			"AND c.pk.hotel.id = :hotelId " +
			"AND c.pk.customer.hotelStaff = true ";	
	
	public final static String FIND_STAFF_IDS_BY_HOTEL_QUERY = "SELECT c.pk.customer.id " +
			"FROM CustomerHotelCheckin c " +
			"WHERE c.active = true " +
			"AND c.pk.hotel.id = :hotelId " +
			"AND c.pk.customer.hotelStaff = true ";	
	
	public final static String FIND_CUSTOMERS_BY_HOTEL_CITY_QUERY = "SELECT c.pk.customer " +
			"FROM CustomerHotelCheckin c " +
			"WHERE c.active = true " +
			"AND c.pk.customer.active = true " +
			"AND (" 
			+ "c.pk.hotel.city = :hotelCity " +
			"  OR c.pk.customer.city = :hotelCity " 
			+")  " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";	
	
	
	public final static String FIND_CHECKIN_UNIQUE_CITIES_QUERY = "SELECT DISTINCT c.pk.hotel.city " +
			"FROM CustomerHotelCheckin c " +
			"WHERE c.active = true " +
			"AND c.pk.customer.active = true " +
			"AND c.pk.hotel.active = true " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";
	
	public final static String FIND_ACTIVE_BY_HOTEL_QUERY = "SELECT c " +
			"FROM CustomerHotelCheckin c " +
			"WHERE c.active = true " +
			"AND c.pk.hotel.id = :hotelId " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";
	
	public final static String FIND_ACTIVE_CUSTOMERS_BY_HOTEL_QUERY = "SELECT c.pk.customer " +
			"FROM CustomerHotelCheckin c " +
			"WHERE c.active = true " +
			"AND c.pk.hotel.id = :hotelId " +	
			"AND c.pk.customer.active = true " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";	
	
	public final static String FIND_ACTIVE_FULL_CHECKIN_BY_HOTEL_QUERY = "SELECT c " +
			"FROM CustomerHotelCheckin c " +
			"WHERE c.active = true " +
			"AND c.fullCheckin = true " +
			"AND c.pk.hotel.id = :hotelId " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";		
	
	public final static String FIND_LAST_CHECKIN_BY_HOTEL_QUERY = "SELECT max(c.validFrom) " +
			"FROM CustomerHotelCheckin c " +
//			"WHERE c.active = true " +
//			"AND c.fullCheckin = true " +
			"WHERE c.pk.customer.id = :customerId " +
			"AND c.pk.hotel.id = :hotelId " 
//			+
//			"AND c.validFrom <= :checkDate " +
//			"AND c.validTo >= :checkDate "
			;	
	
	public final static String IS_ACTIVE_FULL_CHECKIN_FOR_CUSTOMER_BY_HOTEL_QUERY = "SELECT count(c)>0 " +
			"FROM CustomerHotelCheckin c " +
			"WHERE c.active = true " +
			"AND c.pk.hotel.id = :hotelId " +
			"AND c.pk.customer.id = :customerId " +
			"AND (" 
			+ "( " +
				" c.fullCheckin = true " +
				"AND c.validFrom <= :checkDate " +
				"AND c.validTo >= :checkDate " 
			+ " ) " +
				"OR ( c.pk.customer.hotelStaff = TRUE ) " 
			+ " ) ";
	
	public final static String COUNT_ACTIVE_BY_HOTEL_QUERY = "SELECT count(c) " +
			"FROM CustomerHotelCheckin c " +
			"WHERE c.active = true " +
			"AND c.pk.hotel.id = :hotelId "+
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";
	
	public final static String COUNT_NOT_STAFF_ACTIVE_BY_HOTEL_QUERY = "SELECT count(c) " +
			"FROM CustomerHotelCheckin c " +
			"WHERE c.active = true " +
//			"AND c.fullCheckin = true " +
			"AND c.pk.customer.hotelStaff = false " +
			"AND c.pk.hotel.id = :hotelId " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";
	
	public final static String COUNT_NOT_STAFF_FULL_CHECKIN_ACTIVE_BY_HOTEL_QUERY = "SELECT count(c) " +
			"FROM CustomerHotelCheckin c " +
			"WHERE c.active = true " +
			"AND c.fullCheckin = true " +
			"AND c.pk.customer.hotelStaff = false " +
			"AND c.pk.hotel.id = :hotelId " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";
	
	
//	public final static String COUNT_ONLINE_BY_HOTEL_QUERY = "SELECT count(c.) " +
//			"FROM CustomerHotelCheckin c " +
//			"WHERE c.active = true " +
//			"AND c.pk.hotel.id = :hotelId";

	/**
	 * Find customer active checkIn.
	 */
	@Query(FIND_ACTIVE_BY_CUSTOMER_QUERY)
	public List<CustomerHotelCheckin> getActiveByCustomerId(@Param("customerId") Long customerId, @Param("checkDate") Date checkDate);	
	
	/**
	 * Find customer active checkIn.
	 */
	@Query(FIND_HOTEL_BY_CUSTOMER_QUERY)
	public Long getCustomerHotelId(@Param("customerId") Long customerId, @Param("checkDate") Date checkDate);	

	/**
	 * Find customer active checkIn.
	 */
	@Query(COUNT_ACTIVE_BY_HOTEL_QUERY)
	public Integer getActiveCountByHotelId(@Param("hotelId") Long hotelId, @Param("checkDate") Date checkDate);

	/**
	 * Find NOT STAFF active Checkin counter
	 * @param hotelId
	 * @return
	 */
	@Query(COUNT_NOT_STAFF_ACTIVE_BY_HOTEL_QUERY)
	public Integer getActiveCountExcludingStaffByHotelId(@Param("hotelId") Long hotelId, @Param("checkDate") Date checkDate);	
	
	@Query(COUNT_NOT_STAFF_FULL_CHECKIN_ACTIVE_BY_HOTEL_QUERY)
	public Integer getFullCheckinCountExcludingStaffByHotelId(@Param("hotelId") Long hotelId, @Param("checkDate") Date checkDate);
	
//	/**
//	 * Find customer active checkIn.
//	 */
//	@Query(COUNT_ONLINE_BY_HOTEL_QUERY)
//	public Integer getOnlineCountByHotelId(@Param("hotelId") Long hotelId);	
	
	/**
	 * Find customers active checkIn by hotel.
	 */
	@Query(FIND_ACTIVE_BY_HOTEL_QUERY)
	public List<CustomerHotelCheckin> getActiveByHotelId(@Param("hotelId") Long hotelId, @Param("checkDate") Date checkDate);	
	
	@Query(FIND_ACTIVE_CUSTOMERS_BY_HOTEL_QUERY)
	public List<Customer> getActiveCustomersByHotelId(@Param("hotelId") Long hotelId, @Param("checkDate") Date checkDate);	
	
	@Query(FIND_ACTIVE_FULL_CHECKIN_BY_HOTEL_QUERY)
	public List<CustomerHotelCheckin> getActiveFullCheckinByHotelId(@Param("hotelId") Long hotelId, @Param("checkDate") Date checkDate);	
	
	@Query(IS_ACTIVE_FULL_CHECKIN_FOR_CUSTOMER_BY_HOTEL_QUERY)
	public boolean isFullCheckinForCustomerByHotelId(@Param("customerId") Long customerId, @Param("hotelId") Long hotelId, @Param("checkDate") Date checkDate);

	@Query(FIND_STAFF_BY_HOTEL_QUERY)
	public List<Customer> getStaffByHotelId(@Param("hotelId") Long hotelId);	
	
	@Query(FIND_STAFF_IDS_BY_HOTEL_QUERY)
	public List<Integer> getStaffIdsByHotelId(@Param("hotelId") Long hotelId);
	
	@Query(FIND_CUSTOMERS_BY_HOTEL_CITY_QUERY)
	List<Customer> getActiveCustomersByHotelCity(@Param("hotelCity") String hotelCity, @Param("checkDate") Date checkDate);
	
	@Query(FIND_CHECKIN_UNIQUE_CITIES_QUERY)
	List<String> findNotStaffCheckinUniueCities(@Param("checkDate") Date checkDate);
	
	@Query(FIND_LAST_CHECKIN_BY_HOTEL_QUERY)
	Date getLastByCustomerAndHotelId(@Param("customerId") Long customerId, @Param("hotelId") Long hotelId);
}
