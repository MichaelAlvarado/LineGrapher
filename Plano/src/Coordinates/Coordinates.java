package Coordinates;

public class Coordinates {
	
	double x, y; //Cartesian Coordinates
	double r, O; //Polar Coordinates

	public Coordinates (double x, double y, double r, double O){
		this.x = x;
		this.y = y;
		this.r = r;
		this.O = O;
	}


	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		this.r = CartesianCoordinates.changeCartesianToMagnitud(x, y);
		this.O = CartesianCoordinates.changeCartesianToAngle(x, y);
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		this.r = CartesianCoordinates.changeCartesianToMagnitud(x, y);
		this.O = CartesianCoordinates.changeCartesianToAngle(x, y);
	}

	public double getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
		this.x = PolarCoordinates.changePolarToX(r, O);
		this.y = PolarCoordinates.changePolarToY(r, O);
	}

	public double getO() {
		return O;
	}

	public void setO(int o) {
		this.O = o;
		this.x = PolarCoordinates.changePolarToX(r, O);
		this.y = PolarCoordinates.changePolarToY(r, O);
	}
	
}
