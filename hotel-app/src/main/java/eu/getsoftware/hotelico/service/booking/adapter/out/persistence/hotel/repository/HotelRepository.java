package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository;

import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model.HotelDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<HotelDBEntity, Long> {

//	String FIND_ACTIVITY_NUMBER_BY_HOTEL_QUERY = "SELECT (h.hotelActivities.size) " +
//			"FROM Hotel h " +
//			"WHERE h.active = TRUE " +
//			"AND h.id = :hotelId";

	String FIND_VIRTUAL_HOTEL_ID_QUERY = "SELECT (h.id) " +
			"FROM Hotel h " +
			"WHERE h.active = TRUE " +
			"AND h.virtual = TRUE";

	String FIND_VIRTUAL_HOTEL_CODE_QUERY = "SELECT (h.currentHotelAccessCode) " +
			"FROM Hotel h " +
			"WHERE h.active = TRUE " +
			"AND h.virtual = TRUE";

	String FIND_DEMO_HOTEL_ID_QUERY = "SELECT (h.domainEntityId) " +
			"FROM Hotel h " +
			"WHERE h.active = TRUE " +
			"AND h.currentHotelAccessCode = 'demo'";

	String FIND_DEMO_HOTEL_DOMAIN_ID_QUERY = "SELECT (h.domainEntityId) " +
			"FROM Hotel h " +
			"WHERE h.active = TRUE " +
			"AND h.currentHotelAccessCode = 'demo'";

	String FIND_ACTIVE_GPS_HOTELS_QUERY = "SELECT h " +
			"FROM Hotel h " +
			"WHERE h.active = TRUE " +
			"AND h.address.city IS NOT NULL " + //eu: embedded object address
			"AND h.latitude > -90 " +
			"AND h.longitude > -180";

	// ---------------------- Methods ----------------------

	Optional<HotelDBEntity> findByCurrentHotelAccessCodeAndActive(String currentHotelAccessCode, boolean active);

	Optional<HotelDBEntity> findByDomainEntityIdAndActive(HotelDomainEntityId domainEntityId, boolean active);

//	@Query(FIND_ACTIVITY_NUMBER_BY_HOTEL_QUERY)
//	int getActivityCounter(@Param("hotelId") int hotelId);

	@Query(FIND_VIRTUAL_HOTEL_ID_QUERY)
	HotelDomainEntityId getVirtualHotelId();

	List<HotelDBEntity> findByVirtualAndActive(boolean virtual, boolean active);

	@Query(FIND_VIRTUAL_HOTEL_CODE_QUERY)
	String getVirtualHotelCode();

	List<HotelDBEntity> findByCreationTimeAndActive(long creationTime, boolean active);

	@Query(FIND_DEMO_HOTEL_ID_QUERY)
	HotelDomainEntityId getDemoHotelId();

	@Query(FIND_DEMO_HOTEL_DOMAIN_ID_QUERY)
	HotelDomainEntityId getDemoHotelDomainId();

	@Query(FIND_ACTIVE_GPS_HOTELS_QUERY)
	List<HotelDBEntity> findActiveGpsHotels();

	List<HotelDBEntity> findByEmailAndActive(String email, boolean active);
}