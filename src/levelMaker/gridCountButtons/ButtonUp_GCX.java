package levelMaker.gridCountButtons;

import javax.swing.ImageIcon;

import levelMaker.MyButton;
import core.Screen;

/** Pøidává k hodnotì gridCountX 1. */
public class ButtonUp_GCX extends MyButton {
	private String textureFile = "ButtonUp";

	public ButtonUp_GCX(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.getTextureFile();
	}

	public void action() {
		if(Screen.gridCountX < Screen.map.length) {
			Screen.gridCountX++;
		}
	}

	public void getTextureFile() {
		this.texture = new ImageIcon(gridCountButtonTexturePath + this.textureFile + ".png").getImage();
	}

}
