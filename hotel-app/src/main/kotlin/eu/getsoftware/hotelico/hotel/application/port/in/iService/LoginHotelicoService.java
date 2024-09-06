package eu.getsoftware.hotelico.hotel.application.port.in.iService;

import eu.getsoftware.hotelico.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.customer.adapter.out.persistence.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotel.application.infrastructure.dto.ResponseDTO;
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
