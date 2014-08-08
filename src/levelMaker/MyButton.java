package levelMaker;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

/** Obecné tlaèítko. */
public abstract class MyButton {
	public int x;
	public int y;
	public int width;
	public int height;
	
	public Image texture;
	protected String gridCountButtonTexturePath = "res/levelMaker/";
	
	public MyButton(int x, int y, int width, int height) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public void clickButton(MouseEvent e) {
		if(e.getXOnScreen() >= this.x && e.getXOnScreen() <= this.x+this.width && e.getYOnScreen() >= this.y && e.getYOnScreen() <= this.y+this.height) {
			this.action();
		}
	}
	
	public void drawButton(Graphics g) {
		g.drawImage(this.texture, this.x, this.y, null);
	}

	public abstract void action();

	public abstract void getTextureFile();
	
}
