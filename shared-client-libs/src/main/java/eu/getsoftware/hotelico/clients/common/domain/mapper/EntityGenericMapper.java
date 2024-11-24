package eu.getsoftware.hotelico.clients.common.domain.mapper;

public interface EntityGenericMapper<T, DBEntity> {
    T toDomain(DBEntity entity);

    DBEntity toDb(T domain);
}
