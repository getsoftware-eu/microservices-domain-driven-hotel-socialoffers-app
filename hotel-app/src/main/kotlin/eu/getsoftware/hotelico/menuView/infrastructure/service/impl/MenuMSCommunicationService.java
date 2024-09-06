package eu.getsoftware.hotelico.menu.infrastructure.service.impl;

import eu.getsoftware.hotelico.clients.infrastructure.menu.dto.MenuOrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuMSCommunicationService {
    public List<MenuOrderDTO> getActiveMenusByCustomerId(long receiverId, long receiverHotelId, int i, int i1, boolean b) {
        throw new UnsupportedOperationException();
    }

    public List<MenuOrderDTO> getAllHotelMenusToRoom(long receiverId, long receiverHotelId, int i) {
        throw new UnsupportedOperationException();
    }
}
