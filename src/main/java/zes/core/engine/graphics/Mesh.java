package zes.core.engine.graphics;

import java.awt.image.BufferedImage;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import zes.core.engine.math.Vertex;
import zes.core.engine.textures.GameTexture;

public class Mesh {
	private Vertex[] vertices;
	private int[] indices;
	private int vao, pbo, ibo, cbo, tbo; // vertex array object, position buffer object, indices buffer object, color buffer object
	
	private BufferedImage image;
	private GameTexture texture;
	
	private enum Type { POS, COL, TEX; }
	
	public Mesh(Vertex[] verticesIn, int[] indicesIn) { this(verticesIn, indicesIn, null); }
	public Mesh(Vertex[] verticesIn, int[] indicesIn, GameTexture textureIn) {
		vertices = verticesIn;
		indices = indicesIn;
		texture = textureIn;
	}
	
	public void create() {
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		//do position buffer stuff
		pbo = storeData(0, 3, Type.POS);
		//color stuff
		cbo = storeData(1, 3, Type.COL);
		//texture stuff
		tbo = storeData(2, 2, Type.TEX); // only 2D so 2 instead of 3
		
		//now do indice buffer stuff
		IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
		indicesBuffer.put(indices).flip();
		
		ibo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
		//unbind the buffer -> 0
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	private int storeData(int index, int size, Type typeIn) {
		FloatBuffer theBuffer = MemoryUtil.memAllocFloat(vertices.length * size);
		float[] theData = new float[vertices.length * size];
		for (int i = 0; i < vertices.length; i++) {
			switch (typeIn) {
			case POS:
				theData[i * 3] = vertices[i].getPosition().x;
				theData[i * 3 + 1] = vertices[i].getPosition().y;
				theData[i * 3 + 2] = vertices[i].getPosition().z;
				break;
			case COL:
				theData[i * 3] = vertices[i].getColor().x;
				theData[i * 3 + 1] = vertices[i].getColor().y;
				theData[i * 3 + 2] = vertices[i].getColor().z;
				break;
			case TEX:
				theData[i * 2] = vertices[i].getTextureCoords().x;
				theData[i * 2 + 1] = vertices[i].getTextureCoords().y;
				break;
			}
		}
		theBuffer.put(theData).flip();
		
		int bufferID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, theBuffer, GL15.GL_STATIC_DRAW);
		// Shader stuff
		GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
		// Unbind the buffer -> 0
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		return bufferID;
	}
	
	public void destroy() {
		GL15.glDeleteBuffers(pbo);
		GL15.glDeleteBuffers(cbo);
		GL15.glDeleteBuffers(ibo);
		GL15.glDeleteBuffers(tbo);
		
		GL30.glDeleteVertexArrays(vao);
	}
	
	// Getters
	public Vertex[] getVertices() { return vertices; }
	public int[] getIndices() { return indices; }
	public GameTexture getTexture() { return texture; }
	public int getVAO() { return vao; }
	public int getPBO() { return pbo; }
	public int getIBO() { return ibo; }
	public int getCBO() { return cbo; }
	public int getTBO() { return tbo; }
}
