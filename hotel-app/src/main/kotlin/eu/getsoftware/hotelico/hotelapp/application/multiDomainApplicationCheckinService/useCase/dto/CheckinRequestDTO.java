package eu.getsoftware.hotelico.hotelapp.application.multiDomainApplicationCheckinService.useCase.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class CheckinRequestDTO {
        long customerId;
        long hotelId;
        Date checkinFrom;
        Date checkinTo;
}
