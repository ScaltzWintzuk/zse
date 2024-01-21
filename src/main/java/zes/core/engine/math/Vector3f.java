package zes.core.engine.math;

public class Vector3f {
	private float x, y, z;
	
	public Vector3f() {
		this(0, 0, 0);
	}
	
	public Vector3f(float xIn, float yIn, float zIn) {
		x = xIn;
		y = yIn;
		z = zIn;
	}
	
	public void set(float xIn, float yIn, float zIn) {
		x = xIn;
		y = yIn;
		z = zIn;
	}
	
	public float getX() { return x; }
	public float getY() { return y; }
	public float getZ() { return z; }
	
	public void setX(float xIn) { x = xIn; }
	public void setY(float yIn) { y = yIn; }
	public void setZ(float zIn) { z = zIn; }
	
	public void printValues() {
		System.out.printf("<%.2f, %.2f, %.2f>\n", x, y, z);
	}
}
