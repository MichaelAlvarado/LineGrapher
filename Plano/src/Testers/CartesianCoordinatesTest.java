package Testers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Coordinates.CartesianCoordinates;


class CartesianCoordinatesTest {

	
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
//		Assertions.assertThrows(ArithmeticException.class, 
//				() -> CartesianCoordinates.changeCartesianToAngle(0,10));
	}
	
	
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