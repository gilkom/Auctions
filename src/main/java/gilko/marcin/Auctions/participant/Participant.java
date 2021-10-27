package gilko.marcin.Auctions.participant;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import gilko.marcin.Auctions.auction.Auction;
import gilko.marcin.Auctions.auction.AuctionItem;
import gilko.marcin.Auctions.auction_participant.AuctionParticipant;

@Entity
@Table(name = "participant")
public class Participant implements Observator{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long participant_id;
	@NotBlank(message = "Email is mandatory")
	private String mail;
	@NotBlank(message = "First name is mandatory")
	private String first_name;
	@NotBlank(message = "Last name is mandatory")
	private String last_name;
	@Transient
	private AuctionItem auIt;

	@OneToMany(mappedBy = "primaryKey.participant",
			cascade = CascadeType.PERSIST)
	private Set<AuctionParticipant> auctionParticipants = new HashSet<AuctionParticipant>();
	
	public Participant() {}
	
	public Participant(String first_name, String last_name, String mail) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.mail = mail;
	}
	public Long getParticipant_id() {
		return participant_id;
	}
	
	public void setParticipant_id(Long id) {
		this.participant_id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public Set<AuctionParticipant> getAuctionParticipants() {
		return auctionParticipants;
	}

	public void setAuctionParticipants(Set<AuctionParticipant> auctionParticipants) {
		this.auctionParticipants = auctionParticipants;
	}
	public void addAuctionParticipant(AuctionParticipant auctionParticipant) {
		this.auctionParticipants.add(auctionParticipant);
	}
	
	@Override
	public String toString() {
		return "Participant [first_name=" + first_name + ", last_name=" + last_name + ", mail=" + mail + "] "
				+ "auctionItem";
	}
	

	@Override
	public void update(double curr_price, Participant last_bidder, String notification) {
		System.out.println("Message for " + toString());
		System.out.println("AuctionId: " + auIt.getAuction_item_id() + ", curr_price: " + curr_price + ", last_bidder: " +
						last_bidder + ", notification: " + notification);
		System.out.println("End of message");
		
	}
	public void bid(double new_price, AuctionItem auctionItem) {
		if(auctionParticipants.contains(auctionItem)) {
			System.out.println("#####Do sprawdzenia i dorobienia");
		}
		/*if(auctionParticipant.contains(AuctionItem)!myAuctions.contains(AuctionItem)) {
			myAuctions.add(AuctionItem);
			this.auIt = AuctionItem;
			AuctionItem.registerObserver(this);
		}*/
		
		auctionItem.bid(new_price, this);	
	}
	public void showMyAuctions() {
		for(AuctionParticipant auction: auctionParticipants) {
			System.out.println("Id: " + auction.getAuctionItem().getAuction_item_id() + ", description: " + auction.getAuctionItem().getDescription()
				+", Curr price: " +  auction.getAuctionItem().getCurr_price() + ", last bidder: " + auction.getAuctionItem().getLast_bidder().mail);
		}
	}
	
	public void stopObserve(AuctionItem auctionIt) {
		Participant par = auctionIt.getLast_bidder();
		if(this.mail == par.mail) {
			System.out.println("You can't unregister if your bid is the highest!");
		}else {
			System.out.println("Unregistering from auction");
			auctionIt.unregisterObserver(this);
			auctionParticipants.remove(auctionIt);
			//myAuctions.remove(auctionIt);
		}
		
	}



}
