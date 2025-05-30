package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.gateways;

import eu.getsoftware.hotelico.clients.common.domain.gateways.GenericRepositoryService;
import eu.getsoftware.hotelico.clients.common.domain.ids.CheckinDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.repository.CheckinRepositoryAdapter;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import org.springframework.stereotype.Service;

@Service
public class CheckinGatewayService extends GenericRepositoryService<CheckinRootDomainEntity, CheckinDomainEntityId> {
    
    public CheckinGatewayService(CheckinRepositoryAdapter repositoryAdapter) {
        super(repositoryAdapter);
    }
}
