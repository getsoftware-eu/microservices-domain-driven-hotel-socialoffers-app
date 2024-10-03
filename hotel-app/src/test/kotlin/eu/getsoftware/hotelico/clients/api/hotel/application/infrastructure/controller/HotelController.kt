package eu.getsoftware.hotelico.clients.api.hotel.application.infrastructure.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.dto.HotelResponseDTO
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IHotelService
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class HotelController(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var IHotelService: IHotelService
    val mapper = jacksonObjectMapper()

    val sampleHotel =
        HotelResponseDTO(123)

    @Test
    fun givenExistingBankAccount_whenGetRequest_thenReturnsBankAccountJsonWithStatus200() {
        every { IHotelService.getHotelById(1) } returns sampleHotel;

        mockMvc.perform(get("/api/v1/hotels?id=1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.bankCode").value("ING"));
    }

    @Test
    fun givenBankAccountDoesntExist_whenGetRequest_thenReturnsStatus400() {
        every { IHotelService.getHotelById(2) } returns null;

        mockMvc.perform(get("/api/v1/hotels?id=2"))
            .andExpect(status().isBadRequest());
    }

    @Test
    fun whenPostRequestWithBankAccountJson_thenReturnsStatus200() {
        every { IHotelService.addHotel(sampleHotel) } returns sampleHotel;

        mockMvc.perform(post("/api/v1/hotels").content(mapper.writeValueAsString(sampleHotel)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.bankCode").value("ING"));
    }
}