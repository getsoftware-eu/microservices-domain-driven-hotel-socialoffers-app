package eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.messaging;

import eu.getsoftware.hotelico.clients.api.amqp.application.domain.model.DomainMessage;
import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.repository.CheckinRepository;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainApplicationCheckinService.useCase.dto.CheckinDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * central subscriber class
 * eu: builds from payload our local (re-created) instance for persisting it (partial) updated state in repository
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CheckinMessageSubscriber implements DomainMessageSubscriber {
    
    private static final String TYPE = "checkin";
    
    private final CheckinRepository checkinRepository;
    
    @RabbitListener("checkin.checkin.created.event")
    public void createCheckin(DomainMessage<CheckinAggregatePayload> message) {
        CheckinAggregatePayload payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            CheckinDTO checkin = toCheckin(payload).build();
            checkinRepository.save(checkin);
        }
    }

    @RabbitListener("checkin.checkin.updated.event")
    public void updateCheckin(DomainMessage<CheckinAggregatePayload> message) {
        CheckinAggregatePayload payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            CheckinDTO checkin = toCheckin(payload).build();
            checkinRepository.partialUpdateCheckin(checkin);
        }
    } 
    
    @RabbitListener("checkin.checkin.deleted.event")
    public void deleteCheckin(DomainMessage<CheckinAggregatePayload> message) {
        CheckinAggregatePayload payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            checkinRepository.delete(payload.getId());
        }
    }

    /**
     * Re-create an entity from received Event-Payload
     * @param payload
     * @return
     */
    private CheckinBuilder toCheckin(CheckinAggregatePayload payload) {
        return CheckinDTO.builder()
                .initId(payload.getId())
                .from(paylod.getfrom())
                .to(paylod.getTo())
                .hotelId(payload.getHoteId())
                .customerId(payload.getCustomerId());
    }

    private final TenantAccessor tenantAccessor;

    @RabbitListener("checkin.checkin.created.event")
    public void createProduct(DomainMessage<CheckinAggregatePayload> message) {
        CheckinAggregatePayload payload = message.getPayload();
        withMdc(CHECKIN, payload.getId()).execute(() -> {
            log.info("Processing event {}", message.getMessageType());
            var checkin = toCheckin(payload).build();
            checkinRepository.index(checkin);
        });
    }
}

