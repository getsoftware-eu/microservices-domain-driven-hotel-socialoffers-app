package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.jpaTests

import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository.HotelRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class BankAccountRepositoryUnitTest {

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var hotelRepository: HotelRepository

    @Test
    fun WhenFindById_thenReturnEntity() {
        val hotelResponseDto =
            HotelDTO(123);
        
        // when:
        entityManager.persist(hotelResponseDto)
        entityManager.flush()
        
        // then:
        val ingBankAccountFound = hotelRepository.findByIdOrNull(hotelResponseDto.id!!)
        assertThat(ingBankAccountFound == hotelResponseDto)
    }

}