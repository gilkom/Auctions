package gilko.marcin.Auctions.auction;

import java.util.TimerTask;

public class Time extends TimerTask{
	public static int i = 1;
	public void run() {
		System.out.println("taskkk = " + i++);
	}

}
