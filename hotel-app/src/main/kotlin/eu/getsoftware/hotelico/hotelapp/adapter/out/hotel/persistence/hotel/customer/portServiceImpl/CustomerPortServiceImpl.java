package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.customer.portServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerRequestDTO;
import eu.getsoftware.hotelico.clients.api.infrastructure.notification.NotificationService;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.checkin.model.CustomerHotelCheckin;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.checkin.repository.CheckinRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.customer.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.customer.model.Language;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.customer.model.domainServiceImpl.CustomerPersistGatewayServiceImpl;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.customer.repository.CustomerRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.hotel.model.HotelEvent;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.hotel.model.HotelRootEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.hotel.repository.DealRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.hotel.repository.HotelRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.hotel.repository.LanguageRepository;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.out.CheckinPortService;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.CustomerAggregate;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.port.out.iPortService.CustomerPortService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties.convertToDate;

@Service
public class CustomerPortServiceImpl implements CustomerPortService 
{
    @Autowired
    private IHotelService hotelService;
    
    @Autowired
    private HotelRepository hotelRepository;     
    
    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private CustomerRepository customerRepository;
	
	@Autowired
	private LastMessagesService lastMessagesService;	
	
	@Autowired
    private LoginHotelicoService loginService;
	
	@Autowired
    private LanguageRepository languageRepository;
    
    @Autowired
    private MailService mailService;     
    
	@Autowired
    private CheckinPortService checkinService; 
        
	@Autowired
    private CheckinRepository checkinRepository; 
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private DealRepository dealRepository;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private CustomerPersistGatewayServiceImpl customerPersistGatewayService;
    
    @Autowired
    private IMessagingProducerService<HotelEvent> messagingService;
    
    @Override
    public List<CustomerRootEntity> getCustomers() {
        return customerRepository.findByActive(true);
    }
    
    public List<CustomerDTO> getCustomerDTOs() {

        return getCustomers().stream().map((nextObj) -> {
            //TODO Eugen: get all customer hotelId with one query!
            long hotelId = getCustomerHotelId(nextObj.getId());
            return convertCustomerToDto(nextObj, hotelId);
        }
        ).collect(Collectors.toCollection(ArrayList::new));
        
    }
    
    @Override
    public long getCustomerHotelId(long customerId){
        return lastMessagesService.getCustomerHotelId(customerId);
    }
    
    @Override
    public Optional<CustomerRootEntity> getById(long customerId, long dtoRequesterId) {
        
        CustomerRootEntity customerEntity = customerPersistGatewayService.findEntityById(customerId);
        
        CustomerDTO out = null;
       
        if(customerId == dtoRequesterId){
            
            //I want MY Profile!
            out = convertMyCustomerToFullDto(customerEntity);
        }
        else{ //fremd profile
            
            long requesterHotelId = getCustomerHotelId(customerId);
            long hotelId = getCustomerHotelId(customerEntity.getId());

            out = convertCustomerToDto(customerEntity, hotelId);
            
            boolean isPartnerInMyHotelWithFullCheckin = requesterHotelId>0 && requesterHotelId==hotelId && checkinRepository.isFullCheckinForCustomerByHotelId(out.getId(), out.getHotelId(), new Date());

            out.setInMyHotel(isPartnerInMyHotelWithFullCheckin);

            if(dtoRequesterId >0)
            {
                CustomerRootEntity requester = getEntityById(dtoRequesterId).orElseThrow(()-> new RuntimeException("no dtoRequesterId"));
                out = setDtoLastMessageWithRequester(requester, out);
            }
        }
        
        return out;
    }
    
    @Override
    public Optional<CustomerRootEntity> getEntityById(long customerId) {
        return customerRepository.findById(customerId);
    }

