package hotelico.service.booking.adapter.out.persistence.hotel.repository;

import hotelico.service.booking.adapter.out.persistence.customer.model.Language
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LanguageRepository: JpaRepository<Language, Long> {

	/**
	 * Find language by name.
	 */
	fun findByDescriptionShort(descriptionShort: String): Language
}
