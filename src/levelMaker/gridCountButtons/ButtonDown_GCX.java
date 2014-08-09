package levelMaker.gridCountButtons;

import javax.swing.ImageIcon;

import levelMaker.MyButton;
import core.Screen;

/** Ubírá hodnotì gridCountX 1. */
public class ButtonDown_GCX extends MyButton {
	private String textureFile = "ButtonDown";

	public ButtonDown_GCX(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.getTextureFile();
	}

	public void action() {
		if (Screen.gridCountX > 0) {
			Screen.gridCountX--;
		}
	}

	public void getTextureFile() {
		this.texture = new ImageIcon(gridCountButtonTexturePath + this.textureFile + ".png").getImage();
	}

}
