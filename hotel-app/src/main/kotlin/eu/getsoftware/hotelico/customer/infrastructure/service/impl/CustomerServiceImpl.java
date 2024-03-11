package eu.getsoftware.hotelico.customer.infrastructure.service.impl;

import eu.getsoftware.hotelico.checkin.domain.CustomerHotelCheckin;
import eu.getsoftware.hotelico.clients.infrastructure.hotel.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.infrastructure.utils.ControllerUtils;
import eu.getsoftware.hotelico.customer.domain.CustomerAggregate;
import eu.getsoftware.hotelico.customer.domain.CustomerRootEntity;
import eu.getsoftware.hotelico.customer.domain.Language;
import eu.getsoftware.hotelico.customer.domain.iService.CustomerService;
import eu.getsoftware.hotelico.customer.infrastructure.repository.CustomerRepository;
import eu.getsoftware.hotelico.deal.domain.CustomerDeal;
import eu.getsoftware.hotelico.hotel.application.iService.*;
import eu.getsoftware.hotelico.hotel.infrastructure.entities.HotelRootEntity;
import eu.getsoftware.hotelico.hotel.infrastructure.repository.CheckinRepository;
import eu.getsoftware.hotelico.hotel.infrastructure.repository.DealRepository;
import eu.getsoftware.hotelico.hotel.infrastructure.repository.HotelRepository;
import eu.getsoftware.hotelico.hotel.infrastructure.repository.LanguageRepository;
import eu.getsoftware.hotelico.hotel.infrastructure.utils.HotelEvent;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import static eu.getsoftware.hotelico.clients.infrastructure.utils.ControllerUtils.convertToDate;

@Service
public class CustomerServiceImpl implements CustomerService
{
    @Autowired
    private CheckinRepository checkinRepository;      
    
    @Autowired
    private HotelRepository hotelRepository;     
    
    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private CustomerRepository customerRepository;
	
	@Autowired
	private LastMessagesService lastMessagesService;	
	
    @Autowired
	private HotelRabbitMQProducer hotelRabbitmqProducer;
	
	@Autowired
    private LoginHotelicoService loginService;
	
	@Autowired
    private LanguageRepository languageRepository;
    
    @Autowired
    private MailService mailService;     
    
	@Autowired
    private CheckinService checkinService; 
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private DealRepository dealRepository;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    public List<CustomerDTO> getCustomers() {
        List<CustomerRootEntity> list = customerRepository.findByActive(true);
        List<CustomerDTO> outList = new ArrayList<CustomerDTO>();
        for (CustomerRootEntity nextCustomerRootEntity : list) {
            
            //TODO Eugen: get all customer hotelId with one query!
            long hotelId = getCustomerHotelId(nextCustomerRootEntity.getId());
            
            CustomerDTO nextDto = convertCustomerToDto(nextCustomerRootEntity, hotelId);

//            outList.add(updateOwnDtoCheckinInfo(nextDto));
            outList.add(nextDto);
        }
        return outList;
    }

    @Override
    public long getCustomerHotelId(long customerId){
        return lastMessagesService.getCustomerHotelId(customerId);
    }
    
    @Override
    public CustomerDTO getById(long requesterId, long dtoRequesterId) {
        
        CustomerRootEntity customerEntity = customerRepository.getOne(requesterId);
        
        CustomerDTO out = null;
       
        if(requesterId == dtoRequesterId){
            
            //I want MY Profile!
            out = convertMyCustomerToFullDto(customerEntity);
        }
        else{ //fremd profile
            
            long requesterHotelId = getCustomerHotelId(requesterId);
            long hotelId = getCustomerHotelId(customerEntity.getId());

            out = convertCustomerToDto(customerEntity, hotelId);
            
            boolean isPartnerInMyHotelWithFullCheckin = requesterHotelId>0 && requesterHotelId==hotelId && checkinRepository.isFullCheckinForCustomerByHotelId(out.getId(), out.getHotelId(), new Date());

            out.setInMyHotel(isPartnerInMyHotelWithFullCheckin);

            if(dtoRequesterId >0)
            {
                CustomerRootEntity requester = getEntityById(dtoRequesterId);
                out = setDtoLastMessageWithRequester(requester, out);
            }
        }
        
        return out;
    }
    
