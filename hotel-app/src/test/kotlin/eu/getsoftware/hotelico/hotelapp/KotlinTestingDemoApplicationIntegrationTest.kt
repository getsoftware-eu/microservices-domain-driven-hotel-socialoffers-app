package eu.getsoftware.hotelico.hotelapp;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO
import eu.getsoftware.hotelico.hotelapp.main.MainApplication
import org.junit.jupiter.api.Assertions
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
		// when:
		val result = restTemplate.postForEntity("/api/v1/hotels",
			HotelDTO(123), HotelDTO::class.java);

		// then:
		Assertions.assertNotNull(result)
		Assertions.assertEquals(HttpStatus.OK, result?.statusCode)
		Assertions.assertEquals("ING", result.getBody()?.currentHotelAccessCode)
	}
	
	@Test
	fun whenGetCalled_thenShouldBadReqeust() {
		// when:
		val result = restTemplate.getForEntity("/api/v1/hotels?id=2", HotelDTO::class.java);
		
		// then:
		Assertions.assertNotNull(result)
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, result?.statusCode)
	}
	
}