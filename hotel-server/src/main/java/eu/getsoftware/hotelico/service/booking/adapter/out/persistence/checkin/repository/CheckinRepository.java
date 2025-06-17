package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.repository;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.CheckinDBEntity;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CheckinRepository extends JpaRepository<CheckinDBEntity, Long> {

	public final static String FIND_ACTIVE_BY_CUSTOMER_QUERY = """
		SELECT c
		FROM CheckinEntity c
		WHERE c.active = true
		AND c.customerDomainEntityIdValue = :customerId
		AND c.validFrom <= :checkDate
		AND c.validTo >= :checkDate
	""";
	
	public final static String FIND_HOTEL_BY_CUSTOMER_QUERY = "SELECT c.hotelDomainEntityIdValue " +
			"FROM CheckinEntity c " +
			"WHERE c.active = true " +
			"AND c.customerDomainEntityIdValue = :customerId " +
			"AND ( :checkDate BETWEEN c.validFrom AND c.validTo ) " +
			  "OR (c.staffCheckin = TRUE)";	
	
	public final static String FIND_STAFF_BY_HOTEL_QUERY = "SELECT c.customerDomainEntityIdValue " +
			"FROM CheckinEntity c " +
			"WHERE c.active = true " +
			"AND c.hotelDomainEntityIdValue = :hotelId " +
			"AND c.staffCheckin = true ";	
	
	public final static String FIND_STAFF_IDS_BY_HOTEL_QUERY = "SELECT c.customerDomainEntityIdValue " +
			"FROM CheckinEntity c " +
			"WHERE c.active = true " +
			"AND c.hotelDomainEntityIdValue = :hotelId " +
			"AND c.staffCheckin = true ";	
	
//	public final static String FIND_CUSTOMERS_BY_HOTEL_CITY_QUERY = "SELECT c.pk.customer " +
//			"FROM CheckinEntity c " +
//			"WHERE c.active = true " +
////			"AND c.pk.customer.active = true " +
//			"AND (" 
//			+ "c.pk.hotel.city = :hotelCity " +
//			"  OR c.pk.customer.city = :hotelCity " 
//			+")  " +
//			"AND ( :checkDate BETWEEN c.validFrom AND c.validTo ) " ;	
	
	
	public final static String FIND_CHECKIN_UNIQUE_CITIES_QUERY = "SELECT DISTINCT c.hotelDomainEntityIdValue " +
			"FROM CheckinEntity c " +
			"WHERE c.active = true " +
//			"AND c.pk.customer.active = true " +
//			"AND c.pk.hotel.active = true " +
			"AND ( :checkDate BETWEEN c.validFrom AND c.validTo ) " ;
	
	public final static String FIND_ACTIVE_BY_HOTEL_QUERY = "SELECT c " +
			"FROM CheckinEntity c " +
			"WHERE c.active = true " +
			"AND c.hotelDomainEntityIdValue = :hotelId " +
			"AND ( :checkDate BETWEEN c.validFrom AND c.validTo ) " ;


	public final static String FIND_ACTIVE_CUSTOMERS_BY_HOTEL_QUERY = "SELECT c.customerDomainEntityIdValue " +
			"FROM CheckinEntity c " +
			"WHERE c.active = true " +
			"AND c.hotelDomainEntityIdValue = :hotelId " +	
//			"AND c.pk.customer.active = true " +
			"AND ( :checkDate BETWEEN c.validFrom AND c.validTo ) " ;

	
	public final static String FIND_ACTIVE_FULL_CHECKIN_BY_HOTEL_QUERY = "SELECT c " +
			"FROM CheckinEntity c " +
			"WHERE c.active = true " +
			"AND c.fullCheckin = true " +
			"AND c.hotelDomainEntityIdValue = :hotelId " +
			"AND ( :checkDate BETWEEN c.validFrom AND c.validTo ) " ;
		
	
	public final static String FIND_LAST_CHECKIN_BY_HOTEL_QUERY = "SELECT max(c.validFrom) " +
			"FROM CheckinEntity c " +
			"WHERE c.customerDomainEntityIdValue = :customerId " +
			"AND c.hotelDomainEntityIdValue = :hotelId ";	
	
	public final static String IS_ACTIVE_FULL_CHECKIN_FOR_CUSTOMER_BY_HOTEL_QUERY = "SELECT count(c)>0 " +
			"FROM CheckinEntity c " +
			"WHERE c.active = true " +
			"AND c.hotelDomainEntityIdValue = :hotelId " +
			"AND c.customerDomainEntityIdValue = :customerId " +
			"AND (" +
			  "( " +
				" c.fullCheckin = true " +
				"AND ( :checkDate BETWEEN c.validFrom AND c.validTo ) " +
			  " ) " +
				"OR ( c.staffCheckin = TRUE ) " +
			  " ) ";
	
	public final static String COUNT_ACTIVE_BY_HOTEL_QUERY = "SELECT count(c) " +
			"FROM CheckinEntity c " +
			"WHERE c.active = true " +
			"AND c.hotelDomainEntityIdValue = :hotelId "+
			"AND ( :checkDate BETWEEN c.validFrom AND c.validTo ) ";
	
	public final static String COUNT_NOT_STAFF_ACTIVE_BY_HOTEL_QUERY = "SELECT count(c) " +
			"FROM CheckinEntity c " +
			"WHERE c.active = true " +
			"AND c.staffCheckin = false " +
			"AND c.hotelDomainEntityIdValue = :hotelId " +
			"AND ( :checkDate BETWEEN c.validFrom AND c.validTo ) ";
	
	public final static String COUNT_NOT_STAFF_FULL_CHECKIN_ACTIVE_BY_HOTEL_QUERY = "SELECT count(c) " +
			"FROM CheckinEntity c " +
			"WHERE c.active = true " +
			"AND c.fullCheckin = true " +
			"AND c.staffCheckin = false " +
			"AND c.hotelDomainEntityIdValue = :hotelId " +
			"AND ( :checkDate BETWEEN c.validFrom AND c.validTo ) ";

	/**
	 * Find customer active checkIn.
	 */
	@Query(FIND_ACTIVE_BY_CUSTOMER_QUERY)
	public List<CheckinDBEntity> getActiveByCustomerId(@Param("customerId") CustomerDomainEntityId customerId, @Param("checkDate") LocalDate checkDate);	
	
	/**
	 * Find customer active checkIn.
	 */
	@Query(FIND_HOTEL_BY_CUSTOMER_QUERY)
	public Long getCustomerHotelId(@Param("customerId") CustomerDomainEntityId customerId, @Param("checkDate") LocalDate checkDate);	

	/**
	 * Find customer active checkIn.
	 */
	@Query(COUNT_ACTIVE_BY_HOTEL_QUERY)
	public Integer getActiveCountByHotelId(@Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") LocalDate checkDate);

	/**
	 * Find NOT STAFF active Checkin counter
	 * @param hotelId
	 * @return
	 */
	@Query(COUNT_NOT_STAFF_ACTIVE_BY_HOTEL_QUERY)
	public Integer getActiveCountExcludingStaffByHotelId(@Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") LocalDate checkDate);	
	
	@Query(COUNT_NOT_STAFF_FULL_CHECKIN_ACTIVE_BY_HOTEL_QUERY)
	public Integer getFullCheckinCountExcludingStaffByHotelId(@Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") LocalDate checkDate);
	
	/**
	 * Find customers active checkIn by hotel.
	 */
	@Query(FIND_ACTIVE_BY_HOTEL_QUERY)
	public List<CheckinDBEntity> getActiveByHotelId(@Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") LocalDate checkDate);	
	
	@Query(FIND_ACTIVE_CUSTOMERS_BY_HOTEL_QUERY)
	public List<CustomerDBEntity> getActiveCustomersByHotelId(@Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") LocalDate checkDate);	
	
	@Query(FIND_ACTIVE_FULL_CHECKIN_BY_HOTEL_QUERY)
	public List<CheckinDBEntity> getActiveFullCheckinByHotelId(@Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") LocalDate checkDate);	
	
	@Query(IS_ACTIVE_FULL_CHECKIN_FOR_CUSTOMER_BY_HOTEL_QUERY)
	public boolean isFullCheckinForCustomerByHotelId(@Param("customerId") CustomerDomainEntityId customerId, @Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") LocalDate checkDate);

	@Query(FIND_STAFF_BY_HOTEL_QUERY)
	public List<CustomerDBEntity> getStaffByHotelId(@Param("hotelId") HotelDomainEntityId hotelId);	
	
	@Query(FIND_STAFF_IDS_BY_HOTEL_QUERY)
	public List<Integer> getStaffIdsByHotelId(@Param("hotelId") HotelDomainEntityId hotelId);
	
//	@Query(FIND_CUSTOMERS_BY_HOTEL_CITY_QUERY)
//	List<CustomerDBEntity> getActiveCustomersByHotelCity(@Param("hotelCity") String hotelCity, @Param("checkDate") LocalDate checkDate);
	
	@Query(FIND_CHECKIN_UNIQUE_CITIES_QUERY)
	List<String> findNotStaffCheckinUniueCities(@Param("checkDate") LocalDate checkDate);
	
	@Query(FIND_LAST_CHECKIN_BY_HOTEL_QUERY)
	Date getLastByCustomerAndHotelId(@Param("customerId") CustomerDomainEntityId customerId, @Param("hotelId") HotelDomainEntityId hotelId);

	@Query("""
    	SELECT c.hotelDomainEntityIdValue FROM CheckinEntity c
    	WHERE c.hotelDomainEntityIdValue = :customerId
    		AND :checkinDate BETWEEN c.validFrom AND c.validTo
	""")
	HotelDomainEntityId getHotelDomainEntityId(CustomerDomainEntityId customerId, LocalDate checkinDate);

	boolean existsByDomainEntityIdValue(EntityIdentifier domainEntityId);

	Optional<CheckinDBEntity> findByDomainEntityIdValue(EntityIdentifier domainEntityId);

	void deleteByDomainEntityIdValue(EntityIdentifier domainEntityId);
}
