package zes.core.engine.math;

import java.nio.FloatBuffer;

public class Matrix4f {
	private float[] matrix;
	
	private int rows, cols;
	
	public Matrix4f() {
		this(4, 4);
	}
	
	public Matrix4f(int rowsIn) {
		this(rowsIn, 4);
	}
	
	public Matrix4f(int rowsIn, int colsIn) {
		matrix = new float[rowsIn * colsIn];
		
		rows = rowsIn;
		cols = colsIn;
		
	}
	
	/**
	 * Sets the entirety of the matrix to 0
	 */
	private void defaultValues() {
		defaultValues(0.0f);
	}
	
	/**
	 * Sets the entirety of the matrix to a given float vlaue
	 * @param value
	 */
	private void defaultValues(float value) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				changeValAtIndex(i, j, value);
			}
		}
	}
	
	public static Matrix4f identity() {
		Matrix4f identity = new Matrix4f();
		
		identity.set( 1 , 0 , 0 , 0 ,
				      0 , 1 , 0 , 0 ,
				      0 , 0 , 1 , 0 ,
				      0 , 0 , 0 , 1 );
		
		return identity;
	}
	
	public static Matrix4f translate(Vector3f t) {
		Matrix4f translate = new Matrix4f();
		
		float x = t.getX();
		float y = t.getY();
		float z = t.getZ();
		
		translate.set( 1 , 0 , 0 , 0 ,
				       0 , 1 , 0 , 0 ,
				       0 , 0 , 1 , 0 ,
				       x , y , z , 1 );
		
		return translate;
	}
	
	public static Matrix4f scale(Vector3f s) {
		Matrix4f scaled = new Matrix4f();
		
		float x = s.getX();
		float y = s.getY();
		float z = s.getZ();
		
		scaled.set( x , 0 , 0 , 0 ,
				    0 , y , 0 , 0 ,
				    0 , 0 , z , 0 ,
				    0 , 0 , 0 , 1 );
		
		return scaled;
	}
	
	public static Matrix4f rotate(float angle, Vector3f axis) {
		Matrix4f rotation = identity();
		
		float x = axis.getX();
		float y = axis.getY();
		float z = axis.getZ();
		
		float cos = (float) Math.cos(Math.toRadians(angle));
		float sin = (float) Math.sin(Math.toRadians(angle));
		
		float C = 1 - cos;
		
		rotation.set( x*x*C + cos     ,   x*y*C - z*sin   ,   x*z*C + y*sin   ,   0   ,
					  y*x*C + z*sin   ,   y*y*C + cos     ,   y*z*C - x*sin   ,   0   , 
					  z*x*C - y*sin   ,   z*y*C + x*sin   ,   z*z*C + cos     ,   0   ,
					  0               ,   0               ,   0               ,   0   );
		
		return rotation;
	}
	
	public static Matrix4f projection(float fov, float aspect, float near, float far) {
		Matrix4f projection = identity();
		
		return projection;
	}
	
	public static Matrix4f transform(Vector3f position, Vector3f rotation, Vector3f scale) {
		Matrix4f transform = translate(new Vector3f(position.getX(), position.getY(), position.getZ()));
		
		return transform;
	}
	
	public static Matrix4f view(Vector3f position, Vector3f rotation) {
		Matrix4f view = translate(new Vector3f(-position.getX(), -position.getY(), -position.getZ()));
		
		return view;
	}
	
	public static Matrix4f add(Matrix4f a, Matrix4f b) {
		Matrix4f added = identity();
		
		return added;
	}
	
	public static Matrix4f multiply(Matrix4f a, Matrix4f b) {
		Matrix4f multiplied = identity();
		
		return multiplied;
	}
	
	public Matrix4f set(float... v) {
		if (v.length == rows * cols) {
			matrix[0] = v[0];
			matrix[1] = v[4];
			matrix[2] = v[8];
			matrix[3] = v[12];
			matrix[4] = v[1];
			matrix[5] = v[5];
			matrix[6] = v[9];
			matrix[7] = v[13];
			matrix[8] = v[2];
			matrix[9] = v[6];
			matrix[10] = v[10];
			matrix[11] = v[14];
			matrix[12] = v[3];
			matrix[13] = v[7];
			matrix[14] = v[11];
			matrix[15] = v[15];
		}
		return this;
	}
	
	/**
	 * Updates the matrix's row and column tile given a value to update it to
	 * @param column
	 * @param row
	 * @param value
	 * @return
	 */
	public Matrix4f changeValAtIndex(int row, int column, float value) {
		matrix[row * rows + column] = value;
		return this;
	}
	
	/**
	 * Given the row and column, return the value stored in that matrix position
	 * @param column
	 * @param row
	 * @return
	 */
	public float get(int row, int column) {
		return matrix[row * rows + column];
	}
	
	public void print() {
		
	}

	public float[] getAll() {
		return matrix;
	}
}
