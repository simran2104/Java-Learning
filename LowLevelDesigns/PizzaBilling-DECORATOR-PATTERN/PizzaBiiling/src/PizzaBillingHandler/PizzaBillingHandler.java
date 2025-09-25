package PizzaBillingHandler;

import BasePizza.BasePizza;
import BasePizza.FarmHouse;
import BasePizza.VegDelight;
import Toppings.ExtraCheese;
import Toppings.Mushroom;

public class PizzaBillingHandler {
	
	public static void main(String args[]) {
		BasePizza pizza1 = new VegDelight();
		BasePizza pizza2 = new ExtraCheese(new FarmHouse());
		BasePizza pizza3 = new Mushroom(new ExtraCheese(new FarmHouse()));
		
		System.out.println("Cost of Pizza 1: " + pizza1.Cost());
		System.out.println("Cost of Pizza 2: " + pizza2.Cost());
		System.out.println("Cost of Pizza 3: " + pizza3.Cost());
	}
	
}
