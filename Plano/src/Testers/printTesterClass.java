package Testers;

import Coordinates.CartesianCoordinates;
import Coordinates.Coordinates;
import Coordinates.PolarCoordinates;

public class printTesterClass {

	public static void main(String[] args) {
		
		Coordinates test = new PolarCoordinates(5.39, 68.20);
		
		System.out.println("The x coordinate in Cartesian is: " + test.getX());
		System.out.println("The y coordintate in Cartesian is: " + test.getY());
		
		Coordinates test2 = new CartesianCoordinates(2.0, 5.0);
		
		System.out.println("The r magnitude in Polar is: " + test2.getR());
		System.out.println("The angle in Polar is: " + test2.getO());
		
		int a = 5;
		double b = 5.0/10.0;
		
		System.out.println(b);
	}

}