package eu.getsoftware.hotelico.clients.common.domain.mapper;

public interface DtoGenericMapper<T, DBEntity> {
    T toEntity(DBEntity entity);

    DBEntity toDto(T domain);
}
