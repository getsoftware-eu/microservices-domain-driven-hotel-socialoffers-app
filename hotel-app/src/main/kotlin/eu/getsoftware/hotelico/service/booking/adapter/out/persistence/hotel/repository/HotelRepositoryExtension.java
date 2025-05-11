package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository;

import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model.HotelDBEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

public interface HotelRepositoryExtension {
    Page<HotelDBEntity> findVisibleByTags(@NotNull Set<String> tags, @NotNull Pageable pageable);

    Page<HotelDBEntity> findVisibleBySearchTerm(@Nullable String searchTerm, @NotNull Pageable pageable);

//    Page<HotelRootEntity> findVisibleByCategoryQuery(@NotNull CategoryQuery categoryQuery, @NotNull Pageable pageable);

    Optional<HotelDBEntity> findOneIgnoringVisibility(@NotNull String id);

    HotelDBEntity partialUpdateHotel(@NotNull HotelDBEntity product);

    HotelDBEntity partialUpdateAttributes(@NotNull HotelDBEntity product);

    HotelDBEntity partialUpdateImages(@NotNull HotelDBEntity product);

    HotelDBEntity partialUpdateAvailability(@NotNull HotelDBEntity product);
}
