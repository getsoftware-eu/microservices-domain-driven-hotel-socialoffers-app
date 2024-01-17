package eu.getsoftware.hotelico.customer.infrastructure.security.impl//package eu.getsoftware.hotelico.clients.infrastructure.service.security.impl

import eu.getsoftware.hotelico.customer.infrastructure.repository.UserRolesRepository
import eu.getsoftware.hotelico.customer.infrastructure.security.CustomUserDetails
import eu.getsoftware.hotelico.hotel.application.iservice.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * This provider is needed for SPRING-SECURITY
 */

@Service("customUserDetailsService")
class CustomUserDetailsService : UserDetailsService {

    private val IUserService: IUserService
    private val userRolesRepository: UserRolesRepository

    @Autowired
    constructor(IUserService: IUserService, userRolesRepository: UserRolesRepository) {
        this.IUserService = IUserService
        this.userRolesRepository = userRolesRepository
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = IUserService.getByUserName(username)
        if (null == user || user.enabled == 0) {
            throw UsernameNotFoundException("No user present with userName: $username")
        } else {
            val userRoles = userRolesRepository.getStringRoleByUserName(username)
            return CustomUserDetails(user, userRoles)
        }
    }
}
