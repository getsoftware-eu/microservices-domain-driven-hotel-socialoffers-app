package eu.getsoftware.hotelico.service.menu.application.port.in.process;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO;
import eu.getsoftware.hotelico.clients.common.domain.ids.CheckinDomainEntityId;
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
    public void handleCheckinUpdated(CheckinUseCaseDTO checkinDTO) {
        ;
    }

    public void handleCheckinCreated(CheckinUseCaseDTO checkinDTO) {
        ;
    }

    public void handleCheckinClosed(CheckinDomainEntityId checkinDomainEntityId) {
        ;
    }
}
