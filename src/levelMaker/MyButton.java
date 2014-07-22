package levelMaker;

import java.awt.Image;

import javax.swing.ImageIcon;

public class MyButton {
	public int x;
	public int y;
	public int width;
	public int height;
	public int inputValue;
	public int currentValue;
	public String textureFile;
	public Image texture;
	
	public MyButton(int x, int y, int width, int height, int inputValue, int currentValue) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.inputValue = inputValue;
		this.currentValue = currentValue;
	}
	
	public MyButton getTextureFile(String textureFile) {
		this.textureFile = textureFile;
		
		this.texture = new ImageIcon("res/levelMaker/" + this.textureFile + ".png").getImage();
		
		return this;
	}
}
