package eu.getsoftware.hotelico.hotel.infrastructure.repository;

import eu.getsoftware.hotelico.hotel.domain.HotelRootEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface HotelRepository: JpaRepository<HotelRootEntity, Long> {

	companion object {
		
		const val FIND_ACTIVITY_NUMBER_BY_HOTEL_QUERY = "SELECT (h.hotelActivities.size ) " +
		"FROM Hotel h " +
		"WHERE h.active = TRUE "+
		"AND h.id = :hotelId ";

		const val FIND_VIRTUAL_HOTEL_ID_QUERY = "SELECT (h.id) " +
		"FROM Hotel h " +
		"WHERE h.active = TRUE "+
		"AND h.virtual = TRUE ";

		const val FIND_DEMO_HOTEL_ID_QUERY = "SELECT (h.id) " +
		"FROM Hotel h " +
		"WHERE h.active = TRUE "+
		"AND h.currentHotelAccessCode = 'demo' ";

		const val FIND_ACTIVE_GPS_HOTELS_QUERY = "SELECT h " +
		"FROM Hotel h " +
		"WHERE h.active = TRUE "+
		"AND h.city is not null "+
		"AND h.latitude > -90 " +
		"AND h.longitude > -180 ";


		const val FIND_VIRTUAL_HOTEL_CODE_QUERY = "SELECT (h.currentHotelAccessCode) " +
		"FROM Hotel h " +
		"WHERE h.active = TRUE "+
		"AND h.virtual = TRUE ";
	}
	
	/**
	 * Find hotel by hotelCode.
	 */
	//@Query(FIND_BY_HOTEL_CODE_QUERY)
	fun findByCurrentHotelAccessCodeAndActive(currentHotelAccessCode: String, active: Boolean = true): HotelRootEntity
	
	@Query(FIND_ACTIVITY_NUMBER_BY_HOTEL_QUERY)
	fun getActivityCounter(@Param("hotelId") hotelId: Int): Int
	
	@Query(FIND_VIRTUAL_HOTEL_ID_QUERY)
	fun getVirtualHotelId(): Int
	
	//@Query(FIND_ACTIVE_NOT_VIRTUAL_HOTELS_QUERY)
	fun findByVirtualAndActive(virtual: Boolean = false, active: Boolean = true): List<HotelRootEntity>
	
	@Query(FIND_VIRTUAL_HOTEL_CODE_QUERY)
	fun getVirtualHotelCode(): String
	
	//@Query(FIND_HOTEL_BY_CREATION_TIME_QUERY)
	fun findByCreationTimeAndActive(creationTime: Long, active: Boolean = true): List<HotelRootEntity>
	
	@Query(FIND_DEMO_HOTEL_ID_QUERY)
	fun getDemoHotelId(): Int

	@Query(FIND_ACTIVE_GPS_HOTELS_QUERY)
	fun findActiveGpsHotels(): List<HotelRootEntity>
	
//	@Query(FIND_HOTEL_BY_MAIL_QUERY)
	fun findByEmailAndActive(email: String, active: Boolean = true): List<HotelRootEntity>
}
