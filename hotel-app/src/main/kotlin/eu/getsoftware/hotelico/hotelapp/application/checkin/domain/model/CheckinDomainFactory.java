package eu.getsoftware.hotelico.hotelapp.application.checkin.domain.model;

import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.mapper.CheckinEntityMapper;

public class CheckinDomainFactory {

    private final CheckinEntityMapper checkinEntityMapper;

    public CheckinDomainFactory(CheckinEntityMapper checkinEntityMapper) {
        this.checkinEntityMapper = checkinEntityMapper;
    }
}
