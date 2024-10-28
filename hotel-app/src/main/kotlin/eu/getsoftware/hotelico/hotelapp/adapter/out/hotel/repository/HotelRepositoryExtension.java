package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository;

public interface HotelRepositoryExtension {
    Page<Hotel> findVisibleByTags(@NotNull Set<String> tags, @NotNull Pageable pageable);

    Page<Hotel> findVisibleBySearchTerm(@Nullable String searchTerm, @NotNull Pageable pageable);

    Page<Hotel> findVisibleByCategoryQuery(@NotNull CategoryQuery categoryQuery, @NotNull Pageable pageable);

    Optional<Hotel> findOneIgnoringVisibility(@NotNull String id);

    Hotel partialUpdateHotel(@NotNull Hotel product);

    Hotel partialUpdateAttributes(@NotNull Hotel product);

    Hotel partialUpdateImages(@NotNull Hotel product);

    Hotel partialUpdateAvailability(@NotNull Hotel product);
}
