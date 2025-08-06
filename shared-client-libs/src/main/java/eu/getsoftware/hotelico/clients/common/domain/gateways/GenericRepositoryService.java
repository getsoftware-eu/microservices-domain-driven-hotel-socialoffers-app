package eu.getsoftware.hotelico.clients.common.domain.gateways;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

//@RequiredArgsConstructor
public class GenericRepositoryService<T, ID> {

    private final GenericRepositoryPort<T, ID> repositoryPort;
    
    public Optional<T> getByDomainId(EntityIdentifier customerId) {
        return repositoryPort.findByDomainId(customerId);
    }
    
    public GenericRepositoryService(GenericRepositoryPort<T, ID> repositoryPort) {
        this.repositoryPort = repositoryPort;
    }
    
    public T findOrThrow(Long entityId) {
        return repositoryPort.findById(entityId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found for ID: " + entityId));
    }    
    
    //eu: экономит бесчисленные .orElseThrow в коде!!! +++
    public T findOrThrow(ID domainId) {
        return repositoryPort.findByDomainEntityIdValue(domainId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found for ID: " + domainId));
    } 

    public Optional<T> findByField(String fieldName, Object value) {
        return repositoryPort.findByField(fieldName, value);
    }

    public void saveToDb(T entity) {
        repositoryPort.convertAndPersist(entity);
    }
}
