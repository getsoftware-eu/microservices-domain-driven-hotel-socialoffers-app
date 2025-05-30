// path: hotelico/service/booking/adapter/out/persistence/hotel/repository/LanguageRepository.java

package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository;

import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

	/**
	 * Find language by short description.
	 */
	Language findByDescriptionShort(String descriptionShort);
}
