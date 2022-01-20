package de.hotelico.service.impl;

import de.hotelico.model.ChatMessage;
import de.hotelico.model.Customer;
import de.hotelico.dto.CustomerDTO;
import de.hotelico.model.CustomerDeal;
import de.hotelico.model.CustomerHotelCheckin;
import de.hotelico.model.Hotel;
import de.hotelico.model.Language;
import de.hotelico.repository.ChatRepository;
import de.hotelico.repository.CheckinRepository;
import de.hotelico.repository.CustomerRepository;
import de.hotelico.repository.DealRepository;
import de.hotelico.repository.HotelRepository;
import de.hotelico.repository.LanguageRepository;
import de.hotelico.service.CacheService;
import de.hotelico.service.ChatService;
import de.hotelico.service.CheckinService;
import de.hotelico.service.CustomerService;
import de.hotelico.service.HotelService;
import de.hotelico.service.LoginHotelicoService;
import de.hotelico.service.MailService;
import de.hotelico.service.NotificationService;
import de.hotelico.utils.ControllerUtils;
import de.hotelico.utils.HotelEvent;


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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService
{
    @Autowired
    private CheckinRepository checkinRepository;      
    
    @Autowired
    private ChatRepository chatRepository;    
    
    @Autowired
    private HotelRepository hotelRepository;     
    
    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private CustomerRepository customerRepository;
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
    private ChatService chatService;		
    
    @Autowired
    private HotelService hotelService;	
	
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
        List<Customer> list = customerRepository.findByActive(true);
        List<CustomerDTO> outList = new ArrayList<CustomerDTO>();
        for (Customer nextCustomer : list) {
            
            //TODO Eugen: get all customer hotelId with one query!
            long hotelId = getCustomerHotelId(nextCustomer.getId());
            
            CustomerDTO nextDto = convertCustomerToDto(nextCustomer, hotelId);

//            outList.add(updateOwnDtoCheckinInfo(nextDto));
            outList.add(nextDto);
        }
        return outList;
    }

    @Override
    public long getCustomerHotelId(long customerId){
        return cacheService.getCustomerHotelId(customerId);
    }
    
    @Override
    public CustomerDTO getById(long requesterId, long dtoRequesterId) {
        
        Customer customer = customerRepository.getOne(requesterId);
        
        CustomerDTO out = null;
       
        if(requesterId == dtoRequesterId){
            
            //I want MY Profile!
            out = convertMyCustomerToFullDto(customer);
        }
        else{ //fremd profile
            
            long requesterHotelId = getCustomerHotelId(requesterId);
            long hotelId = getCustomerHotelId(customer.getId());

            out = convertCustomerToDto(customer, hotelId);
            
            boolean isPartnerInMyHotelWithFullCheckin = requesterHotelId>0 && requesterHotelId==hotelId && checkinRepository.isFullCheckinForCustomerByHotelId(out.getId(), out.getHotelId(), new Date());

            out.setInMyHotel(isPartnerInMyHotelWithFullCheckin);

            if(dtoRequesterId >0)
            {
                Customer requester = getEntityById(dtoRequesterId);
                out = setDtoLastMessageWithRequester(requester, out);
            }
        }
        
        return out;
    }
    
    @Override
    public Customer getEntityById(long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        return customer;
    }

    @Transactional
    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDto, String password) {
                
        if(getByEmail(customerDto.getEmail())!=null)
        {
            customerDto.setErrorResponse("Email already registered");
            return customerDto;
        }
        
        Customer customer = modelMapper.map(customerDto, Customer.class);

        fillCustomerFromDto(customerDto, customer);
       
        customer = customerRepository.saveAndFlush(customer);
    
        long initHotelId = cacheService.getInitHotelId();

        if(customerDto.getHotelId()!=null && customerDto.getHotelId()>0)
        {
            Hotel hotel =  hotelRepository.getOne(customerDto.getHotelId());
          
            if(hotel!=null && customerDto.getHotelStaff())
            {
                customerDto.setCheckinFrom(new Date());
                customerDto.setCheckinTo(new DateTime().plusYears(10).toDate());
                checkinService.setCustomerCheckin(customerDto, customer);

                if (customerDto.getEmail() != null)
                {
                    mailService.sendMail(customerDto.getEmail(), "HoteliCo staff registration", "You have now a staff account for '" + hotel.getName() + "' hotel in Hotelico. Your password is: '" + customerDto.getPassword() + "'. \nYour HoteliCo team.", null);
                }
                
                customer.setHotelStaff(true);
                customer = customerRepository.saveAndFlush(customer);

                initHotelId = hotel.getId();
            }
        }


        if(customerDto.getPassword()!=null)
        {
            password = customerDto.getPassword();
        }
        
        long passwordHash = loginService.getCryptoHash(customer, password);
        customer.setPasswordHash(passwordHash);
        customer.setPasswordValue(password);
        customer.setLogged(true);
        //customer.updateLastSeenOnline();
        
        
		CustomerDTO dto =  convertMyCustomerToFullDto(customerRepository.saveAndFlush(customer));
        
        
        registerPush();
        
        
        //// NOTIFICATE OTHERS!!!!

        if(!dto.getHotelStaff() && !dto.getAdmin())
        {
            notificationService.notificateAboutEntityEvent(dto, HotelEvent.EVENT_REGISTER, "Now Registered!", dto.getId());
        }

        return dto;
    }

    private void registerPush()
    {
//        ortcClient.subscribeWithNotifications("myChannel", true, new OnMessage() {
//            public void run(OrtcClient sender, String channel, String message) {
//                Log.i(TAG, String.format("Message or push notification on channel %s: %s ",
//                        channel, message));
//            };
//        });
    }


    
    @Transactional
    @Override
    public CustomerDTO addLinkedInCustomer(CustomerDTO customerDto, String linkedInId){
        
       
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customer.setLinkedInId(linkedInId);
        customer.setLogged(true);
        customer.setProfileImageUrl(customerDto.getProfileImageUrl());

        //TODO eugen: get Languages from linkedIn
        
        long virtualHotelId = cacheService.getInitHotelId();
        
        CustomerDTO dto = convertCustomerToDto(customerRepository.saveAndFlush(customer), virtualHotelId);

        dto = loginService.checkBeforeLoginProperties(customerDto, dto);

        if(!dto.getHotelStaff() && !dto.getAdmin())
        {
            notificationService.notificateAboutEntityEvent(dto, HotelEvent.EVENT_REGISTER, "Now Registered!", dto.getId());
        }
        
        return dto;
//        return updateOwnDtoCheckinInfo(dto);
    }
    
    @Transactional
    @Override
    public CustomerDTO addFacebookCustomer(CustomerDTO customerDto, String facebookId){
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customer.setFacebookId(facebookId);
        customer.setProfileImageUrl(customerDto.getProfileImageUrl());
        
        //TODO eugen: get Languages from linkedIn
        
//        CustomerDto dto = modelMapper.map(customerRepository.saveAndFlush(customer), CustomerDto.class);
//        dto = fillDtoFromCustomer(customer, dto);
        
        long virtualHotelId = cacheService.getInitHotelId();
        
        CustomerDTO dto = convertCustomerToDto(customerRepository.saveAndFlush(customer), virtualHotelId);

        dto = loginService.checkBeforeLoginProperties(customerDto, dto);
        
        if(!dto.getHotelStaff() && !dto.getAdmin())
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
        
        long customerDbConsistencyId = cacheService.getCustomerConsistencyId(dto.getId());
        
        //TODO Eugen: ONLY DTO UPDate hier
        if(customerDbConsistencyId > dtoConsistencyId)
        {
            Customer customer = customerRepository.getOne(dto.getId());
            
            if(customer==null)
            {
                dto.setId(-1);
                return dto;
            }
            
//            int hotelId = getCustomerHotelId(customer.getId());

            dto = convertMyCustomerToFullDto(customer);
        }
        
        return dto;
    }

    
    
    
    
    @Override
    public List<Customer> getAllOnline()
    {
        Long milis = new DateTime().minusMinutes(25).getMillis();
        return customerRepository.findAllOnline(new Timestamp(milis));
    }  
	
    @Override
    public List<Customer> getAllIn24hOnline()
    {
        Long milis = new DateTime().minusDays(1).getMillis();
        return customerRepository.findAllOnline(new Timestamp(milis));
    }
    
    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDto, int updatorId) {
        Customer customer = customerRepository.getOne(customerDto.getId());
       
        if(customerDto.getSystemMessages()!=null && !customerDto.getSystemMessages().isEmpty())
        {
           if(customer !=null && customerDto.getSystemMessages().containsKey("guestCustomerId"))
           {
               Long guestId = Long.parseLong(customerDto.getSystemMessages().get("guestCustomerId"));

               if(guestId!=null && guestId>0)
               {
                   this.relocateGuestDealsToLoggedCustomer(customer, guestId);
               }
               
           } 
           
           if(customer !=null && customerDto.getSystemMessages().containsKey(ControllerUtils.PUSH_CHROME_ID))
           {
               customer.setPushRegistrationId(customerDto.getSystemMessages().get(ControllerUtils.PUSH_CHROME_ID));
           }
		   
		   if(customer !=null && customerDto.getSystemMessages().containsKey("latitude") && customerDto.getSystemMessages().containsKey("longitude"))
           {
                customer.setLatitude(Double.parseDouble(customerDto.getSystemMessages().get("latitude")));
			   customer.setLongitude(Double.parseDouble(customerDto.getSystemMessages().get("longitude")));
           }
           
           if(customerDto.getSystemMessages().containsKey("feedbackMessage"))
           {
               String feedbackMessage = customerDto.getSystemMessages().get("feedbackMessage");
              
               String fromName = customerDto.getSystemMessages().get("fromName");
               String fromEmail = customerDto.getSystemMessages().get("fromMail");
               
               if(ControllerUtils.isEmptyString(fromName))
               {
                   fromName = customer.getFirstName();
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
               
               mailService.sendMail("info@hotelico.de", "HoteliCo feedback from " + fromName + " " + (customer.getLastName() != null ? customer.getLastName() : "") + "(id=" + customer.getId() + ")", feedbackMessage + (fromEmail != null ? " [from " + fromEmail + "]" : ""), null);
           }
			
			if(customerDto.getSystemMessages().containsKey("mailList"))
           {
			   notificationService.sendMailList(customer, customerDto.getSystemMessages());
           }  
           
           if(customerDto.getSystemMessages().containsKey("hotelFeedMessage"))
           {
			   notificationService.sendFeedMessage(customer, customerDto.getSystemMessages());
            }          
           
           customerDto.setSystemMessages(new HashMap<String, String>());
        }
       
        if(customer!=null)
        {
            fillCustomerFromDto(customerDto, customer);
            customer.setCity(customerDto.getCity());
            customer.setOriginalCity(customerDto.getOriginalCity());
            customer.setJobTitle(customerDto.getJobTitle());
            customer.setJobDescriptor(customerDto.getJobDescriptor());
            customer.setFirstName(customerDto.getFirstName());
            customer.setLastName(customerDto.getLastName());
            customer.setStatus(customerDto.getStatus());
            customer.setSex(customerDto.getSex());
            customer.setShowAvatar(customerDto.getShowAvatar());
            customer.setAllowHotelNotification(customerDto.getAllowHotelNotification());
            customer.setShowInGuestList(customerDto.getShowInGuestList());
            
            if(customerDto.getEmail()!=null)
            {
                customer.setEmail(customerDto.getEmail());
            }
            
            customer.setCompany(customerDto.getCompany());
            customer.setPrefferedLanguage(customerDto.getPrefferedLanguage());
            customer.setProfileImageUrl(customerDto.getProfileImageUrl());
            
            //EUGEN: only disable(true)!!! enable is not allowed!
			if(customerDto.getHideCheckinPopup())
			{
				customer.setHideCheckinPopup(customerDto.getHideCheckinPopup());
			}
			
			if(customerDto.getHideChromePushPopup())
			{
				customer.setHideChromePushPopup(customerDto.getHideChromePushPopup());
			}
            
            //EUGEN: only disable(true)!!! enable is not allowed!
            if(customerDto.getHideHotelListPopup())
			{
				customer.setHideHotelListPopup(customerDto.getHideHotelListPopup());
			}
            
            //EUGEN: only disable(true)!!! enable is not allowed!
            if(customerDto.getHideWallPopup())
			{
				customer.setHideWallPopup(customerDto.getHideWallPopup());
			}
			
            if(updatorId == customer.getId())
            {
                customer.setLogged(true);
            }
            
            if(customerDto.getPassword()!=null && !customerDto.getPassword().isEmpty() && customerDto.getPassword().length()>5)
            {
                long passwordHash = loginService.getCryptoHash(customer, customerDto.getPassword());
                customer.setPasswordHash(passwordHash);
                customer.setPasswordValue(customerDto.getPassword());
            }

            if(customer.isGuestAccount() && customer.getEmail()!=null && customer.getLastName()!=null && customer.getPasswordHash()!=null)
            {
                customer.setGuestAccount(false);
            }
            
            long consistencyId = new Date().getTime();
            customer.setConsistencyId(consistencyId);

            cacheService.updateCustomerConsistencyId(customer.getId(), consistencyId);
            
            customerRepository.saveAndFlush(customer);
        }
        
//        int hotelId = getCustomerHotelId(customerDto.getId());

        CustomerDTO dto = convertMyCustomerToFullDto(customer);

        return dto;
    }
	
	@Override
    public boolean relocateGuestDealsToLoggedCustomer(Customer customer, Long guestCustomerId)
    {
        if(customer==null || guestCustomerId==null)
        {
            return false;
        }
        
        List<CustomerDeal> anonymGuestDeals = dealRepository.getAnonymDealsByGuestId(guestCustomerId);
        
        for (CustomerDeal next: anonymGuestDeals)
        {
            next.setCustomer(customer);
//            next.setGuestCustomerId(0);
            dealRepository.saveAndFlush(next);
        }
        
        return !(anonymGuestDeals.isEmpty());
    }

    private void fillCustomerFromDto(CustomerDTO customerDto, Customer customer)
    {
        //NOTE eugen: hier set only different [Entity-Dto] fields
        String[] languageDescriptions = customerDto.getLanguages();
        customer.getLanguageSet().clear();
        
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
                    customer.getLanguageSet().add(nextLanguage);
                }
            }
        }
        
        if(customerDto.getBirthdayTime()!=null)
        {
            Long birtdayTime = Long.parseLong(customerDto.getBirthdayTime());
            Date birthdayDate = new Date(birtdayTime);
//            customer.setBirthday(birthdayDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            customer.setBirthday(birthdayDate);
        }
        
        if(customerDto.getProfileImageUrl()==null)
        {
            customer.setProfileImageUrl("");
        }
        
    }
    
    @Override
    public CustomerDTO convertCustomerToDto(Customer customer, long hotelId){
        return convertCustomerToDto(customer, hotelId, false, null);
    }
    
    @Override
    public CustomerDTO convertCustomerToDto(Customer customer, boolean fullSerialization, CustomerHotelCheckin validCheckin){
        return convertCustomerToDto(customer, validCheckin.getHotel().getId(), fullSerialization, validCheckin);
    }
    
    @Override
    public CustomerDTO convertMyCustomerToFullDto(Customer customer){
        return convertCustomerToDto(customer, 0, true, null);
    }
    
    
