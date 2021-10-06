package gilko.marcin.Auctions.auction;

public class AuctionItem {
	private static int count =0;
	
	public int id;
	public String description;
	public int quantity;
	public double start_price;
	public double min_price;
	public double time;
	public double curr_price;
	
	public AuctionItem() {};
	public AuctionItem(String desciption, int quantity, double start_price,
					double min_price, double time, double curr_price) {
		this.id = (++count);
		this.description = desciption;
		this.quantity = quantity;
		this.start_price = start_price;
		this.min_price = min_price;
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
	public void setTime(double time) {
		this.time = time;
	}
	public double getCurr_price() {
		return curr_price;
	}
	public void setCurr_price(double curr_price) {
		this.curr_price = curr_price;
	}
	
	
	
}
