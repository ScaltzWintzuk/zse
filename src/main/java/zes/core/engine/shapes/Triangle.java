package zes.core.engine.shapes;

import org.lwjgl.opengl.GL11;

import zes.core.engine.utils.ZColor;
import zes.core.engine.utils.ZColors;

public class Triangle extends Shape {
	private ZColor color;
	
	private int xPos, yPos;
	private int width, height;
	
	// REMOVE THESE LATER, THIS IS BECAUSE VECTORS DO NOT USE PIXEL COORDIANTES FOR SOME REASON
	private float xVec, yVec;
	
	public Triangle() {
		this(0, 0, 20, 20);
	}
	
	public Triangle(int xPosIn, int yPosIn, int widthIn, int heightIn) {
		this(ZColors.YELLOW, xPosIn, yPosIn, widthIn, heightIn);
	}
	
	public Triangle(ZColor colorIn, int xPosIn, int yPosIn, int widthIn, int heightIn) {
		color = colorIn;
		xPos = xPosIn;
		yPos = yPosIn;
		width = widthIn;
		height = heightIn;
		
		xVec = 0;
		yVec = 0;
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
		GL11.glColor3f(color.getR(), color.getG(), color.getB());
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glVertex2f(xVec, yVec);
		GL11.glVertex2f(xVec, yVec + 0.5f);
		GL11.glVertex2f(xVec + 0.5f, yVec + 0.5f);
		//GL11.glVertex2f(xVec + 0.5f, yVec);
		
		// UPDATE POST DELETE GET
		// POST grabs the data of a website -- Create an account posts to the account database
		// UPDATE change an entry that already exists
		
		/*
		GL11.glVertex2f(xPos, yPos);
		GL11.glVertex2f(xPos + width, yPos);
		GL11.glVertex2f(xPos, yPos - height);
		GL11.glVertex2f(xPos + width, yPos - height);
		*/
		
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
	
	public void incXTest() { xVec += 0.1; }
	public void incYTest() { yVec += 0.1; }
	public void decXTest() { xVec -= 0.1; }
	public void decYTest() { yVec -= 0.1; }
}
