package zes.core.engine.graphics;

import java.io.FileNotFoundException;
import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import zes.core.engine.files.FileUtils;
import zes.core.engine.math.Matrix4f;
import zes.core.engine.math.Vector2f;
import zes.core.engine.math.Vector3f;

public class Shader {
	private String name;
	private String vertexFile, fragmentFile;
	private int vertexID, fragmentID, programID;
	private boolean isLinked = false;
	
	private static FloatBuffer matrix = MemoryUtil.memAllocFloat(16);
	
	public Shader(String vertexIn, String fragmentIn) throws FileNotFoundException {
		this("No Name", vertexIn, fragmentIn);
	}
	
	public Shader(String nameIn, String vertexIn, String fragmentIn) throws FileNotFoundException {
		name = nameIn;
		vertexFile = FileUtils.loadAsStringShader(vertexIn);
		fragmentFile = FileUtils.loadAsStringShader(fragmentIn);
	}
	
	public void create() {
		programID = GL20.glCreateProgram();
		vertexID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		fragmentID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		
		GL20.glShaderSource(vertexID, vertexFile);
		GL20.glCompileShader(vertexID);
		
		if (GL20.glGetShaderi(vertexID, GL20.GL_COMPILE_STATUS) == 0) {
			System.err.println("Vertex Shader: " + GL20.glGetShaderInfoLog(vertexID));
			return;
		}
		
		GL20.glShaderSource(fragmentID, fragmentFile);
		GL20.glCompileShader(fragmentID);
		
		if (GL20.glGetShaderi(fragmentID, GL20.GL_COMPILE_STATUS) == 0) {
			System.err.println("Fragment Shader: " + GL20.glGetShaderInfoLog(fragmentID));
			return;
		}
		
		//attach
		GL20.glAttachShader(programID, vertexID);
		GL20.glAttachShader(programID, fragmentID);
		
		//check link success -- print link error
		GL20.glLinkProgram(programID);
		isLinked = GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == 1;
		if (!isLinked) {
			System.out.println(name + ": link error!");
			System.out.println(GL20.glGetProgramInfoLog(programID));
			return;
		}
		
		//check validate status -- print validate error
		GL20.glValidateProgram(programID);
		if (GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == 0) {
			System.out.println(name + ": validate error!");
			System.out.println(GL20.glGetProgramInfoLog(programID));
			return;
		}
	}
	
	public int gul(String n) {  return GL20.glGetUniformLocation(programID, n); }
	public int getUniformLocation(String name) { return GL20.glGetUniformLocation(programID, name); }
	
	public void setUniform(String name, float val) { GL20.glUniform1f(gul(name), val); }
	public void setUniform(String name, int val) { GL20.glUniform1i(gul(name), val); }
	public void setUniform(String name, boolean val) { GL20.glUniform1i(gul(name), val ? 1 : 0); }
	public void setUniform(String name, Vector2f val) { GL20.glUniform2f(gul(name), val.x, val.y); }
	public void setUniform(String name, Vector3f val) { GL20.glUniform3f(gul(name), val.x, val.y, val.z); }
	public void setUniform(String name, Matrix4f val) {
		matrix.put(val.getAll()).flip();
		GL20.glUniformMatrix4fv(gul(name), true, matrix);
	}
	
	public void bind() {
		GL20.glUseProgram(programID);
	}
	
	public void unbind() {
		GL20.glUseProgram(0);
	}
	
	public void destroy() {
		GL20.glDetachShader(programID, vertexID);
		GL20.glDetachShader(programID, fragmentID);
		GL20.glDeleteShader(vertexID);
		GL20.glDeleteShader(fragmentID);
		GL20.glDeleteProgram(programID);
	}
}
