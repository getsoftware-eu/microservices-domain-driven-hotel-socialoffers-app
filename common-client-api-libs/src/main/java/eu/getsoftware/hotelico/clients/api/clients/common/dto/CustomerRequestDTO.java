package eu.getsoftware.hotelico.clients.api.clients.common.dto;

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
        Date checkinTo,
        Date checkinFrom,
        String password 
) //implements BasicDTO
        
        //all fields are final!!!
{
//    public CustomerRequestDTO(long initId){
//        super(initId);
//    }




}
