package chat.application.port.`in`.process;

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CheckinDTO
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CheckinDomainEntityId
import org.springframework.stereotype.Service

/**
 * ProcessManager changes the domain layer 
 * (from external domain event + local anti-corruption layer)!
 */
@Service
public class ChatCheckinProcessManagerService {

    /**
     * ProcessManager changes the domain layer!
     * @param checkinDTO
     */
    fun handleCheckinUpdated(checkinDTO: CheckinDTO) {
        ;
    } 
    
    fun handleCheckinCreated(checkinDTO: CheckinDTO) {
        ;
    }  
    
    fun handleCheckinClosed(checkinDomainEntityId: CheckinDomainEntityId) {
        ;
    }
}
