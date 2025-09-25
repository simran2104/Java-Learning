package Stocks;

import NotificateAlert.NotificationAlertObserver;

public interface StockObservables {
	public void add(NotificationAlertObserver obj);
	public void remove(NotificationAlertObserver obj);
	public void notifySubscribers();
	public void setStockCount(int newStockCount);
	public int getStockCount();
}
