package chat.application.port.in.process;

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CheckinDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CheckinDomainEntityId;
import org.springframework.stereotype.Service;

@Service
public class ChatCheckinProcessManagerService {

    /**
     * ProcessManager changes the domain layer!
     * @param checkinDTO
     */
    public void handleCheckinUpdated(CheckinDTO checkinDTO){
        ;
    } 
    
    public void handleCheckinCreated(CheckinDTO checkinDTO){
        ;
    }  
    
    public void handleCheckinClosed(CheckinDomainEntityId checkinDomainEntityId){
        ;
    }
}
