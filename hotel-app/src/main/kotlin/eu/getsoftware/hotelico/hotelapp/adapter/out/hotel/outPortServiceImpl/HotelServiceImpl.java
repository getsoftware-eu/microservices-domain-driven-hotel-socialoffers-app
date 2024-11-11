package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.exception.BasicHotelException;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.ActivityDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.WallPostDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.clients.common.utils.ReorderAction;
import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.model.HotelDbActivity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.repository.CheckinRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.repository.CustomerRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelDbEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelEvent;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelWallPost;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository.ActivityRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository.DealRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository.HotelRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository.WallPostRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.viewEntity.model.CustomerDeal;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.out.CheckinPortService;
import eu.getsoftware.hotelico.hotelapp.application.customer.port.out.iPortService.CustomerPortService;
import eu.getsoftware.hotelico.hotelapp.application.deal.domain.infrastructure.dto.CustomerDealDTO;
import eu.getsoftware.hotelico.hotelapp.application.deal.domain.infrastructure.utils.ActivityAction;
import eu.getsoftware.hotelico.hotelapp.application.deal.domain.infrastructure.utils.DealAction;
import eu.getsoftware.hotelico.hotelapp.application.deal.domain.infrastructure.utils.DealStatus;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.ResponseDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.HotelDomainEntity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.customDomainModelImpl.HotelGroupAggregate;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IHotelService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.INotificationService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.LastMessagesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.geom.Point2D;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements IHotelService<HotelDbEntity>
{
	private CustomerPortService<CustomerDBEntity> customerService;
	
	private INotificationService notificationService;	
	
	private CheckinPortService checkinService;
	
	private LastMessagesService lastMessagesService;
	
    private HotelRepository hotelRepository;     
    
    private CustomerRepository customerRepository;    
    
    private ActivityRepository activityRepository;     
	
    private DealRepository dealRepository; 
    
    private WallPostRepository wallPostRepository;
    
    private CheckinRepository checkinRepository;
	
    private ModelMapper modelMapper;
    
    private SimpMessagingTemplate simpMessagingTemplate;
	
	private HotelDomainEntityId specialHotelId = new HotelDomainEntityId(999+"");

    /**
     * eu: single ENTRY POINT TO CREATE DomainEntityChierarchie!!!!
     * @param hotelEntityId
     * @return
     */
    public HotelDomainEntity recreateDomainEntityFromDBProjection(HotelDomainEntityId hotelEntityId)
    {
        HotelDbEntity dbProjection = hotelRepository.findByDomainEntityIdAndActive(hotelEntityId, true)
                .orElseThrow(() -> new RuntimeException("no hotel"));

        return HotelGroupAggregate.getEntityBuilder( dbProjection.getDomainEntityId() )
                .description(dbProjection.getDescription())
                .name(dbProjection.getName())
                .build();
    }
    
    
    public List<HotelDTO> getHotels() {
        List<HotelDbEntity> list = hotelRepository.findByVirtualAndActive(false, true);
        List<HotelDTO> out = new ArrayList<HotelDTO>();
        for (HotelDbEntity hotelRootEntity : list) {
            
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
        List<HotelDbEntity> list = hotelRepository.findByVirtualAndActive(false, true);
        
        Set<String> citiesList = new HashSet<>();
        
        CustomerDBEntity requester = requesterId>0? customerRepository.getOne(requesterId): null;
        
        List<HotelDTO> out = new ArrayList<HotelDTO>();
        for (HotelDbEntity hotelRootEntity : list) {
            
            boolean ignoreDemoHotel = AppConfigProperties.HIDE_DEMO_HOTEL_FROM_HOTEL_LIST && AppConfigProperties.HOTEL_DEMO_CODE.equalsIgnoreCase(hotelRootEntity.getCurrentHotelAccessCode());
            
            if(hotelRootEntity.isActive() && !hotelRootEntity.isVirtual() && !ignoreDemoHotel && (!citiesList.contains(hotelRootEntity.getCity())))
            {
                citiesList.add(hotelRootEntity.getCity());
                HotelDTO hotelDto = convertHotelToDto(hotelRootEntity);
                
                if(requester!=null && hotelRootEntity.getLatitude()>-90 && requester.getLatitude()>-90)
                {
                    double kms = getDistanceKmToHotel(new Point2D.Double(requester.getLatitude(), requester.getLongitude()), hotelRootEntity);
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
        List<HotelDbEntity> list = hotelRepository.findByVirtualAndActive(false, true);
        
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
        for (HotelDbEntity hotelRootEntity : list) {
            
            boolean ignoreDemoHotel = AppConfigProperties.HIDE_DEMO_HOTEL_FROM_HOTEL_LIST && AppConfigProperties.HOTEL_DEMO_CODE.equalsIgnoreCase(hotelRootEntity.getCurrentHotelAccessCode());
           
            if(hotelRootEntity.isActive() && !hotelRootEntity.isVirtual() && !ignoreDemoHotel && (filterCity==null || filterCity.equals(hotelRootEntity.getCity())))
            {
                HotelDTO hotelDto = convertHotelToDto(hotelRootEntity);
                if(requesterOptional.isPresent() && hotelRootEntity.getLatitude()>-90 && requesterOptional.get().getLatitude()>-90)
                {
	                CustomerDBEntity requester = requesterOptional.get();
                    double kms = getDistanceKmToHotel(new Point2D.Double(requester.getLatitude(), requester.getLongitude()), hotelRootEntity);
                    hotelDto.withKmFromMe(kms);
                }
                out.add(hotelDto);

            }
        }
        return out;
    }
    

    @Override
    public Optional<HotelDbEntity> getEntityById(HotelDomainEntityId hotelId) {
        return hotelRepository.findByDomainEntityIdAndActive(hotelId, true);
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
        List<CustomerDBEntity> participantsList = wallPostRepository.getParticipantsByHotelId(hotelId, new Date());

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
        CustomerDBEntity sender = customerRepository.findByDomainId(AppConfigProperties.getTryEntityId(customerId))
		        .orElseThrow(()-> new ResourceNotFoundException("customer not found with initId=" + customerId));
        
        HotelDbActivity activity = (HotelDbActivity) this.getActivityByIdOrInitId(activityId, activityId).orElseThrow(()-> new RuntimeException("not found"));
		
        if(activity==null || action==null)
        {
            return new HotelActivityDTO(activityId);
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
    public void reorderActivitiesInHotel(ReorderAction action, HotelDbActivity activity)
	{
		if(activity.getHotel()==null)
		{
			return;
		}
		
		List<HotelDbActivity> hotelActivities = activityRepository.getAllTimesByHotelId(activity.getHotel().getDomainEntityId());
		
		//sort by orderIndex!!!
		if (hotelActivities.size() > 0) {
			Collections.sort(hotelActivities, new Comparator<HotelDbActivity>() {
				@Override
				public int compare(final HotelDbActivity object1, final HotelDbActivity object2) {
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
		for (HotelDbActivity nextActivity: hotelActivities)
		{
			nextActivity.setOrderIndex(hotelActivities.indexOf(nextActivity));
		}
		
		activityRepository.saveAll(hotelActivities);
		
		activity.setOrderIndex(hotelActivities.indexOf(activity));
	}
	
	@Override
    public String getGpsCity(Point2D.Double latLonPoint)
    {
        List<HotelDbEntity> hotelRootEntities = hotelRepository.findActiveGpsHotels();

        for(HotelDbEntity nextHotelRootEntity : hotelRootEntities)
        {
            double kmFrom = this.getDistanceKmToHotel(latLonPoint, nextHotelRootEntity);

            if(kmFrom<100)
            {
                return nextHotelRootEntity.getCity();
            }

        }

        return null;
    }


    @Override
    public double getDistanceKmToHotel(Point2D.Double from, HotelDbEntity hotelRootEntity)
    {
        if(hotelRootEntity.getLatitude()<=-90)
        {
            return -1;
        }

        double earthRadius = 3958.75; // miles (or 6371.0 kilometers)
        double dLat = Math.toRadians(hotelRootEntity.getLatitude()-from.getX());
        double dLng = Math.toRadians(hotelRootEntity.getLongitude()-from.getY());
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(from.getX())) * Math.cos(Math.toRadians(hotelRootEntity.getLatitude()));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;

        double km = dist /  0.621371192;

        double kmRound = Math.floor(km * 100) / 100;
        
        return kmRound;
    }
	
	@Override
    public List<CustomerDealDTO> getDealsByActivityOrHotelId(CustomerDomainEntityId customerId, HotelDomainEntityId hotelId, long activityId, boolean onlyDealsOfRequester, boolean closed)
	{
		
		//TODO Eugen: get closed deals or Menu!!!!!
		
		List<CustomerDeal> resultList = new ArrayList<>();
		List<CustomerDealDTO> dtoList = new ArrayList<>();
		
		//Eugen: if Customer is staff or admin, will become all deals!!! 

        CustomerDomainEntityId customerEntityId = AppConfigProperties.getTryEntityId(customerId);
        
//        if (customerId == (int)customerId)
//        {
//            customerEntityId = (int)customerId;
//        }

        Optional<CustomerDBEntity> requester = customerRepository.findByDomainId(customerEntityId);
		
		Date filterDateFrom = closed? null: new Date();
		Date filterDateTo = closed? null: new Date();
		
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
                          dealRepository.getActiveByCustomerId(requester.get().getDomainEntityId(), new Date())
                          :
                          dealRepository.getActiveByGuestId(customerId, new Date());
        }
		
		for (CustomerDeal nextDeal: resultList)
		{
			CustomerDealDTO nextDto = convertDealToDto(nextDeal);
			
			dtoList.add(nextDto);
		}
		
		return dtoList;
	}
	
	private CustomerDealDTO convertDealToDto(CustomerDeal deal)
	{
        CustomerDealDTO dto = modelMapper.map(deal, CustomerDealDTO.class);
		
		HotelDbActivity dealActivity = deal.getActivity();
		
		dto.setActivityId(dealActivity.getId());
        dto.setHotelDomainId(dealActivity.getHotel().getDomainEntityId());
       
        dto.setTitle(dealActivity.getTitle());
        dto.setDescription(dealActivity.getDescription());
        dto.setShortDescription(dealActivity.getShortDescription());
       
        dto.setPictureUrl(dealActivity.getPictureUrl());
        dto.setPreviewPictureUrl(dealActivity.getPreviewPictureUrl());
        
		dto.setDealDaysDuration(dealActivity.getDealDaysDuration());
		dto.setActivityArea(dealActivity.getActivityArea());
		dto.setLastMinute(dealActivity.isLastMinute());
		
        if(!deal.isActive())
        {
            dto.setTimeValid(false);
        }
		
		boolean closed = Arrays.asList(DealStatus.REJECTED, DealStatus.CLOSED).contains(deal.getStatus());
		dto.setClosed(closed);
		
		if (deal.getStatus()!=null)
		{
			dto.setDealStatus(deal.getStatus().getName());
		}
		
        return dto;
    }
	
	@Override
    public ResponseDTO deleteDeal(CustomerDomainEntityId customerId, long activityId, int dealId)
	{
        long dealEntityId = (dealId);
        
		CustomerDeal deal = dealRepository.findById(dealEntityId).orElseThrow(()->new RuntimeException("not found"));
		
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
        Optional<CustomerDeal>  dealOptional  = getDealByIdOrInitId(dealDto.getHotelDomainId(), dealDto.getInitId());

        Optional<CustomerDBEntity> sender = customerRepository.findByDomainId(AppConfigProperties.getTryEntityId(guestCustomerId));
        
        if(dealOptional.isPresent())
        {
            var deal = updateDealFromDto(dealOptional.get(), dealDto);

            dealRepository.saveAndFlush(deal);

            return convertDealToDto(deal);
        }
        else
        {
            Optional<HotelDbActivity> activityOptional = getActivityByIdOrInitId((activityId), activityId);
            
            CustomerDeal newDeal = createDeal(sender.get(), guestCustomerId, activityOptional.get());
            return convertDealToDto(newDeal);
        }
//        return null;
	}
 


    private CustomerDeal updateDealFromDto(CustomerDeal deal, CustomerDealDTO activityDealDto)
	{
		//TODO Eugen: updatee dbObject from client DTO
		return null;
	}
	
	@Override
	public CustomerDealDTO addDealAction(CustomerDomainEntityId customerId, long activityId, long givenId, DealAction dealAction, String tablePosition, double totalMoney)
	{
        CustomerDomainEntityId customerEntityId = AppConfigProperties.getTryEntityId(customerId);
		
		CustomerDBEntity sender = customerRepository.findByDomainId(customerEntityId).orElseThrow(()->new RuntimeException("-"));

        Optional<HotelDbActivity> activityOptional = getActivityByIdOrInitId((int)activityId, activityId);
		
        CustomerDealDTO resultDealDto = null;
        
        if(activityOptional.isPresent() /*&& sender != null*/)
		switch (dealAction)
		{
			case NEW_DEAL: {
				
                CustomerDeal newDeal = createDeal(sender, customerId, activityOptional.get());

                resultDealDto = convertDealToDto(newDeal);
                
                break;
            }
			
			case ACCEPT_DEAL: {

                CustomerDeal acceptDeal = new CustomerDeal(sender, activityOptional.get());

                if(sender==null && acceptDeal!=null)
                {
                    CustomerDBEntity anonym = customerService.addGetAnonymCustomer();
                    acceptDeal.setCustomerId(anonym.getDomainEntityId());
                    acceptDeal.setGuestCustomerId(customerEntityId);
                }
                
                acceptDeal.setDealCode(String.valueOf(givenId));

                acceptDeal.setStatus(DealStatus.ACCEPTED);
                
                dealRepository.saveAndFlush(acceptDeal);

                resultDealDto = convertDealToDto(acceptDeal);

                break;
            }
			
			case EXECUTE: {

                List<CustomerDeal> executeDeals = dealRepository.getByInitId(givenId);

                CustomerDeal execute = null;
                
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

                List<CustomerDeal> executeDeals = dealRepository.getByInitId(givenId);

                CustomerDeal execute = null;
                
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
				
				List<CustomerDeal> editDeals = dealRepository.getByInitId(givenId);
                
                if(editDeals.isEmpty() || !editDeals.get(0).getActivity().equals(activityOptional.get()))
                {
					List<DealStatus> filterStatusList = DealStatus.getFilterStatusList(false);
					
					//Eugen: cannot edit closed deals!
                    editDeals = sender!=null?
                                dealRepository.getActiveByCustomerAndActivityId(sender.getDomainEntityId(), activityOptional.get().getId(), filterStatusList, new Date(), new Date())
                                :
                                dealRepository.getActiveByGuestAndActivityId(customerId, activityOptional.get().getId(), filterStatusList, new Date(), new Date());

                    if(editDeals.size()>1)
                    {
                        this.avoidDoubleInitId(editDeals);
                    }
                }
                
                if(!editDeals.isEmpty())
                {
                    for (CustomerDeal next: editDeals)
                    {
                        if(activityOptional.get().equals(next.getActivity()))
                        {
                            return convertDealToDto(next);
                        }
                    }
                }
                
                break;
			}
            
			case REJECT_DEAL: {

                    List<CustomerDeal> rejectDeals = dealRepository.getByInitId(givenId);

                    CustomerDeal rejectDeal = null;

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
            notificationService.sendNotificationToCustomerOrGuest(sender, customerId, HotelEvent.EVENT_DEAL_NEW_UPDATE);
            
            CustomerDBEntity staff = checkinService.getStaffbyHotelId(resultDealDto.getHotelDomainId());
           
			//Notificate STAFF about deal action!!!
            if(staff != null)
            {
                notificationService.sendNotificationToCustomerOrGuest(null, staff.getDomainEntityId(), HotelEvent.EVENT_DEAL_NEW_UPDATE);
            }
        }
        
        return resultDealDto;
	}

    

    public CustomerDeal createDeal(CustomerDBEntity sender, CustomerDomainEntityId guestCustomerId, HotelDbActivity activity)
    {
        CustomerDeal newDeal = new CustomerDeal(sender, activity);

        if(sender==null)
		{
			newDeal.setGuestCustomerId(guestCustomerId);
		}
		
        return newDeal;
    }

    @Override
    public HotelDTO getHotelById(HotelDomainEntityId hotelId) {
	
	    if(hotelId == specialHotelId)
	    {
		    return generateVirtualHotel();
	    }
		
	    HotelDTO out  = hotelRepository.findByDomainEntityIdAndActive(hotelId, true)
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
		HotelDbEntity specialHotelRootEntity = hotelRepository.findByVirtualAndActive(true, true)
				.stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("no virtual hotel found"));
		
		return convertHotelToDto(specialHotelRootEntity);
	}
	
	@Override
    public HotelActivityDTO getHotelActivityById(CustomerDomainEntityId requesterId, long activityId) throws Throwable {
        HotelDbActivity hotelActivity = (HotelDbActivity) getActivityByIdOrInitId(activityId, activityId).orElseThrow(()->new RuntimeException("not found"));

        Optional<CustomerDBEntity> requester = null;
        
        if(hotelActivity!=null)
        {
            requester = customerRepository.findByDomainId((requesterId));
        }
        
        HotelActivityDTO activityDto = convertActivityToDto(hotelActivity, requester.get());

        return activityDto;
    }

    @Override
    public WallPostDTO getWallPostById(WallPostDomainEntityId wallPostId)
    {
        List<WallPostDTO> wallPost = wallPostRepository.getMessageDTOByInitId(wallPostId);
        return wallPost.stream().findFirst().get();
    }

    @Transactional
    @Override
    public HotelDTO addHotel(HotelDTO hotelDto) {
		
		if(!AppConfigProperties.isEmptyString(hotelDto.getEmail()))
		{
			List<HotelDbEntity> hotelsWithSameMail = hotelRepository.findByEmailAndActive(hotelDto.getEmail(), true);
			
			if(!hotelsWithSameMail.isEmpty())
			{
				throw new RuntimeException("The Hotel with this e-mail is already registered. Please contact hotelico Admin.");
			}
		}
		
        HotelDbEntity hotelRootEntity = modelMapper.map(hotelDto, HotelDbEntity.class);

        hotelRootEntity.setConsistencyId(new Date().getTime());

        return convertHotelToDto(hotelRepository.saveAndFlush(hotelRootEntity));
    }

    @Transactional
    @Override
    public HotelActivityDTO addUpdateHotelActivity(CustomerDomainEntityId customerId, HotelActivityDTO hotelActivityDto) throws Throwable {
        if(customerId!=null)
        {
	        Optional<CustomerDBEntity> creatorOpt = customerRepository.findByDomainId(AppConfigProperties.getTryEntityId(customerId));

            //Check creator role
            if(creatorOpt.isPresent())
            {
	            CustomerDBEntity creator = creatorOpt.get();
				
				if(!creator.isHotelStaff() && !creator.isAdmin())
                    return null;
            }
        }
	
	    HotelDbActivity hotelActivity = getActivityByIdOrInitId(hotelActivityDto.getDomainId(), hotelActivityDto.getInitId()).orElse(createHotelActivity(hotelActivityDto));
	    
//		hotelActivity.map(a -> updateHotelActivity(hotelActivityDto))
//				    .orElse(createHotelActivity(hotelActivityDto));
        
        //TODO Eugen: notificate about new/changed activity
        if(hotelActivityDto.getPublishInWall())
        {
			//TODO Eugen: Last Minute!!!!
			if(hotelActivityDto.getLastMinute())
			{
				WallPostDTO checkinNotificationWall = new WallPostDTO(hotelActivity.getHotel().getDomainEntityId(), hotelActivity.getSender().getDomainEntityId());
		
				checkinNotificationWall.getSpecialContent().put("activityId", String.valueOf(hotelActivityDto.getId()));
				checkinNotificationWall.setInitId(new Date().getTime());
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

    @Override
    public Optional<HotelDbActivity> getActivityByIdOrInitId(ActivityDomainEntityId id, long initId)
    {
        if(initId>0)
        {
	        return activityRepository.findByInitIdAndActive(initId, true)
			        .stream().findFirst();
        }    
        
        return activityRepository.findByDomainId(id);
    }
    
    @Override
    public Optional<CustomerDeal> getDealByIdOrInitId(long id, long initId)
    {
        if(initId>0)
        {
            return dealRepository.getByInitId(initId)
		            .stream().findFirst();
        }    
        
        return dealRepository.findByHotelId(id);
    }

    private HotelDbActivity createHotelActivity(HotelActivityDTO hotelActivityDto)
    {
        HotelDbActivity hotelActivity;// ###### CREATE HOTEL OBJECT 

        hotelActivity = modelMapper.map(hotelActivityDto, HotelDbActivity.class);

        HotelDbEntity hotelRootEntity = null;
        
        if(hotelActivityDto.getHotelId()!=null && hotelActivityDto.getHotelId()!=null)
		{
			hotelRootEntity = hotelRepository.findByDomainEntityIdAndActive(hotelActivityDto.getHotelId(), true).orElseThrow();
		}

        CustomerDBEntity sender = null;

        if(hotelActivityDto.getHotelId()!=null && hotelActivityDto.getHotelId()!=null)
		{
			sender = customerService.getEntityById(hotelActivityDto.getSenderDomainId()).orElseThrow(()->new RuntimeException("not found"));
		}

        if(hotelRootEntity !=null)
		{
			hotelActivity.setHotel(hotelRootEntity);
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

    public HotelDbActivity fillActivityFromDto(HotelDbActivity hotelActivity, HotelActivityDTO hotelActivityDto)
    {
        hotelActivity.setInitId(hotelActivityDto.getInitId());

        if(hotelActivity.getValidFrom()==null)
		{
			hotelActivity.setValidFrom(new Date());
		}

        hotelActivity.setActive(true);

        if(hotelActivity.getValidTo()==null)
		{
			hotelActivity.setValidTo(AppConfigProperties.convertToDate(LocalDateTime.now().plusDays(7)));
		}

        hotelActivity.setConsistencyId(new Date().getTime());
        
        return hotelActivity;
    }

    @Override
    public WallPostDTO addWallPost(WallPostDTO wallPostDto) throws Throwable {

        CustomerDBEntity sender = customerService.getByDomainId(wallPostDto.getSenderId()).orElseThrow(() -> new RuntimeException("wallpost not found"));
        
        if(wallPostDto.getHotelId()==null)
        {
            wallPostDto.setHotelId(customerService.getCustomerHotelId(sender.getDomainEntityId()));
        }
        
        if(wallPostDto.getCreationTime()<=0)
        {
            wallPostDto.setCreationTime(new Date().getTime());
        }
        
        HotelDbEntity hotelRootEntity = this.getEntityById(wallPostDto.getHotelId()).orElseThrow(()-> new RuntimeException("wallpost not found"));

        HotelWallPost newMessage = new HotelWallPost(wallPostDto.getMessage(), sender, hotelRootEntity);

        newMessage.setInitId(wallPostDto.getInitId());
        newMessage.setTimestamp(new Timestamp(wallPostDto.getCreationTime()));
        newMessage.setSpecialWallContent(wallPostDto.getSpecialContent().toString());

        //wallPost is valid only 1 day | 1 Month for DEMO...
        if(AppConfigProperties.HOTEL_DEMO_CODE.equalsIgnoreCase(hotelRootEntity.getCurrentHotelAccessCode()))
        {
            newMessage.setValidUntil(AppConfigProperties.convertToDate(LocalDateTime.now().plusMonths(6)));
        }
        else {
            newMessage.setValidUntil(AppConfigProperties.convertToDate(LocalDateTime.now().plusDays(3)));
        }

        wallPostRepository.saveAndFlush(newMessage);

        notificationService.notificateAboutEntityEvent(customerService.convertCustomerWithHotelToDto(sender, wallPostDto.getHotelId()), HotelEvent.EVENT_WALL_NEW_MESSAGE, "New Wall message", newMessage.getId());
        
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
        
		Optional<HotelDbEntity> hotelRootEntityOpt = hotelRepository.findById(hotelDto.getId());
	
	    HotelDbEntity hotelRootEntity = null;
	    
		if(hotelRootEntityOpt.isPresent())
        {
	        hotelRootEntity = hotelRootEntityOpt.get();
			        
            hotelRootEntity.setDescription(hotelDto.getDescription());
            hotelRootEntity.setInfo(hotelDto.getInfo());
            hotelRootEntity.setName(hotelDto.getName());
            hotelRootEntity.setEmail(hotelDto.getEmail());
            hotelRootEntity.setCurrentHotelAccessCode(hotelDto.getCurrentHotelAccessCode());
            hotelRootEntity.setCity(hotelDto.getCity());
            hotelRootEntity.setStreet(hotelDto.getStreet());
            hotelRootEntity.setHouse(hotelDto.getHouse());
            hotelRootEntity.setPostalCode(hotelDto.getPostalCode());
            hotelRootEntity.setFax(hotelDto.getFax());
            hotelRootEntity.setPhone(hotelDto.getPhone());
			
            hotelRootEntity.setRating(hotelDto.getRating());
//            hotel.setPictureUrl(hotelDto.getPictureUrl());

			//Eugen: important, consistencyId only here to create!
            hotelRootEntity.setConsistencyId(new Date().getTime());
            
            if(hotelDto.getCreationTime()>0)
            {
                hotelRootEntity.setCreationTime(hotelDto.getCreationTime());
            }   
			
			if(!AppConfigProperties.isEmptyString(hotelDto.getWellcomeMessage()))
            {
                hotelRootEntity.setWellcomeMessage(hotelDto.getWellcomeMessage());
            }
            
            hotelRepository.saveAndFlush(hotelRootEntity);
        }
		
        return convertHotelToDto(hotelRootEntity);
    }

    @Override
    public CustomerDTO addGuestAction(CustomerDomainEntityId guestCustomerId, String action, Long hotelId, CustomerDTO guestDto) {
        return null;
    }

    private HotelDTO convertHotelToDto(HotelDbEntity hotelRootEntity)
    {
        if(hotelRootEntity == null)
        {
            throw new IllegalArgumentException("HotelRootEntity is null");
        }

        HotelDTO dto = modelMapper.map(hotelRootEntity, HotelDTO.class);
        
        int activityNumber = 0;
        int customerNumber = 0;
        
        try{
            activityNumber = activityRepository.getTimeValidCounterByHotelId(hotelRootEntity.getDomainEntityId(), new Date());
            customerNumber = checkinRepository.getFullCheckinCountExcludingStaffByHotelId(hotelRootEntity.getId(), new Date());
        }
        catch (Exception e){
            ;//TODO Eugen.
        }
        
//        dto.setActivityNumber(activityNumber);
//        dto.setCustomerNumber(customerNumber);
//        dto.setCreationTime(hotelRootEntity.getCreationTime());

		Map<Long, String> anonymeGuests = getNotLoggedGuestPushIdsByHotel(hotelRootEntity);
		
//		dto.setAnonymeGuestNumber(anonymeGuests.keySet().size());
		
        //TODO EUGEN: only active and time valid activities query!
        return dto;
    }

	
    @Override
    public HotelActivityDTO updateHotelActivity(HotelActivityDTO hotelActivityDto)
    {
        Optional<HotelDbActivity> hotelActivityOpt = getActivityByIdOrInitId(hotelActivityDto.getDomainId(), hotelActivityDto.getInitId());
	
	    HotelDbActivity hotelActivity = null;
		
	    if(hotelActivityOpt.isPresent())
        {
            hotelActivity = fillHotelFromDto(hotelActivityDto, hotelActivityOpt.get());
            
            activityRepository.save(hotelActivity);
        }
		
        /// update DTO
        return convertActivityToDto(hotelActivity, null);
    }

    @Override
    public Map<Long, String> getNotLoggedGuestPushIdsByHotel(HotelDbEntity hotelRootEntity) {
        return null;
    }

    @Override
	public Map<Long,String> getNotLoggedGuestPushIdsByHotel(HotelDomainEntity hotelRootEntity)
	{
		Map<Long, String> guestsToPushId = new HashMap<>();
		
		if(hotelRootEntity == null)
		{
			return guestsToPushId;
		}
		
		//TODO convert string data to own structure
		
		String hotelPushIds = hotelRootEntity.getGuestPushIds()!=null?  hotelRootEntity.getGuestPushIds() : "";
		
		splitPushIds(guestsToPushId, hotelPushIds);
		
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
		
		List<HotelDbEntity> allActiveHotelRootEntities = hotelRepository.findByVirtualAndActive(false, true);
		
		for (HotelDbEntity nextHotelRootEntity : allActiveHotelRootEntities)
		{
			String hotelUnsubscribePushIds = nextHotelRootEntity.getUnsubscribeNotificationPushIds()!=null?  nextHotelRootEntity.getUnsubscribeNotificationPushIds() : "";
			
			splitPushIds(unsubscribeGuestsToPushId, hotelUnsubscribePushIds);
		}
		
		return unsubscribeGuestsToPushId;
	}

	public HotelDbActivity fillHotelFromDto(HotelActivityDTO hotelActivityDto, HotelDbActivity hotelActivity)
    {
//        hotelActivity = modelMapper.map(hotelActivityDto, HotelActivity.class);
        
        hotelActivity.setDescription(hotelActivityDto.getDescription());
        hotelActivity.setShortDescription(hotelActivityDto.getShortDescription());
        hotelActivity.setTitle(hotelActivityDto.getTitle());
        //IMPORTANT Eugen: not here
        //            hotelActivity.setPictureUrl(hotelActivityDto.getPictureUrl());

        hotelActivity.setValidFrom(hotelActivityDto.getValidFrom() != null ? hotelActivityDto.getValidFrom() : new Date());

        hotelActivity.setValidTo(hotelActivityDto.getValidTo() != null && !hotelActivityDto.getLastMinute() ? hotelActivityDto.getValidTo() : hotelActivityDto.getLastMinute() ? AppConfigProperties.convertToDate(LocalDateTime.now().plusDays(1).withHour(4)) : AppConfigProperties.convertToDate(LocalDateTime.now().plusDays(7)));

        hotelActivity.setActive(hotelActivityDto.isActiveBoolean());
        hotelActivity.setLastMinute(hotelActivityDto.getLastMinute());
         

        hotelActivity.setConsistencyId(new Date().getTime());
        
        return hotelActivity;
    }

    @Override
    public WallPostDTO addUpdateWallPost(WallPostDTO wallPostDto) throws Throwable {
        List<HotelWallPost> wallPost = wallPostRepository.getMessageBySenderAndInitId(wallPostDto.getSenderId(), wallPostDto.getDomainId());

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
        List<HotelWallPost> wallPosts = wallPostRepository.getMessageByInitId(wallPostDto.getDomainId());
	
	    HotelWallPost updateWallPost = wallPosts.stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("Wallpost not found with initId=" + wallPostDto.getInitId()));
       
        HotelWallPost entity = wallPostRepository.saveAndFlush(updateWallPost);
        return modelMapper.map(entity, WallPostDTO.class);
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
        Optional<CustomerDBEntity> requesterOpt = customerRepository.findByDomainId(requesterId);

        HotelDomainEntityId virtualHotelId = lastMessagesService.getInitHotelId();
       
        List<HotelDbActivity> list =  new ArrayList<>();
	
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
                list = activityRepository.findTimeValidActive(new Date());
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
                list = activityRepository.getTimeValidByHotelId(hotelId, new Date());
            }
        }
        
        List<HotelActivityDTO> out = new ArrayList<HotelActivityDTO>();
        
        //TODO EUGEN how to set seen activities
        //if(requester!=null && requester.getSeenActivities()!=null)
        {
//            requester.getSeenActivities().clear();
            
            Map<Long, CustomerDeal> guestActivityToDealMap = new HashMap<>();
            
            if(requesterOpt.isEmpty() && requesterId!=null)
            {
                List<CustomerDeal> guestDealsList = dealRepository.getActiveByGuestId(requesterId, new Date());

                if(!guestDealsList.isEmpty())
                {
                    for (CustomerDeal next: guestDealsList)
                    {
                        guestActivityToDealMap.put(next.getActivity().getId(), next);
                    }
                }
            }
            
            for (HotelDbActivity hotelActivity : list) {
//                requester.getSeenActivities().add(hotelActivity);
                
                HotelActivityDTO activityDto = this.convertActivityToDto(hotelActivity, requester);
                
                if(activityDto.getValidDealId()==0 && guestActivityToDealMap.containsKey(hotelActivity.getId()))
                {
                    CustomerDeal deal = guestActivityToDealMap.get(hotelActivity.getId());
                    
                    if(hotelActivity.equals(deal.getActivity()))
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
		Integer count = dealRepository.countActiveDealsByCustomerOrGuest(customerId, guestId, new Date());
	
		return count!=null? count : 0;
	}
	
    @Override
    public HotelActivityDTO convertActivityToDto(HotelDbActivity hotelActivity, CustomerDBEntity requester)
    {
	    Objects.requireNonNull(hotelActivity);

        HotelActivityDTO activityDto = modelMapper.map(hotelActivity, HotelActivityDTO.class);
        
        activityDto.setLikeCounter(hotelActivity.getLikedCustomerDomainEntityIds().size());
        activityDto.setSubscribeCounter(hotelActivity.getSubscribeCustomerDomainEntityIds().size());
        
//        activityDto.setLikedByMe(hotelActivity.getLikedCustomers().contains(requester));
//        activityDto.setSubscribeByMe(hotelActivity.getSubscribeCustomers().contains(requester));

        List<CustomerDeal> activityDeals = new ArrayList<>();
        
        if(requester != null)
        {
			List<DealStatus> filterStatuslist = DealStatus.getFilterStatusList(false);
            activityDeals = dealRepository.getActiveByCustomerAndActivityId(requester.getDomainEntityId(), hotelActivity.getId(), filterStatuslist, new Date(), new Date());
        }
        
        if(!activityDeals.isEmpty())
        {
            activityDto.setValidDealId(activityDeals.get(0).getInitId());
        }

        HotelDbEntity hotelRootEntity = hotelActivity.getHotel();

        int otherActivityNumber = 0;
        int hotelCustomerNumber = 0;
        
        if(hotelRootEntity !=null)
        {
            activityDto.setHotelId(hotelRootEntity.getDomainEntityId());
            activityDto.setHotelName(hotelRootEntity.getName());
            activityDto.setHotelCity(hotelRootEntity.getCity());

            try
            {
                otherActivityNumber = activityRepository.getTimeValidCounterByHotelId(hotelRootEntity.getDomainEntityId(), new Date());
                hotelCustomerNumber = checkinRepository.getFullCheckinCountExcludingStaffByHotelId(hotelRootEntity.getDomainEntityId(), new Date());
            }
            catch (Exception e)
            {
                ;//TODO Eugen.
            }
        }
        activityDto.setHotelCustomerNumber(hotelCustomerNumber);
//        activityDto.setLikeCounter(5);
        activityDto.setOtherActivityNumber(otherActivityNumber>0? otherActivityNumber-1 : 0);
        
        boolean isActivityTimeValid = hotelActivity.getValidTo()!=null && hotelActivity.getValidFrom()!=null && hotelActivity.getValidTo().after(new Date()) && hotelActivity.getValidFrom().before(new Date());
        activityDto.setTimeValid(isActivityTimeValid);

        if(activityDto.getInitId() ==0)
        {
            //set activityId, if was not set
            activityDto.setInitId(hotelActivity.getInitId());
        }
        
        return activityDto;
    }

    @Override
    public List<HotelActivityDTO> getHotelActivitiesBySenderAndHotelId(CustomerDomainEntityId senderId, HotelDomainEntityId hotelId)
    {
        List<HotelDbActivity> list = new ArrayList<>();
        
        CustomerDBEntity customerEntity = customerService.getEntityById(AppConfigProperties.getTryEntityId(senderId)).orElseThrow(()-> new RuntimeException("not found"));
        
        if(customerEntity !=null && customerEntity.isHotelStaff() || customerEntity.isAdmin())
        {
            list = activityRepository.getAllTimesByHotelId(hotelId);
        }
        else{
            list = activityRepository.getTimeValidByHotelId(hotelId, new Date());
        }
        
        List<HotelActivityDTO> out = new ArrayList<HotelActivityDTO>();
        for (HotelDbActivity hotelActivity : list) {
            
            HotelActivityDTO activityDto = convertActivityToDto(hotelActivity, customerEntity);
            out.add(activityDto);
        }
        return out;
    }

    @Override
    public List<WallPostDTO> getWallPostsByHotelId(HotelDomainEntityId hotelId)
    {
        List<HotelWallPost> list = wallPostRepository.getByHotelId(hotelId, new Date());
       
        List<WallPostDTO> out = new ArrayList<WallPostDTO>();

        boolean wallIsDemoValid = AppConfigProperties.NO_WALL_EXPIRES_FOR_DEMOHOTEL && hotelId == hotelRepository.getDemoHotelId();
        
        for (HotelWallPost wallPost : list) {

            if(wallIsDemoValid || wallPost.getValidUntil().after(new Date()))
            {
                WallPostDTO dto = convertWallToDto(wallPost);
                out.add(dto);
            }
        }
        return out;
    }

    public WallPostDTO convertWallToDto(HotelWallPost wallPost)
    {
        WallPostDTO dto = modelMapper.map(wallPost, WallPostDTO.class);

        dto.setCreationTime(wallPost.getTimestamp().getTime());

        CustomerDBEntity sender = wallPost.getSender();

        if(sender!=null)
        {
            dto.setSenderName(sender.getFirstName() + (sender.getLastName() != null ? " " + sender.getLastName() : ""));

            dto.setSenderId(sender.getDomainEntityId());

            dto.setHotelStaff(sender.isHotelStaff());
        }
        
        if(dto.getInitId()==0)
        {
            dto.setInitId(wallPost.getTimestamp().getTime());
        }

        dto.setSendTimeString(wallPost.getTimestamp() != null ? AppConfigProperties.getTimeFormatted(wallPost.getTimestamp()) : null);

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
	
	                Optional<HotelDbActivity> activityOpt = getActivityByIdOrInitId(activityId, activityId);
                    
                    if(activityOpt.isPresent())
                    {
	                    HotelDbActivity activity = activityOpt.get();
                        dto.getSpecialContent().put("name", activity.getTitle());
                        dto.getSpecialContent().put("hotelId", activity.getHotel().getId()+"");
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

    public void avoidDoubleInitId(List<CustomerDeal> resultList)
    {
        if(resultList!=null && resultList.size()>1)
        {
            CustomerDeal first = resultList.get(0);

            for (int i = 1; i < resultList.size(); i++)
            {
                CustomerDeal nextObj = resultList.get(i);

                if(!nextObj.equals(first) && nextObj.getInitId() == first.getInitId())
                {
                    nextObj.setInitId(ThreadLocalRandom.current().nextInt(1, 999999));

                    dealRepository.saveAndFlush(nextObj);
                }
            }
        }
    }

	@Override
    public CustomerDTO addGuestAction(long guestCustomerId, String action, Long hotelId, CustomerDTO guestDto)
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
		HotelDbEntity hotelRootEntity = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("hotel with id = " + finalHotelId));

		if("addGuestPushId".equalsIgnoreCase(action))
		{
			Map<Long, String> getNotLoggedGuestPushIds = getNotLoggedGuestPushIdsByHotel(hotelRootEntity);
			
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
			Map<Long, String> getNotLoggedGuestPushIds = getNotLoggedGuestPushIdsByHotel(hotelRootEntity);

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
			
			Map<Long, String> getNotLoggedGuestPushIds = getNotLoggedGuestPushIdsByHotel(hotelRootEntity);
			
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
		
		CustomerDBEntity customerEntity = customerRepository.findByDomainEntityIdAndActive(customerId, true).orElseThrow();
		
		if(customerEntity ==null || !(customerEntity.isAdmin() || customerEntity.isHotelStaff()))
		{
			return new ResponseDTO("There is no staff rights", true);
		}
		
		HotelDbEntity hotelRootEntity = hotelRepository.findByDomainEntityIdAndActive(hotelId, true).orElseThrow();
		
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
    public Optional<HotelDbEntity> findByCurrentHotelAccessCodeAndActive(String hotelCode, boolean active) {
        return null;
    }

    @Override
    public Optional getOne(HotelDomainEntityId hotelId) {
        return Optional.empty();
    }

    @Override
    public HotelDTO convertToDTO(HotelDbEntity hotelEntity) {
        return null;
    }



    @Override
    public ResponseDTO deleteHotelActivity(CustomerDomainEntityId customerId, long activityId) throws Throwable {
        CustomerDTO dto = (CustomerDTO) customerService.getById(AppConfigProperties.getTryEntityId(customerId), 0).orElseThrow(()->new RuntimeException("-"));

        if(!dto.isHotelStaff() && !dto.isAdmin())
        {
            return new ResponseDTO("There is no staff rights", true);
        }
        
        HotelDbActivity activity = (HotelDbActivity) getActivityByIdOrInitId(activityId, activityId).orElseThrow(()-> new ResourceNotFoundException("activity not found id=" + activityId));

        activity.setActive(false);
        activityRepository.save(activity);

//        dto.setHotelId(activity.getHotel().getId());

        notificationService.notificateAboutEntityEvent(dto, HotelEvent.EVENT_REMOVE_ACTIVITY, "activityRemoved", activityId);
        return new ResponseDTO("Activity wurde entfernt", false);
    }

//    @Override
//    public void deleteWallPost(WallPostDto wallPostDto)
//    {
//        wallPostRepository.delete(wallPostDto.getInitId()Id());
//    }
}