//    @Override
    private CustomerDTO convertCustomerToDto(Customer customer, long hotelId, boolean fullSerialization, CustomerHotelCheckin validCheckin)
    {
        if(customer==null) 
        {
            return null;
        }
        
        CustomerDTO dto = modelMapper.map(customer, CustomerDTO.class);
        
        dto = fillDtoProfileInfo(customer, dto, fullSerialization);
	
//      if(hotelId>=0)
        {
            dto = serializeCustomerHotelInfo(dto, hotelId, fullSerialization, validCheckin);
        }

        if(dto.getHotelStaff() && !ControllerUtils.isEmptyString(dto.getHotelName()))
        {
            dto.setFirstName(dto.getHotelName());
            dto.setLastName("");
        }
        
        
        return dto;
    }

    /**
     * set customer profile data
     * @param customer
     * @param dto
     * @return
     */
    private CustomerDTO fillDtoProfileInfo(Customer customer, CustomerDTO dto, boolean fullSerialization)
    {
        if(customer!=null && customer.getLanguageSet()!=null)
        {
            String[] newLanguages = new String[customer.getLanguageSet().size()];
            
            int counter = 0;

            for (Language nextLanguage : customer.getLanguageSet())
            {
                newLanguages[counter++] = nextLanguage.getDescriptionShort();
            }

            dto.setLanguages(newLanguages);
            
            dto.setAvatarUrl(getCustomerAvatarUrl(customer));
            //            
            // if(customer.getLastSeenOnline()!=null)
            //            {
            //                dto.setLastSeenOnlineString(ControllerUtils.dateTimeFormat.format(customer.getLastSeenOnline()));
            //            }
            
            dto.setOnline(ControllerUtils.isCustomerOnline(customer));
            
			dto.addSystemMessage("hasPushRegistrationId", String.valueOf(customer.getPushRegistrationId()!=null));
			
            if(fullSerialization) //SHOW PRIVATE INFORMATION
            {
                dto.setCustomerConsistencyId(customer.getConsistencyId());
                
                if(customer.getBirthday()!=null)
                {
                    dto.setBirthdayTime(customer.getBirthday().getTime()+"");
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
    public String getCustomerAvatarUrl(Customer customer)
    {

        String avatar_m = /*isOrange? "angulr/img/build/avatar/incognito-orange-m.png" :*/ "/" + ControllerUtils.HOST_SUFFIX + "angulr/img/build/avatar/incognito-m.png";
        String avatar_f = /*isOrange? "angulr/img/build/avatar/incognito-orange-f.png" :*/  "/" + ControllerUtils.HOST_SUFFIX + "angulr/img/build/avatar/incognito-f.png";

        if(customer.isHotelStaff())
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
        String currentPictureUrl = ControllerUtils.isEmptyString(customer.getPictureUrl()) ? customer.getProfileImageUrl() : customer.getPictureUrl();
        //		if(!isShowAvatar() && userId && $rootScope.allCustomersById && $rootScope.allCustomersById[userId] && $rootScope.allCustomersById[userId].avatarUrl.length>0 && $rootScope.allCustomersById[userId].showAvatar)
        if(customer.isShowAvatar() && !ControllerUtils.isEmptyString(currentPictureUrl))
		{
			//			return $rootScope.allCustomersById[userId].avatarUrl;
			return currentPictureUrl.startsWith("http") || currentPictureUrl.startsWith("/")? currentPictureUrl :  "/" + currentPictureUrl;
		}

        if(customer.getSex().equalsIgnoreCase("w"))
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


        long virtualCodeId = cacheService.getInitHotelId();
        
        if(dto.getHotelId()!= null && dto.getHotelId()>0 && dto.getHotelId() != virtualCodeId)
        {
            Hotel outHotel = validCheckin!=null? validCheckin.getHotel() : hotelRepository.getOne(dto.getHotelId());
            if(outHotel!=null && !outHotel.isVirtual())
            {
                dto = fillDtoWithHotelInfo(dto, outHotel, validCheckin);
            }
        }
        
        return dto;
    }

	@Override
    public CustomerDTO fillDtoWithHotelInfo(CustomerDTO dto, Hotel outHotel, CustomerHotelCheckin validCheckin)
    {
        if(outHotel!=null && dto!=null)
        {
            dto.setHotelId(outHotel.getId());
            dto.setHotelName(outHotel.getName());
            dto.setHotelCity(outHotel.getCity());
            dto.setCheckedIn(true);
            dto.setHotelConsistencyId(outHotel.getConsistencyId());

            if(ControllerUtils.HOTEL_DEMO_CODE.equalsIgnoreCase(outHotel.getCurrentHotelAccessCode()))
            {
                dto.setFullCheckin(true);
            }
            else if(validCheckin!=null){
                dto.setFullCheckin(validCheckin.isFullCheckin());
            }

            if(dto.getHotelStaff())
            {
                dto.setFirstName(outHotel.getName());
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
			boolean isReadyForNextNotification = cacheService.isNotificationDelayReady(sessionCustomerId);
			
			cacheService.checkCustomerOnline(sessionCustomerId);
			
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
        List<Customer> customers = customerRepository.findByLinkedInIdAndActive(linkedInId, true);
        
        if(customers.isEmpty())
        {
            return null;
        }
        
        //TODO eugen: get Langguages from LinkedIn
//        int hotelId = getCustomerHotelId(customer.getId());
        CustomerDTO dto = convertMyCustomerToFullDto(customers.get(0));

        return dto;
    }
    
    @Override
    public CustomerDTO getByFacebookId(String facebookId){
        List<Customer> customers = customerRepository.findByfacebookIdAndActive(facebookId, true);
        
        if(customers.isEmpty())
        {
            return null;
        }
        
        //TODO eugen: get Langguages from LinkedIn
//        int hotelId = getCustomerHotelId(customer.getId());
        CustomerDTO out = convertMyCustomerToFullDto(customers.get(0));

//        return updateOwnDtoCheckinInfo(out);
        return out;
    }

    @Override
    public CustomerDTO getByEmail(String email)
    {
        if(email==null)
        {
            return null;
        }
        
        List<Customer> customers = customerRepository.findByEmailAndActive(email, true);

        CustomerDTO out = null;
        
        if(!customers.isEmpty())
        {
            Customer customer = customers.get(0);
            
            long hotelId = getCustomerHotelId(customer.getId());
            out = convertMyCustomerToFullDto(customer);
        }
        
//        return updateOwnDtoCheckinInfo(out);
        return out;
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
            CustomerDTO dto = new CustomerDTO();
            dto.setCity(nextCity);
            resultCustomerList.add(dto);
        }
        
        return resultCustomerList;
    }
    
    @Override
    public Set<CustomerDTO> getByCity(long customerId, String city)
    {
        //TODO Eugen: bessere query by active and city
        List<Customer> allCustomers = customerRepository.findAll();

        Set<CustomerDTO> resultList = new HashSet<>();
        
        for (Customer nextCustomer: allCustomers)
        {
            if(nextCustomer.isActive() && (nextCustomer.getCity()!=null && nextCustomer.getCity().equals(city) || city==null && nextCustomer.getCity()==null))
            {
                //TODO Eugen: create global convert method to dto...
                long hotelId = getCustomerHotelId(nextCustomer.getId());
                CustomerDTO dto = convertCustomerToDto(nextCustomer, hotelId);

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
        
        Customer requester = customerRepository.getOne(requesterId);
        
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
            Customer nextCustomer = nextCheckin.getCustomer();

            //eugen: filter guest accounts not active more than 1 day
            if(nextCustomer.isGuestAccount() && nextCustomer.getLastSeenOnline()!=null && nextCustomer.getLastSeenOnline().before(new DateTime().minusDays(ControllerUtils.AWAY_GUEST_DAYS_TIMEOUT).toDate()))
            {
                if(ControllerUtils.SET_AWAY_GUEST_INACTIVE)
                {
                    nextCustomer.setActive(false);
                }

                if(ControllerUtils.SET_AWAY_GUEST_CHECKOUT)
                {
                    //remove guest checkin
                    List<CustomerHotelCheckin> guestCheckin = checkinRepository.getActiveByCustomerId(nextCustomer.getId(), new Date());
                    for (CustomerHotelCheckin nextGuestCheckin : guestCheckin)
                    {
                        nextGuestCheckin.setActive(false);
                        checkinRepository.saveAndFlush(nextGuestCheckin);
                    }
                }
                customerRepository.save(nextCustomer);
                continue;
            }
            
            if(nextCustomer.isHotelStaff())
            {
                continue;
            }
            
            CustomerDTO dto = convertCustomerToDto(nextCustomer, true, nextCheckin);
            
            boolean isPartnerInMyHotelWithFullCheckin = requesterHotelId>0 && requesterHotelId==dto.getHotelId() && nextCheckin.isFullCheckin();

            dto.setInMyHotel(isPartnerInMyHotelWithFullCheckin);

            dto = setDtoLastMessageWithRequester(requester, dto);
            
            customerDtoSet.add(dto);
        }
        
        if(addStaff && hotelId>0)
        {
            List<Customer> staffs = checkinRepository.getStaffByHotelId(hotelId);
            
            for(Customer nextCustomer : staffs)
            {
                CustomerDTO dto = convertCustomerToDto(nextCustomer, hotelId);

                boolean isPartnerInMyHotelWithFullCheckin = requesterHotelId>0 && requesterHotelId==dto.getHotelId();// && nextCheckin.isFullCheckin();

                dto.setInMyHotel(isPartnerInMyHotelWithFullCheckin);

                dto = setDtoLastMessageWithRequester(requester, dto);

                customerDtoSet.add(dto);
            }
        }
        
        return customerDtoSet;
    }

    @Override
    public CustomerDTO setDtoLastMessageWithRequester(Customer requester, CustomerDTO dto)
    {
        if(requester==null || dto==null)
        {
            if(requester==null && dto!=null)
            {
                dto.setInMyHotel(true);
            }
            
            return  dto;
        }
        
        ChatMessage lastMessage = chatRepository.getLastMessageByCustomerAndReceiverIds(dto.getId(), requester.getId());

        long requesterHotelId = this.getCustomerHotelId(requester.getId());

        dto.setInMyHotel(requesterHotelId > 0 && requesterHotelId == dto.getHotelId());
        
        if(lastMessage!=null)
		{
			Timestamp lastMessageTimestamp = lastMessage.getTimestamp();

			String time = ControllerUtils.getTimeFormatted(lastMessageTimestamp);
			
			String message = lastMessage.getMessage();
			message = message.length() > 11 ? message.substring(0, 10)+"...": message;

            dto.setLastMessageToMe(message + " (at " + time + ")");
			dto.setLastMessageTimeToMe(lastMessageTimestamp.getTime());
            dto.setChatWithMe(true);
		}
        
        return dto;
    }

    @Override
    public Customer addGetAnonymCustomer()
    {
        List<Customer> anonymes = customerRepository.getAnonymeCustomer();
       
        Customer anonym = null;
                
        if(anonymes.isEmpty())
        {
            anonym = new Customer();
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
