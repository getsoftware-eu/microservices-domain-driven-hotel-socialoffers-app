package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model;

import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelRootEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.Set;

public interface HotelRepositoryExtension {
    
    Page<HotelRootEntity> findVisibleByTags(@NotNull Set<String> tags, @NotNull Pageable pageable);

    Page<HotelRootEntity> findVisibleBySearchTerm(@Nullable String searchTerm, @NotNull Pageable pageable);

//    Page<HotelRootEntity> findVisibleByCategoryQuery(@NotNull CategoryQuery categoryQuery, @NotNull Pageable pageable);

    Optional<HotelRootEntity> findOneIgnoringVisibility(@NotNull String id);

    HotelRootEntity partialUpdateHotel(@NotNull HotelRootEntity product);

    HotelRootEntity partialUpdateAttributes(@NotNull HotelRootEntity product);

    HotelRootEntity partialUpdateImages(@NotNull HotelRootEntity product);

    HotelRootEntity partialUpdateAvailability(@NotNull HotelRootEntity product);
}
