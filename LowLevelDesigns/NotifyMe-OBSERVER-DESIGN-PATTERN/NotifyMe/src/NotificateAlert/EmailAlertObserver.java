package NotificateAlert;

import Stocks.StockObservables;

public class EmailAlertObserver implements NotificationAlertObserver{
	
	StockObservables observable;
	String email;
	
	public EmailAlertObserver(StockObservables observable, String email) {
		this.observable = observable;
		this.email = email;
	}

	@Override
	public void update() {
		int stockCount = observable.getStockCount();
		if(stockCount > 0) {
			sendEmail(email, "Stock is Up, Hurry Up!");
		}
	}
	
	public void sendEmail(String emailId, String msg) {
		System.out.println("Message: " + msg+ " is send on email Id: " + emailId);
	}

}
