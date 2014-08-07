package level.terrain;

import java.awt.Image;

import core.Screen;

public class Grass implements TerrainInterface {
	public static int id = 0;
	public boolean isWalkable = false;
	public Image texture = null;

	public void getTextureFile() {
		this.texture = Screen.terrain[this.id];
	}

}
