package eu.getsoftware.hotelico.hotel.clients.chat;

import java.io.Serializable;

/**
 * Created by Eugen on 08.02.2016.
 */
abstract class BasicDTO implements Serializable
{
	Long id = 0L;
	
	Long initId = 0L;
	
	Boolean active = true;
	
	String dtoType = this.getClass().getSimpleName().toLowerCase();
	
	BasicDTO(){
		
	};
	
	BasicDTO(Long initId) {
        this.initId = initId;
    }
}
