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
		//draw squares
		g.setColor(Color.LIGHT_GRAY);
		int xgap = this.getWidth()/16; //wide of rectangles
		int ygap = this.getHeight()/16; //height of rectangles
		int xerror = (this.getWidth()%16)/2; //this add the pixels in x axis needed because division of integer
		int yerror = (this.getHeight()%16)/2; //this add the pixels in y axis needed because division of integer
		for(int i = 1; i < 16; i++) {
			g.drawLine((i*xgap + xerror), 0, (i*xgap + xerror), this.getHeight()); //draw x lines
			g.drawLine(0, (i*ygap + yerror), this.getWidth(), (i*ygap + yerror)); //draw y lines
		}
		
		//draw AXIS
		g.setColor(Color.BLACK);
		g.drawLine(0, (this.getHeight()/2), this.getWidth(), (this.getHeight()/2)); //X Axis
		g.drawLine((this.getWidth()/2), 0, (this.getWidth()/2), this.getHeight()); //Y Axis
		
		
		int xOrigin = this.getWidth()/2;
		int yOrigin = this.getHeight()/2;
		
		g.setColor(Color.red);
		for(Point p: coordinates) {
			g.fillOval((p.x*xgap)-(pointWidth/2) + xOrigin , (-p.y*ygap)-(pointHeight/2) + yOrigin, pointWidth, pointHeight);
		}
		
	}
}
