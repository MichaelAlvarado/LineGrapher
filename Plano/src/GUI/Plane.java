package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 * 
 * @author Michael Alvarado
 * This class is the cartasian plane where the point are going to be drawn
 */
public class Plane extends Canvas{
	
	ArrayList<Point> coordinates;
	int pointWidth, pointHeight;
	int scale; 
	
	public Plane() {
		coordinates = new ArrayList<Point>();
		coordinates.add(new Point(0,0));
		coordinates.add(new Point(1,3));
		coordinates.add(new Point(-2, 4));
		coordinates.add(new Point(-2, -5));
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
		int xgap = this.getWidth()/16; //wide of rectangles
		int ygap = this.getHeight()/16; //height of rectangles
		for(int i = 0; i < 8; i++) {
			g.drawLine((xOrigin + i*xgap), 0, (xOrigin + i*xgap), this.getHeight()); //draw positive x lines
			g.drawLine((xOrigin - i*xgap), 0, (xOrigin - i*xgap), this.getHeight()); //draw negative x lines
			g.drawLine(0, (yOrigin - i*ygap), this.getWidth(), (yOrigin - i*ygap)); //draw positive y lines
			g.drawLine(0, (yOrigin + i*ygap), this.getWidth(), (yOrigin + i*ygap)); //draw negative y lines
		}
		
		//draw AXIS
		g.setColor(Color.BLACK);
		g.drawLine(0, yOrigin, this.getWidth(), yOrigin); //X Axis
		g.drawLine(xOrigin, 0, xOrigin, this.getHeight()); //Y Axis
		
		//Draw Points
		g.setColor(Color.red);
		for(Point p: coordinates) {
			g.fillOval((p.x*xgap)-(pointWidth/2) + xOrigin , (-p.y*ygap)-(pointHeight/2) + yOrigin, pointWidth, pointHeight);
		}
		
	}
}
