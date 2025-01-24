package eu.getsoftware.hotelico.clients.api.infrastructure.notification.adapter.out.persistence;

import eu.getsoftware.hotelico.clients.api.infrastructure.notification.adapter.out.persistence.model.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
}
