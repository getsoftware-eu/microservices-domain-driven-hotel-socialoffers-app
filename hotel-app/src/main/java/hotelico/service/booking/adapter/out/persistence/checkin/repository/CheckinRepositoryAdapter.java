package hotelico.service.booking.adapter.out.persistence.checkin.repository;

import eu.getsoftware.hotelico.clients.common.adapter.out.persistence.GenericRepositoryAdapter;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CheckinDomainEntityId;
import hotelico.service.booking.adapter.out.persistence.checkin.mapper.CheckinEntityMapper;
import hotelico.service.booking.adapter.out.persistence.checkin.model.CheckinDBEntity;
import hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import org.springframework.stereotype.Component;

@Component
public class CheckinRepositoryAdapter
        extends GenericRepositoryAdapter<CheckinRootDomainEntity, CheckinDBEntity, CheckinDomainEntityId> {

    public CheckinRepositoryAdapter(CheckinRepository repository, CheckinEntityMapper mapper) {
        super(repository, mapper);
    }
}
