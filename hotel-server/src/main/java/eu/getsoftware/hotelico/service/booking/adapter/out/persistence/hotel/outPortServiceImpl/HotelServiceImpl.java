package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDealDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.HotelDTO;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.exception.BasicHotelException;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.exception.domain.BusinessException;
import eu.getsoftware.hotelico.clients.common.domain.ids.ActivityDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.WallPostDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.*;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelActivityDBEntity;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.repository.CheckinRepository;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.mapper.CustomerDealDtoMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.mapper.CustomerDealEntityMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.mapper.CustomerDtoMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.repository.CustomerJpaRepository;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.deal.model.CustomerDealDBEntity;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.mapper.*;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model.HotelDBEntity;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model.HotelWallPost;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model.InnerHotelEvent;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository.ActivityRepository;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository.DealRepository;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository.HotelRepository;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository.WallPostRepository;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.out.CheckinOutEntityQueryService;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl.CustomerDealRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.customer.port.out.iPortService.CustomerPortService;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.ResponseDTO;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.customDomainModelImpl.HotelActivityRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.customDomainModelImpl.HotelRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.customDomainModelImpl.HotelWallRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService.IHotelService;
import eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService.INotificationService;
import eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService.LastMessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.geom.Point2D;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements IHotelService<HotelRootDomainEntity>
{
    private final HotelActivityDtoMapperImpl hotelActivityDtoMapperImpl;
    private CustomerPortService<CustomerDBEntity> customerService;
	
	private INotificationService notificationService;	
	
	private CheckinOutEntityQueryService checkinService;
	
	private LastMessagesService lastMessagesService;
	
    private final HotelRepository hotelRepository;     
    
    private CustomerJpaRepository customerRepository;    
    
    private ActivityRepository activityRepository;     
	
    private DealRepository dealRepository; 
    
    private WallPostRepository wallPostRepository;
    
    private CheckinRepository checkinRepository;
	
    
    private SimpMessagingTemplate simpMessagingTemplate;
	
	private HotelDomainEntityId specialHotelId = new HotelDomainEntityId(999+"");
    private CustomerDtoMapper customerDtoMapper;
    private HotelDtoMapper hotelDtoMapper;
    private HotelEntityMapper hotelEntityMapper;
    private CustomerDealEntityMapper customerDealEntityMapper;
    private CustomerDealDtoMapper customerDealDtoMapper;
    private HotelActivityDtoMapper hotelActivityDtoMapper;
    private HotelActivityEntityMapper hotelActivityEntityMapper;
    private HotelWallDtoMapper wallPostDTOMapper;
    private HotelWallEntityMapper wallPostEntityMapper;

    /**
     * eu: single ENTRY POINT TO CREATE DomainEntityChierarchie!!!!
     * @param hotelEntityId
     * @return
     */
    public HotelRootDomainEntity recreateDomainEntityFromDBProjection(HotelDomainEntityId hotelEntityId)
    {
        HotelDBEntity dbProjection = hotelRepository.findByDomainEntityIdValue(hotelEntityId)
                .orElseThrow(() -> new RuntimeException("no hotel"));

        return HotelRootDomainEntity.builder().domainEntityId(dbProjection.getDomainEntityId())
                .description(dbProjection.getDescription())
                .name(dbProjection.getName())
                .build();
    }
    
    
    public List<HotelDTO> getHotels() {
        List<HotelDBEntity> list = hotelRepository.findByVirtualAndActive(false, true);
        List<HotelDTO> out = new ArrayList<HotelDTO>();
        for (HotelDBEntity hotelRootEntity : list) {
            
            boolean ignoreDemoHotel = AppConfigProperties.HIDE_DEMO_HOTEL_FROM_HOTEL_LIST && AppConfigProperties.HOTEL_DEMO_CODE.equalsIgnoreCase(hotelRootEntity.getCurrentHotelAccessCode());

            if(hotelRootEntity.isActive() && !ignoreDemoHotel)
            {
                out.add(convertHotelToDto(hotelRootEntity));
            }
			
        }
        return out;
    }

    @Override
    public List<HotelDTO> getHotelCities(CustomerDomainEntityId requesterId) {
        return null;
    }

    @Override
    public List<HotelDTO> getHotelsByCity(CustomerDomainEntityId customerId, String city) throws BasicHotelException {
        return null;
    }

    public List<HotelDTO> getHotelCities(long requesterId) {
        //TODO Eugen: Query by unique city!!!
        List<HotelDBEntity> list = hotelRepository.findByVirtualAndActive(false, true);
        
        Set<String> citiesList = new HashSet<>();
        
        CustomerDBEntity requester = requesterId>0? customerRepository.getOne(requesterId): null;
        
        List<HotelDTO> out = new ArrayList<HotelDTO>();
        for (HotelDBEntity hotelRootEntity : list) {
            
            boolean ignoreDemoHotel = AppConfigProperties.HIDE_DEMO_HOTEL_FROM_HOTEL_LIST && AppConfigProperties.HOTEL_DEMO_CODE.equalsIgnoreCase(hotelRootEntity.getCurrentHotelAccessCode());
            
            if(hotelRootEntity.isActive() && !hotelRootEntity.isVirtual() && !ignoreDemoHotel && (!citiesList.contains(hotelRootEntity.getAddress().getCity())))
            {
                citiesList.add(hotelRootEntity.getAddress().getCity());
                HotelDTO hotelDto = convertHotelToDto(hotelRootEntity);
                
                if(requester!=null && hotelRootEntity.getLatitude()>-90 && requester.getLatitude()>-90)
                {
                    var domainroot = hotelEntityMapper.toDomain(hotelRootEntity);
                    double kms = getDistanceKmToHotel(new Point2D.Double(requester.getLatitude(), requester.getLongitude()), domainroot);
                    hotelDto.setKmFromMe(kms);
                }
                out.add(hotelDto);
            }
        }
        return out;
    }
    
    public List<HotelDTO> getHotelsByCity(long requesterId, String filterCity) throws BasicHotelException
    {
        //TODO Eugen: Query by unique filterCity!!!
        List<HotelDBEntity> list = hotelRepository.findByVirtualAndActive(false, true);
        
        if(filterCity.equalsIgnoreCase("-1"))
        {
            filterCity = null;
        }
        
        Optional<CustomerDBEntity> requesterOptional = customerRepository.findById(requesterId);

        if(!requesterOptional.isPresent())
        {
            throw new BasicHotelException("Customer not found with id=" + requesterId);	
        }
        
        List<HotelDTO> out = new ArrayList<HotelDTO>();
        for (HotelDBEntity hotelRootEntity : list) {
            
            boolean ignoreDemoHotel = AppConfigProperties.HIDE_DEMO_HOTEL_FROM_HOTEL_LIST && AppConfigProperties.HOTEL_DEMO_CODE.equalsIgnoreCase(hotelRootEntity.getCurrentHotelAccessCode());
           
            if(hotelRootEntity.isActive() && !hotelRootEntity.isVirtual() && !ignoreDemoHotel && (filterCity==null || filterCity.equals(hotelRootEntity.getAddress().getCity())))
            {
                HotelDTO hotelDto = convertHotelToDto(hotelRootEntity);
                if(requesterOptional.isPresent() && hotelRootEntity.getLatitude()>-90 && requesterOptional.get().getLatitude()>-90)
                {
	                CustomerDBEntity requester = requesterOptional.get();
                    var root = hotelEntityMapper.toDomain(hotelRootEntity);
                    double kms = getDistanceKmToHotel(new Point2D.Double(requester.getLatitude(), requester.getLongitude()), root);
                    hotelDto.withKmFromMe(kms);
                }
                out.add(hotelDto);

            }
        }
        return out;
    }
    

    @Override
    public Optional<HotelDBEntity> getEntityById(HotelDomainEntityId hotelId) {
        return hotelRepository.findByDomainEntityIdValue(hotelId);
    }

    @Override
    public Optional getEntityByDomainId(HotelDomainEntityId hotelId) {
        return Optional.empty();
    }


    @Override
    public HotelDTO getHotelByCode(String hotelCode) {

        if(AppConfigProperties.isEmptyString(hotelCode))
        {
	        new IllegalArgumentException("hotelcode is wrong: " + hotelCode);
        }
	
	    HotelDTO dto = hotelRepository.findByCurrentHotelAccessCodeAndActive(hotelCode, true)
			    .map(h -> convertHotelToDto(h))
			    .orElseThrow(() -> new ResourceNotFoundException("hotel not found, hotelCode is wrong: " + hotelCode));
        
        return dto;
    }

    @Override
    public boolean existsHotelByCode(String hotelCode) {
        return false;
    }

    @Override
    public List<CustomerDTO> getWallPostParticipantsByHotelId(CustomerDomainEntityId requesterId, HotelDomainEntityId hotelId)
    {
        List<CustomerDBEntity> participantsList = wallPostRepository.getParticipantsByHotelId(hotelId, LocalDate.now());

        List<CustomerDTO> out = new ArrayList<CustomerDTO>();

        for (CustomerDBEntity nextParticipant : participantsList) {

            out.add(customerService.convertCustomerWithHotelToDto(nextParticipant, hotelId));
        }
        
        return out;
    }

    @Override
    public List<CustomerDealDTO> getDealsByActivityOrHotelId(CustomerDomainEntityId customerId, HotelDomainEntityId hotelId, int activityId, boolean onlyRequesterDeals, boolean showClosed) {
        return null;
    }


    @Override
    public HotelActivityDTO addActivityAction(CustomerDomainEntityId customerId, long activityId, ActivityAction action) throws Throwable {
        CustomerDBEntity sender = customerRepository.findByDomainEntityIdValue(AppConfigProperties.getTryEntityId(customerId))
		        .orElseThrow(()-> new ResourceNotFoundException("customer not found with initId=" + customerId));
        
        HotelActivityDBEntity activity = (HotelActivityDBEntity) this.getActivityByIdOrInitId(activityId, activityId).orElseThrow(()-> new RuntimeException("not found"));
		
        if(activity==null || action==null)
        {
            return new HotelActivityDTO(ActivityDomainEntityId.from(String.valueOf(activityId)));
        }
        
        switch (action)
        {
	        case LIKE:
            {
                Collection<CustomerDomainEntityId> likedBy = activity.getLikedCustomerDomainEntityIds();
                likedBy.add(sender.getDomainEntityId());
                activity.setLikedCustomerDomainEntityIds(likedBy);
                activityRepository.saveAndFlush(activity);
                
                break;
            }
            
	        case DISLIKE:
            {
                Collection<CustomerDomainEntityId> likedBy = activity.getLikedCustomerDomainEntityIds();
                likedBy.remove(sender.getDomainEntityId());
                activity.setLikedCustomerDomainEntityIds(likedBy);
                activityRepository.saveAndFlush(activity);
                
                break;
            }  
			
	        case SUBSCRIBE:
            {
                Set<CustomerDomainEntityId> subscribeBy = activity.getSubscribeCustomerDomainEntityIds();
				subscribeBy.add(sender.getDomainEntityId());
                activity.setSubscribeCustomerDomainEntityIds(subscribeBy);
                activityRepository.saveAndFlush(activity);
                
                break;
            }
            
	        case UNSUBSCRIBE:
            {
                Set<CustomerDomainEntityId> subscribeBy = activity.getSubscribeCustomerDomainEntityIds();
				subscribeBy.remove(sender.getDomainEntityId());
                activity.setSubscribeCustomerDomainEntityIds(subscribeBy);
                activityRepository.saveAndFlush(activity);
                
                break;
            } 
			
	        case ORDER_STEP_TOP:
	        case ORDER_STEP_BOTTOM:
            {
//				int hotelId = activity.getHotel().getId();
	
				ReorderAction reorderAction = ReorderAction.parseByTitle(action.getName());
				
				reorderActivitiesInHotel(reorderAction, activity);
				
                break;
            }
			
        }

        var activityDto = convertActivityToDto(activity, sender);
        
        return activityDto;
    }
	
	@Override
    public void reorderActivitiesInHotel(ReorderAction action, HotelActivityDBEntity activity)
	{
		if(activity.getHotelDomainId()==null)
		{
			return;
		}
		
		List<HotelActivityDBEntity> hotelActivities = activityRepository.getAllTimesByHotelId(activity.getHotelDomainId());
		
		//sort by orderIndex!!!
		if (hotelActivities.size() > 0) {
			Collections.sort(hotelActivities, new Comparator<HotelActivityDBEntity>() {
				@Override
				public int compare(final HotelActivityDBEntity object1, final HotelActivityDBEntity object2) {
					return Integer.compare(object1.getOrderIndex(), object2.getOrderIndex());
				}
			} );
		}
		
//		hotelActivities
//				.stream()
//				.sorted((object1, object2) -> Integer.compare(object1.getOrderIndex(), object2.getOrderIndex()));
		
		
		int currentActivityIndex = hotelActivities.indexOf(activity);
		
		int newActivityIndex = 0;
		
		switch (action)
		{
			case ORDER_TOP:{
				newActivityIndex = 0;
				break;
			}
			
			case ORDER_BOTTOM: {
				newActivityIndex = hotelActivities.size()-1;
				break;
			}
			
			case ORDER_STEP_TOP:
			{
				newActivityIndex = currentActivityIndex - 1;
				break;
			}
			
			case ORDER_STEP_BOTTOM: {
				newActivityIndex = currentActivityIndex + 1;
				break;
			}
		}
		
		if(newActivityIndex<0)
		{
			newActivityIndex = 0;
		}
		
		if(newActivityIndex >= hotelActivities.size())
		{
			newActivityIndex = hotelActivities.size()-1;
		}
		
		Collections.swap(hotelActivities, currentActivityIndex, newActivityIndex);
		
		//set new orderIndex
		for (HotelActivityDBEntity nextActivity: hotelActivities)
		{
			nextActivity.setOrderIndex(hotelActivities.indexOf(nextActivity));
		}
		
		activityRepository.saveAll(hotelActivities);
		
		activity.setOrderIndex(hotelActivities.indexOf(activity));
	}
	
	@Override
    public String getGpsCity(Point2D.Double latLonPoint)
    {
        List<HotelDBEntity> hotelRootEntities = hotelRepository.findActiveGpsHotels();

        for( HotelDBEntity nextHotelRootEntity : hotelRootEntities)
        {

            var domainRoot = hotelEntityMapper.toDomain(nextHotelRootEntity);
            double kmFrom = this.getDistanceKmToHotel(latLonPoint, domainRoot);

            if(kmFrom<100)
            {
                return nextHotelRootEntity.getAddress().getCity();
            }

        }

        return null;
    }

    @Override
    public double getDistanceKmToHotel(Point2D.Double from, HotelRootDomainEntity hotelRootEntity)
    {
//        if(hotelRootEntity.getLatitude()<=-90)
//        {
//            return -1;
//        }
//
//        double earthRadius = 3958.75; // miles (or 6371.0 kilometers)
//        double dLat = Math.toRadians(hotelRootEntity.getLatitude()-from.getX());
//        double dLng = Math.toRadians(hotelRootEntity.getLongitude()-from.getY());
//        double sindLat = Math.sin(dLat / 2);
//        double sindLng = Math.sin(dLng / 2);
//        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
//                * Math.cos(Math.toRadians(from.getX())) * Math.cos(Math.toRadians(hotelRootEntity.getLatitude()));
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//        double dist = earthRadius * c;
//
//        double km = dist /  0.621371192;
//
//        double kmRound = Math.floor(km * 100) / 100;
//        
//        return kmRound;
        return 0;
    }
	
	@Override
    public List<CustomerDealDTO> getDealsByActivityOrHotelId(CustomerDomainEntityId customerId, HotelDomainEntityId hotelId, long activityId, boolean onlyDealsOfRequester, boolean closed)
	{
		
		//TODO Eugen: get closed deals or Menu!!!!!
		
		List<CustomerDealDBEntity> resultList = new ArrayList<>();
		List<CustomerDealDTO> dtoList = new ArrayList<>();
		
		//Eugen: if Customer is staff or admin, will become all deals!!! 

        CustomerDomainEntityId customerEntityId = AppConfigProperties.getTryEntityId(customerId);
        
//        if (customerId == (int)customerId)
//        {
//            customerEntityId = (int)customerId;
//        }

        Optional<CustomerDBEntity> requester = customerRepository.findByDomainEntityIdValue(customerEntityId);
		
		LocalDate filterDateFrom = closed? null: LocalDate.now();
        LocalDate filterDateTo = closed? null: LocalDate.now();
		
		List<DealStatus> filterDealStatusList = DealStatus.getFilterStatusList(closed);
		
		if(activityId>0)
		{
            if(onlyDealsOfRequester)
            {
                resultList = requester!=null?
							 
							 dealRepository.getActiveByCustomerAndActivityId(requester.get().getDomainEntityId(), activityId, filterDealStatusList, filterDateFrom, filterDateTo)
                             :
							 dealRepository.getActiveByGuestAndActivityId(customerId, activityId, filterDealStatusList, filterDateFrom, filterDateTo);
            
                if(resultList.size()>1)
                {
                   this.avoidDoubleInitId(resultList);
                }
            }
            else {
                resultList =  dealRepository.getActivityDeals(activityId, filterDealStatusList, filterDateFrom, filterDateTo);
            }
		}
		else if(hotelId!=null)
		{
            if(onlyDealsOfRequester)
            {
                resultList =  requester!=null?
									
							  dealRepository.getActiveByCustomerAndHotelId(requester.get().getDomainEntityId(), hotelId, filterDealStatusList, filterDateFrom, filterDateTo)
                              :
							  dealRepository.getActiveByGuestAndHotelId(customerId, hotelId, filterDealStatusList, filterDateFrom, filterDateTo);
            }
            else {
                resultList = dealRepository.getHotelDeals(hotelId, filterDealStatusList, filterDateFrom, filterDateTo);
            }
		}
		else //get deals in all hotels
        {
			//Eugen: ignore closed!!!
            resultList =  requester!=null?
                          dealRepository.getActiveByCustomerId(requester.get().getDomainEntityId(), LocalDate.now())
                          :
                          dealRepository.getActiveByGuestId(customerId, LocalDate.now());
        }
		
		for (CustomerDealDBEntity nextDeal: resultList)
		{
			CustomerDealDTO nextDto = convertDealToDto(nextDeal);
			
			dtoList.add(nextDto);
		}
		
		return dtoList;
	}
	
	private CustomerDealDTO convertDealToDto(CustomerDealDBEntity deal)
	{
        
        
        
        CustomerDealRootDomainEntity domain = customerDealEntityMapper.toDomain(deal);
        CustomerDealDTO dto = customerDealDtoMapper.toDto(domain);

        ActivityDomainEntityId dealActivity = deal.getActivityDomainEntityId();
		
//		dto.setActivityId(dealActivity.getId());
//        dto.setHotelDomainId(dealActivity.getHotelDomainId());
//       
//        dto.setTitle(dealActivity.getTitle());
//        dto.setDescription(dealActivity.getDescription());
//        dto.setShortDescription(dealActivity.getShortDescription());
//       
//        dto.setPictureUrl(dealActivity.getPictureUrl());
//        dto.setPreviewPictureUrl(dealActivity.getPreviewPictureUrl());
//        
//		dto.setDealDaysDuration(dealActivity.getDealDaysDuration());
//		dto.setActivityArea(dealActivity.getActivityArea());
//		dto.setLastMinute(dealActivity.isLastMinute());
//		
//        if(!deal.isActive())
//        {
//            dto.setTimeValid(false);
//        }
//		
//		boolean closed = Arrays.asList(DealStatus.REJECTED, DealStatus.CLOSED).contains(deal.getStatus());
//		dto.setClosed(closed);
//		
//		if (deal.getStatus()!=null)
//		{
//			dto.setDealStatus(deal.getStatus().getName());
//		}
		
        return dto;
    }
	
	@Override
    public ResponseDTO deleteDeal(CustomerDomainEntityId customerId, long activityId, int dealId)
	{
        long dealEntityId = (dealId);
        
		CustomerDealDBEntity deal = dealRepository.findById(dealEntityId).orElseThrow(()->new RuntimeException("not found"));
		
		ResponseDTO response = new ResponseDTO();
		
		if(deal.isActive())
		{
			deal.setActive(false);
			dealRepository.saveAndFlush(deal);
			response.setMessage("Deal removed successfully");
		}
		else{
			response.setMessage("Deal not found");
		}
		
		return response;
	}
	
	@Override
	public CustomerDealDTO addUpdateDeal(CustomerDomainEntityId guestCustomerId, long activityId, CustomerDealDTO dealDto)
	{
        Optional<CustomerDealDBEntity>  dealOptional  = getDealByIdOrInitId(dealDto.getHotelDomainId(), 123);

        Optional<CustomerDBEntity> sender = customerRepository.findByDomainEntityIdValue(AppConfigProperties.getTryEntityId(guestCustomerId));
        
        if(dealOptional.isPresent())
        {
            var deal = updateDealFromDto(dealOptional.get(), dealDto);

            dealRepository.saveAndFlush(deal);

            return convertDealToDto(deal);
        }
        else
        {
            Optional<HotelActivityDBEntity> activityOptional = getActivityByIdOrInitId((activityId), activityId);
            
            CustomerDealDBEntity newDeal = createDeal(sender.get(), guestCustomerId, activityOptional.get());
            return convertDealToDto(newDeal);
        }
//        return null;
	}
 


    private CustomerDealDBEntity updateDealFromDto(CustomerDealDBEntity deal, CustomerDealDTO activityDealDto)
	{
		//TODO Eugen: updatee dbObject from client DTO
		return null;
	}
	
	@Override
	public CustomerDealDTO addDealAction(CustomerDomainEntityId customerId, long activityId, long givenId, DealAction dealAction, String tablePosition, double totalMoney)
	{
        CustomerDomainEntityId customerEntityId = AppConfigProperties.getTryEntityId(customerId);
		
		CustomerDBEntity sender = customerRepository.findByDomainEntityIdValue(customerEntityId).orElseThrow(()->new RuntimeException("-"));

        Optional<HotelActivityDBEntity> activityOptional = getActivityByIdOrInitId((int)activityId, activityId);
		
        CustomerDealDTO resultDealDto = null;
        
        if(activityOptional.isPresent() /*&& sender != null*/)
		switch (dealAction)
		{
			case NEW_DEAL: {
				
                CustomerDealDBEntity newDeal = createDeal(sender, customerId, activityOptional.get());

                resultDealDto = convertDealToDto(newDeal);
                
                break;
            }
			
			case ACCEPT_DEAL: {

                CustomerDealDBEntity acceptDeal = new CustomerDealDBEntity(sender, activityOptional.get());

                if(sender==null && acceptDeal!=null)
                {
                    CustomerDBEntity anonym = customerService.addGetAnonymCustomer();
                    acceptDeal.setCustomerDomainEntityId(anonym.getDomainEntityId());
//                    acceptDeal.setGuestCustomerId(customerEntityId);
                }
                
                acceptDeal.setDealCode(String.valueOf(givenId));

                acceptDeal.setStatus(DealStatus.ACCEPTED);
                
                dealRepository.saveAndFlush(acceptDeal);

                resultDealDto = convertDealToDto(acceptDeal);

                break;
            }
			
			case EXECUTE: {

                List<CustomerDealDBEntity> executeDeals = dealRepository.getByInitId(givenId);

                CustomerDealDBEntity execute = null;
                
                if(!executeDeals.isEmpty())
                {
                    execute = executeDeals.get(0);	
                }
                
                if(execute != null)
                {
//						if (sender == null)
//						{
//							Customer anonym = customerService.addGetAnonymCustomer();
//							execute.setCustomer(anonym);
//							execute.setGuestCustomerId(customerEntityId);
//						}
                    
//						execute.setDealCode(String.valueOf(givenId));
                    
                    execute.setStatus(DealStatus.EXECUTED);
                    
                    if(tablePosition!=null && !tablePosition.equalsIgnoreCase("-"))
                    {
                        execute.setTablePosition(tablePosition);
                    }
                    dealRepository.saveAndFlush(execute);

                    resultDealDto = convertDealToDto(execute);
                }
                else {
                    return null;
                }

                break;
            }
			
			case CLOSE: {

                List<CustomerDealDBEntity> executeDeals = dealRepository.getByInitId(givenId);

                CustomerDealDBEntity execute = null;
                
                if(!executeDeals.isEmpty())
                {
                    execute = executeDeals.get(0);	
                }
                
                if(execute != null)
                {
                    execute.setStatus(DealStatus.CLOSED);
                    if(totalMoney>0 || execute.getTotalMoney()==null || execute.getTotalMoney()<totalMoney)
                    {
                        execute.setTotalMoney(totalMoney);
                    }
                    
                    dealRepository.saveAndFlush(execute);

                    resultDealDto = convertDealToDto(execute);
                }
                else {
                    return null;
                }

                break;
            }
            
			case EDIT_DEAL: {
				
				List<CustomerDealDBEntity> editDeals = dealRepository.getByInitId(givenId);
                
                if(editDeals.isEmpty() || !editDeals.get(0).getActivityDomainEntityId().equals(activityOptional.get()))
                {
					List<DealStatus> filterStatusList = DealStatus.getFilterStatusList(false);
					
					//Eugen: cannot edit closed deals!
                    editDeals = sender!=null?
                                dealRepository.getActiveByCustomerAndActivityId(sender.getDomainEntityId(), activityOptional.get().getId(), filterStatusList, LocalDate.now(), LocalDate.now())
                                :
                                dealRepository.getActiveByGuestAndActivityId(customerId, activityOptional.get().getId(), filterStatusList, LocalDate.now(), LocalDate.now());

                    if(editDeals.size()>1)
                    {
                        this.avoidDoubleInitId(editDeals);
                    }
                }
                
                if(!editDeals.isEmpty())
                {
                    for (CustomerDealDBEntity next: editDeals)
                    {
                        if(activityOptional.get().equals(next.getActivityDomainEntityId()))
                        {
                            return convertDealToDto(next);
                        }
                    }
                }
                
                break;
			}
            
			case REJECT_DEAL: {

                    List<CustomerDealDBEntity> rejectDeals = dealRepository.getByInitId(givenId);

                    CustomerDealDBEntity rejectDeal = null;

                    if(!rejectDeals.isEmpty())
                    {
                        rejectDeal = rejectDeals.get(0);
                    }

                    if(rejectDeal != null)
                    {
                        rejectDeal.setStatus(DealStatus.REJECTED);
                        rejectDeal.setActive(false);
                        dealRepository.saveAndFlush(rejectDeal);

                        resultDealDto = convertDealToDto(rejectDeal);
                    }
//                    else {
//                        return null;
//                    }
                
                break;
            }
		}

        if(resultDealDto!=null)
        {
            notificationService.sendNotificationToCustomerOrGuest(sender, customerId, InnerHotelEvent.EVENT_DEAL_NEW_UPDATE);
            
            CustomerDBEntity staff = checkinService.getStaffbyHotelId(resultDealDto.getHotelDomainId());
           
			//Notificate STAFF about deal action!!!
            if(staff != null)
            {
                notificationService.sendNotificationToCustomerOrGuest(null, staff.getDomainEntityId(), InnerHotelEvent.EVENT_DEAL_NEW_UPDATE);
            }
        }
        
        return resultDealDto;
	}

    

    public CustomerDealDBEntity createDeal(CustomerDBEntity sender, CustomerDomainEntityId guestCustomerId, HotelActivityDBEntity activity)
    {
        CustomerDealDBEntity newDeal = new CustomerDealDBEntity(sender, activity);

        if(sender==null)
		{
//			newDeal.setGuestCustomerId(guestCustomerId);
		}
		
        return newDeal;
    }

    @Override
    public HotelDTO getHotelById(HotelDomainEntityId hotelId) {
	
	    if(hotelId == specialHotelId)
	    {
		    return generateVirtualHotel();
	    }
		
	    HotelDTO out  = hotelRepository.findByDomainEntityIdValue(hotelId)
			    .map(h -> convertHotelToDto(h))
			    .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with hotelId " + hotelId));
        
        return out;
    }

    @Override
    public HotelDTO getHotelByDomainId(HotelDomainEntityId hotelId) {
        return null;
    }

    private HotelDTO generateVirtualHotel()
	{
		HotelDBEntity specialHotelRootEntity = hotelRepository.findByVirtualAndActive(true, true)
				.stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("no virtual hotel found"));
		
		return convertHotelToDto(specialHotelRootEntity);
	}
	
	@Override
    public HotelActivityDTO getHotelActivityById(CustomerDomainEntityId requesterId, long activityId) throws Throwable {
        HotelActivityDBEntity hotelActivity = (HotelActivityDBEntity) getActivityByIdOrInitId(activityId, activityId).orElseThrow(()->new RuntimeException("not found"));

        Optional<CustomerDBEntity> requester = null;
        
        if(hotelActivity!=null)
        {
            requester = customerRepository.findByDomainEntityIdValue((requesterId));
        }
        
        HotelActivityDTO activityDto = convertActivityToDto(hotelActivity, requester.get());

        return activityDto;
    }

    @Override
    public WallPostDTO getWallPostById(WallPostDomainEntityId wallPostId)
    {
        List<WallPostDTO> wallPost = new ArrayList<>(); //wallPostRepository.getMessageDTOByInitId(wallPostId);
        return wallPost.stream().findFirst().get();
    }

    @Transactional
    @Override
    public HotelDTO addHotel(HotelDTO hotelDto) {
		
		if(!AppConfigProperties.isEmptyString(hotelDto.getEmail()))
		{
			List<HotelDBEntity> hotelsWithSameMail = hotelRepository.findByEmailAndActive(hotelDto.getEmail(), true);
			
			if(!hotelsWithSameMail.isEmpty())
			{
				throw new RuntimeException("The Hotel with this e-mail is already registered. Please contact hotelico Admin.");
			}
		}

        HotelRootDomainEntity domain = hotelDtoMapper.toDomain(hotelDto);
        HotelDBEntity hotelRootEntity = hotelEntityMapper.toDb(domain);

        hotelRootEntity.setConsistencyId(System.currentTimeMillis());

        return convertHotelToDto(hotelRepository.saveAndFlush(hotelRootEntity));
    }

    @Transactional
    @Override
    public HotelActivityDTO addUpdateHotelActivity(CustomerDomainEntityId customerId, HotelActivityDTO hotelActivityDto) throws Throwable {
        if(customerId!=null)
        {
	        Optional<CustomerDBEntity> creatorOpt = customerRepository.findByDomainEntityIdValue(AppConfigProperties.getTryEntityId(customerId));

            //Check creator role
            if(creatorOpt.isPresent())
            {
	            CustomerDBEntity creator = creatorOpt.get();
				
				if(!creator.isHotelStaff() && !creator.isAdmin())
                    return null;
            }
        }
	
	    HotelActivityDBEntity hotelActivity = getActivityByIdOrInitId(hotelActivityDto.getDomainEntityId(), hotelActivityDto.getInitId()).orElse(createHotelActivity(hotelActivityDto));
	    
//		hotelActivity.map(a -> updateHotelActivity(hotelActivityDto))
//				    .orElse(createHotelActivity(hotelActivityDto));
        
        //TODO Eugen: notificate about new/changed activity
        if(hotelActivityDto.isPublishInWall())
        {
			//TODO Eugen: Last Minute!!!!
			if(hotelActivityDto.isLastMinute())
			{
				WallPostDTO checkinNotificationWall = new WallPostDTO(new WallPostDomainEntityId("sdf"),  hotelActivity.getSender().getDomainEntityId());
		
				checkinNotificationWall.getSpecialContent().put("activityId", String.valueOf(hotelActivityDto.getDomainEntityId()));
//				checkinNotificationWall.setSequenceId(System.currentTimeMillis());
				checkinNotificationWall.setMessage("New last minute deal:");
				this.addUpdateWallPost(checkinNotificationWall);
			}
	
            notificationService.broadcastActivityNotification(hotelActivityDto);
        }
		
        return hotelActivityDto;
    }

    @Override
    public Optional getActivityByIdOrInitId(long activId, long activId1) {
        return Optional.empty();
    }

