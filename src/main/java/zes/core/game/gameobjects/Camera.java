package zes.core.game.gameobjects;

import zes.core.engine.math.Vector3f;

public class Camera {
	private Vector3f position, rotation;
	
	private float mX_old, mX;
	private float mY_old, mY;
	
	public Camera() {
		this(new Vector3f(), new Vector3f());
	}
	
	public Camera(Vector3f position) {
		this(position, new Vector3f());
	}
	
	public Camera(float x, float y, float z) {
		this(new Vector3f(x, y, z), new Vector3f());
	}
	
	public Camera(Vector3f positionIn, Vector3f rotationIn) {
		position = positionIn;
		rotation = rotationIn;
	}
	
	public void update() {
		
	}
	
	public void movementControls() {
		
	}
	
	public void nonMovementControls() {
		
	}
	
	public Vector3f getPositionVector() { return position; }
	public Vector3f getRotatioNVector() { return rotation; }
	
	public Camera setPosition(float x, float y, float z) { position = new Vector3f(x, y, z); return this; }
	public Camera setPosition(Vector3f positionIn) { position = positionIn; return this; }
	
	public Camera setYaw(float amount) { rotation.set(rotation.getX(), amount, rotation.getZ()); return this; }
	public Camera setPitch(float amount) {
		return this;
	}
	
	public Camera setRotatioN(Vector3f rotationIn) {
		rotation = rotationIn;
		return this;
	}
	
}
