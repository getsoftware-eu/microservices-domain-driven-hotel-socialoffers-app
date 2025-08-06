package eu.getsoftware.hotelico.service.booking.application.customer.port.in;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.CustomerFilters;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryPort {

    Optional<CustomerRootDomainEntity> findByDomainId(CustomerDomainEntityId domainId);
    
    List<CustomerDTO> findByFilters(CustomerFilters filters);
}
