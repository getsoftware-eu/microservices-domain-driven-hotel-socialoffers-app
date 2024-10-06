package eu.getsoftware.hotelico.hotelapp.application.multiDomainApplicationCheckinService.useCase.handler;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerRequestDTO;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerHotelCheckin;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.IHotelRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.ICheckinService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CreateHotelCheckinHandler {

    private ICheckinService checkinService;

    @NotNull
    @Override
    @Transactional
    public ICustomerHotelCheckin handle(CustomerRequestDTO customerRequestDTO, ICustomerRootEntity customerEntity, IHotelRootEntity hotelRootEntity, boolean isFullCheckin) {
        
        ICustomerHotelCheckin customerHotelCheckin = checkinService.createCheckin(customerEntity, hotelRootEntity);
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
