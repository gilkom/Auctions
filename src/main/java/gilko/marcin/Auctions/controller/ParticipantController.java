package gilko.marcin.Auctions.controller;

import java.time.Duration;
import java.time.LocalDateTime;
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
	
	@RequestMapping("/participant/{participant_id}/auction/{auctionItem_id}")
	public ModelAndView viewAuction(@PathVariable(name = "participant_id") Long participant_id, 
			@PathVariable(name= "auctionItem_id") Long auctionItem_id, Model model) {
		ModelAndView mav = new ModelAndView("auction");
		
		Participant participant = participantService.get(participant_id);
		mav.addObject("participant", participant);
		
		AuctionItem auctionItem = auctionItemService.get(auctionItem_id);
		mav.addObject("auctionItem", auctionItem);
		
		LocalDateTime now = LocalDateTime.now();
		Duration duration = Duration.between(auctionItem.getStart_time(), now);
		System.out.println("duration: " + duration.getSeconds());
		long seconds = auctionItem.getTime() - duration.getSeconds();
		
		System.out.println("seconds: " + seconds);
		mav.addObject("seconds", seconds);
		
		return mav;
	}
	
	@RequestMapping("/participant/{participant_id}/new_auction")
	public String showNewAuctionItemPage(@PathVariable(name = "participant_id") Long participant_id, Model model) {
		AuctionItem auctionItem = new AuctionItem();
		Participant participant = participantService.get(participant_id);
		model.addAttribute("auctionItem", auctionItem);
		model.addAttribute("participant", participant);
		
		return "new_auction";
	}
	
	@RequestMapping(value="/participant/{participant_id}/new_auction/save", method = RequestMethod.POST)
	public String saveNewAuction(@PathVariable(name = "participant_id")Long participant_id, 
				@ModelAttribute("auctionItem")@Valid AuctionItem auctionItem,
				BindingResult auctionResult, Model model) {
				
		
				Participant participant = participantService.get(participant_id);
				model.addAttribute("participant", participant);
				
				auctionItem.setStart_time(LocalDateTime.now());
				auctionItem.setCurr_price(auctionItem.getStart_price());
				
				if(auctionResult.hasErrors()) {
					return "new_auction";
				}else {
					auctionItemService.save(auctionItem);
					return "redirect:/participant/" + participant_id + "/all_auctions";
				}

	}
	
	@RequestMapping(value="/participant/{participant_id}/auction/{auction_item_id}/bid", method = RequestMethod.POST)
	public String bid(@PathVariable(name = "participant_id")Long participant_id,
			@PathVariable(name = "auction_item_id")Long auction_item_id,
			@ModelAttribute("auctionItem")@Valid AuctionItem auctionItem,
			BindingResult auctionResult, Model model) {
			Participant participant = participantService.get(participant_id);
			
		if(auctionResult.hasErrors()) {
			return "new_auction";
		}else {
			auctionItemService.save(auctionItem);
			return "redirect:/participant/" + participant_id + "/auction/" + auction_item_id;
		}
	}
	
	

}
