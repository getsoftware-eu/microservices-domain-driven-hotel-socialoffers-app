package eu.getsoftware.hotelico.hotel.repository;

import eu.getsoftware.hotelico.hotel.model.Language
import org.springframework.data.jpa.repository.JpaRepository

interface LanguageRepository: JpaRepository<eu.getsoftware.hotelico.hotel.model.Language, Long> {

	/**
	 * Find language by name.
	 */
	fun findByDescriptionShort(descriptionShort: String): eu.getsoftware.hotelico.hotel.model.Language
}
