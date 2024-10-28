package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository;

import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelWallPost;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WallPostRepository extends JpaRepository<HotelWallPost, Long> {
	
	//Eugen: direct DTO from Repository
	final static String FIND_MESSAGE_DTO_BY_INIT_ID = "SELECT new eu.getsoftware.hotelico.hotel.infrastructure.dto.HotelWallPostDTO(w.hotelId, w.creationTime, w.timestamp, w.message, w.senderName, w.sendTimeString, w.hotelStaff, w.senderId) " +
			"FROM HotelWallPost w " +
			" WHERE w.active = true " +
			" AND w.initId = :initId " ;
	
	final static String FIND_BY_HOTEL_QUERY = "SELECT w " +
			"FROM HotelWallPost w " +
			"WHERE w.hotel.id = :hotelId " +
			"AND w.validUntil > :checkDate";
	
	final static String FIND_PARTICIPANTS_BY_HOTEL_QUERY = "SELECT DISTINCT w.sender " +
			"FROM HotelWallPost w " +
			"WHERE w.hotel.id = :hotelId " +
			"AND w.validUntil > :checkDate";

	final static String FIND_MESSAGE_BY_INIT_ID = "SELECT w " +
			"FROM HotelWallPost w " +
			" WHERE w.active = true " +
			" AND w.initId = :initId " ;
//			" AND w.sender.id = :senderId ";
	
	final static String FIND_MESSAGE_BY_SENDER_INIT_ID = "SELECT w " +
			"FROM HotelWallPost w " +
			" WHERE w.active = true " +
			" AND w.initId = :initId " +
			" AND w.sender.id = :senderId ";
	 
	@Query(FIND_BY_HOTEL_QUERY)
	List<HotelWallPost> getByHotelId(@Param("hotelId") Long hotelId, @Param("checkDate") Date checkDate);

	@Query(FIND_PARTICIPANTS_BY_HOTEL_QUERY)
	List<CustomerRootEntity> getParticipantsByHotelId(@Param("hotelId") Long hotelId, @Param("checkDate") Date checkDate);
	
	@Query(FIND_MESSAGE_BY_INIT_ID)
	List<HotelWallPost> getMessageByInitId(@Param("initId") Long initId);
	
	@Query(FIND_MESSAGE_BY_SENDER_INIT_ID)
	List<HotelWallPost> getMessageBySenderAndInitId(@Param("senderId") Long senderId, @Param("initId") Long initId);
	@Query(FIND_MESSAGE_DTO_BY_INIT_ID)
	List<WallPostDTO> getMessageDTOByInitId(@Param("initId") Long initId);
}
