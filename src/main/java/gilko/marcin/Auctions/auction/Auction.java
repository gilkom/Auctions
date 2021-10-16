package gilko.marcin.Auctions.auction;

import gilko.marcin.Auctions.participant.Observator;
import gilko.marcin.Auctions.participant.Participant;

public interface Auction {
	void startAuction();
	void stopAuction();
	void notifyObservator();
	void registerObserver(Observator o);
	void unregisterObserver(Observator o);
	void checkTimeLeft();
	void bid(double new_price, Participant bidder);
}
