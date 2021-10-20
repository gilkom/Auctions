package gilko.marcin.Auctions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gilko.marcin.Auctions.participant.Participant;
import gilko.marcin.Auctions.service.ParticipantService;

@Controller
public class ParticipantController {
	
	@Autowired
	private ParticipantService service;
	
	@RequestMapping("/participants")
	public String viewParticipants(Model model) {
		List<Participant> listParticipant = service.listAll();
		model.addAttribute("listParticipant", listParticipant);
		
		return "participants";
	}
}
