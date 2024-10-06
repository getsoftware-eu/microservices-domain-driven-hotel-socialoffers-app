package eu.getsoftware.hotelico.hotelapp.application.multiDomainApplicationCheckinService.useCase.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class CheckinDTO {

    long customerId;
    
    @Setter
    long hotelId;
    Date checkinFrom;
    Date checkinTo;

    public CheckinDTO(long customerId, long hotelId) {
        this.customerId = customerId;
        this.hotelId = hotelId;
    }

}