//    @Override
    public Optional<HotelActivityDBEntity> getActivityByIdOrInitId(ActivityDomainEntityId id, long initId)
    {
        if(initId>0)
        {
	        return activityRepository.findByInitIdAndActive(initId, true)
			        .stream().findFirst();
        }    
        
        return activityRepository.findByDomainEntityIdValue(id);
    }
    
    @Override
    public Optional<CustomerDealDBEntity> getDealByIdOrInitId(HotelDomainEntityId hotelId, long initId)
    {
        if(initId>0)
        {
            return dealRepository.getByInitId(initId)
		            .stream().findFirst();
        }    
        
        return dealRepository.findByPkHotelDomainEntityIdValue(hotelId);
    }

    private HotelActivityDBEntity createHotelActivity(HotelActivityDTO hotelActivityDto)
    {
        HotelActivityDBEntity hotelActivity;// ###### CREATE HOTEL OBJECT 

        HotelActivityRootDomainEntity hotelActivityRootDomainEntity = hotelActivityDtoMapper.toDomain(hotelActivityDto);
        hotelActivity = hotelActivityEntityMapper.toDb(hotelActivityRootDomainEntity);

        HotelDBEntity hotelRootEntity = null;
        
        if(hotelActivityDto.getHotelId()!=null && hotelActivityDto.getHotelId()!=null)
		{
			hotelRootEntity = hotelRepository.findByDomainEntityIdValue(hotelActivityDto.getHotelId()).orElseThrow();
		}

        CustomerDBEntity sender = null;

        if(hotelActivityDto.getHotelId()!=null && hotelActivityDto.getHotelId()!=null)
		{
			sender = customerService.getEntityById(hotelActivityDto.getSenderDomainId()).orElseThrow(()->new RuntimeException("not found"));
		}

        if(hotelRootEntity !=null)
		{
			hotelActivity.setHotelDomainId(hotelRootEntity.getDomainEntityId());
		}
		
        if(sender!=null)
			hotelActivity.setSender(sender);

        // ############################### test ####

        hotelActivity = fillActivityFromDto(hotelActivity, hotelActivityDto);

        hotelActivity.setActive(true);
        
        hotelActivity = activityRepository.saveAndFlush(hotelActivity);
	
		//reorder new activity!!!!
		reorderActivitiesInHotel(ReorderAction.ORDER_TOP, hotelActivity);
	
		hotelActivityDto = convertActivityToDto(hotelActivity, sender);
        
        return hotelActivity;
    }

    public HotelActivityDBEntity fillActivityFromDto(HotelActivityDBEntity hotelActivity, HotelActivityDTO hotelActivityDto)
    {
        hotelActivity.setInitId(hotelActivityDto.getInitId());

        if(hotelActivity.getValidFrom()==null)
		{
			hotelActivity.setValidFrom(LocalDate.now());
		}

        hotelActivity.setActive(true);

        if(hotelActivity.getValidTo()==null)
		{
			hotelActivity.setValidTo(LocalDate.now().plusDays(7));
		}

        hotelActivity.setConsistencyId(System.currentTimeMillis());
        
        return hotelActivity;
    }

    @Override
    public WallPostDTO addWallPost(WallPostDTO wallPostDto) {

        CustomerDBEntity sender = customerService.getByDomainId(wallPostDto.getSenderId()).orElseThrow(() -> new BusinessException("wallpost not found"));
        
        if(wallPostDto.getHotelId()==null)
        {
            wallPostDto.setHotelId(customerService.getCustomerHotelId(sender.getDomainEntityId()));
        }
        
//        if(wallPostDto.getCreationTime()<=0)
//        {
//            wallPostDto.setCreationTime(System.currentTimeMillis());
//        }
        
        HotelDBEntity hotelRootEntity = this.getEntityById(wallPostDto.getHotelId()).orElseThrow(()-> new BusinessException("wallpost not found"));

        HotelWallPost newMessage = new HotelWallPost(wallPostDto.getMessage(), sender, hotelRootEntity);

        newMessage.setInitId(wallPostDto.getSequenceId());
        newMessage.setTimestamp(new Timestamp(wallPostDto.getCreationTime()));
        newMessage.setSpecialWallContent(wallPostDto.getSpecialContent().toString());

        //wallPost is valid only 1 day | 1 Month for DEMO...
        if(AppConfigProperties.HOTEL_DEMO_CODE.equalsIgnoreCase(hotelRootEntity.getCurrentHotelAccessCode()))
        {
            newMessage.setValidUntil(LocalDate.now().plusMonths(6));
        }
        else {
            newMessage.setValidUntil(LocalDate.now().plusDays(3));
        }

        wallPostRepository.saveAndFlush(newMessage);

        notificationService.notificateAboutEntityEvent(customerService.convertCustomerWithHotelToDto(sender, wallPostDto.getHotelId()), InnerHotelEvent.EVENT_WALL_NEW_MESSAGE, "New Wall message", newMessage.getDomainEntityId());
        
//        fillWallPostDto(wallPostDto, newMessage);

        return convertWallToDto(newMessage);
    }

