package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.repository;

import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.HotelDbEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

public interface HotelRepositoryExtension {
    Page<HotelDbEntity> findVisibleByTags(@NotNull Set<String> tags, @NotNull Pageable pageable);

    Page<HotelDbEntity> findVisibleBySearchTerm(@Nullable String searchTerm, @NotNull Pageable pageable);

//    Page<HotelRootEntity> findVisibleByCategoryQuery(@NotNull CategoryQuery categoryQuery, @NotNull Pageable pageable);

    Optional<HotelDbEntity> findOneIgnoringVisibility(@NotNull String id);

    HotelDbEntity partialUpdateHotel(@NotNull HotelDbEntity product);

    HotelDbEntity partialUpdateAttributes(@NotNull HotelDbEntity product);

    HotelDbEntity partialUpdateImages(@NotNull HotelDbEntity product);

    HotelDbEntity partialUpdateAvailability(@NotNull HotelDbEntity product);
}
