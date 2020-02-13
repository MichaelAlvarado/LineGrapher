package Testers;

import Coordinates.CartesianCoordinates;
import Coordinates.PolarCoordinates;

public class printTesterClass {

	public static void main(String[] args) {
		
		PolarCoordinates test = new PolarCoordinates(5.39, 68.20);
		double x = PolarCoordinates.changePolarToX(test.getR(), test.getO());
		double y = PolarCoordinates.changePolarToY(test.getR(), test.getO());
		
		System.out.println("The x coordinate in Cartesian is: " + x);
		System.out.println("The y coordintate in Cartesian is: " + y);
		
		CartesianCoordinates test2 = new CartesianCoordinates(2.0, 5.0);
		double r = CartesianCoordinates.changeCartesianToMagnitud(test2.getX(), test2.getY());
		double O = CartesianCoordinates.changeCartesianToAngle(test2.getX(), test2.getY());
		
		System.out.println("The r magnitude in Polar is: " + r);
		System.out.println("The angle in Polar is: " + O);
		
	}

}