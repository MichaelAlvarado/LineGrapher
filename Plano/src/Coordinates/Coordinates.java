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
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public double getO() {
		return O;
	}

	public void setO(int o) {
		O = o;
	}
	
}
