package eu.getsoftware.hotelico.clients.common.adapter.out.persistence;

import eu.getsoftware.hotelico.clients.common.domain.domainGateway.GenericRepositoryPort;
import eu.getsoftware.hotelico.clients.common.domain.mapper.EntityGenericMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;

//@RequiredArgsConstructor
public class GenericRepositoryAdapter<T, DBEntity, ID> implements GenericRepositoryPort<T, ID> {

    private final JpaRepository<DBEntity, Long> repository;
    private final EntityGenericMapper<T, DBEntity> mapper;

    GenericRepositoryAdapter(JpaRepository<DBEntity, Long> repository, EntityGenericMapper<T, DBEntity> mapper){
        this.repository = repository;
        this.mapper = mapper;
    }
    
    @Override
    public Optional<T> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }    
   
    @Override
    public Collection<T> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }    
    
    @Override
    public Optional<T> findByDomainId(ID domainId) {
       return this.findByField("domainId", domainId);
    }

    @Override
    public Optional<T> findByField(String fieldName, Object value) {
        try {
            for (DBEntity entity : repository.findAll()) {
                Field field = entity.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                if (value.equals(field.get(entity))) {
                    return Optional.of(mapper.toDomain(entity));
                }
            }
        } catch (Exception e) {
            throw new EntityNotFoundException("Error during findByField", e);
        }
        return Optional.empty();
    }

    @Override
    @Transactional // eu : but not in Domain interface!!! write to db
    public void convertAndPersist(T entity) {
        DBEntity dbEntity = mapper.toDb(entity);
        repository.save(dbEntity);
    }
}