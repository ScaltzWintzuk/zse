package zes.core.engine.shapes;

import org.lwjgl.opengl.GL11;

import zes.core.engine.utils.ZColor;
import zes.core.engine.utils.ZColors;

public class Circle {
	private ZColor color;
	
	private Rectangle hitbox;
	
	private float radius;
	
	// REMOVE THESE LATER, THIS IS BECAUSE VECTORS DO NOT USE PIXEL COORDIANTES FOR SOME REASON
	private float xVec, yVec;
	
	public Circle() {
		this(0.33f);
	}
	
	public Circle(float radiusIn) {
		this(ZColors.YELLOW, radiusIn);
	}
	
	public Circle(ZColor colorIn, float radiusIn) {
		color = colorIn;
		
		radius = radiusIn;
		
		xVec = 0;
		yVec = 0;
	}
	
	public void draw() {
		draw(0.33f);
	}
	
	public void draw(float radius) {
		draw(ZColors.BLUE, radius);
	}
	
	public void draw(ZColor color, float radius) {
		GL11.glColor3f(color.getR(), color.getG(), color.getB());
		
	    GL11.glBegin(GL11.GL_LINE_LOOP);
	    int size = 77;
	    for(int i = 0; i < size; i++)
	    {
	        float theta = 2.0f * 3.1415926f * ((float) (i) / (size)); //get the current angle
	        
	        
	        float xVal = radius * (float) Math.cos(theta); // calculate the x component
	        float yVal = radius * (float) Math.sin(theta); // calculate the y component

	        GL11.glVertex2f(xVal + xVec, yVal + yVec);//output vertex

	    }
		
		GL11.glEnd();
	}
	
	public void randomMovements() {
		
	}
	
	
	
	public float getRadius() { return radius; }
	public void setRadius(float radiusIn) { radius = radiusIn; }
	
	public void incXTest() { xVec += 0.1; }
	public void incYTest() { yVec += 0.1; }
	public void decXTest() { xVec -= 0.1; }
	public void decYTest() { yVec -= 0.1; }
}
