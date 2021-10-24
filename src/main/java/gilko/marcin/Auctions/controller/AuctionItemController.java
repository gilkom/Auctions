package gilko.marcin.Auctions.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gilko.marcin.Auctions.auction.AuctionItem;
import gilko.marcin.Auctions.service.AuctionItemService;

@Controller
public class AuctionItemController {
	
	@Autowired
	private AuctionItemService service;
	
	@RequestMapping("/auctions")
	public String viewAuctions(Model model) {
		List<AuctionItem> listAuctionItems = service.listAll();
		model.addAttribute("listAuctionItems", listAuctionItems);
		
		return "auctions";
	}
	
	@RequestMapping("/auctions/new")
	public String showNewAuctionItemPage(Model model) {
		AuctionItem auctionItem = new AuctionItem();
		model.addAttribute("auctionItem", auctionItem);
		
		return "new_auction_item";
	}
	
	@RequestMapping(value="auctions/new/save", method = RequestMethod.POST)
	public String saveNewAuction(@Valid @ModelAttribute("auctionItem") AuctionItem auctionItem,
				BindingResult result) {
		if(result.hasErrors()) {
			return "new_auction_item";
		}else {
			service.save(auctionItem);
			return "redirect:/auctions";
		}

	}
	
}
