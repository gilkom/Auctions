package gilko.marcin.Auctions.participant;

import gilko.marcin.Auctions.auction.Auction;

public class Participant implements Observator{
	
	private String firstName;
	private String secondName;
	private String mail;
	private Auction AuctionItem;
	
	public Participant() {}
	
	public Participant(String firstName, String secondName, String mail) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.mail = mail;
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
	@Override
	public String toString() {
		return "Participant [firstName=" + firstName + ", secondName=" + secondName + ", mail=" + mail + "]";
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	public void bid(double new_price, Auction AuctionItem) {
		AuctionItem.registerObserver(this);
		AuctionItem.bid(new_price, this);
		
		
	}

}
