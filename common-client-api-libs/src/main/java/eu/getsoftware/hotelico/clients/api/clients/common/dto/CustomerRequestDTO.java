package eu.getsoftware.hotelico.clients.api.clients.common.dto;

import eu.getsoftware.hotelico.clients.common.dto.BasicDTO;
import lombok.Getter;
import lombok.With;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * DTO lieber immutable - all field are final and no side effects!
 */
@With
@Getter
@SuperBuilder //eu:  will inherit the builder capabilities from the abstract class, allowing you to set fields from both the abstract class and the subclass.
public class CustomerRequestDTO extends BasicDTO
        
        //all fields are final!!!
{
    public CustomerRequestDTO(long initId){
        super(initId);
    }

    private long id = 0;
    
    private String hotelCode = null;
    
    private long hotelId = 0;
    
    private Date checkinTo = null;
}
