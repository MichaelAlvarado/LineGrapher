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

	ArrayList<Coordinates> coordinates; 
	int pointWidth, pointHeight; //points of coordinates
	int xGap, yGap; //this is the distance of line to line
	int xOrigin, yOrigin;
	int scale; 
	Coordinates currentPoint; //last coordinate 
	boolean isCartasianPlane; //to print cartasian or polar plane

	public Plane() {
		coordinates = new ArrayList<Coordinates>();
		coordinates.add(new CartesianCoordinates(0,0));
		coordinates.add(new CartesianCoordinates(1,3));
		coordinates.add(new CartesianCoordinates(-2,4));
		coordinates.add(new CartesianCoordinates(-2,-5));
		coordinates.add(new CartesianCoordinates(3,-2));
		pointWidth = 10;
		pointHeight = 10;
		scale = 1;
		xGap = this.getWidth()/16; //wide of rectangles
		yGap = this.getHeight()/16; //height of rectangles
		isCartasianPlane = true;
		xOrigin = this.getWidth()/2; //position in canvas of point x origin
		yOrigin = this.getHeight()/2; //position in canvas of point y origin
	}

	@Override 
	public void paint(Graphics g) {
		//have to initialize the variable here IDK why
		xGap = this.getWidth()/16; //wide of rectangles
		yGap = this.getHeight()/16; //height of rectangles
		xOrigin = this.getWidth()/2; //position in canvas of point x origin
		yOrigin = this.getHeight()/2; //position in canvas of point y origin
		
		//draw squares cartesian Plane
		if(isCartasianPlane) {
			g.setColor(Color.LIGHT_GRAY);
			//Its draw negative and positive axis separately to make sure they are align with axis X and Y
			for(int i = 0; i < 8; i++) {
				g.drawLine((xOrigin + i*xGap), 0, (xOrigin + i*xGap), this.getHeight()); //draw positive x lines
				g.drawLine((xOrigin - i*xGap), 0, (xOrigin - i*xGap), this.getHeight()); //draw negative x lines
				g.drawLine(0, (yOrigin - i*yGap), this.getWidth(), (yOrigin - i*yGap)); //draw positive y lines
				g.drawLine(0, (yOrigin + i*yGap), this.getWidth(), (yOrigin + i*yGap)); //draw negative y lines
			}
		}
		
		//draw polar Plane
		else {
			for(int i = 1; i < 30; i++) {
			g.drawOval(xOrigin - (i*xGap/2), yOrigin - (i*yGap/2), i*xGap, i*yGap);
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
		for(Coordinates p: coordinates) {
			g.fillOval(((int)(p.getX()/this.scale*xGap)-(pointWidth/2)+xOrigin), ((int)(-p.getY()/this.scale*yGap)-(pointHeight/2)+yOrigin), pointWidth, pointHeight);
		}

		//Draw current Point in diferent Color
		currentPoint = coordinates.get(coordinates.size()-1); //Testing Purposes (this will be define eveytime there is an input
		g.setColor(Color.BLUE);
		g.fillOval(((int)(currentPoint.getX()/this.scale*xGap)-(pointWidth/2)+xOrigin),((int)(-currentPoint.getY()/this.scale*yGap)-(pointHeight/2)+yOrigin), pointWidth, pointHeight);

		//Draw lines from point
		g.setColor(Color.GREEN);
		for(int i = 1; i < coordinates.size(); i++) {
			Coordinates p0 = coordinates.get(i-1);
			Coordinates p1 = coordinates.get(i);
			g.drawLine(((int)(p0.getX()/this.scale*xGap)+xOrigin), ((int)(-p0.getY()/this.scale*yGap)+yOrigin), ((int)(p1.getX()/this.scale*xGap)+xOrigin), ((int)(-p1.getY()/this.scale*yGap)+yOrigin));
		}
		
		//Draw panel with coordinates
		g.setColor(new Color(0,0,0,100));
		g.fillRect(this.getWidth()-200, 0, 200, 30);
		//Draw coordinates point on panel
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.setColor(Color.BLACK);
		g.drawString("( " + currentPoint.getX() + " , " + currentPoint.getY() + " )", this.getWidth()-200, 20);
	}

	public void changeScale(int value) {
		this.scale = value;
		this.repaint();
	}
	
	public void changePlane() {
		this.isCartasianPlane = !this.isCartasianPlane;
		this.repaint();
	}
	
	public void addCartesianCoordinateDisplacement(int x, int y) {
		coordinates.add(new CartesianCoordinates(currentPoint.getX()+x,currentPoint.getY()+y));
		this.repaint();
	}

	public void addPolarCoordinateDisplacement(int r, int O) {
		coordinates.add(new PolarCoordinates(r,O));
		this.repaint();
	}
}
