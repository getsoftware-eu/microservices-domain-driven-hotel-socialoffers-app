package eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.common.utils.DealAction;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelDBActivity;
import eu.getsoftware.hotelico.service.booking.adapter.out.viewEntity.model.CustomerDeal;
import eu.getsoftware.hotelico.service.booking.application.deal.domain.infrastructure.dto.CustomerDealDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IDealService {

    @Transactional
    List<CustomerDealDTO> getDealsByActivityOrHotelId(long customerId, long hotelId, long activityId, boolean onlyDealsOfRequester, boolean closed);

//    @Transactional
//    ResponseDTO deleteDeal(long customerId, long activityId, long dealId);

    @Transactional
    CustomerDealDTO addUpdateDeal(long guestCustomerId, long activityId, CustomerDealDTO activityDealDto);

    @Transactional
    CustomerDealDTO addDealAction(long guestCustomerId, long activityId, long givenId, DealAction action, String tablePosition, double totalMoney);

    @Transactional
    int getCustomerDealCounter(long customerId, long guestId);

    @Transactional
    Optional<HotelDBActivity> getActivityByIdOrInitId(long id, long initId);

    @Transactional
    Optional<CustomerDeal> getDealByIdOrInitId(long id, long initId);

}
