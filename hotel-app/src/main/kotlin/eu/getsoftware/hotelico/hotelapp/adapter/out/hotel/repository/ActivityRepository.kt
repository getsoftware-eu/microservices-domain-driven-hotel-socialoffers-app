package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository;

import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.model.HotelActivity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ActivityRepository: JpaRepository<HotelActivity, Long> {

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
	fun getAllTimesByHotelId(@Param("hotelId") hotelId: Long): MutableList<HotelActivity>		
	
	/**
	 * count activities by hotel.
	 */
	@Query(COUNT_ALL_BY_HOTEL_QUERY)
	fun countAllTimesByHotelId(@Param("hotelId") hotelId: Long): Int		
		
	/**
	 * Find activity by hotel.
	 */
	@Query(FIND_TIME_VALID_BY_HOTEL_QUERY)
	fun getTimeValidByHotelId(@Param("hotelId") hotelId: Long, @Param("checkDate") checkDate: Date): MutableList<HotelActivity>	
	
	/**
	 * Find activity by hotel.
	 */
	@Query(FIND_TIME_VALID_BY_HOTEL_COUNTER_QUERY)
	fun getTimeValidCounterByHotelId(@Param("hotelId") hotelId: Long, @Param("checkDate") checkDate: Date): Int		 
	 
	/**
	 * Find activity by creator and hotel.
	 */
	@Query(FIND_BY_CREATOR_AND_HOTEL_QUERY)
	fun getByCreatorAndHotelId(@Param("creatorId") creatorId: Long, @Param("hotelId") hotelId: Long): MutableList<HotelActivity>
	
	//@Query(FIND_BY_ACTIVITY_ID_QUERY)
	fun findByInitIdAndActive(initId: Long, active: Boolean = true): MutableList<HotelActivity>
	
	@Query(FIND_ALL_ACTIVE_QUERY)
	fun findAllTimesActive(): MutableList<HotelActivity>	
	
	@Query(FIND_ALL_TIME_VALID_ACTIVE_QUERY)
	fun findTimeValidActive(@Param("checkDate") checkDate: Date): MutableList<HotelActivity>
}
