package levelMaker.gridCountButtons;

import javax.swing.ImageIcon;

import levelMaker.LevelMaker;
import levelMaker.MyButton;
import core.Screen;

/** Pøidává k hodnotì gridCountY 1. */
public class ButtonUp_GCY extends MyButton {
	private String textureFile = "ButtonUp";

	public ButtonUp_GCY(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.getTextureFile();
	}

	public void action() {
		if (Screen.gridCountY < Screen.map[0].length) {
			LevelMaker.lastGridCountY = LevelMaker.gridCountY;
			LevelMaker.gridCountY++;
		}
	}

	public void getTextureFile() {
		this.texture = new ImageIcon(gridCountButtonTexturePath + this.textureFile + ".png").getImage();
	}

}
