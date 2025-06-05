package eu.getsoftware.hotelico.service.booking.application.checkin.multiDomainOrchestratorCheckinService.useCase.handler;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseRequestDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.HotelDTO;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.in.usecase.CheckinUseCase;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.out.CheckinOutWriteService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CreateHotelCheckinHandler {

    private CheckinUseCase checkinUseCase;
    private CheckinOutWriteService checkinWriteService;

    @NotNull
    @Transactional
    public CheckinRootDomainEntity handle(CheckinUseCaseRequestDTO checkinRequestDTO, CustomerDTO customerEntity, HotelDTO hotelRootEntity, boolean isFullCheckin) {
        
        CheckinRootDomainEntity CheckinRootDomainEntity = checkinUseCase.createCheckin(customerEntity, hotelRootEntity, true);
//        CheckinRootDomainEntity.setCustomer(customerEntity);
//        CheckinRootDomainEntity.setHotel(hotelRootEntity);


        CheckinRootDomainEntity.setStaffCheckin(customerEntity.isHotelStaff());
        CheckinRootDomainEntity.setFullCheckin(isFullCheckin);

        CustomerDTO.builder().build();


        CheckinRootDomainEntity.setLvalidFrom(checkinRequestDTO.checkinFrom());
        CheckinRootDomainEntity.setLvalidTo(checkinRequestDTO.checkinTo());
        checkinWriteService.save(CheckinRootDomainEntity);
        
        return CheckinRootDomainEntity;
    }
}
