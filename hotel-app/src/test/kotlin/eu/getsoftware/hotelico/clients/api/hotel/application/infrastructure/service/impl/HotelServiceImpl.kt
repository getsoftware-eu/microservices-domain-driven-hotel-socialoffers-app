package eu.getsoftware.hotelico.clients.api.hotel.application.infrastructure.service.impl;

import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.HotelRootEntity
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.repository.HotelRepository
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.dto.HotelDTO
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

class HotelServiceImpl {
	
	val hotelRepository: HotelRepository = mockk();
	val IHotelService =
		eu.getsoftware.hotelico.hotelapp.application.hotel.iPortService.IHotelService(hotelRepository);
	
	val sampleHotelDTO = HotelDTO(123);
	val sampleHotelEntity =
        HotelRootEntity();
	
	@Test
	fun whenGetHotel_thenReturnHotel() {
		//given
		every { hotelRepository.findByIdOrNull(1) } returns sampleHotelEntity;
		
		//when
		val result = IHotelService.getBankAccount(1);
		
		//then
		verify(exactly = 1) { hotelRepository.findByIdOrNull(1) };
		assertEquals(sampleHotelEntity, result)
	}
}