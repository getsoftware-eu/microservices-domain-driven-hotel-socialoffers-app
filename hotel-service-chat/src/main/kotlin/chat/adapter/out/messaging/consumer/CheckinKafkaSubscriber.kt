package chat.adapter.out.messaging.consumer

import chat.application.port.`in`.process.ChatCheckinProcessManagerService
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.CheckinUpdatedEventPayload
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.domainMessage.DomainMessage
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {}

@Component
//@Slf4j
//@RequiredArgsConstructor
internal class CheckinKafkaSubscriber(
    val chatCheckinProcessManager: ChatCheckinProcessManagerService
) {
    
    @KafkaListener(topics = ["checkin.checkin.created.event", "checkin.checkin.updated.event"], groupId = "eu_group_1")
    fun listener(data: DomainMessage<*>) {
        println("Kafka listener : " + data.toString())
    }

    @KafkaListener(topics = ["checkin.checkin.created.event"], groupId = "eu_group_1")
    fun createCheckin(message: DomainMessage<CheckinUpdatedEventPayload>) {
        val payload: CheckinUpdatedEventPayload = message.getPayload()

        run {
            log.info("Processing event {}", message.getMessageType())
            val checkinUseCaseDTO: CheckinUseCaseDTO = toCheckin(payload)
            //            checkinRepository.save(checkinDTO);
            chatCheckinProcessManager.handleCheckinCreated(checkinUseCaseDTO)
        }
    }

    //    @DomainMessageHandler("checkin.checkin.updated.event")
    @KafkaListener(topics = ["checkin.checkin.updated.event"], groupId = "eu_group_1")
    fun updateCheckin(message: DomainMessage<CheckinUpdatedEventPayload>) {
        val payload: CheckinUpdatedEventPayload = message.getPayload()

        run {
            log.info("Processing event {}", message.getMessageType())
            val checkin: CheckinUseCaseDTO = toCheckin(payload)
            //            checkinRepository.partialUpdateCheckin(checkin);
            chatCheckinProcessManager.handleCheckinUpdated(checkin)
        }
    }

    //    @DomainMessageHandler("checkin.checkin.deleted.event")
    @KafkaListener(topics = ["checkin.checkin.deleted.event"], groupId = "eu_group_1")
    fun deleteCheckin(message: DomainMessage<CheckinUpdatedEventPayload>) {
        val payload: CheckinUpdatedEventPayload = message.getPayload()

        run {
            log.info("Processing event {}", message.getMessageType())
            //            checkinRepository.deleteById(payload.getEntityId());
            chatCheckinProcessManager.handleCheckinClosed(payload.getEntityId())
        }
    }

    /**
     * Re-create an entity from received Event-Payload
     *
     * @param payload
     * @return
     */
    private fun toCheckin(payload: CheckinUpdatedEventPayload): CheckinUseCaseDTO {
        return CheckinUseCaseDTO.builder()
            .initId(payload.entityId)
            .checkinFrom(payload.checkinFrom)
            .checkinTo(payload.checkinTo)
            .hotelId(payload.hotelId)
            .customerId(payload.customerId)
            .build()
    }
}
