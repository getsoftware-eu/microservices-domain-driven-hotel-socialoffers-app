package eu.getsoftware.hotelico.service.menu.adapter.out.persistence.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.menu.MenuItemDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.menu.MenuOrderDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.clients.common.utils.DealStatus;
import eu.getsoftware.hotelico.service.menu.adapter.out.persistence.model.MenuItemMappedEntity;
import eu.getsoftware.hotelico.service.menu.adapter.out.persistence.model.MenuOrderMappedEntity;
import eu.getsoftware.hotelico.service.menu.adapter.out.persistence.repository.MenuItemRepository;
import eu.getsoftware.hotelico.service.menu.adapter.out.persistence.repository.MenuOrderRepository;
import eu.getsoftware.hotelico.service.menu.application.port.out.IMenuPortService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
	private final MenuItemRepository menuItemRepository;		
	private final MenuOrderRepository menuOrderRepository;

	private final ModelMapper modelMapper;


	@Override
	public List<MenuOrderDTO> getActiveMenusByCustomerId(long customerId, long hotelId, long cafeId, long orderId, boolean closed)
	{
		//TODO Eugen: CHECK get closed menu on demand!!!

		//get customerDTO
//		long customerId = ControllerUtils.getTryEntityId(requesterId);
		//TODO: how get CustomerDTO : from localDB(viewEntity filled by eventSourcing?+) or from (other asynchron microservice request?-)
		//CustomerDTO customerDTO = customerService.getCustomerById(customerId);

		List<MenuOrderMappedEntity> menus = new ArrayList<>();

		Date filterDateFrom = closed? null : new Date();
		Date filterDateTo = closed? null : new Date();

		List<DealStatus> filterStatusList = DealStatus.getFilterStatusList(closed);

		if(orderId>0)
		{
			Optional<MenuOrderMappedEntity> menuOpt = menuOrderRepository.findById(orderId);

			if(menuOpt.isPresent())
			{
				menus.add(menuOpt.get());
			}

			if(menus.isEmpty())
			{
				menus = menuOrderRepository.getMenuByInitId(orderId);
			}
		}
		else if(hotelId>0) //if(customerDTO !=null && (customerDTO.isAdmin() || customerDTO.isHotelStaff() && hotelId== lastMessagesService.getCustomerHotelId(customerId)))
		{
			menus = menuOrderRepository.getActiveByHotelId(hotelId, filterStatusList, filterDateFrom, filterDateTo);
		}
		else
		{
			menus = menuOrderRepository.getActiveIdByCustomerId(customerId, filterStatusList, filterDateFrom, filterDateTo);
		}

		List<MenuOrderDTO> dtoList = new ArrayList<>();

		for (MenuOrderMappedEntity nextMenu: menus)
		{
			dtoList.add(convertMenuOrderToDto(nextMenu));
		}

		return dtoList;
	}

	@Override
	public MenuOrderDTO addMenuAction(long customerId, long initMenuOrderId, String action) {
		return null;
	}

	@Override
	public List<MenuOrderDTO> getAllHotelMenusToRoom(CustomerDTO requesterDTO, long hotelId, long cafeId) {
		return List.of();
	}

	@Override
	public MenuOrderDTO deleteMenuOrder(long requesterId, long initMenuOrderId) {
		return null;
	}

	//	@Override
	public List<MenuOrderDTO> getAllHotelMenusToRoom(long customerId, long hotelId, long cafeId, boolean isAdminOrStaff)
	{
//		long customerId = ControllerUtils.getTryEntityId(requesterId);
//		CustomerRootEntity customerEntity = customerId>0? customerRepository.getOne(customerId) : null;

		List<MenuOrderMappedEntity> waitingMenusToRoom = new ArrayList<>();

		if(isAdminOrStaff && hotelId == getCustomerActualHotelId(customerId))
		{
			List<DealStatus> statusList =  new ArrayList<>();
			//ONLY ACCEPTED
			statusList.add(DealStatus.ACCEPTED);
			waitingMenusToRoom = menuOrderRepository.getWaitingToRoomByHotelId(hotelId, statusList, new Date(), new Date());
		}

		List<MenuOrderDTO> dtoList = new ArrayList<>();

		for (MenuOrderMappedEntity nextMenu: waitingMenusToRoom)
		{
			dtoList.add(convertMenuOrderToDto(nextMenu));
		}

		return dtoList;
	}

	private long getCustomerActualHotelId(long customerId) {

		//TODO get customer and check hotelId

		return 1;
	}


	@Transactional
