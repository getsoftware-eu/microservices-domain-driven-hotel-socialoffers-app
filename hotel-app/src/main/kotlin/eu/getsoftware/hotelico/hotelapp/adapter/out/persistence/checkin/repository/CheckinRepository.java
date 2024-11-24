package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.repository;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.model.CheckinDBEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CheckinRepository extends JpaRepository<CheckinDBEntity, Long> {

	public final static String FIND_ACTIVE_BY_CUSTOMER_QUERY = "SELECT c " +
			"FROM CheckinDbEntity c " +
			"WHERE c.active = true " +
			"AND c.pk.customer.id = :customerId " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";	
	
	public final static String FIND_HOTEL_BY_CUSTOMER_QUERY = "SELECT c.pk.hotel.id " +
			"FROM CheckinDbEntity c " +
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
			"FROM CheckinDbEntity c " +
			"WHERE c.active = true " +
			"AND c.pk.hotel.id = :hotelId " +
			"AND c.pk.customer.hotelStaff = true ";	
	
	public final static String FIND_STAFF_IDS_BY_HOTEL_QUERY = "SELECT c.pk.customer.id " +
			"FROM CheckinDbEntity c " +
			"WHERE c.active = true " +
			"AND c.pk.hotel.id = :hotelId " +
			"AND c.pk.customer.hotelStaff = true ";	
	
	public final static String FIND_CUSTOMERS_BY_HOTEL_CITY_QUERY = "SELECT c.pk.customer " +
			"FROM CheckinDbEntity c " +
			"WHERE c.active = true " +
			"AND c.pk.customer.active = true " +
			"AND (" 
			+ "c.pk.hotel.city = :hotelCity " +
			"  OR c.pk.customer.city = :hotelCity " 
			+")  " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";	
	
	
	public final static String FIND_CHECKIN_UNIQUE_CITIES_QUERY = "SELECT DISTINCT c.pk.hotel.city " +
			"FROM CheckinDbEntity c " +
			"WHERE c.active = true " +
			"AND c.pk.customer.active = true " +
			"AND c.pk.hotel.active = true " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";
	
	public final static String FIND_ACTIVE_BY_HOTEL_QUERY = "SELECT c " +
			"FROM CheckinDbEntity c " +
			"WHERE c.active = true " +
			"AND c.pk.hotel.id = :hotelId " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";
	
	public final static String FIND_ACTIVE_CUSTOMERS_BY_HOTEL_QUERY = "SELECT c.pk.customer " +
			"FROM CheckinDbEntity c " +
			"WHERE c.active = true " +
			"AND c.pk.hotel.id = :hotelId " +	
			"AND c.pk.customer.active = true " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";	
	
	public final static String FIND_ACTIVE_FULL_CHECKIN_BY_HOTEL_QUERY = "SELECT c " +
			"FROM CheckinDbEntity c " +
			"WHERE c.active = true " +
			"AND c.fullCheckin = true " +
			"AND c.pk.hotel.id = :hotelId " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";		
	
	public final static String FIND_LAST_CHECKIN_BY_HOTEL_QUERY = "SELECT max(c.validFrom) " +
			"FROM CheckinDbEntity c " +
			"WHERE c.pk.customer.id = :customerId " +
			"AND c.pk.hotel.id = :hotelId ";	
	
	public final static String IS_ACTIVE_FULL_CHECKIN_FOR_CUSTOMER_BY_HOTEL_QUERY = "SELECT count(c)>0 " +
			"FROM CheckinDbEntity c " +
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
			"FROM CheckinDbEntity c " +
			"WHERE c.active = true " +
			"AND c.pk.hotel.id = :hotelId "+
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";
	
	public final static String COUNT_NOT_STAFF_ACTIVE_BY_HOTEL_QUERY = "SELECT count(c) " +
			"FROM CheckinDbEntity c " +
			"WHERE c.active = true " +
			"AND c.pk.customer.hotelStaff = false " +
			"AND c.pk.hotel.id = :hotelId " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";
	
	public final static String COUNT_NOT_STAFF_FULL_CHECKIN_ACTIVE_BY_HOTEL_QUERY = "SELECT count(c) " +
			"FROM CheckinDbEntity c " +
			"WHERE c.active = true " +
			"AND c.fullCheckin = true " +
			"AND c.pk.customer.hotelStaff = false " +
			"AND c.pk.hotel.id = :hotelId " +
			"AND c.validFrom <= :checkDate " +
			"AND c.validTo >= :checkDate ";

	/**
	 * Find customer active checkIn.
	 */
	@Query(FIND_ACTIVE_BY_CUSTOMER_QUERY)
	public List<CheckinDBEntity> getActiveByCustomerId(@Param("customerId") CustomerDomainEntityId customerId, @Param("checkDate") Date checkDate);	
	
	/**
	 * Find customer active checkIn.
	 */
	@Query(FIND_HOTEL_BY_CUSTOMER_QUERY)
	public Long getCustomerHotelId(@Param("customerId") CustomerDomainEntityId customerId, @Param("checkDate") Date checkDate);	

	/**
	 * Find customer active checkIn.
	 */
	@Query(COUNT_ACTIVE_BY_HOTEL_QUERY)
	public Integer getActiveCountByHotelId(@Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") Date checkDate);

	/**
	 * Find NOT STAFF active Checkin counter
	 * @param hotelId
	 * @return
	 */
	@Query(COUNT_NOT_STAFF_ACTIVE_BY_HOTEL_QUERY)
	public Integer getActiveCountExcludingStaffByHotelId(@Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") Date checkDate);	
	
	@Query(COUNT_NOT_STAFF_FULL_CHECKIN_ACTIVE_BY_HOTEL_QUERY)
	public Integer getFullCheckinCountExcludingStaffByHotelId(@Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") Date checkDate);
	
	/**
	 * Find customers active checkIn by hotel.
	 */
	@Query(FIND_ACTIVE_BY_HOTEL_QUERY)
	public List<CheckinDBEntity> getActiveByHotelId(@Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") Date checkDate);	
	
	@Query(FIND_ACTIVE_CUSTOMERS_BY_HOTEL_QUERY)
	public List<CustomerDBEntity> getActiveCustomersByHotelId(@Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") Date checkDate);	
	
	@Query(FIND_ACTIVE_FULL_CHECKIN_BY_HOTEL_QUERY)
	public List<CheckinDBEntity> getActiveFullCheckinByHotelId(@Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") Date checkDate);	
	
	@Query(IS_ACTIVE_FULL_CHECKIN_FOR_CUSTOMER_BY_HOTEL_QUERY)
	public boolean isFullCheckinForCustomerByHotelId(@Param("customerId") CustomerDomainEntityId customerId, @Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") Date checkDate);

	@Query(FIND_STAFF_BY_HOTEL_QUERY)
	public List<CustomerDBEntity> getStaffByHotelId(@Param("hotelId") HotelDomainEntityId hotelId);	
	
	@Query(FIND_STAFF_IDS_BY_HOTEL_QUERY)
	public List<Integer> getStaffIdsByHotelId(@Param("hotelId") HotelDomainEntityId hotelId);
	
	@Query(FIND_CUSTOMERS_BY_HOTEL_CITY_QUERY)
	List<CustomerDBEntity> getActiveCustomersByHotelCity(@Param("hotelCity") String hotelCity, @Param("checkDate") Date checkDate);
	
	@Query(FIND_CHECKIN_UNIQUE_CITIES_QUERY)
	List<String> findNotStaffCheckinUniueCities(@Param("checkDate") Date checkDate);
	
	@Query(FIND_LAST_CHECKIN_BY_HOTEL_QUERY)
	Date getLastByCustomerAndHotelId(@Param("customerId") CustomerDomainEntityId customerId, @Param("hotelId") HotelDomainEntityId hotelId);

	HotelDomainEntityId getCustomerHotelDomainId(CustomerDomainEntityId customerId, Date date);
}
