package eu.getsoftware.hotelico.clients.common.adapter.out.persistence;

import eu.getsoftware.hotelico.clients.common.domain.gateways.GenericRepositoryPort;
import eu.getsoftware.hotelico.clients.common.domain.mapper.EntityGenericMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;

//@RequiredArgsConstructor
public class GenericRepositoryAdapter<T, DBEntity, ID> implements GenericRepositoryPort<T, ID> {

    private final JpaRepository<DBEntity, Long> repository;
    private final EntityGenericMapper<T, DBEntity> mapper;
    private final EntityManager entityManager;
    private final Class<DBEntity> entityClass;
    
    public GenericRepositoryAdapter(JpaRepository<DBEntity, Long> repository, EntityGenericMapper<T, DBEntity> mapper, EntityManager entityManager, Class<DBEntity> entityClass){
        this.repository = repository;
        this.mapper = mapper;
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }    
   
    @Override
    @Transactional(readOnly = true)
    public Collection<T> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }    
    
    @Override
    public Optional<T> findByDomainEntityIdValue(ID domainId) {
       return this.findByField("domainId", domainId);
    }

//    @Override
    @Transactional(readOnly = true)
    public Optional<T> findByField(String fieldName, Object value) {
        try {
            // ✅ Используем Criteria API вместо загрузки всех записей
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<DBEntity> query = criteriaBuilder.createQuery(entityClass);
           
            Root<DBEntity> fromRoot = query.from(entityClass); //eu: FROM your_table

            query
                .select(fromRoot) //eu: SELECT *
                .where(criteriaBuilder.equal(fromRoot.get(fieldName), value)); //eu: WHERE fieldName = 'value'

            return entityManager.createQuery(query)
                    .getResultList()
                    .stream()
                    .findFirst() //находит первый элемент, что позволяет безопасно вернуть OPTIONAL!!!
                    .map(mapper::toDomain); // eu: we map already OPTIONAL! (from FIND-FIRST)

        } catch (Exception e) {
            throw new EntityNotFoundException(
                    String.format("Error finding entity by field '%s' with value '%s'", fieldName, value), e);
        }
    }
    
//    @Override
    @Transactional(readOnly = true)
    public Optional<T> findByFieldOld(String fieldName, Object value) {
        try {

            return repository.findAll()
                    .stream()
                    .filter(entity -> {
                        try {
                            Field field = entity.getClass().getDeclaredField(fieldName);
                            field.setAccessible(true);
                            return value.equals(field.get(entity));
                        } catch (Exception e) {
                            return false; // Игнорируем ошибки доступа к полям
                        }
                    })
                    .findFirst()
                    .map(mapper::toDomain); // not Optional.of(mapper.toDomain(entity)
            
//            for (DBEntity entity : repository.findAll()) {
//                Field field = entity.getClass().getDeclaredField(fieldName);
//                field.setAccessible(true);
//                if (value.equals(field.get(entity))) {
//                    return Optional.of(mapper.toDomain(entity));
//                }
//            }
        } catch (Exception e) {
            throw new EntityNotFoundException("Error during findByField" + fieldName, e);
        }
    }

    @Override
    @Transactional // eu : but not in Domain interface!!! write to db
    public void convertAndPersist(T entity) {
        try {
            DBEntity dbEntity = mapper.toDb(entity);
            repository.save(dbEntity);
        } catch (Exception e) {
            throw new PersistenceException("Failed to persist entity", e); //eu: extra add "nice" adapter exceptions
        }
    }
}