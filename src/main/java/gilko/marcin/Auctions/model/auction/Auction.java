package gilko.marcin.Auctions.model.auction;

import gilko.marcin.Auctions.model.participant.Observator;
import gilko.marcin.Auctions.model.participant.Participant;

public interface Auction {
	void startAuction();
	void stopAuction();
	void notifyObservator(String notification);
	void registerObserver(Observator o);
	void unregisterObserver(Observator o);
	void checkTimeLeft();
	void bid(double new_price, Participant bidder);
}
