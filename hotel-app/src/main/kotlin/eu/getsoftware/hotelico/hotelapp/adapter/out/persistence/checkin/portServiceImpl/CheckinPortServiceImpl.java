package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.portServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CheckinDTO;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CheckinRequestDTO;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.HotelDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.CheckinRootDomainEntity;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.out.CheckinPortService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CheckinPortServiceImpl implements CheckinPortService {

    @Override
    public List<CheckinRootDomainEntity> getActiveByCustomerId(CustomerDomainEntityId id, Date date) {
        return null;
    }

    @Override
    public Date getLastByCustomerAndHotelId(CustomerDomainEntityId id, HotelDomainEntityId id1) {
        return null;
    }

    @Override
    public void save(CheckinRootDomainEntity CheckinRootDomainEntity) {

    }

    @Override
    public List<CustomerDTO> getStaffByHotelId(HotelDomainEntityId hotelId) {
        return null;
    }

    @Override
    public CheckinRootDomainEntity createCheckin(CheckinRequestDTO customerRequestDto) {
        return null;
    }

    @Override
    public CheckinRootDomainEntity createCheckin(CustomerDTO customer, HotelDTO hotel, boolean isFullCheckin) {
        return null;
    }

    @Override
    public Integer getActiveCountByHotelId(HotelDomainEntityId receiverHotelId, Date date) {
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
