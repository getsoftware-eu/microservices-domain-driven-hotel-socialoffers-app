package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository;

import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelRootEntity;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface Hotel2Repository {
    Optional<HotelRootEntity> findVisibleById(String id);

}
