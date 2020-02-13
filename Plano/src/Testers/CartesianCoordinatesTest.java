package Testers;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Coordinates.CartesianCoordinates;

class CartesianCoordinatesTest {

	@SuppressWarnings("deprecation")
	@Test
	void testCartesianCoordinates() {
		System.out.println("This test ran.");
		assertEquals(CartesianCoordinates.changeCartesianToAngle(2.0,5.0), 
				(Math.round(68.19859051 * 100.0) / 100.0));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	void testCartesianCoordinates2() {
		assertEquals(CartesianCoordinates.changeCartesianToMagnitud(2.0, 5.0), 
				(Math.round(5.385164807 * 100.0) / 100.0));
	}

}