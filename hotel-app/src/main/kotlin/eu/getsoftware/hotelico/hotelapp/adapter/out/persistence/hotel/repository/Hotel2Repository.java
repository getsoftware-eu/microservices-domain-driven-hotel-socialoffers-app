package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.repository;

import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.HotelDbEntity;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface Hotel2Repository {
    Optional<HotelDbEntity> findVisibleById(String id);

}
