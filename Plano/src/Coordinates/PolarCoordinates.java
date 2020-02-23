
package Coordinates;

public class PolarCoordinates extends Coordinates{
	
	public PolarCoordinates(double r, double O) {
		super(changePolarToX(r,O), changePolarToY(r,O), r, O);
	}

	/**
	 * @author jorgecalderon
	 * Objective - Change a given r-O Polar coordinate to it's corresponding Cartesian y coordinate
	 * Preconditions - For this method to work the user must have already entered an r-O Polar coordinate
	 * Postconditions - After method the coordinate will have readily available it's y coordinate
	 * Date - 02/12/2020
	 * @param r - Magnitude of trace 
	 * @param O - Polar angle in degrees 
	 * @return - the Cartesian y coordinate of the current displacement
	 */
	
	public static double changePolarToY(double r, double O) {
		if (r < 0) {
			throw new IllegalArgumentException("Magnitude cannot be negative");
		}
		O = Math.toRadians(O);
		double y = r*(Math.sin(O));
		return Math.round(y * 100.0) / 100.0;
	}

	/**
	 * @author jorgecalderon
	 * Objective - Change a given r-O Polar coordinate to it's corresponding Cartesian x coordinate
	 * Preconditions - For this method to work the user must have already entered an r-O Polar coordinate
	 * Postconditions - After method the coordinate will have readily available it's x coordinate
	 * Date - 02/12/2020
	 * @param r - Magnitude of trace 
	 * @param O - Polar angle in degrees 
	 * @return - the Cartesian x coordinate of the current displacement
	 */
	
	public static double changePolarToX(double r, double O) {
		if (r < 0) {
			throw new IllegalArgumentException("Magnitude cannot be negative");
		}
		O = Math.toRadians(O);
		double x = r*Math.cos(O);
		return Math.round(x * 100.0) / 100.0;
	}
}