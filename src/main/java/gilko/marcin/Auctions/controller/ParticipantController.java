package gilko.marcin.Auctions.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping("/participants/new")
	public String showNewParticipantPage(Model model) {
		Participant participant = new Participant();
		model.addAttribute("participant", participant);
		
		return "new_participant";
	}
	
	@RequestMapping(value="participants/new/save", method = RequestMethod.POST)
	public String saveNewParticipant(@Valid @ModelAttribute("participant") Participant participant,
			BindingResult result) {
		if(result.hasErrors()) {
			return "new_participant";
		}else {
			service.save(participant);
			return "redirect:/participants";
		}
	}
	

}
