package eu.getsoftware.hotelico.clients.infrastructure.exception

/**
 *
 * <br/>
 * Created by e.fanshil
 * At 11.10.2018 16:40
 * 
 * Common Dashboard Exception
 */
class HotelException(message:String?) : Exception(message) {

    var reponseMessage: String? = message
}