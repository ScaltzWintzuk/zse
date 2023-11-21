package zes.core.engine.shapes;

import zes.core.engine.utils.ZColor;

public abstract class Shape {
	private ZColor color;
	
	public Shape() {
		
	}
	
	public Shape(ZColor colorIn) { 
		
	}
	
	public abstract void draw();
}
