package hotelico.service.booking.adapter.out.persistence.checkin.gateways;

import eu.getsoftware.hotelico.clients.common.domain.domainGateway.GenericRepositoryService;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CheckinDomainEntityId;
import hotelico.service.booking.adapter.out.persistence.checkin.repository.CheckinRepositoryAdapter;
import hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import org.springframework.stereotype.Service;

@Service
public class CheckinGatewayService extends GenericRepositoryService<CheckinRootDomainEntity, CheckinDomainEntityId> {
    
    public CheckinGatewayService(CheckinRepositoryAdapter repositoryAdapter) {
        super(repositoryAdapter);
    }
}
