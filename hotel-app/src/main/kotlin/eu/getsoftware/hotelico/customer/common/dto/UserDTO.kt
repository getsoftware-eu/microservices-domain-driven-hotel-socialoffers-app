package eu.getsoftware.hotelico.customer.common.dto;

/**
 * for serialisation to JSON!
 */
class UserDTO
{
    private var id: Int = 0

    private var title: String? = null

    private var firstName: String? = null

    private var lastName: String? = null

    private var email: String? = null

    private var company: String? = null

    private var jobTitle: String? = null

//    private List<String> languages;

//    private boolean authLinkedIn;

    private var hotelId: Int? = null
}
