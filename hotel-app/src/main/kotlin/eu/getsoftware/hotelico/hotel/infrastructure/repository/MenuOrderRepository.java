package eu.getsoftware.hotelico.hotel.infrastructure.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eu.getsoftware.hotelico.infrastructure.utils.DealStatus;
import eu.getsoftware.hotelico.menu.domain.MenuOrder;

public interface MenuOrderRepository extends JpaRepository<MenuOrder, Long> {

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
	
	public final static String FIND_BY_INIT_ID_QUERY = " SELECT o.id " +
			" FROM MenuOrder o " +
			" WHERE o.initId = :initId " +
			" AND o.active = true";
	
	@Query(FIND_BY_CUSTOMER_ID_QUERY)
	List<MenuOrder> getActiveIdByCustomerId(@Param("customerId") Long customerId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") Date checkDateFrom, @Param("checkDateTo") Date checkDateTo);		
	
	@Query(FIND_BY_HOTEL_ID_QUERY)
	List<MenuOrder> getActiveByHotelId(@Param("hotelId") Long hotelId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") Date checkDateFrom, @Param("checkDateTo") Date checkDateTo);		
	
	@Query(FIND_WAITING_TO_ROOM_BY_HOTEL_ID_QUERY)
	List<MenuOrder> getWaitingToRoomByHotelId(@Param("hotelId") Long hotelId, @Param("statusList") List<DealStatus> statusList, @Param("checkDateFrom") Date checkDateFrom, @Param("checkDateTo") Date checkDateTo);
	
	@Query(FIND_BY_INIT_ID_QUERY)
	List<MenuOrder> getMenuByInitId(@Param("initId") Long initId);
}
