package eu.getsoftware.hotelico.hotelapp.application.hotel.port.in;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.usecase.register.dto.HotelRegisterRequestDTO;

/**
 * in port, only DTO, not inner entity
 * <br/>
 * Created by e.fanshil
 * At 05.02.2016 12:10
 */
public interface HotelRegisterUseCase {
	 HotelDTO create(HotelRegisterRequestDTO requestModel);
}
