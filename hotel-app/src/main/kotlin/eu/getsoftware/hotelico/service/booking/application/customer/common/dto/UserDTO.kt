package eu.getsoftware.hotelico.service.booking.application.customer.common.dto;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId

/**
 * for serialisation to JSON!
 */
data class UserDTO (
   
//    var id: Long = 0

    var domainEntityId: CustomerDomainEntityId,

    var title: String? = null,

    var firstName: String? = null,

    var lastName: String? = null,

    var email: String? = null,

    var company: String? = null,

    var jobTitle: String? = null,

//    List<String> languages;

//    boolean authLinkedIn;

    var hotelDomainId: String? = null
) { 
    
}
