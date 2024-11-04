package eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class CheckinDTO {

    long initId;
    long customerId;
    
    @Setter
    long hotelId;
    
    @NotNull
    Date checkinFrom;
    Date checkinTo;

    public CheckinDTO(long customerId, long hotelId) {
        this.customerId = customerId;
        this.hotelId = hotelId;
    }

}
