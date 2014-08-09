package levelMaker.gridCountButtons;

import javax.swing.ImageIcon;

import levelMaker.LevelMaker;
import levelMaker.MyButton;

/** Ubírá hodnotì gridCountX 1. */
public class ButtonDown_GCX extends MyButton {
	private String textureFile = "ButtonDown";

	public ButtonDown_GCX(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.getTextureFile();
	}

	public void action() {
		if (LevelMaker.gridCountX > 1) {
			LevelMaker.lastGridCountX = LevelMaker.gridCountX;
			LevelMaker.gridCountX--;
		}
	}

	public void getTextureFile() {
		this.texture = new ImageIcon(gridCountButtonTexturePath + this.textureFile + ".png").getImage();
	}

}
