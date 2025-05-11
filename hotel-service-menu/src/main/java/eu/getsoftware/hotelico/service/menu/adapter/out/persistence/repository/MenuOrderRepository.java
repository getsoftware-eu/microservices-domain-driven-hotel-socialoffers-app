package eu.getsoftware.hotelico.service.menu.adapter.out.persistence.repository;

import eu.getsoftware.hotelico.clients.common.utils.DealStatus;
import eu.getsoftware.hotelico.service.menu.adapter.out.persistence.model.MenuOrderMappedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MenuOrderRepository extends JpaRepository<MenuOrderMappedEntity, Long> {

	public final static String FIND_BY_CUSTOMER_ID_QUERY = " SELECT o " +
			" FROM MenuOrder o " +
			" WHERE (o.sender.id = :customerId " +
			" 		OR o.guestCustomerId = :customerId) " +
			" AND o.active = true" +
			" AND (o.status in (:statusList)) " +
			" AND (:checkDateFrom is NULL OR o.validTo >= :checkDateFrom) " +
			" AND (:checkDateTo is NULL OR o.validFrom <= :checkDateTo) ";
	
	public final static String FIND_BY_HOTEL_ID_QUERY = " SELECT o " +
			" FROM MenuOrder o " +
			" WHERE o.hotel.id = :hotelId " +
			" AND o.active = true " +
			" AND (o.status in (:statusList)) " +
			" AND (:checkDateFrom is NULL OR o.validTo >= :checkDateFrom) " +
			" AND (:checkDateTo is NULL OR o.validFrom <= :checkDateTo) ";
	
	public final static String FIND_WAITING_TO_ROOM_BY_HOTEL_ID_QUERY = " SELECT o " +
			" FROM MenuOrder o " +
			" WHERE o.hotel.id = :hotelId " +
			" AND o.active = true " +
			" AND o.orderInRoom = true " +
			" AND (o.status in (:statusList)) " +
			" AND (:checkDateFrom is NULL OR o.validTo >= :checkDateFrom) " +
			" AND (:checkDateTo is NULL OR o.validFrom <= :checkDateTo) ";
	
	public final static String FIND_BY_INIT_ID_QUERY = """
			SELECT o.id
			FROM MenuOrder o
			WHERE o.initId = :initId
			   AND o.active = true
	""";
	
	@Query(FIND_BY_CUSTOMER_ID_QUERY)
	List<MenuOrderMappedEntity> getActiveIdByCustomerId(@Param("customerId") Long customerId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") Date checkDateFrom, @Param("checkDateTo") Date checkDateTo);		
	
	@Query(FIND_BY_HOTEL_ID_QUERY)
	List<MenuOrderMappedEntity> getActiveByHotelId(@Param("hotelId") Long hotelId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") Date checkDateFrom, @Param("checkDateTo") Date checkDateTo);		
	
	@Query(FIND_WAITING_TO_ROOM_BY_HOTEL_ID_QUERY)
	List<MenuOrderMappedEntity> getWaitingToRoomByHotelId(@Param("hotelId") Long hotelId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") Date checkDateFrom, @Param("checkDateTo") Date checkDateTo);
	
	@Query(FIND_BY_INIT_ID_QUERY)
	List<MenuOrderMappedEntity> getMenuByInitId(@Param("initId") Long initId);
}
