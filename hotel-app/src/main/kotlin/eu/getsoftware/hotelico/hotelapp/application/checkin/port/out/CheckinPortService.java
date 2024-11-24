package eu.getsoftware.hotelico.hotelapp.application.checkin.port.out;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CheckinDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.CheckinRequestDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.CheckinRootDomainEntity;

import java.util.Date;
import java.util.List;

/**
 * out port - обслуживает именно меня, можно entity использовать and not only dto usage
 */
public interface CheckinPortService {
    
    List<CheckinRootDomainEntity> getActiveByCustomerId(CustomerDomainEntityId id, Date date);

    Date getLastByCustomerAndHotelId(CustomerDomainEntityId id, HotelDomainEntityId id1);

    void save(CheckinRootDomainEntity CheckinRootDomainEntity);

    List<CustomerDTO> getStaffByHotelId(HotelDomainEntityId hotelId);

    CheckinRootDomainEntity createCheckin(CheckinRequestDTO customerRequestDto);

    CheckinRootDomainEntity createCheckin(CustomerDTO customer, HotelDTO hotel, boolean isFullCheckin);

    Integer getActiveCountByHotelId(HotelDomainEntityId receiverHotelId, Date date);

//    CheckinDTO getResponseDTO(CheckinRootDomainEntity newCheckin);

    void deleteAllImagesAndAttachments(CheckinDTO checkinDTO);

    CustomerDTO updateCheckin(CustomerDTO sessionCustomer);

    CustomerDBEntity getStaffbyHotelId(HotelDomainEntityId hotelId);

    List<CustomerDTO> getActiveCustomersByHotelId(HotelDomainEntityId hotelId, Date date);
}
