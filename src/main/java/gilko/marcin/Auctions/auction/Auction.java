package gilko.marcin.Auctions.auction;

import gilko.marcin.Auctions.participant.Observator;
import gilko.marcin.Auctions.participant.Participant;

public interface Auction {
	public void startAuction();
	public void stopAuction();
	public void notifyObserver();
	public void registerObserver(Observator o);
	public void unregisterObserver(Observator o);
}
