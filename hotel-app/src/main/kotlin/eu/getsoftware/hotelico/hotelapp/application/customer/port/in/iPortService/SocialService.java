package eu.getsoftware.hotelico.hotelapp.application.customer.port.in.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;

import java.util.Optional;

/**
 * All methods of this class throws NullPoinrerException if a required argument is null
 */
public interface SocialService
{
    CustomerDTO getByLinkedInId(String linkedInId);
    
    /**
     * 
     * @param facebookId
     * @return Dto of customer 
     * returns ResourceNotFoundException if customer with facebookId not found
     */
    Optional<CustomerDTO> getByFacebookId(String facebookId);
   
//    @Transactional 
    CustomerDTO addLinkedInCustomer(CustomerDTO customerDto, String linkedIn);
       
    CustomerDTO addFacebookCustomer(CustomerDTO customerDto, String facebookId);
}
