package eu.getsoftware.hotelico.clients.api.application.infrastructure.exception;

/**
 *
 * <br/>
 * Created by e.fanshil
 * At 11.10.2018 16:40
 * 
 * Common Dashboard Exception
 */
public class BasicHotelException extends Exception {
	
	String message;
	String reponseMessage;
		
	public BasicHotelException(String message)
	{
		super(message);
		this.message = message;
		this.reponseMessage = message;
	}
}