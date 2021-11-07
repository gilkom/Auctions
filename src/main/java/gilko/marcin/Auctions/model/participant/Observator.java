package gilko.marcin.Auctions.model.participant;

public interface Observator {
	void update(double curr_price, Participant last_bidder, String notification);
}
