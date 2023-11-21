package zes.core.engine.utils;

public class ZColor {
	private String colorName;
	private float r, g, b, a;
	
	// Constructors
	public ZColor(String colorIn) { this(colorIn, 0, 0, 0, 0); }
	public ZColor(String colorIn, float rIn, float gIn, float bIn) { this(colorIn, rIn, gIn, bIn, 0); }
	public ZColor(String colorIn, float rIn, float gIn, float bIn, float aIn) {
		colorName = colorIn;
		r = rIn;
		g = gIn;
		b = bIn;
		a = aIn;
	}
	
	public void setAll(float rIn, float gIn, float bIn) { setAll(rIn, gIn, bIn); }
	public void setAll(float rIn, float gIn, float bIn, float aIn) { r = rIn; g = gIn; b = bIn; a = aIn; }
	
	// Getters
	public String getColorName() { return colorName; }
	public float getR() { return r; }
	public float getG() { return g; }
	public float getB() { return b; }
	public float getA() { return a; }
	
	// Setters
	public void setR(float rIn) { r = rIn; }
	public void setG(float gIn) { g = gIn; }
	public void setB(float bIn) { b = bIn; }
	public void setA(float aIn) { a = aIn; }
	
	public void incR() { r++; }
	public void incG() { g++; }
	public void incB() { b++; }
	public void incA() { a++; }
}
