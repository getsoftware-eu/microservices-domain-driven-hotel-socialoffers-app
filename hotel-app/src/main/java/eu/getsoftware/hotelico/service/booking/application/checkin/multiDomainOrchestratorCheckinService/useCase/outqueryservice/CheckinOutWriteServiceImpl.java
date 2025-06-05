package eu.getsoftware.hotelico.service.booking.application.checkin.multiDomainOrchestratorCheckinService.useCase.outqueryservice;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.out.CheckinOutWriteService;

public class CheckinOutWriteServiceImpl implements CheckinOutWriteService {
    @Override
    public void save(CheckinRootDomainEntity CheckinRootDomainEntity) {
        
    }

    @Override
    public CustomerDTO updateCheckin(CustomerDTO sessionCustomer) {
        return null;
    }
}
