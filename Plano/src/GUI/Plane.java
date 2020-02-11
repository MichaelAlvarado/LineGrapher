package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author Michael Alvarado
 * This class is the cartasian plane where the point are going to be drawn
 */
public class Plane extends Canvas{

	@Override 
	public void paint(Graphics g) {
		//draw AXIS
		g.setColor(Color.BLACK);
		g.drawLine(0, (this.getHeight()/2), this.getWidth(), (this.getHeight()/2)); //X Axis
		g.drawLine((this.getWidth()/2), 0, (this.getWidth()/2), this.getHeight()); //Y Axis
	}
}
