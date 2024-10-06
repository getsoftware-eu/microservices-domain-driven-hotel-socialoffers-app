package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerHotelCheckin;
import eu.getsoftware.hotelico.hotelapp.application.multiDomainApplicationCheckinService.useCase.dto.CheckinDTO;
import eu.getsoftware.hotelico.hotelapp.application.multiDomainApplicationCheckinService.useCase.dto.CheckinRequestDTO;

import java.util.Date;
import java.util.List;

public interface ICheckinService {
    
    List<ICustomerHotelCheckin> getActiveByCustomerId(long id, Date date);

    Date getLastByCustomerAndHotelId(long id, long id1);

    ICustomerHotelCheckin save(ICustomerHotelCheckin customerHotelCheckin);

    List<CustomerDTO> getStaffByHotelId(long hotelId);

    ICustomerHotelCheckin createCheckin(CheckinRequestDTO customerRequestDto);

    ICustomerHotelCheckin createCheckin(CustomerDTO customer, HotelDTO hotel);

    Integer getActiveCountByHotelId(long receiverHotelId, Date date);

    CheckinDTO getResponseDTO(ICustomerHotelCheckin newCheckin);
}
