package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.repository;

import eu.getsoftware.hotelico.clients.common.adapter.out.persistence.GenericRepositoryAdapter;
import eu.getsoftware.hotelico.clients.common.domain.ids.CheckinDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.mapper.CheckinEntityMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.CheckinDBEntity;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

@Component
public class CheckinRepositoryAdapter
        extends GenericRepositoryAdapter<CheckinRootDomainEntity, CheckinDBEntity, CheckinDomainEntityId> {

    public CheckinRepositoryAdapter(CheckinRepository repository, CheckinEntityMapper mapper, EntityManager entityManager) {
        super(repository, mapper, entityManager, CheckinDBEntity.class);
    }
}
