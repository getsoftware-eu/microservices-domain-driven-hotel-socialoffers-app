package eu.getsoftware.hotelico.hotelapp.application.hotel.iPortService.out.iRepository;

import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerRootEntity
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotelCustomer.model.CustomerHotelCheckin
import org.springframework.data.repository.query.Param
import java.util.*

public interface ICheckinRepository {

    /**
     * Find customer active checkIn.
     */
    fun getActiveByCustomerId(
        @Param("customerId") customerId: Long?,
        @Param("checkDate") checkDate: Date?
    ): List<CustomerHotelCheckin?>?

    /**
     * Find customer active checkIn.
     */
    fun getCustomerHotelId(@Param("customerId") customerId: Long?, @Param("checkDate") checkDate: Date?): Long?

    /**
     * Find customer active checkIn.
     */
    fun getActiveCountByHotelId(@Param("hotelId") hotelId: Long?, @Param("checkDate") checkDate: Date?): Int?

    /**
     * Find NOT STAFF active Checkin counter
     * @param hotelId
     * @return
     */
    fun getActiveCountExcludingStaffByHotelId(
        @Param("hotelId") hotelId: Long?,
        @Param("checkDate") checkDate: Date?
    ): Int?

    fun getFullCheckinCountExcludingStaffByHotelId(
        @Param("hotelId") hotelId: Long?,
        @Param("checkDate") checkDate: Date?
    ): Int?

    /**
     * Find customers active checkIn by hotel.
     */
    fun getActiveByHotelId(
        @Param("hotelId") hotelId: Long?,
        @Param("checkDate") checkDate: Date?
    ): List<CustomerHotelCheckin?>?

    fun getActiveCustomersByHotelId(
        @Param("hotelId") hotelId: Long?,
        @Param("checkDate") checkDate: Date?
    ): List<CustomerRootEntity?>?

    fun getActiveFullCheckinByHotelId(
        @Param("hotelId") hotelId: Long?,
        @Param("checkDate") checkDate: Date?
    ): List<CustomerHotelCheckin?>?

    fun isFullCheckinForCustomerByHotelId(
        @Param("customerId") customerId: Long?,
        @Param("hotelId") hotelId: Long?,
        @Param("checkDate") checkDate: Date?
    ): Boolean

    fun getStaffByHotelId(@Param("hotelId") hotelId: Long?): List<CustomerRootEntity?>?

    fun getStaffIdsByHotelId(@Param("hotelId") hotelId: Long?): List<Int?>?

    fun getActiveCustomersByHotelCity(
        @Param("hotelCity") hotelCity: String?,
        @Param("checkDate") checkDate: Date?
    ): List<CustomerRootEntity?>?

    fun findNotStaffCheckinUniueCities(@Param("checkDate") checkDate: Date?): List<String?>?

    fun getLastByCustomerAndHotelId(@Param("customerId") customerId: Long?, @Param("hotelId") hotelId: Long?): Date?

}
