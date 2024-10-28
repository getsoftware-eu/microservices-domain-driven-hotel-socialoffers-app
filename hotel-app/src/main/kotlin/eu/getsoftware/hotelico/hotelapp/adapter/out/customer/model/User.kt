package eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model

import eu.getsoftware.hotelico.clients.common.utils.HibernateUtils
import eu.getsoftware.hotelico.clients.common.utils.HibernateUtils.ColumnDefinition.VARCHAR_100_DEFAULT_EMPTY
import eu.getsoftware.hotelico.clients.common.utils.HibernateUtils.ColumnDefinition.VARCHAR_255_DEFAULT_EMPTY
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.io.Serializable

/**
 *
 * <br/>
 * Created by e.fanshil
 * At 20.12.2016 15:17
 */
@Entity
@Table(name = "users", schema = "customer")
open class User : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id:Long = 0

    @Column(columnDefinition = VARCHAR_255_DEFAULT_EMPTY)
    open var keycloakId: String = ""

    @Column(name = "username", columnDefinition = VARCHAR_100_DEFAULT_EMPTY)
    open var userName: String = ""

    @Column
    @NotNull
    @Size(max=500)
    private var password:String = ""

    @javax.persistence.Transient
    @NotNull
    var tempPassword: String = ""

//    @Column(columnDefinition = VARCHAR_100_DEFAULT_EMPTY)
//    open var email: String = ""
    
    @Column(columnDefinition = HibernateUtils.ColumnDefinition.LONG_20_DEFAULT_0)
    open var userHash: Long = 0

//    @Column(columnDefinition = VARCHAR_255_DEFAULT_EMPTY)
//    open var fullName: String = ""

    @Column
    private val accountNonExpired: Boolean? = null

    @Column
    private val accountNonLocked: Boolean? = null

    @Column
    private val credentialsNonExpired: Boolean? = null

//    @ManyToOne
//    @JoinColumn(name = "mandant_id", nullable = true)
//    open var mandant: MandantEntity? = null

    @Column
    open var enabled:Int = 1

    @Column(columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
    open var restAccess:Boolean = false
    
//    @Column(columnDefinition = HibernateUtils.Column.BOOL_DEFAULT_TRUE)
//    open var sensorMapDarkMode:Boolean = true
//   
//    @Column(columnDefinition = HibernateUtils.Column.BOOL_DEFAULT_FALSE)
//    open var subAccess:Boolean = false
    
    constructor()

    constructor(keycloakId:String) {
        this.keycloakId = keycloakId
    }

    constructor(userName:String, password:String/*, mandant: MandantEntity*/) {
        this.userName = userName
        this.password = password
//        this.mandant = mandant
    }

    // eugen: for securityCall
    constructor(user: User) 
    {
        this.id = user.id
        this.keycloakId = user.keycloakId
        this.userName = user.userName
        this.password = user.password
        this.enabled = user.enabled
//        this.fullName = user.fullName
//        this.mandant = user.mandant
    }

    fun getUserPassword() : String
    {
        return this.password
    }
    
    fun setUserPassword(password: String)
    {
        this.password = password
    }
}