//    private void fillWallPostDto(WallPostDto wallPostDto, HotelWallPost newMessage)
//    {
//        
//    }

    @Transactional
    @Override
    public HotelDTO updateHotel(HotelDTO hotelDto) {
        
		Optional<HotelDBEntity> hotelRootEntityOpt = hotelRepository.findByDomainEntityIdValue(hotelDto.getDomainEntityId());
	
	    HotelDBEntity hotelRootEntity = null;
	    
		if(hotelRootEntityOpt.isPresent())
        {
	        hotelRootEntity = hotelRootEntityOpt.get();
			        
            hotelRootEntity.setDescription(hotelDto.getDescription());
            hotelRootEntity.setInfo(hotelDto.getInfo());
            hotelRootEntity.setName(hotelDto.getName());
            hotelRootEntity.setEmail(hotelDto.getEmail());
            hotelRootEntity.setCurrentHotelAccessCode(hotelDto.getCurrentHotelAccessCode());
            hotelRootEntity.getAddress().setCity(hotelDto.getCity());
            hotelRootEntity.getAddress().setStreet(hotelDto.getStreet());
            hotelRootEntity.setHouse(hotelDto.getHouse());
            hotelRootEntity.setPostalCode(hotelDto.getPostalCode());
            hotelRootEntity.setFax(hotelDto.getFax());
            hotelRootEntity.setPhone(hotelDto.getPhone());
			
            hotelRootEntity.setRating(hotelDto.getRating());
//            hotel.setPictureUrl(hotelDto.getPictureUrl());

			//Eugen: important, consistencyId only here to create!
            hotelRootEntity.setConsistencyId(System.currentTimeMillis());
            
            if(hotelDto.getCreationTime()>0)
            {
                hotelRootEntity.setCreationTime(hotelDto.getCreationTime());
            }   
			
			if(!AppConfigProperties.isEmptyString(hotelDto.getWelcomeMessage()))
            {
                hotelRootEntity.setWelcomeMessage(hotelDto.getWelcomeMessage());
            }
            
            hotelRepository.saveAndFlush(hotelRootEntity);
        }
		
        return convertHotelToDto(hotelRootEntity);
    }

    private HotelDTO convertHotelToDto(HotelDBEntity hotelRootEntity)
    {
        if(hotelRootEntity == null)
        {
            throw new IllegalArgumentException("HotelRootEntity is null");
        }

        HotelRootDomainEntity domain = hotelEntityMapper.toDomain(hotelRootEntity);
        HotelDTO dto = hotelDtoMapper.toDto(domain);
        
        int activityNumber = 0;
        int customerNumber = 0;
        
        try{
            activityNumber = activityRepository.getTimeValidCounterByHotelId(hotelRootEntity.getDomainEntityId(), LocalDate.now());
            customerNumber = checkinRepository.getFullCheckinCountExcludingStaffByHotelId(hotelRootEntity.getDomainEntityId(), LocalDate.now());
        }
        catch (Exception e){
            ;//TODO Eugen.
        }
        
//        dto.setActivityNumber(activityNumber);
//        dto.setCustomerNumber(customerNumber);
//        dto.setCreationTime(hotelRootEntity.getCreationTime());
        HotelRootDomainEntity root = hotelEntityMapper.toDomain(hotelRootEntity);

		Map<Long, String> anonymeGuests = getNotLoggedGuestPushIdsByHotel(root);
		
//		dto.setAnonymeGuestNumber(anonymeGuests.keySet().size());
		
        //TODO EUGEN: only active and time valid activities query!
        return dto;
    }

	
    @Override
    public HotelActivityDTO updateHotelActivity(HotelActivityDTO hotelActivityDto)
    {
        Optional<HotelActivityDBEntity> hotelActivityOpt = getActivityByIdOrInitId(hotelActivityDto.getDomainEntityId(), hotelActivityDto.getInitId());
	
	    HotelActivityDBEntity hotelActivity = null;
		
	    if(hotelActivityOpt.isPresent())
        {
            hotelActivity = fillHotelFromDto(hotelActivityDto, hotelActivityOpt.get());
            
            activityRepository.save(hotelActivity);
        }
		
        /// update DTO
        return convertActivityToDto(hotelActivity, null);
    }
    

    @Override
	public Map<Long,String> getNotLoggedGuestPushIdsByHotel(HotelRootDomainEntity hotelRootEntity)
	{
		Map<Long, String> guestsToPushId = new HashMap<>();

		if(hotelRootEntity == null)
		{
			return guestsToPushId;
		}

		//TODO convert string data to own structure

//		String hotelPushIds = hotelRootEntity.getGuestPushIds()!=null?  hotelRootEntity.getGuestPushIds() : "";
//		
//		splitPushIds(guestsToPushId, hotelPushIds);

		return guestsToPushId;
	}
	
	/**
	 * TODO convert string to own data structire
	 * @param guestsToPushId
	 * @param hotelPushIds
	 */
	private void splitPushIds(Map<Long, String> guestsToPushId, String hotelPushIds)
	{
		String[] guestPushIds = hotelPushIds.split("<#>");
		
		for (String guestPushIdMapping : guestPushIds)
		{
			String[] pushMapping = guestPushIdMapping.split("<->");

			if (pushMapping.length > 1)
			{
				String nextGuestId = pushMapping[0];
				String nextPushId = pushMapping[1];

//				if (!loggedGuestPushIds.contains(nextPushId) && !ControllerUtils.isEmptyString(nextPushId))
				{
					guestsToPushId.put(Long.parseLong(nextGuestId), nextPushId);
				}
			}
		}
	}
	
	@Override
	public Map<Long,String> getUnsubscribeGuestPushIds()
	{
		Map<Long, String> unsubscribeGuestsToPushId = new HashMap<>();
		
		List<HotelDBEntity> allActiveHotelRootEntities = hotelRepository.findByVirtualAndActive(false, true);
		
		for (HotelDBEntity nextHotelRootEntity : allActiveHotelRootEntities)
		{
			String hotelUnsubscribePushIds = nextHotelRootEntity.getUnsubscribeNotificationPushIds()!=null?  nextHotelRootEntity.getUnsubscribeNotificationPushIds() : "";
			
			splitPushIds(unsubscribeGuestsToPushId, hotelUnsubscribePushIds);
		}
		
		return unsubscribeGuestsToPushId;
	}

	public HotelActivityDBEntity fillHotelFromDto(HotelActivityDTO hotelActivityDto, HotelActivityDBEntity hotelActivity)
    {
//        hotelActivity = modelMapper.map(hotelActivityDto, HotelActivity.class);
        
        hotelActivity.setDescription(hotelActivityDto.getDescription());
        hotelActivity.setShortDescription(hotelActivityDto.getShortDescription());
        hotelActivity.setTitle(hotelActivityDto.getTitle());
        //IMPORTANT Eugen: not here
        //            hotelActivity.setPictureUrl(hotelActivityDto.getPictureUrl());

        hotelActivity.setValidFrom(hotelActivityDto.getValidFrom() != null ? hotelActivityDto.getValidFrom() : LocalDate.now());

        hotelActivity.setValidTo(hotelActivityDto.getValidTo() != null && !hotelActivityDto.isLastMinute() ? hotelActivityDto.getValidTo() : hotelActivityDto.isLastMinute() ? AppConfigProperties.convertToLocalDate(LocalDateTime.now().plusDays(1).withHour(4)) : AppConfigProperties.convertToLocalDate(LocalDateTime.now().plusDays(7)));

        hotelActivity.setActive(hotelActivityDto.isActive());
        hotelActivity.setLastMinute(hotelActivityDto.isLastMinute());
         

        hotelActivity.setConsistencyId(System.currentTimeMillis());
        
        return hotelActivity;
    }

    @Override
    public WallPostDTO addUpdateWallPost(WallPostDTO wallPostDto) {
        List<HotelWallPost> wallPost = wallPostRepository.getMessageBySenderAndInitId(wallPostDto.getSenderId(), wallPostDto.getDomainEntityId());

        if(wallPost.isEmpty())
        {
            wallPostDto = addWallPost(wallPostDto);
            notificationService.broadcastWallNotification(wallPostDto);
        }
        else {
            //updateWallPost(wallPostDto);
        }
        
        return wallPostDto;
    }

    @Override
    public int getCustomerDealCounter(CustomerDomainEntityId receiverId, int i) {
        return 0;
    }


    @Override
    public WallPostDTO updateWallPost(WallPostDTO wallPostDto)
    {
        List<HotelWallPost> wallPosts = wallPostRepository.getMessageByInitId(wallPostDto.getDomainEntityId());
	
	    HotelWallPost updateWallPost = wallPosts.stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("Wallpost not found with initId=" + wallPostDto.getSequenceId()));
       
        HotelWallPost entity = wallPostRepository.saveAndFlush(updateWallPost);
        HotelWallRootDomainEntity domain = wallPostEntityMapper.toDomain(entity);
        return wallPostDTOMapper.toDto(domain);
            //            wallPost.setDescription(hotelDto.getDescription());
            //            wallPost.setName(hotelDto.getName());
            //            wallPost.setEmail(hotelDto.getEmail());
            //            wallPost.setCurrentHotelAccessCode(hotelDto.getCurrentHotelAccessCode());
            //            wallPost.setCity(hotelDto.getCity());
            //            wallPost.setStreet(hotelDto.getStreet());
            //            wallPost.setHouse(hotelDto.getHouse());
            //            wallPost.setPostalCode(hotelDto.getPostalCode());
            //            wallPost.setFax(hotelDto.getFax());
            //            wallPost.setPhone(hotelDto.getPhone());
            //            wallPost.setLogoUrl(hotelDto.getLo());
    }
    
    @Override
    public List<HotelActivityDTO> getHotelActivitiesByHotelId(CustomerDomainEntityId requesterId, HotelDomainEntityId hotelId)
    {
        Optional<CustomerDBEntity> requesterOpt = customerRepository.findByDomainEntityIdValue(requesterId);

        HotelDomainEntityId virtualHotelId = lastMessagesService.getInitHotelId();
       
        List<HotelActivityDBEntity> list =  new ArrayList<>();
	
	    CustomerDBEntity requester = null;
		
	    if(virtualHotelId == hotelId || hotelId==null)
        {
            //VIRTUAL HOTEL:
            if(requesterOpt.isPresent())
            {
	            requester = requesterOpt.get();
				if((requester.isHotelStaff() || requester.isAdmin()))
                //add all active activities if admin or staff
                list = activityRepository.findAllTimesActive();
            }
            else {
                //add only time valid activities to customers
                list = activityRepository.findTimeValidActive(LocalDate.now());
            }
        }
        else{
            //REAL HOTEL:
		    if(requesterOpt.isPresent())
		    {
			    requester = requesterOpt.get();
				if(requester!=null && (requester.isHotelStaff() || requester.isAdmin()))
		        {
		            list = activityRepository.getAllTimesByHotelId(hotelId);
		        }
            }
            else {
                list = activityRepository.getTimeValidByHotelId(hotelId, LocalDate.now());
            }
        }
        
        List<HotelActivityDTO> out = new ArrayList<HotelActivityDTO>();
        
        //TODO EUGEN how to set seen activities
        //if(requester!=null && requester.getSeenActivities()!=null)
        {
//            requester.getSeenActivities().clear();
            
            Map<CustomerDomainEntityId, CustomerDealDBEntity> guestActivityToDealMap = new HashMap<>();
            
            if(requesterOpt.isEmpty() && requesterId!=null)
            {
                List<CustomerDealDBEntity> guestDealsList = dealRepository.getActiveByGuestId(requesterId, LocalDate.now());

                if(!guestDealsList.isEmpty())
                {
                    for (CustomerDealDBEntity next: guestDealsList)
                    {
                        guestActivityToDealMap.put(next.getCustomerDomainEntityId(), next);
                    }
                }
            }
            
            for (HotelActivityDBEntity hotelActivity : list) {
//                requester.getSeenActivities().add(hotelActivity);
                
                HotelActivityDTO activityDto = this.convertActivityToDto(hotelActivity, requester);
                
                if(activityDto.getValidDealId()==0 && guestActivityToDealMap.containsKey(hotelActivity.getId()))
                {
                    CustomerDealDBEntity deal = guestActivityToDealMap.get(hotelActivity.getId());
                    
                    if(hotelActivity.equals(deal.getActivityDomainEntityId()))
                    {
                        activityDto.setValidDealId(deal.getInitId());
                    }
                }
                
                out.add(activityDto);
            }
            
            //TODO EUGEN: ERROR IF SET SEEN ACTIVITIES
            //customerRepository.saveAndFlush(requester);
        }
        
        return out;
    }

    @Override
    public HotelActivityDTO addActivityAction(CustomerDomainEntityId customerId, long activityId, String action) {
        return null;
    }


    @Override
    public int getCustomerDealCounter(CustomerDomainEntityId customerId, CustomerDomainEntityId guestId)
	{
		Integer count = dealRepository.countActiveDealsByCustomerOrGuest(customerId, guestId, LocalDate.now());
	
		return count!=null? count : 0;
	}
	
    @Override
    public HotelActivityDTO convertActivityToDto(HotelActivityDBEntity hotelActivity, CustomerDBEntity requester)
    {
	    Objects.requireNonNull(hotelActivity);

        HotelActivityRootDomainEntity domain = hotelActivityEntityMapper.toDomain(hotelActivity);
        HotelActivityDTO activityDto = hotelActivityDtoMapper.toDto(domain);
        
        activityDto.setLikeCounter(hotelActivity.getLikedCustomerDomainEntityIds().size());
        activityDto.setSubscribeCounter(hotelActivity.getSubscribeCustomerDomainEntityIds().size());
        
//        activityDto.setLikedByMe(hotelActivity.getLikedCustomers().contains(requester));
//        activityDto.setSubscribeByMe(hotelActivity.getSubscribeCustomers().contains(requester));

        List<CustomerDealDBEntity> activityDeals = new ArrayList<>();
        
        if(requester != null)
        {
			List<DealStatus> filterStatuslist = DealStatus.getFilterStatusList(false);
            activityDeals = dealRepository.getActiveByCustomerAndActivityId(requester.getDomainEntityId(), hotelActivity.getId(), filterStatuslist, LocalDate.now(), LocalDate.now());
        }
        
        if(!activityDeals.isEmpty())
        {
            activityDto.setValidDealId(activityDeals.get(0).getInitId());
        }

        HotelDomainEntityId hotelRootEntityId = hotelActivity.getHotelDomainId();

        int otherActivityNumber = 0;
        int hotelCustomerNumber = 0;
        
        if(hotelRootEntityId !=null)
        {
            activityDto.setHotelId(hotelRootEntityId);
//            activityDto.setHotelName(hotelRootEntity.getName());
//            activityDto.setHotelCity(hotelRootEntity.getAddress().getCity());

            try
            {
                otherActivityNumber = activityRepository.getTimeValidCounterByHotelId(hotelRootEntityId, LocalDate.now());
                hotelCustomerNumber = checkinRepository.getFullCheckinCountExcludingStaffByHotelId(hotelRootEntityId, LocalDate.now());
            }
            catch (Exception e)
            {
                ;//TODO Eugen.
            }
        }
        activityDto.setHotelCustomerNumber(hotelCustomerNumber);
//        activityDto.setLikeCounter(5);
        activityDto.setOtherActivityNumber(otherActivityNumber>0? otherActivityNumber-1 : 0);
        
        boolean isActivityTimeValid = hotelActivity.getValidTo()!=null && hotelActivity.getValidFrom()!=null && hotelActivity.getValidTo().isAfter(LocalDate.now()) && hotelActivity.getValidFrom().isBefore(LocalDate.now());
        activityDto.setTimeValid(isActivityTimeValid);

        if(activityDto.getInitId() ==0)
        {
            //set activityId, if was not set
            activityDto.setSequenceId(hotelActivity.getInitId());
        }
        
        return activityDto;
    }

    @Override
    public List<HotelActivityDTO> getHotelActivitiesBySenderAndHotelId(CustomerDomainEntityId senderId, HotelDomainEntityId hotelId)
    {
        List<HotelActivityDBEntity> list = new ArrayList<>();
        
        CustomerDBEntity customerEntity = customerService.getEntityById(AppConfigProperties.getTryEntityId(senderId)).orElseThrow(()-> new RuntimeException("not found"));
        
        if(customerEntity !=null && customerEntity.isHotelStaff() || customerEntity.isAdmin())
        {
            list = activityRepository.getAllTimesByHotelId(hotelId);
        }
        else{
            list = activityRepository.getTimeValidByHotelId(hotelId, LocalDate.now());
        }
        
        List<HotelActivityDTO> out = new ArrayList<HotelActivityDTO>();
        for (HotelActivityDBEntity hotelActivity : list) {
            
            HotelActivityDTO activityDto = convertActivityToDto(hotelActivity, customerEntity);
            out.add(activityDto);
        }
        return out;
    }

    @Override
    public List<WallPostDTO> getWallPostsByHotelId(HotelDomainEntityId hotelId)
    {
        List<HotelWallPost> list = wallPostRepository.getByHotelId(hotelId, LocalDate.now());
       
        List<WallPostDTO> out = new ArrayList<WallPostDTO>();

        boolean wallIsDemoValid = AppConfigProperties.NO_WALL_EXPIRES_FOR_DEMOHOTEL && hotelId == hotelRepository.getDemoHotelId();
        
        for (HotelWallPost wallPost : list) {

            if(wallIsDemoValid || wallPost.getValidUntil().isAfter(LocalDate.now()))
            {
                WallPostDTO dto = convertWallToDto(wallPost);
                out.add(dto);
            }
        }
        return out;
    }

    public WallPostDTO convertWallToDto(HotelWallPost wallPost)
    {
        HotelWallRootDomainEntity domain = wallPostEntityMapper.toDomain(wallPost);
        WallPostDTO dto = wallPostDTOMapper.toDto(domain);

//        dto.setCreationTime(wallPost.getTimestamp().getTime()); //TODO auto with mapper!!!

        CustomerDBEntity sender = wallPost.getSender();

//        if(sender!=null) //TODO auto with mapper!!!
//        {
//            dto.setSenderName(sender.getFirstName() + (sender.getLastName() != null ? " " + sender.getLastName() : ""));
//
//            dto.setSenderId(sender.getDomainEntityId());
//
//            dto.setHotelStaff(sender.isHotelStaff());
//        }
        
//        if(dto.getSequenceId()==0)
//        {
//            dto.setSequenceId(wallPost.getTimestamp().getTime());
//        }

//        dto.setSendTimeString(wallPost.getTimestamp() != null ? AppConfigProperties.getTimeFormatted(wallPost.getTimestamp()) : null);

		dto.setHotelId(wallPost.getHotel().getDomainEntityId());
		
        if(wallPost.getSpecialWallContent()!=null)
        {
            String specContent = wallPost.getSpecialWallContent();

            String[] split = specContent.replace("{","").replace("}", "").split("=");

            if(split.length>1)
            {
                String key = split[0];
                String value = split[1];

                dto.getSpecialContent().put(key, value);
                
                if(key.equalsIgnoreCase("activityId"))
                {
                    long activityId = Long.parseLong(value);
	
	                Optional<HotelActivityDBEntity> activityOpt = getActivityByIdOrInitId(activityId, activityId);
                    
                    if(activityOpt.isPresent())
                    {
	                    HotelActivityDBEntity activity = activityOpt.get();
                        dto.getSpecialContent().put("name", activity.getTitle());
                        dto.getSpecialContent().put("hotelId", activity.getHotelDomainId()+"");
                        dto.getSpecialContent().put("pictureUrl", activity.getPreviewPictureUrl()!=null?activity.getPreviewPictureUrl() : AppConfigProperties.PREVIEW_ACTIVITY_NOT_AVAILABLE_URL );
                    }

                }
                else 
                if(key.equalsIgnoreCase("customerId"))
                {
                    long customerId = Integer.parseInt(value);
                    
                    CustomerDBEntity customerEntity = customerRepository.getOne(customerId);

                    if(customerEntity !=null)
                    {
                        dto.getSpecialContent().put("name", customerEntity.getFirstName() + " " + customerEntity.getLastName());
                        dto.getSpecialContent().put("pictureUrl", customerService.getCustomerAvatarUrl(customerEntity));
                    }
                }
            }
        }
        
        return dto;
    }

    public void avoidDoubleInitId(List<CustomerDealDBEntity> resultList)
    {
        if(resultList!=null && resultList.size()>1)
        {
            CustomerDealDBEntity first = resultList.get(0);

            for (int i = 1; i < resultList.size(); i++)
            {
                CustomerDealDBEntity nextObj = resultList.get(i);

                if(!nextObj.equals(first) && nextObj.getInitId() == first.getInitId())
                {
                    nextObj.setInitId(ThreadLocalRandom.current().nextInt(1, 999999));

                    dealRepository.saveAndFlush(nextObj);
                }
            }
        }
    }

	@Override
    public CustomerDTO addGuestAction(CustomerDomainEntityId guestCustomerId, String action, Long hotelId, CustomerDTO guestDto)
	{
		Objects.requireNonNull(guestDto);
		
		hotelId = hotelId!=null? hotelId : guestDto.getHotelId();
		
		String actionHotelId = (String) guestDto.getSystemMessages().get("actionHotelId");
		
		if ((hotelId == null || hotelId <= 0) && actionHotelId != null)
		{
			hotelId = Long.parseLong(actionHotelId);
		}
		
		if (hotelId == null || hotelId <= 0)
		{
			return null;
		}
		
		Long finalHotelId = hotelId;
		HotelDBEntity hotelRootEntity = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("hotel with id = " + finalHotelId));

		if("addGuestPushId".equalsIgnoreCase(action))
		{
            HotelRootDomainEntity hotelRootDomainEntity = hotelEntityMapper.toDomain(hotelRootEntity);
			Map<Long, String> getNotLoggedGuestPushIds = getNotLoggedGuestPushIdsByHotel(hotelRootDomainEntity);
			
			if(!guestDto.getSystemMessages().containsKey("guestCustomerId"))
			{
				return null;
			}
			
			String guestCustomerId1 = (String) guestDto.getSystemMessages().get("guestCustomerId");
			Long dtoGuestCustomerId = Long.parseLong(guestCustomerId1);
			
			String guestPushId = (String) guestDto.getSystemMessages().get(AppConfigProperties.PUSH_CHROME_ID);

			if (getNotLoggedGuestPushIds.containsValue(guestPushId) || AppConfigProperties.isEmptyString(guestPushId))
			{
				return null;
			}

//			if (getNotLoggedGuestPushIds.containsKey(dtoGuestCustomerId))
			{
				getNotLoggedGuestPushIds.put(dtoGuestCustomerId, guestPushId);
			}

			String hotelGuestPushIds = "";
			
			for (Long next: getNotLoggedGuestPushIds.keySet())
			{
				hotelGuestPushIds += next + "<->" + getNotLoggedGuestPushIds.get(next) + "<#>";
			}

			hotelRootEntity.setGuestPushIds(hotelGuestPushIds);
			hotelRepository.saveAndFlush(hotelRootEntity);

		}
		else if("removeGuestPushId".equalsIgnoreCase(action))
		{
            var domainRoot = hotelEntityMapper.toDomain(hotelRootEntity);
			Map<Long, String> getNotLoggedGuestPushIds = getNotLoggedGuestPushIdsByHotel(domainRoot);

			if(!guestDto.getSystemMessages().containsKey("guestCustomerId"))
			{
				return null;
			}

			Long dtoGuestCustomerId = Long.parseLong((String) guestDto.getSystemMessages().get("guestCustomerId"));			
			
			String guestPushId = (String)  guestDto.getSystemMessages().get(AppConfigProperties.PUSH_CHROME_ID);

//			String hotelPushIds = hotel.getGuestPushIds();

			if(!getNotLoggedGuestPushIds.containsValue(guestPushId))
			{
				return null;
			}

			getNotLoggedGuestPushIds.remove(dtoGuestCustomerId);

			String hotelGuestPushIds = "";

			for (Long next: getNotLoggedGuestPushIds.keySet())
			{
				hotelGuestPushIds += next + "<->" + getNotLoggedGuestPushIds.get(next) + "<#>";
			}
			
			hotelRootEntity.setGuestPushIds(hotelGuestPushIds);
			hotelRepository.saveAndFlush(hotelRootEntity);

		}
		else if("unsubscribeHotelActivityNotifications".equalsIgnoreCase(action))
		{
			//TODO add pushId or guestId to unsuscribe notification List
            var root = hotelEntityMapper.toDomain(hotelRootEntity);

            Map<Long, String> getNotLoggedGuestPushIds = getNotLoggedGuestPushIdsByHotel(root);
			
			if(!guestDto.getSystemMessages().containsKey("guestCustomerId"))
			{
				return null;
			}
			
			Long dtoGuestCustomerId = Long.parseLong((String) guestDto.getSystemMessages().get("guestCustomerId"));
			
			String guestPushId = (String) guestDto.getSystemMessages().get(AppConfigProperties.PUSH_CHROME_ID);
			
			if(!getNotLoggedGuestPushIds.containsValue(guestPushId))
			{
				return null;
			}
			
			String unsubscribeActivityNotificationsPushId = dtoGuestCustomerId+"";
			
			String unsubscribeNotificationPushIds = hotelRootEntity.getUnsubscribeNotificationPushIds();
			
			String newUnsibscribeEntry = guestCustomerId + "<->" + unsubscribeActivityNotificationsPushId + "<#>";
			
			if(unsubscribeNotificationPushIds==null)
			{
				unsubscribeNotificationPushIds = "";
			}
			
			if(!unsubscribeNotificationPushIds.contains(newUnsibscribeEntry))
			{
				unsubscribeNotificationPushIds += newUnsibscribeEntry;
			}
			
			hotelRootEntity.setUnsubscribeNotificationPushIds(unsubscribeNotificationPushIds);
			hotelRepository.saveAndFlush(hotelRootEntity);
		}
		
		return null;
	}

	@Transactional
    @Override
    public ResponseDTO deleteHotel(HotelDomainEntityId hotelId, CustomerDomainEntityId customerId) {
		
		CustomerDBEntity customerEntity = customerRepository.findByDomainEntityIdValueAndActive(customerId, true).orElseThrow();
		
		if(customerEntity ==null || !(customerEntity.isAdmin() || customerEntity.isHotelStaff()))
		{
			return new ResponseDTO("There is no staff rights", true);
		}
		
		HotelDBEntity hotelRootEntity = hotelRepository.findByDomainEntityIdValue(hotelId).orElseThrow();
		
		hotelRootEntity.setActive(false);

        List<CustomerDBEntity> hotelStaff = checkinRepository.getStaffByHotelId(hotelId);

        for (CustomerDBEntity nextStaff: hotelStaff)
        {
            nextStaff.setActive(false);
            customerRepository.saveAndFlush(nextStaff);
        }
        
        hotelRepository.saveAndFlush(hotelRootEntity);
		return new ResponseDTO("Hotel wurde entfernt", false);
	}

    @Override
    public String getVirtualHotelCode() {
        return "";
    }

    @Override
    public Optional<HotelRootDomainEntity> findByCurrentHotelAccessCodeAndActive(String hotelCode, boolean active) {
        return Optional.empty();
    }


    @Override
    public Optional getOne(HotelDomainEntityId hotelId) {
        return Optional.empty();
    }

    @Override
    public HotelDTO convertToDTO(HotelRootDomainEntity hotelEntity) {
        return null;
    }

     


    @Override
    public ResponseDTO deleteHotelActivity(CustomerDomainEntityId customerId, long activityId) throws Throwable {
        CustomerDTO dto = (CustomerDTO) customerService.getById(AppConfigProperties.getTryEntityId(customerId), 0).orElseThrow(()->new RuntimeException("-"));

        if(!dto.isHotelStaff() && !dto.isAdmin())
        {
            return new ResponseDTO("There is no staff rights", true);
        }
        
        HotelActivityDBEntity activity = (HotelActivityDBEntity) getActivityByIdOrInitId(activityId, activityId).orElseThrow(()-> new ResourceNotFoundException("activity not found id=" + activityId));

        activity.setActive(false);
        activityRepository.save(activity);

//        dto.setHotelId(activity.getHotel().getId());

        notificationService.notificateAboutEntityEvent(dto, InnerHotelEvent.EVENT_REMOVE_ACTIVITY, "activityRemoved", customerId);
        return new ResponseDTO("Activity wurde entfernt", false);
    }

//    @Override
//    public void deleteWallPost(WallPostDto wallPostDto)
//    {
//        wallPostRepository.delete(wallPostDto.getInitId()Id());
//    }
}
