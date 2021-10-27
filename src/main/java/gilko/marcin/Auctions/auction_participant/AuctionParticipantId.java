package gilko.marcin.Auctions.auction_participant;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import gilko.marcin.Auctions.auction.AuctionItem;
import gilko.marcin.Auctions.participant.Participant;

@Embeddable
public class AuctionParticipantId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private AuctionItem auctionItem;
	@ManyToOne(cascade = CascadeType.ALL)
	private Participant participant;
	
	public AuctionParticipantId() {}
	
	public AuctionParticipantId(AuctionItem auctionItem, Participant participant) {
		this.auctionItem = auctionItem;
		this.participant = participant;
	}
	
	public AuctionItem getAuctionItem() {
		return auctionItem;
	}
	
	public void setAuctionItem(AuctionItem auctionItem) {
		this.auctionItem = auctionItem;
	}
	
	public Participant getParticipant() {
		return participant;
	}
	
	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
}
