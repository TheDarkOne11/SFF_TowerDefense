package levelMaker.gridCountButtons;

import javax.swing.ImageIcon;

import levelMaker.MyButton;
import core.Screen;

/** Ubírá hodnotì gridCountY 1. */
public class ButtonDown_GCY extends MyButton {
	private String textureFile = "ButtonDown";

	public ButtonDown_GCY(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.getTextureFile();
	}

	public void action() {
		if (Screen.gridCountY > 0) {
			Screen.gridCountY--;
		}
	}

	public void getTextureFile() {
		this.texture = new ImageIcon(gridCountButtonTexturePath + this.textureFile + ".png").getImage();
	}

}