    @Override
    public CustomerRootEntity getEntityById(long customerId) {
        CustomerRootEntity customerEntity = customerRepository.getOne(customerId);
        return customerEntity;
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
            HotelRootEntity hotelRootEntity =  hotelRepository.getOne(customerDto.getHotelId());
          
            if(hotelRootEntity !=null && customerDto.isHotelStaff())
            {
                customerDto.setCheckinFrom(new Date());
                customerDto.setCheckinTo(convertToDate(LocalDateTime.now().plusYears(10)));
                checkinService.setCustomerCheckin(customerDto, customerEntity);

                if (customerDto.getEmail() != null)
                {
                    mailService.sendMail(customerDto.getEmail(), "HoteliCo staff registration", "You have now a staff account for '" + hotelRootEntity.getName() + "' hotel in Hotelico. Your password is: '" + customerDto.getPassword() + "'. \nYour HoteliCo team.", null);
                }
                
                customerEntity.getEntityAggregate().setHotelStaff(true);
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
    
    
        hotelRabbitmqProducer.registerPush(dto);
        
        
        //// NOTIFICATE OTHERS!!!!

        if(!dto.isHotelStaff() && !dto.isAdmin())
        {
            notificationService.notificateAboutEntityEvent(dto, HotelEvent.EVENT_REGISTER, "Now Registered!", dto.getId());
        }

        return dto;
    }
    
    @Transactional
    @Override
    public CustomerDTO addLinkedInCustomer(CustomerDTO customerDto, String linkedInId){
        
        CustomerRootEntity customerEntity = modelMapper.map(customerDto, CustomerRootEntity.class);
    
        CustomerAggregate aggregate = customerEntity.getEntityAggregate();
    
        aggregate.setLinkedInId(linkedInId);
        aggregate.setLogged(true);
        aggregate.setProfileImageUrl(customerDto.getProfileImageUrl());

        //TODO eugen: get Languages from linkedIn
        
        long virtualHotelId = lastMessagesService.getInitHotelId();
        
        CustomerDTO dto = convertCustomerToDto(customerRepository.saveAndFlush(customerEntity), virtualHotelId);

        dto = loginService.checkBeforeLoginProperties(customerDto, dto);

        if(!dto.isHotelStaff() && !dto.isAdmin())
        {
            notificationService.notificateAboutEntityEvent(dto, HotelEvent.EVENT_REGISTER, "Now Registered!", dto.getId());
        }
        
        return dto;
//        return updateOwnDtoCheckinInfo(dto);
    }
    
    @Transactional
    @Override
    public CustomerDTO addFacebookCustomer(CustomerDTO customerDto, String facebookId){
        CustomerRootEntity customerEntity = modelMapper.map(customerDto, CustomerRootEntity.class);
        customerEntity.getEntityAggregate().setFacebookId(facebookId);
        customerEntity.getEntityAggregate().setProfileImageUrl(customerDto.getProfileImageUrl());
        
        //TODO eugen: get Languages from linkedIn
        
//        CustomerDto dto = modelMapper.map(customerRepository.saveAndFlush(customer), CustomerDto.class);
//        dto = fillDtoFromCustomer(customer, dto);
        
        long virtualHotelId = lastMessagesService.getInitHotelId();
        
        CustomerDTO dto = convertCustomerToDto(customerRepository.saveAndFlush(customerEntity), virtualHotelId);

        dto = loginService.checkBeforeLoginProperties(customerDto, dto);
        
        if(!dto.isHotelStaff() && !dto.isAdmin())
        {
            notificationService.notificateAboutEntityEvent(dto, HotelEvent.EVENT_REGISTER, "Now Registered!", dto.getId());
        }
        
//        return updateOwnDtoCheckinInfo(dto);
        return dto;
    }
    
    

    @Override
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
    public List<CustomerRootEntity> getAllOnline()
    {
        LocalDateTime ldt = LocalDateTime.now().minusMinutes(25);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("America/Los_Angeles"));
        Long milis  = zdt.toInstant().toEpochMilli();
        return customerRepository.findAllOnline(new Timestamp(milis));
    }  
	
