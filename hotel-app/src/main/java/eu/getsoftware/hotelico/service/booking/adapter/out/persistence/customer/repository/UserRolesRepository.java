// path: hotelico/service/booking/adapter/out/persistence/customer/repository/UserRolesRepository.java

package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.repository;

import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {

    String QUERY_ROLE_STRING_BY_USERNAME =
            "SELECT role.role " +
                    "FROM UserRole role, User user " +
                    "WHERE user.userName = ?1 " +
                    "AND role.user_id = user.id " +
                    "AND user.enabled = 1";

    String QUERY_ROLE_BY_USERNAME =
            "SELECT role " +
                    "FROM UserRole role, User user " +
                    "WHERE user.userName = ?1 " +
                    "AND role.user_id = user.id " +
                    "AND user.enabled = 1";

    @Query(QUERY_ROLE_STRING_BY_USERNAME)
    List<String> getStringRoleByUserName(String username);

    @Query(QUERY_ROLE_BY_USERNAME)
    List<UserRole> getRoleByUserName(String username);
}
