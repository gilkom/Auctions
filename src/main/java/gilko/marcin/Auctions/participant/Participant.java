package gilko.marcin.Auctions.participant;

import java.util.*;

import javax.persistence.CascadeType;
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
	private Long id;
	@NotBlank(message = "Email is mandatory")
	private String mail;
	@NotBlank(message = "First name is mandatory")
	private String first_name;
	@NotBlank(message = "Last name is mandatory")
	private String last_name;
	@Transient
	private AuctionItem auIt;
	@Transient
	private Set<AuctionParticipant> auctionParticipant = new HashSet<AuctionParticipant>();
	
	public Participant() {}
	
	public Participant(String first_name, String last_name, String mail) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.mail = mail;
		setMyAuctions(new HashSet<>());
	}
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	@OneToMany(mappedBy = "primaryKey.id", cascade = CascadeType.ALL)
	public Set<AuctionParticipant> getAuctionParticipant() {
		return auctionParticipant;
	}

	public void setAuctionParticipant(Set<AuctionParticipant> auctionParticipant) {
		this.auctionParticipant = auctionParticipant;
	}
	public void addAuctionParticipant(AuctionParticipant auctionParticipant) {
		this.auctionParticipant.add(auctionParticipant);
	}
	
	
	
	@Override
	public String toString() {
		return "Participant [first_name=" + first_name + ", last_name=" + last_name + ", mail=" + mail + "] "
				+ "auctionItem";
	}
	

	@Override
	public void update(double curr_price, Participant last_bidder, String notification) {
		System.out.println("Message for " + toString());
		System.out.println("AuctionId: " + auIt.getId() + ", curr_price: " + curr_price + ", last_bidder: " +
						last_bidder + ", notification: " + notification);
		System.out.println("End of message");
		
	}
	public void bid(double new_price, AuctionItem AuctionItem) {
		if(!myAuctions.contains(AuctionItem)) {
			myAuctions.add(AuctionItem);
			this.auIt = AuctionItem;
			AuctionItem.registerObserver(this);
		}
		
		AuctionItem.bid(new_price, this);	
	}
	public void showMyAuctions() {
		for(AuctionItem auction: myAuctions) {
			System.out.println("Id: " + auction.getId() + ", description: " + auction.getDescription()
				+", Curr price: " +  auction.getCurr_price() + ", last bidder: " + auction.getLast_bidder().mail);
		}
	}
	
	public void stopObserve(AuctionItem auctionIt) {
		Participant par = auctionIt.getLast_bidder();
		if(this.mail == par.mail) {
			System.out.println("You can't unregister if your bid is the highest!");
		}else {
			System.out.println("Unregistering from auction");
			auctionIt.unregisterObserver(this);
			myAuctions.remove(auctionIt);
		}
		
	}



}
