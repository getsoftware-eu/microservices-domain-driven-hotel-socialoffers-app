package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.application.iservice;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.menu.dto.MenuItemDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.menu.dto.MenuOrderDTO;

import java.util.List;


/**
 * <br/>
 * Created by e.fanshil
 * At 05.02.2016 15:30
 */
public interface IMenuPortService
{	
	List<MenuOrderDTO> getActiveMenusByCustomerId(long customerId, long hotelId, long cafeId, long orderId, boolean closed);
	
	MenuOrderDTO addMenuAction(long customerId, long initMenuOrderId, String action);

	/**
	 * 
	 * @param requesterId - either customer or staff: staff becomes all hotel orders!!!
	 * @param hotelId
	 * @param cafeId
	 * @return
	 */
	List<MenuOrderDTO> getAllHotelMenusToRoom(long requesterId, long hotelId, long cafeId);
	
	MenuOrderDTO deleteMenuOrder(long requesterId, long initMenuOrderId);
	
	MenuOrderDTO addUpdateMenu(long customerId, long initMenuOrderId, MenuOrderDTO menuOrderDto);
	
	List<MenuItemDTO> getMenuItemsByHotelId(long customerId, long hotelId, long cafeId);
	
	MenuItemDTO deleteMenuItem(long requesterId, long menuItemId);

	MenuItemDTO addUpdateMenuItem(long customerId, long hotelId, long cafeId, long itemId, MenuItemDTO menuItemDto);
	
	List<MenuItemDTO> getReorderedMenuItems(long customerId, long hotelId, long cafeId, String reorder);
}
