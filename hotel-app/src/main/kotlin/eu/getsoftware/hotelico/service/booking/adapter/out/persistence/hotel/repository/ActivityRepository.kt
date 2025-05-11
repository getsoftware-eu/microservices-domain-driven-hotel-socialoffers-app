package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.ActivityDomainEntityId
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface ActivityRepository: JpaRepository<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDbActivity, Long> {

	companion object {

		const val FIND_ALL_BY_HOTEL_QUERY = "SELECT a " +
		"FROM HotelActivity a " +
		"WHERE a.hotel.id = :hotelId " +
		"AND a.active = TRUE "
		;

		const val COUNT_ALL_BY_HOTEL_QUERY = "SELECT count(a) " +
		"FROM HotelActivity a " +
		"WHERE a.hotel.id = :hotelId " +
		"AND a.active = TRUE "
		;

		const val FIND_TIME_VALID_BY_HOTEL_QUERY = "SELECT a " +
		"FROM HotelActivity a " +
		"WHERE a.hotel.id = :hotelId " +
		"AND a.validTo > :checkDate "+
		"AND a.validFrom <= :checkDate "+
		"AND a.active = TRUE " +
		"AND a.hidden = FALSE ";

		const val FIND_TIME_VALID_BY_HOTEL_COUNTER_QUERY = "SELECT count(a) " +
		"FROM HotelActivity a " +
		"WHERE a.hotel.id = :hotelId "+
		"AND a.validTo > :checkDate "+
		"AND a.validFrom <= :checkDate "+
		"AND a.active = TRUE " +
		"AND a.hidden = FALSE ";

		const val FIND_BY_CREATOR_AND_HOTEL_QUERY = "SELECT a " +
		"FROM HotelActivity a " +
		"WHERE a.hotel.id = :hotelId " +
		"AND a.sender.id = :creatorId "+
		"AND a.active = TRUE";

		const val FIND_ALL_ACTIVE_QUERY = "SELECT a " +
		"FROM HotelActivity a " +
		"WHERE a.active = TRUE " +
		"AND a.hotel.active = TRUE ";

		const val FIND_ALL_TIME_VALID_ACTIVE_QUERY = "SELECT a " +
		"FROM HotelActivity a " +
		"WHERE a.validTo > :checkDate "+
		"AND a.validFrom <= :checkDate "+
		"AND a.active = TRUE " +
		"AND a.hidden = FALSE " +
		"AND a.hotel.active = TRUE ";

	}
	/**
	 * Find activity by hotel.
	 */
	@Query(FIND_ALL_BY_HOTEL_QUERY)
	fun getAllTimesByHotelId(@Param("hotelId") hotelId: HotelDomainEntityId): MutableList<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDbActivity>		
	
	/**
	 * count activities by hotel.
	 */
	@Query(COUNT_ALL_BY_HOTEL_QUERY)
	fun countAllTimesByHotelId(@Param("hotelId") hotelId: HotelDomainEntityId): Int		
		
	/**
	 * Find activity by hotel.
	 */
	@Query(FIND_TIME_VALID_BY_HOTEL_QUERY)
	fun getTimeValidByHotelId(@Param("hotelId") hotelId: HotelDomainEntityId, @Param("checkDate") checkDate: LocalDate): MutableList<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDbActivity>	
	
	/**
	 * Find activity by hotel.
	 */
	@Query(FIND_TIME_VALID_BY_HOTEL_COUNTER_QUERY)
	fun getTimeValidCounterByHotelId(@Param("hotelId") hotelId: HotelDomainEntityId, @Param("checkDate") checkDate: LocalDate): Int		 
	 
	/**
	 * Find activity by creator and hotel.
	 */
	@Query(FIND_BY_CREATOR_AND_HOTEL_QUERY)
	fun getByCreatorAndHotelId(@Param("creatorId") creatorId: CustomerDomainEntityId, @Param("hotelId") hotelId: HotelDomainEntityId): MutableList<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDbActivity>
	
	//@Query(FIND_BY_ACTIVITY_ID_QUERY)
	fun findByInitIdAndActive(initId: Long, active: Boolean = true): MutableList<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDbActivity>
	
	@Query(FIND_ALL_ACTIVE_QUERY)
	fun findAllTimesActive(): MutableList<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDbActivity>	
	
	@Query(FIND_ALL_TIME_VALID_ACTIVE_QUERY)
	fun findTimeValidActive(@Param("checkDate") checkDate: LocalDate): MutableList<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDbActivity>
	fun findByHotelDomainId(id: HotelDomainEntityId): Optional<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDbActivity> {
		return Optional.empty<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDbActivity>()
	}

	fun findByDomainId(id: ActivityDomainEntityId): Optional<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDbActivity> {
		TODO("Not yet implemented")
	}
}
