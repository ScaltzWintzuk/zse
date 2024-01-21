package zes.core.engine.math;

public class Vector2f {
	private float x, y;
	
	public Vector2f() {
		this(0, 0);
	}
	
	public Vector2f(float xIn, float yIn) {
		x = xIn;
		y = yIn;
	}
	
	public void set(float xIn, float yIn) {
		x = xIn;
		y = yIn;
	}
	
	public void print() {
		System.out.printf("<%.2f, %.2f>\n", x, y);
	}
	
	public float getX() { return x; }
	public float getY() { return y; }
	
	public void setX(float xIn) { x = xIn; }
	public void setY(float yIn) { y = yIn; }
}
