package eu.getsoftware.hotelico.clients.api.clients.dto.usecase

import eu.getsoftware.hotelico.clients.common.domain.IDomainRequestDTO
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

/**
 *
 * create Record in adapter package, but dtoInterface in useCase package!!
 *
 * REQUEST Representation:
 * every requestDTO has the name of requester and its password???  requester is active-session-user
 * @param name
 * @param password
 */
data class UserRegisterRequestUseCaseDTO (
    @get:JvmName("requesterId")
    val requesterId: Long,
    
    @NotBlank(message = "Name cannot be blank")
    @Size(
        min = 3,
        message = "Name must be at least 3 characters long"
    )
    @get:JvmName("name")
    val name: String,
    
    val email: String?,
    val username: String,

//    val fullName: String
//    get() = "$firstName $lastName"
    
    @Size(min = 8, message = "{validation.name.size.too_short}") @Size(
        max = 200,
        message = "{validation.name.size.too_long}"
    )
    val password: String, 
    val specialFieldForUseCase: String, 
    val creationTime: LocalDateTime? = LocalDateTime.now()
) : IDomainRequestDTO {

    private fun validateName(checkName: String) {
        require(checkName.length >= 3) { "Name must be at least 3 characters long" }
    }

    private fun validatePassword(checkPassword: String) {
        require(!checkPassword.equals("checkPassword", ignoreCase = true)) { "Password cannot be 'checkPassword'" }
    }

    init {
        validateName(name)
        validatePassword(password)
    }

    override fun requesterId(): Long {
        return requesterId
    }

    override fun name(): String {
        return name
    }
}
