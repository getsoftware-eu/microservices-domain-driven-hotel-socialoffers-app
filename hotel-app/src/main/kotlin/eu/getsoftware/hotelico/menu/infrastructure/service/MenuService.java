package eu.getsoftware.hotelico.menu.infrastructure.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import eu.getsoftware.hotelico.menu.infrastructure.dto.MenuItemDto;
import eu.getsoftware.hotelico.menu.infrastructure.dto.MenuOrderDTO;

/**
 * <br/>
 * Created by e.fanshil
 * At 05.02.2016 15:30
 */
public interface MenuService
{	
	@Transactional
	List<MenuOrderDTO> getActiveMenusByCustomerId(long customerId, long hotelId, long cafeId, long orderId, boolean closed);
	
	@Transactional MenuOrderDTO addMenuAction(long customerId, long initMenuOrderId, String action);

	/**
	 * 
	 * @param requesterId - either customer or staff: staff becomes all hotel orders!!!
	 * @param hotelId
	 * @param cafeId
	 * @return
	 */
	@Transactional
	List<MenuOrderDTO> getAllHotelMenusToRoom(long requesterId, long hotelId, long cafeId);
	
	@Transactional MenuOrderDTO deleteMenuOrder(long requesterId, long initMenuOrderId);
	
	@Transactional MenuOrderDTO addUpdateMenu(long customerId, long initMenuOrderId, MenuOrderDTO menuOrderDto);
	
	@Transactional
	List<MenuItemDto> getMenuItemsByHotelId(long customerId, long hotelId, long cafeId);
	
	@Transactional
	MenuItemDto deleteMenuItem(long requesterId, long menuItemId);

	@Transactional
	MenuItemDto addUpdateMenuItem(long customerId, long hotelId, long cafeId, long itemId, MenuItemDto menuItemDto);
	
	@Transactional
	List<MenuItemDto> getReorderedMenuItems(long customerId, long hotelId, long cafeId, String reorder);
}