    @Transactional
    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDto, String password) {
                
        if(getByEmail(customerDto.getEmail()).isPresent())
        {
            customerDto.setErrorResponse("Email already registered");
            return customerDto;
        }
        
        CustomerRootEntity customerEntity = modelMapper.map(customerDto, CustomerRootEntity.class);

        fillCustomerFromDto(customerDto, customerEntity);
       
        customerEntity = customerRepository.saveAndFlush(customerEntity);
    
        long initHotelId = lastMessagesService.getInitHotelId();

        if(customerDto.getHotelId()>0)
        {
            HotelRootEntity hotelRootEntity = hotelRepository.findById(customerDto.getHotelId()).orElseThrow(()-> new RuntimeException("hotel not found"));
          
            if(hotelRootEntity !=null && customerDto.isHotelStaff())
            {
                customerDto.setCheckinFrom(new Date());
                customerDto.setCheckinTo(convertToDate(LocalDateTime.now().plusYears(10)));
                checkinService.setCustomerCheckin(customerDto, customerEntity);

                if (customerDto.getEmail() != null)
                {
                    mailService.sendMail(customerDto.getEmail(), "HoteliCo staff registration", "You have now a staff account for '" + hotelRootEntity.getName() + "' hotel in Hotelico. Your password is: '" + customerDto.getPassword() + "'. \nYour HoteliCo team.", null);
                }
                
                customerEntity.setHotelStaff(true);
                customerEntity = customerRepository.saveAndFlush(customerEntity);

                initHotelId = hotelRootEntity.getId();
            }
        }


        if(customerDto.getPassword()!=null)
        {
            password = customerDto.getPassword();
        }
        
        long passwordHash = loginService.getCryptoHash(customerEntity, password);
    
        CustomerAggregate aggregate = customerEntity.getEntityAggregate();
    
        aggregate.setPasswordHash(passwordHash);
        aggregate.setPasswordValue(password);
        aggregate.setLogged(true);
        //customer.updateLastSeenOnline();
        
		CustomerDTO dto =  convertMyCustomerToFullDto(customerRepository.saveAndFlush(customerEntity));
    
        messagingService.sendCustomerNotification(dto, hotelEvent);
        
        //// NOTIFICATE OTHERS!!!!

        if(!dto.isHotelStaff() && !dto.isAdmin())
        {
            notificationService.notificateAboutEntityEvent(dto, HotelEvent.EVENT_REGISTER, "Now Registered!", dto.getId());
        }

        return dto;
    }
    
    

    @Override
    @Transactional
    public CustomerDTO synchronizeCustomerToDto(CustomerDTO dto)
    {
        if(dto.getId()<=0)
        {
            return dto;
        }
        
        long dtoConsistencyId = dto.getCustomerConsistencyId();
        
        long customerDbConsistencyId = lastMessagesService.getCustomerConsistencyId(dto.getId());
        
        //TODO Eugen: ONLY DTO UPDate hier
        if(customerDbConsistencyId > dtoConsistencyId)
        {
            CustomerRootEntity customerEntity = customerRepository.getOne(dto.getId());
            
            if(customerEntity ==null)
            {
                dto.setId(-1L);
                return dto;
            }
            
//            int hotelId = getCustomerHotelId(customer.getId());

            dto = convertMyCustomerToFullDto(customerEntity);
        }
        
        return dto;
    }

    
    
    
    
   
    
    @Override
    @Transactional
    public CustomerDTO updateCustomer(CustomerRequestDTO customerDto, int updatorId) {
        
        CustomerRootEntity customerEntity = customerRepository.getOne(customerDto.getId());
    
        CustomerAggregate customerAggregate = new CustomerAggregate(customerEntity);
        
        if(customerDto.getSystemMessages()!=null && !customerDto.getSystemMessages().isEmpty())
        {
           if(customerEntity !=null && customerDto.getSystemMessages().containsKey("guestCustomerId"))
           {
               Long guestId = Long.parseLong(customerDto.getSystemMessages().get("guestCustomerId"));

               if(guestId!=null && guestId>0)
               {
                   this.relocateGuestDealsToLoggedCustomer(customerEntity, guestId);
               }
               
           } 
           
           if(customerEntity !=null && customerDto.getSystemMessages().containsKey(AppConfigProperties.PUSH_CHROME_ID))
           {
               customerEntity.setPushRegistrationId(customerDto.getSystemMessages().get(AppConfigProperties.PUSH_CHROME_ID));
           }
		   
		   if(customerEntity !=null && customerDto.getSystemMessages().containsKey("latitude") && customerDto.getSystemMessages().containsKey("longitude"))
           {
               //eugen2022
               customerAggregate.setLatitude(Double.parseDouble(customerDto.getSystemMessages().get("latitude")));
               customerAggregate.setLongitude(Double.parseDouble(customerDto.getSystemMessages().get("longitude")));
           }
           
           if(customerDto.getSystemMessages().containsKey("feedbackMessage"))
           {
               String feedbackMessage = customerDto.getSystemMessages().get("feedbackMessage");
              
               String fromName = customerDto.getSystemMessages().get("fromName");
               String fromEmail = customerDto.getSystemMessages().get("fromMail");
               
               if(AppConfigProperties.isEmptyString(fromName))
               {
                   fromName = customerEntity.getFirstName();
               }
               
               try
               {
                  //Eugen: decodes encodeUriComponent!!!
                  feedbackMessage = URLDecoder.decode(feedbackMessage, "UTF-8");
               }
               catch (UnsupportedEncodingException e)
               {
                  e.printStackTrace();
               }
               
               mailService.sendMail("info@hotelico.de", "HoteliCo feedback from " + fromName + " " + (customerEntity.getLastName() != null ? customerEntity.getLastName() : "") + "(id=" + customerEntity.getId() + ")", feedbackMessage + (fromEmail != null ? " [from " + fromEmail + "]" : ""), null);
           }
			
			if(customerDto.getSystemMessages().containsKey("mailList"))
           {
			   notificationService.sendMailList(customerEntity, customerDto.getSystemMessages());
           }  
           
           if(customerDto.getSystemMessages().containsKey("hotelFeedMessage"))
           {
			   notificationService.sendFeedMessage(customerEntity, customerDto.getSystemMessages());
            }          
           
           customerDto.setSystemMessages(new HashMap<String, String>());
        }
       
        if(customerEntity !=null)
        {
            fillCustomerFromDto(customerDto, customerEntity);
            customerAggregate.setCity(customerDto.getCity());
            customerAggregate.setOriginalCity(customerDto.getOriginalCity());
            customerAggregate.setJobTitle(customerDto.getJobTitle());
            customerAggregate.setJobDescriptor(customerDto.getJobDescriptor());
            customerAggregate.setFirstName(customerDto.getFirstName());
            customerAggregate.setLastName(customerDto.getLastName());
            customerAggregate.setStatus(customerDto.getStatus());
            customerAggregate.setSex(customerDto.getSex());
            customerAggregate.setShowAvatar(customerDto.getShowAvatar());
            customerAggregate.setAllowHotelNotification(customerDto.getAllowHotelNotification());
            customerAggregate.setShowInGuestList(customerDto.getShowInGuestList());
            
            if(customerDto.getEmail()!=null)
            {
                customerAggregate.setEmail(customerDto.getEmail());
            }
    
            customerAggregate.setCompany(customerDto.getCompany());
            customerAggregate.setPrefferedLanguage(customerDto.getPrefferedLanguage());
            customerAggregate.setProfileImageUrl(customerDto.getProfileImageUrl());
            
            //EUGEN: only disable(true)!!! enable is not allowed!
			if(customerDto.getHideCheckinPopup())
			{
                customerAggregate.setHideCheckinPopup(customerDto.getHideCheckinPopup());
			}
			
			if(customerDto.getHideChromePushPopup())
			{
                customerAggregate.setHideChromePushPopup(customerDto.getHideChromePushPopup());
			}
            
            //EUGEN: only disable(true)!!! enable is not allowed!
            if(customerDto.getHideHotelListPopup())
			{
                customerAggregate.setHideHotelListPopup(customerDto.getHideHotelListPopup());
			}
            
            //EUGEN: only disable(true)!!! enable is not allowed!
            if(customerDto.getHideWallPopup())
			{
                customerAggregate.setHideWallPopup(customerDto.getHideWallPopup());
			}
			
            if(updatorId == customerEntity.getId())
            {
                customerAggregate.setLogged(true);
            }
            
            if(customerDto.getPassword()!=null && !customerDto.getPassword().isEmpty() && customerDto.getPassword().length()>5)
            {
                long passwordHash = loginService.getCryptoHash(customerEntity, customerDto.getPassword());
                customerAggregate.setPasswordHash(passwordHash);
                customerAggregate.setPasswordValue(customerDto.getPassword());
            }

            if(customerEntity.isGuestAccount() && customerEntity.getEmail()!=null && customerEntity.getLastName()!=null && customerEntity.getPasswordHash()!=null)
            {
                customerAggregate.setGuestAccount(false);
            }
            
            long consistencyId = new Date().getTime();
            customerAggregate.setConsistencyId(consistencyId);

            lastMessagesService.updateCustomerConsistencyId(customerEntity.getId(), consistencyId);
            
            customerRepository.saveAndFlush(customerEntity);
        }
        
//        int hotelId = getCustomerHotelId(customerDto.getId());

        CustomerDTO dto = convertMyCustomerToFullDto(customerEntity);

        return dto;
    }

    @Override
    public boolean relocateGuestDealsToLoggedCustomer(ICustomerRootEntity customerEntity, Long guestCustomerId) {
        return false;
    }

    @Override
    public CustomerDTO convertCustomerToDto(ICustomerRootEntity customerEntity, long hotelId) {
        return null;
    }

    @Override
    public CustomerDTO convertCustomerToDto(ICustomerRootEntity customerEntity, boolean fullSerialization, CustomerHotelCheckin validCheckin) {
        return null;
    }

    @Override
    public CustomerDTO convertMyCustomerToFullDto(ICustomerRootEntity customerEntity) {
        return null;
    }

    @Override
    public boolean relocateGuestDealsToLoggedCustomer(CustomerRootEntity customerEntity, Long guestCustomerId)
    {
        if(customerEntity ==null || guestCustomerId==null)
        {
            return false;
        }
        
        List<CustomerDeal> anonymGuestDeals = dealRepository.getAnonymDealsByGuestId(guestCustomerId);
        
        for (CustomerDeal next: anonymGuestDeals)
        {
            next.setCustomer(customerEntity);
//            next.setGuestCustomerId(0);
            dealRepository.saveAndFlush(next);
        }
        
        return !(anonymGuestDeals.isEmpty());
    }

    private void fillCustomerFromDto(CustomerDTO customerDto, CustomerRootEntity customerEntity)
    {
        //NOTE eugen: hier set only different [Entity-Dto] fields
        String[] languageDescriptions = customerDto.getLanguages();
        customerEntity.getLanguageSet().clear();
        
        CustomerAggregate customerAggregate = customerEntity.getEntityAggregate();
        
        if(languageDescriptions!=null)
        {
            for (int i = 0; i < languageDescriptions.length; i++)
            {
                String shortDescription = languageDescriptions[i];
                Language nextLanguage = languageRepository.findByDescriptionShort(shortDescription);

                if(nextLanguage==null && shortDescription!=null)
                {
                    //add Language, if it is not exists
                    nextLanguage = new Language();
                    nextLanguage.setDescriptionShort(shortDescription);
                    nextLanguage.setDescriptionLong(shortDescription);
                    languageRepository.saveAndFlush(nextLanguage);
                }

                if(nextLanguage!=null && nextLanguage.getId()>0)
                {
                    customerEntity.getLanguageSet().add(nextLanguage);
                }
            }
        }
        
        if(customerDto.getBirthdayTime()!=null)
        {
            Long birtdayTime = Long.parseLong(customerDto.getBirthdayTime());
            Date birthdayDate = new Date(birtdayTime);
//            customer.setBirthday(birthdayDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            customerAggregate.setBirthday(birthdayDate);
        }
        
        if(customerDto.getProfileImageUrl()==null)
        {
            customerAggregate.setProfileImageUrl("");
        }
        
    }
    
    @Override
    public CustomerDTO convertCustomerToDto(CustomerRootEntity customerEntity, long hotelId){
        return convertCustomerToDto(customerEntity, hotelId, false, null);
    }
    
    @Override
    public CustomerDTO convertCustomerToDto(CustomerRootEntity customerEntity, boolean fullSerialization, CustomerHotelCheckin validCheckin){
        return convertCustomerToDto(customerEntity, validCheckin.getHotel().getId(), fullSerialization, validCheckin);
    }
    
    @Override
    public CustomerDTO convertMyCustomerToFullDto(CustomerRootEntity customerEntity){
        return convertCustomerToDto(customerEntity, 0, true, null);
    }
    
    
