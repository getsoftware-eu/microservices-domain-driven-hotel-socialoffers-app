package eu.getsoftware.hotelico.customer.domain.iService;

import eu.getsoftware.hotelico.checkin.domain.CustomerHotelCheckin;
import eu.getsoftware.hotelico.clients.infrastructure.hotel.dto.CustomerDTO;
import eu.getsoftware.hotelico.customer.domain.iEntity.ICustomerEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * All methods of this class throws NullPoinrerException if a required argument is null
 */
public interface CustomerService
{
    List<CustomerDTO> getCustomers();
    
    long getCustomerHotelId(long customerId);
    
    CustomerDTO getByLinkedInId(String linkedInId);
    
    /**
     * 
     * @param facebookId
     * @return Dto of customer 
     * returns ResourceNotFoundException if customer with facebookId not found
     */
    Optional<CustomerDTO> getByFacebookId(String facebookId);    
    
    Optional<CustomerDTO> getByEmail(String email);
    
    @Transactional
    Set<CustomerDTO> getByHotelId(long customerId, long hotelId, boolean addStaff);
	
	CustomerDTO fillDtoWithHotelInfo(CustomerDTO dto, CustomerHotelCheckin validCheckin);
	
	
    @Transactional
    Set<CustomerDTO> getByCity(long customerId, String city);
    
    @Transactional
    Set<CustomerDTO> getCustomerCities(long customerId);

    @Transactional CustomerDTO getById(long customerId, long requesterCustomerId);
    
    @Transactional
    ICustomerEntity getEntityById(long customerId);
        
    @Transactional CustomerDTO addCustomer(CustomerDTO customerDto, String password);
   
    @Transactional CustomerDTO addLinkedInCustomer(CustomerDTO customerDto, String linkedIn);
       
    @Transactional 
    CustomerDTO addFacebookCustomer(CustomerDTO customerDto, String facebookId);
    
    @Transactional CustomerDTO updateCustomer(CustomerDTO customerDto, int requesterId);
    
    @Transactional
    boolean relocateGuestDealsToLoggedCustomer(ICustomerEntity customerEntity, Long guestCustomerId);

    @Transactional
    List<ICustomerEntity> getAllOnline();
	
    @Transactional
    List<ICustomerEntity> getAllIn24hOnline();

    CustomerDTO convertCustomerToDto(ICustomerEntity customerEntity, long hotelId);

    CustomerDTO convertCustomerToDto(ICustomerEntity customerEntity, boolean fullSerialization, CustomerHotelCheckin validCheckin);
    CustomerDTO convertMyCustomerToFullDto(ICustomerEntity customerEntity);
		
    @Transactional CustomerDTO serializeCustomerHotelInfo(CustomerDTO dto, long hotelId, boolean fullSerialization, CustomerHotelCheckin validCheckin);
    
    @Transactional CustomerDTO synchronizeCustomerToDto(CustomerDTO customerDto);
    
    @Transactional
    void deleteCustomer(CustomerDTO customerDto);
    
    @Transactional
    void setCustomerPing(long sessionCustomerId);

    String getCustomerAvatarUrl(ICustomerEntity customerEntity);


    /**
     * sometimes we need anonym customer entity
     * @return
     */
    ICustomerEntity addGetAnonymCustomer();

    boolean isStaffOrAdminId(long receiverId);
}
