package NotificateAlert;

import Stocks.StockObservables;

public class MobileAlertObserver implements NotificationAlertObserver{

	StockObservables observable;
	String mobileNo;
	
	public MobileAlertObserver(StockObservables observable, String mobileNo) {
		this.observable = observable;
		this.mobileNo = mobileNo;
	}

	@Override
	public void update() {
		int stockCount = observable.getStockCount();
		if(stockCount > 0) {
			sendMsg(mobileNo, "Stock is Up, Hurry Up!");
		}
	}
	
	public void sendMsg(String mobileNo, String msg) {
		System.out.println("Message: " + msg+ " is send on MobileNo: " + mobileNo);
	}

}
