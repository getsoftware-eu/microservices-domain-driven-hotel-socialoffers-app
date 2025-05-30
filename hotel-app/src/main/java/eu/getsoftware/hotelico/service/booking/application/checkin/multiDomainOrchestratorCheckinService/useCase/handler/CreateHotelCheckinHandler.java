package eu.getsoftware.hotelico.service.booking.application.checkin.multiDomainOrchestratorCheckinService.useCase.handler;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseRequestDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.HotelDTO;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.out.CheckinPortService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CreateHotelCheckinHandler {

    private CheckinPortService checkinService;

    @NotNull
    @Transactional
    public CheckinRootDomainEntity handle(CheckinUseCaseRequestDTO checkinRequestDTO, CustomerDTO customerEntity, HotelDTO hotelRootEntity, boolean isFullCheckin) {
        
        CheckinRootDomainEntity CheckinRootDomainEntity = checkinService.createCheckin(customerEntity, hotelRootEntity, true);
//        CheckinRootDomainEntity.setCustomer(customerEntity);
//        CheckinRootDomainEntity.setHotel(hotelRootEntity);


        CheckinRootDomainEntity.setStaffCheckin(customerEntity.isHotelStaff());
        CheckinRootDomainEntity.setFullCheckin(isFullCheckin);

        CustomerDTO.builder().build();


        CheckinRootDomainEntity.setValidFrom(checkinRequestDTO.checkinFrom());
        CheckinRootDomainEntity.setValidTo(checkinRequestDTO.checkinTo());
        checkinService.save(CheckinRootDomainEntity);
        
        return CheckinRootDomainEntity;
    }
}
