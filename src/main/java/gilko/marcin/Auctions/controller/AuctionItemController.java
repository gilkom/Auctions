package gilko.marcin.Auctions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import gilko.marcin.Auctions.service.AuctionItemService;

@Controller
public class AuctionItemController {
	
	@Autowired
	private AuctionItemService service;
}
