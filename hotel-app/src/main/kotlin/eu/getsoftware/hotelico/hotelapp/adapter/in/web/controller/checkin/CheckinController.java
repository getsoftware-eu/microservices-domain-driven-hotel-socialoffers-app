package eu.getsoftware.hotelico.hotelapp.adapter.in.web.controller.checkin;


import com.fasterxml.jackson.databind.ObjectMapper;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseRequestDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.openapi.generated.checkin.CheckinApi;
import eu.getsoftware.hotelico.clients.openapi.generated.checkin.dto.CheckinRequestRestDTO;
import eu.getsoftware.hotelico.clients.openapi.generated.checkin.dto.CheckinResponseRestDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.in.usecase.CheckinUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkin")
@RequiredArgsConstructor
public class CheckinController implements CheckinApi {

    private final CheckinUseCase checkinUseCase;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(CheckinController.class);


//    @PostMapping("/register")
    public ResponseEntity<CheckinResponseRestDTO> registerUser(@Valid @RequestBody CheckinRequestRestDTO restRequestDTO) throws Throwable {

        MDC.put("userId", "1");
        log.info("Hello structured logging!");

        //TODO objectMapper here
        CheckinUseCaseRequestDTO useCaseRequest = new CheckinUseCaseRequestDTO(
                    CustomerDomainEntityId.from(restRequestDTO.getCustomerId()),
                    HotelDomainEntityId.from(restRequestDTO.getHotelId()),
                    restRequestDTO.getCheckinFrom(),
                    restRequestDTO.getCheckinTo(),
                    1,
                    "Eugen");

        CheckinUseCaseDTO useCaseResponse = checkinUseCase.createCustomerCheckin(useCaseRequest);

        //TODO objectMapper here
        CheckinResponseRestDTO response = new CheckinResponseRestDTO();
        response.setUserId(useCaseResponse.getCustomerId().uuidValue());
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}