//	@Override
	public MenuOrderDTO addMenuAction(CustomerDomainEntityId requesterId, long initMenuOrderId, String action)
	{
        CustomerDomainEntityId customerId = AppConfigProperties.getTryEntityId(requesterId);

		List<MenuOrderMappedEntity> menuOrders = menuOrderRepository.getMenuByInitId(initMenuOrderId);

        MenuOrderMappedEntity menuOrder = null;

		if(menuOrders.isEmpty())
		{
			menuOrder = menuOrderRepository.findById(initMenuOrderId).orElseThrow(() -> new RuntimeException("..."));
		}

		if(!menuOrders.isEmpty())
		{
			menuOrder = menuOrders.get(0);
		}

		return convertMenuOrderToDto(menuOrder);
	}



	private MenuOrderDTO convertMenuOrderToDto(MenuOrderMappedEntity menuOrder)
	{
//		if(menuOrder == null)
//		{
//			return null;
//		}
//
		MenuOrderDTO dto = modelMapper.map(menuOrder, MenuOrderDTO.class);
//
//		if(menuOrder.getHotelRootEntityId() > 0)
//		{
//			dto = dto.withHotelId(menuOrder.getHotelRootEntityId());
//		}
//
//		if(menuOrder.getSenderId() > 0)
//		{
//			dto = dto.withSenderId(menuOrder.getSenderId());
//		}
//
//		if(DealStatus.REJECTED.equals(menuOrder.getStatus()))
//		{
//			dto.setActive(false);
//		}
//
//		boolean closed = Arrays.asList(DealStatus.REJECTED, DealStatus.CLOSED).contains(menuOrder.getStatus());
//
//		dto = dto.withClosed(closed);
//
//		if(menuOrder.getStatus()!=null)
//		{
//			dto = dto.withOrderStatus(menuOrder.getStatus().getName());
//		}
//
////		dto.getTotalMoney(menuOrder.get);
//
//		MenuItemDTO[] dtoItems = new MenuItemDTO[menuOrder.getMenuItems().size()];
//
////		if(menuOrder.getMenuItems().size()>0)
////		{
//			dto = dto.withItemAmount(menuOrder.getMenuItems().size());
//
////			int counter = 0;
////			
////			for (MenuItem nextItem: menuOrder.getMenuItems())
////			{
////				dtoItems[counter] = convertMenuItemToDto(nextItem);
////				counter++;
////			}
////			
////			dto.setMenuItems(dtoItems);
////		}

		return dto;
	}

	private MenuItemDTO convertMenuItemToDto(MenuItemMappedEntity menuItem)
	{
		MenuItemDTO dto = modelMapper.map(menuItem, MenuItemDTO.class);

//		dto = dto.withHotelId(menuItem.getHotelRootEntityId());
//		dto = dto.withSenderId(menuItem.getCreatorId());
//
//		if(!menuItem.isActive())
//		{
//			dto = dto.withAmount(-1);
//		}
		return dto;
	}

	@Transactional
