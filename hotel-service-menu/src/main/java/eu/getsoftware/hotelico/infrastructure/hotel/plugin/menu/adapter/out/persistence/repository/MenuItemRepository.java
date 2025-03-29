package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.repository;

import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.model.MenuItemMappedEntity;
import jakarta.persistence.metamodel.Attribute;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * eu: from DDD example
 */

@RepositoryRestResource
public interface MenuItemRepository extends JpaRepository<MenuItemMappedEntity, Long> {

	@RestResource(exported = false)
	@Query("select p from MenuItemMappedEntity p")
	Stream<MenuItemMappedEntity> streamAll();

	@RestResource(exported = false)
	List<MenuItemMappedEntity> findByRefPriceNull();

	@RestResource(exported = false)
	List<MenuItemMappedEntity> findByLastModifiedAtBefore(@Param("lastModifiedAt") LocalDateTime lastModifiedAt);

	@RestResource(rel = "find-by-sku", path = "find-by-sku")
	Optional<MenuItemMappedEntity> findBySku(@Param("sku") String sku);

	@RestResource(exported = false)
	@Query("Select distinct value(a) from MenuItemMappedEntity p join p.attrs a " +
			"where key(a).namespace = :namespace " +
			"and key(a).name = :attributeName " +
			"and value(a).attributeType = com.epages.entity.attributes.AttributeType.STRING " +
			"and value(a).stringValue like concat(:query, '%')")
	List<Attribute> findStringAttributeValuesByNameAndSearchQuery(@Param("namespace") String namespace,
																  @Param("attributeName") String attributeName,
																  @Param("query") String query, Pageable pageable);

	@RestResource(exported = false)
	@Query("Select count(p) from MenuItemMappedEntity p join p.attrs a " +
			"where key(a).namespace = :namespace " +
			"and key(a).name = :name ")
	int countByAttributeNamespaceAndAttributeName(@Param("namespace") String namespace,
												  @Param("name") String attributeName);

	@RestResource(exported = false)
	@Query("Select count(p) from MenuItemMappedEntity p where p.sku = :sku")
	int countBySku(@Param("sku") String sku);
	
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
	public List<MenuItemMappedEntity> findByOrderId(@Param("orderId") long orderId);
	
	@Query(FIND_BY_HOTEL_OR_CAFE_ID_QUERY)
	public List<MenuItemMappedEntity> getMenuItemsByHotelOrCafeId(@Param("hotelId") Long hotelId, @Param("cafeId") long cafeId);
	
	@Query(FIND_BY_INIT_ID_QUERY)
	 List<MenuItemMappedEntity> getByInitId(@Param("initId") long initId);
	
	@Query(FIND_BY_ORDER_ID_AND_ITEM_INIT_ID_QUERY)
    MenuItemMappedEntity findByOrderAndInitId(@Param("initId") long initId, @Param("orderId") long orderId);
}
