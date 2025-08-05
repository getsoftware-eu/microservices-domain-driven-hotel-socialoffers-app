package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository;

import eu.getsoftware.hotelico.clients.common.adapter.out.persistence.GenericRepositoryAdapter;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.mapper.HotelEntityMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model.HotelDBEntity;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.customDomainModelImpl.HotelRootDomainEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

@Component
public class HotelRepositoryAdapter
        extends GenericRepositoryAdapter<HotelRootDomainEntity, HotelDBEntity, HotelDomainEntityId> {

    public HotelRepositoryAdapter(HotelRepository repository, HotelEntityMapper mapper, EntityManager entityManager) {
        super(repository, mapper, entityManager, HotelDBEntity.class);
    }
}
