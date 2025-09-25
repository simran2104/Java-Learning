package ShapeFactoryHandler;

import ShapeFactory.ShapeFactory;

public class ShapeFactoryHandler {
	public static void main(String args[]) {
		ShapeFactory shapeFactory = new ShapeFactory();
		shapeFactory.getShape("CIRCLE");
		shapeFactory.getShape("SQUARE");
		shapeFactory.getShape("RECTANGLE");
	}
}
