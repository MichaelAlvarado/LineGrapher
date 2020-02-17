package Testers;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Coordinates.CartesianCoordinates;

class CartesianCoordinatesTest {

	
	@Test
	void testCartesianCoordinates() {
		assertEquals(68.2, CartesianCoordinates.changeCartesianToAngle(2.0,5.0), 
				(0));
	}
	
	
	@Test
	void testCartesianCoordinates2() {
		assertEquals(5.39, CartesianCoordinates.changeCartesianToMagnitud(2.0, 5.0), 
				(0));
	}

}