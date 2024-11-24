package eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.handler;

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CheckinRequestDTO;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.HotelDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.CheckinRootDomainEntity;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.out.CheckinPortService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CreateHotelCheckinHandler {

    private CheckinPortService checkinService;

    @NotNull
    @Transactional
    public CheckinRootDomainEntity handle(CheckinRequestDTO checkinRequestDTO, CustomerDTO customerEntity, HotelDTO hotelRootEntity, boolean isFullCheckin) {
        
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
