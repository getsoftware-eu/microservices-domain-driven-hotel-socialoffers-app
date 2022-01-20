package de.hotelico.service;

import de.hotelico.dto.CustomerDTO;
import de.hotelico.model.Customer;
import de.hotelico.model.CustomerHotelCheckin;
import de.hotelico.model.Hotel;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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
	
	CustomerDTO fillDtoWithHotelInfo(CustomerDTO dto, Hotel outHotel, CustomerHotelCheckin validCheckin);
	
	/**
     * Set this dto info personally to me
     * @param requester
     * @param dto
     * @return
     */
    @Transactional CustomerDTO setDtoLastMessageWithRequester(Customer requester, CustomerDTO dto);

    @Transactional
    Set<CustomerDTO> getByCity(long customerId, String city);
    
    @Transactional
    Set<CustomerDTO> getCustomerCities(long customerId);

    @Transactional CustomerDTO getById(long customerId, long requesterCustomerId);
    
    @Transactional
    Customer getEntityById(long customerId);
        
    @Transactional CustomerDTO addCustomer(CustomerDTO customerDto, String password);
   
    @Transactional CustomerDTO addLinkedInCustomer(CustomerDTO customerDto, String linkedIn);
       
    @Transactional CustomerDTO addFacebookCustomer(CustomerDTO customerDto, String facebookId);
    
    @Transactional CustomerDTO updateCustomer(CustomerDTO customerDto, int requesterId);
    
    @Transactional
    boolean relocateGuestDealsToLoggedCustomer(Customer customer, Long guestCustomerId);

    @Transactional
    List<Customer> getAllOnline();
	
    @Transactional
    List<Customer> getAllIn24hOnline();

    CustomerDTO convertCustomerToDto(Customer customer, long hotelId);

    CustomerDTO convertCustomerToDto(Customer customer, boolean fullSerialization, CustomerHotelCheckin validCheckin);
    CustomerDTO convertMyCustomerToFullDto(Customer customer);
		
//	CustomerDto convertCustomerToDto(Customer customer, int hotelId, boolean fullSerialization);
    
    @Transactional CustomerDTO serializeCustomerHotelInfo(CustomerDTO dto, long hotelId, boolean fullSerialization, CustomerHotelCheckin validCheckin);
    
    @Transactional CustomerDTO synchronizeCustomerToDto(CustomerDTO customerDto);
    
    @Transactional
    void deleteCustomer(CustomerDTO customerDto);
    
    @Transactional
    void setCustomerPing(long sessionCustomerId);

    //    @Transactional
//    CustomerDto addUnreadInfo(CustomerDto out);

    String getCustomerAvatarUrl(Customer customer);


    /**
     * sometimes we need anonym customer entity
     * @return
     */
    Customer addGetAnonymCustomer();

    boolean isStaffOrAdminId(long receiverId);
}
