package gilko.marcin.Auctions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import gilko.marcin.Auctions.model.notification.Notification;
import gilko.marcin.Auctions.model.participant.Participant;
import gilko.marcin.Auctions.service.NotificationService;
import gilko.marcin.Auctions.service.ParticipantService;

@Controller
public class NotificationController {
	
	@Autowired
	private ParticipantService participantService;
	@Autowired
	private NotificationService notificationService;
	

	@RequestMapping("/participant/{participant_id}/notifications")
	public String viewNotifications(@PathVariable(name = "participant_id") Long id, Model model) {
		List<Notification> listNotifications = notificationService.listByParticipantId(id);
		Participant participant = participantService.get(id);
		
		model.addAttribute("listNotifications", listNotifications);
		model.addAttribute("participant", participant);
		return "notifications";
		
	}
}
