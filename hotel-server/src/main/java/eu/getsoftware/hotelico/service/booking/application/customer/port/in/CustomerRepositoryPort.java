package eu.getsoftware.hotelico.service.booking.application.customer.port.in;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.CustomerFilters;

import java.util.List;

public interface CustomerRepositoryPort {
    List<CustomerDTO> findByFilters(CustomerFilters filters);
}
