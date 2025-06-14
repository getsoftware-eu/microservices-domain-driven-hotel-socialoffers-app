// path: hotelico/service/booking/adapter/out/persistence/hotel/repository/ActivityRepository.java

package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository;

import eu.getsoftware.hotelico.clients.common.domain.ids.ActivityDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelActivityDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<HotelActivityDBEntity, Long> {

	/**
	 * Найти все активные активности по отелю.
	 */
	@Query(ActivityQueries.FIND_ALL_BY_HOTEL_QUERY)
	List<HotelActivityDBEntity> getAllTimesByHotelId(@Param("hotelId") HotelDomainEntityId hotelId);

	/**
	 * Посчитать активные активности по отелю.
	 */
	@Query(ActivityQueries.COUNT_ALL_BY_HOTEL_QUERY)
	int countAllTimesByHotelId(@Param("hotelId") HotelDomainEntityId hotelId);

	/**
	 * Найти активности, валидные на конкретную дату.
	 */
	@Query(ActivityQueries.FIND_TIME_VALID_BY_HOTEL_QUERY)
	List<HotelActivityDBEntity> getTimeValidByHotelId(@Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") LocalDate checkDate);

	/**
	 * Посчитать количество валидных активностей по дате.
	 */
	@Query(ActivityQueries.FIND_TIME_VALID_BY_HOTEL_COUNTER_QUERY)
	int getTimeValidCounterByHotelId(@Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") LocalDate checkDate);

	/**
	 * Найти активности, созданные конкретным пользователем.
	 */
	@Query(ActivityQueries.FIND_BY_CREATOR_AND_HOTEL_QUERY)
	List<HotelActivityDBEntity> getByCreatorAndHotelId(@Param("creatorId") CustomerDomainEntityId creatorId, @Param("hotelId") HotelDomainEntityId hotelId);

	/**
	 * Поиск по initId и флагу активности.
	 */
	List<HotelActivityDBEntity> findByInitIdAndActive(Long initId, boolean active);

	/**
	 * Найти все активные активности во всех отелях.
	 */
	@Query(ActivityQueries.FIND_ALL_ACTIVE_QUERY)
	List<HotelActivityDBEntity> findAllTimesActive();

	/**
	 * Найти все валидные и активные активности по дате.
	 */
	@Query(ActivityQueries.FIND_ALL_TIME_VALID_ACTIVE_QUERY)
	List<HotelActivityDBEntity> findTimeValidActive(@Param("checkDate") LocalDate checkDate);

	/**
	 * Метод-заглушка, если понадобится использовать custom реализацию.
	 */
	default Optional<HotelActivityDBEntity> findByhotelDomainEntityIdValue(HotelDomainEntityId id) {
		return Optional.empty();
	}

	default Optional<HotelActivityDBEntity> findByDomainEntityIdValue(ActivityDomainEntityId id) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
