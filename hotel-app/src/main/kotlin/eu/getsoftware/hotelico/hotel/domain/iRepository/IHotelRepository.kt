package eu.getsoftware.hotelico.hotel.domain.iRepository;

import eu.getsoftware.hotelico.hotel.infrastructure.entities.HotelRootEntity

public interface IHotelRepository {

    /**
     * Find hotel by hotelCode.
     */
    fun findByCurrentHotelAccessCodeAndActive(currentHotelAccessCode: String, active: Boolean = true): HotelRootEntity?

    fun getActivityCounter(hotelId: Int): Int

    fun getVirtualHotelId(): Int

    fun findByVirtualAndActive(virtual: Boolean = false, active: Boolean = true): List<HotelRootEntity>

    fun getVirtualHotelCode(): String

    fun findByCreationTimeAndActive(creationTime: Long, active: Boolean = true): List<HotelRootEntity>

    fun getDemoHotelId(): Int

    fun findActiveGpsHotels(): List<HotelRootEntity>

    fun findByEmailAndActive(email: String, active: Boolean = true): List<HotelRootEntity>

}
