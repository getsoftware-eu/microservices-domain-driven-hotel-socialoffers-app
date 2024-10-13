package eu.getsoftware.hotelico.clients.api.clients.common.dto;

import eu.getsoftware.hotelico.clients.common.domain.IDomainRequestDTO;
import lombok.With;

import java.util.Date;

/**
 * DTO lieber immutable - all field are final and no side effects!
 */
@With
//@Getter
//@SuperBuilder //eu:  will inherit the builder capabilities from the abstract class, allowing you to set fields from both the abstract class and the subclass.
public record CustomerRequestDTO (
        long hotelCode,
        long hotelId,
        long requesterId,
        Date checkinTo,
        Date checkinFrom,
        String name, 
        String password 
) implements IDomainRequestDTO
        
        //all fields are final!!!
{
//    public CustomerRequestDTO(long initId){
//        super(initId);
//    }




}
