package levelMaker.buttons;

import javax.swing.ImageIcon;

import levelMaker.LevelMaker;
import levelMaker.MyButton;
import lib.PathVar;

/** Ub�r� hodnot� gridCountY 1. */
public class ButtonDown_GCY extends MyButton {
	private String textureFile = "ButtonDown";

	public ButtonDown_GCY(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.getTextureFile();
	}

	public void action() {
		if (LevelMaker.gridCountY > 1) {
			LevelMaker.lastGridCountY = LevelMaker.gridCountY;
			LevelMaker.gridCountY--;
		}
	}

	public void getTextureFile() {
		this.texture = new ImageIcon(PathVar.gridCountButtonPath + this.textureFile + ".png").getImage();
	}

}