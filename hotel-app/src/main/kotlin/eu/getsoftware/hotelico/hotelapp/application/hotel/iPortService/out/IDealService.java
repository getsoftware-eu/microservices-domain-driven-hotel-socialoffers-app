package eu.getsoftware.hotelico.hotelapp.application.hotel.iPortService.out;

import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotelCustomer.model.HotelActivity;
import eu.getsoftware.hotelico.hotelapp.application.deal.infrastructure.dto.CustomerDealDTO;
import eu.getsoftware.hotelico.hotelapp.application.deal.infrastructure.utils.DealAction;
import eu.getsoftware.hotelico.hotelapp.application.hotel.infrastructure.dto.ResponseDTO;
import eu.getsoftware.hotelico.hotelapp.model.CustomerDeal;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IDealService {

    @Transactional
    List<CustomerDealDTO> getDealsByActivityOrHotelId(long customerId, long hotelId, long activityId, boolean onlyDealsOfRequester, boolean closed);

    @Transactional
    ResponseDTO deleteDeal(long customerId, long activityId, long dealId);

    @Transactional
    CustomerDealDTO addUpdateDeal(long guestCustomerId, long activityId, CustomerDealDTO activityDealDto);

    @Transactional
    CustomerDealDTO addDealAction(long guestCustomerId, long activityId, long givenId, DealAction action, String tablePosition, double totalMoney);

    @Transactional
    int getCustomerDealCounter(long customerId, long guestId);

    @Transactional
    Optional<HotelActivity> getActivityByIdOrInitId(long id, long initId);

    @Transactional
    Optional<CustomerDeal> getDealByIdOrInitId(long id, long initId);

}
