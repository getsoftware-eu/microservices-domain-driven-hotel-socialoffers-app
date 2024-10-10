package eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainApplicationCheckinService.useCase.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class CheckinDTO {

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
