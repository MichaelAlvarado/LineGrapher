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
		pointWidth = 10;
		pointHeight = 10;
		scale = 1;
	}
	
	@Override 
	public void paint(Graphics g) {
		//draw AXIS
		g.setColor(Color.BLACK);
		g.drawLine(0, (this.getHeight()/2), this.getWidth(), (this.getHeight()/2)); //X Axis
		g.drawLine((this.getWidth()/2), 0, (this.getWidth()/2), this.getHeight()); //Y Axis
		
		int xOrigin = this.getWidth()/2;
		int yOrigin = this.getHeight()/2;
		
		g.setColor(Color.red);
		g.fillOval(xOrigin-(pointWidth/2), yOrigin-(pointHeight/2), pointWidth, pointHeight);
		
	}
}
