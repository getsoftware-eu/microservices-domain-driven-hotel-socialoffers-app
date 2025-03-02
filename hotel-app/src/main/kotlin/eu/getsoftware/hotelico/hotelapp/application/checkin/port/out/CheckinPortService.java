package eu.getsoftware.hotelico.hotelapp.application.checkin.port.out;

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CheckinDTO;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CheckinRequestDTO;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.HotelDTO;
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

    // Queries
    CustomerDBEntity getStaffbyHotelId(HotelDomainEntityId hotelId);

    List<CheckinRootDomainEntity> getActiveByCustomerId(CustomerDomainEntityId id, Date date);

    // Commands
    
    void save(CheckinRootDomainEntity CheckinRootDomainEntity);
    
    CheckinRootDomainEntity createCheckin(CheckinRequestDTO customerRequestDto);

    CheckinRootDomainEntity createCheckin(CustomerDTO customer, HotelDTO hotel, boolean isFullCheckin);
    
//    CheckinDTO getResponseDTO(CheckinRootDomainEntity newCheckin);

    void deleteAllImagesAndAttachments(CheckinDTO checkinDTO);

    CustomerDTO updateCheckin(CustomerDTO sessionCustomer);

    }
