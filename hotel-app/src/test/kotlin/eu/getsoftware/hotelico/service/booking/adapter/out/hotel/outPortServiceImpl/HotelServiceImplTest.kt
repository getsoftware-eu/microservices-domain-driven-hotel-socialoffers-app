//package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.outPortServiceImpl;
//
//import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO
//import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId
//import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelDbEntity
//import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository.HotelRepository
//import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.customDomainModelImpl.HotelDomainEntity
//import io.mockk.every
//import io.mockk.mockk
//import io.mockk.verify
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.Disabled
//import org.junit.jupiter.api.Test
//import org.springframework.data.repository.findByIdOrNull
//
//@Disabled
//class HotelServiceImplTest {
//	
//	val hotelRepository: HotelRepository = mockk();
//	
//	val hotelService = HotelServiceImpl(hotelRepository);
//	
//	val sampleHotelResponseDTO = HotelDTO.builder().initId(123).build();
//	
//	val sampleHotelEntity = HotelDomainEntity.builder().domainEntityId(HotelDomainEntityId("1")).build() as HotelDbEntity ;
//	
//	@Test
//	fun whenGetHotel_thenReturnHotel() {
//		//given
//		every { hotelRepository.findByIdOrNull(1) } returns sampleHotelEntity;
//		
//		//when
//		val result = hotelService.getHotelById(HotelDomainEntityId("123"));
//		
//		//then
//		verify(exactly = 1) { hotelRepository.findByIdOrNull(1) };
//		Assertions.assertEquals(sampleHotelEntity, result)
//	}
//}