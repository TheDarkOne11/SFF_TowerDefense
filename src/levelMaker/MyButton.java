package levelMaker;

import java.awt.Image;

import javax.swing.ImageIcon;

public class MyButton {
	public int x, y, width, height;
	public String textureFile;
	public Image texture;
	
	public MyButton(int x, int y, int width, int height) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public MyButton getTextureFile(String textureFile) {
		this.textureFile = textureFile;
		
		this.texture = new ImageIcon("res/levelMaker/" + this.textureFile + ".png").getImage();
		
		return this;
	}
}
