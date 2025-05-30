package hotelico.service.booking.adapter.out.persistence.hotel.repository;

import hotelico.service.booking.adapter.out.persistence.hotel.model.HotelDBEntity;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface Hotel2Repository {
    Optional<HotelDBEntity> findVisibleById(String id);

}