//    @Override
    
    /**
     * TODO modelMapper + manual sets OR Builder all one time???
     * Jackson sadly can only rename fields, without external logik for setters
     * @param customerEntity
     * @param hotelId
     * @param fullSerialization
     * @param validCheckin
     * @return
     */
    private CustomerDTO convertCustomerToDto(CustomerRootEntity customerEntity, long hotelId, boolean fullSerialization, CustomerHotelCheckin validCheckin)
    {
        CustomerDTO dto = modelMapper.map(Objects.requireNonNull(customerEntity), CustomerDTO.class);
        
        dto = fillDtoProfileInfo(customerEntity, dto, fullSerialization);
	
//      if(hotelId>=0)
        {
            dto = serializeCustomerHotelInfo(dto, hotelId, fullSerialization, validCheckin);
        }

        if(dto.isHotelStaff() && !AppConfigProperties.isEmptyString(dto.getHotelName()))
        {
            dto.setFirstName(dto.getHotelName());
            dto.setLastName("");
        }
        
        
        return dto;
    }

    /**
     * set customer profile data
     * @param customerEntity
     * @param dto
     * @return
     */
    private CustomerDTO fillDtoProfileInfo(CustomerRootEntity customerEntity, CustomerDTO dto, boolean fullSerialization)
    {
        if(customerEntity !=null && customerEntity.getLanguageSet()!=null)
        {
            String[] newLanguages = new String[customerEntity.getLanguageSet().size()];
            
            int counter = 0;

            for (Language nextLanguage : customerEntity.getLanguageSet())
            {
                newLanguages[counter++] = nextLanguage.getDescriptionShort();
            }

            dto.setLanguages(newLanguages);
            
            dto.setAvatarUrl(getCustomerAvatarUrl(customerEntity));
            //            
            // if(customer.getLastSeenOnline()!=null)
            //            {
            //                dto.setLastSeenOnlineString(ControllerUtils.dateTimeFormat.format(customer.getLastSeenOnline()));
            //            }
            
            dto.setOnline(isCustomerOnline(customerEntity));
            
			dto.addSystemMessage("hasPushRegistrationId", String.valueOf(customerEntity.getPushRegistrationId()!=null));
			
            if(fullSerialization) //SHOW PRIVATE INFORMATION
            {
                dto.setCustomerConsistencyId(customerEntity.getConsistencyId());
                
                if(customerEntity.getCustomerDetails().getBirthday()!=null)
                {
                    dto.setBirthdayTime(customerEntity.getCustomerDetails().getBirthday().getTime()+"");
                }
				
            }
            else //HIDE NOT PUBLIC INFORMATION
            {
                dto.setBirthdayTime(null);
                //dto.setBirthday(null);
                dto.setCheckinFrom(null);
                dto.setCheckinTo(null);
                dto.setEmail(null);
                dto.setPassword(null);
            }
            
        }
        
        return dto;
    }
    
    
    
    @Override
    public String getCustomerAvatarUrl(CustomerRootEntity customerEntity)
    {

        String avatar_m = /*isOrange? "angulr/img/build/avatar/incognito-orange-m.png" :*/ "/" + AppConfigProperties.HOST_SUFFIX + "angulr/img/build/avatar/incognito-m.png";
        String avatar_f = /*isOrange? "angulr/img/build/avatar/incognito-orange-f.png" :*/  "/" + AppConfigProperties.HOST_SUFFIX + "angulr/img/build/avatar/incognito-f.png";

        if(customerEntity.isHotelStaff())
		{
			return  "/" + AppConfigProperties.HOST_SUFFIX + "angulr/img/build/avatar/staff.png";
		}

        //		if(!isShowAvatar() && (!userId || userId==$scope.hotelState.profileData.id))
        //		{
        //			if($scope.hotelState.profileData && $scope.hotelState.profileData.avatarUrl && $scope.hotelState.profileData.showAvatar && $scope.hotelState.profileData.avatarUrl.length>0)
        //			{
        //				return $scope.hotelState.profileData.avatarUrl;
        //			}
        //		}
        String currentPictureUrl = AppConfigProperties.isEmptyString(customerEntity.getPictureUrl()) ? customerEntity.getProfileImageUrl() : customerEntity.getPictureUrl();
        //		if(!isShowAvatar() && userId && $rootScope.allCustomersById && $rootScope.allCustomersById[userId] && $rootScope.allCustomersById[userId].avatarUrl.length>0 && $rootScope.allCustomersById[userId].showAvatar)
        if(customerEntity.isShowAvatar() && !AppConfigProperties.isEmptyString(currentPictureUrl))
		{
			//			return $rootScope.allCustomersById[userId].avatarUrl;
			return currentPictureUrl.startsWith("http") || currentPictureUrl.startsWith("/")? currentPictureUrl :  "/" + currentPictureUrl;
		}

        if(customerEntity.getSex().equalsIgnoreCase("w"))
		{
			return avatar_f;
		}

        //		if(userId == undefined)//current user
        //		{
        //			return $scope.hotelState.profileData.sex && $scope.hotelState.profileData.sex=="w" ? avatar_f : avatar_m;
        //		}

        return avatar_m;
    }

    @Override
    @Transactional
    public CustomerDTO serializeCustomerHotelInfo(CustomerDTO requestDTO, long hotelId, boolean fullSerialization, CustomerHotelCheckin validCheckin)
    {
        if(requestDTO==null)
        {
            return requestDTO;
        }

        CustomerDTO customerDto = requestDTO.clone();

        //we trust to Parameter
        customerDto.setHotelId(hotelId);

        if(fullSerialization)
        {        
            return checkinService.updateOwnDtoCheckinInfo(customerDto, validCheckin);
        }

        customerDto.setCheckedIn(false);
        customerDto.setHotelConsistencyId(0l);
        customerDto.setHotelName(null);
        customerDto.setHotelCity(null);
        
        if(hotelId<=0){
            return  customerDto;
        }


        long virtualCodeId = lastMessagesService.getInitHotelId();
        
        if(/*customerDto.getHotelId()!= null &&*/ customerDto.getHotelId()>0 && customerDto.getHotelId() != virtualCodeId)
        {
            HotelRootEntity outHotelRootEntity = validCheckin!=null? validCheckin.getHotel() : hotelRepository.getOne(customerDto.getHotelId());
            if(outHotelRootEntity !=null && !outHotelRootEntity.isVirtual())
            {
                customerDto = fillDtoWithHotelInfo(customerDto, validCheckin);
            }
        }
        
        return customerDto;
    }
    
    /**
     * TODO auto generate immutable final dto from Entity!!!
     * @param dto
     * @param validCheckin
     * @return
     */
	@Override
    public CustomerDTO fillDtoWithHotelInfo(CustomerDTO dto, CustomerHotelCheckin validCheckin)
    {
        if(validCheckin.getHotel() !=null && dto!=null)
        {
            HotelRootEntity outHotelRootEntity = validCheckin.getHotel();
            dto.setHotelId(outHotelRootEntity.getId());
            dto.setHotelDTO(hotelService.convertToDTO(outHotelRootEntity));
            dto.setCheckedIn(true);
            dto.setHotelConsistencyId(outHotelRootEntity.getConsistencyId());
    
            /**
            TypeMap<CustomerRootEntity, CustomerDTO> propertyMapper = this.mapper.createTypeMap(CustomerRootEntity.class, CustomerDTO.class);
            propertyMapper.addMappings(
                    mapper -> mapper.map(src -> src.getName(), CustomerDTO::setCreator)
            );
//            Condition<Long, Long> hasTimestamp = ctx -> ctx. != null && ctx.getSource() > 0;
//            Condition<Long, Long> isStaff = ctx -> ctx. != null && ctx.getSource() > 0;
//            propertyMapper.addMappings(
//                    mapper -> mapper.when(hasTimestamp).map(CustomerRootEntity::getTimestamp, CustomerDTO::setCreationTime)
//                    mapper -> mapper.when(hasTimestamp).map(CustomerRootEntity::getTimestamp, CustomerDTO::setCreationTime)
//            );
            
            // when game has zero timestamp
            CustomerRootEntity game = new CustomerRootEntity(1L, "Game 1");
            game.setTimestamp(0L);
            CustomerDTO gameDTO = this.mapper.map(game, CustomerDTO.class);
            **/
    
            /**
             * TODO STRATEGY prepare all optional fields and execute DTO Builder or constructor???
             */
    
            if(AppConfigProperties.HOTEL_DEMO_CODE.equalsIgnoreCase(outHotelRootEntity.getCurrentHotelAccessCode()))
            {
                dto.setFullCheckin(true);
            }
            else if(validCheckin!=null){
                dto.setFullCheckin(validCheckin.isFullCheckin());
            }

            if(dto.isHotelStaff())
            {
                dto.setFirstName(outHotelRootEntity.getName());
                dto.setLastName("");
            }
        }
        
        return dto;
    }
	


    @Transactional
    @Override
    public void deleteCustomer(CustomerDTO customerDto) {
        customerRepository.deleteById(customerDto.getId());
    }

    @Override
    public String getCustomerAvatarUrl(ICustomerRootEntity customerEntity) {
        return null;
    }


    @Override
    public Optional<CustomerDTO> getByEmail(String email)
    {
        CustomerRootEntity customerEntity = customerRepository.findByEmailAndActive(Objects.requireNonNull(email), true)
                .stream().findFirst().get();

         if(customerEntity != null) {
             
            return Optional.of(convertMyCustomerToFullDto(customerEntity));
         }
//        return updateOwnDtoCheckinInfo(out);
        
        return Optional.empty();
    }

    @Override
    public Set<CustomerDTO> getCustomerCities(long customerId)
    {
        //TODO Eugen: bessere query by active and city
        List<String> allCustomersCities = customerRepository.findNotStaffUniueCities();
        List<String> allCheckinCustomersCities = checkinRepository.findNotStaffCheckinUniueCities(new Date());

        Set<String> citiesList = new HashSet<>();
        citiesList.addAll(allCheckinCustomersCities);
        citiesList.addAll(allCustomersCities);
        
        Set<CustomerDTO> resultCustomerList = new HashSet<>();
        
        for (String nextCity: citiesList)
        {
            CustomerDTO dto = new CustomerDTO(new DateTime().getMillis());
            dto.setCity(nextCity);
            resultCustomerList.add(dto);
        }
        
        return resultCustomerList;
    }
    
    @Override
    public Set<CustomerDTO> getByCity(long customerId, String city)
    {
        //TODO Eugen: bessere query by active and city
        List<CustomerRootEntity> allCustomerEntities = customerRepository.findAll();

        Set<CustomerDTO> resultList = new HashSet<>();
        
        for (CustomerRootEntity nextCustomerRootEntity : allCustomerEntities)
        {
            if(nextCustomerRootEntity.isActive() && (nextCustomerRootEntity.getCity()!=null && nextCustomerRootEntity.getCity().equals(city) || city==null && nextCustomerRootEntity.getCity()==null))
            {
                //TODO Eugen: create global convert method to dto...
                long hotelId = getCustomerHotelId(nextCustomerRootEntity.getId());
                CustomerDTO dto = convertCustomerToDto(nextCustomerRootEntity, hotelId);

//                boolean isPartnerInMyHotelWithFullCheckin = customerHotelId>0 && customerHotelId==dto.getHotelId() && checkinRepository.isFullCheckinForCustomerByHotelId(dto.getId(), dto.getHotelId(), new Date());
//                dto.setInMyHotel(isPartnerInMyHotelWithFullCheckin);

                resultList.add(dto);
            }
        }
        
        return resultList;
    }

    @Override
    @Transactional
    public Set<CustomerDTO> getByHotelId(long guestRequesterId, long hotelId, boolean addStaff)
    {
        long requesterId = guestRequesterId;
        
        CustomerRootEntity requester = customerRepository.getOne(requesterId);
        
//        if(requester==null)
//        {
//            //TODO eugen: check if user is checked in this hotel
//            return new HashSet<>();
//        }
        
        List<CustomerHotelCheckin> checkins = new ArrayList<>();
        
        if(AppConfigProperties.SHOW_ONLY_FULL_CHECKIN_USERS)
        {
            checkins = checkinRepository.getActiveFullCheckinByHotelId(hotelId, new Date());
        }
        else {
            checkins = checkinRepository.getActiveByHotelId(hotelId, new Date());
        }
        
        Set<CustomerDTO> customerDtoSet =  new HashSet<>();

        long requesterHotelId = this.getCustomerHotelId(requesterId);

        for(CustomerHotelCheckin nextCheckin : checkins)
        {
            CustomerRootEntity nextCustomerRootEntity = nextCheckin.getCustomer();

            //eugen: filter guest accounts not active more than 1 day
            if(nextCustomerRootEntity.isGuestAccount() && nextCustomerRootEntity.getLastSeenOnline()!=null && nextCustomerRootEntity.getLastSeenOnline().before(convertToDate(LocalDateTime.now().minusDays(AppConfigProperties.AWAY_GUEST_DAYS_TIMEOUT))))
            {
                if(AppConfigProperties.SET_AWAY_GUEST_INACTIVE)
                {
                    nextCustomerRootEntity.setActive(false);
                }

                if(AppConfigProperties.SET_AWAY_GUEST_CHECKOUT)
                {
                    //remove guest checkin
                    List<CustomerHotelCheckin> guestCheckin = checkinRepository.getActiveByCustomerId(nextCustomerRootEntity.getId(), new Date());
                    for (CustomerHotelCheckin nextGuestCheckin : guestCheckin)
                    {
                        nextGuestCheckin.setActive(false);
                        checkinRepository.saveAndFlush(nextGuestCheckin);
                    }
                }
                customerRepository.save(nextCustomerRootEntity);
                continue;
            }
            
            if(nextCustomerRootEntity.isHotelStaff())
            {
                continue;
            }
            
            CustomerDTO dto = convertCustomerToDto(nextCustomerRootEntity, true, nextCheckin);
            
            boolean isPartnerInMyHotelWithFullCheckin = requesterHotelId>0 && requesterHotelId==dto.getHotelId() && nextCheckin.isFullCheckin();

            dto.setInMyHotel(isPartnerInMyHotelWithFullCheckin);

            dto = setDtoLastMessageWithRequester(requester, dto);
            
            customerDtoSet.add(dto);
        }
        
        if(addStaff && hotelId>0)
        {
            List<CustomerRootEntity> staffs = checkinRepository.getStaffByHotelId(hotelId);
            
            for(CustomerRootEntity nextCustomerRootEntity : staffs)
            {
                CustomerDTO dto = convertCustomerToDto(nextCustomerRootEntity, hotelId);

                boolean isPartnerInMyHotelWithFullCheckin = requesterHotelId>0 && requesterHotelId==dto.getHotelId();// && nextCheckin.isFullCheckin();

                dto.setInMyHotel(isPartnerInMyHotelWithFullCheckin);

                dto = setDtoLastMessageWithRequester(requester, dto);

                customerDtoSet.add(dto);
            }
        }
        
        return customerDtoSet;
    }

    

    @Override
    public CustomerRootEntity addGetAnonymCustomer()
    {
        List<CustomerRootEntity> anonymes = customerRepository.getAnonymeCustomer();
       
        CustomerRootEntity anonym = null;
                
        if(anonymes.isEmpty())
        {
            anonym = new CustomerRootEntity();
            anonym.setFirstName("[anonym]");
            anonym.setLastName("[anonym]");
            anonym.setEmail("[anonym]");
            customerRepository.saveAndFlush(anonym);
        }
        else {
            anonym = anonymes.get(0);
        }
        
        return anonym;
    }

    @Override
    public boolean isStaffOrAdminId(long receiverId)
    {
        return customerRepository.checkStaffOrAdmin(receiverId);
    }

}
