package gilko.marcin.Auctions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gilko.marcin.Auctions.auction.AuctionItem;

@SpringBootApplication
public class AuctionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuctionsApplication.class, args);
		AuctionItem rower = new AuctionItem("Super rower costam", 1, 40.00, 50.00, 10, 40.00);
		AuctionItem rower1 = new AuctionItem("Super rower1 costam", 1, 40.00, 50.00, 15, 40.00);
		AuctionItem rower2 = new AuctionItem("Super rower2 costam", 1, 40.00, 50.00, 6, 40.00);
		System.out.println(rower.toString());
		System.out.println(rower1.toString());
		System.out.println(rower2.toString());
		rower.startAuction();
		rower1.startAuction();
		rower.stopAuction();
		}

}
