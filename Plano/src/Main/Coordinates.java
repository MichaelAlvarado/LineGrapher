package Main;

public class Coordinates {
	
	int x, y; //Cartasian Coordinates
	int r, O; //Polar Coordinates

	public Coordinates (int x, int y, int r, int O){
		this.x = x;
		this.y = y;
		this.r = r;
		this.O = O;
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
}
