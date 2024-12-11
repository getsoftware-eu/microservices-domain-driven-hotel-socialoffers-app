package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.portServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.SocketNotificationCommand;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.model.CheckinDBEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.repository.CheckinRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.Language;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.repository.CustomerRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.HotelDBEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.InnerHotelEvent;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.repository.DealRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.repository.HotelRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.repository.LanguageRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.viewEntity.model.CustomerDeal;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.CheckinRootDomainEntity;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.out.CheckinPortService;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.port.out.iPortService.CustomerPortService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties.convertToDate;
import static eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.portServiceImpl.OnlineServiceImpl.isCustomerOnline;

@Service
@RequiredArgsConstructor
public class CustomerPortServiceImpl implements CustomerPortService<CustomerDBEntity> 
{
    private final IHotelService hotelService;
    
    private final HotelRepository hotelRepository;     
    
    private final INotificationService notificationService;
    
    private final CustomerRepository customerRepository;
	
	private LastMessagesService lastMessagesService;	
	
    private final LoginHotelicoService loginService;
	
    private final LanguageRepository languageRepository;
    
    private final MailService mailService;     
    
    private final CheckinPortService checkinService; 
        
    private final CheckinRepository checkinRepository; 
    
    private final ModelMapper modelMapper;
    
    private final DealRepository dealRepository;
	
//    private final CustomerPersistGatewayServiceImpl customerPersistGatewayService;
    
    private final IMessagingProducerService<InnerHotelEvent> messagingService;

    public CustomerRootDomainEntity recreateDomainEntityFromDBProjection(CustomerDomainEntityId customerEntityId)
    {
        CustomerDBEntity dbProjection = customerRepository.findByDomainEntityIdAndActive(customerEntityId, true)
                .orElseThrow(() -> new RuntimeException("no hotel"));

        //Eugen: try to use external builder, but have to duplicate only fields, not methods (Lombok @Builder make it possible)
//        CustomerAggregate.CustomerBuilder.CustomerBuilderBuilder domain = CustomerAggregate.builder(/*new CustomerEntityId(dbProjection.getCustomerUUID())*/)
//                .firstName(dbProjection.getFirstName());
//                //.build();
//        
//        return CustomerAggregate.buildDomain(domain);

        return //CustomerAggregate.getEntityBuilder()
                CustomerRootDomainEntity.builder()
                .domainEntityId(dbProjection.getDomainEntityId())
                .firstName(dbProjection.getFirstName()) 
                .build();
    }
    
    @Override
    public List<CustomerDBEntity> getCustomerEntities() {
        return customerRepository.findByActive(true);
    }
    
    public List<CustomerDTO> getCustomerDTOs() {

        List<CustomerDBEntity> entities = getCustomerEntities();

        Type listType = new TypeToken<List<CustomerDTO>>() {}.getType();

        return modelMapper.map(entities, listType);
    }
    
    @Override
    public HotelDomainEntityId getCustomerHotelId(CustomerDomainEntityId customerId){
        return lastMessagesService.getCustomerHotelId(customerId);
    }
    
//    @Override
//    public Optional<CustomerDBEntity> getById(long customerId, long dtoRequesterId) {
//        
//        CustomerDBEntity customerEntity = customerPersistGatewayService.findEntityById(customerId);
//        
//        CustomerDTO out = null;
//       
//        if(customerId == dtoRequesterId){
//            
//            //I want MY Profile!
//            out = convertMyCustomerToFullDto(customerEntity);
//        }
//        else{ //fremd profile
//            
//            long requesterHotelId = getCustomerHotelId(customerId);
//            long hotelId = getCustomerHotelId(customerEntity.getId());
//
//            out = convertCustomerToDto(customerEntity, hotelId);
//            
//            boolean isPartnerInMyHotelWithFullCheckin = requesterHotelId>0 && requesterHotelId==hotelId && checkinRepository.isFullCheckinForCustomerByHotelId(out.getId(), out.getHotelId(), new Date());
//
//            out.setInMyHotel(isPartnerInMyHotelWithFullCheckin);
//
//            if(dtoRequesterId >0)
//            {
//                CustomerDBEntity requester = getEntityById(dtoRequesterId).orElseThrow(()-> new RuntimeException("no dtoRequesterId"));
//                out = setDtoLastMessageWithRequester(requester, out);
//            }
//        }
//        
//        return out;
//    }
    
