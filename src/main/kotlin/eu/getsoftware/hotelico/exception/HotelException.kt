package eu.getsoftware.hotelico.exception

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