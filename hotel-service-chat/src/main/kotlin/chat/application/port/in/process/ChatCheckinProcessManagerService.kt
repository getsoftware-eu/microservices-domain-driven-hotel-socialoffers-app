package chat.application.port.`in`.process;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO
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
     * @param checkinUseCaseDTO
     */
    fun handleCheckinUpdated(checkinUseCaseDTO: CheckinUseCaseDTO) {
        ;
    } 
    
    fun handleCheckinCreated(checkinUseCaseDTO: CheckinUseCaseDTO) {
        ;
    }  
    
    fun handleCheckinClosed(checkinDomainEntityId: CheckinDomainEntityId) {
        ;
    }
}
