package eu.getsoftware.hotelico.infrastructure.notification;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import eu.getsoftware.hotelico.clients.infrastructure.notification.NotificationRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationService
{

    private final NotificationRepository notificationRepository;
    private String standandSender = "hotelicoStandardSender";
    
    /**
     * 
     * @param notificationRequest
     */
    public void persistConsumedNotification(NotificationRequest notificationRequest) {
        
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
