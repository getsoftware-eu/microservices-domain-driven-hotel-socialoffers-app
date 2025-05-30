// path: hotelico/service/booking/application/customer/common/dto/UserDTO.java

package eu.getsoftware.hotelico.service.booking.application.customer.common.dto;

import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * For serialization to JSON!
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private CustomerDomainEntityId domainEntityId;

    private String title;

    private String firstName;

    private String lastName;

    private String email;

    private String company;

    private String jobTitle;

    // private List<String> languages;

    // private boolean authLinkedIn;

    private String hotelDomainId;
}
