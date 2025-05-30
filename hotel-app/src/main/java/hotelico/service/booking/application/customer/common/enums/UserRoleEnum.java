// path: hotelico/service/booking/application/customer/common/enums/UserRoleEnum.java

package hotelico.service.booking.application.customer.common.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum UserRoleEnum {

    ROLE_HOTELICO_ADMIN("ROLE_HOTELICO_ADMIN", "Darf alles. Nur Admin darf ... bearbeiten."),
    ROLE_HOTELICO_EDITOR("ROLE_HOTELICO_EDITOR", "Darf eigenen ... bearbeiten und erstellen."),
    ROLE_HOTELICO_SENSOR("ROLE_HOTELICO_SENSOR", "Darf eigenen ... anpassen."),
    ROLE_HOTELICO_VIEWER("ROLE_HOTELICO_VIEWER", "Darf nur eigenen ... anschauen."),
    ROLE_HOTELICO_LINK("ROLE_HOTELICO_LINK", "Darf nur Kunden-Links anschauen."),
    ROLE_ANONYMOUS("ROLE_ANONYMOUS", "Keine Rechte");

    private final String value;
    private final String description;

    UserRoleEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String shortValue() {
        return value.replace("ROLE_", "");
    }

    public String simpleString() {
        return value.replace("ROLE_HOTELICO_", "");
    }

    public static UserRoleEnum parse(String role) {
        if (role == null || role.isBlank()) return null;

        for (UserRoleEnum x : values()) {
            if (role.equalsIgnoreCase(x.value)
                    || role.equalsIgnoreCase(x.name())
                    || role.equalsIgnoreCase(x.shortValue())) {
                return x;
            }
        }
        return null;
    }

    public static List<UserRoleEnum> parse(List<String> roleList) {
        List<UserRoleEnum> result = new ArrayList<>();
        if (roleList == null) return result;

        for (String role : roleList) {
            UserRoleEnum parsed = parse(role.trim());
            if (parsed != null) {
                result.add(parsed);
            }
        }
        return result;
    }
}
