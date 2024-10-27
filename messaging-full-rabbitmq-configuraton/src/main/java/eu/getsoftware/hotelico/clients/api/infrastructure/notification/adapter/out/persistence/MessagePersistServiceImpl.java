package eu.getsoftware.hotelico.clients.api.infrastructure.notification.adapter.out.persistence;

import eu.getsoftware.hotelico.clients.api.infrastructure.notification.adapter.out.persistence.model.NotificationEntity;
import eu.getsoftware.hotelico.clients.api.infrastructure.notification.application.service.IMessagePersistService;
import eu.getsoftware.hotelico.clients.infrastructure.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MessagePersistServiceImpl implements IMessagePersistService
{

    private final NotificationRepository notificationRepository;
    private final String standardSender = "hotelicoStandardSender";
    
    /**
     * 
     * @param notificationRequest
     */
    public void persistConsumedNotification(NotificationRequest notificationRequest) {
        
        NotificationEntity buildedNotificationEntity = NotificationEntity.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerName(notificationRequest.toCustomerName())
                .sender(standardSender)
                .message(notificationRequest.message())
                .sentAt(LocalDateTime.now())
                .build();
        
        notificationRepository.save(
                buildedNotificationEntity
        );
    }

   
}
