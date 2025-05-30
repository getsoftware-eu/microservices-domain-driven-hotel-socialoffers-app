// path: hotelico/service/booking/adapter/out/persistence/customer/security/CustomUserDetailsService.java

package hotelico.service.booking.adapter.out.persistence.customer.security;

import hotelico.service.booking.adapter.out.persistence.customer.model.UserEntity;
import hotelico.service.booking.adapter.out.persistence.customer.repository.UserRolesRepository;
import hotelico.service.booking.application.customer.domain.infrastructure.security.CustomUserDetails;
import hotelico.service.booking.application.hotel.port.out.iPortService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This provider is needed for SPRING-SECURITY
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserService userService;
    private final UserRolesRepository userRolesRepository;

    @Autowired
    public CustomUserDetailsService(IUserService userService, UserRolesRepository userRolesRepository) {
        this.userService = userService;
        this.userRolesRepository = userRolesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.getByUserName(username);

        if (user == null || user.getEnabled() == 0) {
            throw new UsernameNotFoundException("No user present with userName: " + username);
        }

        List<String> userRoles = userRolesRepository.getStringRoleByUserName(username);
        return new CustomUserDetails(user, userRoles);
    }
}
