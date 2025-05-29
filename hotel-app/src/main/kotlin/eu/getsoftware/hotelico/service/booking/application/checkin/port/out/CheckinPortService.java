package eu.getsoftware.hotelico.service.booking.application.checkin.port.out;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseRequestDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.HotelDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * out port - обслуживает именно меня, можно entity использовать and not only dto usage
 */
public interface CheckinPortService {

    // Queries
    CustomerDBEntity getStaffbyHotelId(HotelDomainEntityId hotelId);

    List<CheckinRootDomainEntity> getActiveByCustomerId(CustomerDomainEntityId id, Date date);

    // Commands
    
    void save(CheckinRootDomainEntity CheckinRootDomainEntity);
    
    CheckinRootDomainEntity createCheckin(CheckinUseCaseRequestDTO customerRequestDto);

    CheckinRootDomainEntity createCheckin(CustomerDTO customer, HotelDTO hotel, boolean isFullCheckin);
    
//    CheckinDTO getResponseDTO(CheckinRootDomainEntity newCheckin);

    void deleteAllImagesAndAttachments(CheckinUseCaseDTO checkinDTO);

    CustomerDTO updateCheckin(CustomerDTO sessionCustomer);

    List<CustomerDTO> getActiveCustomersByHotelId(HotelDomainEntityId hotelId, LocalDate date);

    Integer getActiveCountByHotelId(HotelDomainEntityId receiverHotelId, LocalDate now);
}
