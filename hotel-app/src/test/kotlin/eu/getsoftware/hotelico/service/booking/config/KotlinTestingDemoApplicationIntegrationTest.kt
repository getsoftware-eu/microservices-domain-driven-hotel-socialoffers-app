//package eu.getsoftware.hotelico.hotelapp.main;
//
//import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.Disabled
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.web.client.TestRestTemplate
//import org.springframework.http.HttpStatus
//
//@Disabled
//@SpringBootTest(
//		classes = [MainApplication::class],
//		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class KotlinTestingDemoApplicationIntegrationTest {
//	
//	@Autowired
//	lateinit var restTemplate: TestRestTemplate
//	
//	@Test
//	fun whePostCalled_thenShouldReturnBankObject() {
//		// when:
//		val result = restTemplate.postForEntity("/api/v1/hotels",
//			HotelDTO.builder().build(), HotelDTO::class.java);
//
//		// then:
//		Assertions.assertNotNull(result)
//		Assertions.assertEquals(HttpStatus.OK, result?.statusCode)
//		Assertions.assertEquals("ING", result.getBody()?.currentHotelAccessCode)
//	}
//	
//	@Test
//	fun whenGetCalled_thenShouldBadReqeust() {
//		// when:
//		val result = restTemplate.getForEntity("/api/v1/hotels?id=2", HotelDTO::class.java);
//		
//		// then:
//		Assertions.assertNotNull(result)
//		Assertions.assertEquals(HttpStatus.BAD_REQUEST, result?.statusCode)
//	}
//	
//}