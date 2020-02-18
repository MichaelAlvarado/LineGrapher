package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

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
	Color colorTrace[];

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
		this.colorTrace = new Color[4];
		colorTrace[0] = Color.RED;
		colorTrace[1] = Color.ORANGE;
		colorTrace[2] = Color.MAGENTA;
		colorTrace[3] = new Color(20,198,5); //Green

	}

	@Override 
	public void paint(Graphics g) {
		//have to initialize the variable here IDK why
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
		g.setColor(Color.red);
		currentPoint = currentLine.get(currentLine.size()-1);
		for(Coordinates p: currentLine) {
			if(p.equals(currentPoint)) {
				g.setColor(Color.BLUE); //Draw current Point different Color
			}
			g.fillOval((printCoordinatesX(p)-(pointWidth/2)),(printCoordinatesY(p)-(pointHeight/2)), pointWidth, pointHeight);
		}

		//Draw lines from point
		g.setColor(colorTrace[3]);//Color of Lines
		for(ArrayList<Coordinates> coordinates: lines) {
			if(coordinates.equals(currentLine)) {
				g.setColor(colorTrace[2]); //Color of Current Line
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
		g.setColor(Color.BLUE);
		if(isCartesianCoordinate)
			g.drawString("( " + currentPoint.getX() + " , " + currentPoint.getY() + " )", this.getWidth()-195, 20);
		else
			g.drawString("( " + currentPoint.getR() + " , " + currentPoint.getO() + " )", this.getWidth()-195, 20);

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
	
	private int printCoordinatesX(Coordinates coor) {
		return ((int)(coor.getX()/this.scale*xGap)+xOrigin);
	}

	private int printCoordinatesY(Coordinates coor) {
		return ((int)(-coor.getY()/this.scale*yGap)+yOrigin);
	}

	public void addCartesianCoordinateDisplacement(int x, int y) {
		Coordinates coordinate = new CartesianCoordinates(currentPoint.getX()+x,currentPoint.getY()+y);
		if (coordinate.getX() > 20 || coordinate.getY() > 20) {
			JOptionPane.showMessageDialog(this, "Out of bounds displacement! Try again!");
		}
		else {
			currentLine.add(coordinate);
			this.repaint();
		}

	}

	public void addPolarCoordinateDisplacement(int r, int O) {
		double x = (r*(java.lang.Math.cos(Double.valueOf(Math.toRadians(O)))));
		double y = (r*(java.lang.Math.sin(Double.valueOf(Math.toRadians(O)))));
		Coordinates coordinate = new CartesianCoordinates(currentPoint.getX()+x,currentPoint.getY()+y);
		if (coordinate.getX() > 20 || coordinate.getY() > 20) {
			JOptionPane.showMessageDialog(this, "Out of bounds displacement! Try again!");
		}
		else {
			currentLine.add(coordinate);
			this.repaint();
		}
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


}
