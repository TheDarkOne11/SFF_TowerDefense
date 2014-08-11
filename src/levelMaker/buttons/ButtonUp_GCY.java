package levelMaker.buttons;

import javax.swing.ImageIcon;

import levelMaker.LevelMaker;
import levelMaker.MyButton;
import lib.PathVar;
import core.Screen;

/** P�id�v� k hodnot� gridCountY 1. */
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
		this.texture = new ImageIcon(PathVar.gridCountButtonPath + this.textureFile + ".png").getImage();
	}

}
