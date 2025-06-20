package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.messaging;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.mapper.CheckinDtoMapper;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.checkin.port.out.CheckinOutEntityQueryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
@RepositoryEventHandler
@RequiredArgsConstructor
public class CheckinRepositoryEventHandler {

    @NonNull
    private final CheckinMessagePublisher checkinMessagePublisher;

    @NonNull
    private final CheckinOutEntityQueryService checkinService;

    private final CheckinDtoMapper checkinDomainMapper;

    @HandleAfterCreate //выполнить определенные действия сразу после создания нового объекта в базе данных
    public void sendCreatedEvent(CheckinRootDomainEntity checkin) {
        CheckinUseCaseDTO checkinDTO = checkinDomainMapper.toDto(checkin);
        checkinMessagePublisher.publishCheckinCreatedEvent(checkinDTO);
    }

    @HandleAfterSave //выполнить определенные действия сразу после update объекта в базе данных
    public void sendUpdatedEvent(CheckinRootDomainEntity checkin) {
        CheckinUseCaseDTO checkinDTO = checkinDomainMapper.toDto(checkin);
        checkinMessagePublisher.publishCheckinUpdatedEvent(checkinDTO);
    }

    @HandleAfterLinkSave
    public void onDefaultImageSavedEvent(CheckinRootDomainEntity checkin, Object image) {
        CheckinUseCaseDTO checkinDTO = checkinDomainMapper.toDto(checkin);
        checkinMessagePublisher.publishCheckinUpdatedEvent(checkinDTO);
    }

    @HandleAfterLinkDelete
    public void onChildEntityDeleted(CheckinUseCaseDTO checkinDTO, Object child) {
        if (child instanceof Image) {
            // for DELETE on default-image
            checkinMessagePublisher.publishCheckinUpdatedEvent(checkinDTO);
        }
    }

    @HandleBeforeDelete
    public void deleteImagesAndAttachmentsBeforeCheckinDeletion(CheckinRootDomainEntity checkin){
        CheckinUseCaseDTO checkinDTO = checkinDomainMapper.toDto(checkin);
//        checkinService.deleteAllImagesAndAttachments(checkinDTO);
    }

    @HandleAfterDelete
    public void sendDeletedEvent(CheckinRootDomainEntity checkin) {
        CheckinUseCaseDTO checkinDTO = checkinDomainMapper.toDto(checkin);
        checkinMessagePublisher.publishCheckinDeletedEvent(checkinDTO);
    }
}
