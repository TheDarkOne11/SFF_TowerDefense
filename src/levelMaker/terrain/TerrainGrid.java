package levelMaker.terrain;

import levelMaker.MyButton;
import core.Screen;

/** Abstraktn� metoda pro krajiny. */
public abstract class TerrainGrid extends MyButton {
	public int id;

	public TerrainGrid(int x, int y, int width, int height, int id) {
		super(x, y, width, height);
		this.id = id;
		this.getTextureFile();
	}

	public void getTextureFile() {
		this.texture = Screen.terrain.get(id);
	}

	public void action() {

	}

}