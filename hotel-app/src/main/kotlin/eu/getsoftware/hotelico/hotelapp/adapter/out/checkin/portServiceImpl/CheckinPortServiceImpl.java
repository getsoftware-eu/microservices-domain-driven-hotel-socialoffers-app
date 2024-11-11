package eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.portServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.model.ICustomerHotelCheckinEntity;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.dto.CheckinDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.dto.CheckinRequestDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.out.CheckinPortService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CheckinPortServiceImpl implements CheckinPortService {
    
    @Override
    public List<ICustomerHotelCheckinEntity> getActiveByCustomerId(long id, Date date) {
        return null;
    }

    @Override
    public Date getLastByCustomerAndHotelId(CustomerDomainEntityId id, HotelDomainEntityId id1) {
        return null;
    }

    @Override
    public void save(ICustomerHotelCheckinEntity customerHotelCheckin) {

    }

    @Override
    public List<CustomerDTO> getStaffByHotelId(HotelDomainEntityId hotelId) {
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
    public Integer getActiveCountByHotelId(HotelDomainEntityId receiverHotelId, Date date) {
        return null;
    }


    @Override
    public CheckinDTO getResponseDTO(ICustomerHotelCheckinEntity newCheckin) {
        return null;
    }

    @Override
    public void deleteAllImagesAndAttachments(CheckinDTO checkinDTO) {
        
    }

    @Override
    public CustomerDTO updateCheckin(CustomerDTO sessionCustomer) {
        return null;
    }

    @Override
    public CustomerDBEntity getStaffbyHotelId(HotelDomainEntityId hotelId) {
        return null;
    }

    @Override
    public List<CustomerDTO> getActiveCustomersByHotelId(HotelDomainEntityId hotelId, Date date) {
        return null;
    }

}
