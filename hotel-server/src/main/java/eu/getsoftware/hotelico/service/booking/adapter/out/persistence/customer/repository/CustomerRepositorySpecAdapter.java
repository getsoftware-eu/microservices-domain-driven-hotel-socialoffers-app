package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.repository;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.mapper.CustomerDtoMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.mapper.CustomerEntityMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.CustomerFilters;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.customer.port.in.CustomerRepositoryPort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositorySpecAdapter implements CustomerRepositoryPort {

    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerEntityMapper;
    private final CustomerDtoMapper customerDtoMapper;

    public CustomerRepositorySpecAdapter(CustomerRepository customerRepository, CustomerEntityMapper customerEntityMapper, CustomerDtoMapper customerDtoMapper) {
        this.customerRepository = customerRepository;
        this.customerEntityMapper = customerEntityMapper;
        this.customerDtoMapper = customerDtoMapper;
    }

    @Override
    public Optional<CustomerRootDomainEntity> findByDomainId(CustomerDomainEntityId domainId) {
        Optional<CustomerDBEntity> jpaEntity = customerRepository.findByDomainEntityIdValue(domainId/*.uuidValue()*/);

        return jpaEntity.map(customerEntityMapper::toDomain);
    }

    @Override
    public CustomerRootDomainEntity save(CustomerRootDomainEntity domainEntity) {
        CustomerDBEntity jpaEntity = customerEntityMapper.toDb(domainEntity);
        CustomerDBEntity saved = customerRepository.save(jpaEntity);
        return customerEntityMapper.toDomain(saved);
    }
    
    @Override
    public List<CustomerDTO> findByFilters(CustomerFilters filters) {
        // Создаем пустую спецификацию.
        Specification<CustomerDBEntity> spec = Specification.where(null);

        // Динамически добавляем условия, если они присутствуют
        if (filters.getName() != null) {
            spec = spec.and(CustomerSpecifications.hasName(filters.getName()));
        }
        if (filters.getEmail() != null) {
            spec = spec.and(CustomerSpecifications.hasEmail(filters.getEmail()));
        }
        if (filters.getRegisteredAfter() != null) {
            spec = spec.and(CustomerSpecifications.isRegisteredAfter(filters.getRegisteredAfter()));
        }

        // Передаем объединенную спецификацию в репозиторий
        List<CustomerDBEntity> dbEntities = customerRepository.findAll(spec);

        // Преобразуем сущности в доменные объекты
        return dbEntities.stream()
                .map(customerEntityMapper::toDomain)
                .map(customerDtoMapper::toDto)
                .toList();
    }
}
