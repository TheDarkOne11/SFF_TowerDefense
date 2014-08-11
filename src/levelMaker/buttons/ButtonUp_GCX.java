package levelMaker.buttons;

import javax.swing.ImageIcon;

import levelMaker.LevelMaker;
import levelMaker.MyButton;
import lib.PathVar;
import core.Screen;

/** Pøidává k hodnotì gridCountX 1. */
public class ButtonUp_GCX extends MyButton {
	private String textureFile = "ButtonUp";

	public ButtonUp_GCX(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.getTextureFile();
	}

	public void action() {
		if (LevelMaker.gridCountX < Screen.map.length) {
			LevelMaker.lastGridCountX = LevelMaker.gridCountX;
			LevelMaker.gridCountX++;
		}
	}

	public void getTextureFile() {
		this.texture = new ImageIcon(PathVar.gridCountButtonPath + this.textureFile + ".png").getImage();
	}

}
