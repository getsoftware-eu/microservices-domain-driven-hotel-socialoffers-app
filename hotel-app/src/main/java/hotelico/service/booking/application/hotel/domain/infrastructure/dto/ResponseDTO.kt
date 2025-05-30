package hotelico.service.booking.application.hotel.domain.infrastructure.dto;

/**
 * Created by Eugen on 09.08.2015.
 */
data class ResponseDTO(private var id: Int = 0)
{
	var message: String? = null

	var isError: Boolean = false
	
	constructor(message: String, isError: Boolean): this() {
		this.message = message
		this.isError = isError
	}
}
