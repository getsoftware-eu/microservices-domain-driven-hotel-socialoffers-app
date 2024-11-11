package eu.getsoftware.hotelico.hotelapp.application.menu.infrastructure.service;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.menu.dto.MenuOrderDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuMSCommunicationService {
    public List<MenuOrderDTO> getActiveMenusByCustomerId(CustomerDomainEntityId receiverId, HotelDomainEntityId receiverHotelId, int i, int i1, boolean b) {
        throw new UnsupportedOperationException();
    }

    public List<MenuOrderDTO> getAllHotelMenusToRoom(CustomerDomainEntityId receiverId, HotelDomainEntityId receiverHotelId, int i) {
        throw new UnsupportedOperationException();
    }
}
