package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.menu.dto.MenuItemDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.menu.dto.MenuOrderDTO;
import eu.getsoftware.hotelico.clients.common.utils.ControllerUtils;
import eu.getsoftware.hotelico.clients.common.utils.DealStatus;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.model.MenuItemEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.model.MenuOrder;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.repository.MenuItemRepository;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.repository.MenuOrderRepository;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.application.iPortService.IMenuPortService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
 

/**
 * <br/>
 * Created by e.fanshil
 * At 05.02.2016 15:31
 */
@Service
@RequiredArgsConstructor
public class MenuPortServiceImpl implements IMenuPortService
{
	private final CustomerService customerService;		
	private final LastMessagesService lastMessagesService;		
	private final CheckinService checkinService;	
	private final NotificationService notificationService;		
	private final MenuItemRepository menuItemRepository;		
	private final MenuOrderRepository menuOrderRepository;
	private final ModelMapper modelMapper;
	
	@Override
	public List<MenuOrderDTO> getActiveMenusByCustomerId(CustomerDTO customerDTO, long hotelId, long cafeId, long orderId, boolean closed)
	{
		//TODO Eugen: CHECK get closed menu on demand!!!
		
		//get customerDTO
//		long customerId = ControllerUtils.getTryEntityId(requesterId);
//		CustomerRootEntity customerEntity = customerRepository.getOne(customerId);

		List<MenuOrder> menus = new ArrayList<>();
		
		Date filterDateFrom = closed? null : new Date();
		Date filterDateTo = closed? null : new Date();
		
		List<DealStatus> filterStatusList = DealStatus.getFilterStatusList(closed);
		
		if(orderId>0)
		{
			Optional<MenuOrder> menuOpt = menuOrderRepository.findById(orderId);

			if(menuOpt.isPresent())
			{
				menus.add(menuOpt.get());
			}
			
			if(menus.isEmpty())
			{
				menus = menuOrderRepository.getMenuByInitId(orderId);
			}
		}
		else if(customerDTO !=null && (customerDTO.isAdmin() || customerDTO.isHotelStaff() && hotelId== lastMessagesService.getCustomerHotelId(customerId)))
		{
			menus = menuOrderRepository.getActiveByHotelId(hotelId, filterStatusList, filterDateFrom, filterDateTo);
		}
		else
		{
			menus = menuOrderRepository.getActiveIdByCustomerId(customerDTO.getId(), filterStatusList, filterDateFrom, filterDateTo);
		}
		
		List<MenuOrderDTO> dtoList = new ArrayList<>();
		
		for (MenuOrder nextMenu: menus)
		{
			dtoList.add(convertMenuOrderToDto(nextMenu));
		}
		
		return dtoList;
	}
	
