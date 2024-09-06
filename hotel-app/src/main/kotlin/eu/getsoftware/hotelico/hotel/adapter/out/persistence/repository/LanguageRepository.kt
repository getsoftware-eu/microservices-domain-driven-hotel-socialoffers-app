package eu.getsoftware.hotelico.hotel.adapter.out.persistence.repository;

import eu.getsoftware.hotelico.customer.adapter.out.persistence.model.Language
import org.springframework.data.jpa.repository.JpaRepository

interface LanguageRepository: JpaRepository<Language, Long> {

	/**
	 * Find language by name.
	 */
	fun findByDescriptionShort(descriptionShort: String): Language
}
