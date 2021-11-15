package gilko.marcin.Auctions.model.participant;

public interface Observator {
	void update(double curr_price, long last_bidder, String notification);
}
