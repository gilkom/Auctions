package gilko.marcin.Auctions.model.auction;

import gilko.marcin.Auctions.model.auction_participant.AuctionParticipant;
import gilko.marcin.Auctions.model.notification.Notification;
import gilko.marcin.Auctions.model.participant.Observator;
import gilko.marcin.Auctions.model.participant.Participant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "auction")
public class AuctionItem implements Auction{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auction_id")
	private long auction_item_id;
	
	private long owner;
	@NotBlank(message = "Name is mandatory")
	private String name;
	@NotBlank(message = "Description is mandatory")
	private String description;
	@NotNull(message = "Quantity is mandatory")
	private int quantity;
	@NotNull(message = "Start price is mandatory")
	private double start_price;
	@NotNull(message = "Minimum price is mandatory")
	private double min_price;
	@NotNull(message = "Time is mandatory")
	private int time;
	private double curr_price;

	private long last_bidder;
	@Transient
	private int counter;
	private LocalDateTime start_time;
	@Transient
	private Timer timer;
	@Transient
	private TimerTask task;
	@Transient
	private ArrayList<Observator> observers;
	
	@OneToMany(mappedBy = "primaryKey.auctionItem", cascade = CascadeType.ALL)
	private Set<AuctionParticipant> auctionParticipants = new HashSet<AuctionParticipant>();
	
	@OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
	private Set<Notification> notifications;
	
	public AuctionItem() {};
	
	public AuctionItem(String name, String desciption, int quantity, double start_price,
					double min_price, int time) {
		observers = new ArrayList<Observator>();
		this.name = name;
		this.description = desciption;
		this.quantity = quantity;
		this.start_price = start_price;
		this.min_price = min_price;
		this.time = time;
		this.curr_price = start_price;
		this.timer = new Timer();
		this.counter = 0;
		this.start_time = LocalDateTime.now();
		System.out.println("start_time: " + this.start_time);
	}

	public long getAuction_item_id() {
		return auction_item_id;
	}
	public void setAuction_item_id(long auction_item_id) {
		this.auction_item_id = auction_item_id;
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
	public int getTime() {
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
	public long getLast_bidder() {
		return last_bidder;
	}
	public void setLast_bidder(long last_bidder) {
		this.last_bidder = last_bidder;
	}
	
	public Set<AuctionParticipant> getAuctionParticipants(){
		return auctionParticipants;
	}
	
	public void setAuctionParticipants(Set<AuctionParticipant> auctionParticipants) {
		this.auctionParticipants = auctionParticipants;
	}
	
	public void addAuctionParticipants(AuctionParticipant auctionParticipant) {
		this.auctionParticipants.add(auctionParticipant);
	}
	
	public Set<Notification> getNotifications(){
		return notifications;
	}
	
	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}
	
	public void addNotifications(Notification notification) {
		this.notifications.add(notification);
	}

	@Override
	public void startAuction() {
		notifyObservator("Start Auction");
		task = new TimerTask() {
			@Override
			public void run() {
				long result = time-(counter);
				System.out.println("Time left counter id: " + auction_item_id + ", =" + result);
				counter++;
				if(counter >= time) {
					System.out.println("end of " + auction_item_id);
					notifyObservator("End of auction: " + auction_item_id);
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
	public void bid(double new_price, long bidder) {
		if(curr_price < new_price) {
			curr_price = new_price;
			last_bidder = bidder;
			System.out.println("podano nową cenę: " + curr_price + " przez: " + last_bidder);
		}
		notifyObservator("Bidding");
	}
	
	@Override
	public String toString() {
		return "AuctionItem [id=" + auction_item_id + ", description=" + description + ", quantity=" + quantity + ", start_price="
				+ start_price + ", min_price=" + min_price + ", time=" + time + ", curr_price=" + curr_price
				+"]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getStart_time() {
		return start_time;
	}

	public void setStart_time(LocalDateTime start_time) {
		this.start_time = start_time;
	}

	public long getOwner() {
		return owner;
	}

	public void setOwner(long owner) {
		this.owner = owner;
	}
	
	
	
	
}
