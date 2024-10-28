package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface Hotel2Repository {
    Optional<Hotel> findVisibleById(String id);

}
