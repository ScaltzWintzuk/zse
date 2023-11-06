package zes.core.engine.math;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Vertex {
	private Vector3f position, color;
	private Vector2f textureCoords;
	
	public Vertex(Vector3f positionIn, Vector3f colorIn, Vector2f textureCoordsIn) {
		position = positionIn;
		color = colorIn;
		textureCoords = textureCoordsIn;
	}
	
	// Getters
	public Vector3f getPosition() { return position; }
	public Vector3f getColor() { return color; }
	public Vector2f getTextureCoords() { return textureCoords; }
	
	// Setters
	public void setPosition(Vector3f positionIn) { positionIn = position; }
	public void setColor(Vector3f colorIn) { color = colorIn; }
	public void setTextureCoords(Vector2f textureCoordsIn) { textureCoords = textureCoordsIn; }
}
