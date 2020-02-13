package Coordinates;

public class CartesianCoordinates extends Coordinates{
	
	public CartesianCoordinates(double x, double y) {
		super(x,y,changeCartesianToMagnitud(x,y),changeCartesianToAngle(x,y));
	}
	
	public static double changeCartesianToAngle(double x2, double y2) {
		double O = Math.atan((y2/x2));
		O = Math.toDegrees(O);
		return Math.round(O * 100.0) / 100.0;
	}
	public static double changeCartesianToMagnitud(double x2, double y2) {
		double r =  Math.sqrt(x2*x2 + y2*y2);
		return Math.round(r * 100.0) / 100.0;
	}

	
}