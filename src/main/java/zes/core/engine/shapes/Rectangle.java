package zes.core.engine.shapes;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import zes.core.engine.utils.ZColor;
import zes.core.engine.utils.ZColors;

public class Rectangle {
	private ZColor color;
	
	private int xPos, yPos;
	private int width, height;
	
	public Rectangle() {
		this(0, 0, 20, 20);
	}
	
	public Rectangle(int xPosIn, int yPosIn, int widthIn, int heightIn) {
		this(ZColors.YELLOW, xPosIn, yPosIn, widthIn, heightIn);
	}
	
	public Rectangle(ZColor colorIn, int xPosIn, int yPosIn, int widthIn, int heightIn) {
		color = colorIn;
		xPos = xPosIn;
		yPos = yPosIn;
		width = widthIn;
		height = heightIn;
	}
	
	public void draw() {
		draw(xPos, yPos, width, height);
	}
	
	public void draw(float width, float height) {
		draw(xPos, yPos, width, height);
	}
	
	public void draw(int x, int y, float width, float height) {
		draw(ZColors.BLUE, x, y, width, height);
	}
	
	public void draw(ZColor color, int x, int y, float width, float height) {
		xPos = x;
		yPos = y;
		
		GL11.glColor3f(color.getR(), color.getG(), color.getB());
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x + width, y);
		GL11.glVertex2f(x, y - height);
		GL11.glVertex2f(x + width, y - height);
		
		/*
		GL11.glVertex2f(-x, y);
		GL11.glVertex2f(width, y);
		GL11.glVertex2f(width, -height);
		GL11.glVertex2f(-x, -height);
		*/
		
		GL11.glEnd();
	}
	
	public int getXPos() { return xPos; }
	public int getYPos() { return yPos; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public void setXPos(int xPosIn) { xPos = xPosIn; }
	public void setYPos(int yPosIn) { yPos = yPosIn; }
}
