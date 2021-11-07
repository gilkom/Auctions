package gilko.marcin.Auctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gilko.marcin.Auctions.model.notification.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{

}
