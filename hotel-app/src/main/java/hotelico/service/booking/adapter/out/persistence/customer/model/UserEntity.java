package hotelico.service.booking.adapter.out.persistence.customer.model;

import eu.getsoftware.hotelico.clients.common.utils.HibernateUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "users", schema = "customer")
@Data
//eu: удовлетворяешь JPA, и в то же время предотвращаешь прямое создание объекта вне контролируемого контекста.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = HibernateUtils.ColumnDefinition.VARCHAR_255_DEFAULT_EMPTY)
    private String keycloakId;

    @Column(name = "username", columnDefinition = HibernateUtils.ColumnDefinition.VARCHAR_100_DEFAULT_EMPTY)
    private String userName;

    @Column
    @NotNull
    @Size(max = 500)
    @Getter @Setter
    private String password;

    @Transient
    @NotNull
    private String tempPassword;

    @Column(columnDefinition = HibernateUtils.ColumnDefinition.LONG_20_DEFAULT_0)
    private Long userHash;

    @Column
    private Boolean accountNonExpired;

    @Column
    private Boolean accountNonLocked;

    @Column
    private Boolean credentialsNonExpired;

    @Column
    private int enabled = 1;

    @Column(columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    private boolean restAccess;

    // Специальный конструктор для копирования
    public UserEntity(UserEntity user) {
        this.id = user.id;
        this.keycloakId = user.keycloakId;
        this.userName = user.userName;
        this.password = user.password;
        this.enabled = user.enabled;
    }
    
}