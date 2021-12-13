package gilko.marcin.Auctions.model.auction;

import gilko.marcin.Auctions.model.notification.Notification;
import gilko.marcin.Auctions.model.participant.Observator;
import gilko.marcin.Auctions.model.participant.Participant;

public interface Auction {
	void startAuction(Participant participant, AuctionItem auctionItem);
	void stopAuction(Participant participant, AuctionItem auctionItem);
	void notifyObservator(String notification);
	void notifyObservator(Notification notification);
	void registerObserver(Observator o);
	void unregisterObserver(Observator o);
	void checkTimeLeft();
	void bid(double new_price, long bidder);
}
