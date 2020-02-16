package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import Coordinates.CartesianCoordinates;
import Coordinates.Coordinates;
import Coordinates.PolarCoordinates;

/**
 * 
 * @author Michael Alvarado
 * This class is the cartasian plane where the point are going to be drawn
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

	public Plane() {
		currentLine = new ArrayList<Coordinates>();
		currentLine.add(new CartesianCoordinates(0,0));
		currentLine.add(new CartesianCoordinates(1,3));
		currentLine.add(new CartesianCoordinates(-2,4));
		currentLine.add(new CartesianCoordinates(-2,-5));
		currentLine.add(new CartesianCoordinates(3,-2));
		lines = new ArrayList<ArrayList<Coordinates>>();
		lines.add(currentLine);
		pointWidth = 10;
		pointHeight = 10;
		scale = 1;
		isCartesianPlane = true;
		isCartesianCoordinate = true;
	}

	@Override 
	public void paint(Graphics g) {
		//have to initialize the variable here IDK why
		xGap = this.getWidth()/16; //wide of rectangles
		yGap = this.getHeight()/16; //height of rectangles
		xOrigin = this.getWidth()/2; //position in canvas of point x origin
		yOrigin = this.getHeight()/2; //position in canvas of point y origin

		//draw squares cartesian Plane
		if(isCartesianPlane) {
			g.setColor(Color.LIGHT_GRAY);
			//Its draw negative and positive axis separately to make sure they are align with axis X and Y
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
				g.drawLine(xOrigin, yOrigin, printCoordinatesX(angle), printCoordinatesY(angle));
				g.drawString(String.valueOf(angle.getO()), printCoordinatesX(angle), printCoordinatesY(angle));
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
			g.drawString(String.valueOf(s*this.scale), (xOrigin + s*xGap), yOrigin); //draw positive X coordinate 
			g.drawString(String.valueOf(-s*this.scale), (xOrigin - s*xGap), yOrigin); //draw negative X coordinate 
			g.drawString(String.valueOf(s*this.scale), xOrigin, (yOrigin - s*yGap)); //draw positive Y coordinate 
			g.drawString(String.valueOf(-s*this.scale), xOrigin, (yOrigin + s*yGap)); //draw negative Y coordinate 
		}

		//Draw Points
		g.setColor(Color.red);
		for(Coordinates p: currentLine) {
			g.fillOval((printCoordinatesX(p)-(pointWidth/2)),(printCoordinatesY(p)-(pointHeight/2)), pointWidth, pointHeight);
		}

		//Draw current Point in diferent Color
		currentPoint = currentLine.get(currentLine.size()-1);
		g.setColor(Color.BLUE);
		g.fillOval((printCoordinatesX(currentPoint)-(pointWidth/2)),(printCoordinatesY(currentPoint)-(pointHeight/2)), pointWidth, pointHeight);

		//Draw lines from point
		g.setColor(Color.ORANGE);
		for(ArrayList<Coordinates> coordinates: lines) {
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
		g.setColor(Color.BLUE);
		if(isCartesianCoordinate)
			g.drawString("( " + currentPoint.getX() + " , " + currentPoint.getY() + " )", this.getWidth()-200, 20);
		else
			g.drawString("( " + currentPoint.getR() + " , " + currentPoint.getO() + " )", this.getWidth()-200, 20);

	}

	public void changeScale(int value) {
		this.scale = value;
		this.repaint();
	}

	public void changePlane() {
		this.isCartesianPlane = !this.isCartesianPlane;
		this.repaint();
	}

	public void changeCoordinate() {
		this.isCartesianCoordinate = !this.isCartesianCoordinate;
		this.repaint();
	}

	public void addCartesianCoordinateDisplacement(int x, int y) {
		Coordinates coordinate = new CartesianCoordinates(currentPoint.getX()+x,currentPoint.getY()+y);
		currentLine.add(coordinate);
		this.repaint();
	}

	public void addPolarCoordinateDisplacement(int r, int O) {
		Coordinates coordinate = new PolarCoordinates(r,O);//This must be change to displacement
		currentLine.add(new PolarCoordinates(r,O));
		this.repaint();
	}

	public void clearAll() {
		//clear All Lines
		lines.clear();
		ArrayList<Coordinates> line = new ArrayList<Coordinates>();
		line.add(new CartesianCoordinates(0,0));
		lines.add(line);
		currentLine = line;
		this.repaint();
	}

	public void clear() {
		//clear only the current Line
		currentLine.clear();
		currentLine.add(new CartesianCoordinates(0,0));
		this.repaint();
	}

	public void newLine() {
		//Create a new Line to trace
		ArrayList<Coordinates> line = new ArrayList<Coordinates>();
		line.add(new CartesianCoordinates(0,0));
		lines.add(line);
		currentLine = line; 
		this.repaint();
	}

	private int printCoordinatesX(Coordinates coor) {
		return ((int)(coor.getX()/this.scale*xGap)+xOrigin);
	}

	private int printCoordinatesY(Coordinates coor) {
		return ((int)(-coor.getY()/this.scale*yGap)+yOrigin);
	}

}
