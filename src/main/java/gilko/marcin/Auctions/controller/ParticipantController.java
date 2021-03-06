package gilko.marcin.Auctions.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gilko.marcin.Auctions.model.auction.AuctionItem;
import gilko.marcin.Auctions.model.auction_participant.AuctionParticipant;
import gilko.marcin.Auctions.model.auction_participant.AuctionParticipantId;
import gilko.marcin.Auctions.model.notification.Notification;
import gilko.marcin.Auctions.model.participant.Participant;
import gilko.marcin.Auctions.service.AuctionItemService;
import gilko.marcin.Auctions.service.AuctionParticipantService;
import gilko.marcin.Auctions.service.NotificationService;
import gilko.marcin.Auctions.service.ParticipantService;

@Controller
public class ParticipantController {
	
	@Autowired
	private ParticipantService participantService;
	@Autowired
	private AuctionItemService auctionItemService;
	@Autowired
	private AuctionParticipantService auctionParticipantService;
	@Autowired
	private NotificationService notificationService;
	
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
		List<AuctionItem> listAuctionItems = auctionItemService.sortedListAll();
		Participant participant = participantService.get(id);
		model.addAttribute("listAuctionItems", listAuctionItems);
		model.addAttribute("participant", participant);
		
		
		
		return "all_auctions";
	}
	
	@RequestMapping("/participant/{participant_id}/all_active_auctions")
	public String viewAllActiveAuctions(@PathVariable(name = "participant_id") Long id, Model model) {
		List<AuctionItem> listAuctionItems = auctionItemService.sortedListAll();
		List<AuctionItem> listActiveAuctionItems = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		
		for(int i = 0; i < listAuctionItems.size(); i++) {
			long sec = listAuctionItems.get(i).getTime();
			LocalDateTime timeLeft = listAuctionItems.get(i).getStart_time().plusSeconds(sec); 
			if(now.isBefore(timeLeft)) {
				listActiveAuctionItems.add(listAuctionItems.get(i));	
			}
		}
		Participant participant = participantService.get(id);
		model.addAttribute("listActiveAuctionItems", listActiveAuctionItems);
		model.addAttribute("participant", participant);
		
		
		
		return "all_active_auctions";
	
	}
	
	@RequestMapping("/participant/{participant_id}/my_auctions")
	public String viewMyAuctions(@PathVariable(name = "participant_id") Long id, Model model) {
		List<Long> auctionParticipantList = auctionParticipantService.listByParticipantId(id);
		List<AuctionItem> listMyAuctionItems = auctionItemService.sortedListByParticipantId(auctionParticipantList);
		
		Participant participant = participantService.get(id);
		model.addAttribute("listMyAuctionItems", listMyAuctionItems);
		model.addAttribute("participant", participant);
		
		
		
		return "my_auctions";
	
	}
	
	
	@RequestMapping("/participant/{participant_id}/auction/{auctionItem_id}")
	public ModelAndView viewAuction(@PathVariable(name = "participant_id") Long participant_id, 
			@PathVariable(name= "auctionItem_id") Long auctionItem_id) {
		ModelAndView mav = new ModelAndView("auction");
		
		Participant participant = participantService.get(participant_id);
		mav.addObject("participant", participant);
		
		AuctionItem auctionItem = auctionItemService.get(auctionItem_id);
		mav.addObject("auctionItem", auctionItem);
		
		Duration duration = Duration.between(auctionItem.getStart_time(), LocalDateTime.now());
		long seconds = auctionItem.getTime() - duration.getSeconds();
		mav.addObject("seconds", seconds);
		
		List<Notification> listNotifications = notificationService.auctionHistory(auctionItem_id, auctionItem.getOwner());		
		mav.addObject("listNotifications", listNotifications);
		
		return mav;
	}
	
	@PostMapping("/participant/{participant_id}/auction/{auction_item_id}/bid")
	public String bid(@PathVariable(name = "participant_id")Long participant_id,
			@PathVariable(name = "auction_item_id")Long auction_item_id,
			@ModelAttribute("auctionItem")@Valid AuctionItem auctionItem,
			BindingResult auctionResult, Model model) {
		
				AuctionItem previousPriceItem = auctionItemService.get(auction_item_id);
				double newPrice = auctionItem.getCurr_price();
				double oldPrice = previousPriceItem.getCurr_price();
		
				Participant participant = participantService.get(participant_id);
				model.addAttribute("participant", participant);
			
				AuctionItem auctionIt = auctionItemService.get(auction_item_id);

				auctionIt.setLast_bidder(participant.getParticipant_id());
				auctionIt.setCurr_price(auctionItem.getCurr_price());
				model.addAttribute("auctionItem",auctionIt);
				
				Duration duration = Duration.between(auctionIt.getStart_time(), LocalDateTime.now());				
				long seconds = auctionIt.getTime() - duration.getSeconds();				
				model.addAttribute("seconds", seconds);
				
				AuctionParticipantId auctionParticipantId = new AuctionParticipantId(auctionItem, participant);
				AuctionParticipant auctionParticipant = new AuctionParticipant(auctionParticipantId);
				
				List<AuctionParticipant> auctionParticipantList = auctionParticipantService.listId(auction_item_id);
				
				if(newPrice <=oldPrice) {
					return "redirect:/participant/" + participant_id + "/auction/" + auction_item_id;
				}
				String message = "User " + participant_id+ " bidded " + newPrice;
				Notification notification = new Notification(LocalDateTime.now(),message, participant, auctionItem);
				
				if(auctionResult.hasErrors()) {
					return "auction";
				}else {
					auctionItemService.save(auctionIt);
					auctionParticipantService.registerObserver(auctionParticipant);
					
					auctionParticipantList = auctionParticipantService.listId(auction_item_id);
					for(int i = 0; i < auctionParticipantList.size(); i++) {

						Notification notific = new Notification(notification.getNotification_time(),
									notification.getMessage(), auctionParticipantList.get(i).getParticipant(),
									notification.getAuctionItem());
						
						notificationService.save(notific);
						
					}
					return "redirect:/participant/" + participant_id + "/auction/" + auction_item_id;
				}
	}
	
	@RequestMapping("/participant/{participant_id}/new_auction")
	public String showNewAuctionItemPage(@PathVariable(name = "participant_id") Long participant_id, Model model) {
		AuctionItem auctionItem = new AuctionItem();
		Participant participant = participantService.get(participant_id);
		
		model.addAttribute("auctionItem", auctionItem);
		model.addAttribute("participant", participant);
		
		return "new_auction";
	}
	
	@PostMapping("/participant/{participant_id}/new_auction/save")
	public String saveNewAuction(@PathVariable(name = "participant_id")Long participant_id, 
				@ModelAttribute("auctionItem")@Valid AuctionItem auctionItem,
				BindingResult auctionResult, Model model) {
				
		
				Participant participant = participantService.get(participant_id);
				model.addAttribute("participant", participant);
				
				auctionItem.setStart_time(LocalDateTime.now());
				auctionItem.setCurr_price(auctionItem.getStart_price());
				auctionItem.setOwner(participant_id);
				
				AuctionParticipantId auctionParticipantId = new AuctionParticipantId(auctionItem, participant);
				AuctionParticipant auctionParticipant = new AuctionParticipant(auctionParticipantId);
				
				Notification notification = new Notification(LocalDateTime.now(),"You created a new auction", participant, auctionItem);
				
				if(auctionResult.hasErrors()) {
					return "new_auction";
				}else {
					
					
					auctionItemService.save(auctionItem);
					auctionParticipantService.registerObserver(auctionParticipant);
					notificationService.save(notification);
					return "redirect:/participant/" + participant_id + "/all_auctions";
				}

	}
	
	
	

}
