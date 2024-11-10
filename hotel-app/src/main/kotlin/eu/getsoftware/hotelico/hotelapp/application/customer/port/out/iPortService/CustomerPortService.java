package eu.getsoftware.hotelico.hotelapp.application.customer.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.model.ICustomerHotelCheckinEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * All methods of this class throws NullPoinrerException if a required argument is null
 */
public interface CustomerPortService<T extends ICustomerRootEntity>
{
    List<T> getCustomerEntities();

    Optional<T> getEntityById(long customerId);

    List<CustomerDTO> getCustomerDTOs();
    
    long getCustomerHotelId(long customerId);
    
    Optional<CustomerDTO> getByEmail(String email);
    
    Set<CustomerDTO> getByHotelId(long customerId, long hotelId, boolean addStaff);

    CustomerDTO fillDtoWithHotelInfo(CustomerDTO dto, ICustomerHotelCheckinEntity validCheckin);
   
    Set<CustomerDTO> getByCity(long customerId, String city);
    
    Set<CustomerDTO> getCustomerCities(long customerId);

    Optional<CustomerDTO> getById(long customerId, long requesterCustomerId);
    
    CustomerDTO addCustomer(CustomerDTO customerDto, String password);

    CustomerDTO updateCustomer(CustomerDTO customerDto, int requesterId);
    
//    @Transactional
    boolean relocateGuestDealsToLoggedCustomer(T customerEntity, Long guestCustomerId);

    CustomerDTO convertCustomerToDto(T customerEntity, long hotelId);

    CustomerDTO convertCustomerToDto(T customerEntity, boolean fullSerialization, ICustomerHotelCheckinEntity validCheckin);
    CustomerDTO convertMyCustomerToFullDto(T customerEntity);
		
    CustomerDTO serializeCustomerHotelInfo(CustomerDTO dto, long hotelId, boolean fullSerialization, ICustomerHotelCheckinEntity validCheckin);

    CustomerDTO synchronizeCustomerToDto(CustomerDTO customerDto);
    
    @Transactional
    void deleteCustomer(CustomerDTO customerDto);
    
    String getCustomerAvatarUrl(T customerEntity);

    /**
     * sometimes we need anonym customer entity
     * @return
     */
    T addGetAnonymCustomer();

    boolean isStaffOrAdminId(long receiverId);

    void save(T customerEntity);

    void setCustomerPing(long customerId);

    List<CustomerDTO> findAllOnline(Timestamp timestamp);

    List<T> getAllIn24hOnline();
}
