package eu.getsoftware.hotelico.hotel.infrastructure.repository

import eu.getsoftware.hotelico.hotel.infrastructure.dto.HotelDTO
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
    fun WhenFindById_thenReturnBankAccount() {
        val hotelDto = HotelDTO(123);
        entityManager.persist(hotelDto)
        entityManager.flush()
        val ingBankAccountFound = hotelRepository.findByIdOrNull(hotelDto.id!!)
        assertThat(ingBankAccountFound == hotelDto)
    }

}