    @Override
    public List<CustomerRootEntity> getAllIn24hOnline()
    {
        LocalDateTime ldt = LocalDateTime.now().minusDays(1);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("America/Los_Angeles"));
        Long milis  = zdt.toInstant().toEpochMilli();
        return customerRepository.findAllOnline(new Timestamp(milis));
    }
    
    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDto, int updatorId) {
        
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
           
           if(customerEntity !=null && customerDto.getSystemMessages().containsKey(ControllerUtils.PUSH_CHROME_ID))
           {
               customerEntity.getEntityAggregate().setPushRegistrationId(customerDto.getSystemMessages().get(ControllerUtils.PUSH_CHROME_ID));
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
               
               if(ControllerUtils.isEmptyString(fromName))
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

        if(dto.isHotelStaff() && !ControllerUtils.isEmptyString(dto.getHotelName()))
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
    
    public static boolean isCustomerOnline(CustomerRootEntity customerEntity)
    {
        return customerEntity !=null && (customerEntity.isHotelStaff() || customerEntity.getLastSeenOnline()!=null && customerEntity.getLastSeenOnline().after(convertToDate(LocalDateTime.now().minusMinutes(25))));
    }
    
    @Override
    public String getCustomerAvatarUrl(CustomerRootEntity customerEntity)
    {

        String avatar_m = /*isOrange? "angulr/img/build/avatar/incognito-orange-m.png" :*/ "/" + ControllerUtils.HOST_SUFFIX + "angulr/img/build/avatar/incognito-m.png";
        String avatar_f = /*isOrange? "angulr/img/build/avatar/incognito-orange-f.png" :*/  "/" + ControllerUtils.HOST_SUFFIX + "angulr/img/build/avatar/incognito-f.png";

        if(customerEntity.isHotelStaff())
		{
			return  "/" + ControllerUtils.HOST_SUFFIX + "angulr/img/build/avatar/staff.png";
		}

        //		if(!isShowAvatar() && (!userId || userId==$scope.hotelState.profileData.id))
        //		{
        //			if($scope.hotelState.profileData && $scope.hotelState.profileData.avatarUrl && $scope.hotelState.profileData.showAvatar && $scope.hotelState.profileData.avatarUrl.length>0)
        //			{
        //				return $scope.hotelState.profileData.avatarUrl;
        //			}
        //		}
        String currentPictureUrl = ControllerUtils.isEmptyString(customerEntity.getPictureUrl()) ? customerEntity.getEntityAggregate().getProfileImageUrl() : customerEntity.getPictureUrl();
        //		if(!isShowAvatar() && userId && $rootScope.allCustomersById && $rootScope.allCustomersById[userId] && $rootScope.allCustomersById[userId].avatarUrl.length>0 && $rootScope.allCustomersById[userId].showAvatar)
        if(customerEntity.isShowAvatar() && !ControllerUtils.isEmptyString(currentPictureUrl))
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
    public CustomerDTO serializeCustomerHotelInfo(CustomerDTO dto, long hotelId, boolean fullSerialization, CustomerHotelCheckin validCheckin)
    {
        if(dto==null)
        {
            return dto;
        }

        //we trust to Parameter
        dto.setHotelId(hotelId);

        if(fullSerialization)
        {        
            return checkinService.updateOwnDtoCheckinInfo(dto, validCheckin);
        }

        dto.setCheckedIn(false);
        dto.setHotelConsistencyId(0l);
        dto.setHotelName(null);
        dto.setHotelCity(null);
        
        if(hotelId<=0){
            return  dto;
        }


        long virtualCodeId = lastMessagesService.getInitHotelId();
        
        if(/*dto.getHotelId()!= null &&*/ dto.getHotelId()>0 && dto.getHotelId() != virtualCodeId)
        {
            HotelRootEntity outHotelRootEntity = validCheckin!=null? validCheckin.getHotel() : hotelRepository.getOne(dto.getHotelId());
            if(outHotelRootEntity !=null && !outHotelRootEntity.isVirtual())
            {
                dto = fillDtoWithHotelInfo(dto, validCheckin);
            }
        }
        
        return dto;
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
            dto.setHotelName(outHotelRootEntity.getName());
            dto.setHotelCity(outHotelRootEntity.getCity());
            dto.setCheckedIn(true);
            dto.setHotelConsistencyId(outHotelRootEntity.getConsistencyId());
    
            /**
            TypeMap<CustomerRootEntity, CustomerDTO> propertyMapper = this.mapper.createTypeMap(CustomerRootEntity.class, CustomerDTO.class);
            propertyMapper.addMappings(
                    mapper -> mapper.map(src -> src.getEntityAggregate().getName(), CustomerDTO::setCreator)
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
    
            if(ControllerUtils.HOTEL_DEMO_CODE.equalsIgnoreCase(outHotelRootEntity.getCurrentHotelAccessCode()))
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
    public void setCustomerPing(long sessionCustomerId)
    {
//        Customer onlineCustomer = customerRepository.getOne(sessionCustomerId);
        
        //TODO EUGEN: java memory o
        if(sessionCustomerId>0)
		{
			boolean isReadyForNextNotification = lastMessagesService.isNotificationDelayReady(sessionCustomerId);
			
			lastMessagesService.checkCustomerOnline(sessionCustomerId);
			
			//TODO EUGEN: always response on ping? or only if something changes?
			if(isReadyForNextNotification)
			{
				notificationService.createAndSendNotification(sessionCustomerId, HotelEvent.EVENT_PING);
			}
			else{
				notificationService.createAndSendNotification(sessionCustomerId, HotelEvent.EVENT_ONLINE_CUSTOMERS);
			}
		}

    }


    @Override
    public CustomerDTO getByLinkedInId(String linkedInId){
        List<CustomerRootEntity> customerEntities = customerRepository.findByLinkedInIdAndActive(linkedInId, true);
        
        if(customerEntities.isEmpty())
        {
            return null;
        }
        
        //TODO eugen: get Langguages from LinkedIn
//        int hotelId = getCustomerHotelId(customer.getId());
        CustomerDTO dto = convertMyCustomerToFullDto(customerEntities.get(0));

        return dto;
    }
    
    @Override
    public Optional<CustomerDTO> getByFacebookId(String facebookId){
    
        //TODO eugen: get Langguages from LinkedIn
    
        return customerRepository.findByFacebookIdAndActive(Objects.requireNonNull(facebookId), true)
                .stream()
                .findFirst().map(c -> Optional.of(convertMyCustomerToFullDto(c)))
                .orElse(Optional.empty());
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
            if(nextCustomerRootEntity.isActive() && (nextCustomerRootEntity.getEntityAggregate().getCity()!=null && nextCustomerRootEntity.getEntityAggregate().getCity().equals(city) || city==null && nextCustomerRootEntity.getEntityAggregate().getCity()==null))
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
        
        if(ControllerUtils.SHOW_ONLY_FULL_CHECKIN_USERS)
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
            if(nextCustomerRootEntity.isGuestAccount() && nextCustomerRootEntity.getLastSeenOnline()!=null && nextCustomerRootEntity.getLastSeenOnline().before(convertToDate(LocalDateTime.now().minusDays(ControllerUtils.AWAY_GUEST_DAYS_TIMEOUT))))
            {
                if(ControllerUtils.SET_AWAY_GUEST_INACTIVE)
                {
                    nextCustomerRootEntity.getEntityAggregate().setActive(false);
                }

                if(ControllerUtils.SET_AWAY_GUEST_CHECKOUT)
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
            anonym.getEntityAggregate().setFirstName("[anonym]");
            anonym.getEntityAggregate().setLastName("[anonym]");
            anonym.getEntityAggregate().setEmail("[anonym]");
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
