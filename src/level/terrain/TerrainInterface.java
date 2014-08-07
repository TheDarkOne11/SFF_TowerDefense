package level.terrain;

import java.awt.Image;

public interface TerrainInterface {
	public int id = -1;
	public boolean isWalkable = false;
	public int xPos = -1;
	public int yPos = -1;
	
	public Image texture = null;
	
	/** Bu� bude k textur�m p�istupovat pomoc� terrain array nebo k nim bude p�istupovat p��mo ze souboru tzn. bude 
	 * 	si s�m vytv��et texturu (Image). */
	public void getTextureFile();
}
