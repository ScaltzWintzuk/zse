package zes.core.engine.shapes;

import zes.core.engine.utils.ZColor;

public abstract class Shape {
	private ZColor color;
	
	public Shape() {
		
	}
	
	public Shape(ZColor colorIn) { 
		color = colorIn;
	}
	
	public abstract void draw();
	
	public ZColor getColor() { return color; }
	public void setColor(ZColor colorIn) { color = colorIn; }
}
