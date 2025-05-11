package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.repository;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.util.*

@Repository
interface CustomerRepository: JpaRepository<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity, Long> {
	
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

	fun findByDomainEntityIdAndActive(domainEntityId: CustomerDomainEntityId, active: Boolean = true): Optional<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity>


	/**
	 * Find user by eMail.
	 */
	fun findByEmailAndActive(email: String, active: Boolean): List<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity>
	
	/**
	 * Find user by linkedIn Id.
	 */
	fun findByLinkedInIdAndActive(linkedInId: String, active: Boolean): List<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity>	
	
	/**
	 * Find user by facebook Id.
	 */
	fun findByFacebookIdAndActive(facebookId: String, active: Boolean): List<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity>
	
	fun findByActive(active: Boolean): List<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity>	
	
	fun findByActive(active: Boolean, pageable: Pageable): Page<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity>	
	
	fun findByLoggedAndActive(logged: Boolean, active: Boolean): List<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity>
	
	@Query(FIND_ALL_ONLINE)
	fun findAllOnline(@Param("checkDate") checkDate: Timestamp): List<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity>

	//	@Query(FIND_BY_ID_LIST)
	fun findByIdIn(ids: List<Long>): List<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity>
	fun findByDomainIdIn(ids: List<CustomerDomainEntityId>): List<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity>
	
	fun findByHotelStaffAndActive(hotelStaff: Boolean, active: Boolean): List<String>

	@Query(FIND_ANONYM_CUSTOMER)
	fun getAnonymeCustomer(): List<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity>
	
	@Query(CHECK_STAFF_OR_ADMIN_CUSTOMER)
	fun checkStaffOrAdmin(@Param("customerId") customerId: CustomerDomainEntityId): Boolean

	@Query(FIND_CUSTOMER_CITIES)
	abstract fun findNotStaffUniueCities(): List<String>

//	override fun findByName(name: String?): Optional<CustomerDBEntity>
	fun findByDomainId(customerId: CustomerDomainEntityId): Optional<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity> 

}
