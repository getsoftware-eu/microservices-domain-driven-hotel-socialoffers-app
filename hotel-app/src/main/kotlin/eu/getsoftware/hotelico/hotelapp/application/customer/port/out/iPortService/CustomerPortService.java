package eu.getsoftware.hotelico.hotelapp.application.customer.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
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
    List<ICustomerRootEntity> getCustomers();
    
    long getCustomerHotelId(long customerId);
    
    Optional<ICustomerRootEntity> getByEmail(String email);
    
    Set<ICustomerRootEntity> getByHotelId(long customerId, long hotelId, boolean addStaff);

    CustomerDTO fillDtoWithHotelInfo(CustomerDTO dto, ICustomerHotelCheckin validCheckin);
   
    Set<ICustomerRootEntity> getByCity(long customerId, String city);
    
    Set<ICustomerRootEntity> getCustomerCities(long customerId);

    ICustomerRootEntity getById(long customerId, long requesterCustomerId);
    
    Optional<ICustomerRootEntity> getEntityById(long customerId);
 
    ICustomerRootEntity addCustomer(ICustomerRootEntity customerDto, String password);
    
    ICustomerRootEntity updateCustomer(ICustomerRootEntity customerDto, int requesterId);
    
//    @Transactional
    boolean relocateGuestDealsToLoggedCustomer(ICustomerRootEntity customerEntity, Long guestCustomerId);

    CustomerDTO convertCustomerToDto(ICustomerRootEntity customerEntity, long hotelId);

    CustomerDTO convertCustomerToDto(ICustomerRootEntity customerEntity, boolean fullSerialization, ICustomerHotelCheckin validCheckin);
    CustomerDTO convertMyCustomerToFullDto(ICustomerRootEntity customerEntity);
		
    ICustomerRootEntity serializeCustomerHotelInfo(ICustomerRootEntity dto, long hotelId, boolean fullSerialization, ICustomerHotelCheckin validCheckin);

    CustomerDTO synchronizeCustomerToDto(ICustomerRootEntity customerDto);
    
    @Transactional
    void deleteCustomer(ICustomerRootEntity customerDto);
    
    String getCustomerAvatarUrl(ICustomerRootEntity customerEntity);

    /**
     * sometimes we need anonym customer entity
     * @return
     */
    ICustomerRootEntity addGetAnonymCustomer();

    boolean isStaffOrAdminId(long receiverId);

    ICustomerRootEntity save(ICustomerRootEntity customerEntity);

    ICustomerRootEntity getOne(Long id);
}
