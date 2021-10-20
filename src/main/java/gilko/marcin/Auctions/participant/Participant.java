package gilko.marcin.Auctions.participant;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import gilko.marcin.Auctions.auction.Auction;
import gilko.marcin.Auctions.auction.AuctionItem;

@Entity
@Table(name = "participants")
public class Participant implements Observator{
	private String mail;
	private String firstName;
	private String secondName;
	@Transient
	private AuctionItem auIt;
	@Transient
	private Set<AuctionItem> myAuctions;
	
	public Participant() {}
	
	public Participant(String firstName, String secondName, String mail) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.mail = mail;
		setMyAuctions(new HashSet<>());
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public Set<AuctionItem> getMyAuctions() {
		return myAuctions;
	}

	public void setMyAuctions(Set<AuctionItem> myAuctions) {
		this.myAuctions = myAuctions;
	}
	@Override
	public String toString() {
		return "Participant [firstName=" + firstName + ", secondName=" + secondName + ", mail=" + mail + "] "
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
