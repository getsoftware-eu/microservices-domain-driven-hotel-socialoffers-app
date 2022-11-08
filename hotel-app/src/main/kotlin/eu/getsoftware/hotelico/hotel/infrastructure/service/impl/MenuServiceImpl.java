package eu.getsoftware.hotelico.hotel.infrastructure.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.getsoftware.hotelico.customer.infrastructure.repository.CustomerRepository;
import eu.getsoftware.hotelico.customer.infrastructure.service.CustomerService;
import eu.getsoftware.hotelico.hotel.domain.HotelRootEntity;
import eu.getsoftware.hotelico.hotel.infrastructure.repository.HotelRepository;
import eu.getsoftware.hotelico.hotel.infrastructure.repository.MenuItemRepository;
import eu.getsoftware.hotelico.hotel.infrastructure.repository.MenuOrderRepository;
import eu.getsoftware.hotelico.hotel.infrastructure.service.CacheService;
import eu.getsoftware.hotelico.hotel.infrastructure.service.CheckinService;
import eu.getsoftware.hotelico.hotel.infrastructure.service.MenuService;
import eu.getsoftware.hotelico.hotel.infrastructure.service.NotificationService;
import eu.getsoftware.hotelico.hotel.infrastructure.utils.HotelEvent;
import eu.getsoftware.hotelico.hotel.model.CustomerEntity;
import eu.getsoftware.hotelico.infrastructure.utils.ControllerUtils;
import eu.getsoftware.hotelico.infrastructure.utils.DealStatus;
import eu.getsoftware.hotelico.menu.domain.MenuItem;
import eu.getsoftware.hotelico.menu.domain.MenuOrder;
import eu.getsoftware.hotelico.menu.infrastructure.dto.MenuItemDto;
import eu.getsoftware.hotelico.menu.infrastructure.dto.MenuOrderDTO;

/**
 * <br/>
 * Created by e.fanshil
 * At 05.02.2016 15:31
 */
@Service
public class MenuServiceImpl implements MenuService
{
	@Autowired
	private CustomerService customerService;		
	
	@Autowired
	private CacheService cacheService;		
	
	@Autowired
	private CheckinService checkinService;	
	
	@Autowired
	private NotificationService notificationService;		
	
	@Autowired
	private MenuItemRepository menuItemRepository;		
	
	@Autowired
	private CustomerRepository customerRepository;	
	
	@Autowired
	private MenuOrderRepository menuOrderRepository;
	
	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public List<MenuOrderDTO> getActiveMenusByCustomerId(long requesterId, long hotelId, long cafeId, long orderId, boolean closed)
	{
		//TODO Eugen: CHECK get closed menu on demand!!!
		
		long customerId = ControllerUtils.getTryEntityId(requesterId);
		
		CustomerEntity customerEntity = customerRepository.getOne(customerId);

		List<MenuOrder> menus = new ArrayList<>();
		
		Date filterDateFrom = closed? null : new Date();
		Date filterDateTo = closed? null : new Date();
		
		List<DealStatus> filterStatusList = DealStatus.getFilterStatusList(closed);
		
		if(orderId>0)
		{
			MenuOrder menu = menuOrderRepository.getOne(orderId);

			if(menu!=null)
			{
				menus.add(menu);
			}
			
			if(menus.isEmpty())
			{
				menus = menuOrderRepository.getMenuByInitId(orderId);
			}
		}
		else if(customerEntity !=null && (customerEntity.isAdmin() || customerEntity.isHotelStaff() && hotelId==cacheService.getCustomerHotelId(customerId)))
		{
			menus = menuOrderRepository.getActiveByHotelId(hotelId, filterStatusList, filterDateFrom, filterDateTo);
		}
		else
		{
			menus = menuOrderRepository.getActiveIdByCustomerId(customerId, filterStatusList, filterDateFrom, filterDateTo);
		}
		
		List<MenuOrderDTO> dtoList = new ArrayList<>();
		
		for (MenuOrder nextMenu: menus)
		{
			dtoList.add(convertMenuOrderToDto(nextMenu));
		}
		
		return dtoList;
	}
	
	@Override
	public List<MenuOrderDTO> getAllHotelMenusToRoom(long requesterId, long hotelId, long cafeId)
	{
		long customerId = ControllerUtils.getTryEntityId(requesterId);
		
		CustomerEntity customerEntity = customerId>0? customerRepository.getOne(customerId) : null;

		List<MenuOrder> waitingMenusToRoom = new ArrayList<>();
		
		if(customerEntity !=null && (customerEntity.isAdmin() || customerEntity.isHotelStaff() && hotelId == cacheService.getCustomerHotelId(customerId)))
		{
			List<DealStatus> statusList =  new ArrayList<>();
			//ONLY ACCEPTED
			statusList.add(DealStatus.ACCEPTED);
			waitingMenusToRoom = menuOrderRepository.getWaitingToRoomByHotelId(hotelId, statusList, new Date(), new Date());
		}
		
		List<MenuOrderDTO> dtoList = new ArrayList<>();
		
		for (MenuOrder nextMenu: waitingMenusToRoom)
		{
			dtoList.add(convertMenuOrderToDto(nextMenu));
		}
		
		return dtoList;
	}
	
