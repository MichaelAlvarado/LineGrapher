package Testers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Coordinates.PolarCoordinates;

class PolarCoordinatesTest {

	/**
	 * @author jorgecalderon
	 * Objective - Test the changePolarToY method in PolarCoordinates class
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
		assertEquals(5.0, PolarCoordinates.changePolarToY(5.39,68.2), 
				(0));
		assertEquals(0, PolarCoordinates.changePolarToY(0,0), 
				(0));
		assertEquals(-15.97, PolarCoordinates.changePolarToY(20,-53), 
				(0));
		assertEquals(33.76, PolarCoordinates.changePolarToY(83,24), 
			(0));
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> PolarCoordinates.changePolarToY(-11,10));
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> PolarCoordinates.changePolarToY(-23,70));
	}
	
	/**
	 * @author jorgecalderon
	 * Objective - Test the changePolarToX method in PolarCoordinates class
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
		assertEquals(2.0, PolarCoordinates.changePolarToX(5.39, 68.2), 
				(0));
		assertEquals(0, PolarCoordinates.changePolarToX(0,0), 
				(0));
		assertEquals(0, PolarCoordinates.changePolarToX(10,270), 
				(0));
		assertEquals(0, PolarCoordinates.changePolarToX(5,90), 
				(0));
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> PolarCoordinates.changePolarToX(-11,10));
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> PolarCoordinates.changePolarToX(-23,70));
	}

}