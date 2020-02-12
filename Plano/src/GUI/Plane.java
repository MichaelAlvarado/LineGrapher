package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import Main.CartesianCoordinates;
import Main.Coordinates;

/**
 * 
 * @author Michael Alvarado
 * This class is the cartasian plane where the point are going to be drawn
 */
public class Plane extends Canvas{
	
	ArrayList<Coordinates> coordinates;
	int pointWidth, pointHeight;
	int scale; 
	Coordinates currentPoint;
	
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
	}
	
	@Override 
	public void paint(Graphics g) {
		int xOrigin = this.getWidth()/2; //position in canvas of point x origin
		int yOrigin = this.getHeight()/2; //position in canvas of point y origin
		
		//draw squares
		g.setColor(Color.LIGHT_GRAY);
		int xGap = this.getWidth()/16; //wide of rectangles
		int yGap = this.getHeight()/16; //height of rectangles
		//Its draw negative and positive axis separately to make sure they are align with axis X and Y
		for(int i = 0; i < 8; i++) {
			g.drawLine((xOrigin + i*xGap), 0, (xOrigin + i*xGap), this.getHeight()); //draw positive x lines
			g.drawLine((xOrigin - i*xGap), 0, (xOrigin - i*xGap), this.getHeight()); //draw negative x lines
			g.drawLine(0, (yOrigin - i*yGap), this.getWidth(), (yOrigin - i*yGap)); //draw positive y lines
			g.drawLine(0, (yOrigin + i*yGap), this.getWidth(), (yOrigin + i*yGap)); //draw negative y lines
		}
		
		//draw X and Y AXIS
		g.setColor(Color.BLACK);
		g.drawLine(0, yOrigin, this.getWidth(), yOrigin); //X Axis
		g.drawLine(xOrigin, 0, xOrigin, this.getHeight()); //Y Axis
		
		//draw coordinates Number
		g.setColor(Color.BLACK);
		for(int s = 0; s < 8; s++) {
			g.drawString(String.valueOf(s*this.scale), (xOrigin + s*xGap), yOrigin); //draw positive X coordinate 
			g.drawString(String.valueOf(-s*this.scale), (xOrigin - s*xGap), yOrigin); //draw negative X coordinate 
			g.drawString(String.valueOf(s*this.scale), xOrigin, (yOrigin - s*yGap)); //draw positive Y coordinate 
			g.drawString(String.valueOf(-s*this.scale), xOrigin, (yOrigin + s*yGap)); //draw negative Y coordinate 
		}
		
		//Draw Points
		g.setColor(Color.red);
		for(Coordinates p: coordinates) {
			g.fillOval((p.getX()*xGap)-(pointWidth/2) + xOrigin , (-p.getY()*yGap)-(pointHeight/2) + yOrigin, pointWidth, pointHeight);
		}
		
		//Draw current Point
		currentPoint = coordinates.get(coordinates.size()-1); //Testing Purposes (this will be define eveytime there is an input
		g.setColor(Color.BLUE);
		g.fillOval((currentPoint.getX()*xGap)-(pointWidth/2) + xOrigin , (-currentPoint.getY()*yGap)-(pointHeight/2) + yOrigin, pointWidth, pointHeight);
		
		//Draw lines from point
		g.setColor(Color.GREEN);
		for(int i = 1; i < coordinates.size(); i++) {
			Coordinates p0 = coordinates.get(i-1);
			Coordinates p1 = coordinates.get(i);
			g.drawLine(p0.getX()*xGap+xOrigin, -p0.getY()*yGap+yOrigin, p1.getX()*xGap+xOrigin, -p1.getY()*yGap+yOrigin);
		}		
	}
	
}
