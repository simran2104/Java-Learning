package Toppings;

import BasePizza.BasePizza;

public class Mushroom extends Toppings{
	BasePizza basePizza;
	
	public Mushroom(BasePizza basePizza) {
		this.basePizza = basePizza;
	}
	
	@Override
	public int Cost() {
		return this.basePizza.Cost() + 5;
	}

}
