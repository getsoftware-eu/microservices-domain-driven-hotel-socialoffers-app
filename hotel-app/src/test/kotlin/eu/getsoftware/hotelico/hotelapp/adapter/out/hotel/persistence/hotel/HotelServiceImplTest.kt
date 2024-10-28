package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelRootEntity
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.outPortServiceImpl.HotelServiceImpl
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository.HotelRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

class HotelServiceImplTest {
	
	val hotelRepository: HotelRepository = mockk();
	
	val iHotelService = HotelServiceImpl(hotelRepository);
	
	val sampleHotelResponseDTO =
		HotelDTO(123);
	
	val sampleHotelEntity =
        HotelRootEntity();
	
	@Test
	fun whenGetHotel_thenReturnHotel() {
		//given
		every { hotelRepository.findByIdOrNull(1) } returns sampleHotelEntity;
		
		//when
		val result = iHotelService.getHotelById(123);
		
		//then
		verify(exactly = 1) { hotelRepository.findByIdOrNull(1) };
		Assertions.assertEquals(sampleHotelEntity, result)
	}
}