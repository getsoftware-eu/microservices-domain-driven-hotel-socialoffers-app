package de.hotelico.dto;

/**
 * Created by Eugen on 09.08.2015.
 */
class ResponseDTO
{
	private var id: Int = 0

	var message: String? = null

	var isError: Boolean = false

	constructor(): super()
	
	constructor(message: String, isError: Boolean): this() {
		this.message = message
		this.isError = isError
	}
}
