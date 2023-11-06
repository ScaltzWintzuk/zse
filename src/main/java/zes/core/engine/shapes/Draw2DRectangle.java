package zes.core.engine.shapes;

public class Draw2DRectangle {
	private int xPos, yPos;
	private int width, height;
	
	public Draw2DRectangle(int xPosIn, int yPosIn, int widthIn, int heightIn) {
		xPos = xPosIn;
		yPos = yPosIn;
		width = widthIn;
		height = heightIn;
	}
	
	public int getXPos() { return xPos; }
	public int getYPos() { return yPos; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
}
