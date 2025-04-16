package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.application.port.in.process;

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CheckinDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CheckinDomainEntityId;
import org.springframework.stereotype.Service;

/**
 * ProcessManager changes the domain layer 
 * (from external domain event + local anti-corruption layer)!
 */
@Service
public class MenuProcessManagerService {

    /**
     * ProcessManager changes the domain layer!
     * @param checkinDTO
     */
    public void handleCheckinUpdated(CheckinDTO checkinDTO) {
        ;
    }

    public void handleCheckinCreated(CheckinDTO checkinDTO) {
        ;
    }

    public void handleCheckinClosed(CheckinDomainEntityId checkinDomainEntityId) {
        ;
    }
}
