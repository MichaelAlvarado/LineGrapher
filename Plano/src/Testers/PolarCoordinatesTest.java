package Testers;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Coordinates.PolarCoordinates;

class PolarCoordinatesTest {

	
	@Test
	void testCartesianCoordinates() {
		assertEquals(5.0, PolarCoordinates.changePolarToY(5.39,68.2), 
				(0));
	}
	
	
	@Test
	void testCartesianCoordinates2() {
		assertEquals(2.0, PolarCoordinates.changePolarToX(5.39, 68.2), 
				(0));
	}

}