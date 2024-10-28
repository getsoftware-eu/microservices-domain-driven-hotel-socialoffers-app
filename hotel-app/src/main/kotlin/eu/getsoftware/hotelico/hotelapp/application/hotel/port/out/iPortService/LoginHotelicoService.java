package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.ResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <br/>
 * Created by e.fanshil
 * At 03.02.2016 13:59
 */
public interface LoginHotelicoService
{
	@Transactional CustomerDTO checkLogin(String email, String password);
	
	@Transactional
	void logoutCustomer(CustomerDTO customerDto);
	
	@Transactional CustomerDTO resetPassword(String email, String resetCode);
	
	@Transactional ResponseDTO requestPasswordReset(String email);
	
	@Transactional
	void setLogged(long customerId, boolean logged);
	
	long getCryptoHash(CustomerRootEntity customerEntity, String initialPassword);
	
	@Transactional
	Optional<CustomerDTO> checkBeforeLoginProperties(CustomerDTO loggingCustomer, CustomerDTO dbCustomer);
}
