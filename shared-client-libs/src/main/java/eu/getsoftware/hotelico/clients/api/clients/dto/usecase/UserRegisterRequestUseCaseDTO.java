package eu.getsoftware.hotelico.clients.api.clients.dto.usecase;

import eu.getsoftware.hotelico.clients.common.domain.IDomainRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * 
 * create Record in adapter package, but dtoInterface in useCase package!!
 * 
 * REQUEST Representation:
 * every requestDTO has the name of requester and its password???  requester is active-session-user
 * @param name
 * @param password
 */
public record UserRegisterRequestUseCaseDTO(
    
	long requesterId,
	
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 3, message = "Name must be at least 3 characters long")
	String name,

	@Email(message = "Invalid email format")
	@NotBlank(message = "Email cannot be blank")
	String email,
	
	String username,

	@Size(min = 8, message = "{validation.name.size.too_short}")
	@Size(max = 200, message = "{validation.name.size.too_long}")	
	String password,

	String specialFieldForUseCase,
	
	LocalDateTime creationTime
	
) implements IDomainRequestDTO {

	public UserRegisterRequestUseCaseDTO {
		
		if (creationTime == null) {
			creationTime = LocalDateTime.now();
		}
		
		validateName(name);
		validatePasswort(password);
	}

	private void validateName(String checkName) {
		if (checkName.length() < 3) {
			throw new IllegalArgumentException("Name must be at least 3 characters long");
		}
	}
	private void validatePasswort(String checkPassword) {
		if (checkPassword.equalsIgnoreCase("checkPassword")) {
			throw new IllegalArgumentException("Password cannot be 'checkPassword'");
		}
	}
}
