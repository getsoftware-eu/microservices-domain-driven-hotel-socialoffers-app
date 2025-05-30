package eu.getsoftware.hotelico.clients.common.domain.mapper;

public interface EntityGenericMapper<T, DBEntity> {
    T toDomain(DBEntity entity);

//    @Mapping(target = "sourceDomainEntityId", source = "domainEntityId")
    DBEntity toDb(T domain);

    
}
