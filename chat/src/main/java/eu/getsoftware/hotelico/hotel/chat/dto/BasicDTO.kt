package eu.getsoftware.hotelico.hotel.chat.dto

import java.io.Serializable

/**
 * Created by Eugen on 08.02.2016.
 */
abstract class BasicDTO: Serializable
{
    var id : Long = 0L

    open var initId : Long = 0L

    var active: Boolean = true

    val dtoType: String = this.javaClass.simpleName.toLowerCase()

    constructor()

    constructor(initId: Long) {
        this.initId = initId
    }
}
