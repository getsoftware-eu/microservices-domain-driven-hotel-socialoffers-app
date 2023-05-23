package eu.getsoftware.hotelico.hotel.infrastructure.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import eu.getsoftware.hotelico.hotel.infrastructure.dto.HotelDTO
import eu.getsoftware.hotelico.hotel.infrastructure.service.HotelService
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
    lateinit var hotelService: HotelService
    val mapper = jacksonObjectMapper()

    val sampleHotel = HotelDTO(123)

    @Test
    fun givenExistingBankAccount_whenGetRequest_thenReturnsBankAccountJsonWithStatus200() {
        every { hotelService.getHotelById(1) } returns sampleHotel;

        mockMvc.perform(get("/api/v1/hotels?id=1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.bankCode").value("ING"));
    }

    @Test
    fun givenBankAccountDoesntExist_whenGetRequest_thenReturnsStatus400() {
        every { hotelService.getHotelById(2) } returns null;

        mockMvc.perform(get("/api/v1/hotels?id=2"))
            .andExpect(status().isBadRequest());
    }

    @Test
    fun whenPostRequestWithBankAccountJson_thenReturnsStatus200() {
        every { hotelService.addHotel(sampleHotel) } returns sampleHotel;

        mockMvc.perform(post("/api/v1/hotels").content(mapper.writeValueAsString(sampleHotel)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.bankCode").value("ING"));
    }
}