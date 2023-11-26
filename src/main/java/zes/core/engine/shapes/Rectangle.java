package zes.core.engine.shapes;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import zes.core.engine.controls.ControllerManager;
import zes.core.engine.controls.MouseInput;
import zes.core.engine.utils.ZColor;
import zes.core.engine.utils.ZColors;

public class Rectangle extends Shape {
	private ZColor color;
	
	private float xPos, yPos;
	private float width, height;
	
	public Rectangle() { this(0, 0, 1, 1); }
	
	public Rectangle(float xPosIn, float yPosIn, float widthIn, float heightIn) { this(ZColors.YELLOW, xPosIn, yPosIn, widthIn, heightIn); }
	
	public Rectangle(ZColor colorIn, float xPosIn, float yPosIn, float widthIn, float heightIn) {
		color = colorIn;
		xPos = xPosIn;
		yPos = yPosIn;
		width = widthIn;
		height = heightIn;
	}
	
	/**
	 * Taking in no arguments, this will draw a rectangle using the fields xPos, yPos, width, and height
	 */
	public void draw() { draw(xPos, yPos, width, height); }
	
	/**
	 * Takes in width and height as an argument and funnels to drawing a rectangle using the fields xPos and yPos
	 * @param width
	 * @param height
	 */
	public void draw(float width, float height) { draw(xPos, yPos, width, height); }
	
	/**
	 * Takes in the xPos, yPos, width, and height as arguments, but uses the color field.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void draw(float x, float y, float width, float height) { draw(color, x, y, width, height); }
	
	/**
	 * This method is the method that all draw methods eventually call, but this takes in the color, xPos, yPos, width, and height as arguments and draws the GL11.QUAD accordingly
	 * @param color
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void draw(ZColor color, float x, float y, float width, float height) {
		GL11.glColor3f(color.getR(), color.getG(), color.getB());
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glVertex2f(xPos, yPos);
		GL11.glVertex2f(xPos, yPos + height);
		GL11.glVertex2f(xPos + width, yPos + height);
		GL11.glVertex2f(xPos + width, yPos);
		
		GL11.glEnd();
	}
	
	// ==============
	//    Getters
	// ==============
	public float getXPos() { return xPos; }
	public float getYPos() { return yPos; }
	public float getWidth() { return width; }
	public float getHeight() { return height; }
	
	// ==============
	//    Setters
	// ==============
	public void setXPos(float xPosIn) { xPos = xPosIn; }
	public void setYPos(float yPosIn) { yPos = yPosIn; }
	
	// ==============================
	//    Incrementers/Decrementers
	// ==============================
	public void incXTest() { xPos += 0.1; }
	public void incYTest() { yPos += 0.1; }
	public void decXTest() { xPos -= 0.1; }
	public void decYTest() { yPos -= 0.1; }
}