    @Override
    public Optional<CustomerDBEntity> getEntityById(CustomerDomainEntityId customerId) {
        return customerRepository.findByDomainId(customerId);//.orElseThrow(()->new RuntimeException("not found"));
//        return Optional.of(modelMapper.map(entity, CustomerDTO.class));
    }

    @Transactional
    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDto, String password) {

        if(getByEmail(customerDto.getEmail()).isPresent())
        {
            throw new RuntimeException("Email already registered");
//            return customerDto;
        }

        CustomerDBEntity customerEntity = modelMapper.map(customerDto, CustomerDBEntity.class);

        fillCustomerFromDto(customerDto, customerEntity);

        customerEntity = customerRepository.saveAndFlush(customerEntity);

        HotelDomainEntityId initHotelId = lastMessagesService.getInitHotelId();

        if(customerDto.getHotelId()>0)
        {
            HotelDBEntity hotelRootEntity = hotelRepository.findById(customerDto.getHotelId()).orElseThrow(()-> new RuntimeException("hotel not found"));

            if(hotelRootEntity !=null && customerDto.isHotelStaff())
            {
                customerDto.setCheckinFrom(new Date());
                customerDto.setCheckinTo(convertToDate(LocalDateTime.now().plusYears(10)));
//                checkinService.createCheckin(CheckinRequestDTO(customerDto.getInitId(), customerDto.getHotelId(), ));

                if (customerDto.getEmail() != null)
                {
                    mailService.sendMail(customerDto.getEmail(), "HoteliCo staff registration", "You have now a staff account for '" + hotelRootEntity.getName() + "' hotel in Hotelico. Your password is: '" + customerDto.getPassword() + "'. \nYour HoteliCo team.", null);
                }

                customerEntity.setHotelStaff(true);
                customerEntity = customerRepository.saveAndFlush(customerEntity);

                initHotelId = hotelRootEntity.getDomainEntityId();
            }
        }


        if(customerDto.getPassword()!=null)
        {
            password = customerDto.getPassword();
        }

        long passwordHash = loginService.getCryptoHash(customerEntity, password);

//        CustomerAggregate aggregate = customerEntity.getEntityAggregate();
//
//        aggregate.setPasswordHash(passwordHash);
//        aggregate.setPasswordValue(password);
//        aggregate.setLogged(true);
        //customer.updateLastSeenOnline();

		CustomerDTO dto =  convertMyCustomerToFullDto(customerRepository.saveAndFlush(customerEntity));

        SocketNotificationCommand message = new SocketNotificationCommand(customerDto.getSequenceId(), customerDto.getHotelId(), customerDto.getLastName(), "message");
        messagingService.sendSocketNotificationCommand(message, InnerHotelEvent.EVENT_REGISTER);

        //// NOTIFICATE OTHERS!!!!

        if(!dto.isHotelStaff() && !dto.isAdmin())
        {
            notificationService.notificateAboutEntityEvent(dto, InnerHotelEvent.EVENT_REGISTER, "Now Registered!", dto.getId());
        }

        return dto;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDto, int requesterId) {
        return null;
    }


