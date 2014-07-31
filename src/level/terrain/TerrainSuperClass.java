package level.terrain;

/**
 * Nadt��da, ze kter� budou dal�� potomci krajin d�dit.
 * @author Petr
 *
 */
public abstract class TerrainSuperClass {
	public int id = -1;
	public boolean isWalkable = false;
	private int xPos, yPos;
	
	public TerrainSuperClass(int id) {
		this.id = id;
		this.isWalkable = false;
	}
	
	public TerrainSuperClass(int id, boolean isWalkable) {
		this.id = id;
		this.isWalkable = isWalkable;
	}
	
	public TerrainSuperClass(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public TerrainSuperClass(int id, boolean isWalkable, int xPos, int yPos) {
		this.id = id;
		this.isWalkable = isWalkable;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	/** Bu� bude k textur�m p�istupovat pomoc� terrain array nebo k nim bude p�istupovat p��mo ze souboru tzn. bude 
	 * 	si s�m vytv��et texturu (Image). */
	public abstract void getTextureFile();
}
