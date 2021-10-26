package gilko.marcin.Auctions.auction_participant;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import gilko.marcin.Auctions.auction.AuctionItem;
import gilko.marcin.Auctions.participant.Participant;

@Embeddable
public class AuctionParticipant implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private AuctionItem auctionItem;
	private Participant participant;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	public AuctionItem getAuctionItem() {
		return auctionItem;
	}
	
	public void setAuctionItem(AuctionItem auctionItem) {
		this.auctionItem = auctionItem;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Participant getParticipant() {
		return participant;
	}
	
	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
}
