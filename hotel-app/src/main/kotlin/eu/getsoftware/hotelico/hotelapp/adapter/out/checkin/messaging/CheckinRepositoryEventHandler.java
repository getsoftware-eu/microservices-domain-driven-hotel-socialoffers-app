package eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.messaging;

import eu.getsoftware.hotelico.clients.common.domain.mapper.IDomainMapper;
import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.model.CustomerHotelCheckin;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.dto.CheckinDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.dto.CheckinRequestDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.port.out.CheckinPortService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
@RepositoryEventHandler
@RequiredArgsConstructor
public class CheckinRepositoryEventHandler {

//    @NonNull
//    private ResourceProcessor<Resource<CheckinDTO>> checkinResourceProcessor;

    @NonNull
    private final CheckinMessagePublisher checkinMessagePublisher;

    @NonNull
    private final CheckinPortService checkinService;

    private final IDomainMapper<CustomerHotelCheckin, CheckinRequestDTO, CheckinDTO> checkinDomainMapper;

    @HandleAfterCreate //выполнить определенные действия сразу после создания нового объекта в базе данных
    public void sendCreatedEvent(CustomerHotelCheckin checkin) {
        CheckinDTO checkinDTO = checkinDomainMapper.toResponseDTO(checkin);
        checkinMessagePublisher.publishCheckinCreatedEvent(checkinDTO);
    }

    @HandleAfterSave //выполнить определенные действия сразу после update объекта в базе данных
    public void sendUpdatedEvent(CustomerHotelCheckin checkin) {
        CheckinDTO checkinDTO = checkinDomainMapper.toResponseDTO(checkin);
        checkinMessagePublisher.publishCheckinUpdatedEvent(checkinDTO);
    }

    @HandleAfterLinkSave
    public void onDefaultImageSavedEvent(CustomerHotelCheckin checkin, Object image) {
        CheckinDTO checkinDTO = checkinDomainMapper.toResponseDTO(checkin);
        checkinMessagePublisher.publishCheckinUpdatedEvent(checkinDTO);
    }

    @HandleAfterLinkDelete
    public void onChildEntityDeleted(CheckinDTO checkinDTO, Object child) {
        if (child instanceof Image) {
            // for DELETE on default-image
            checkinMessagePublisher.publishCheckinUpdatedEvent(checkinDTO);
        }
    }

    @HandleBeforeDelete
    public void deleteImagesAndAttachmentsBeforeCheckinDeletion(CustomerHotelCheckin checkin){
        CheckinDTO checkinDTO = checkinDomainMapper.toResponseDTO(checkin);
        checkinService.deleteAllImagesAndAttachments(checkinDTO);
    }

    @HandleAfterDelete
    public void sendDeletedEvent(CustomerHotelCheckin checkin) {
        CheckinDTO checkinDTO = checkinDomainMapper.toResponseDTO(checkin);
        checkinMessagePublisher.publishCheckinDeletedEvent(checkinDTO);
    }
}
