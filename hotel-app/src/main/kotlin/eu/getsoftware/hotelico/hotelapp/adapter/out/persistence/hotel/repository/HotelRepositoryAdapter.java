package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.repository;

import eu.getsoftware.hotelico.clients.common.adapter.out.persistence.GenericRepositoryAdapter;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.mapper.HotelEntityMapper;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.HotelDBEntity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.customDomainModelImpl.HotelRootDomainEntity;
import org.springframework.stereotype.Component;

@Component
public class HotelRepositoryAdapter
        extends GenericRepositoryAdapter<HotelRootDomainEntity, HotelDBEntity, HotelDomainEntityId> {

    public HotelRepositoryAdapter(HotelRepository repository, HotelEntityMapper mapper) {
        super(repository, mapper);
    }
}
