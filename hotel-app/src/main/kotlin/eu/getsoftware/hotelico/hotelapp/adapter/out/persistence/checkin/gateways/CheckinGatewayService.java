package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.gateways;

import eu.getsoftware.hotelico.clients.common.domain.domainGateway.GenericRepositoryService;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CheckinDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.repository.CheckinRepositoryAdapter;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.CheckinRootDomainEntity;
import org.springframework.stereotype.Service;

@Service
/*public*/ class CheckinGatewayService extends GenericRepositoryService<CheckinRootDomainEntity, CheckinDomainEntityId> {
    
    public CheckinGatewayService(CheckinRepositoryAdapter repositoryAdapter) {
        super(repositoryAdapter);
    }
}
