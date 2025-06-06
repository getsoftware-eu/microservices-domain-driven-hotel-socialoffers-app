package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository;

import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.WallPostDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model.HotelWallPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WallPostRepository extends JpaRepository<HotelWallPost, Long> {
	
	//Eugen: direct DTO from Repository
	final static String FIND_MESSAGE_DTO_BY_INIT_ID = "SELECT new eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.WallPostDTO(w.hotelId, w.creationTime, w.timestamp, w.message, w.senderName, w.sendTimeString, w.hotelStaff, w.senderDomainId) " +
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
//			" AND w.sender.id = :senderDomainId ";
	
	final static String FIND_MESSAGE_BY_SENDER_INIT_ID = "SELECT w " +
			"FROM HotelWallPost w " +
			" WHERE w.active = true " +
			" AND w.initId = :initId " +
			" AND w.sender.id = :senderDomainId ";
	 
	@Query(FIND_BY_HOTEL_QUERY)
	List<HotelWallPost> getByHotelId(@Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") LocalDate checkDate);

	@Query(FIND_PARTICIPANTS_BY_HOTEL_QUERY)
	List<CustomerDBEntity> getParticipantsByHotelId(@Param("hotelId") HotelDomainEntityId hotelId, @Param("checkDate") LocalDate checkDate);
	
	@Query(FIND_MESSAGE_BY_INIT_ID)
	List<HotelWallPost> getMessageByInitId(@Param("initId") WallPostDomainEntityId initId);
	
	@Query(FIND_MESSAGE_BY_SENDER_INIT_ID)
	List<HotelWallPost> getMessageBySenderAndInitId(@Param("senderDomainId") CustomerDomainEntityId senderId, @Param("initId") WallPostDomainEntityId initId);
//	@Query(FIND_MESSAGE_DTO_BY_INIT_ID)
//	List<WallPostDTO> getMessageDTOByInitId(@Param("initId") WallPostDomainEntityId initId);
}
