package eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.handler;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.model.ICustomerHotelCheckinEntity;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.dto.CheckinRequestDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.out.CheckinPortService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CreateHotelCheckinHandler {

    private CheckinPortService checkinService;

    @NotNull
    @Transactional
    public ICustomerHotelCheckinEntity handle(CheckinRequestDTO checkinRequestDTO, CustomerDTO customerEntity, HotelDTO hotelRootEntity, boolean isFullCheckin) {
        
        ICustomerHotelCheckinEntity customerHotelCheckin = checkinService.createCheckin(customerEntity, hotelRootEntity, true);
//        customerHotelCheckin.setCustomer(customerEntity);
//        customerHotelCheckin.setHotel(hotelRootEntity);


        customerHotelCheckin.setStaffCheckin(customerEntity.isHotelStaff());
        customerHotelCheckin.setFullCheckin(isFullCheckin);

        CustomerDTO.builder().build();


        customerHotelCheckin.setValidFrom(checkinRequestDTO.checkinFrom());
        customerHotelCheckin.setValidTo(checkinRequestDTO.checkinTo());
        checkinService.save(customerHotelCheckin);
        
        return customerHotelCheckin;
    }
}
