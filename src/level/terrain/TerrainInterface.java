package level.terrain;

import java.awt.Image;

public interface TerrainInterface {
	public int id = -1;
	public boolean isWalkable = false;
	public int xPos = -1;
	public int yPos = -1;
	
	public Image texture = null;
	
	/** Buï bude k texturám pøistupovat pomocí terrain array nebo k nim bude pøistupovat pøímo ze souboru tzn. bude 
	 * 	si sám vytváøet texturu (Image). */
	public void getTextureFile();
}
