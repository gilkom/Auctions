package gilko.marcin.Auctions.auction;

import gilko.marcin.Auctions.participant.Observator;
import gilko.marcin.Auctions.participant.Participant;
import java.util.Timer;

public class AuctionItem implements Auction{
	private static int count =0;
	
	public int id;
	public String description;
	public int quantity;
	public double start_price;
	public double min_price;
	public int time;
	public double curr_price;
	public int time_left;
	
	public AuctionItem() {};
	public AuctionItem(String desciption, int quantity, double start_price,
					double min_price, int time, double curr_price) {
		this.id = (++count);
		this.description = desciption;
		this.quantity = quantity;
		this.start_price = start_price;
		this.min_price = min_price;
		this.time = time;
		this.curr_price = curr_price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getStart_price() {
		return start_price;
	}
	public void setStart_price(double start_price) {
		this.start_price = start_price;
	}
	public double getMin_price() {
		return min_price;
	}
	public void setMin_price(double min_price) {
		this.min_price = min_price;
	}
	public double getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public double getCurr_price() {
		return curr_price;
	}
	public void setCurr_price(double curr_price) {
		this.curr_price = curr_price;
	}
	

	@Override
	public void startAuction() {
		long current = System.currentTimeMillis();
		System.out.println("time_left=" + time);
		time_left = time;
		while(time_left >=0) {
			if(System.currentTimeMillis()- current > 1000) {
				System.out.println("id = " + id + ":" +time_left--);
				
				current = System.currentTimeMillis();
			}
		}
	System.out.println("Bam");
		
	}
	@Override
	public void stopAuction() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void registerObserver(Observator o) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void unregisterObserver(Observator o) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString() {
		return "AuctionItem [id=" + id + ", description=" + description + ", quantity=" + quantity + ", start_price="
				+ start_price + ", min_price=" + min_price + ", time=" + time + ", curr_price=" + curr_price
				+"]";
	}
	
	
	
	
}