//    @Override
//    @Transactional
//    public CustomerDTO synchronizeCustomerToDto(CustomerDTO dto)
//    {
//        if(dto.getId()<=0)
//        {
//            return dto;
//        }
//        
//        long dtoConsistencyId = dto.getCustomerConsistencyId();
//        
//        long customerDbConsistencyId = lastMessagesService.getCustomerConsistencyId(dto.getId());
//        
//        //TODO Eugen: ONLY DTO UPDate hier
//        if(customerDbConsistencyId > dtoConsistencyId)
//        {
//            CustomerDBEntity customerEntity = customerRepository.getOne(dto.getId());
//            
//            if(customerEntity ==null)
//            {
//                dto.setId(-1L);
//                return dto;
//            }
//            
////            int hotelId = getCustomerHotelId(customer.getId());
//
//            dto = convertMyCustomerToFullDto(customerEntity);
//        }
//        
//        return dto;
//    }
//    
//    @Override
//    @Transactional
//    public CustomerDTO updateCustomer(CustomerRequestDTO customerDto, int updatorId) {
//        
//        CustomerDBEntity customerEntity = customerRepository.getOne(customerDto.getId());
//    
//        CustomerAggregate customerAggregate = new CustomerAggregate(customerEntity);
//        
//        if(customerDto.getSystemMessages()!=null && !customerDto.getSystemMessages().isEmpty())
//        {
//           if(customerEntity !=null && customerDto.getSystemMessages().containsKey("guestCustomerId"))
//           {
//               Long guestId = Long.parseLong(customerDto.getSystemMessages().get("guestCustomerId"));
//
//               if(guestId!=null && guestId>0)
//               {
//                   this.relocateGuestDealsToLoggedCustomer(customerEntity, guestId);
//               }
//               
//           } 
//           
//           if(customerEntity !=null && customerDto.getSystemMessages().containsKey(AppConfigProperties.PUSH_CHROME_ID))
//           {
//               customerEntity.setPushRegistrationId(customerDto.getSystemMessages().get(AppConfigProperties.PUSH_CHROME_ID));
//           }
//		   
//		   if(customerEntity !=null && customerDto.getSystemMessages().containsKey("latitude") && customerDto.getSystemMessages().containsKey("longitude"))
//           {
//               //eugen2022
//               customerAggregate.setLatitude(Double.parseDouble(customerDto.getSystemMessages().get("latitude")));
//               customerAggregate.setLongitude(Double.parseDouble(customerDto.getSystemMessages().get("longitude")));
//           }
//           
//           if(customerDto.getSystemMessages().containsKey("feedbackMessage"))
//           {
//               String feedbackMessage = customerDto.getSystemMessages().get("feedbackMessage");
//              
//               String fromName = customerDto.getSystemMessages().get("fromName");
//               String fromEmail = customerDto.getSystemMessages().get("fromMail");
//               
//               if(AppConfigProperties.isEmptyString(fromName))
//               {
//                   fromName = customerEntity.getFirstName();
//               }
//               
//               try
//               {
//                  //Eugen: decodes encodeUriComponent!!!
//                  feedbackMessage = URLDecoder.decode(feedbackMessage, "UTF-8");
//               }
//               catch (UnsupportedEncodingException e)
//               {
//                  e.printStackTrace();
//               }
//               
//               mailService.sendMail("info@hotelico.de", "HoteliCo feedback from " + fromName + " " + (customerEntity.getLastName() != null ? customerEntity.getLastName() : "") + "(id=" + customerEntity.getId() + ")", feedbackMessage + (fromEmail != null ? " [from " + fromEmail + "]" : ""), null);
//           }
//			
//			if(customerDto.getSystemMessages().containsKey("mailList"))
//           {
//			   notificationService.sendMailList(customerEntity, customerDto.getSystemMessages());
//           }  
//           
//           if(customerDto.getSystemMessages().containsKey("hotelFeedMessage"))
//           {
//			   notificationService.sendFeedMessage(customerEntity, customerDto.getSystemMessages());
//            }          
//           
//           customerDto.setSystemMessages(new HashMap<String, String>());
//        }
//       
//        if(customerEntity !=null)
//        {
//            fillCustomerFromDto(customerDto, customerEntity);
//            customerAggregate.setCity(customerDto.getCity());
//            customerAggregate.setOriginalCity(customerDto.getOriginalCity());
//            customerAggregate.setJobTitle(customerDto.getJobTitle());
//            customerAggregate.setJobDescriptor(customerDto.getJobDescriptor());
//            customerAggregate.setFirstName(customerDto.getFirstName());
//            customerAggregate.setLastName(customerDto.getLastName());
//            customerAggregate.setStatus(customerDto.getStatus());
//            customerAggregate.setSex(customerDto.getSex());
//            customerAggregate.setShowAvatar(customerDto.getShowAvatar());
//            customerAggregate.setAllowHotelNotification(customerDto.getAllowHotelNotification());
//            customerAggregate.setShowInGuestList(customerDto.getShowInGuestList());
//            
//            if(customerDto.getEmail()!=null)
//            {
//                customerAggregate.setEmail(customerDto.getEmail());
//            }
//    
//            customerAggregate.setCompany(customerDto.getCompany());
//            customerAggregate.setPrefferedLanguage(customerDto.getPrefferedLanguage());
//            customerAggregate.setProfileImageUrl(customerDto.getProfileImageUrl());
//            
//            //EUGEN: only disable(true)!!! enable is not allowed!
//			if(customerDto.getHideCheckinPopup())
//			{
//                customerAggregate.setHideCheckinPopup(customerDto.getHideCheckinPopup());
//			}
//			
//			if(customerDto.getHideChromePushPopup())
//			{
//                customerAggregate.setHideChromePushPopup(customerDto.getHideChromePushPopup());
//			}
//            
//            //EUGEN: only disable(true)!!! enable is not allowed!
//            if(customerDto.getHideHotelListPopup())
//			{
//                customerAggregate.setHideHotelListPopup(customerDto.getHideHotelListPopup());
//			}
//            
//            //EUGEN: only disable(true)!!! enable is not allowed!
//            if(customerDto.getHideWallPopup())
//			{
//                customerAggregate.setHideWallPopup(customerDto.getHideWallPopup());
//			}
//			
//            if(updatorId == customerEntity.getId())
//            {
//                customerAggregate.setLogged(true);
//            }
//            
//            if(customerDto.getPassword()!=null && !customerDto.getPassword().isEmpty() && customerDto.getPassword().length()>5)
//            {
//                long passwordHash = loginService.getCryptoHash(customerEntity, customerDto.getPassword());
//                customerAggregate.setPasswordHash(passwordHash);
//                customerAggregate.setPasswordValue(customerDto.getPassword());
//            }
//
//            if(customerEntity.isGuestAccount() && customerEntity.getEmail()!=null && customerEntity.getLastName()!=null && customerEntity.getPasswordHash()!=null)
//            {
//                customerAggregate.setGuestAccount(false);
//            }
//            
//            long consistencyId = System.currentTimeMillis();
//            customerAggregate.setConsistencyId(consistencyId);
//
//            lastMessagesService.updateCustomerConsistencyId(customerEntity.getId(), consistencyId);
//            
//            customerRepository.saveAndFlush(customerEntity);
//        }
//        
////        int hotelId = getCustomerHotelId(customerDto.getId());
//
//        CustomerDTO dto = convertMyCustomerToFullDto(customerEntity);
//
//        return dto;
//    }

    @Override
    public CustomerDTO synchronizeCustomerToDto(CustomerDTO customerDto) {
        return null;
    }

    //    @Override
    public boolean relocateGuestDealsToLoggedCustomer(CustomerDBEntity customerEntity, CustomerDomainEntityId guestCustomerId)
    {
        if(customerEntity ==null || guestCustomerId==null)
        {
            return false;
        }
        
        List<CustomerDeal> anonymGuestDeals = dealRepository.getAnonymDealsByGuestId(guestCustomerId);
        
        for (CustomerDeal next: anonymGuestDeals)
        {
            next.setCustomerId(customerEntity.getDomainEntityId());
//            next.setGuestCustomerId(0);
            dealRepository.saveAndFlush(next);
        }
        
        return !(anonymGuestDeals.isEmpty());
    }

    @Override
    public CustomerDTO convertCustomerWithHotelToDto(CustomerDBEntity customerEntity, HotelDomainEntityId hotelId) {
        return null;
    }

    @Override
    public CustomerDTO convertCustomerWithHotelToDto(CustomerDBEntity customerEntity, boolean fullSerialization, CheckinRootDomainEntity validCheckin) {
        return null;
    }

    private void fillCustomerFromDto(CustomerDTO customerDto, CustomerDBEntity customerEntity)
    {
        //NOTE eugen: hier set only different [Entity-Dto] fields
        List<String> languageDescriptions = customerDto.getLanguages();
        customerEntity.getLanguageSet().clear();
        
        if(languageDescriptions!=null)
        {
            for (int i = 0; i < languageDescriptions.size(); i++)
            {
                String shortDescription = languageDescriptions.get(i);
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
            customerEntity.getCustomerDetails().setBirthday(birthdayDate);
        }
        
    }
    
    public CustomerDTO convertCustomerToDto(CustomerDBEntity customerEntity, HotelDomainEntityId hotelId){
        return convertCustomerToDto(customerEntity, hotelId, false, null);
    }


    public CustomerDTO convertCustomerToDto(CustomerDBEntity customerEntity, boolean fullSerialization, CheckinDBEntity validCheckin){
        return convertCustomerToDto(customerEntity, validCheckin.getHotelDomainEntityId(), fullSerialization, validCheckin);
    }
    
    public CustomerDTO convertMyCustomerToFullDto(CustomerDBEntity customerEntity){
        return convertCustomerToDto(customerEntity, new HotelDomainEntityId("-"), true, null);
    }

    @Override
    public CustomerDTO serializeCustomerHotelInfo(CustomerDTO dto, HotelDomainEntityId hotelId, boolean fullSerialization, CheckinRootDomainEntity validCheckin) {
        return null;
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
    private CustomerDTO convertCustomerToDto(CustomerDBEntity customerEntity, HotelDomainEntityId hotelId, boolean fullSerialization, CheckinDBEntity validCheckin)
    {
        CustomerDTO dto = modelMapper.map(Objects.requireNonNull(customerEntity), CustomerDTO.class);
        
        dto = fillDtoProfileInfo(customerEntity, dto, fullSerialization);
	
//      if(hotelId>=0)
        {
            dto = serializeCustomerHotelInfo(dto, hotelId, fullSerialization, validCheckin);
        }

//        if(dto.isHotelStaff() && !AppConfigProperties.isEmptyString(dto.getHotelDTO().getName()))
//        {
//            dto.setFirstName(dto.getHotelName());
//            dto.setLastName("");
//        }
        
        
        return dto;
    }

    /**
     * set customer profile data
     * @param customerEntity
     * @param dto
     * @return
     */
    private CustomerDTO fillDtoProfileInfo(CustomerDBEntity customerEntity, CustomerDTO dto, boolean fullSerialization)
    {
        if(customerEntity !=null && customerEntity.getLanguageSet()!=null)
        {
            List<String> newLanguages = new ArrayList<>();
            
            int counter = 0;

            for (Language nextLanguage : customerEntity.getLanguageSet())
            {
                newLanguages.add(nextLanguage.getDescriptionShort());
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
//                dto.setEmail(null);
//                dto.setPassword(null);
            }
            
        }
        
        return dto;
    }
    
    
    
    @Override
    public String getCustomerAvatarUrl(CustomerDBEntity customerEntity)
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

//    @Override
//    @Transactional
//    public CustomerDTO serializeCustomerHotelInfo(CustomerDTO requestDTO, long hotelId, boolean fullSerialization, CheckinRootDomainEntity validCheckin)
//    {
//        if(requestDTO==null)
//        {
//            return requestDTO;
//        }
//
//        CustomerDTO customerDto = requestDTO.clone();
//
//        //we trust to Parameter
//        customerDto.setHotelId(hotelId);
//
//        if(fullSerialization)
//        {        
//            return checkinService.updateOwnDtoCheckinInfo(customerDto, validCheckin);
//        }
//
////        customerDto.setCheckedIn(false);
////        customerDto.setHotelConsistencyId(0l);
////        customerDto.setHotelName(null);
////        customerDto.setHotelCity(null);
//        
//        if(hotelId<=0){
//            return  customerDto;
//        }
//
//
//        long virtualCodeId = lastMessagesService.getInitHotelId();
//        
//        if(/*customerDto.getHotelId()!= null &&*/ customerDto.getHotelId()>0 && customerDto.getHotelId() != virtualCodeId)
//        {
//            HotelRootEntity outHotelRootEntity = validCheckin!=null? validCheckin.getHotel() : hotelRepository.findById(customerDto.getHotelId()).orElseThrow(()-> new RuntimeException("no customer"));
//            if(outHotelRootEntity !=null && !outHotelRootEntity.isVirtual())
//            {
//                customerDto = fillDtoWithHotelInfo(customerDto, validCheckin);
//            }
//        }
//        
//        return customerDto;
//    }
    
//    /**
//     * TODO auto generate immutable final dto from Entity!!!
//     * @param dto
//     * @param validCheckin
//     * @return
//     */
//	@Override
//    public CustomerDTO fillDtoWithHotelInfo(CustomerDTO dto, CheckinRootDomainEntity validCheckin)
//    {
//        if(validCheckin.getHotel() !=null && dto!=null)
//        {
//            HotelRootEntity outHotelRootEntity = validCheckin.getHotel();
//            dto.setHotelId(outHotelRootEntity.getId());
//            dto.setHotelDTO(hotelService.convertToDTO(outHotelRootEntity));
//            dto.setCheckedIn(true);
//            dto.setHotelConsistencyId(outHotelRootEntity.getConsistencyId());
//    
//            /**
//            TypeMap<CustomerRootEntity, CustomerDTO> propertyMapper = this.mapper.createTypeMap(CustomerRootEntity.class, CustomerDTO.class);
//            propertyMapper.addMappings(
//                    mapper -> mapper.map(src -> src.getName(), CustomerDTO::setCreator)
//            );
////            Condition<Long, Long> hasTimestamp = ctx -> ctx. != null && ctx.getSource() > 0;
////            Condition<Long, Long> isStaff = ctx -> ctx. != null && ctx.getSource() > 0;
////            propertyMapper.addMappings(
////                    mapper -> mapper.when(hasTimestamp).map(CustomerRootEntity::getTimestamp, CustomerDTO::setCreationTime)
////                    mapper -> mapper.when(hasTimestamp).map(CustomerRootEntity::getTimestamp, CustomerDTO::setCreationTime)
////            );
//            
//            // when game has zero timestamp
//            CustomerRootEntity game = new CustomerRootEntity(1L, "Game 1");
//            game.setTimestamp(0L);
//            CustomerDTO gameDTO = this.mapper.map(game, CustomerDTO.class);
//            **/
//    
//            /**
//             * TODO STRATEGY prepare all optional fields and execute DTO Builder or constructor???
//             */
//    
//            if(AppConfigProperties.HOTEL_DEMO_CODE.equalsIgnoreCase(outHotelRootEntity.getCurrentHotelAccessCode()))
//            {
//                dto.setFullCheckin(true);
//            }
//            else if(validCheckin!=null){
//                dto.setFullCheckin(validCheckin.isFullCheckin());
//            }
//
//            if(dto.isHotelStaff())
//            {
//                dto.setFirstName(outHotelRootEntity.getName());
//                dto.setLastName("");
//            }
//        }
//        
//        return dto;
//    }
	


    @Transactional
//    @Override
    public void deleteCustomer(CustomerDTO customerDto) {
        customerRepository.deleteById(customerDto.getId());
    }

  


    @Override
    public Optional<CustomerDTO> getByEmail(String email)
    {
        CustomerDBEntity customerEntity = customerRepository.findByEmailAndActive(Objects.requireNonNull(email), true)
                .stream().findFirst().get();

         if(customerEntity != null) {
             
            return Optional.of(convertMyCustomerToFullDto(customerEntity));
         }
//        return updateOwnDtoCheckinInfo(out);
        
        return Optional.empty();
    }

    @Override
    public Set<CustomerDTO> getCustomerCities(CustomerDomainEntityId customerId)
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
            CustomerDTO dto = CustomerDTO.builder()
                    .domainEntityId(customerId)
                    .build();
            
            resultCustomerList.add(dto);
        }
        
        return resultCustomerList;
    }

    @Override
    public Optional<CustomerDTO> getById(CustomerDomainEntityId customerId, long requesterCustomerId) {
        return Optional.empty();
    }

    @Override
    public Set<CustomerDTO> getByCity(CustomerDomainEntityId customerId, String city)
    {
        //TODO Eugen: bessere query by active and city
        List<CustomerDBEntity> allCustomerEntities = customerRepository.findAll();

        Set<CustomerDTO> resultList = new HashSet<>();
        
        for (CustomerDBEntity nextCustomerRootEntity : allCustomerEntities)
        {
            if(nextCustomerRootEntity.isActive() && (nextCustomerRootEntity.getCustomerDetails().getCity()!=null && nextCustomerRootEntity.getCustomerDetails().getCity().equals(city) || city==null && nextCustomerRootEntity.getCustomerDetails().getCity()==null))
            {
                //TODO Eugen: create global convert method to dto...
                HotelDomainEntityId hotelId = getCustomerHotelId(nextCustomerRootEntity.getDomainEntityId());
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
    public Set<CustomerDTO> getByHotelId(CustomerDomainEntityId guestRequesterId, HotelDomainEntityId hotelId, boolean addStaff)
    {
        Set<CustomerDTO> customerDtoSet =  new HashSet<>();

//        long requesterId = guestRequesterId;
//        
//        CustomerDBEntity requester = customerRepository.getOne(requesterId);
//        
////        if(requester==null)
////        {
////            //TODO eugen: check if user is checked in this hotel
////            return new HashSet<>();
////        }
//        
//        List<CheckinRootDomainEntity> checkins = new ArrayList<>();
//        
//        if(AppConfigProperties.SHOW_ONLY_FULL_CHECKIN_USERS)
//        {
//            checkins = checkinRepository.getActiveFullCheckinByHotelId(hotelId, new Date());
//        }
//        else {
//            checkins = checkinRepository.getActiveByHotelId(hotelId, new Date());
//        }
//        
//
//        long requesterHotelId = this.getCustomerHotelId(requesterId);
//
//        for(CheckinRootDomainEntity nextCheckin : checkins)
//        {
//            CustomerDBEntity nextCustomerRootEntity = nextCheckin.getCustomer();
//
//            //eugen: filter guest accounts not active more than 1 day
//            if(nextCustomerRootEntity.isGuestAccount() && nextCustomerRootEntity.getLastSeenOnline()!=null && nextCustomerRootEntity.getLastSeenOnline().before(convertToDate(LocalDateTime.now().minusDays(AppConfigProperties.AWAY_GUEST_DAYS_TIMEOUT))))
//            {
//                if(AppConfigProperties.SET_AWAY_GUEST_INACTIVE)
//                {
//                    nextCustomerRootEntity.setActive(false);
//                }
//
//                if(AppConfigProperties.SET_AWAY_GUEST_CHECKOUT)
//                {
//                    //remove guest checkin
//                    List<CheckinRootDomainEntity> guestCheckin = checkinRepository.getActiveByCustomerId(nextCustomerRootEntity.getId(), new Date());
//                    for (CheckinRootDomainEntity nextGuestCheckin : guestCheckin)
//                    {
//                        nextGuestCheckin.setActive(false);
//                        checkinRepository.saveAndFlush(nextGuestCheckin);
//                    }
//                }
//                customerRepository.save(nextCustomerRootEntity);
//                continue;
//            }
//            
//            if(nextCustomerRootEntity.isHotelStaff())
//            {
//                continue;
//            }
//            
//            CustomerDTO dto = convertCustomerToDto(nextCustomerRootEntity, true, nextCheckin);
//            
//            boolean isPartnerInMyHotelWithFullCheckin = requesterHotelId>0 && requesterHotelId==dto.getHotelId() && nextCheckin.isFullCheckin();
//
//            dto.setInMyHotel(isPartnerInMyHotelWithFullCheckin);
//
//            dto = setDtoLastMessageWithRequester(requester, dto);
//            
//            customerDtoSet.add(dto);
//        }
//        
//        if(addStaff && hotelId>0)
//        {
//            List<CustomerDBEntity> staffs = checkinRepository.getStaffByHotelId(hotelId);
//            
//            for(CustomerDBEntity nextCustomerRootEntity : staffs)
//            {
//                CustomerDTO dto = convertCustomerToDto(nextCustomerRootEntity, hotelId);
//
//                boolean isPartnerInMyHotelWithFullCheckin = requesterHotelId>0 && requesterHotelId==dto.getHotelId();// && nextCheckin.isFullCheckin();
//
//                dto.setInMyHotel(isPartnerInMyHotelWithFullCheckin);
//
//                dto = setDtoLastMessageWithRequester(requester, dto);
//
//                customerDtoSet.add(dto);
//            }
//        }
        
        return customerDtoSet;
    }

    @Override
    public CustomerDTO fillDtoWithHotelInfo(CustomerDTO dto, CheckinRootDomainEntity validCheckin) {
        return null;
    }


    @Override
    public CustomerDBEntity addGetAnonymCustomer()
    {
        List<CustomerDBEntity> anonymes = customerRepository.getAnonymeCustomer();

        CustomerDBEntity anonym = null;
                
        if(anonymes.isEmpty())
        {
            anonym = (CustomerDBEntity) CustomerDBEntity.builder()
                    .firstName("[anonym]")
                    .lastName("[anonym]")
                    .email("[anonym]")
                    .build();
            
            customerRepository.saveAndFlush(anonym);
        }
        else {
            anonym = anonymes.get(0);
        }
        
        return anonym;
    }

    @Override
    public boolean isStaffOrAdminId(CustomerDomainEntityId receiverId)
    {
        return customerRepository.checkStaffOrAdmin(receiverId);
    }

    @Override
    public void save(CustomerDBEntity customerEntity) {

    }

    @Override
    public void setCustomerPing(CustomerDomainEntityId customerId) {

    }

    @Override
    public List<CustomerDTO> findAllOnline(Timestamp timestamp) {
        return List.of();
    }

    @Override
    public List<CustomerDBEntity> getAllIn24hOnline() {
        return List.of();
    }

    @Override
    public Optional<CustomerDBEntity> getByDomainId(CustomerDomainEntityId customerId) {
        return Optional.empty();
    }

}
