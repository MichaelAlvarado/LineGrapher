package Coordinates;

public class CartesianCoordinates extends Coordinates{
	
	public CartesianCoordinates(double x, double y) {
		super(x,y,changeCartesianToMagnitud(x,y),changeCartesianToAngle(x,y));
	}
	
	/**
	 * @author jorgecalderon
	 * Objective - Change a given x-y coordinate to it's corresponding Polar coordinate angle
	 * Preconditions - For this method to work the user must have already entered an x-y coordinate
	 * Postconditions - After method the coordinate will have readily available it's angle in degrees
	 * Date - 02/12/2020
	 * @param x2 - the x coordinate 
	 * @param y2 - y coordinate 
	 * @return - the angle in degrees of the current displacement
	 */
	
	public static double changeCartesianToAngle(double x2, double y2) {
//		if ((x2 == 0) && (y2 != 0)){
//			throw new ArithmeticException("Division by cero results in "
//					+ "indefnitie number");
//		}
		double O = Math.atan((y2/x2));
		O = Math.toDegrees(O);
		return Math.round(O * 100.0) / 100.0;
	}
	
	/**
	 * @author jorgecalderon
	 * Objective - Change a given x-y coordinate to it's corresponding Polar coordinate magnitude
	 * Preconditions - For this method to work the user must have already entered an x-y coordinate
	 * Postconditions - After method the coordinate will have readily available it's Polar coordinate 
	 * magnitude
	 * Date - 02/12/2020
	 * @param x2 - the x coordinate 
	 * @param y2 - y coordinate 
	 * @return - the magnitude of the current displacement
	 */
	
	public static double changeCartesianToMagnitud(double x2, double y2) {
		double r =  Math.sqrt(x2*x2 + y2*y2);
		return Math.round(r * 100.0) / 100.0;
	}
}