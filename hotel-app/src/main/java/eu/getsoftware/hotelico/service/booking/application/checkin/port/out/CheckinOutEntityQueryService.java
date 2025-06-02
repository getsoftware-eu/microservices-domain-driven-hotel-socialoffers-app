package eu.getsoftware.hotelico.service.booking.application.checkin.port.out;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseRequestDTO;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.customDomainModelImpl.HotelRootDomainEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * out port queries - обслуживает именно меня, можно entity использовать and not only dto usage
 */
public interface CheckinOutEntityQueryService {

    Optional<HotelRootDomainEntity> getHotelEntityFromCheckinRequest(CheckinUseCaseRequestDTO checkinRequestDto);

    CustomerDBEntity getStaffbyHotelId(HotelDomainEntityId hotelId);

    List<CheckinRootDomainEntity> getActiveByCustomerId(CustomerDomainEntityId id, Date date);
    
    Integer getActiveCountByHotelId(HotelDomainEntityId receiverHotelId, LocalDate now);

    LocalDate getLastByCustomerAndHotelId(CustomerDomainEntityId domainEntityId, HotelDomainEntityId domainEntityId1);

}
