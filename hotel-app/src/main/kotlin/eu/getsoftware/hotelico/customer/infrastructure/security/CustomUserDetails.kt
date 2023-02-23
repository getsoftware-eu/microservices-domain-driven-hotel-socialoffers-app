package eu.getsoftware.hotelico.customer.infrastructure.security//package eu.getsoftware.hotelico.clients.infrastructure.service.security

import eu.getsoftware.hotelico.customer.domain.User
import eu.getsoftware.hotelico.customer.domain.enums.UserRoleEnum
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.util.StringUtils
import java.util.*

/**
 * This service is needed for SPRING-SECURITY
 */

class CustomUserDetails : User, UserDetails
{

    private val userRoles: List<String>

    constructor(user: User, userRoles: List<String>) : super(user) {
        this.userRoles = userRoles
    }

    override fun getPassword(): String {
        return super.getUserPassword()
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {

        val roles = StringUtils.collectionToCommaDelimitedString(userRoles)
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles)
    }

    override fun isAccountNonExpired(): Boolean {
        // TODO Auto-generated method stub
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return super.userName
    }

    fun isAdmin(): Boolean {

        val roleStrings = StringUtils.collectionToCommaDelimitedString(userRoles)

        val split = roleStrings.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        return Arrays.asList(*split).contains(UserRoleEnum.ROLE_HOTELICO_ADMIN.value)
    }    

    fun canEdit(): Boolean {

        val roleStrings = StringUtils.collectionToCommaDelimitedString(userRoles)

        val split = roleStrings.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val asList = Arrays.asList(*split)

        return asList.contains(UserRoleEnum.ROLE_HOTELICO_ADMIN.value) || asList.contains(UserRoleEnum.ROLE_HOTELICO_EDITOR.value)
    }

}
