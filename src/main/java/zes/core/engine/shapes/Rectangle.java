package zes.core.engine.shapes;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import zes.core.engine.utils.ZColor;
import zes.core.engine.utils.ZColors;

public class Rectangle {
	private int xPos, yPos;
	private int width, height;
	
	public Rectangle() {
		
	}
	
	public Rectangle(int xPosIn, int yPosIn, int widthIn, int heightIn) {
		xPos = xPosIn;
		yPos = yPosIn;
		width = widthIn;
		height = heightIn;
	}
	
	public void draw(int x, int y, float width, float height) {
		draw(ZColors.blue, x, y, width, height);
	}
	
	public void draw(ZColor color, int x, int y, float width, float height) {
		xPos = x;
		yPos = y;
		
		GL11.glColor3f(color.getR(), color.getG(), color.getB());
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glVertex2f(-x, y);
		GL11.glVertex2f(width, y);
		GL11.glVertex2f(width, -height);
		GL11.glVertex2f(-x, -height);
		
		GL11.glEnd();
	}
	
	public int getXPos() { return xPos; }
	public int getYPos() { return yPos; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
}
