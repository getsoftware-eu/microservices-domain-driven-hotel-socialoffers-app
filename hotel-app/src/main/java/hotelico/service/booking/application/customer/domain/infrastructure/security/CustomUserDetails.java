package hotelico.service.booking.application.customer.domain.infrastructure.security; 

import hotelico.service.booking.adapter.out.persistence.customer.model.UserEntity;
import hotelico.service.booking.application.customer.common.enums.UserRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * This service is needed for SPRING-SECURITY
 */
public class CustomUserDetails extends UserEntity implements UserDetails {

    private final List<String> userRoles;

    public CustomUserDetails(UserEntity user, List<String> userRoles) {
        super(user);
        this.userRoles = userRoles;
    }

    @Override
    public String getPassword() {
        return super.getUserPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // TODO: handle real expiration logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // TODO: handle real lock logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // TODO: handle real credential expiration logic
    }

    @Override
    public boolean isEnabled() {
        return true; // TODO: handle real enable/disable logic
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    public boolean isAdmin() {
        String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
        String[] split = roles.split(",");
        return Arrays.asList(split).contains(UserRoleEnum.ROLE_HOTELICO_ADMIN.getValue());
    }

    public boolean canEdit() {
        String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
        String[] split = roles.split(",");
        List<String> roleList = Arrays.asList(split);
        return roleList.contains(UserRoleEnum.ROLE_HOTELICO_ADMIN.getValue())
                || roleList.contains(UserRoleEnum.ROLE_HOTELICO_EDITOR.getValue());
    }
}
