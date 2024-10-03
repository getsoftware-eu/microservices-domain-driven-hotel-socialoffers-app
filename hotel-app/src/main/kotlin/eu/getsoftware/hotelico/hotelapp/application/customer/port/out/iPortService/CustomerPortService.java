package eu.getsoftware.hotelico.hotelapp.application.customer.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotelCustomer.model.CustomerHotelCheckin;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerHotelCheckin;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * All methods of this class throws NullPoinrerException if a required argument is null
 */
public interface CustomerPortService
{
    List<CustomerDTO> getCustomers();
    
    long getCustomerHotelId(long customerId);
    
    Optional<CustomerDTO> getByEmail(String email);
    
    Set<CustomerDTO> getByHotelId(long customerId, long hotelId, boolean addStaff);
	
	CustomerDTO fillDtoWithHotelInfo(CustomerDTO dto, ICustomerHotelCheckin validCheckin);
   
    Set<CustomerDTO> getByCity(long customerId, String city);
    
    Set<CustomerDTO> getCustomerCities(long customerId);

    CustomerDTO getById(long customerId, long requesterCustomerId);
    
    ICustomerRootEntity getEntityById(long customerId);
 
    CustomerDTO addCustomer(CustomerDTO customerDto, String password);
    
    CustomerDTO updateCustomer(CustomerDTO customerDto, int requesterId);
    
//    @Transactional
    boolean relocateGuestDealsToLoggedCustomer(ICustomerRootEntity customerEntity, Long guestCustomerId);

    CustomerDTO convertCustomerToDto(ICustomerRootEntity customerEntity, long hotelId);

    CustomerDTO convertCustomerToDto(ICustomerRootEntity customerEntity, boolean fullSerialization, CustomerHotelCheckin validCheckin);
    CustomerDTO convertMyCustomerToFullDto(ICustomerRootEntity customerEntity);
		
    CustomerDTO serializeCustomerHotelInfo(CustomerDTO dto, long hotelId, boolean fullSerialization, CustomerHotelCheckin validCheckin);
    
    CustomerDTO synchronizeCustomerToDto(CustomerDTO customerDto);
    
    @Transactional
    void deleteCustomer(CustomerDTO customerDto);
    
    String getCustomerAvatarUrl(ICustomerRootEntity customerEntity);

    /**
     * sometimes we need anonym customer entity
     * @return
     */
    ICustomerRootEntity addGetAnonymCustomer();

    boolean isStaffOrAdminId(long receiverId);

    CustomerDTO save(CustomerDTO customerEntity);

    CustomerDTO getOne(Long id);
}
