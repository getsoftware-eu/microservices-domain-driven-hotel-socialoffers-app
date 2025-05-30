package hotelico.service.booking.application.checkin.domain.model;

import hotelico.service.booking.adapter.out.persistence.checkin.mapper.CheckinEntityMapper;

public class CheckinDomainFactory {

    private final CheckinEntityMapper checkinEntityMapper;

    public CheckinDomainFactory(CheckinEntityMapper checkinEntityMapper) {
        this.checkinEntityMapper = checkinEntityMapper;
    }
}
