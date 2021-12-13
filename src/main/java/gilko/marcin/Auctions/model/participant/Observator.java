package gilko.marcin.Auctions.model.participant;

import gilko.marcin.Auctions.model.notification.Notification;

public interface Observator {
	void update(double curr_price, long last_bidder, Notification notification);

	void update(double curr_price, long last_bidder, String notification);
}
