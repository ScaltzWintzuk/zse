package zes.core.engine.textures;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL46;
import org.lwjgl.stb.STBImage;

public class TextureSystem {
	private ArrayList<GameTexture> registeredTextures = new ArrayList();
	private GameTexture current = null;
	
	private static TextureSystem instance;

	//-------------------------
	// TextureSystem Constructors
	//-------------------------

	public static TextureSystem getInstance() {
		return instance = (instance != null) ? instance : new TextureSystem();
	}

	public TextureSystem() {
		// empty for now
	}

	//---------------------
	//TextureSystem Methods
	//---------------------
	
	/**
	 * Attempts to register a texture into OpenGL. Upon passing, the texture will have it's associated file properties loaded.
	 * @param textureIn
	 * @return
	 */
	public GameTexture registerTexture(GameTexture textureIn) {
		if (textureIn != null) {
			// only register textures that aren't already registered!
			if (isTextureRegistered(textureIn)) {
				throw new IllegalStateException("Texture ID already somehow registered! " + textureIn.getTextureID() + " : " + textureIn.getFilePath());
			}
			
			registerI(textureIn);
			registeredTextures.add(textureIn);
			
			if (textureIn.getTextureID() > 0) {
				System.out.println("Registered Texture: " + textureIn.getTextureID() + " : " + textureIn.getFilePath());
				
				// Now register child textures (if there are any)
				textureIn.registerChildTextures(this);
			}
			else {
				System.out.println("Failed to register texture: " + textureIn.getFilePath());
			}
		}
		
		return textureIn;
	}
	
	/**
	 * Attempts to delete a registered texture from OpenGL.
	 * @param textureIn
	 */
	public void destroyTexture(GameTexture textureIn) {
		if (textureIn != null) {
			if (isTextureRegistered(textureIn)) {
				int id = textureIn.getTextureID();

				if (textureIn.destroy()) {
					Iterator<GameTexture> it = registeredTextures.iterator();
					
					while (it.hasNext()) {
						int curID = it.next().getTextureID();
						if (curID == id) {
							it.remove();
							break;
						}
					}
				}
			}
			else { System.err.println("Texture destroy error: " + textureIn.getFilePath() + " has not been registered!"); }
		}
	}
	
	/**
	 * Binds the current texture to OpenGL if the texture has been registered.
	 * @param textureIn
	 */
	public void bind(GameTexture textureIn) {
		if (textureIn != null) {
			if (isTextureRegistered(textureIn)) {
				GL46.glBindTexture(GL46.GL_TEXTURE_2D, textureIn.getTextureID());
				current = textureIn;
			}
			else { System.err.println("Texture bind error: " + textureIn.getFilePath() + " has not been registered!"); }
		}
	}
	
	/**
	 * Returns true if the specified texture has been registered into OpenGL. 
	 * @param textureIn
	 * @return
	 */
	public boolean isTextureRegistered(GameTexture textureIn) { return textureIn != null && registeredTextures.contains(textureIn); }

	/**
	 * Destroys all the textures within a TextureSystem manager
	 */
	public void destroyAllTextures() {
		// Unbind first
		GL46.glBindTexture(GL46.GL_TEXTURE_2D, 0);
		current = null;
		
		// Destroy each
		registeredTextures.forEach(t -> t.destroy());
	}
	
	//---------------------
	// TextureSystem Getters
	//---------------------
	
	public GameTexture getBoundTexture() { return current; }
	
	//------------------------------
	// TextureSystem Internal Methods
	//------------------------------

	private void registerI(GameTexture textureIn) {

		String filePath = textureIn.getFilePath();
		
		// ensure that OpenGL is initialized first.
		if (GLFW.glfwInit()) {
			try {
				IntBuffer width = BufferUtils.createIntBuffer(1);
				IntBuffer height = BufferUtils.createIntBuffer(1);
				IntBuffer comp = BufferUtils.createIntBuffer(1);
				System.err.println("<" + width + ", " + height + ", " + comp + ">");
				
				ByteBuffer.allocateDirect(4);
				ByteBuffer data = STBImage.stbi_load(textureIn.getFilePath(), width, height, comp, 4);
				
				int w = width.get(0);
				int h = height.get(0);
				
				System.err.println("TEST! >> " + textureIn.getName());
				int texID = GL46.glGenTextures();
				
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID);
				
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
				
				GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, w, h, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, data);
				STBImage.stbi_image_free(data);
				
				//--------------------------------------------
				// Set the values onto the GameTexture object
				//--------------------------------------------
				
				textureIn.width = w;
				textureIn.height = h;
				textureIn.textureID = texID;
				
			}
			catch (Exception e) {
				e.printStackTrace();
				System.err.println("Failed to read the image information! " + filePath);
			}
		}
	}
}
