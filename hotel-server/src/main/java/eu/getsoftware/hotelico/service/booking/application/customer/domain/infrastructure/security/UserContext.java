// path: hotelico/service/booking/application/customer/domain/infrastructure/security/UserContext.java

package eu.getsoftware.hotelico.service.booking.application.customer.domain.infrastructure.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class UserContext {

	private final String username;
	private final List<GrantedAuthority> authorities;

	public UserContext(String username, List<GrantedAuthority> authorities) {
		this.username = username;
		this.authorities = authorities;
	}

	public String getUsername() {
		return username;
	}

	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public static UserContext create(String username, List<GrantedAuthority> authorities) {
		if (username == null || username.isBlank()) {
			throw new IllegalArgumentException("Username is blank: " + username);
		}
		return new UserContext(username, authorities);
	}
}
