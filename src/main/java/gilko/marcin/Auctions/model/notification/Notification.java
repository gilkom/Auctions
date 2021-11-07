package gilko.marcin.Auctions.model.notification;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import gilko.marcin.Auctions.model.participant.Participant;

@Entity
@Table(name = "notification")
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_id")
	private long notification_id;
	private LocalDateTime notification_time;
	private String message;

	@ManyToOne
	@JoinColumn(name = "participant_id")
	private Participant participant;
	
	public Notification() {}
	public Notification(LocalDateTime notification_time, String message, Participant participant) {
		this.notification_time = notification_time;
		this.message = message;
		this.participant = participant;
	}
	
	public long getNotification_id() {
		return notification_id;
	}
	public LocalDateTime getNotification_time() {
		return notification_time;
	}
	public void setNotification_time(LocalDateTime notification_time) {
		this.notification_time = notification_time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Participant getParticipant() {
		return participant;
	}
	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
}
