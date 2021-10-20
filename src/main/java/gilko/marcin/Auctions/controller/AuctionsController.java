package gilko.marcin.Auctions.controller;
import org.springframework.web.bind.annotation.RequestMapping;


public class AuctionsController {
	@RequestMapping("/")
	public String viewAuctions() {
		return "index";
	}
}
