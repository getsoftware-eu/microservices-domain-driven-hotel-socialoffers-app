package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.gateways;

import eu.getsoftware.hotelico.clients.common.domain.domainGateway.GenericRepositoryService;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository.HotelRepositoryAdapter;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.customDomainModelImpl.HotelRootDomainEntity;
import org.springframework.stereotype.Service;

@Service
public class HotelGatewayService extends GenericRepositoryService<HotelRootDomainEntity, HotelDomainEntityId> {
    
    public HotelGatewayService(HotelRepositoryAdapter repositoryAdapter) {
        super(repositoryAdapter);
    }
}
