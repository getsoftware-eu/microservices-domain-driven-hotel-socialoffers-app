package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.checkin.portServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.model.ICustomerHotelCheckinEntity;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainApplicationCheckinService.useCase.dto.CheckinDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainApplicationCheckinService.useCase.dto.CheckinRequestDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.out.CheckinPortService;

import java.util.Date;
import java.util.List;

public class CheckinPortServiceImpl implements CheckinPortService {
    
    @Override
    public List<ICustomerHotelCheckinEntity> getActiveByCustomerId(long id, Date date) {
        return null;
    }

    @Override
    public Date getLastByCustomerAndHotelId(long id, long id1) {
        return null;
    }

    @Override
    public void save(ICustomerHotelCheckinEntity customerHotelCheckin) {

    }

    @Override
    public List<CustomerDTO> getStaffByHotelId(long hotelId) {
        return null;
    }

    @Override
    public ICustomerHotelCheckinEntity createCheckin(CheckinRequestDTO customerRequestDto) {
        return null;
    }

    @Override
    public ICustomerHotelCheckinEntity createCheckin(CustomerDTO customer, HotelDTO hotel, boolean isFullCheckin) {
        return null;
    }

    @Override
    public Integer getActiveCountByHotelId(long receiverHotelId, Date date) {
        return null;
    }

    @Override
    public CheckinDTO getResponseDTO(ICustomerHotelCheckinEntity newCheckin) {
        return null;
    }
}
