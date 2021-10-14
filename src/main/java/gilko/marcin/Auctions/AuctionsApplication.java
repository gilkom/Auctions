package gilko.marcin.Auctions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gilko.marcin.Auctions.auction.AuctionItem;
import gilko.marcin.Auctions.participant.Participant;

@SpringBootApplication
public class AuctionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuctionsApplication.class, args);
		AuctionItem rower = new AuctionItem("Super rower costam", 1, 40.00, 50.00, 10, 40.00);
		AuctionItem rower1 = new AuctionItem("Super rower1 costam", 1, 40.00, 50.00, 15, 40.00);
		AuctionItem rower2 = new AuctionItem("Super rower2 costam", 1, 40.00, 50.00, 6, 40.00);
		AuctionItem szafka = new AuctionItem("Czarna szafka", 1, 30.00, 40.00, 30, 0.00);
		
		Participant part1 = new Participant("Jan", "Kowalski", "kowalski@gmail.com");
		Participant part2 = new Participant("Andrzej", "Nowak", "nowak@gmail.com");
		System.out.println(rower.toString());
		System.out.println(rower1.toString());
		System.out.println(rower2.toString());
		rower.startAuction();
		rower1.startAuction();
		rower.stopAuction();
		szafka.startAuction();
		szafka.checkTimeLeft();
		

		System.out.println("-----");
		part1.bid(55,rower2);
		part2.bid(56, rower2);
		part1.bid(45, rower);
		
		part1.showMyAuctions();
		part2.showMyAuctions();
		
		part1.stopObserve(rower2);
		
		part1.showMyAuctions();
		part2.showMyAuctions();
		
		part2.stopObserve(rower2);
		
		rower2.stopAuction();
		
		rower.startAuction();
		szafka.checkTimeLeft();
		}

}
