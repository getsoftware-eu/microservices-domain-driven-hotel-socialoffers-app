package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.application.port.in.process;

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CheckinDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CheckinDomainEntityId;
import org.springframework.stereotype.Service;

/**
 * ProcessManager changes the domain layer!
 *
 */
@Service
public class MenuProcessManagerService {

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
