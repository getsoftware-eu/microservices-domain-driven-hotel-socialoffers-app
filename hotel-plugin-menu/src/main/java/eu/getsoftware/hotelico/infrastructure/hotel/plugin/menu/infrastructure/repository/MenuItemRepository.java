package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.infrastructure.repository;

import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.domain.model.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MenuItemRepository extends JpaRepository<MenuItemEntity, Long> {

	public final static String FIND_BY_ORDER_ID_QUERY = " SELECT m " +
			" FROM MenuItem m " +
			" WHERE m.menuOrder IS NOT NULL " +
			" AND m.menuOrder.id = :orderId " +
			" AND m.active = true";		
	
	public final static String FIND_BY_INIT_ID_QUERY = " SELECT m " +
			" FROM MenuItem m " +
			" WHERE m.initId = :initId " +
			" AND m.active = true " +
			" AND m.menuOrder IS NULL ";
	
	public final static String FIND_BY_ORDER_ID_AND_ITEM_INIT_ID_QUERY = " SELECT m " +
			" FROM MenuItem m " +
			" WHERE m.initId = :initId " +
			" AND m.active = true " +
			" AND m.menuOrder.id = :orderId ";
	/**
	 * public available Items
	 */
	public final static String FIND_BY_HOTEL_OR_CAFE_ID_QUERY = " SELECT m " +
			" FROM MenuItem m " +
			" WHERE (m.hotel IS NOT NULL OR m.cafeId >0) " +
			" AND (m.hotel.id = :hotelId OR m.cafeId = :cafeId) " +
			" AND m.active = true " +
			" AND m.menuOrder IS NULL ";
	
	/**
	 * Find language by name.
	 */
	@Query(FIND_BY_ORDER_ID_QUERY)
	public List<MenuItemEntity> findByOrderId(@Param("orderId") long orderId);
	
	@Query(FIND_BY_HOTEL_OR_CAFE_ID_QUERY)
	public List<MenuItemEntity> getMenuItemsByHotelOrCafeId(@Param("hotelId") Long hotelId, @Param("cafeId") long cafeId);
	
	@Query(FIND_BY_INIT_ID_QUERY)
	 List<MenuItemEntity> getByInitId(@Param("initId") long initId);
	
	@Query(FIND_BY_ORDER_ID_AND_ITEM_INIT_ID_QUERY)
	MenuItemEntity findByOrderAndInitId(@Param("initId") long initId, @Param("orderId") long orderId);
}
