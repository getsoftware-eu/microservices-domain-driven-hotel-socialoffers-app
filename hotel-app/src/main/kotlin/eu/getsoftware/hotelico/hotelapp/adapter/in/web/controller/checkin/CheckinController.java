package eu.getsoftware.hotelico.hotelapp.adapter.in.web.controller.checkin;


import eu.getsoftware.hotelico.clients.api.clients.common.dto.CheckinDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.CheckinRequestDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.in.CheckinUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkin")
@RequiredArgsConstructor
public class CheckinController {

    private final CheckinUseCase checkinUseCase;

    @PostMapping("/register")
    public CheckinDTO registerUser(@Valid @RequestBody CheckinRequestDTO requestDTO) {
        return checkinUseCase.createCustomerCheckin(requestDTO);
    }
}