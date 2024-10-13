package eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainApplicationCheckinService.useCase.handler;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerRequestDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.model.ICustomerHotelCheckinEntity;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.out.CheckinPortService;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.IHotelRootEntity;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CreateHotelCheckinHandler {

    private CheckinPortService checkinService;

    @NotNull
    @Override
    @Transactional
    public ICustomerHotelCheckinEntity handle(CustomerRequestDTO customerRequestDTO, ICustomerRootEntity customerEntity, IHotelRootEntity hotelRootEntity, boolean isFullCheckin) {
        
        ICustomerHotelCheckinEntity customerHotelCheckin = checkinService.createCheckin(customerEntity, hotelRootEntity);
//        customerHotelCheckin.setCustomer(customerEntity);
//        customerHotelCheckin.setHotel(hotelRootEntity);

        CustomerDTO customerDto = new CustomerDto();

        customerHotelCheckin.setStaffCheckin(customerEntity.isHotelStaff());
        customerHotelCheckin.setFullCheckin(isFullCheckin);
        customerDto.setFullCheckin(isFullCheckin);

        customerHotelCheckin.setValidFrom(customerRequestDTO.getCheckinFrom());
        customerHotelCheckin.setValidTo(customerRequestDTO.getCheckinTo());
        checkinService.save(customerHotelCheckin);
        
        return customerHotelCheckin;
    }
}
