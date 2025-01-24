package eu.getsoftware.hotelico.hotelapp.adapter.in.web.controller.checkin;


import com.fasterxml.jackson.databind.ObjectMapper;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CheckinDTO;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CheckinRequestDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.in.CheckinUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkin")
@RequiredArgsConstructor
public class CheckinController {

    private final CheckinUseCase checkinUseCase;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(CheckinController.class);


    @PostMapping("/register")
    public CheckinDTO registerUser(@Valid @RequestBody CheckinRequestDTO requestDTO) throws Throwable {

        MDC.put("userId", "1");
        log.atInfo().info("Hello structured logging!");
       
        return checkinUseCase.createCustomerCheckin(requestDTO);
    }
}