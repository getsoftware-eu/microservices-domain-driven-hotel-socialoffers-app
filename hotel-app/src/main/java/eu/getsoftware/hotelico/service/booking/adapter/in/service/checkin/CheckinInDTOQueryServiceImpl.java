package eu.getsoftware.hotelico.service.booking.adapter.in.service.checkin;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.gateways.CheckinGatewayService;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.gateways.CustomerGatewayService;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.mapper.HotelDtoMapperImpl;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.in.queryservice.CheckinInDTOQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckinInDTOQueryServiceImpl implements CheckinInDTOQueryService {

    private final CheckinGatewayService userGatewayService;
    private final CustomerGatewayService customerGatewayService;
    private final HotelDtoMapperImpl hotelDtoMapperImpl;
    
    @Override
    public CustomerDTO getStaffFirstByHotelId(HotelDomainEntityId hotelId)
    {
        return getStaffByHotelId(hotelId).stream().findFirst().orElseThrow(() -> new RuntimeException("staff not found for hotel " + hotelId));
    }
   
    @Override
    public List<CustomerDTO> getStaffByHotelId(HotelDomainEntityId hotelId) {
        return null;
    }

    @Override
    public Integer getActiveCountByHotelId(HotelDomainEntityId receiverHotelId, Date date) {
        return null;
    }
    

    @Override
    public List<CustomerDTO> getActiveCustomersByHotelId(HotelDomainEntityId hotelId, LocalDate date) {
        return List.of();
    }
  
    @Override
    public List<CustomerDTO> getActiveCustomersByHotelId(HotelDomainEntityId hotelId, Date date) {
        return null;
    }

}
