package eu.getsoftware.hotelico.service.booking.application.checkin.port.in.queryservice;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *  * Команды (Commands) идут через UseCase.
 * CCA: Only if I want to allow these getters (as a port service) without useCases
 * TODO Use here not Entity, but as response only Dto or limited ReadOnly Record-Queries from DB
 */

public interface CheckinInDTOQueryService {
    
    List<CustomerDTO> getStaffByHotelId(HotelDomainEntityId hotelId);

    List<CustomerDTO> getActiveCustomersByHotelId(HotelDomainEntityId hotelId, LocalDate date);

    List<CustomerDTO> getActiveCustomersByHotelId(HotelDomainEntityId hotelId, Date date);

    Integer getActiveCountByHotelId(HotelDomainEntityId receiverHotelId, Date date);

    //eu: here domainId, no just id!!!
    CustomerDTO getStaffFirstByHotelId(HotelDomainEntityId hotelId);
}
