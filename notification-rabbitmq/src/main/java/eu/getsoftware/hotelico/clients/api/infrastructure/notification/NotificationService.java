package eu.getsoftware.hotelico.clients.api.infrastructure.notification;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.NotificationConsumeRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService
{

    private final NotificationRepository notificationRepository;
    private final String standandSender = "hotelicoStandardSender";
    
    /**
     * 
     * @param notificationRequest
     */
    public void persistConsumedNotification(NotificationConsumeRequest notificationRequest) {
        
        NotificationEntity buildedNotificationEntity = NotificationEntity.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerName())
                .sender(standandSender)
                .message(notificationRequest.message())
                .sentAt(LocalDateTime.now())
                .build();
        
        notificationRepository.save(
                buildedNotificationEntity
        );
    }

   
}
