package zes.core.engine.textures;

import org.lwjgl.opengl.GL11;

public class GameTexture {
	/** The internal name of this texture. */
	private String name;
	/** The internal use ID for this texture object. -1 indicates an unregistered texture ID. -- Assigned dynamically through texture registration. */
	public int textureID = -1;
	/** The file system path where this texture exists. */
	private String filePath;
	/** The texture's width in pixels. -- Assigned dynamically through texture registration. */
	public int width = -1;
	/** The texture's height in pixels. -- Assigned dynamically through texture registration. */
	public int height = -1;
	/** True if this texture ID has been deleted through OpenGL. */
	private boolean destroyed = false;
	
	//--------------
	// Constructors
	//--------------
	
	public GameTexture(String filePathIn) { this("noname", filePathIn); }
	public GameTexture(String nameIn, String filePathIn) {
		name = nameIn;
		filePath = filePathIn;
	}
	
	//-------------------
	// Protected Methods
	//-------------------
	
	protected void registerChildTextures(TextureSystem systemIn) {}
	
	//----------------
	// Public Methods
	//----------------
	
	public boolean destroy() {
		if (textureID != -1) {
			GL11.glDeleteTextures(textureID);
			textureID = -1;
			destroyed = true;
			return true;
		}
		return false;
	}
	
	public boolean hasBeenRegistered() { return textureID != -1; }
	
	//---------------------
	// GameTexture Getters
	//---------------------
	
	public String getName() { return name; }
	public int getTextureID() { return textureID; }
	public String getFilePath() { return filePath; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public boolean hasBeenDestroyed() { return destroyed; }
}
