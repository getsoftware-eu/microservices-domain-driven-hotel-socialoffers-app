package eu.getsoftware.hotelico.clients.infrastructure.exception;

/**
 *
 * <br/>
 * Created by e.fanshil
 * At 11.10.2018 16:40
 * 
 * Common Dashboard Exception
 */
public class HotelException extends Exception {
	
	String message;
	String reponseMessage;
		
	public HotelException(String message)
	{
		super(message);
		this.message = message;
		this.reponseMessage = message;
	}
}