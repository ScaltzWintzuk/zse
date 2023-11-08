package zes.core.engine.utils;

public class ZColor {
	private String colorName;
	private int r, g, b, a;
	
	// Constructors
	public ZColor(String colorIn) { this(colorIn, 0, 0, 0, 0); }
	public ZColor(String colorIn, int rIn, int gIn, int bIn) { this(colorIn, rIn, gIn, bIn, 0); }
	public ZColor(String colorIn, int rIn, int gIn, int bIn, int aIn) {
		colorName = colorIn;
		r = rIn;
		g = gIn;
		b = bIn;
		a = aIn;
	}
	
	public void setAll(int rIn, int gIn, int bIn) { setAll(rIn, gIn, bIn); }
	public void setAll(int rIn, int gIn, int bIn, int aIn) { r = rIn; g = gIn; b = bIn; a = aIn; }
	
	// Getters
	public String getColorName() { return colorName; }
	public int getR() { return r; }
	public int getG() { return g; }
	public int getB() { return b; }
	public int getA() { return a; }
	
	// Setters
	public void setR(int rIn) { r = rIn; }
	public void setG(int gIn) { g = gIn; }
	public void setB(int bIn) { b = bIn; }
	public void setA(int aIn) { a = aIn; }
}
