package eu.getsoftware.hotelico.hotel.infrastructure.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eu.getsoftware.hotelico.hotel.domain.HotelWallPost;
import eu.getsoftware.hotelico.hotel.model.CustomerEntity;

public interface WallPostRepository extends JpaRepository<HotelWallPost, Long> {

	public final static String FIND_BY_HOTEL_QUERY = "SELECT w " +
			"FROM HotelWallPost w " +
			"WHERE w.hotel.id = :hotelId " +
			"AND w.validUntil > :checkDate";
	
	public final static String FIND_PARTICIPANTS_BY_HOTEL_QUERY = "SELECT DISTINCT w.sender " +
			"FROM HotelWallPost w " +
			"WHERE w.hotel.id = :hotelId " +
			"AND w.validUntil > :checkDate";

	public final static String FIND_MESSAGE_BY_INIT_ID = "SELECT w " +
			"FROM HotelWallPost w " +
			" WHERE w.active = true " +
			" AND w.initId = :initId " ;
//			" AND w.sender.id = :senderId ";
	
	public final static String FIND_MESSAGE_BY_SENDER_INIT_ID = "SELECT w " +
			"FROM HotelWallPost w " +
			" WHERE w.active = true " +
			" AND w.initId = :initId " +
			" AND w.sender.id = :senderId ";
	 
	@Query(FIND_BY_HOTEL_QUERY)
	public List<HotelWallPost> getByHotelId(@Param("hotelId") Long hotelId, @Param("checkDate") Date checkDate);

	@Query(FIND_PARTICIPANTS_BY_HOTEL_QUERY)
	public List<CustomerEntity> getParticipantsByHotelId(@Param("hotelId") Long hotelId, @Param("checkDate") Date checkDate);
	
	@Query(FIND_MESSAGE_BY_INIT_ID)
	List<HotelWallPost> getMessageByInitId(@Param("initId") Long initId);
	
	@Query(FIND_MESSAGE_BY_SENDER_INIT_ID)
	List<HotelWallPost> getMessageBySenderAndInitId(@Param("senderId") Long senderId, @Param("initId") Long initId);
}