	@Override
	public List<MenuOrderDTO> getAllHotelMenusToRoom(CustomerDTO customerDTO, long hotelId, long cafeId)
	{
//		long customerId = ControllerUtils.getTryEntityId(requesterId);
//		CustomerRootEntity customerEntity = customerId>0? customerRepository.getOne(customerId) : null;

		List<MenuOrder> waitingMenusToRoom = new ArrayList<>();
		
		if(customerDTO !=null && (customerDTO.isAdmin() || customerDTO.isHotelStaff() && hotelId == lastMessagesService.getCustomerHotelId(customerDTO.getId())))
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
	public List<MenuOrderDTO> getActiveMenusByCustomerId(long customerId, long hotelId, long cafeId, long orderId, boolean closed) {
		return null;
	}

	@Transactional
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

	@Override
	public List<MenuOrderDTO> getAllHotelMenusToRoom(long requesterId, long hotelId, long cafeId) {
		return null;
	}

	private MenuOrderDTO convertMenuOrderToDto(MenuOrder menuOrder)
	{
		if(menuOrder == null)
		{
			return null;
		}
		
		MenuOrderDTO dto = modelMapper.map(menuOrder, MenuOrderDTO.class);
		
		if(menuOrder.getHotelRootEntityId() > 0)
		{
			dto.setHotelId(menuOrder.getHotelRootEntityId());
		}
		
		if(menuOrder.getSenderId() > 0)
		{
			dto.setSenderId(menuOrder.getSenderId());
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
		
		MenuItemDTO[] dtoItems = new MenuItemDTO[menuOrder.getMenuItems().size()];
		
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
	
	private MenuItemDTO convertMenuItemToDto(MenuItemEntity menuItem)
	{
		MenuItemDTO dto = modelMapper.map(menuItem, MenuItemDTO.class);

		dto.setHotelId(menuItem.getHotelRootEntity().getId());
		dto.setSenderId(menuItem.getCreator().getId());
		
		if(!menuItem.isActive())
		{
			dto.setAmount(-1);
		}
		return dto;
	}
	
	@Transactional
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
	
	@Transactional
	@Override
	public MenuItemDTO deleteMenuItem(CustomerDTO customerDTO, long menuItemId)
	{
//		long customerId = ControllerUtils.getTryEntityId(requesterId);
//		CustomerRootEntity customerEntity = customerRepository.getOne(customerId);
		
		if(customerDTO !=null && (customerDTO.isAdmin() || customerDTO.isHotelStaff()))
		{
			List<MenuItemEntity> menuItems = menuItemRepository.getByInitId(menuItemId);
			
			MenuItemEntity menuItem = null;
			
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
	
	@Transactional
	@Override
	public MenuItemDTO addUpdateMenuItem(long customerId, long hotelId, long cafeId, long itemId, MenuItemDTO menuItemDto)
	{
		List<MenuItemEntity> menuItems = menuItemRepository.getByInitId(itemId);

		MenuItemEntity menuItem = null;
		
		if(menuItemDto.getId()>=0 || !menuItems.isEmpty())
		{
			menuItem = menuItems.isEmpty()? menuItemRepository.getOne(menuItemDto.getId()) : menuItems.get(0);
		}
		
		if(menuItem==null)
		{
			 menuItem = modelMapper.map(menuItemDto, MenuItemEntity.class);
		}

		fillMenuItemFromDto(menuItem, menuItemDto);

		menuItemRepository.saveAndFlush(menuItem);

		return convertMenuItemToDto(menuItem);
	}

	@Override
	public List<MenuItemDTO> getReorderedMenuItems(long customerId, long hotelId, long cafeId, String reorder)
	{
		List<MenuItemDTO> resultList = new ArrayList<>();
		
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

				MenuItemEntity menuItem = menuItemRepository.getOne(menuItemId);
				
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

	private MenuItemEntity fillMenuItemFromDto(MenuItemEntity menuItem, MenuItemDTO dto)
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
		
		menuItem.setDelimiter(dto.isDelimiter());
		
		if(menuItem.getHotelRootEntityId()<=0)
		{
			menuItem.setHotelRootEntityId(dto.getHotelId());
		}
		
		if(menuItem.getCreatorId()<=0)
		{
			menuItem.setCreatorId(dto.getSenderId());
		}
		
		return menuItem;
	}
	
	@Transactional
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
		HotelRootEntity hotelRootEntity = hotelPortService.getOne(menuOrderDto.getHotelId());
		if(hotelRootEntity !=null)
		{
			menuOrder.setHotelRootEntity(hotelRootEntity);
		}
		
		CustomerRootEntity sender = customerPortService.getById(menuOrderDto.getSenderId());
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
			menuOrder.setMenuItems(new HashSet<MenuItemEntity>());
		}

		DealStatus clientOrderStatus = DealStatus.parseByName(menuOrderDto.getOrderStatus());
		
		if(clientOrderStatus!=null)
		{
			//TODO Eugen: ignore rewrite menu
			if(!(DealStatus.ACCEPTED.equals(clientOrderStatus) && DealStatus.EXECUTED.equals(menuOrder.getStatus()) || DealStatus.CLOSED.equals(menuOrder.getStatus())))
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
				menuOrder.setMenuItems(new HashSet<MenuItemEntity>());
			}

			Set<MenuItemEntity> tempEntityMenuItems = new HashSet<>();
			
			for (int i = 0; i <menuOrderDto.getMenuItems().length ; i++)
			{
				MenuItemDTO nextMenuItem = menuOrderDto.getMenuItems()[i];
				
				if(nextMenuItem.getAmount()>0)
				{
					MenuItemEntity entityItem = menuItemRepository.findByOrderAndInitId(nextMenuItem.getInitId(), menuOrder.getId());
					
					if(entityItem==null)
					{
						entityItem = modelMapper.map(nextMenuItem, MenuItemEntity.class);
					}
				
					entityItem.setMenuOrder(menuOrder);
					
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
			
			ICustomerRootEntity staff = checkinService.getStaffbyHotelId(menuOrderDto.getHotelId());
			
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
	public List<MenuItemDTO> getMenuItemsByHotelId(long customerId, long hotelId, long cafeId)
	{
		List<MenuItemEntity> menuItems = menuItemRepository.getMenuItemsByHotelOrCafeId(hotelId, cafeId);
		
		List<MenuItemDTO> dtoList = new ArrayList<>();
		
		for (MenuItemEntity nextItem: menuItems)
		{
			dtoList.add(convertMenuItemToDto(nextItem));
		}
		
		return dtoList;
	}

	@Override
	public MenuItemDTO deleteMenuItem(long requesterId, long menuItemId) {
		return null;
	}

}
