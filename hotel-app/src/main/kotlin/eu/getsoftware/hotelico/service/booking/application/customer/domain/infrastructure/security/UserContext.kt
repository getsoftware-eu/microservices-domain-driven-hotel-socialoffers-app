package eu.getsoftware.hotelico.service.booking.application.customer.domain.infrastructure.security

import org.springframework.security.core.GrantedAuthority

/**
 *
 *
 * Aug 4, 2016
 */
class UserContext {
	
	var username: String
	var authorities: List<GrantedAuthority>
	
	constructor(username: String, authorities: List<GrantedAuthority>) {
		this.username = username
		this.authorities = authorities
	}
	
	companion object {
		
		@JvmStatic
		fun create(username: String, authorities: kotlin.collections.List<GrantedAuthority>): eu.getsoftware.hotelico.service.booking.application.customer.domain.infrastructure.security.UserContext {
			if (username.isNullOrBlank()) throw IllegalArgumentException("Username is blank: $username")
			return eu.getsoftware.hotelico.service.booking.application.customer.domain.infrastructure.security.UserContext(
                username,
                authorities
            )
		}
	}
	
}
