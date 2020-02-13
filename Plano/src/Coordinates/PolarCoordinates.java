
package Coordinates;

public class PolarCoordinates extends Coordinates{
	
	public PolarCoordinates(double r, double O) {
		super(changePolarToX(r,O), changePolarToY(r,O), r, O);
	}

	
	public static double changePolarToY(double r, double O) {
		double y = r*Math.sin(O);
		return Math.round(y * 100.0) / 100.0;
	}

	public static double changePolarToX(double r, double O) {
		double x = r*Math.cos(O);
		return Math.round(x * 100.0) / 100.0;
	}
	
}