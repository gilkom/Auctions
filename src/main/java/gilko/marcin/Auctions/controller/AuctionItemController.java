package gilko.marcin.Auctions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@RequestMapping("/new")
	public String showNewAuctionItemPage(Model model) {
		AuctionItem auctionItem = new AuctionItem();
		model.addAttribute("auctionItem", auctionItem);
		
		return "new_auction_item";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveNewAuction(@ModelAttribute("auctionItem") AuctionItem auctionItem) {
		service.save(auctionItem);
		
		return "redirect:/auctions";
	}
	
}
