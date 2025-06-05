package eu.getsoftware.hotelico.service.booking.application.checkin.port.out;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;

public interface CheckinOutWriteService {

    void save(CheckinRootDomainEntity CheckinRootDomainEntity);

    //TODO ob UseCase or service???
    CustomerDTO updateCheckin(CustomerDTO sessionCustomer);
    
}
