package eu.getsoftware.hotelico.infrastructure.hotel.repository;

import eu.getsoftware.hotelico.domain.customer.Language
import org.springframework.data.jpa.repository.JpaRepository

interface LanguageRepository: JpaRepository<Language, Long> {

	/**
	 * Find language by name.
	 */
	fun findByDescriptionShort(descriptionShort: String): Language
}
