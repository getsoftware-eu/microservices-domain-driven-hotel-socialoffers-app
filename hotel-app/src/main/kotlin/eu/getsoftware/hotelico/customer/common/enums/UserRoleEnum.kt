package eu.getsoftware.hotelico.customer.common.enums

/**
 *
 * <br/>
 * Created by e.fanshil
 * At 20.12.2016 15:19
 *
 * Enum for Spring-Security ROLE
 *
 */

enum class UserRoleEnum (value:String, var description:String) {
    ROLE_HOTELICO_ADMIN("ROLE_HOTELICO_ADMIN", "Darf alles. Nur Admin darf ... bearbeiten."),
    ROLE_HOTELICO_EDITOR("ROLE_HOTELICO_EDITOR", "Darf eigenen ... bearbeiten und erstellen."),
    ROLE_HOTELICO_SENSOR("ROLE_HOTELICO_SENSOR", "Darf eigenen ... anpassen."),
    ROLE_HOTELICO_VIEWER("ROLE_HOTELICO_VIEWER", "Darf nur eigenen ... anschauen."),
    ROLE_HOTELICO_LINK("ROLE_HOTELICO_LINK", "Darf nur Kunden-Links anschauen."),
    ROLE_ANONYMOUS("ROLE_ANONYMOUS", "Keine Rechte");

    val value: String = value

    companion object {

        fun parse(role: String?): UserRoleEnum? {

            return UserRoleEnum.values().filter { x -> (role.equals(x.value, ignoreCase = true) || role.equals(x.name, ignoreCase = true) || role.equals(x.shortValue(), ignoreCase = true)) }.firstOrNull()
        }
        
        fun parse(roleList: List<String>): List<UserRoleEnum> {

            return roleList.mapNotNull { x -> parse(x.trim()) }
        }
    }

    fun shortValue() : String
    {
        return this.value.removePrefix("ROLE_")
    }
    
    
    fun simpleString() : String
    {
        return this.value.removePrefix("ROLE_HOTELICO_")
    }
}
