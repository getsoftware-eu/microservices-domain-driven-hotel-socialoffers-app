package eu.getsoftware.hotelico.hotel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

interface CustomerRepository: JpaRepository<eu.getsoftware.hotelico.hotel.model.Customer, Long> {
	
	companion object {
		
		const val FIND_ALL_ONLINE = "SELECT c " +
				"FROM Customer c " +
				"WHERE c.active = TRUE "+
				"AND c.logged = TRUE "+
				"AND c.lastSeenOnline > :checkDate "
	
		const val FIND_CUSTOMER_CITIES = "SELECT DISTINCT c.city " +
				"FROM Customer c " +
				"WHERE c.active = TRUE " +
				"AND c.hotelStaff = FALSE "	+
				"AND c.city IS NOT NULL "	
		
		const val FIND_ANONYM_CUSTOMER = "SELECT  c " +
				"FROM Customer c " +
				"WHERE c.active = TRUE "+
				"AND c.email = '[anonym]' "	
		
		const val CHECK_STAFF_OR_ADMIN_CUSTOMER = "SELECT CASE WHEN (c.hotelStaff=TRUE OR c.admin=TRUE) THEN true ELSE false END " +
				"FROM Customer c " +
				"WHERE c.active = TRUE "+
				"AND c.id = :customerId " 
	}

	/**
	 * Find user by eMail.
	 */
	fun findByEmailAndActive(email: String, active: Boolean = true): List<eu.getsoftware.hotelico.hotel.model.Customer>
	
	/**
	 * Find user by linkedIn Id.
	 */
	fun findByLinkedInIdAndActive(linkedInId: String, active: Boolean = true): List<eu.getsoftware.hotelico.hotel.model.Customer>	
	
	/**
	 * Find user by facebook Id.
	 */
	fun findByFacebookIdAndActive(facebookId: String, active: Boolean = true): List<eu.getsoftware.hotelico.hotel.model.Customer>
	
	fun findByActive(active: Boolean = true): List<eu.getsoftware.hotelico.hotel.model.Customer>	
	
	fun findByActive(active: Boolean = true, pageable: Pageable): Page<eu.getsoftware.hotelico.hotel.model.Customer>	
	
	fun findByLoggedAndActive(logged: Boolean = true, active: Boolean = true): List<eu.getsoftware.hotelico.hotel.model.Customer>
	
	@Query(FIND_ALL_ONLINE)
	fun findAllOnline(@Param("checkDate") checkDate: Timestamp): List<eu.getsoftware.hotelico.hotel.model.Customer>

//	@Query(FIND_BY_ID_LIST)
	fun findByIdIn(ids: List<Long>): List<eu.getsoftware.hotelico.hotel.model.Customer>
	
	fun findByHotelStaffAndActive(hotelStaff: Boolean = true, active: Boolean = true): List<String>

	@Query(FIND_ANONYM_CUSTOMER)
	fun getAnonymeCustomer(): List<eu.getsoftware.hotelico.hotel.model.Customer>
	
	@Query(CHECK_STAFF_OR_ADMIN_CUSTOMER)
	fun checkStaffOrAdmin(@Param("customerId") customerId: Long): Boolean

	@Query(FIND_CUSTOMER_CITIES)
	abstract fun findNotStaffUniueCities(): List<String>
}
