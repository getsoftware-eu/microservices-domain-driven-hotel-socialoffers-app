package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.hotel.repository;

import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.customer.model.Language
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LanguageRepository: JpaRepository<Language, Long> {

	/**
	 * Find language by name.
	 */
	fun findByDescriptionShort(descriptionShort: String): Language
}
