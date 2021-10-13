package gilko.marcin.Auctions.participant;

import java.util.*;


import gilko.marcin.Auctions.auction.Auction;
import gilko.marcin.Auctions.auction.AuctionItem;

public class Participant implements Observator{
	
	private String firstName;
	private String secondName;
	private String mail;
	private AuctionItem auIt;
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
	public void update(double curr_price, Participant last_bidder) {
		System.out.println("Message for " + toString());
		System.out.println("AuctionId: " + auIt.getId() + ", curr_price: " + curr_price + ", last_bidder: " +
						last_bidder);
		System.out.println("End of message");
		
	}
	public void bid(double new_price, AuctionItem AuctionItem) {
		myAuctions.add(AuctionItem);
		this.auIt = AuctionItem;
		AuctionItem.registerObserver(this);
		AuctionItem.bid(new_price, this);	
	}
	
	public void stopObserve() {
		
	}



}
