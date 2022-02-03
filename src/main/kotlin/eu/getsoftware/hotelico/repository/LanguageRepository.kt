package eu.getsoftware.hotelico.repository;

import eu.getsoftware.hotelico.model.Language
import org.springframework.data.jpa.repository.JpaRepository

interface LanguageRepository: JpaRepository<Language, Long> {

	/**
	 * Find language by name.
	 */
	fun findByDescriptionShort(descriptionShort: String): Language
}
