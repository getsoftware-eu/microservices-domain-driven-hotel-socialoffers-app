package hotelico.service.booking.adapter.in.web.controller.checkin;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseRequestDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.in.web.controller.checkin.CheckinController;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.in.usecase.CheckinUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {
        CheckinController.class,
        CheckinControllerTest.TestConfig.class
})
@AutoConfigureMockMvc
class CheckinControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // Автоматически подключается

    @Autowired
    private CheckinUseCase checkinUseCase;
    
    @TestConfiguration
    static class TestConfig {
        @Bean
        public CheckinUseCase checkinUseCase() {
            return Mockito.mock(CheckinUseCase.class);
        }
    }
    
    @Test
    void shouldCheckinUser() throws Throwable {

        Date fromDate = new Date();
        Date toDate = new Date();
        toDate.setMonth(12);
        
        CheckinUseCaseDTO mockResponse = CheckinUseCaseDTO.builder()
                .customerId(CustomerDomainEntityId.from(1))
                .hotelId(HotelDomainEntityId.from(2))
                .checkinFrom(fromDate)
                .checkinTo(toDate)
                .build();

        when(checkinUseCase.createCustomerCheckin(any(CheckinUseCaseRequestDTO.class)))
                .thenReturn(mockResponse);

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Eugen\", \"email\":\"eugen@example.com\", \"password\":\"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Eugen"))
                .andExpect(jsonPath("$.email").value("eugen@example.com"));
    }
}