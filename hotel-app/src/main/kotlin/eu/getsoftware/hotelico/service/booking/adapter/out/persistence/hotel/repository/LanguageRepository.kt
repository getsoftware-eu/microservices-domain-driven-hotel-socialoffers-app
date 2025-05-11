package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LanguageRepository: JpaRepository<eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.Language, Long> {

	/**
	 * Find language by name.
	 */
	fun findByDescriptionShort(descriptionShort: String): eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.Language
}
