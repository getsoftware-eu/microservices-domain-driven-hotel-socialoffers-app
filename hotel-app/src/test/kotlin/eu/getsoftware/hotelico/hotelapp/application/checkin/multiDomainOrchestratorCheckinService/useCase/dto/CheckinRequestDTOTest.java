package eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.dto;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * eu: test DTO validation on parameters!!!
 */
class CheckinRequestDTOTest {

    @Test
    public void givenCheckinRequestDTO_whenWrongDates_thenError(){
        
        assertThrows(IllegalArgumentException.class, () -> new CheckinRequestDTO(new CustomerDomainEntityId("123"), new HotelDomainEntityId("2"), new Date(), new Date(), 123, "Eugen"));
    }
    

}