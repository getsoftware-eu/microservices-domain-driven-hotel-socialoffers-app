package de.hotelico.repository;

import de.hotelico.model.Language
import org.springframework.data.jpa.repository.JpaRepository

interface LanguageRepository: JpaRepository<Language, Long> {

	/**
	 * Find language by name.
	 */
	fun findByDescriptionShort(descriptionShort: String): Language
}
