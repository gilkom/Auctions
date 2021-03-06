package gilko.marcin.Auctions.model.auction_participant;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import gilko.marcin.Auctions.model.auction.AuctionItem;
import gilko.marcin.Auctions.model.participant.Participant;

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
	
	private boolean active;
	
	public AuctionParticipant() {}
	
	public AuctionParticipant(AuctionParticipantId auctionParticipantId) {
		this.active = true;
		this.primaryKey = auctionParticipantId;
		
	}
	
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
