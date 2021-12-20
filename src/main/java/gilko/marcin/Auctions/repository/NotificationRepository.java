package gilko.marcin.Auctions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gilko.marcin.Auctions.model.notification.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{

	@Query("SELECT n FROM Notification n WHERE n.participant.participant_id = :participant_id order by notification_id desc")
	public List<Notification> findByParticipant_id(Long participant_id);
}
