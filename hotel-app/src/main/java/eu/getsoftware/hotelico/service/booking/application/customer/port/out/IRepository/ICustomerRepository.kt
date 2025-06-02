//package eu.getsoftware.hotelico.service.booking.application.customer.port.out.IRepository
//
//import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.repository.CustomerRepository.Companion.CHECK_STAFF_OR_ADMIN_CUSTOMER
//import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.repository.CustomerRepository.Companion.FIND_ALL_ONLINE
//import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.repository.CustomerRepository.Companion.FIND_ANONYM_CUSTOMER
//import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.repository.CustomerRepository.Companion.FIND_CUSTOMER_CITIES
//import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.CustomerRootDomainEntity
//import org.springframework.data.domain.Page
//import org.springframework.data.domain.Pageable
//import org.springframework.data.jpa.repository.Query
//import org.springframework.data.repository.query.Param
//import java.sql.Timestamp
//
//public interface ICustomerRepository {
//
//    fun saveAndFlush(entity: CustomerRootDomainEntity): CustomerRootDomainEntity
//   
//    /**
//     * Find user by eMail.
//     */
//    fun findByEmailAndActive(email: String, active: Boolean = true): List<CustomerRootDomainEntity>
//
//    /**
//     * Find user by linkedIn Id.
//     */
//    fun findByLinkedInIdAndActive(linkedInId: String, active: Boolean = true): List<CustomerRootDomainEntity>
//
//    /**
//     * Find user by facebook Id.
//     */
//    fun findByFacebookIdAndActive(facebookId: String, active: Boolean = true): List<CustomerRootDomainEntity>
//
//    fun findByActive(active: Boolean = true): List<CustomerRootDomainEntity>
//
//    fun findByActive(active: Boolean = true, pageable: Pageable): Page<CustomerRootDomainEntity>
//
//    fun findByLoggedAndActive(logged: Boolean = true, active: Boolean = true): List<CustomerRootDomainEntity>
//
//    @Query(FIND_ALL_ONLINE)
//    fun findAllOnline(@Param("checkDate") checkDate: Timestamp): List<CustomerRootDomainEntity>
//
//    //	@Query(FIND_BY_ID_LIST)
//    fun findByIdIn(ids: List<Long>): List<CustomerRootDomainEntity>
//
//    fun findByHotelStaffAndActive(hotelStaff: Boolean = true, active: Boolean = true): List<String>
//
//    @Query(FIND_ANONYM_CUSTOMER)
//    fun getAnonymeCustomer(): List<CustomerRootDomainEntity>
//
//    @Query(CHECK_STAFF_OR_ADMIN_CUSTOMER)
//    fun checkStaffOrAdmin(@Param("customerId") customerId: Long): Boolean
//
//    @Query(FIND_CUSTOMER_CITIES)
//    abstract fun findNotStaffUniueCities(): List<String>
//}