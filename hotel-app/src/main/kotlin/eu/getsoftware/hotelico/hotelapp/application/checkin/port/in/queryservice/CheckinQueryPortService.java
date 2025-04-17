package eu.getsoftware.hotelico.hotelapp.application.checkin.port.in.queryservice;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *  * Команды (Commands) идут через UseCase.
 * CCA: Only if I want to allow these getters (as a port service) without useCases
 * TODO Use here not Entity, but as response only Dto or limited ReadOnly Record-Queries from DB
 */

public interface CheckinQueryPortService {
    
    List<CustomerDTO> getStaffByHotelId(HotelDomainEntityId hotelId);

    List<CustomerDTO> getActiveCustomersByHotelId(HotelDomainEntityId hotelId, Date date);

    LocalDate getLastByCustomerAndHotelId(CustomerDomainEntityId id, HotelDomainEntityId id1);

    Integer getActiveCountByHotelId(HotelDomainEntityId receiverHotelId, Date date);
}
