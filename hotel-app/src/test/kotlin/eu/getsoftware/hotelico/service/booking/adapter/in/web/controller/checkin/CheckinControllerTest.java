package eu.getsoftware.hotelico.service.booking.adapter.in.web.controller.checkin;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseRequestDTO;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.in.usecase.CheckinUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CheckinController.class)
class CheckinControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckinUseCase checkinUseCase;

    @Test
    void shouldCheckinUser() throws Exception {

        Date fromDate = new Date();
        Date toDate = new Date();
        toDate.setMonth(12);
        
        CheckinUseCaseDTO mockResponse = CheckinUseCaseDTO.builder()
                .customerId(1)
                .hotelId(2)
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