package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import Coordinates.CartesianCoordinates;
import Coordinates.Coordinates;
import Coordinates.PolarCoordinates;

/**
 * 
 * @author Michael Alvarado
 * Date - 11/Feb/2020
 * This class is the canvas where the Plane is going to be drawn at
 * This class can display the Cartesian or Polar Plane (use changePlane method to switch between planes)
 * The plane can the scaled to allow the user to see all points (use changeScale method)
 * On the upper right side of the plane it will display the current point coordinates, with changeCoordinates method you could see coordinates in Polar or Cartesian format
 * 
 *
 */
public class Plane extends Canvas{

	ArrayList<ArrayList<Coordinates>> lines; //Where all the lines are
	Coordinates currentPoint; //last coordinate 
	ArrayList<Coordinates> currentLine; //last line 
	int pointWidth, pointHeight; //points of coordinates
	int xGap, yGap; //this is the distance of line to line
	int xOrigin, yOrigin; //Pixel position on canvas origin
	int scale; 
	boolean isCartesianPlane;
	boolean isCartesianCoordinate;
	Color cP, cL, pP, pL; //Color for c=current p=previous P=Point L=Line

	public Plane() {
		currentLine = new ArrayList<Coordinates>();
		currentLine.add(new CartesianCoordinates(0,0));
		lines = new ArrayList<ArrayList<Coordinates>>();
		lines.add(currentLine);
		pointWidth = 10;
		pointHeight = 10;
		scale = 1;
		isCartesianPlane = true;
		isCartesianCoordinate = true;
		cP = Color.BLUE;
		cL = Color.MAGENTA;
		pP = Color.RED;
		pL = new Color(20,198,5); //Green

	}

	@Override 
	public void paint(Graphics g) {
		xGap = this.getWidth()/16; //wide of rectangles
		yGap = this.getHeight()/16; //height of rectangles
		xOrigin = this.getWidth()/2; //position in canvas of point x origin
		yOrigin = this.getHeight()/2; //position in canvas of point y origin

		//draw squares Cartesian Plane
		if(isCartesianPlane) {
			g.setColor(Color.LIGHT_GRAY);
			//It draws negative and positive axis separately to make sure they are aligned with axis X and Y
			for(int i = 1; i < 8; i++) {
				g.drawLine((xOrigin + i*xGap), 0, (xOrigin + i*xGap), this.getHeight()); //draw positive x lines
				g.drawLine((xOrigin - i*xGap), 0, (xOrigin - i*xGap), this.getHeight()); //draw negative x lines
				g.drawLine(0, (yOrigin - i*yGap), this.getWidth(), (yOrigin - i*yGap)); //draw positive y lines
				g.drawLine(0, (yOrigin + i*yGap), this.getWidth(), (yOrigin + i*yGap)); //draw negative y lines
			}
		}

		//draw polar Plane
		else {
			g.setColor(Color.LIGHT_GRAY);
			//Draw the magnitudes circles
			for(int i = 1; i < 8; i++) {
				g.drawOval(xOrigin - (i*xGap), yOrigin - (i*yGap), 2*i*xGap, 2*i*yGap);
			}
			//Draw the angle lines
			Coordinates angle = new PolarCoordinates(7,30);
			for(int i = 1; i <= 12; i++) {
				angle.setO(30*i);
				int x = ((int)(angle.getX()*xGap)+xOrigin);
				int y = ((int)(-angle.getY()*yGap)+yOrigin);
				g.drawLine(xOrigin, yOrigin, x, y);
				g.drawString(String.valueOf(angle.getO()), x, y);
			}
		}

		//draw X and Y AXIS
		g.setColor(Color.BLACK);
		g.drawLine(0, yOrigin, this.getWidth(), yOrigin); //X Axis
		g.drawLine(xOrigin, 0, xOrigin, this.getHeight()); //Y Axis

		//draw coordinates Number
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		for(int s = 0; s < 8; s++) {
			//If is polar plane only draw one number per magnitude
			g.drawString(String.valueOf(s*this.scale), (xOrigin + s*xGap), yOrigin); //draw positive X coordinate 
			if(isCartesianPlane) {
				g.drawString(String.valueOf(-s*this.scale), (xOrigin - s*xGap), yOrigin); //draw negative X coordinate 
				g.drawString(String.valueOf(s*this.scale), xOrigin, (yOrigin - s*yGap)); //draw positive Y coordinate 
				g.drawString(String.valueOf(-s*this.scale), xOrigin, (yOrigin + s*yGap)); //draw negative Y coordinate 
			}
		}

		//Draw Points
		g.setColor(pP);
		currentPoint = currentLine.get(currentLine.size()-1);
		for(Coordinates p: currentLine) {
			if(p.equals(currentPoint)) {
				g.setColor(cP); //Draw current Point different Color
			}
			g.fillOval((printCoordinatesX(p)-(pointWidth/2)),(printCoordinatesY(p)-(pointHeight/2)), pointWidth, pointHeight);
		}

		//Draw lines from point to point
		g.setColor(pL);//Color of Lines
		for(ArrayList<Coordinates> coordinates: lines) {
			if(coordinates.equals(currentLine)) {
				g.setColor(cL); //Color of Current Line
			}
			for(int i = 1; i < coordinates.size(); i++) {
				Coordinates p0 = coordinates.get(i-1);
				Coordinates p1 = coordinates.get(i);
				g.drawLine(printCoordinatesX(p0), printCoordinatesY(p0), printCoordinatesX(p1), printCoordinatesY(p1));
			}
		}

		//Draw panel with coordinates
		g.setColor(new Color(0,0,0,100));
		g.fillRect(this.getWidth()-200, 0, 200, 30);
		//Draw coordinates point on panel
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.setColor(cP);
		if(isCartesianCoordinate)
			g.drawString("( " + currentPoint.getX() + " , " + currentPoint.getY() + " )", this.getWidth()-200, 20);
		else
			g.drawString("( " + currentPoint.getR() + " , " + currentPoint.getO() + " )", this.getWidth()-200, 20);

	}

