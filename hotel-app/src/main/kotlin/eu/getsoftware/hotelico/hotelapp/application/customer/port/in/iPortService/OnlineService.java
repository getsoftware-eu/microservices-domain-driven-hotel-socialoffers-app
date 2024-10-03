package eu.getsoftware.hotelico.hotelapp.application.customer.port.in.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * All methods of this class throws NullPoinrerException if a required argument is null
 */
public interface OnlineService
{
    List<CustomerDTO> getAllOnline();
	
    List<CustomerDTO> getAllIn24hOnline();

    @Transactional
    void setCustomerPing(long sessionCustomerId);
}