	@Override
	public MenuOrderDTO addMenuAction(long requesterId, long initMenuOrderId, String action)
	{
		long customerId = ControllerUtils.getTryEntityId(requesterId);
		
		List<MenuOrder> menuOrders = menuOrderRepository.getMenuByInitId(initMenuOrderId);
		
		MenuOrder menuOrder = null;

		if(menuOrders.isEmpty())
		{
			menuOrder = menuOrderRepository.getOne(initMenuOrderId);
		}
		
		if(!menuOrders.isEmpty())
		{
			menuOrder = menuOrders.get(0);
		}
		
		return convertMenuOrderToDto(menuOrder);
	}
	
	private MenuOrderDTO convertMenuOrderToDto(MenuOrder menuOrder)
	{
		//TODO Eugen:
		
		if(menuOrder == null)
		{
			return null;
		}
		
		MenuOrderDTO dto = modelMapper.map(menuOrder, MenuOrderDTO.class);
		
		if(menuOrder.getHotelRootEntity()!=null)
		{
			dto.setHotelId(menuOrder.getHotelRootEntity().getId());
		}
		
		if(menuOrder.getSender()!=null)
		{
			dto.setSenderId(menuOrder.getSender().getId());
		}
		
		if(DealStatus.REJECTED.equals(menuOrder.getStatus()))
		{
			dto.setActive(false);
		}
		
		boolean closed = Arrays.asList(DealStatus.REJECTED, DealStatus.CLOSED).contains(menuOrder.getStatus());
		
		dto.setClosed(closed);
		
		if(menuOrder.getStatus()!=null)
		{
			dto.setOrderStatus(menuOrder.getStatus().getName());
		}
		
//		dto.getTotalMoney(menuOrder.get);
		
		MenuItemDto[] dtoItems = new MenuItemDto[menuOrder.getMenuItems().size()];
		
//		if(menuOrder.getMenuItems().size()>0)
//		{
			dto.setItemAmount(menuOrder.getMenuItems().size());
			
//			int counter = 0;
//			
//			for (MenuItem nextItem: menuOrder.getMenuItems())
//			{
//				dtoItems[counter] = convertMenuItemToDto(nextItem);
//				counter++;
//			}
//			
//			dto.setMenuItems(dtoItems);
//		}
		
		
		
		return dto;
	}	
	
	private MenuItemDto convertMenuItemToDto(MenuItem menuItem)
	{
		//TODO Eugen:
		
		MenuItemDto dto = modelMapper.map(menuItem, MenuItemDto.class);

		dto.setHotelId(menuItem.getHotelRootEntity().getId());
		dto.setSenderId(menuItem.getCreator().getId());
		
		if(!menuItem.isActive())
		{
			dto.setAmount(-1);
		}
		return dto;
	}
	
	@Override
	public MenuOrderDTO deleteMenuOrder(long requesterId, long initMenuOrderId)
	{
		long customerId = ControllerUtils.getTryEntityId(requesterId);
		
		List<MenuOrder> menuOrders = menuOrderRepository.getMenuByInitId(initMenuOrderId);

		MenuOrder menuOrder = null;
		
		if(menuOrders.isEmpty())
		{
			menuOrder = menuOrderRepository.getOne(initMenuOrderId);
		}
		
		if(!menuOrders.isEmpty())
		{
			menuOrder = menuOrders.get(0);
			
			menuOrder.setActive(false);
			
			menuOrderRepository.saveAndFlush(menuOrder);
			
			return convertMenuOrderToDto(menuOrder);
		}
		
		return null;
	}	
	
	@Override
	public MenuItemDto deleteMenuItem(long requesterId, long menuItemId)
	{
		
		long customerId = ControllerUtils.getTryEntityId(requesterId);
		
		CustomerEntity customerEntity = customerRepository.getOne(customerId);
		
		if(customerEntity !=null && (customerEntity.isAdmin() || customerEntity.isHotelStaff()))
		{

			List<MenuItem> menuItems = menuItemRepository.getByInitId(menuItemId);
			
			MenuItem menuItem = null;
			
			if(menuItems.isEmpty() || menuItemId>0)
			{
				menuItem = menuItems.isEmpty()? menuItemRepository.getOne(menuItemId) : menuItems.get(0);
			}
			
			if(menuItem!=null && (customerEntity.isAdmin() || menuItem.getHotelRootEntity().getId() == (customerService.getCustomerHotelId(customerId))))
			{
				menuItem.setActive(false);
				
				menuItemRepository.saveAndFlush(menuItem);
				
				return convertMenuItemToDto(menuItem);
			}
		}
		
		return null;
	}
	
