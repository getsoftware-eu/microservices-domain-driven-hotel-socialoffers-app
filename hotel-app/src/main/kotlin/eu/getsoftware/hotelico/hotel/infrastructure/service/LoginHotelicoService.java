package eu.getsoftware.hotelico.hotel.infrastructure.service;

import org.springframework.transaction.annotation.Transactional;

import eu.getsoftware.hotelico.clients.infrastructure.hotel.dto.CustomerDTO;
import eu.getsoftware.hotelico.customer.domain.CustomerRootEntity;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.ResponseDTO;

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
	
	@Transactional CustomerDTO checkBeforeLoginProperties(CustomerDTO loggingCustomer, CustomerDTO dbCustomer);
}
