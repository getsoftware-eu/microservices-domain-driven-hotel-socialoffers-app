package eu.getsoftware.hotelico.infrastructure.hotel.dto

import java.io.Serializable

/**
 * Created by Eugen on 08.02.2016.
 */
abstract class BasicDTO: Serializable
{
	var id : Long = 0L
	
	var initId : Long = 0L

	var active: Boolean = true

	val dtoType: String = this.javaClass.simpleName.toLowerCase()
}
