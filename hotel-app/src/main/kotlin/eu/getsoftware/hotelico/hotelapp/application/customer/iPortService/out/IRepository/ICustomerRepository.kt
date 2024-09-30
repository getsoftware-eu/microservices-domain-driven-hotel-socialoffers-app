package eu.getsoftware.hotelico.hotelapp.application.customer.iPortService.out.IRepository

import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.repository.CustomerRepository.Companion.CHECK_STAFF_OR_ADMIN_CUSTOMER
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.repository.CustomerRepository.Companion.FIND_ALL_ONLINE
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.repository.CustomerRepository.Companion.FIND_ANONYM_CUSTOMER
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.repository.CustomerRepository.Companion.FIND_CUSTOMER_CITIES
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.sql.Timestamp

public interface ICustomerRepository {

    fun saveAndFlush(entity: ICustomerRootEntity): ICustomerRootEntity
   
    /**
     * Find user by eMail.
     */
    fun findByEmailAndActive(email: String, active: Boolean = true): List<ICustomerRootEntity>

    /**
     * Find user by linkedIn Id.
     */
    fun findByLinkedInIdAndActive(linkedInId: String, active: Boolean = true): List<ICustomerRootEntity>

    /**
     * Find user by facebook Id.
     */
    fun findByFacebookIdAndActive(facebookId: String, active: Boolean = true): List<ICustomerRootEntity>

    fun findByActive(active: Boolean = true): List<ICustomerRootEntity>

    fun findByActive(active: Boolean = true, pageable: Pageable): Page<ICustomerRootEntity>

    fun findByLoggedAndActive(logged: Boolean = true, active: Boolean = true): List<ICustomerRootEntity>

    @Query(FIND_ALL_ONLINE)
    fun findAllOnline(@Param("checkDate") checkDate: Timestamp): List<ICustomerRootEntity>

    //	@Query(FIND_BY_ID_LIST)
    fun findByIdIn(ids: List<Long>): List<ICustomerRootEntity>

    fun findByHotelStaffAndActive(hotelStaff: Boolean = true, active: Boolean = true): List<String>

    @Query(FIND_ANONYM_CUSTOMER)
    fun getAnonymeCustomer(): List<ICustomerRootEntity>

    @Query(CHECK_STAFF_OR_ADMIN_CUSTOMER)
    fun checkStaffOrAdmin(@Param("customerId") customerId: Long): Boolean

    @Query(FIND_CUSTOMER_CITIES)
    abstract fun findNotStaffUniueCities(): List<String>
}