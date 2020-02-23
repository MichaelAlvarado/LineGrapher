package Testers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Coordinates.CartesianCoordinates;


class CartesianCoordinatesTest {

	
	/**
	 * @author jorgecalderon
	 * Objective - Test the changeCartesianToAngleMethod in CartesianCoordinates class
	 * Preconditions - Think about the worst cases possible to test the method's functionality. 
	 * Ex: Negative and Positive Numbers, Ceros, etc...
	 * Postconditions - If all tests pass it means the methods are working correctly for the specified
	 * test cases.
	 * Date - 02/22/2020
	 * @param  - N/A 
	 * @param  - N/A 
	 * @return - N/A
	 */
	
	@Test
	void testCartesianCoordinates() {
		assertEquals(68.2, CartesianCoordinates.changeCartesianToAngle(2.0,5.0), 
				(0));
		assertEquals(24.78, CartesianCoordinates.changeCartesianToAngle(-13,-6), 
				(0));
		assertEquals(0, CartesianCoordinates.changeCartesianToAngle(2.0,0), 
				(0));
		assertEquals(-73.3, CartesianCoordinates.changeCartesianToAngle(-3.0,10.0), 
				(0));
	}
	
	/**
	 * @author jorgecalderon
	 * Objective - Test the changeCartesianToMagnitude in CartesianCoordinates class
	 * Preconditions - Think about the worst cases possible to test the method's functionality. 
	 * Ex: Negative and Positive Numbers, Ceros, etc...
	 * Postconditions - If all tests pass it means the methods are working correctly for the specified
	 * test cases.
	 * Date - 02/22/2020
	 * @param  - N/A 
	 * @param  - N/A 
	 * @return - N/A
	 */
	
	@Test
	void testCartesianCoordinates2() {
		assertEquals(5.39, CartesianCoordinates.changeCartesianToMagnitud(2.0, 5.0), 
				(0));
		assertEquals(0, CartesianCoordinates.changeCartesianToMagnitud(0, 0), 
				(0));
		assertEquals(18.03, CartesianCoordinates.changeCartesianToMagnitud(-10.0, -15.0), 
				(0));
	}

}