	@Override
	public MenuItemDto addUpdateMenuItem(long customerId, long hotelId, long cafeId, long itemId, MenuItemDto menuItemDto)
	{
		List<MenuItem> menuItems = menuItemRepository.getByInitId(itemId);

		MenuItem menuItem = null;
		
		if(menuItemDto.getId()>=0 || !menuItems.isEmpty())
		{
			menuItem = menuItems.isEmpty()? menuItemRepository.getOne(menuItemDto.getId()) : menuItems.get(0);
		}
		
		if(menuItem==null)
		{
			 menuItem = modelMapper.map(menuItemDto, MenuItem.class);
		}

		fillMenuItemFromDto(menuItem, menuItemDto);

		menuItemRepository.saveAndFlush(menuItem);

		return convertMenuItemToDto(menuItem);
	}

	@Override
	public List<MenuItemDto> getReorderedMenuItems(long customerId, long hotelId, long cafeId, String reorder)
	{
		List<MenuItemDto> resultList = new ArrayList<>();
		
		if(ControllerUtils.isEmptyString(reorder))
		{
			return resultList;
		}

		String[] itemReorders = reorder.split("#");

		for (int i = 0; i < itemReorders.length; i++)
		{
			String nextReorder = itemReorders[i];

			String[] split = nextReorder.split(">");
			
			if(split.length>1)
			{
				long menuItemId = Integer.parseInt(split[0]);
				
				int newOrderIndex = Integer.parseInt(split[1]);

				MenuItem menuItem = menuItemRepository.getOne(menuItemId);
				
				if(menuItem!=null && menuItem.getOrderIndex()!=newOrderIndex)
				{
					menuItem.setOrderIndex(newOrderIndex);

					menuItemRepository.saveAndFlush(menuItem);
					
					resultList.add(convertMenuItemToDto(menuItem));
				}
			}
		}
		
		return resultList;
	}

	private MenuItem fillMenuItemFromDto(MenuItem menuItem, MenuItemDto dto)
	{
		if(menuItem==null || dto == null)
		{
			return menuItem;
		}
		
		menuItem.setConsistencyId(new Date().getTime());

		menuItem.setTitle(dto.getTitle());
		menuItem.setAmount(dto.getAmount());
		menuItem.setDescription(dto.getDescription());
		menuItem.setShortDescription(dto.getShortDescription());
		menuItem.setPrice(dto.getPrice());
		menuItem.setOrderIndex(dto.getOrderIndex());
		
		menuItem.setDelimiter(dto.getDelimiter());
		
		if(menuItem.getHotelRootEntity()==null)
		{
			HotelRootEntity hotelRootEntity = hotelRepository.getOne(dto.getHotelId());
			menuItem.setHotelRootEntity(hotelRootEntity);
		}
		
		if(menuItem.getCreator()==null)
		{
			CustomerEntity sender = customerRepository.getOne(dto.getSenderId());
			menuItem.setCreator(sender);
		}
		
		return menuItem;
	}
	
