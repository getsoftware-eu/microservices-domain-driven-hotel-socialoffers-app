package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.repository;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.HotelDbEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HotelRepository: JpaRepository<HotelDbEntity, Long> {

	companion object {
		
		const val FIND_ACTIVITY_NUMBER_BY_HOTEL_QUERY = "SELECT (h.hotelActivities.size ) " +
		"FROM Hotel h " +
		"WHERE h.active = TRUE "+
		"AND h.id = :hotelId ";

		const val FIND_VIRTUAL_HOTEL_ID_QUERY = "SELECT (h.id) " +
		"FROM Hotel h " +
		"WHERE h.active = TRUE "+
		"AND h.virtual = TRUE ";

		const val FIND_DEMO_HOTEL_ID_QUERY = "SELECT (h.domainId) " +
		"FROM Hotel h " +
		"WHERE h.active = TRUE "+
		"AND h.currentHotelAccessCode = 'demo' ";	
		
		const val FIND_DEMO_HOTEL_DOMAIN_ID_QUERY = "SELECT (h.domainEntityId) " +
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
	fun findByCurrentHotelAccessCodeAndActive(currentHotelAccessCode: String, active: Boolean = true): Optional<HotelDbEntity>
	
	fun findByDomainEntityIdAndActive(domainEntityId: HotelDomainEntityId, active: Boolean = true): Optional<HotelDbEntity>
	
	@Query(FIND_ACTIVITY_NUMBER_BY_HOTEL_QUERY)
	fun getActivityCounter(@Param("hotelId") hotelId: Int): Int
	
	@Query(FIND_VIRTUAL_HOTEL_ID_QUERY)
	fun getVirtualHotelId(): HotelDomainEntityId
	
	//@Query(FIND_ACTIVE_NOT_VIRTUAL_HOTELS_QUERY)
	fun findByVirtualAndActive(virtual: Boolean = false, active: Boolean = true): List<HotelDbEntity>
	
	@Query(FIND_VIRTUAL_HOTEL_CODE_QUERY)
	fun getVirtualHotelCode(): String
	
	//@Query(FIND_HOTEL_BY_CREATION_TIME_QUERY)
	fun findByCreationTimeAndActive(creationTime: Long, active: Boolean = true): List<HotelDbEntity>
	
	@Query(FIND_DEMO_HOTEL_ID_QUERY)
	fun getDemoHotelId(): HotelDomainEntityId
	
	@Query(FIND_DEMO_HOTEL_DOMAIN_ID_QUERY)
	fun getDemoHotelDomainId(): HotelDomainEntityId

	@Query(FIND_ACTIVE_GPS_HOTELS_QUERY)
	fun findActiveGpsHotels(): List<HotelDbEntity>
	
//	@Query(FIND_HOTEL_BY_MAIL_QUERY)
	fun findByEmailAndActive(email: String, active: Boolean = true): List<HotelDbEntity>
}
