package eu.getsoftware.hotelico.service.booking.application.checkin.multiDomainOrchestratorCheckinService.useCase.outqueryservice;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseRequestDTO;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.out.CheckinOutEntityQueryService;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.customDomainModelImpl.HotelRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService.IHotelService;
import eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService.LastMessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckinOutEntityQueryServiceImpl implements CheckinOutEntityQueryService {

    private final LastMessagesService lastMessagesService;
    private final IHotelService hotelService;
    
    @Override
    public List<CheckinRootDomainEntity> getActiveByCustomerId(CustomerDomainEntityId id, Date date) {
        return null;
    }

    @Override
    public LocalDate getLastByCustomerAndHotelId(CustomerDomainEntityId id, HotelDomainEntityId id1) {
        return null;
    }


    public Optional<HotelRootDomainEntity> getHotelEntityFromCheckinRequest(CheckinUseCaseRequestDTO checkinRequestDto) {

        HotelDomainEntityId virtualHotelId = lastMessagesService.getInitHotelId();

        String virtualHotelCode = AppConfigProperties.ALLOW_INIT_VIRTUAL_HOTEL ? hotelService.getVirtualHotelCode() : null;

        Optional<HotelRootDomainEntity> hotelEntityOpt = Optional.empty();

//		if(checkinRequestDto.getHotelCode()!=null && !(checkinRequestDto.getHotelCode().equals(virtualHotelCode) ))
//		{
//			hotelEntityOpt = hotelService.findByCurrentHotelAccessCodeAndActive(checkinRequestDto.getHotelCode(), true);
//		}

//		if(hotelEntityOpt.isEmpty() && checkinRequestDto.getHotelId()>0 && (!AppConfigProperties.ALLOW_INIT_VIRTUAL_HOTEL || checkinRequestDto.getHotelId() != virtualHotelId))
//		{
//			hotelEntityOpt = hotelService.getOne(checkinRequestDto.getHotelId());
//		}

        return hotelEntityOpt;
    }

    @Override
    public CustomerDBEntity getStaffbyHotelId(HotelDomainEntityId hotelId) {
        return null;
    }

    @Override
    public Integer getActiveCountByHotelId(HotelDomainEntityId receiverHotelId, LocalDate now) {
        return 0;
    }
    
}
