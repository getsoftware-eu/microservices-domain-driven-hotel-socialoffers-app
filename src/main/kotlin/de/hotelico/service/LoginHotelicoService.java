package de.hotelico.service;

import org.springframework.transaction.annotation.Transactional;

import de.hotelico.dto.CustomerDTO;
import de.hotelico.dto.ResponseDTO;
import de.hotelico.model.Customer;

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
	
	long getCryptoHash(Customer customer, String initialPassword);
	
	@Transactional CustomerDTO checkBeforeLoginProperties(CustomerDTO loggingCustomer, CustomerDTO dbCustomer);
}
