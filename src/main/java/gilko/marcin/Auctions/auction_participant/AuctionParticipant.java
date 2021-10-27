package gilko.marcin.Auctions.auction_participant;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import gilko.marcin.Auctions.auction.AuctionItem;
import gilko.marcin.Auctions.participant.Participant;

@Entity
@Table(name = "auction_participant")
@AssociationOverrides({
	@AssociationOverride(name= "primaryKey.participant",
			joinColumns = @JoinColumn(name = "participant_id")),
	@AssociationOverride(name = "primaryKey.auctionItem",
			joinColumns = @JoinColumn(name = "auction_id"))})
public class AuctionParticipant {
	
	@EmbeddedId
	private AuctionParticipantId primaryKey = new AuctionParticipantId();
	
	public AuctionParticipantId getPrimaryKey() {
		return primaryKey;
	}
	
	public void setPrimaryKey(AuctionParticipantId primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	@Transient
	public Participant getParticipant() {
		return getPrimaryKey().getParticipant();
	}
	
	public void setParticipant(Participant participant) {
		getPrimaryKey().setParticipant(participant);
	}
	
	@Transient
	public AuctionItem getAuctionItem() {
		return getPrimaryKey().getAuctionItem();
	}
	
	public void setAuctionItem(AuctionItem auctionItem) {
		getPrimaryKey().setAuctionItem(auctionItem);
	}
	
	
}
