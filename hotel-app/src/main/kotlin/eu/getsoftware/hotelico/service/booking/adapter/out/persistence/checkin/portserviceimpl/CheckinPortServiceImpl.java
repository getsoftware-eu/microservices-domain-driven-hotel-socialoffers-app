package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.portserviceimpl;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseRequestDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.HotelDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.in.queryservice.CheckinQueryPortService;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.out.CheckinPortService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class CheckinPortServiceImpl implements CheckinPortService, CheckinQueryPortService {

    @Override
    public List<CheckinRootDomainEntity> getActiveByCustomerId(CustomerDomainEntityId id, Date date) {
        return null;
    }

    @Override
    public LocalDate getLastByCustomerAndHotelId(CustomerDomainEntityId id, HotelDomainEntityId id1) {
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
    public CheckinRootDomainEntity createCheckin(CheckinUseCaseRequestDTO customerRequestDto) {
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
    public void deleteAllImagesAndAttachments(CheckinUseCaseDTO checkinDTO) {
        
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
