package eu.getsoftware.hotelico.infrastructure.hotel.service;

import org.springframework.transaction.annotation.Transactional;

import eu.getsoftware.hotelico.hotel.model.CustomerEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.dto.CustomerDTO;
import eu.getsoftware.hotelico.infrastructure.hotel.dto.ResponseDTO;

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
	
	long getCryptoHash(CustomerEntity customerEntity, String initialPassword);
	
	@Transactional CustomerDTO checkBeforeLoginProperties(CustomerDTO loggingCustomer, CustomerDTO dbCustomer);
}