//	@Override
	public MenuOrderDTO deleteMenuOrder(CustomerDomainEntityId requesterId, long initMenuOrderId)
	{
        CustomerDomainEntityId customerId = AppConfigProperties.getTryEntityId(requesterId);

		List<MenuOrderMappedEntity> menuOrders = menuOrderRepository.getMenuByInitId(initMenuOrderId);

		MenuOrderMappedEntity menuOrder = null;

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
//	@Override
	public MenuItemDTO deleteMenuItem(boolean isAdminOrStaff, long menuItemId, long customerId)
	{
//		long customerId = ControllerUtils.getTryEntityId(requesterId);
//		CustomerRootEntity customerEntity = customerRepository.getOne(customerId);

		if(isAdminOrStaff)
		{
			List<MenuItemMappedEntity> menuItems = menuItemRepository.getByInitId(menuItemId);

            MenuItemMappedEntity menuItem = null;

			if(menuItems.isEmpty() || menuItemId>0)
			{
				menuItem = menuItems.isEmpty()? menuItemRepository.findById(menuItemId).orElseThrow(()->new RuntimeException("wronf menuItemId")) : menuItems.get(0);
			}

			if(menuItem!=null && (isAdminOrStaff || menuItem.getHotelRootEntityId() == (getCustomerActualHotelId(customerId))))
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
		List<MenuItemMappedEntity> menuItems = menuItemRepository.getByInitId(itemId);

        MenuItemMappedEntity menuItem = null;

		if(menuItemDto.getId()>=0 || !menuItems.isEmpty())
		{
			menuItem = menuItems.isEmpty()? menuItemRepository.findById(menuItemDto.getId()).orElseThrow(()-> new RuntimeException("bla")) : menuItems.get(0);
		}

		if(menuItem==null)
		{
			 menuItem = modelMapper.map(menuItemDto, MenuItemMappedEntity.class);
		}

		fillMenuItemFromDto(menuItem, menuItemDto);

		menuItemRepository.saveAndFlush(menuItem);

		return convertMenuItemToDto(menuItem);
	}

	@Override
	public List<MenuItemDTO> getReorderedMenuItems(long customerId, long hotelId, long cafeId, String reorder)
	{
		List<MenuItemDTO> resultList = new ArrayList<>();

		if(AppConfigProperties.isEmptyString(reorder))
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

                MenuItemMappedEntity menuItem = menuItemRepository.getOne(menuItemId);

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

	private MenuItemMappedEntity fillMenuItemFromDto(MenuItemMappedEntity menuItem, MenuItemDTO dto)
	{
		if(menuItem==null || dto == null)
		{
			return menuItem;
		}

		menuItem.setConsistencyId(System.currentTimeMillis());

//		menuItem.setTitle(dto.getTitle());
//		menuItem.setAmount(dto.getAmount());
//		menuItem.setDescription(dto.getDescription());
//		menuItem.setShortDescription(dto.getShortDescription());
//		menuItem.setPrice(dto.getPrice());
//		menuItem.setOrderIndex(dto.getOrderIndex());
//
//		menuItem.setDelimiter(dto.isDelimiter());
//
//		if(menuItem.getHotelRootEntityId()<=0)
//		{
//			menuItem.setHotelRootEntityId(dto.getHotelId());
//		}
//
//		if(menuItem.getCreatorId()<=0)
//		{
//			menuItem.setCreatorId(dto.getSenderId());
//		}

		return menuItem;
	}

	@Transactional
	@Override
	public MenuOrderDTO addUpdateMenu(long customerId, long initMenuOrderId, MenuOrderDTO menuOrderDto)
	{
		List<MenuOrderMappedEntity> menuOrders = menuOrderRepository.getMenuByInitId(initMenuOrderId);

        MenuOrderMappedEntity menuOrder = null;

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
			menuOrder = modelMapper.map(menuOrderDto, MenuOrderMappedEntity.class);

			menuOrder.setValidFrom(new Date());
			menuOrder.setValidTo(AppConfigProperties.convertToDate(LocalDateTime.now().withHour(0).withMinute(0).plusDays(1).plusHours(3)));
			menuOrder.setStatus(DealStatus.ACCEPTED);

			menuOrder.setTimestamp(new Timestamp(System.currentTimeMillis()));
		}

//		//TODO Eugen: action
//		HotelDTO hotelDTO = hotelPortService.getById(menuOrderDto.getHotelId());
//		if(hotelDTO !=null)
//		{
//			menuOrder.setHotelRootEntity(hotelDTO);
//		}
//
//		CustomerDTO sender = customerPortService.getById(menuOrderDto.getSenderId());
//		if(sender!=null)
//		{
//			menuOrder.setSender(sender);
//		}
//		else{
//			menuOrder.setGuestCustomerId(menuOrderDto.getSenderId());
//		}

		menuOrder.setConsistencyId(System.currentTimeMillis());

		if(menuOrder.getInitId()<=0)
		{
			menuOrder.setInitId(System.currentTimeMillis());

			menuOrder.setOrderCode(AppConfigProperties.generateCode());
		}

//		if(initMenuOrderId<=0)
		{
			menuOrder.setMenuItems(new HashSet<MenuItemMappedEntity>());
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

		if(initMenuOrderId>0 && !AppConfigProperties.isEmptyString(menuOrderDto.getCustomerComment()))
		{
			menuOrder.setCustomerComment(menuOrderDto.getCustomerComment() + (!menuOrderDto.getCustomerComment().equalsIgnoreCase(menuOrder.getCustomerComment()) && !AppConfigProperties.isEmptyString(menuOrder.getCustomerComment())? " | old: " +menuOrder.getCustomerComment() : "" ));
		}

		if(DealStatus.REJECTED.equals(menuOrder.getStatus()))
		{
			menuOrder.setActive(false);
		}

		if(menuOrder.getTotalMoney()==null || menuOrder.getTotalMoney()<menuOrder.getTotalPrice())
		{
			menuOrder.setTotalMoney(menuOrder.getTotalPrice());
		}

		if(menuOrder.getTotalMoney()==null || menuOrder.getTotalMoney()<menuOrderDto.getTotalMoney())
		{
			menuOrder.setTotalMoney(menuOrderDto.getTotalMoney());
		}

		menuOrderRepository.saveAndFlush(menuOrder);

		if(!menuOrderDto.getMenuItems().isEmpty()) // && initMenuOrderId<=0)
		{
			if(menuOrder.getMenuItems()==null)
			{
				menuOrder.setMenuItems(new HashSet<MenuItemMappedEntity>());
			}

			Set<MenuOrderMappedEntity> tempEntityMenuItems = new HashSet<>();

//			for (int i = 0; i <menuOrderDto.getMenuItems().length ; i++)
//			{
//				MenuItemDTO nextMenuItem = menuOrderDto.getMenuItems()[i];
//
//				if(nextMenuItem.getAmount()>0)
//				{
//					MenuOrderMappedEntity entityItem = menuItemRepository.findByOrderAndInitId(nextMenuItem.getInitId(), menuOrder.getId());
//
//					if(entityItem==null)
//					{
//						entityItem = modelMapper.map(nextMenuItem, MenuOrderMappedEntity.class);
//					}
//
//					entityItem.setMenuOrder(menuOrder);
//
//					menuItemRepository.saveAndFlush(entityItem);
//
//					tempEntityMenuItems.add(entityItem);
//				}
//			}

//			menuOrder.setMenuItems(tempEntityMenuItems);
		}

//		notificationService.sendNotificationToCustomerOrGuest(null, menuOrderDto.getSenderId(), HotelEvent.EVENT_MENU_NEW_UPDATE);
//
//		if(DealStatus.ACCEPTED.equals(clientOrderStatus))
//		{
//			//TODO Eugen: notificate STAFF about menu??? if orderedToRoom
//
//			CustomerDTO staff = checkinService.getStaffbyHotelId(menuOrderDto.getHotelId());
//
//			//Notificate STAFF about deal action!!!
//			if(staff != null)
//			{
//				notificationService.sendNotificationToCustomerOrGuest(null, staff.getId(), HotelEvent.EVENT_MENU_NEW_UPDATE);
//			}
//		}
//
//		menuOrderRepository.saveAndFlush(menuOrder);

		return convertMenuOrderToDto(menuOrder);
	}

	@Override
	public List<MenuItemDTO> getMenuItemsByHotelId(long customerId, long hotelId, long cafeId) {
		return List.of();
	}

	@Override
	public MenuItemDTO deleteMenuItem(long requesterId, long menuItemId) {
		return null;
	}

////	@Override
//	public List<MenuItemDTO> getMenuItemsByHotelId(long customerId, long hotelId, long cafeId)
//	{
////		List<MenuItemMappedEntity> menuItems = menuItemRepository.getMenuItemsByHotelOrCafeId(hotelId, cafeId);
////
//		List<MenuItemDTO> dtoList = new ArrayList<>();
////
////		for (MenuItemMappedEntity nextItem: menuItems)
////		{
////			dtoList.add(convertMenuItemToDto(nextItem));
////		}
//
//		return dtoList;
//	}

//	@Override
//	public MenuItemDTO deleteMenuItem(long requesterId, long menuItemId) {
//		return null;
//	}

}
