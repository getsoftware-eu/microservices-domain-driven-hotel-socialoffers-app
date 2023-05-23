package eu.getsoftware.hotelico.hotel.infrastructure.service.impl;

import eu.getsoftware.hotelico.hotel.domain.HotelRootEntity
import eu.getsoftware.hotelico.hotel.infrastructure.dto.HotelDTO
import eu.getsoftware.hotelico.hotel.infrastructure.repository.HotelRepository
import eu.getsoftware.hotelico.hotel.infrastructure.service.HotelService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

class HotelServiceImpl {
	
	val hotelRepository: HotelRepository = mockk();
	val hotelService = HotelService(hotelRepository);
	
	val sampleHotelDTO = HotelDTO(123);
	val sampleHotelEntity = HotelRootEntity();
	
	@Test
	fun whenGetHotel_thenReturnHotel() {
		//given
		every { hotelRepository.findByIdOrNull(1) } returns sampleHotelEntity;
		
		//when
		val result = hotelService.getBankAccount(1);
		
		//then
		verify(exactly = 1) { hotelRepository.findByIdOrNull(1) };
		assertEquals(sampleHotelEntity, result)
	}
}