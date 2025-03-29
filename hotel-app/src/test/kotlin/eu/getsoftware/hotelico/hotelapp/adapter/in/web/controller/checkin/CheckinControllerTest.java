package eu.getsoftware.hotelico.hotelapp.adapter.in.web.controller.checkin;

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CheckinDTO;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CheckinRequestDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.in.usecase.CheckinUseCase;
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
        
        CheckinDTO mockResponse = CheckinDTO.builder()
                .customerId(1)
                .hotelId(2)
                .checkinFrom(fromDate)
                .checkinTo(toDate)
                .build();

        when(checkinUseCase.createCustomerCheckin(any(CheckinRequestDTO.class)))
                .thenReturn(mockResponse);

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Eugen\", \"email\":\"eugen@example.com\", \"password\":\"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Eugen"))
                .andExpect(jsonPath("$.email").value("eugen@example.com"));
    }
}