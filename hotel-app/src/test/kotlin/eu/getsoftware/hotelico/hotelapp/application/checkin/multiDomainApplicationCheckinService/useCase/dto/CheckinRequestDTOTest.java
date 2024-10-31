package eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainApplicationCheckinService.useCase.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * eu: test DTO validation on parameters!!!
 */
class CheckinRequestDTOTest {

    @Test
    public void givenCheckinRequestDTO_whenWrongDates_thenError(){
        
        assertThrows(IllegalArgumentException.class, () -> new CheckinRequestDTO("Jane", 2, 150));
    }
    

}