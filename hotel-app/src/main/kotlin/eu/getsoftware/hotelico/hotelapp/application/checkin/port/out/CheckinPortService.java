package eu.getsoftware.hotelico.hotelapp.application.checkin.port.out;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.model.ICustomerHotelCheckinEntity;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.dto.CheckinDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.dto.CheckinRequestDTO;

import java.util.Date;
import java.util.List;

/**
 * out port - обслуживает именно меня, можно entity использовать and not only dto usage
 */
public interface CheckinPortService {
    
    List<ICustomerHotelCheckinEntity> getActiveByCustomerId(long id, Date date);

    Date getLastByCustomerAndHotelId(long id, long id1);

    void save(ICustomerHotelCheckinEntity customerHotelCheckin);

    List<CustomerDTO> getStaffByHotelId(long hotelId);

    ICustomerHotelCheckinEntity createCheckin(CheckinRequestDTO customerRequestDto);

    ICustomerHotelCheckinEntity createCheckin(CustomerDTO customer, HotelDTO hotel, boolean isFullCheckin);

    Integer getActiveCountByHotelId(long receiverHotelId, Date date);

    CheckinDTO getResponseDTO(ICustomerHotelCheckinEntity newCheckin);

    void deleteAllImagesAndAttachments(CheckinDTO checkinDTO);

    CustomerDTO updateCheckin(CustomerDTO sessionCustomer);
}
