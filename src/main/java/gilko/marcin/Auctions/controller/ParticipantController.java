package gilko.marcin.Auctions.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gilko.marcin.Auctions.auction.AuctionItem;
import gilko.marcin.Auctions.participant.Participant;
import gilko.marcin.Auctions.service.AuctionItemService;
import gilko.marcin.Auctions.service.ParticipantService;

@Controller
public class ParticipantController {
	
	@Autowired
	private ParticipantService participantService;
	@Autowired
	private AuctionItemService auctionItemService;
	
	@RequestMapping("/participants")
	public String viewParticipants(Model model) {
		List<Participant> listParticipant = participantService.listAll();
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
			participantService.save(participant);
			return "redirect:/participants";
		}
	}
	
	@RequestMapping("/participant/{participant_id}")
	public ModelAndView participantMenu(@PathVariable(name = "participant_id") Long id) {
		ModelAndView mav = new ModelAndView("participant");
		Participant participant = participantService.get(id);
		mav.addObject("participant", participant);
		
		return mav;
	}
	
	@RequestMapping("/participant/{participant_id}/all_auctions")
	public String viewAllAuctions(@PathVariable(name = "participant_id") Long id, Model model) {
		List<AuctionItem> listAuctionItems = auctionItemService.listAll();
		Participant participant = participantService.get(id);
		model.addAttribute("listAuctionItems", listAuctionItems);
		model.addAttribute("participant", participant);
		
		return "all_auctions";
	}
	

}
