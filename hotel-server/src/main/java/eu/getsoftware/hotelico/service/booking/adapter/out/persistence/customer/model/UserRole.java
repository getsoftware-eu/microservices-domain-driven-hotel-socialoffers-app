package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model;

import eu.getsoftware.hotelico.service.booking.application.customer.common.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(
        name = "user_roles",
        uniqueConstraints = @UniqueConstraint(columnNames = {"role", "user_id"})
)
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED) // важно для JPA
@AllArgsConstructor
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id", unique = true, nullable = false)
    private Long userRoleId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 45)
    private UserRoleEnum role = UserRoleEnum.ROLE_HOTELICO_VIEWER;

    public UserRole(Long userId, UserRoleEnum role) {
        this.userId = userId;
        this.role = role;
    }
}