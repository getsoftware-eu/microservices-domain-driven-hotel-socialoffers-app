package eu.getsoftware.hotelico.clients.common.domain.gateways;

import java.util.Collection;
import java.util.Optional;

public interface GenericRepositoryPort<T, ID> {

    Collection<T> findAll(); 

    Optional<T> findById(Long id);

    Optional<T> findByDomainId(ID id);

    Optional<T> findByField(String fieldName, Object value);

    void convertAndPersist(T entity);
}
