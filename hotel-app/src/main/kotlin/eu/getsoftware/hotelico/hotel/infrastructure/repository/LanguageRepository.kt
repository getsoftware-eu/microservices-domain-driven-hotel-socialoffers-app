package eu.getsoftware.hotelico.hotel.infrastructure.repository;

import eu.getsoftware.hotelico.customer.domain.Language
import org.springframework.data.jpa.repository.JpaRepository

interface LanguageRepository: JpaRepository<Language, Long> {

	/**
	 * Find language by name.
	 */
	fun findByDescriptionShort(descriptionShort: String): Language
}
