package ShapeFactory;

import Shapes.Circle;
import Shapes.Shapes;
import Shapes.Square;

public class ShapeFactory {
	public void getShape(String input) {
		Shapes shape;
		
		switch(input) {
			case "CIRCLE": 
				shape = new Circle();
				shape.draw();
				break;
			case "SQUARE":
				shape = new Square();
				shape.draw();
				break;
			default:
				System.out.println("Different Shape.");
			}
	}
}
