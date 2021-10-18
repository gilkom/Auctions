package gilko.marcin.Auctions.auction;

import gilko.marcin.Auctions.participant.Observator;

import gilko.marcin.Auctions.participant.Participant;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "auction")
public class AuctionItem implements Auction{
	
	private ArrayList<Observator> observers;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String description;
	private int quantity;
	private double start_price;
	private double min_price;
	private int time;
	private double curr_price;
	private Participant last_bidder;
	private int counter;
	
	private Timer timer;
	private TimerTask task;
	
	public AuctionItem() {};
	
	public AuctionItem(String desciption, int quantity, double start_price,
					double min_price, int time, double curr_price) {
		observers = new ArrayList<Observator>();
		this.description = desciption;
		this.quantity = quantity;
		this.start_price = start_price;
		this.min_price = min_price;
		this.time = time;
		this.curr_price = curr_price;
		this.timer = new Timer();
		this.counter = 0;
	}

	public long getId() {
		return id;
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
	public Participant getLast_bidder() {
		return last_bidder;
	}
	public void setLast_bidder(Participant last_bidder) {
		this.last_bidder = last_bidder;
	}

	@Override
	public void startAuction() {
		notifyObservator("Start Auction");
		task = new TimerTask() {
			@Override
			public void run() {
				long result = time-(counter);
				System.out.println("Time left counter id: " + id + ", =" + result);
				counter++;
				if(counter >= time) {
					System.out.println("end of " + id);
					notifyObservator("End of auction: " + id);
					cancel();
				}

			}
		};
		timer = new Timer("Auction");
		timer.schedule(task,time, 1000L);

			System.out.println("start_time=" + time);
			System.out.println("startAuction");


	}
	@Override
	public void stopAuction() {
		System.out.println("stopAuction");
		notifyObservator("Stop Auction");
		
	}
	@Override
	public void checkTimeLeft() {
		long result = time-(counter);
		System.out.println("Time left to end the auction = " + result);
	}
	
	@Override
	public void notifyObservator(String notification) {
		System.out.println(" - ");
		System.out.println("Powiadomienia:");
		for(int i = 0; i < observers.size(); i++) {
			Observator Obs = observers.get(i);
			Obs.update(curr_price,last_bidder, notification);
		}
		
	}
	@Override
	public void registerObserver(Observator o) {
		observers.add(o);
		System.out.println("dodano obserwatora: " + o);
	}
	@Override
	public void unregisterObserver(Observator o) {
		int i = observers.indexOf(o);
		if(i >=0) {
			observers.remove(i);
		}
		System.out.println("usunięto obserwatora: " + o);
	}
	@Override
	public void bid(double new_price, Participant bidder) {
		if(curr_price < new_price) {
			curr_price = new_price;
			last_bidder = bidder;
			System.out.println("podano nową cenę: " + curr_price + " przez: " + last_bidder);
		}
		notifyObservator("Bidding");
	}
	
	@Override
	public String toString() {
		return "AuctionItem [id=" + id + ", description=" + description + ", quantity=" + quantity + ", start_price="
				+ start_price + ", min_price=" + min_price + ", time=" + time + ", curr_price=" + curr_price
				+"]";
	}
	
	
	
	
}
