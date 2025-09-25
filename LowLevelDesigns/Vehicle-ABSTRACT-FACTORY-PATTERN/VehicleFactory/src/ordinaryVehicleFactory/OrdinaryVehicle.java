package ordinaryVehicleFactory;

import luxuryVehicleFactory.LuxuryVehicle;
import vehicleFactory.VehicleFactory;

public class OrdinaryVehicle implements VehicleFactory{
	LuxuryVehicle luxury;

	@Override
	public void getVehicle(String input) {
		switch(input) {
			case "BMW":
				luxury = new Bmw();
				System.out.println(luxury.Cost());
				break;
			case "SWIFT":
				
		}
	}

}
