package eu.getsoftware.hotelico.service.booking.application.customer.port.in;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.CustomerFilters;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryPort {

    Optional<CustomerRootDomainEntity> findByDomainId(CustomerDomainEntityId domainId);
    CustomerRootDomainEntity save(CustomerRootDomainEntity domainEntity);
    
    List<CustomerDTO> findByFilters(CustomerFilters filters);

    Optional<CustomerRootDomainEntity> findByName(@NotBlank(message = "Name cannot be blank") @Size(min = 3, message = "Name must be at least 3 characters long") String name);
}