	@Override
	public MenuOrderDTO addUpdateMenu(long customerId, long initMenuOrderId, MenuOrderDTO menuOrderDto)
	{
		List<MenuOrder> menuOrders = menuOrderRepository.getMenuByInitId(initMenuOrderId);
		
		MenuOrder menuOrder = null;
		
		if(menuOrders.isEmpty())
		{
			menuOrder = menuOrderRepository.getOne(initMenuOrderId);
		}
		
		if(!menuOrders.isEmpty())
		{
			menuOrder = menuOrders.get(0);
		}
		
		if(menuOrder==null)
		{
			menuOrder = modelMapper.map(menuOrderDto, MenuOrder.class);
			
			menuOrder.setValidFrom(new Date());
			menuOrder.setValidTo(ControllerUtils.convertToDate(LocalDateTime.now().withHour(0).withMinute(0).plusDays(1).plusHours(3)));
			menuOrder.setStatus(DealStatus.ACCEPTED);

			menuOrder.setTimestamp(new Timestamp(new Date().getTime()));
		}
		
		//TODO Eugen: action
		HotelRootEntity hotelRootEntity = hotelRepository.getOne(menuOrderDto.getHotelId());
		if(hotelRootEntity !=null)
		{
			menuOrder.setHotelRootEntity(hotelRootEntity);
		}
		
		CustomerEntity sender = customerRepository.getOne(menuOrderDto.getSenderId());
		if(sender!=null)
		{
			menuOrder.setSender(sender);
		}
		else{
			menuOrder.setGuestCustomerId(menuOrderDto.getSenderId());
		}
		
		menuOrder.setConsistencyId(new Date().getTime());
		
		if(menuOrder.getInitId()<=0)
		{
			menuOrder.setInitId(new Date().getTime());

			menuOrder.setOrderCode(ControllerUtils.generateCode());
		}
		
//		if(initMenuOrderId<=0)
		{
			menuOrder.setMenuItems(new HashSet<MenuItem>());
		}

		DealStatus clientOrderStatus = DealStatus.parseByName(menuOrderDto.getOrderStatus());
		
		if(clientOrderStatus!=null)
		{
			//TODO Eugen: ignore rewrite menu
			if(DealStatus.ACCEPTED.equals(clientOrderStatus) && DealStatus.EXECUTED.equals(menuOrder.getStatus()) || DealStatus.CLOSED.equals(menuOrder.getStatus()))
			{
				;
			}
			else 
			{
				menuOrder.setStatus(clientOrderStatus);
			}
		}
		
		if(initMenuOrderId>0 && !ControllerUtils.isEmptyString(menuOrderDto.getCustomerComment()))
		{
			menuOrder.setCustomerComment(menuOrderDto.getCustomerComment() + (!menuOrderDto.getCustomerComment().equalsIgnoreCase(menuOrder.getCustomerComment()) && !ControllerUtils.isEmptyString(menuOrder.getCustomerComment())? " | old: " +menuOrder.getCustomerComment() : "" ));
		}
		
		if(DealStatus.REJECTED.equals(menuOrder.getStatus()))
		{
			menuOrder.setActive(false);
		}
		
		if(menuOrder.getTotalMoney()==null || menuOrder.getTotalMoney()<menuOrder.getTotalPrice())
		{
			menuOrder.setTotalMoney(menuOrder.getTotalPrice());
		}

		if(menuOrder.getTotalMoney()==null || menuOrderDto.getTotalMoney()!=null && menuOrder.getTotalMoney()<menuOrderDto.getTotalMoney())
		{
			menuOrder.setTotalMoney(menuOrderDto.getTotalMoney());
		}
		
		menuOrderRepository.saveAndFlush(menuOrder);
		
		if(menuOrderDto.getMenuItems().length>0) // && initMenuOrderId<=0)
		{
			if(menuOrder.getMenuItems()==null)
			{
				menuOrder.setMenuItems(new HashSet<MenuItem>());
			}

			Set<MenuItem> tempEntityMenuItems = new HashSet<>();
			
			for (int i = 0; i <menuOrderDto.getMenuItems().length ; i++)
			{
				MenuItemDto nextMenuItem = menuOrderDto.getMenuItems()[i];
				
				if(nextMenuItem.getAmount()>0)
				{
					MenuItem entityItem = menuItemRepository.findByOrderAndInitId(nextMenuItem.getInitId(), menuOrder.getId());
					
					if(entityItem==null)
					{
						entityItem = modelMapper.map(nextMenuItem, MenuItem.class);
					}
				
					entityItem.setMenuOrder(menuOrder);
					//TODO fill entity from dto????
					//TODO Persist item????
					
					menuItemRepository.saveAndFlush(entityItem);

					tempEntityMenuItems.add(entityItem);
				}
			}

			menuOrder.setMenuItems(tempEntityMenuItems);
		}
		
		notificationService.sendNotificationToCustomerOrGuest(null, menuOrderDto.getSenderId(), HotelEvent.EVENT_MENU_NEW_UPDATE);
		
		
		if(DealStatus.ACCEPTED.equals(clientOrderStatus))
		{
			//TODO Eugen: notificate STAFF about menu??? if orderedToRoom
			
			CustomerEntity staff = checkinService.getStaffbyHotelId(menuOrderDto.getHotelId());
			
			//Notificate STAFF about deal action!!!
			if(staff != null)
			{
				notificationService.sendNotificationToCustomerOrGuest(null, staff.getId(), HotelEvent.EVENT_MENU_NEW_UPDATE);
			}
		}
		
		menuOrderRepository.saveAndFlush(menuOrder);
		
		return convertMenuOrderToDto(menuOrder);
	}
	
	@Override
	public List<MenuItemDto> getMenuItemsByHotelId(long customerId, long hotelId, long cafeId)
	{
		List<MenuItem> menuItems = menuItemRepository.getMenuItemsByHotelOrCafeId(hotelId, cafeId);
		
		List<MenuItemDto> dtoList = new ArrayList<>();
		
		for (MenuItem nextItem: menuItems)
		{
			dtoList.add(convertMenuItemToDto(nextItem));
		}
		
		return dtoList;
	}	
	
}
