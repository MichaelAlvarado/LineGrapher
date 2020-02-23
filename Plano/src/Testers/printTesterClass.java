package Testers;

import Coordinates.CartesianCoordinates;
import Coordinates.Coordinates;
import Coordinates.PolarCoordinates;

/**
 * @author jorgecalderon
 * Objective - Simple tester method used in early development to test if the methods in both 
 * CartesianCoordinates and PolarCoordinates were working properly
 * Preconditions - N/A
 * Postconditions - N/A
 * Date - 02/12/2020
 * @param  - N/A 
 * @param  - N/A 
 * @return - N/A
 */

public class printTesterClass {

	public static void main(String[] args) {
		
		Coordinates test = new PolarCoordinates(5.39, 68.20);
		
		System.out.println("The x coordinate in Cartesian is: " + test.getX());
		System.out.println("The y coordintate in Cartesian is: " + test.getY());
		
		Coordinates test2 = new CartesianCoordinates(2.0, 5.0);
		
		System.out.println("The r magnitude in Polar is: " + test2.getR());
		System.out.println("The angle in Polar is: " + test2.getO());
	}
}