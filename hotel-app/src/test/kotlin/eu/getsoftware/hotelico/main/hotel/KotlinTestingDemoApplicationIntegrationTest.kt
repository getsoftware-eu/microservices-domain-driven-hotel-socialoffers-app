package eu.getsoftware.hotelico.main.hotel

import eu.getsoftware.hotelico.hotel.infrastructure.dto.HotelDTO
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(
		classes = arrayOf(MainApplication::class),
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KotlinTestingDemoApplicationIntegrationTest {
	
	@Autowired
	lateinit var restTemplate: TestRestTemplate
	
	@Test
	fun whePostCalled_thenShouldReturnBankObject() {
		val result = restTemplate.postForEntity("/api/v1/hotels", HotelDTO(123), HotelDTO::class.java);
		
		assertNotNull(result)
		assertEquals(HttpStatus.OK, result?.statusCode)
		assertEquals("ING", result.getBody()?.currentHotelAccessCode)
	}
	
	@Test
	fun whenGetCalled_thenShouldBadReqeust() {
		val result = restTemplate.getForEntity("/api/v1/hotels?id=2", HotelDTO::class.java);
		
		assertNotNull(result)
		assertEquals(HttpStatus.BAD_REQUEST, result?.statusCode)
	}
	
}