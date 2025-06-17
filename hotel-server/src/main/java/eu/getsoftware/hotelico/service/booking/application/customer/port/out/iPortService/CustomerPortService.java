package eu.getsoftware.hotelico.service.booking.application.customer.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * All methods of this class throws NullPoinrerException if a required argument is null
 */
public interface CustomerPortService<T extends CustomerRootDomainEntity>
{
    List<T> getCustomerEntities();

    Optional<T> getEntityById(CustomerDomainEntityId customerId);

    List<CustomerDTO> getCustomerDTOs();

    HotelDomainEntityId getCustomerHotelId(CustomerDomainEntityId customerId);

    Optional<CustomerDTO> getByEmail(String email);

    Set<CustomerDTO> getByHotelId(CustomerDomainEntityId customerId, HotelDomainEntityId hotelId, boolean addStaff);

    CustomerDTO fillDtoWithHotelInfo(CustomerDTO dto, CheckinRootDomainEntity validCheckin);

    Set<CustomerDTO> getByCity(CustomerDomainEntityId customerId, String city);

    Set<CustomerDTO> getCustomerCities(CustomerDomainEntityId customerId);

    Optional<CustomerDTO> getById(CustomerDomainEntityId customerId, long requesterCustomerId);

    CustomerDTO addCustomer(CustomerDTO customerDto, String password);

    CustomerDTO updateCustomer(CustomerDTO customerDto, int requesterId);

//    @Transactional
    boolean relocateGuestDealsToLoggedCustomer(T customerEntity, CustomerDomainEntityId guestCustomerId);

    CustomerDTO convertCustomerWithHotelToDto(T customerEntity, HotelDomainEntityId hotelId);

    CustomerDTO convertCustomerWithHotelToDto(T customerEntity, boolean fullSerialization, CheckinRootDomainEntity validCheckin);
    CustomerDTO convertMyCustomerToFullDto(T customerEntity);

    CustomerDTO serializeCustomerHotelInfo(CustomerDTO dto, HotelDomainEntityId hotelId, boolean fullSerialization, CheckinRootDomainEntity validCheckin);

    CustomerDTO synchronizeCustomerToDto(CustomerDTO customerDto);

    @Transactional
    void deleteCustomer(CustomerDTO customerDto);

    String getCustomerAvatarUrl(T customerEntity);

    /**
     * sometimes we need anonym customer entity
     * @return
     */
    T addGetAnonymCustomer();

    boolean isStaffOrAdminId(CustomerDomainEntityId receiverId);

    void save(T customerEntity);

    void setCustomerPing(CustomerDomainEntityId customerId);

    List<CustomerDTO> findAllOnline(Timestamp timestamp);

    List<T> getAllIn24hOnline();

    Optional<T> getByDomainId(CustomerDomainEntityId customerId);
}