	/**
	 * @author Michael Alvarado
	 * Date - 11/Feb/2020
	 * Objective - This method will change the scale of the plane to value parameter
	 * @param value - The scale value which the coordinate will be step
	 */
	public void changeScale(int value) {
		this.scale = value;
		this.repaint();
	}

	/**
	 * @author Michael Alvarado
	 * Date - 11/Feb/2020
	 * Objective - This method will change the plane between Cartesian and Polar and repaint the canvas to see the changes
	 */
	public void changePlane() {
		this.isCartesianPlane = !this.isCartesianPlane;
		this.repaint();
	}

	/**
	 * @author Michael Alvarado
	 * Date - 11/Feb/2020
	 * Objective - This method will change the Coordinate format of the current point
	 */
	public void changeCoordinate() {
		this.isCartesianCoordinate = !this.isCartesianCoordinate;
		this.repaint();
	}
	
	/**
	 * @author Fabiola Badillo
	 * Date - 17/Feb/2020
	 * Objective - This method will add a new displacement in the cartesian format
	 */
	public void addCartesianCoordinateDisplacement(int x, int y) {
		Coordinates coordinate = new CartesianCoordinates(currentPoint.getX()+x,currentPoint.getY()+y);
		if (coordinate.getX() > 20 || coordinate.getX() < -20 || coordinate.getY() > 20 || coordinate.getY() < -20) {
			JOptionPane.showMessageDialog(this, "Out of bounds displacement! Try again!");
		}
		else {
			currentLine.add(coordinate);
			this.repaint();
		}
	}
	
	/**
	 * @author Fabiola Badillo
	 * Date - 17/Feb/2020
	 * Objective - This method will add a new displacement in the polar format
	 */
	public void addPolarCoordinateDisplacement(int r, int O) {
		double x = PolarCoordinates.changePolarToX(r, O);
		double y = PolarCoordinates.changePolarToY(r, O);
		Coordinates coordinate = new CartesianCoordinates(currentPoint.getX()+x,currentPoint.getY()+y);
		if (coordinate.getX() > 20 || coordinate.getX() < -20 || coordinate.getY() > 20 || coordinate.getY() < -20) {
			JOptionPane.showMessageDialog(this, "Out of bounds displacement! Try again!");
		}
		else {
			currentLine.add(coordinate);
			this.repaint();
		}
	}

	/**
	 * @author Michael Alvarado
	 * Date - 13/Feb/2020
	 * Objective - this method erase all the lines and create a new line
	 */
	public void clearAll() {
		lines.clear();
		newLine();
	}
	
	/**
	 * @author Michael Alvarado
	 * Date - 13/Feb/2020
	 * Objective - this method erase the current line,create a new line with point in the origin and repaint canvas
	 */
	public void clear() {
		currentLine.clear();
		currentLine.add(new CartesianCoordinates(0,0));
		this.repaint();
	}

	/**
	 * @author Michael Alvarado
	 * Date - 13/Feb/2020
	 * Objective - this method create a new list of coordinates(Line), adds point at the origin, adds that line to the list of lines and make this new line the current line. 
	 * It repaints the canvas so that the change can be seen
	 */
	public void newLine() {
		ArrayList<Coordinates> line = new ArrayList<Coordinates>();
		line.add(new CartesianCoordinates(0,0));
		lines.add(line);
		currentLine = line; 
		this.repaint();
	}
	
	
	/**
	 * @author Michael Alvarado
	 * Date - 20/Feb/2020
	 * Objective - this method will change the color of the current point
	 */
	public void currentPointColor(Color cP) {
		this.cP = cP;
		this.repaint();
	}
	
	/**
	 * @author Michael Alvarado
	 * Date - 20/Feb/2020
	 * Objective - this method will change the color of the current line
	 */
	public void currentLineColor(Color cL) {
		this.cL = cL;
		this.repaint();
	}
	
	/**
	 * @author Michael Alvarado
	 * Date - 20/Feb/2020
	 * Objective - this method will change the color of the points previous to the current point
	 */
	public void previousPointColor(Color pP) {
		this.pP = pP;
		this.repaint();
	}
	
	/**
	 * @author Michael Alvarado
	 * Date - 20/Feb/2020
	 * Objective - this method will change the color of the lines previous to the current line
	 */
	public void previousLineColor(Color pL) {
		this.pL = pL;
		this.repaint();
	}

	/**
	 * @author Michael Alvarado
	 * Date - 13/Feb/2020
	 * @param coor - give the coordinate it want to draw on the plane
	 * @return - The position in x (in pixels) the coordinate should be painted
	 */
	private int printCoordinatesX(Coordinates coor) {
		return ((int)(coor.getX()/this.scale*xGap)+xOrigin);
	}

	/**
	 * @author Michael Alvarado
	 * Date - 13/Feb/2020
	 * @param coor - give the coordinate it want to draw on the plane
	 * @return - The position in y (in pixels) the coordinate should be painted
	 */
	private int printCoordinatesY(Coordinates coor) {
		return ((int)(-coor.getY()/this.scale*yGap)+yOrigin);
	}

}
