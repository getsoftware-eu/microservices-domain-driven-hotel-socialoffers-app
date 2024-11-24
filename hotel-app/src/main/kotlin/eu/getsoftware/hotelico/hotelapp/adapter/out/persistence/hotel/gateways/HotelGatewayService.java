package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.gateways;

import eu.getsoftware.hotelico.clients.common.domain.domainGateway.GenericRepositoryService;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.repository.HotelRepositoryAdapter;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.customDomainModelImpl.HotelRootDomainEntity;
import org.springframework.stereotype.Service;

@Service
public class HotelGatewayService extends GenericRepositoryService<HotelRootDomainEntity, HotelDomainEntityId> {
    
    public HotelGatewayService(HotelRepositoryAdapter repositoryAdapter) {
        super(repositoryAdapter);
    }
}
