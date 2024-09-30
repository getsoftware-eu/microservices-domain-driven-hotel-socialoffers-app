package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.repository;

import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerRootEntity
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity
import eu.getsoftware.hotelico.hotelapp.application.customer.port.out.IRepository.ICustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.sql.Timestamp

interface CustomerRepository: ICustomerRepository, JpaRepository<CustomerRootEntity, Long> {
	
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
	override fun findByEmailAndActive(email: String, active: Boolean): List<CustomerRootEntity>
	
	/**
	 * Find user by linkedIn Id.
	 */
	override fun findByLinkedInIdAndActive(linkedInId: String, active: Boolean): List<CustomerRootEntity>	
	
	/**
	 * Find user by facebook Id.
	 */
	override fun findByFacebookIdAndActive(facebookId: String, active: Boolean): List<CustomerRootEntity>
	
	override fun findByActive(active: Boolean): List<CustomerRootEntity>	
	
	override fun findByActive(active: Boolean, pageable: Pageable): Page<ICustomerRootEntity>	
	
	override fun findByLoggedAndActive(logged: Boolean, active: Boolean): List<CustomerRootEntity>
	
	@Query(FIND_ALL_ONLINE)
	override fun findAllOnline(@Param("checkDate") checkDate: Timestamp): List<CustomerRootEntity>

//	@Query(FIND_BY_ID_LIST)
override fun findByIdIn(ids: List<Long>): List<CustomerRootEntity>
	
	override fun findByHotelStaffAndActive(hotelStaff: Boolean, active: Boolean): List<String>

	@Query(FIND_ANONYM_CUSTOMER)
	override fun getAnonymeCustomer(): List<CustomerRootEntity>
	
	@Query(CHECK_STAFF_OR_ADMIN_CUSTOMER)
	override fun checkStaffOrAdmin(@Param("customerId") customerId: Long): Boolean

	@Query(FIND_CUSTOMER_CITIES)
	abstract override fun findNotStaffUniueCities(): List<String>
}
