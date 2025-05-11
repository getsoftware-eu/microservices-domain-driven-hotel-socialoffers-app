package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.security//package eu.getsoftware.hotelico.clients.infrastructure.service.security.impl

import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.UserEntity
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.repository.UserRolesRepository
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.infrastructure.security.CustomUserDetails
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

    private val IUserService: eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService.IUserService
    private val userRolesRepository: UserRolesRepository

    @Autowired
    constructor(IUserService: eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService.IUserService, userRolesRepository: UserRolesRepository) {
        this.IUserService = IUserService
        this.userRolesRepository = userRolesRepository
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user : UserEntity = IUserService.getByUserName(username)
        if (null == user || user.enabled == 0) {
            throw UsernameNotFoundException("No user present with userName: $username")
        } else {
            val userRoles = userRolesRepository.getStringRoleByUserName(username)
            return CustomUserDetails(user, userRoles)
        }
    }
}
