package eu.getsoftware.hotelico.clients.api.infrastructure.notification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
}
