package gilko.marcin.Auctions.participant;

public interface Observator {
	void update(double curr_price, Participant last_bidder);
}
