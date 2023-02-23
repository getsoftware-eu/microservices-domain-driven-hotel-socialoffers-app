package eu.getsoftware.hotelico.customer.infrastructure.service;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import eu.getsoftware.hotelico.checkin.domain.CustomerHotelCheckin;
import eu.getsoftware.hotelico.customer.domain.CustomerRootEntity;
import eu.getsoftware.hotelico.customer.infrastructure.dto.CustomerDTO;

public interface CustomerService
{
    @Transactional
    List<CustomerDTO> getCustomers();
    
    @Transactional
    long getCustomerHotelId(long customerId);
    
    @Transactional CustomerDTO getByLinkedInId(String linkedInId);    
    
    @Transactional CustomerDTO getByFacebookId(String facebookId);    
    
    @Transactional CustomerDTO getByEmail(String email);
    
    @Transactional
    Set<CustomerDTO> getByHotelId(long customerId, long hotelId, boolean addStaff);
	
	CustomerDTO fillDtoWithHotelInfo(CustomerDTO dto, CustomerHotelCheckin validCheckin);
	
	/**
     * Set this dto info personally to me
     * @param requester
     * @param dto
     * @return
     */
    @Transactional CustomerDTO setDtoLastMessageWithRequester(CustomerRootEntity requester, CustomerDTO dto);

    @Transactional
    Set<CustomerDTO> getByCity(long customerId, String city);
    
    @Transactional
    Set<CustomerDTO> getCustomerCities(long customerId);

    @Transactional CustomerDTO getById(long customerId, long requesterCustomerId);
    
    @Transactional
    CustomerRootEntity getEntityById(long customerId);
        
    @Transactional CustomerDTO addCustomer(CustomerDTO customerDto, String password);
   
    @Transactional CustomerDTO addLinkedInCustomer(CustomerDTO customerDto, String linkedIn);
       
    @Transactional CustomerDTO addFacebookCustomer(CustomerDTO customerDto, String facebookId);
    
    @Transactional CustomerDTO updateCustomer(CustomerDTO customerDto, int requesterId);
    
    @Transactional
    boolean relocateGuestDealsToLoggedCustomer(CustomerRootEntity customerEntity, Long guestCustomerId);

    @Transactional
    List<CustomerRootEntity> getAllOnline();
	
    @Transactional
    List<CustomerRootEntity> getAllIn24hOnline();

    CustomerDTO convertCustomerToDto(CustomerRootEntity customerEntity, long hotelId);

    CustomerDTO convertCustomerToDto(CustomerRootEntity customerEntity, boolean fullSerialization, CustomerHotelCheckin validCheckin);
    CustomerDTO convertMyCustomerToFullDto(CustomerRootEntity customerEntity);
		
    @Transactional CustomerDTO serializeCustomerHotelInfo(CustomerDTO dto, long hotelId, boolean fullSerialization, CustomerHotelCheckin validCheckin);
    
    @Transactional CustomerDTO synchronizeCustomerToDto(CustomerDTO customerDto);
    
    @Transactional
    void deleteCustomer(CustomerDTO customerDto);
    
    @Transactional
    void setCustomerPing(long sessionCustomerId);

    String getCustomerAvatarUrl(CustomerRootEntity customerEntity);


    /**
     * sometimes we need anonym customer entity
     * @return
     */
    CustomerRootEntity addGetAnonymCustomer();

    boolean isStaffOrAdminId(long receiverId);
}
