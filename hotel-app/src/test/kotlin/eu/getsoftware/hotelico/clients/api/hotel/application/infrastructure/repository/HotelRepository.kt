package eu.getsoftware.hotelico.clients.api.hotel.application.infrastructure.repository

import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.repository.HotelRepository
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.dto.HotelResponseDTO
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
        val hotelResponseDto =
            HotelResponseDTO(123);
        entityManager.persist(hotelResponseDto)
        entityManager.flush()
        val ingBankAccountFound = hotelRepository.findByIdOrNull(hotelResponseDto.id!!)
        assertThat(ingBankAccountFound == hotelResponseDto)
    }

}